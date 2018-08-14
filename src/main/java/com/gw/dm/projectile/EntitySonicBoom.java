package com.gw.dm.projectile;

import com.gw.dm.entity.EntityDestrachan;
import com.gw.dm.util.DungeonMobsHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.*;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;


public class EntitySonicBoom extends Entity {
	public EntityLiving shootingEntity;
	public double accelerationX;
	public double accelerationY;
	public double accelerationZ;
	private int xTile = -1;
	private int yTile = -1;
	private int zTile = -1;
	private boolean inGround = false;
	private int ticksAlive = 0;
	private int ticksInAir = 0;
	private int explodeCount = 0;
	private int lastExplosionTick;


	public EntitySonicBoom(World par1World) {
		super(par1World);
		isImmuneToFire = true;
	}


	public EntitySonicBoom(World par1World,
	                       EntityLiving par2EntityLiving, double ax, double ay, double az) {
		super(par1World);
		shootingEntity = par2EntityLiving;
		setSize(1.0F, 1.0F);
		setLocationAndAngles(par2EntityLiving.posX, par2EntityLiving.posY + 1,
				par2EntityLiving.posZ, par2EntityLiving.rotationYaw,
				par2EntityLiving.rotationPitch);
		setPosition(posX, posY, posZ);
		motionX = motionY = motionZ = 0.0D;
		ax += rand.nextGaussian() * 0.4D;
		ay += rand.nextGaussian() * 0.4D;
		az += rand.nextGaussian() * 0.4D;
		double dist = (double) MathHelper.sqrt(ax * ax + ay * ay + az * az);
		accelerationX = ax / dist * 0.1D;
		accelerationY = ay / dist * 0.1D;
		accelerationZ = az / dist * 0.1D;
	}


	protected void onImpact(boolean lastAttempt) {
		if (ticksAlive > (lastExplosionTick + 10)) {
			world.createExplosion(this, posX, posY, posZ, 2F, true);
			lastExplosionTick = ticksAlive;
			explodeCount++;
		}

		if (explodeCount > DungeonMobsHelper.getDifficulty(world)) {
			setDead();
		}
		if (lastAttempt) {
			setDead();
		}
	}


	protected float getMotionFactor() {
		return 0.8F;
	}


	@SideOnly(Side.CLIENT)
	public boolean isInRangeToRenderDist(double par1) {
		return false;
	}


