package com.gw.dm.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.world.World;

import com.gw.dm.DungeonMobsDamageSource;
import com.gw.dm.DungeonMobsHelper;
import com.gw.dm.EntityDungeonMob;

public class EntityCockatrice extends EntityDungeonMob 
{
	private boolean ignoreHeight;
	private int stoneChance;
	
	private EntityPetrified stonedPlayer;
	private boolean incoming;
	private boolean waitTick;
	
	public EntityCockatrice(World par1World) 
	{
		super(par1World);
		this.experienceValue = 15;
		this.ignoreHeight = false;
		this.setSize(1.2F, 1.5F);
		
		if(this.worldObj != null)
			this.stoneChance = 6 * DungeonMobsHelper.getDifficulty(this.worldObj);
		else
			this.stoneChance = 15;
		
		this.incoming = false;
		this.stonedPlayer = null;
		this.waitTick = false;
		
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0F, false));
		this.tasks.addTask(3, new EntityAIWander(this, 1.0F));
        this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(4, new EntityAILookIdle(this));
		this.targetTasks.addTask(0, new EntityAIHurtByTarget(this, false));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
	}
	
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(25.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.4D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(3.0D);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.0D);
    }

	protected boolean isAIEnabled()
	{
		return true;
	}

	public int getTotalArmorValue()
	{
		return 2;
	}
	
	public int getAttackStrength(Entity par1Entity)
	{
		return 3;
	}
	
	public boolean getCanSpawnHere()
	{
		if(this.worldObj.canBlockSeeTheSky((int)this.posX, (int)this.posY, (int)this.posZ))
			return false;
		
		if(this.posY > 64.0D && !this.ignoreHeight)
			return false;
		
		return super.getCanSpawnHere();
	}
	
	protected void dropFewItems(boolean par1, int par2)
	{
		int var3;
		int var5;
		
		var3 = this.rand.nextInt(3);
        
        for(var5 = 0; var5 < var3; var5++)
        {
        	this.dropItem(Items.feather, 1);
        }
	}
	
	public boolean attackEntityAsMob(Entity ent)
	{
		int moo = this.worldObj.rand.nextInt(100) + 1; 
		
		if(moo < this.stoneChance && ent instanceof EntityPlayer && !this.worldObj.isRemote && ent.ticksExisted > 100)
		{
			EntityPlayer cow = (EntityPlayer)ent;
			
			if(!cow.capabilities.isCreativeMode)
			{
				this.worldObj.playSoundAtEntity(ent, "dungeonmobs:t_s", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
				
				EntityPetrified foo = new EntityPetrified(this.worldObj);
				EntityPlayer bar = (EntityPlayer)ent;
				
				foo.setLocationAndAngles(bar.posX, bar.posY, bar.posZ, bar.rotationYaw, bar.rotationPitch);
				
				foo.setStuff(bar);
				
				//bar.addPotionEffect(new PotionEffect(Potion.harm.id, 1, 100));
				//ent.setDead();
				
				bar.attackEntityFrom(DungeonMobsDamageSource.petrified, 100);
				
				this.incoming = true;
				this.waitTick = true;
				this.stonedPlayer = foo;
			}
		}
		else if(moo < this.stoneChance && !this.worldObj.isRemote)
		{
			int a = (int)ent.posX;
			int b = (int)ent.boundingBox.minY;
			int c = (int)ent.posZ;
			
			ent.setDead();
			this.worldObj.playSoundAtEntity(ent, "dungeonmobs:t_s", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
			
			this.worldObj.setBlock(a, b, c, Blocks.stone);
			this.worldObj.setBlock(a, b + 1, c, Blocks.stone);
		}
		
		return super.attackEntityAsMob(ent);
	}
	
	public int getTalkInterval()
    {
        return 80;
    }
	
	protected String getLivingSound()
	{
		return "dungeonmobs:co_l";
	}
	
	protected String getHurtSound()
	{
		return "dungeonmobs:co_h";
	}
	
	protected String getDeathSound()
	{
		return "dungeonmobs:co_h";
	}
	
	public void onEntityUpdate()
	{
		super.onEntityUpdate();
		
		if(this.incoming && !this.worldObj.isRemote)
		{
			if(this.waitTick)
				this.waitTick = false;
			else
			{
				this.worldObj.spawnEntityInWorld(this.stonedPlayer);
				
				this.incoming = false;
				this.stonedPlayer = null;
			}
		}
	}
}
