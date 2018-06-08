package com.gw.dm.entity;

import java.util.Iterator;
import java.util.List;

import com.gw.dm.DungeonMobs;
import com.gw.dm.DungeonMobsHelper;
import com.gw.dm.EntityDungeonMob;
import com.gw.dm.potion.PotionEffectAddled;


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
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

public class EntityUmberHulk extends EntityDungeonMob 
{
	private boolean ignoreHeight;
	private int confuseTicks;
	
	public EntityUmberHulk(World par1World) 
	{
		super(par1World);
		this.setSize(1.9F, 2.3F);
		this.experienceValue = 45;
		this.isImmuneToFire = true;
		this.ignoreHeight = false;
		
		this.confuseTicks = 0;
		
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
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(60.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.18D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(9.0D);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0D);
    }
	
	protected boolean isAIEnabled()
	{
		return true;
	}

	public int getTotalArmorValue()
	{
		return 12;
	}
	
	public int getAttackStrength(Entity par1Entity)
	{
		return 9;
	}
	
	protected String getLivingSound()
	{
		return "dungeonmobs:uh_l";
	}
	
	protected String getHurtSound()
	{
		return "dungeonmobs:uh_h";
	}
	
	protected String getDeathSound()
	{
		return null;
	}
	
	protected void playStepSound(int par1, int par2, int par3, int par4)
    {
        this.playSound("dungeonmobs:bt_s", 0.15F, 1.0F);
    }
	
	public boolean getCanSpawnHere()
	{
		if(this.worldObj.canBlockSeeTheSky((int)this.posX, (int)this.posY, (int)this.posZ))
			return false;
		
		if(this.posY > 40.0D && !this.ignoreHeight)
			return false;
		
		return super.getCanSpawnHere();
	}
	
	public int getTalkInterval()
    {
        return 240;
    }
	
	public void onLivingUpdate()
    {
		this.confuseTicks++;
		
		if(this.confuseTicks % 20 == 0)
		{
			List nearbyPlayers = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, this.boundingBox.expand(16.0D, 4.0D, 16.0D));
			Iterator iter = nearbyPlayers.iterator();
			
			while(iter.hasNext())
			{
				EntityPlayer foo = (EntityPlayer)iter.next();
				
				if(this.canEntityBeSeen(foo) && !foo.capabilities.isCreativeMode)
				{
					/*
					if(this.worldObj != null && DungeonMobs.confusedMovementInput != null)
					{
						int bar = DungeonMobsHelper.getDifficulty(this.worldObj);
						DungeonMobs.confusedMovementInput.setConfValue(bar);
					}
					*/

					//foo.addPotionEffect(new PotionEffectAddled(DungeonMobs.potionAddleID, 300, 0));
					foo.addPotionEffect(new PotionEffect(Potion.confusion.id, 100, 1));
				}
			}
			
			this.confuseTicks = 0;
		}
			
		super.onLivingUpdate();
    }
	
	@Override
	protected void jump()
    {
        this.motionY = 0.4D;

        if (this.isPotionActive(Potion.jump))
            this.motionY += (double)((float)(this.getActivePotionEffect(Potion.jump).getAmplifier() + 1) * 0.1F);

        if (this.isSprinting())
        {
            float var1 = this.rotationYaw * 0.017453292F;
            this.motionX -= (double)(MathHelper.sin(var1) * 0.2F);
            this.motionZ += (double)(MathHelper.cos(var1) * 0.2F);
        }

        this.isAirBorne = true;
        ForgeHooks.onLivingJump(this);
    }
	
	protected void dropFewItems(boolean par1, int par2)
	{
		int var3;
		int var4;
		
		var3 = this.rand.nextInt(4) + 2 + (2 * par2);

        for (var4 = 0; var4 < var3; var4++)
        {
        	this.dropItem(Items.leather, 1);
        }
	}
}