	@Override
	public void onUpdate() {
		world.spawnParticle(EnumParticleTypes.SPELL, posX, posY, posZ, 1.0d, 1.0d, 1.0d);
		++ticksAlive;

		if (ticksAlive % 50 == 0)
			onImpact(false);

		if (!world.isRemote && (shootingEntity != null && shootingEntity.isDead
				|| !world.isAirBlock(new BlockPos(posX, posY, posZ)))) {
			setDead();
		} else if (!isDead) {
			super.onUpdate();
			if (inGround) {

				if (world.isAirBlock(new BlockPos(xTile, yTile, zTile))) {
					if (ticksAlive == 600) {
						setDead();
					}

					return;
				}

				inGround = false;
				motionX *= (double) (rand.nextFloat() * 0.2F);
				motionY *= (double) (rand.nextFloat() * 0.2F);
				motionZ *= (double) (rand.nextFloat() * 0.2F);
				ticksAlive = 0;
				ticksInAir = 0;
			} else {
				++ticksInAir;
			}

			Vec3d var15 = new Vec3d(posX, posY, posZ);
			Vec3d var2 = new Vec3d(posX + motionX, posY + motionY, posZ + motionZ);
			RayTraceResult var3 = world.rayTraceBlocks(var15, var2);
			var15 = new Vec3d(posX, posY, posZ);
			var2 = new Vec3d(posX + motionX, posY + motionY, posZ + motionZ);

			if (var3 != null) {
				var2 = new Vec3d(var3.hitVec.x, var3.hitVec.y, var3.hitVec.z);
			}

			Entity var4 = null;
			List var5 = world.getEntitiesWithinAABBExcludingEntity(this,
					getEntityBoundingBox().offset(motionX, motionY, motionZ)
							.expand(1.0D, 1.0D, 1.0D));
			double var6 = 0.0D;

			for (int var8 = 0; var8 < var5.size(); ++var8) {
				Entity var9 = (Entity) var5.get(var8);

				if (var9.canBeCollidedWith()
						&& !var9.isEntityEqual(shootingEntity)
						&& !(var9 instanceof EntityDestrachan)
						&& !(var9 instanceof EntityArrow)
						&& !(var9 instanceof EntityThrowable)
						&& !(var9 instanceof EntityEyeRay)) {
					float var10 = 0.3F;
					AxisAlignedBB var11 = var9.getEntityBoundingBox()
							.expand(var10, var10, var10);
					RayTraceResult var12 = var11.calculateIntercept(var15, var2);

					if (var12 != null) {
						double var13 = var15.distanceTo(var12.hitVec);

						if (var13 < var6 || var6 == 0.0d) {
							var4 = var9;
							var6 = var13;
						}
					}
				}
			}

			if (var4 != null) {
				var3 = new RayTraceResult(var4);
			}

			if (var3 != null) {
				onImpact(true);
			}

			posX += motionX;
			posY += motionY;
			posZ += motionZ;
			float var16 = MathHelper.sqrt(motionX * motionX + motionZ * motionZ);
			rotationYaw = (float) (Math.atan2(motionZ, motionX)
					* 180.0D / Math.PI) + 90.0F;

			for (rotationPitch = (float) (Math.atan2((double) var16, motionY)
					* 180.0D / Math.PI) - 90.0F;
			     rotationPitch - prevRotationPitch < -180.0F;
			     prevRotationPitch -= 360.0F) {
			}

			while (rotationPitch - prevRotationPitch >= 180.0F) {
				prevRotationPitch += 360.0F;
			}

			while (rotationYaw - prevRotationYaw < -180.0F) {
				prevRotationYaw -= 360.0F;
			}

			while (rotationYaw - prevRotationYaw >= 180.0F) {
				prevRotationYaw += 360.0F;
			}

			rotationPitch = prevRotationPitch + (rotationPitch - prevRotationPitch) * 0.2F;
			rotationYaw = prevRotationYaw + (rotationYaw - prevRotationYaw) * 0.2F;
			float var17 = getMotionFactor();

			if (isInWater()) {
				for (int var19 = 0; var19 < 4; ++var19) {
					float var18 = 0.25F;
					world.spawnParticle(EnumParticleTypes.WATER_BUBBLE, posX
									- motionX * (double) var18, posY
									- motionY * (double) var18, posZ
									- motionZ * (double) var18,
							motionX, motionY, motionZ);
				}
				var17 = 0.8F;
			}

			motionX += accelerationX;
			motionY += accelerationY;
			motionZ += accelerationZ;
			motionX *= (double) var17;
			motionY *= (double) var17;
			motionZ *= (double) var17;
			setPosition(posX, posY, posZ);
		}
	}


	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
		par1NBTTagCompound.setShort("xTile", (short) xTile);
		par1NBTTagCompound.setShort("yTile", (short) yTile);
		par1NBTTagCompound.setShort("zTile", (short) zTile);
		par1NBTTagCompound.setByte("inGround", (byte) (inGround ? 1 : 0));
		par1NBTTagCompound.setTag("direction", newDoubleNBTList(new double[]{motionX, motionY, motionZ}));
		par1NBTTagCompound.setInteger("explosionCount", explodeCount);
		par1NBTTagCompound.setInteger("lastExplode", lastExplosionTick);
		par1NBTTagCompound.setInteger("ticksAlive", ticksAlive);
	}


	@Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
		xTile = par1NBTTagCompound.getShort("xTile");
		yTile = par1NBTTagCompound.getShort("yTile");
		zTile = par1NBTTagCompound.getShort("zTile");
		inGround = par1NBTTagCompound.getByte("inGround") == 1;

		if (par1NBTTagCompound.hasKey("direction")) {
			NBTTagList var2 = par1NBTTagCompound.getTagList("direction", 6);
			motionX = var2.getDoubleAt(0);
			motionY = var2.getDoubleAt(1);
			motionZ = var2.getDoubleAt(2);
		} else {
			setDead();
		}

		explodeCount = par1NBTTagCompound.getInteger("explosionCount");
		lastExplosionTick = par1NBTTagCompound.getInteger("lastExplode");
		ticksAlive = par1NBTTagCompound.getInteger("ticksAlive");
	}


	@Override
	public boolean canBeCollidedWith() {
		return false;
	}


	public float getCollisionBorderSize() {
		return 1.0F;
	}


	@SideOnly(Side.CLIENT)
	public float getShadowSize() {
		return 0.0F;
	}


	/**
	 * Gets how bright this entity is.
	 */
	public float getBrightness(float par1) {
		return 1.0F;
	}


	@SideOnly(Side.CLIENT)
	public int getBrightnessForRender(float par1) {
		return 15728880;
	}


	@Override
	protected void entityInit() {
	}


	@Override
	public void setDead() {
		super.setDead();
	}
}
