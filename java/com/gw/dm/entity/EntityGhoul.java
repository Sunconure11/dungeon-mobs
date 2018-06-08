package com.gw.dm.entity;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class EntityGhoul extends EntityZombie
{
	//private EntityLiving[] attackedTargets;
	
	public boolean ignoreHeight;
	
	public EntityGhoul(World par1World) 
	{
		super(par1World);
		//this.texture = "/mods/dungeonMobs/Ghoul.png";
		this.experienceValue = 10;
		//this.moveSpeed = 0.27F;
		this.ignoreHeight = false;
		//this.attackedTargets = new EntityLiving[0];
		
		//this.tasks.addTask(1, new EntityAIFleeSun(this, this.moveSpeed));
	}
	
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(25.0D);
    }
	
	protected String getLivingSound()
	{
		return "dungeonmobs:g_l";
	}
	
	protected String getHurtSound()
	{
		return "dungeonmobs:g_h";
	}
	
	protected String getDeathSound()
	{
		return "dungeonmobs:g_d";
	}
	
	/*
	public void onUpdate()
	{
		List foo = this.worldObj.getLoadedEntityList();
		
		for(foo.)
		
		super.onUpdate();
	}
	*/
	
	public int getAttackStrength(Entity par1Entity)
    {
        //ItemStack var2 = this.getHeldItem();
        int var3 = 6;

        /*
        if (var2 != null)
        {
            var3 += var2.getDamageVsEntity(this);
        }
        */

        return var3;
    }
	
	public boolean attackEntityAsMob(Entity par1)
	{
		this.getAttackTarget().addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 40, 5));
		
		return super.attackEntityAsMob(par1);
	}
	
	public boolean getCanSpawnHere()
	{
		if(this.worldObj.canBlockSeeTheSky((int)this.posX, (int)this.posY, (int)this.posZ))
			return false;
		
		if(this.posY > 60.0D && !this.ignoreHeight)
			return false;
		
		return super.getCanSpawnHere();
	}
	
	/*
	public void setAttackTarget(EntityLiving par1)
	{
		int foo = this.attackedTargets.length;
		boolean newTarget = true;
		
		for(int i = 0; i < foo; i++)
		{
			if(attackedTargets[i] == par1)
			{
				newTarget = false;
				break;
			}
		}
		
		if(newTarget)
		{
			EntityLiving[] bar = new EntityLiving[foo + 1];
			int ctr;
			
			for(ctr = 0; ctr < foo; ctr++)
			{
				bar[ctr] = attackedTargets[ctr];
			}
			
			bar[ctr + 1] = par1;
			
			this.attackedTargets = bar;
		}
		
		super.setAttackTarget(par1);
	}
	*/
	
	public void setIgnoreHeight(boolean par1)
	{
		this.ignoreHeight = par1;
	}
}
