package com.gw.dm.entity;

import com.gw.dm.DungeonMobsHelper;
import com.gw.dm.EntityDungeonMob;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityManticore extends EntityDungeonMob implements IRangedAttackMob 
{
	public EntityManticore(World par1World) 
	{
		super(par1World);
		this.experienceValue = 32;
		
		this.setSize(1.6F, 1.4F);
		
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIArrowAttack(this, 0.3F, 10, 50, 40.0F));
		this.tasks.addTask(3, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0F, false));
		this.tasks.addTask(4, new EntityAIWander(this, 1.0F));
        this.tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(5, new EntityAILookIdle(this));
        
        this.targetTasks.addTask(0, new EntityAIHurtByTarget(this, false));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
	}

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase entityliving, float f) 
	{
		int foo = this.getRNG().nextInt(6 - DungeonMobsHelper.getDifficulty(this.worldObj));
		foo += (DungeonMobsHelper.getDifficulty(this.worldObj) * 2);
		
		for(int i = 0; i < foo; i++)
		{
			EntityArrow entityarrow = new EntityArrow(this.worldObj, this, entityliving, 1.6F, (float)(20 + foo));
			
			entityarrow.posX += ((this.getRNG().nextFloat() - 0.5) / 2);
			entityarrow.posY += ((this.getRNG().nextFloat() - 0.5) / 2);
			entityarrow.posZ += ((this.getRNG().nextFloat() - 0.5) / 2);
			
			entityarrow.setDamage(Math.max(2, DungeonMobsHelper.getDifficulty(this.worldObj) * 3));
			
	        this.worldObj.spawnEntityInWorld(entityarrow);
		}
		
		this.playSound("dungeonmobs:ma_s", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
	}
	
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(30.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.3D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(0.0D);
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
		return 3;
	}
	
	protected String getLivingSound()
	{
		int foo = this.getRNG().nextInt(2);
		
		if(foo == 0)
			return "dungeonmobs:ma_l";
		else
			return "dungeonmobs:ma_l2";
	}
	
	protected String getHurtSound()
	{
		return "dungeonmobs:ma_h";
	}
	
	protected String getDeathSound()
	{
		return "dungeonmobs:ma_d";
	}
	
	public boolean getCanSpawnHere()
	{
		if(this.worldObj.canBlockSeeTheSky((int)this.posX, (int)this.posY, (int)this.posZ))
			return false;
		
		if(this.posY > 42.0D && !this.ignoreHeight)
			return false;
		
		return super.getCanSpawnHere();
	}
	
	public int getTalkInterval()
    {
        return 100;
    }
	
	protected void dropFewItems(boolean par1, int par2)
	{
		this.dropItem(Items.arrow, this.rand.nextInt(12) + this.rand.nextInt(12));
		this.dropItem(Items.leather, this.rand.nextInt(3));
	}
	
	public boolean attackEntityFrom(DamageSource par1DamageSource, int par2)
    {
		if(par1DamageSource.getEntity() != null && par1DamageSource.getEntity().equals(this))
			return false;
		
		return super.attackEntityFrom(par1DamageSource, par2);
    }
}
