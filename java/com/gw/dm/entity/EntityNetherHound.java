package com.gw.dm.entity;

import com.gw.dm.DungeonMobsHelper;
import com.gw.dm.EntityDungeonMob;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderHell;

public class EntityNetherHound extends EntityDungeonMob 
{
	private int flameTimer;
	private int jumpTimer;
	public boolean ignoreHeight;

	public EntityNetherHound(World par1World) 
	{
		super(par1World);
		this.flameTimer = 0;
		this.jumpTimer = 0;
		this.ignoreHeight = false;
		this.experienceValue = 32;
		this.isImmuneToFire = true;
		
		this.setSize(1.2F, 1.1F);
		
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0F, false));
		this.tasks.addTask(3, new EntityAIWander(this, 1.0F));
        this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(4, new EntityAILookIdle(this));
		this.targetTasks.addTask(0, new EntityAIHurtByTarget(this, false));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
	}

	protected boolean isAIEnabled()
	{
		return true;
	}
	
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(28.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.42D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(5.0D);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.0D);
    }

	public int getTotalArmorValue()
	{
		return 5;
	}
	
	public int getAttackStrength(Entity par1Entity)
	{
		return 5;
	}
	
	protected String getLivingSound()
	{
		return "dungeonmobs:nh_l";
	}
	
	protected String getHurtSound()
	{
		return "dungeonmobs:nh_h";
	}
	
	protected String getDeathSound()
	{
		return null;
	}
	
	public boolean getCanSpawnHere()
	{
		if(this.worldObj.provider instanceof WorldProviderHell)
			return super.getCanSpawnHere();
		
		if(this.worldObj.canBlockSeeTheSky((int)this.posX, (int)this.posY, (int)this.posZ))
			return false;
		
		if(this.posY > 48.0D && !this.ignoreHeight)
			return false;
		
		return super.getCanSpawnHere();
	}
	
	public int getTalkInterval()
    {
        return 80;
    }
	
	protected void dropFewItems(boolean par1, int par2)
	{
		int var3;
		int var4;
		
		var3 = this.rand.nextInt(2);

        for (var4 = 0; var4 < var3; var4++)
        {
            this.dropItem(Items.blaze_powder, 1);
        }
	}
	
	public boolean attackEntityAsMob(Entity ent)
	{
		ent.setFire(8 + (DungeonMobsHelper.getDifficulty(this.worldObj) * 2));
		
		return super.attackEntityAsMob(ent);
	}
	
	public void onLivingUpdate()
	{
		if(this.getAttackTarget() != null && this.canEntityBeSeen(this.getAttackTarget()))
		{
			double barX = this.getAttackTarget().posX - this.posX;
			double barY = this.getAttackTarget().posY - this.posY;
			double barZ = this.getAttackTarget().posZ - this.posZ;
					
			double fooX = barX * barX;
			double fooY = barY * barY;
			double fooZ = barZ * barZ;
					
			if(fooX + fooY + fooZ <= 16.0D)
			{
				this.flameTimer++;
						
				if(this.flameTimer >= (170 - (40 * DungeonMobsHelper.getDifficulty(this.worldObj))))
					this.worldObj.playSoundAtEntity(this, "dungeonmobs:nh_i", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
					
				if(this.flameTimer >= (200 - (40 * DungeonMobsHelper.getDifficulty(this.worldObj))))
				{
					this.breatheFire();
					this.flameTimer = 0;
				}
			}
		}
		
		super.onLivingUpdate();
	}
	
	public void breatheFire()
	{
		int dir = MathHelper.floor_double((double)((this.rotationYaw * 4F) / 360F) + 0.5D) & 3;
		
		boolean onAngle;
		
		float angle = (float)((this.rotationYaw * 4F) / 360F) + 0.5F;
		
		while(angle > 1.0F)
		{
			angle -= 1.0;
		}
		
		if(angle <= 0.75F && angle >= 0.25F)
			onAngle = true;
		else
			onAngle = false;
		
		int myX = (int)this.posX;
		int myY = (int)this.posY;
		int myZ = (int)this.posZ;
		
		if(!onAngle)
		{
			if(dir == 0) // SOUTH, +Z
			{
				this.makeFire(myX, myY, myZ + 1);
				
				this.makeFire(myX - 1, myY - 1, myZ + 2);
				this.makeFire(myX, myY - 1, myZ + 2);
				this.makeFire(myX + 1, myY - 1, myZ + 2);
				
				this.makeFire(myX - 1, myY, myZ + 2);
				this.makeFire(myX, myY, myZ + 2);
				this.makeFire(myX + 1, myY, myZ + 2);
				
				this.makeFire(myX - 1, myY + 1, myZ + 2);
				this.makeFire(myX, myY + 1, myZ + 2);
				this.makeFire(myX + 1, myY + 1, myZ + 2);
				
				this.makeFire(myX - 2, myY - 2, myZ + 3);
				this.makeFire(myX - 1, myY - 2, myZ + 3);
				this.makeFire(myX, myY - 2, myZ + 3);
				this.makeFire(myX + 1, myY - 2, myZ + 3);
				this.makeFire(myX + 2, myY - 2, myZ + 3);
				
				this.makeFire(myX - 2, myY - 1, myZ + 3);
				this.makeFire(myX - 1, myY - 1, myZ + 3);
				this.makeFire(myX, myY - 1, myZ + 3);
				this.makeFire(myX + 1, myY - 1, myZ + 3);
				this.makeFire(myX + 2, myY - 1, myZ + 3);
				
				this.makeFire(myX - 2, myY, myZ + 3);
				this.makeFire(myX - 1, myY, myZ + 3);
				this.makeFire(myX, myY, myZ + 3);
				this.makeFire(myX + 1, myY, myZ + 3);
				this.makeFire(myX + 2, myY, myZ + 3);
				
				this.makeFire(myX - 2, myY + 1, myZ + 3);
				this.makeFire(myX - 1, myY + 1, myZ + 3);
				this.makeFire(myX, myY + 1, myZ + 3);
				this.makeFire(myX + 1, myY + 1, myZ + 3);
				this.makeFire(myX + 2, myY + 1, myZ + 3);
				
				this.makeFire(myX - 2, myY + 2, myZ + 3);
				this.makeFire(myX - 1, myY + 2, myZ + 3);
				this.makeFire(myX, myY + 2, myZ + 3);
				this.makeFire(myX + 1, myY + 2, myZ + 3);
				this.makeFire(myX + 2, myY + 2, myZ + 3);
			}
			else if(dir == 1) // WEST, -X
			{
				this.makeFire(myX - 1, myY, myZ);
				
				this.makeFire(myX - 2, myY - 1, myZ - 1);
				this.makeFire(myX - 2, myY - 1, myZ);
				this.makeFire(myX - 2, myY - 1, myZ + 1);
				
				this.makeFire(myX - 2, myY, myZ - 1);
				this.makeFire(myX - 2, myY, myZ);
				this.makeFire(myX - 2, myY, myZ + 1);
				
				this.makeFire(myX - 2, myY + 1, myZ - 1);
				this.makeFire(myX - 2, myY + 1, myZ);
				this.makeFire(myX - 2, myY + 1, myZ + 1);
				
				this.makeFire(myX - 3, myY - 2, myZ - 2);
				this.makeFire(myX - 3, myY - 2, myZ - 1);
				this.makeFire(myX - 3, myY - 2, myZ);
				this.makeFire(myX - 3, myY - 2, myZ + 1);
				this.makeFire(myX - 3, myY - 2, myZ + 2);
				
				this.makeFire(myX - 3, myY - 1, myZ - 2);
				this.makeFire(myX - 3, myY - 1, myZ - 1);
				this.makeFire(myX - 3, myY - 1, myZ);
				this.makeFire(myX - 3, myY - 1, myZ + 1);
				this.makeFire(myX - 3, myY - 1, myZ + 2);
				
				this.makeFire(myX - 3, myY, myZ - 2);
				this.makeFire(myX - 3, myY, myZ - 1);
				this.makeFire(myX - 3, myY, myZ);
				this.makeFire(myX - 3, myY, myZ + 1);
				this.makeFire(myX - 3, myY, myZ + 2);
				
				this.makeFire(myX - 3, myY + 1, myZ - 2);
				this.makeFire(myX - 3, myY + 1, myZ - 1);
				this.makeFire(myX - 3, myY + 1, myZ);
				this.makeFire(myX - 3, myY + 1, myZ + 1);
				this.makeFire(myX - 3, myY + 1, myZ + 2);
				
				this.makeFire(myX - 3, myY + 2, myZ - 2);
				this.makeFire(myX - 3, myY + 2, myZ - 1);
				this.makeFire(myX - 3, myY + 2, myZ);
				this.makeFire(myX - 3, myY + 2, myZ + 1);
				this.makeFire(myX - 3, myY + 2, myZ + 2);
			}
			else if(dir == 2) // NORTH, -Z
			{
				this.makeFire(myX, myY, myZ - 1);
				
				this.makeFire(myX - 1, myY - 1, myZ - 2);
				this.makeFire(myX, myY - 1, myZ - 2);
				this.makeFire(myX + 1, myY - 1, myZ - 2);
				
				this.makeFire(myX - 1, myY, myZ - 2);
				this.makeFire(myX, myY, myZ - 2);
				this.makeFire(myX + 1, myY, myZ - 2);
				
				this.makeFire(myX - 1, myY + 1, myZ - 2);
				this.makeFire(myX, myY + 1, myZ - 2);
				this.makeFire(myX + 1, myY + 1, myZ - 2);
				
				this.makeFire(myX - 2, myY - 2, myZ - 3);
				this.makeFire(myX - 1, myY - 2, myZ - 3);
				this.makeFire(myX, myY - 2, myZ - 3);
				this.makeFire(myX + 1, myY - 2, myZ - 3);
				this.makeFire(myX + 2, myY - 2, myZ - 3);
				
				this.makeFire(myX - 2, myY - 1, myZ - 3);
				this.makeFire(myX - 1, myY - 1, myZ - 3);
				this.makeFire(myX, myY - 1, myZ - 3);
				this.makeFire(myX + 1, myY - 1, myZ - 3);
				this.makeFire(myX + 2, myY - 1, myZ - 3);
				
				this.makeFire(myX - 2, myY, myZ - 3);
				this.makeFire(myX - 1, myY, myZ - 3);
				this.makeFire(myX, myY, myZ - 3);
				this.makeFire(myX + 1, myY, myZ - 3);
				this.makeFire(myX + 2, myY, myZ - 3);
				
				this.makeFire(myX - 2, myY + 1, myZ - 3);
				this.makeFire(myX - 1, myY + 1, myZ - 3);
				this.makeFire(myX, myY + 1, myZ - 3);
				this.makeFire(myX + 1, myY + 1, myZ - 3);
				this.makeFire(myX + 2, myY + 1, myZ - 3);
				
				this.makeFire(myX - 2, myY + 2, myZ - 3);
				this.makeFire(myX - 1, myY + 2, myZ - 3);
				this.makeFire(myX, myY + 2, myZ - 3);
				this.makeFire(myX + 1, myY + 2, myZ - 3);
				this.makeFire(myX + 2, myY + 2, myZ - 3);
			}
			else // EAST, +X
			{
				this.makeFire(myX + 1, myY, myZ);
				
				this.makeFire(myX + 2, myY - 1, myZ - 1);
				this.makeFire(myX + 2, myY - 1, myZ);
				this.makeFire(myX + 2, myY - 1, myZ + 1);
				
				this.makeFire(myX + 2, myY, myZ - 1);
				this.makeFire(myX + 2, myY, myZ);
				this.makeFire(myX + 2, myY, myZ + 1);
				
				this.makeFire(myX + 2, myY + 1, myZ - 1);
				this.makeFire(myX + 2, myY + 1, myZ);
				this.makeFire(myX + 2, myY + 1, myZ + 1);
				
				this.makeFire(myX + 3, myY - 2, myZ - 2);
				this.makeFire(myX + 3, myY - 2, myZ - 1);
				this.makeFire(myX + 3, myY - 2, myZ);
				this.makeFire(myX + 3, myY - 2, myZ + 1);
				this.makeFire(myX + 3, myY - 2, myZ + 2);
				
				this.makeFire(myX + 3, myY - 1, myZ - 2);
				this.makeFire(myX + 3, myY - 1, myZ - 1);
				this.makeFire(myX + 3, myY - 1, myZ);
				this.makeFire(myX + 3, myY - 1, myZ + 1);
				this.makeFire(myX + 3, myY - 1, myZ + 2);
				
				this.makeFire(myX + 3, myY, myZ - 2);
				this.makeFire(myX + 3, myY, myZ - 1);
				this.makeFire(myX + 3, myY, myZ);
				this.makeFire(myX + 3, myY, myZ + 1);
				this.makeFire(myX + 3, myY, myZ + 2);
				
				this.makeFire(myX + 3, myY + 1, myZ - 2);
				this.makeFire(myX + 3, myY + 1, myZ - 1);
				this.makeFire(myX + 3, myY + 1, myZ);
				this.makeFire(myX + 3, myY + 1, myZ + 1);
				this.makeFire(myX + 3, myY + 1, myZ + 2);
				
				this.makeFire(myX + 3, myY + 2, myZ - 2);
				this.makeFire(myX + 3, myY + 2, myZ - 1);
				this.makeFire(myX + 3, myY + 2, myZ);
				this.makeFire(myX + 3, myY + 2, myZ + 1);
				this.makeFire(myX + 3, myY + 2, myZ + 2);
			}
		} // H'okay, so we're on an angle...
		else
		{
			// Let's get some basic math here.
			// SOUTHWEST: - X, + Z
			// NORTHWEST: - X, - Z
			// NORTHEAST: + X, - Z
			// SOUTHEAST: + X, + Z
			if(dir == 0)
			{
				// SOUTHWEST
				this.makeFire(myX - 1, myY, myZ + 1);
				
				this.makeFire(myX - 1, myY - 1, myZ + 2);
				this.makeFire(myX - 2, myY - 1, myZ + 1);
				
				this.makeFire(myX - 1, myY, myZ + 2);
				this.makeFire(myX - 2, myY, myZ + 1);
				
				this.makeFire(myX - 1, myY + 1, myZ + 2);
				this.makeFire(myX - 2, myY + 1, myZ + 1);
				
				this.makeFire(myX - 1, myY - 2, myZ + 3);
				this.makeFire(myX - 2, myY - 2, myZ + 2);
				this.makeFire(myX - 3, myY - 2, myZ + 1);
				
				this.makeFire(myX - 1, myY - 1, myZ + 3);
				this.makeFire(myX - 2, myY - 1, myZ + 2);
				this.makeFire(myX - 3, myY - 1, myZ + 1);
				
				this.makeFire(myX - 1, myY, myZ + 3);
				this.makeFire(myX - 2, myY, myZ + 2);
				this.makeFire(myX - 3, myY, myZ + 1);
				
				this.makeFire(myX - 1, myY + 1, myZ + 3);
				this.makeFire(myX - 2, myY + 1, myZ + 2);
				this.makeFire(myX - 3, myY + 1, myZ + 1);
				
				this.makeFire(myX - 1, myY + 2, myZ + 3);
				this.makeFire(myX - 2, myY + 2, myZ + 2);
				this.makeFire(myX - 3, myY + 2, myZ + 1);
				
				this.makeFire(myX - 1, myY - 3, myZ + 4);
				this.makeFire(myX - 2, myY - 3, myZ + 3);
				this.makeFire(myX - 3, myY - 3, myZ + 2);
				this.makeFire(myX - 4, myY - 3, myZ + 1);
				
				this.makeFire(myX - 1, myY - 2, myZ + 4);
				this.makeFire(myX - 2, myY - 2, myZ + 3);
				this.makeFire(myX - 3, myY - 2, myZ + 2);
				this.makeFire(myX - 4, myY - 2, myZ + 1);
				
				this.makeFire(myX - 1, myY - 1, myZ + 4);
				this.makeFire(myX - 2, myY - 1, myZ + 3);
				this.makeFire(myX - 3, myY - 1, myZ + 2);
				this.makeFire(myX - 4, myY - 1, myZ + 1);
				
				this.makeFire(myX - 1, myY, myZ + 4);
				this.makeFire(myX - 2, myY, myZ + 3);
				this.makeFire(myX - 3, myY, myZ + 2);
				this.makeFire(myX - 4, myY, myZ + 1);
				
				this.makeFire(myX - 1, myY + 1, myZ + 4);
				this.makeFire(myX - 2, myY + 1, myZ + 3);
				this.makeFire(myX - 3, myY + 1, myZ + 2);
				this.makeFire(myX - 4, myY + 1, myZ + 1);
				
				this.makeFire(myX - 1, myY + 2, myZ + 4);
				this.makeFire(myX - 2, myY + 2, myZ + 3);
				this.makeFire(myX - 3, myY + 2, myZ + 2);
				this.makeFire(myX - 4, myY + 2, myZ + 1);
				
				this.makeFire(myX - 1, myY + 3, myZ + 4);
				this.makeFire(myX - 2, myY + 3, myZ + 3);
				this.makeFire(myX - 3, myY + 3, myZ + 2);
				this.makeFire(myX - 4, myY + 3, myZ + 1);
			}
			if(dir == 1)
			{
				// NORTHWEST
				this.makeFire(myX - 1, myY, myZ - 1);
				
				this.makeFire(myX - 1, myY - 1, myZ - 2);
				this.makeFire(myX - 2, myY - 1, myZ - 1);
				
				this.makeFire(myX - 1, myY, myZ - 2);
				this.makeFire(myX - 2, myY, myZ - 1);
				
				this.makeFire(myX - 1, myY + 1, myZ - 2);
				this.makeFire(myX - 2, myY + 1, myZ - 1);
				
				this.makeFire(myX - 1, myY - 2, myZ - 3);
				this.makeFire(myX - 2, myY - 2, myZ - 2);
				this.makeFire(myX - 3, myY - 2, myZ - 1);
				
				this.makeFire(myX - 1, myY - 1, myZ - 3);
				this.makeFire(myX - 2, myY - 1, myZ - 2);
				this.makeFire(myX - 3, myY - 1, myZ - 1);
				
				this.makeFire(myX - 1, myY, myZ - 3);
				this.makeFire(myX - 2, myY, myZ - 2);
				this.makeFire(myX - 3, myY, myZ - 1);
				
				this.makeFire(myX - 1, myY + 1, myZ - 3);
				this.makeFire(myX - 2, myY + 1, myZ - 2);
				this.makeFire(myX - 3, myY + 1, myZ - 1);
				
				this.makeFire(myX - 1, myY + 2, myZ - 3);
				this.makeFire(myX - 2, myY + 2, myZ - 2);
				this.makeFire(myX - 3, myY + 2, myZ - 1);
			
				this.makeFire(myX - 1, myY - 3, myZ - 4);
				this.makeFire(myX - 2, myY - 3, myZ - 3);
				this.makeFire(myX - 3, myY - 3, myZ - 2);
				this.makeFire(myX - 4, myY - 3, myZ - 1);
				
				this.makeFire(myX - 1, myY - 2, myZ - 4);
				this.makeFire(myX - 2, myY - 2, myZ - 3);
				this.makeFire(myX - 3, myY - 2, myZ - 2);
				this.makeFire(myX - 4, myY - 2, myZ - 1);
				
				this.makeFire(myX - 1, myY - 1, myZ - 4);
				this.makeFire(myX - 2, myY - 1, myZ - 3);
				this.makeFire(myX - 3, myY - 1, myZ - 2);
				this.makeFire(myX - 4, myY - 1, myZ - 1);
				
				this.makeFire(myX - 1, myY, myZ - 4);
				this.makeFire(myX - 2, myY, myZ - 3);
				this.makeFire(myX - 3, myY, myZ - 2);
				this.makeFire(myX - 4, myY, myZ - 1);
				
				this.makeFire(myX - 1, myY + 1, myZ - 4);
				this.makeFire(myX - 2, myY + 1, myZ - 3);
				this.makeFire(myX - 3, myY + 1, myZ - 2);
				this.makeFire(myX - 4, myY + 1, myZ - 1);
				
				this.makeFire(myX - 1, myY + 2, myZ - 4);
				this.makeFire(myX - 2, myY + 2, myZ - 3);
				this.makeFire(myX - 3, myY + 2, myZ - 2);
				this.makeFire(myX - 4, myY + 2, myZ - 1);
				
				this.makeFire(myX - 1, myY + 3, myZ - 4);
				this.makeFire(myX - 2, myY + 3, myZ - 3);
				this.makeFire(myX - 3, myY + 3, myZ - 2);
				this.makeFire(myX - 4, myY + 3, myZ - 1);
			}
			if(dir == 2)
			{
				// NORTHEAST
				this.makeFire(myX + 1, myY, myZ - 1);
				
				this.makeFire(myX + 1, myY - 1, myZ - 2);
				this.makeFire(myX + 2, myY - 1, myZ - 1);
				
				this.makeFire(myX + 1, myY, myZ - 2);
				this.makeFire(myX + 2, myY, myZ - 1);
				
				this.makeFire(myX + 1, myY + 1, myZ - 2);
				this.makeFire(myX + 2, myY + 1, myZ - 1);
				
				this.makeFire(myX + 1, myY - 2, myZ - 3);
				this.makeFire(myX + 2, myY - 2, myZ - 2);
				this.makeFire(myX + 3, myY - 2, myZ - 1);
				
				this.makeFire(myX + 1, myY - 1, myZ - 3);
				this.makeFire(myX + 2, myY - 1, myZ - 2);
				this.makeFire(myX + 3, myY - 1, myZ - 1);
				
				this.makeFire(myX + 1, myY, myZ - 3);
				this.makeFire(myX + 2, myY, myZ - 2);
				this.makeFire(myX + 3, myY, myZ - 1);
				
				this.makeFire(myX + 1, myY + 1, myZ - 3);
				this.makeFire(myX + 2, myY + 1, myZ - 2);
				this.makeFire(myX + 3, myY + 1, myZ - 1);
				
				this.makeFire(myX + 1, myY + 2, myZ - 3);
				this.makeFire(myX + 2, myY + 2, myZ - 2);
				this.makeFire(myX + 3, myY + 2, myZ - 1);
			
				this.makeFire(myX + 1, myY - 3, myZ - 4);
				this.makeFire(myX + 2, myY - 3, myZ - 3);
				this.makeFire(myX + 3, myY - 3, myZ - 2);
				this.makeFire(myX + 4, myY - 3, myZ - 1);
				
				this.makeFire(myX + 1, myY - 2, myZ - 4);
				this.makeFire(myX + 2, myY - 2, myZ - 3);
				this.makeFire(myX + 3, myY - 2, myZ - 2);
				this.makeFire(myX + 4, myY - 2, myZ - 1);
				
				this.makeFire(myX + 1, myY - 1, myZ - 4);
				this.makeFire(myX + 2, myY - 1, myZ - 3);
				this.makeFire(myX + 3, myY - 1, myZ - 2);
				this.makeFire(myX + 4, myY - 1, myZ - 1);
				
				this.makeFire(myX + 1, myY, myZ - 4);
				this.makeFire(myX + 2, myY, myZ - 3);
				this.makeFire(myX + 3, myY, myZ - 2);
				this.makeFire(myX + 4, myY, myZ - 1);
				
				this.makeFire(myX + 1, myY + 1, myZ - 4);
				this.makeFire(myX + 2, myY + 1, myZ - 3);
				this.makeFire(myX + 3, myY + 1, myZ - 2);
				this.makeFire(myX + 4, myY + 1, myZ - 1);
				
				this.makeFire(myX + 1, myY + 2, myZ - 4);
				this.makeFire(myX + 2, myY + 2, myZ - 3);
				this.makeFire(myX + 3, myY + 2, myZ - 2);
				this.makeFire(myX + 4, myY + 2, myZ - 1);
				
				this.makeFire(myX + 1, myY + 3, myZ - 4);
				this.makeFire(myX + 2, myY + 3, myZ - 3);
				this.makeFire(myX + 3, myY + 3, myZ - 2);
				this.makeFire(myX + 4, myY + 3, myZ - 1);
			}
			else
			{
				// SOUTHEAST
				this.makeFire(myX + 1, myY, myZ + 1);
				
				this.makeFire(myX + 1, myY - 1, myZ + 2);
				this.makeFire(myX + 2, myY - 1, myZ + 1);
				
				this.makeFire(myX + 1, myY, myZ + 2);
				this.makeFire(myX + 2, myY, myZ + 1);
				
				this.makeFire(myX + 1, myY + 1, myZ + 2);
				this.makeFire(myX + 2, myY + 1, myZ + 1);
				
				this.makeFire(myX + 1, myY - 2, myZ + 3);
				this.makeFire(myX + 2, myY - 2, myZ + 2);
				this.makeFire(myX + 3, myY - 2, myZ + 1);
				
				this.makeFire(myX + 1, myY - 1, myZ + 3);
				this.makeFire(myX + 2, myY - 1, myZ + 2);
				this.makeFire(myX + 3, myY - 1, myZ + 1);
				
				this.makeFire(myX + 1, myY, myZ + 3);
				this.makeFire(myX + 2, myY, myZ + 2);
				this.makeFire(myX + 3, myY, myZ + 1);
				
				this.makeFire(myX + 1, myY + 1, myZ + 3);
				this.makeFire(myX + 2, myY + 1, myZ + 2);
				this.makeFire(myX + 3, myY + 1, myZ + 1);
				
				this.makeFire(myX + 1, myY + 2, myZ + 3);
				this.makeFire(myX + 2, myY + 2, myZ + 2);
				this.makeFire(myX + 3, myY + 2, myZ + 1);
			
				this.makeFire(myX + 1, myY - 3, myZ + 4);
				this.makeFire(myX + 2, myY - 3, myZ + 3);
				this.makeFire(myX + 3, myY - 3, myZ + 2);
				this.makeFire(myX + 4, myY - 3, myZ + 1);
				
				this.makeFire(myX + 1, myY - 2, myZ + 4);
				this.makeFire(myX + 2, myY - 2, myZ + 3);
				this.makeFire(myX + 3, myY - 2, myZ + 2);
				this.makeFire(myX + 4, myY - 2, myZ + 1);
				
				this.makeFire(myX + 1, myY - 1, myZ + 4);
				this.makeFire(myX + 2, myY - 1, myZ + 3);
				this.makeFire(myX + 3, myY - 1, myZ + 2);
				this.makeFire(myX + 4, myY - 1, myZ + 1);
				
				this.makeFire(myX + 1, myY, myZ + 4);
				this.makeFire(myX + 2, myY, myZ + 3);
				this.makeFire(myX + 3, myY, myZ + 2);
				this.makeFire(myX + 4, myY, myZ + 1);
				
				this.makeFire(myX + 1, myY + 1, myZ + 4);
				this.makeFire(myX + 2, myY + 1, myZ + 3);
				this.makeFire(myX + 3, myY + 1, myZ + 2);
				this.makeFire(myX + 4, myY + 1, myZ + 1);
				
				this.makeFire(myX + 1, myY + 2, myZ + 4);
				this.makeFire(myX + 2, myY + 2, myZ + 3);
				this.makeFire(myX + 3, myY + 2, myZ + 2);
				this.makeFire(myX + 4, myY + 2, myZ + 1);
				
				this.makeFire(myX + 1, myY + 3, myZ + 4);
				this.makeFire(myX + 2, myY + 3, myZ + 3);
				this.makeFire(myX + 3, myY + 3, myZ + 2);
				this.makeFire(myX + 4, myY + 3, myZ + 1);
			}
		}
	}
	
	private void makeFire(int x, int y, int z)
	{
		double foo = this.worldObj.rand.nextFloat() - 0.5F;
		double bar = this.worldObj.rand.nextFloat() - 0.5F;
		double cow = this.worldObj.rand.nextFloat() - 0.5F;
		this.worldObj.spawnParticle("flame", x + foo, y + bar, z + cow, 0.0D, 0.0D, 0.0D);
		
		foo = this.worldObj.rand.nextFloat() - 0.5F;
		bar = this.worldObj.rand.nextFloat() - 0.5F;
		cow = this.worldObj.rand.nextFloat() - 0.5F;
		this.worldObj.spawnParticle("smoke", x + foo, y + bar, z + cow, 0.0D, 0.0D, 0.0D);
		
		if(this.worldObj.getBlock(x, y, z) == Blocks.air)
			this.worldObj.setBlock(x, y, z, Blocks.fire);
		else if(this.worldObj.getBlock(x, y, z) == Blocks.water)
		{
			this.worldObj.playSoundEffect(x + 0.5D, y + 0.5D, z + 0.5D, "random.fizz", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
			this.worldObj.setBlock(x, y, z, Blocks.air);
		}
	}
	
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);
        
        par1NBTTagCompound.setInteger("StoneStatus", this.flameTimer);
    }
	
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readEntityFromNBT(par1NBTTagCompound);

        this.flameTimer  = par1NBTTagCompound.getInteger("flameTimer");
    }
}
