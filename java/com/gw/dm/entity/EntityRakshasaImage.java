package com.gw.dm.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityRakshasaImage extends EntityCreature implements IRangedAttackMob
{
	private int timeToLive;
	public EntityRakshasa owner;
	
	public EntityRakshasaImage(World par1World) 
	{
		super(par1World);
		this.experienceValue = 0;
		this.timeToLive = 240;
		this.owner = null;
		
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIArrowAttack(this, 1.0F, 40, 16.0F));
		this.tasks.addTask(3, new EntityAIWander(this, 1.0F));
        this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(5, new EntityAILookIdle(this));
        this.targetTasks.addTask(0, new EntityAIHurtByTarget(this, false));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
	}
	
	public EntityRakshasaImage(World par1World, int par2) 
	{
		this(par1World);
		this.timeToLive = par2;
	}
	
	public EntityRakshasaImage(World par1World, EntityLivingBase foo) 
	{
		this(par1World);
		this.setAttackTarget(foo);
	}
	
	public EntityRakshasaImage(World par1World, EntityLivingBase foo, EntityRakshasa bar) 
	{
		this(par1World);
		this.setAttackTarget(foo);
		this.owner = bar;
	}
	
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(42.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3D);
        //this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(0.0D);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.0D);
    }
	
	public int getAttackStrength(Entity par1Entity)
	{
		return 0;
	}

	public boolean getCanSpawnHere()
	{
		return super.getCanSpawnHere();
		
		/*
		int var1 = MathHelper.floor_double(this.posX);
        int var2 = MathHelper.floor_double(this.boundingBox.minY);
        int var3 = MathHelper.floor_double(this.posZ);
        
        return this.worldObj.checkIfAABBIsClear(this.boundingBox) && 
        		this.worldObj.getCollidingBoundingBoxes(this, this.boundingBox).isEmpty() && 
        		!this.worldObj.isAnyLiquid(this.boundingBox) && 
        		this.getBlockPathWeight(var1, var2, var3) >= 0.0F;
        */
	}
	
	protected String getLivingSound()
	{
		return null;
		//return "dungeonmobs:ra_l";
	}
	
	protected String getHurtSound()
	{
		return null;
		//return "dungeonmobs:ra_h";
	}
	
	protected String getDeathSound()
	{
		return null;
		//return "dungeonmobs:ra_h";
	}
	
	protected boolean isAIEnabled()
	{
		return true;
	}
	
	public void onLivingUpdate()
	{
		this.timeToLive--;
		
		if(this.timeToLive == 0)
			this.setDead();
		
		super.onLivingUpdate();
	}
	
	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2)
	{
		this.setDead();
		
		return false;
	}	 
	
	protected void dropFewItems(boolean par1, int par2)
	{
		;
	}

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase var1, float lol) 
	{
		;	
	}
	
	public void setDead()
	{
		//this.worldObj.spawnParticle("hugeexplosion", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
		this.worldObj.spawnParticle("explode", this.posX, this.posY + 1.5D, this.posZ, 0.0D, 0.0D, 0.0D);
		
		for(int i = 0; i < 8; i++)
		{
			double foo = this.worldObj.rand.nextDouble() - this.worldObj.rand.nextDouble();
			
			this.worldObj.spawnParticle("explode", this.posX + foo, this.posY + 1.0D + foo, this.posZ + foo, 0.0D, 0.0D, 0.0D);
		}
		
		this.worldObj.playSoundEffect(this.posX + 0.5D, this.posY + 0.5D, this.posZ + 0.5D, "random.fizz", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
		
		if(this.owner != null)
			this.owner.destroyImage(this);
		
		super.setDead();
	}
	
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);
        
        par1NBTTagCompound.setInteger("timeToLive", this.timeToLive);
    }
	
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readEntityFromNBT(par1NBTTagCompound);

        this.timeToLive  = par1NBTTagCompound.getInteger("timeToLive");
    }
}
