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

import com.gw.dm.DungeonMobsDamageSource;
import com.gw.dm.util.ConfigHandler;

public class EntityLightball extends EntityThrowable {
	public static final DamageSource LIGHT_BALL = DungeonMobsDamageSource.LIGHT_BALL;
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
				EntityLivingBase entity = (EntityLivingBase) result.entityHit;
				float dmg = ConfigHandler.damagex
						+ (ConfigHandler.damageplus / 5.0f);
				if(entity instanceof EntityPlayer) {
					switch(world.getDifficulty()) {
						case EASY:
							dmg *= 0.5;
							break;
						case NORMAL:
							dmg *= 0.75;
							break;
						case PEACEFUL:
							dmg = 0.0f;
							break;
						case HARD:
						default:
							break;					
					}
				}
				// First, do some armor piercing damage
				if(damageable(entity)) {
					entity.setHealth(entity.getHealth() - dmg);
	                if(entity.getHealth() <= 0.0F) {
	                		entity.onDeath(DungeonMobsDamageSource.LIGHT_BALL
	        						.causeMobDamage(getThrower()));
	                }
	            }
				// Then do some normal damage
				// This is what the explosion was used as a stand-in for
				entity.attackEntityFrom(DungeonMobsDamageSource.LIGHT_BALL
						.causeMobDamage(getThrower()), dmg);
			}
			world.playSound((EntityPlayer)null, this.posX, this.posY, this.posZ, 
					SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 
					4.0F, (1.0F + (this.world.rand.nextFloat() 
							- world.rand.nextFloat()) * 0.2F) * 0.7F);
			setDead();
		}

	}
	
	
	private boolean damageable(EntityLivingBase entity) {
		boolean out = entity.getIsInvulnerable() || entity.world.isRemote || (entity.getHealth() <= 0.0);
		if(entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)entity;
			out = out || player.isCreative();
		}
		return !out;
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
	@Override
	public int getBrightnessForRender() {
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
