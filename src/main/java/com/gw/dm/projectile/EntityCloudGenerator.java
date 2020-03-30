package com.gw.dm.projectile;

import net.minecraft.entity.EntityAreaEffectCloud;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class EntityCloudGenerator extends EntityThrowable {
	private static final double RANGE = 160.0d * 160.0d;
	private PotionEffect type;
	private int color;
	private int duration; // Time to live
	private int age;
	private double sx, sy, sz;
	
	
	public EntityCloudGenerator(World worldIn, EntityLivingBase thrower, PotionEffect effect) {
		super(worldIn, thrower);
		type = effect;
		color = effect.getPotion().getLiquidColor();
		duration = 180;
		age = 0;
		sx = posX;
		sy = posY;
		sz = posZ;
	}
	
	@Override
	public void onUpdate() {
		if (!world.isRemote) {
			spawnGas();
		}
		if ((traveled() > RANGE) || (++age > duration)) {
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
		spawnAreaEffectCloud();
		setDead();
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		Potion effect = type.getPotion();
		compound.setInteger("EffectType", effect.getIdFromPotion(effect));
		compound.setInteger("Duration", duration);
		compound.setInteger("FireAge", age);
		compound.setDouble("startX", sx);
		compound.setDouble("startY", sy);
		compound.setDouble("startZ", sz);
	}
	
	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
		type = PotionType.getPotionTypeForName("poison").getEffects().get(0);
		duration = compound.getInteger("Duration");
		age = compound.getInteger("FireAge");
		sx = compound.getDouble("startX");
		sy = compound.getDouble("startY");
		sz = compound.getDouble("startZ");
	}
	
	private void spawnAreaEffectCloud() {
		EntityAreaEffectCloud droppedCloud = new EntityAreaEffectCloud(world, posX, posY, posZ);
		float powerLeft = (float) (1.0f - (traveled() / RANGE));
		droppedCloud.setOwner(getThrower());
		droppedCloud.setDuration(duration - age);
		droppedCloud.setRadius(2.5f + (2.0f * powerLeft));
		droppedCloud.setRadiusOnUse(-0.5f);
		droppedCloud.setWaitTime(10); // Not sure what this should actually be
		droppedCloud.setRadiusPerTick(-droppedCloud.getRadius() / (float) droppedCloud.getDuration());
		droppedCloud.addEffect(new PotionEffect(type.getPotion(), type.getDuration(), type.getAmplifier()));
		world.spawnEntity(droppedCloud);
	}
	
	private void spawnGas() {
		EntityAreaEffectCloud tempCloud = new EntityAreaEffectCloud(world, posX, posY, posZ);
		tempCloud.setOwner(getThrower());
		tempCloud.setDuration(10);
		tempCloud.setRadius(2.5f);
		tempCloud.setRadiusOnUse(-0.5f);
		tempCloud.setWaitTime(10); // Not sure what this should actually be
		tempCloud.setRadiusPerTick(-tempCloud.getRadius() / (float) tempCloud.getDuration());
		tempCloud.addEffect(new PotionEffect(type.getPotion(), type.getDuration(), type.getAmplifier()));
		world.spawnEntity(tempCloud);
	}
	
	private double traveled() {
		return ((posX - sx) * (posX - sx)) + ((posY - sy) * (posY - sx)) + ((posZ - sz) * (posZ - sz));
	}
	
	
	@Override
	public boolean hasNoGravity() {
		return true;
	}
	
	
	@Override
	public boolean isInWater() {
		return false;
	}
	
	
	@Override
	public boolean canBePushed() {
		return false;
	}
	
}
