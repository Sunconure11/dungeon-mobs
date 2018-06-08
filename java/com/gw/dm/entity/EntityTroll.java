package com.gw.dm.entity;

import com.gw.dm.DungeonMobsHelper;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIFleeSun;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIRestrictSun;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

public class EntityTroll extends EntityMob 
{
	public int stoneStatus = 1;
	public int stoneCounter = 0;
	public int regenTimer = 0;
	
	public EntityTroll(World par1World) 
	{
		super(par1World);
		this.experienceValue = 40;
		
		this.setSize(1.3F, 2.5F);
		
		this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIRestrictSun(this));
        this.tasks.addTask(2, new EntityAIFleeSun(this, 1.0F));
        this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0F, false));
        this.tasks.addTask(4, new EntityAIAttackOnCollide(this, EntityAnimal.class, 1.0F, false));
        this.tasks.addTask(5, new EntityAIWander(this, 1.0F));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityAnimal.class, 8.0F));
        this.tasks.addTask(6, new EntityAILookIdle(this));
        
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityAnimal.class, 0, true));
	}
	
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.22D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(5.0D);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.0D);
    }

	protected boolean isAIEnabled()
	{
		return true;
	}
	
	public int getTotalArmorValue()
	{
		return 6;
	}
	
	public int getAttackStrength(Entity par1Entity)
	{
		return 5;
	}
	
	protected String getLivingSound()
	{
		return "dungeonmobs:t_l";
	}
	
	protected String getHurtSound()
	{
		return "dungeonmobs:t_h";
	}
	
	protected String getDeathSound()
	{
		return "dungeonmobs:t_d";
	}
	
	protected void playStepSound(int par1, int par2, int par3, int par4)
    {
		this.playSound("dungeonmobs:bt_s", 0.15F, 1.0F);
    }
	
	public int getTalkInterval()
    {
        return 80;
    }
	
	public boolean getCanSpawnHere()
	{
		if(this.worldObj.canBlockSeeTheSky((int)this.posX, (int)this.posY, (int)this.posZ))
			return false;
		
		if(!this.isValidLightLevel())
			return false;
		
		return super.getCanSpawnHere();
	}
	
	@Override
	protected boolean isValidLightLevel()
    {
        int var1 = MathHelper.floor_double(this.posX);
        int var2 = MathHelper.floor_double(this.boundingBox.minY);
        int var3 = MathHelper.floor_double(this.posZ);

        if (this.worldObj.getSavedLightValue(EnumSkyBlock.Sky, var1, var2, var3) > this.rand.nextInt(32))
            return false;
        else
        {
            int var4 = this.worldObj.getBlockLightValue(var1, var2, var3);

            if (this.worldObj.isThundering())
            {
                int var5 = this.worldObj.skylightSubtracted;
                this.worldObj.skylightSubtracted = 10;
                var4 = this.worldObj.getBlockLightValue(var1, var2, var3);
                this.worldObj.skylightSubtracted = var5;
            }

            return var4 <= this.rand.nextInt(5);
        }
    }
	
	public void onLivingUpdate()
    {
			if (this.worldObj.isDaytime() && !this.worldObj.isRemote)
	        {
	            float var1 = this.getBrightness(1.0F);
	
	            if (var1 > 0.5F && this.worldObj.canBlockSeeTheSky(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY), MathHelper.floor_double(this.posZ)))
	            {
	            	this.stoneCounter++;
	            	
	            	int foo = 20 + (DungeonMobsHelper.getDifficulty(this.worldObj) * 15);
	            	
	            	if(this.stoneCounter % foo == 0)
	            	{
	            		this.stoneStatus++;
	            		
	            		if(this.stoneStatus > (7 + DungeonMobsHelper.getDifficulty(this.worldObj)))
	            			this.stoneStatus = 7 + DungeonMobsHelper.getDifficulty(this.worldObj);
	            	}
	            	
	            	foo = 40 + (DungeonMobsHelper.getDifficulty(this.worldObj) * 20);
	            	
	            	if(this.stoneCounter % foo == 0)
	            	{
	            		if(!this.isPotionActive(Potion.moveSlowdown))
		            		this.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, foo + (60 - (4 * DungeonMobsHelper.getDifficulty(this.worldObj))), DungeonMobsHelper.getDifficulty(this.worldObj) + 1));
	            	}
	            	
	            	if(this.stoneStatus >= (7 + DungeonMobsHelper.getDifficulty(this.worldObj)))
	            	{
	            		this.worldObj.playSoundAtEntity(this, "dungeonmobs:t_s", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
	            		this.turnToStone();
	            	}
	            }
	        }
	        else if(!this.worldObj.isRemote)
	        {
	        	if(this.stoneCounter > 0)
	        	{
	        		for(int i = 0; i < (2 + DungeonMobsHelper.getDifficulty(this.worldObj)); i++)
	        		{
		        		this.stoneCounter--;
		        		
		        		int foo = 20 + (DungeonMobsHelper.getDifficulty(this.worldObj) * 15);
		        		
		        		if(this.stoneCounter % foo == 0)
		            	{
		            		this.stoneStatus--;
		            		
		            		if(this.stoneStatus < 1)
		            			stoneStatus = 1;
		            	}
	        		}
	        	}
	        }
	        
			if(!this.isBurning())
			{
				if(!this.isPotionActive(Potion.regeneration))
					this.addPotionEffect(new PotionEffect(Potion.regeneration.id, 60, DungeonMobsHelper.getDifficulty(this.worldObj) + 3));
			}
			else
				if(this.isPotionActive(Potion.regeneration))
					this.removePotionEffect(Potion.regeneration.id);
			
			/*
	        if(!this.isBurning() && this.regenTimer > 0)
        	{
	        	if(this.getHealth() < this.getMaxHealth())
	        	{
	        		this.regenTimer++;
	        		
	        		if(this.regenTimer > 19)
	        		{
	        			this.health += DungeonMobsHelper.getDifficulty(this.worldObj) + 1;
	        			
	        			if(this.health > this.getMaxHealth())
	        				this.health = this.getMaxHealth();
	        			
	        			this.regenTimer = 1;
	        		}
	        	}
        	}
        	*/
	        
	        super.onLivingUpdate();
    }

	private void turnToStone() 
	{
		if(!this.worldObj.isRemote)
		{
			this.regenTimer = -1;
			
			this.setDead();
			
			this.worldObj.setBlock((int)this.posX, (int)this.boundingBox.minY, (int)this.posZ, Blocks.stone);
			this.worldObj.setBlock((int)this.posX, (int)this.boundingBox.minY + 1, (int)this.posZ, Blocks.stone);
			this.worldObj.spawnParticle("smoke", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
			this.worldObj.spawnParticle("smoke", this.posX, this.posY + 1, this.posZ, 0.0D, 0.0D, 0.0D);
		}
	}
	
	protected void dropFewItems(boolean par1, int par2)
	{
		int var3;
		int var4;
		
		var3 = this.rand.nextInt(3);
		
		for(var4 = 0; var4 < var3; var4++)
	    {
			this.dropItem(Items.leather, 1);
	    }
	}
	
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);
        
        par1NBTTagCompound.setInteger("StoneStatus", this.stoneStatus);
        par1NBTTagCompound.setInteger("StoneCounter", this.stoneCounter);
        par1NBTTagCompound.setInteger("RegenTimer", this.regenTimer);
    }
	
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readEntityFromNBT(par1NBTTagCompound);

        this.stoneStatus  = par1NBTTagCompound.getInteger("StoneStatus");
        this.stoneCounter = par1NBTTagCompound.getInteger("StoneCounter");
        this.regenTimer   = par1NBTTagCompound.getInteger("RegenTimer");
    }
	
	public void setDead()
	{
		this.regenTimer = -1;
		
		super.setDead();
	}
}
