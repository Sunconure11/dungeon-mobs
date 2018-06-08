package com.gw.dm.entity;


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
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityHookHorror extends EntityDungeonMob 
{
	private boolean ignoreHeight;
	
	public EntityHookHorror(World par1World) 
	{
		super(par1World);
		this.experienceValue = 35;
		this.ignoreHeight = false;
		
		this.setSize(1.0F, 2.8F);
		
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
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(35.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.28D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(5.0D);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.0D);
    }
	
	public int getTotalArmorValue()
	{
		return 9;
	}
	
	public int getAttackStrength(Entity par1Entity)
	{
		return 5;
	}

	protected String getLivingSound()
	{
		int foo = this.rand.nextInt(2);
		
		if(foo == 0)
			return "dungeonmobs:hh_l";
		else
			return "dungeonmobs:hh_l2";
	}
	
	protected String getHurtSound()
	{
		return "dungeonmobs:hh_h";
	}
	
	protected String getDeathSound()
	{
		return "dungeonmobs:hh_h";
	}
	
	protected void playStepSound(int par1, int par2, int par3, int par4)
    {
        this.playSound("dungeonmobs:bt_s", 0.15F, 1.0F);
    }
	
	public int getTalkInterval()
    {
        return 60;
    }
	
	/*
	protected int getDropItemId()
	{
		return Block.tripWireSource.blockID;
	}
	*/
	
	protected void dropFewItems(boolean par1, int par2)
	{
		int var3;
		int var4;
		int var5;
		
		var3 = this.rand.nextInt(4);

        for(var5 = 0; var5 < var3; var5++)
        {
        	this.dropItem(Items.bone, 1);
        }
	}
	
	public boolean getCanSpawnHere()
	{
		if(this.worldObj.canBlockSeeTheSky((int)this.posX, (int)this.posY, (int)this.posZ))
			return false;
		
		if(this.posY > 45.0D && !this.ignoreHeight)
			return false;
		
		return super.getCanSpawnHere();
	}
	
	public boolean attackEntityAsMob(Entity ent)
	 {
		 if(ent instanceof EntityPlayer)
		 {
			 EntityPlayer foo = (EntityPlayer)ent;
			 
			 for(int i = 0; i < 4; i++)
			 {
				 ItemStack armorPiece = foo.getCurrentArmor(i);
				 
				 if(armorPiece != null)
				 {
					 int lawlz;
					 
					 if(armorPiece.isItemEnchanted())
						 lawlz = this.getRNG().nextInt(6) + 5;
					 else
						 lawlz = this.getRNG().nextInt(6) + 25;
					 
					 armorPiece.damageItem(lawlz,  this);
				 }
			 }
			 
			 int moo = 4;
			 
			 if(this.worldObj.difficultySetting == EnumDifficulty.EASY)
				 moo--;
			 if(this.worldObj.difficultySetting == EnumDifficulty.NORMAL)
				 moo -= 2;
			 if(this.worldObj.difficultySetting == EnumDifficulty.HARD)
				 moo -= 3;
			 
			 int bar = this.rand.nextInt(4 - moo);
			 
			 if(bar == 0)
			 {
				 ItemStack weapon = foo.inventory.getStackInSlot(foo.inventory.currentItem);
				 
				 if(weapon != null)
				 {
					 EntityItem drop = foo.dropPlayerItemWithRandomChoice(foo.inventory.decrStackSize(foo.inventory.currentItem, 1), false);
					 
					 if(drop != null)
						 drop.delayBeforeCanPickup = 60;
				 }
			 }
		 }
		 
		 return super.attackEntityAsMob(ent);
	 }
	
	public void onEntityUpdate()
	{
		int moo = 0;
		 
		 if(this.worldObj.difficultySetting == EnumDifficulty.EASY)
			 moo = 1;
		 if(this.worldObj.difficultySetting == EnumDifficulty.NORMAL)
			 moo = 2;
		 if(this.worldObj.difficultySetting == EnumDifficulty.HARD)
			 moo = 3;
		
		if(this.rand.nextInt(5) < moo)
			this.attackTime -= (moo * 3);
		
		super.onEntityUpdate();
	}
}
