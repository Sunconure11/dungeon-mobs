package com.gw.dm.entity;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.gw.dm.DungeonMobsHelper;
import com.gw.dm.EntityDungeonMob;
import com.gw.dm.projectile.EntityEyeRay;

import net.minecraft.block.Block;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
//import net.minecraft.entity.ai.EntityAINearestAttackableTargetSorter;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;

public class EntityCaveFisher extends EntityDungeonMob
{
	private int stringTimer;
	private int grabTimer;
	private EntityPlayer myTarget;
	
	public boolean ignoreHeight;
	
	public float myAngles[] = new float[4];
	// prev left, prev right, moar prev left, moar prev right
	
	public EntityCaveFisher(World par1World) 
	{
		super(par1World);
		this.setSize(1.4F, 0.9F);
        this.experienceValue = 20;
        this.stringTimer = 0;
        this.grabTimer = 0;
        this.ignoreHeight = false;
        
        for(int i = 0; i < 4; i++)
        {
        	this.myAngles[i] = 0.0F;
        }
	}
	
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.6D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0D);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.0D);
    }
	
	protected boolean isAIEnabled()
	{
		return false;
	}

	protected void entityInit()
    {
        super.entityInit();
        this.dataWatcher.addObject(16, new Byte((byte)0));
    }
	
	public void onUpdate()
    {
        super.onUpdate();

        if (!this.worldObj.isRemote)
            this.setBesideClimbableBlock(this.isCollidedHorizontally);
    }
	
	public boolean isOnLadder()
    {
        return this.isBesideClimbableBlock();
    }

    /**
     * Sets the Entity inside a web block.
     */
    public void setInWeb() 
    {
    	;
    }
    
    public boolean isBesideClimbableBlock()
    {
        return (this.dataWatcher.getWatchableObjectByte(16) & 1) != 0;
    }
    
    public void setBesideClimbableBlock(boolean par1)
    {
        byte var2 = this.dataWatcher.getWatchableObjectByte(16);

        if (par1)
            var2 = (byte)(var2 | 1);
        else
            var2 &= -2;

        this.dataWatcher.updateObject(16, Byte.valueOf(var2));
    }
	
	public int getTotalArmorValue()
	{
		return 8;
	}
	
	public int getAttackStrength(Entity par1Entity)
	{
		return 4;
	}
	
	protected String getLivingSound()
	{
		return "dungeonmobs:cf_l";
	}
	
	protected String getHurtSound()
	{
		return "dungeonmobs:cf_h";
	}
	
	protected String getDeathSound()
	{
		return "dungeonmobs:cf_d";
	}
	
	protected void playStepSound(int par1, int par2, int par3, int par4)
    {
        this.playSound("mob.spider.step", 0.15F, 1.0F);
    }
	
	public int getTalkInterval()
    {
        return 60;
    }
	
	public EnumCreatureAttribute getCreatureAttribute()
	{
		return EnumCreatureAttribute.ARTHROPOD;
	}
	
	public boolean getCanSpawnHere()
	{
		if(this.worldObj.canBlockSeeTheSky((int)this.posX, (int)this.posY, (int)this.posZ))
			return false;
		
		if(this.posY > 48.0D && !this.ignoreHeight)
			return false;
		
		return super.getCanSpawnHere();
	}
	
	protected void dropFewItems(boolean par1, int par2)
	{
		int var3;
		int var4;
		
		var3 = this.rand.nextInt(5) + 1;
		
		for(var4 = 0; var4 < var3; var4++)
	    {
			this.dropItem(Items.string, 1);
			
			//this.dropItem(Item.silk.itemID, 1);
	    }
	}
	
	public boolean isPotionApplicable(PotionEffect par1PotionEffect)
    {
        return par1PotionEffect.getPotionID() == Potion.poison.id ? false : super.isPotionApplicable(par1PotionEffect);
    }
	
	protected Entity findPlayerToAttack()
    {
        double var2 = 16.0D;
        /*
        this.myTarget = this.worldObj.getClosestVulnerablePlayerToEntity(this, var2);
        this.entityToAttack = this.myTarget;
        return this.worldObj.getClosestVulnerablePlayerToEntity(this, var2);
        */
        
        Entity foo = this.worldObj.getClosestVulnerablePlayerToEntity(this, var2);
        
        if(foo == null)
        {
        	List bar = this.worldObj.getEntitiesWithinAABB(EntityBat.class, this.boundingBox.expand(16.0F, 8.0F, 16.0F));
        	Iterator iter = bar.iterator();
        	
        	if(iter.hasNext())
        		return (Entity)iter.next();
        	
        	bar = null;
        }
        
        return foo;
    }
	
	public void onLivingUpdate()
	{
		this.stringTimer++;
		
		int foo = 160 - (DungeonMobsHelper.getDifficulty(this.worldObj) * 20);
		
		if(this.stringTimer - foo > 0)
		{
			if(this.getEntityToAttack() != null && this.canEntityBeSeen(this.getEntityToAttack()))
			{
				int tarX = (int)this.getEntityToAttack().posX;
				int tarY = (int)(this.getEntityToAttack().posY + 0.5);
				int tarZ = (int)this.getEntityToAttack().posZ;
				
				if(this.worldObj.getBlock(tarX, tarY, tarZ) == Blocks.air)
				{
					this.worldObj.setBlock(tarX, tarY, tarZ, Blocks.web, 0, 3);
					
					if(DungeonMobsHelper.getDifficulty(this.worldObj) > 2)
					{
						if(this.worldObj.getBlock(tarX - 1, tarY, tarZ) == Blocks.air)
							this.worldObj.setBlock(tarX - 1, tarY, tarZ, Blocks.web, 0, 3);
					
						if(this.worldObj.getBlock(tarX + 1, tarY, tarZ) == Blocks.air)
							this.worldObj.setBlock(tarX + 1, tarY, tarZ, Blocks.web, 0, 3);
						
						if(this.worldObj.getBlock(tarX, tarY, tarZ - 1) == Blocks.air)
							this.worldObj.setBlock(tarX, tarY, tarZ - 1, Blocks.web, 0, 3);
						
						if(this.worldObj.getBlock(tarX, tarY, tarZ + 1) == Blocks.air)
							this.worldObj.setBlock(tarX, tarY, tarZ + 1, Blocks.web, 0, 3);
						
						if(this.worldObj.getBlock(tarX, tarY + 1, tarZ) == Blocks.air)
							this.worldObj.setBlock(tarX, tarY + 1, tarZ, Blocks.web, 0, 3);
					}
				}
			}
			else
			{
				int tarX = (int)this.posX;
				int tarY = (int)(this.posY + 0.5);
				int tarZ = (int)this.posZ;
				
				if(this.worldObj.getBlock(tarX, tarY, tarZ) == Blocks.air)
					this.worldObj.setBlock(tarX, tarY, tarZ, Blocks.web, 0, 3);
				
				if(this.worldObj.getBlock(tarX - 1, tarY, tarZ) == Blocks.air)
					this.worldObj.setBlock(tarX - 1, tarY, tarZ, Blocks.web, 0, 3);
			
				if(this.worldObj.getBlock(tarX + 1, tarY, tarZ) == Blocks.air)
					this.worldObj.setBlock(tarX + 1, tarY, tarZ, Blocks.web, 0, 3);
				
				if(this.worldObj.getBlock(tarX, tarY, tarZ - 1) == Blocks.air)
					this.worldObj.setBlock(tarX, tarY, tarZ - 1, Blocks.web, 0, 3);
				
				if(this.worldObj.getBlock(tarX, tarY, tarZ + 1) == Blocks.air)
					this.worldObj.setBlock(tarX, tarY, tarZ + 1, Blocks.web, 0, 3);
				
				if(this.worldObj.getBlock(tarX, tarY + 1, tarZ) == Blocks.air)
					this.worldObj.setBlock(tarX, tarY + 1, tarZ, Blocks.web, 0, 3);
			}
			
			this.stringTimer = 0;
		}
		
		if(this.getEntityToAttack() != null && this.canEntityBeSeen(this.getEntityToAttack()))
		{
			this.grabTimer++;
			
			int bork = this.rand.nextInt(4 - DungeonMobsHelper.getDifficulty(this.worldObj));
		
			if(this.grabTimer == (foo * 3) - 16)
				this.worldObj.playSoundAtEntity(this, "dungeonmobs:cf_c", 1.0F, (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F + 1.0F);
			
			if(bork == 0 && this.grabTimer == (foo * 3) - 8)
				this.worldObj.playSoundAtEntity(this, "mob.slime.small", 1.0F, (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F + 1.0F);
			
			if(this.grabTimer >= (foo * 3))
			{
				if(bork == 0)
				{
					double difX = this.getEntityToAttack().posX - this.posX;
					double difZ;
					for (difZ = this.getEntityToAttack().posZ - this.posZ; difX * difX + difZ * difZ < 1.0E-4D; difZ = (Math.random() - Math.random()) * 0.01D)
			        {
			            difX = (Math.random() - Math.random()) * 0.01D;
			        }
					
					this.worldObj.playSoundAtEntity(this, "dungeonmobs:cf_r", 1.0F, (this.worldObj.rand.nextFloat() - this.worldObj.rand.nextFloat()) * 0.2F + 1.0F);
					
					if(!(this.getEntityToAttack() instanceof EntityPlayer))
						this.grabTarget(this.getEntityToAttack(), difX, difZ);
					else
					{
						EntityPlayer crap = (EntityPlayer)this.getEntityToAttack();
						
						if(!crap.capabilities.isCreativeMode)
						{
							//if(FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
							if(!(this.getEntityToAttack() instanceof EntityPlayerMP))
							{
								int moo = FMLClientHandler.instance().getClient().thePlayer.getEntityId();
								int cow = this.getEntityToAttack().getEntityId();
								
								if(moo == cow)
									this.grabTarget(FMLClientHandler.instance().getClient().thePlayer, difX, difZ);
							}
							else
								DungeonMobsHelper.sendKnockBackPacket((EntityPlayerMP)this.getEntityToAttack(), difX, difZ);
						}
					}
				}
					
				this.grabTimer = 0;
			}
		}
		
		super.onLivingUpdate();
	}
	
	public void grabTarget(EntityClientPlayerMP ent, double x, double z)
	{
		ent.isAirBorne = true;
        float var7 = MathHelper.sqrt_double(x * x + z * z);
        float var8 = 1.0F;
        ent.motionX /= 2.0D;
        ent.motionY /= 2.0D;
        ent.motionZ /= 2.0D;
        ent.motionX -= x / (double)var7 * (double)var8;
        ent.motionY += (double)var8;
        ent.motionZ -= z / (double)var7 * (double)var8;

        if (ent.motionY > 0.4000000059604645D)
        {
            ent.motionY = 0.4000000059604645D;
        }
	}
	
	public void grabTarget(EntityPlayerMP ent, double x, double z)
	{
		// PURE!
		ent.isAirBorne = true;
        float var7 = MathHelper.sqrt_double(x * x + z * z);
        float var8 = 1.0F;
        ent.motionX /= 2.0D;
        ent.motionY /= 2.0D;
        ent.motionZ /= 2.0D;
        ent.motionX -= x / (double)var7 * (double)var8;
        ent.motionY += (double)var8;
        ent.motionZ -= z / (double)var7 * (double)var8;

        if (ent.motionY > 0.4000000059604645D)
        {
            ent.motionY = 0.4000000059604645D;
        }
	}
	
	public void grabTarget(Entity ent, double x, double z)
	{
		ent.isAirBorne = true;
        float var7 = MathHelper.sqrt_double(x * x + z * z);
        float var8 = 1.0F;
        ent.motionX /= 2.0D;
        ent.motionY /= 2.0D;
        ent.motionZ /= 2.0D;
        ent.motionX -= x / (double)var7 * (double)var8;
        ent.motionY += (double)var8;
        ent.motionZ -= z / (double)var7 * (double)var8;

        if (ent.motionY > 0.4000000059604645D)
        {
            ent.motionY = 0.4000000059604645D;
        }
	}
	
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);
        
        par1NBTTagCompound.setInteger("StringTimer", this.stringTimer);
        par1NBTTagCompound.setInteger("GrabTimer", this.grabTimer);
    }
	
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readEntityFromNBT(par1NBTTagCompound);

        this.stringTimer  = par1NBTTagCompound.getInteger("StringTimer");
        this.grabTimer    = par1NBTTagCompound.getInteger("GrabTimer");
    }
	
	public boolean attackEntityFrom(DamageSource par1DamageSource, int par2)
    {
		if(par1DamageSource.getEntity() != null && (par1DamageSource.getEntity() instanceof EntityLiving && !(par1DamageSource.getEntity() instanceof EntityEyeRay)))
		{
			if(par1DamageSource.getEntity() instanceof EntityThrowable)
			{
				EntityThrowable foo = (EntityThrowable)par1DamageSource.getEntity();
				this.entityToAttack = (Entity)foo.getThrower();
			}
			else
				this.entityToAttack = par1DamageSource.getEntity();
		}

		return super.attackEntityFrom(par1DamageSource, par2);
    }
}
