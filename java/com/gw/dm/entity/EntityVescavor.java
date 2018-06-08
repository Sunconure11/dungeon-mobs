package com.gw.dm.entity;

import java.util.Iterator;
import java.util.List;

import com.gw.dm.DungeonMobs;
import com.gw.dm.DungeonMobsHelper;
import com.gw.dm.EntityDungeonMob;
import com.gw.dm.potion.PotionEffectAddled;

import net.minecraft.block.Block;
import net.minecraft.block.Block.SoundType;
import net.minecraft.enchantment.EnchantmentHelper;
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
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

public class EntityVescavor extends EntityDungeonMob
{
	private int confuseTicks;
	
	public EntityVescavor(World par1World) 
	{
		super(par1World);
		this.setSize(0.8F, 0.9F);
        this.experienceValue = 20;
        this.ignoreHeight = false;
        this.confuseTicks = 0;
        
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0F, false));
        this.tasks.addTask(2, new EntityAIWander(this, 1.0F));
        this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(4, new EntityAILookIdle(this));
        
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
    }

	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(12.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(2.0D);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.0D);
    }
	
	protected boolean isAIEnabled()
	{
		return true;
	}
	
	public int getTotalArmorValue()
	{
		return 0;
	}
	
	public int getAttackStrength(Entity par1Entity)
	{
		return 2;
	}
	
	protected String getLivingSound()
	{
		return "dungeonmobs:v_l2";
	}
	
	protected String getHurtSound()
	{
		return "dungeonmobs:v_h";
	}
	
	protected String getDeathSound()
	{
		return "dungeonmobs:v_d";
	}
	
	public int getTalkInterval()
    {
        return 100;
    }
	
	public boolean getCanSpawnHere()
	{
		if(this.worldObj.canBlockSeeTheSky((int)this.posX, (int)this.posY, (int)this.posZ))
			return false;
		
		if(this.posY > 36.0D && !this.ignoreHeight)
			return false;
		
		return super.getCanSpawnHere();
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

					foo.addPotionEffect(new PotionEffectAddled(DungeonMobs.potionAddleID, 360, 0));
					//foo.addPotionEffect(new PotionEffect(Potion.confusion.id, 100, 1));
				}
			}
			
			this.confuseTicks = 0;
		}
			
		super.onLivingUpdate();
    }
	
	@Override
	protected void jump()
    {
        this.motionY = 0.7D;

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
		int var5;
		
		var3 = this.rand.nextInt(4);

        for(var5 = 0; var5 < var3; var5++)
        {
        	this.dropItem(Items.emerald, 1);
        }
	}
}
