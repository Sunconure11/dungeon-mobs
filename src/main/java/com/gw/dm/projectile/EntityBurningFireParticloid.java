package com.gw.dm.projectile;

import net.minecraft.entity.projectile.EntitySmallFireball;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

/**
 * A short lived fireball that will spontaniously die without needing to
 * hit a target, and with particle-like behavior.  Intended for the
 * Eldermobs fire attack (could be used elsewhere).
 */
public class EntityBurningFireParticloid extends EntitySmallFireball {
	private int duration; // Time to live
	private int age;
	
	public EntityBurningFireParticloid(World worldIn, double x, double y, double z, double accelX, double accelY, double accelZ) {
		super(worldIn, x, y, z, accelX, accelY, accelZ);
		age = 0;
		duration = 10 + rand.nextInt(10);
	}
	
	@Override
	public void onUpdate() {
		super.onUpdate();
		if (++age > duration) {
			setDead();
		}
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		compound.setInteger("Duration", duration);
		compound.setInteger("FireAge", age);
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
		duration = compound.getInteger("Duration");
		age = compound.getInteger("FireAge");
	}
}
