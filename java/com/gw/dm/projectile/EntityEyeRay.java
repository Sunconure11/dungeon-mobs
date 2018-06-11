package com.gw.dm.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

// FIXME:  This whole class makes no sense!  Its a *PROJECTILE*, nothing more complex. Recreate from scratch!

public class EntityEyeRay extends EntityThrowable {
	PotionEffect[] myEffect;


	public EntityEyeRay(World worldIn) {
		super(worldIn);
	}


	public EntityEyeRay(World worldIn, EntityLivingBase throwerIn) {
		super(worldIn, throwerIn);
	}


	public EntityEyeRay(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}
	
	
	public void setEffects(PotionEffect[] effs) {
		myEffect = effs;
	}
	
	
	public void setEffects(PotionEffect eff) {
		myEffect = new PotionEffect[]{eff};
	}


	@Override
	protected void onImpact(RayTraceResult result) {
		if(myEffect != null) {
			if(result.entityHit instanceof EntityLivingBase) {
				EntityLivingBase victim = (EntityLivingBase)result.entityHit;
				for(PotionEffect eff: myEffect) {
					if(eff != null) {
						victim.addPotionEffect(eff);
					}
				}
			}
		}
	}

}
