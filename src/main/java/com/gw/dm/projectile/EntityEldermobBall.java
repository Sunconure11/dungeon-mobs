package com.gw.dm.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityEldermobBall extends EntityThrowable {
	private int age;


	public EntityEldermobBall(World worldIn) {
		super(worldIn);
		age = 0;
	}


	public EntityEldermobBall(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
		age = 0;
	}


	public EntityEldermobBall(World world, EntityLivingBase thrower) {
		super(world, thrower);
		age = 0;
	}


	@Override
	protected void onImpact(RayTraceResult result) {
		if (!world.isRemote) {
			if (result.entityHit instanceof EntityLivingBase) {
				// No longer checking for null thrower -- that should never happen!
				EntityLivingBase entity = (EntityLivingBase) result.entityHit;
				entity.attackEntityFrom(DamageSource.causeMobDamage(getThrower())
						.setDifficultyScaled()
						.setProjectile(), 12.0f);
				world.newExplosion(this, posX, posY, posZ, 1, false, true);
			}
			setDead();
		}

	}


	@Override
	public float getGravityVelocity() {
		return 0.0f;
	}


	@Override
	public void onUpdate() {
		age++;
		if ((age >= 72) && world.isRemote) {
			world.newExplosion(this, posX, posY, posZ, 1, false, false);
			this.setDead();
		}
		super.onUpdate();
	}


	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
		compound.setInteger("Age", age);
	}


	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
		age = compound.getInteger("Age");
	}


	public float getBrightness(float partialTicks) {
		return 1.0f;
	}


	@SideOnly(Side.CLIENT)
	public int getBrightnessForRender(float partialTicks) {
		return 0xf000f0;
	}


	@Override
	public boolean hasNoGravity() {
		return true;
	}


	@Override
	public boolean isInWater() {
		return false;
	}

}
