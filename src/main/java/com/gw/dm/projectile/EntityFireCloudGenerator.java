package com.gw.dm.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityFireCloudGenerator extends EntityThrowable {
	private static final float TWOPI = (float) (Math.PI * 2.0d);
	private static final float RADIUS = 3.0f;
	private static final double RANGE = 160.0d * 160.0d;
	private static final int NUM = 12;
	private int duration; // Time to live
	private int age;
	private double sx, sy, sz;
	
	
	public EntityFireCloudGenerator(World worldIn, EntityLivingBase throwerIn) {
		super(worldIn, throwerIn);
		duration = 120;
		age = 0;
		sx = posX;
		sy = posY;
		sz = posZ;
	}
	
	@Override
	public void onUpdate() {
		if (!world.isRemote) {
			spawnCloud();
		}
		if ((traveled() > RANGE) || isInWater() || (++age > duration)) {
			setDead();
		}
		super.onUpdate();
	}
	
	@Override
	public float getGravityVelocity() {
		return 0.0f;
	}
	
	@Override
	protected void onImpact(RayTraceResult result) {
		spawnCloud();
		setDead();
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		compound.setInteger("Duration", duration);
		compound.setInteger("FireAge", age);
		compound.setDouble("startX", sx);
		compound.setDouble("startY", sy);
		compound.setDouble("startZ", sz);
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
		duration = compound.getInteger("Duration");
		age = compound.getInteger("FireAge");
		sx = compound.getDouble("startX");
		sy = compound.getDouble("startY");
		sz = compound.getDouble("startZ");
	}
	
	private void spawnCloud() {
		for (int i = 0; i < NUM; i++) {
			float hangle = rand.nextFloat() * TWOPI;
			float vangle = rand.nextFloat() * TWOPI;
			float dist = rand.nextFloat() * RADIUS;
			float vcos = MathHelper.cos(vangle);
			double x = (posX + (dist * vcos * MathHelper.sin(hangle)));
			double y = (posY + (dist * MathHelper.sin(vangle)));
			double z = (posZ + (dist * vcos * MathHelper.cos(hangle)));
			EntityBurningFireParticloid particle = new EntityBurningFireParticloid(world, x, y, z, (rand.nextDouble() - 0.5d) * 0.02d, (rand.nextDouble() * 0.03d) + 0.01d, (rand.nextDouble() - 0.5d) * 0.02d);
			//System.out.println("Spawning fire at " + x + ", " + y + ", " + z);
			world.spawnEntity(particle);
		}
	}
	
	private double traveled() {
		return ((posX - sx) * (posX - sx)) + ((posY - sy) * (posY - sx)) + ((posZ - sz) * (posZ - sz));
	}
	
	
	@Override
	public boolean hasNoGravity() {
		return true;
	}
	
	
	@Override
	public boolean canBePushed() {
		return false;
	}
	
}
