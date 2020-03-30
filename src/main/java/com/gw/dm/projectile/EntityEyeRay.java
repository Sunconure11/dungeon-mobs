package com.gw.dm.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

// FIXME:  This whole class makes no sense!  Its a *PROJECTILE*, nothing more complex. Recreate from scratch!

public class EntityEyeRay extends EntityThrowable {
	private static final double RANGE = 160.0d * 160.0d;
	PotionEffect[] myEffect;
	private int age;
	private double sx, sy, sz;
	
	
	public EntityEyeRay(World worldIn) {
		super(worldIn);
		sx = posX;
		sy = posY;
		sz = posZ;
		age = 0;
	}
	
	
	public EntityEyeRay(World worldIn, EntityLivingBase throwerIn) {
		super(worldIn, throwerIn);
		sx = posX;
		sy = posY;
		sz = posZ;
		age = 0;
	}
	
	
	public EntityEyeRay(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
		sx = posX;
		sy = posY;
		sz = posZ;
		age = 0;
	}
	
	
	public void setEffects(PotionEffect[] effs) {
		myEffect = effs;
	}
	
	
	public void setEffects(PotionEffect eff) {
		myEffect = new PotionEffect[]{eff};
	}
	
	@Override
	public boolean isInWater() {
		return false;
	}
	
	@Override
	public void onUpdate() {
		if ((traveled() > RANGE) || (++age > 100)) {
			setDead();
		}
		super.onUpdate();
	}
	
	@Override
	protected float getGravityVelocity() {
		return 0.0f;
	}
	
	@Override
	protected void onImpact(RayTraceResult result) {
		if (myEffect != null) {
			if (result.entityHit instanceof EntityLivingBase) {
				EntityLivingBase victim = (EntityLivingBase) result.entityHit;
				for (PotionEffect eff : myEffect) {
					if (eff != null) {
						victim.addPotionEffect(eff);
					}
				}
			}
		}
	}
	
	private double traveled() {
		return ((posX - sx) * (posX - sx)) + ((posY - sy) * (posY - sx)) + ((posZ - sz) * (posZ - sz));
	}
	
}
