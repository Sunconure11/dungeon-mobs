package com.gw.dm.projectile;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityLightball extends EntityThrowable {
	private static final DamageSource SOURCE = new DamageSource("light_ball")
			.setDamageBypassesArmor();
	private int age;

	public EntityLightball(World worldIn) {
		super(worldIn);
		age = 0;
	}


	public EntityLightball(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
		age = 0;
	}


	public EntityLightball(World world, EntityLivingBase thrower) {
		super(world, thrower);
		age = 0;
	}


	@Override
	protected void onImpact(RayTraceResult result) {
		if (!world.isRemote) {
			if (result.entityHit instanceof EntityLivingBase) {
				// No longer checking for null thrower -- that should never happen!
				EntityLivingBase entity = (EntityLivingBase) result.entityHit;
				switch (world.getDifficulty()) {
					case EASY:
						entity.attackEntityFrom(DamageSource.causeMobDamage(getThrower())
								.setDamageBypassesArmor(), 1.0f);
						break;
					case HARD:
						entity.attackEntityFrom(DamageSource.causeMobDamage(getThrower())
								.setDamageBypassesArmor(), 3.0f);
						break;
					case NORMAL:
					default:
						entity.attackEntityFrom(DamageSource.causeMobDamage(getThrower())
								.setDamageBypassesArmor(), 2.0f);
						break;
				}
			}
			// Nerfing this -- mob is too hard for the nether; this might be 
			// configurable in the future.
			//world.newExplosion(this, posX, posY, posZ, 1, false, false);
			world.playSound((EntityPlayer)null, this.posX, this.posY, this.posZ, 
					SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 
					4.0F, (1.0F + (this.world.rand.nextFloat() 
							- world.rand.nextFloat()) * 0.2F) * 0.7F);
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
