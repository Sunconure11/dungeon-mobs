package com.gw.dm.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import com.gw.dm.sound.AudioHandler;

public class EntityGhoul extends EntityZombie {
	
	public boolean ignoreHeight;
	
	public EntityGhoul(World par1World) {
		super(par1World);
		this.experienceValue = 10;
		this.ignoreHeight = false;
	}
	
	
	protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(25.0d);
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.27f);
    }
	
	
	@Override
	protected SoundEvent getAmbientSound() {
		return AudioHandler.entityGhoulAmbient;
	}
	
	
	@Override
	protected SoundEvent getHurtSound(DamageSource ds)	{
		return AudioHandler.entityGhoulHurt;
	}
	
	@Override
	protected SoundEvent getDeathSound() {
		return AudioHandler.entityGhoulDeath;
	}
	
	
	public int getAttackStrength(Entity par1Entity) {
        return 6;
    }
	
	
	public boolean attackEntityAsMob(Entity par1) {
		getAttackTarget().addPotionEffect(
				new PotionEffect(Potion.getPotionFromResourceLocation("slowness"), 40, 5));		
		return super.attackEntityAsMob(par1);
	}
	
	
	public boolean getCanSpawnHere() {
		if(world.canSeeSky(new BlockPos((int)this.posX, (int)this.posY, (int)this.posZ)))
			return false;
		
		if(posY > 60.0D && !ignoreHeight)
			return false;
		
		return super.getCanSpawnHere();
	}
	
	
	public void setIgnoreHeight(boolean par1) {
		ignoreHeight = par1;
	}
}
