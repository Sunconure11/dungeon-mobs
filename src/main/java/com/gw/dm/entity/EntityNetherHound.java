package com.gw.dm.entity;

import com.gw.dm.DungeonMobs;
import com.gw.dm.EntityDungeonMob;
import com.gw.dm.util.AudioHandler;
import com.gw.dm.util.DungeonMobsHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderHell;

public class EntityNetherHound extends EntityDungeonMob {
	private static String mobName = DungeonMobs.MODID + ":dmnetherhound";
	public boolean ignoreHeight;
	private int flameTimer;
	private int jumpTimer;

	public EntityNetherHound(World par1World) {
		super(par1World);
		flameTimer = 0;
		jumpTimer = 0;
		ignoreHeight = false;
		experienceValue = 32;
		isImmuneToFire = true;

		setSize(1.2F, 1.1F);

		tasks.addTask(1, new EntityAISwimming(this));
		tasks.addTask(2, new EntityAIAttackMelee(this, 1.0F, false));
		tasks.addTask(3, new EntityAIWander(this, 1.0F));
		tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		tasks.addTask(4, new EntityAILookIdle(this));
		targetTasks.addTask(0, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(1, new EntityAINearestAttackableTarget(this,
				EntityPlayer.class, 0, true, false, null));
	}


	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(28.0D);
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.42D);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D);
		getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.0D);
	}


	@Override
	public int getTotalArmorValue() {
		return 5;
	}


	public int getAttackStrength(Entity par1Entity) {
		return 5;
	}


	@Override
	protected SoundEvent getAmbientSound() {
		return AudioHandler.entityNetherHoundAmbient;
	}


	@Override
	protected SoundEvent getHurtSound(DamageSource src) {
		return AudioHandler.entityNetherHoundHurt;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return null;
	}


	@Override
	public boolean getCanSpawnHere() {
		if (world.provider instanceof WorldProviderHell) {
			System.out.println(isNotColliding());
			return super.getCanSpawnHere();
		}
		if (DungeonMobsHelper.isNearSpawner(world, this, mobName)) {
			return super.getCanSpawnHere();
		}
		if (world.canBlockSeeSky(new BlockPos((int) posX, (int) posY, (int) posZ))) {
			return false;
		}
		if (posY > 48.0D && !ignoreHeight) {
			return false;
		}
		return super.getCanSpawnHere();
	}


	@Override
	public int getTalkInterval() {
		return 80;
	}


	@Override
	protected void dropFewItems(boolean par1, int par2) {
		int var3;
		int var4;

		var3 = rand.nextInt(2);

		for (var4 = 0; var4 < var3; var4++) {
			dropItem(Items.BLAZE_POWDER, 1);
		}
	}


	@Override
	public boolean attackEntityAsMob(Entity ent) {
		ent.setFire(8 + (DungeonMobsHelper.getDifficulty(world) * 2));

		return super.attackEntityAsMob(ent);
	}


	@Override
	public void onLivingUpdate() {
		if (getAttackTarget() != null
				&& canEntityBeSeen(getAttackTarget())) {
			double barX = getAttackTarget().posX - posX;
			double barY = getAttackTarget().posY - posY;
			double barZ = getAttackTarget().posZ - posZ;

			double fooX = barX * barX;
			double fooY = barY * barY;
			double fooZ = barZ * barZ;

			if (fooX + fooY + fooZ <= 16.0D) {
				flameTimer++;

				if (flameTimer >= (170 - (40 * DungeonMobsHelper.getDifficulty(world))))
					playSound(AudioHandler.entityNetherHoundFire, 1.0f, 1.0f);

				if (flameTimer >= (200 - (40 * DungeonMobsHelper.getDifficulty(world)))) {
					breatheFire();
					flameTimer = 0;
				}
			}
		}

		super.onLivingUpdate();
	}


	public void breatheFire() {
		int dir = MathHelper.floor((double) ((rotationYaw * 4F) / 360F) + 0.5D) & 3;

		boolean onAngle;

		float angle = (float) ((rotationYaw * 4F) / 360F) + 0.5F;

		while (angle > 1.0F) {
			angle -= 1.0;
		}

		if (angle <= 0.75F && angle >= 0.25F) {
			onAngle = true;
		} else {
			onAngle = false;
		}

		int myX = (int) posX;
		int myY = (int) posY;
		int myZ = (int) posZ;

		if (!onAngle) {
			if (dir == 0) { // SOUTH, +Z
				makeFire(myX, myY, myZ + 1);

				makeFire(myX - 1, myY - 1, myZ + 2);
				makeFire(myX, myY - 1, myZ + 2);
				makeFire(myX + 1, myY - 1, myZ + 2);

				makeFire(myX - 1, myY, myZ + 2);
				makeFire(myX, myY, myZ + 2);
				makeFire(myX + 1, myY, myZ + 2);

				makeFire(myX - 1, myY + 1, myZ + 2);
				makeFire(myX, myY + 1, myZ + 2);
				makeFire(myX + 1, myY + 1, myZ + 2);

				makeFire(myX - 2, myY - 2, myZ + 3);
				makeFire(myX - 1, myY - 2, myZ + 3);
				makeFire(myX, myY - 2, myZ + 3);
				makeFire(myX + 1, myY - 2, myZ + 3);
				makeFire(myX + 2, myY - 2, myZ + 3);

				makeFire(myX - 2, myY - 1, myZ + 3);
				makeFire(myX - 1, myY - 1, myZ + 3);
				makeFire(myX, myY - 1, myZ + 3);
				makeFire(myX + 1, myY - 1, myZ + 3);
				makeFire(myX + 2, myY - 1, myZ + 3);

				makeFire(myX - 2, myY, myZ + 3);
				makeFire(myX - 1, myY, myZ + 3);
				makeFire(myX, myY, myZ + 3);
				makeFire(myX + 1, myY, myZ + 3);
				makeFire(myX + 2, myY, myZ + 3);

				makeFire(myX - 2, myY + 1, myZ + 3);
				makeFire(myX - 1, myY + 1, myZ + 3);
				makeFire(myX, myY + 1, myZ + 3);
				makeFire(myX + 1, myY + 1, myZ + 3);
				makeFire(myX + 2, myY + 1, myZ + 3);

				makeFire(myX - 2, myY + 2, myZ + 3);
				makeFire(myX - 1, myY + 2, myZ + 3);
				makeFire(myX, myY + 2, myZ + 3);
				makeFire(myX + 1, myY + 2, myZ + 3);
				makeFire(myX + 2, myY + 2, myZ + 3);

			} else if (dir == 1) { // WEST, -X
				makeFire(myX - 1, myY, myZ);

				makeFire(myX - 2, myY - 1, myZ - 1);
				makeFire(myX - 2, myY - 1, myZ);
				makeFire(myX - 2, myY - 1, myZ + 1);

				makeFire(myX - 2, myY, myZ - 1);
				makeFire(myX - 2, myY, myZ);
				makeFire(myX - 2, myY, myZ + 1);

				makeFire(myX - 2, myY + 1, myZ - 1);
				makeFire(myX - 2, myY + 1, myZ);
				makeFire(myX - 2, myY + 1, myZ + 1);

				makeFire(myX - 3, myY - 2, myZ - 2);
				makeFire(myX - 3, myY - 2, myZ - 1);
				makeFire(myX - 3, myY - 2, myZ);
				makeFire(myX - 3, myY - 2, myZ + 1);
				makeFire(myX - 3, myY - 2, myZ + 2);

				makeFire(myX - 3, myY - 1, myZ - 2);
				makeFire(myX - 3, myY - 1, myZ - 1);
				makeFire(myX - 3, myY - 1, myZ);
				makeFire(myX - 3, myY - 1, myZ + 1);
				makeFire(myX - 3, myY - 1, myZ + 2);

				makeFire(myX - 3, myY, myZ - 2);
				makeFire(myX - 3, myY, myZ - 1);
				makeFire(myX - 3, myY, myZ);
				makeFire(myX - 3, myY, myZ + 1);
				makeFire(myX - 3, myY, myZ + 2);

				makeFire(myX - 3, myY + 1, myZ - 2);
				makeFire(myX - 3, myY + 1, myZ - 1);
				makeFire(myX - 3, myY + 1, myZ);
				makeFire(myX - 3, myY + 1, myZ + 1);
				makeFire(myX - 3, myY + 1, myZ + 2);

				makeFire(myX - 3, myY + 2, myZ - 2);
				makeFire(myX - 3, myY + 2, myZ - 1);
				makeFire(myX - 3, myY + 2, myZ);
				makeFire(myX - 3, myY + 2, myZ + 1);
				makeFire(myX - 3, myY + 2, myZ + 2);

			} else if (dir == 2) { // NORTH, -Z
				makeFire(myX, myY, myZ - 1);

				makeFire(myX - 1, myY - 1, myZ - 2);
				makeFire(myX, myY - 1, myZ - 2);
				makeFire(myX + 1, myY - 1, myZ - 2);

				makeFire(myX - 1, myY, myZ - 2);
				makeFire(myX, myY, myZ - 2);
				makeFire(myX + 1, myY, myZ - 2);

				makeFire(myX - 1, myY + 1, myZ - 2);
				makeFire(myX, myY + 1, myZ - 2);
				makeFire(myX + 1, myY + 1, myZ - 2);

				makeFire(myX - 2, myY - 2, myZ - 3);
				makeFire(myX - 1, myY - 2, myZ - 3);
				makeFire(myX, myY - 2, myZ - 3);
				makeFire(myX + 1, myY - 2, myZ - 3);
				makeFire(myX + 2, myY - 2, myZ - 3);

				makeFire(myX - 2, myY - 1, myZ - 3);
				makeFire(myX - 1, myY - 1, myZ - 3);
				makeFire(myX, myY - 1, myZ - 3);
				makeFire(myX + 1, myY - 1, myZ - 3);
				makeFire(myX + 2, myY - 1, myZ - 3);

				makeFire(myX - 2, myY, myZ - 3);
				makeFire(myX - 1, myY, myZ - 3);
				makeFire(myX, myY, myZ - 3);
				makeFire(myX + 1, myY, myZ - 3);
				makeFire(myX + 2, myY, myZ - 3);

				makeFire(myX - 2, myY + 1, myZ - 3);
				makeFire(myX - 1, myY + 1, myZ - 3);
				makeFire(myX, myY + 1, myZ - 3);
				makeFire(myX + 1, myY + 1, myZ - 3);
				makeFire(myX + 2, myY + 1, myZ - 3);

				makeFire(myX - 2, myY + 2, myZ - 3);
				makeFire(myX - 1, myY + 2, myZ - 3);
				makeFire(myX, myY + 2, myZ - 3);
				makeFire(myX + 1, myY + 2, myZ - 3);
				makeFire(myX + 2, myY + 2, myZ - 3);

			} else {// EAST, +X
				makeFire(myX + 1, myY, myZ);

				makeFire(myX + 2, myY - 1, myZ - 1);
				makeFire(myX + 2, myY - 1, myZ);
				makeFire(myX + 2, myY - 1, myZ + 1);

				makeFire(myX + 2, myY, myZ - 1);
				makeFire(myX + 2, myY, myZ);
				makeFire(myX + 2, myY, myZ + 1);

				makeFire(myX + 2, myY + 1, myZ - 1);
				makeFire(myX + 2, myY + 1, myZ);
				makeFire(myX + 2, myY + 1, myZ + 1);

				makeFire(myX + 3, myY - 2, myZ - 2);
				makeFire(myX + 3, myY - 2, myZ - 1);
				makeFire(myX + 3, myY - 2, myZ);
				makeFire(myX + 3, myY - 2, myZ + 1);
				makeFire(myX + 3, myY - 2, myZ + 2);

				makeFire(myX + 3, myY - 1, myZ - 2);
				makeFire(myX + 3, myY - 1, myZ - 1);
				makeFire(myX + 3, myY - 1, myZ);
				makeFire(myX + 3, myY - 1, myZ + 1);
				makeFire(myX + 3, myY - 1, myZ + 2);

				makeFire(myX + 3, myY, myZ - 2);
				makeFire(myX + 3, myY, myZ - 1);
				makeFire(myX + 3, myY, myZ);
				makeFire(myX + 3, myY, myZ + 1);
				makeFire(myX + 3, myY, myZ + 2);

				makeFire(myX + 3, myY + 1, myZ - 2);
				makeFire(myX + 3, myY + 1, myZ - 1);
				makeFire(myX + 3, myY + 1, myZ);
				makeFire(myX + 3, myY + 1, myZ + 1);
				makeFire(myX + 3, myY + 1, myZ + 2);

				makeFire(myX + 3, myY + 2, myZ - 2);
				makeFire(myX + 3, myY + 2, myZ - 1);
				makeFire(myX + 3, myY + 2, myZ);
				makeFire(myX + 3, myY + 2, myZ + 1);
				makeFire(myX + 3, myY + 2, myZ + 2);
			}
		} else {
			// H'okay, so we're on an angle...
			// Let's get some basic math here.
			// SOUTHWEST: - X, + Z
			// NORTHWEST: - X, - Z
			// NORTHEAST: + X, - Z
			// SOUTHEAST: + X, + Z
			if (dir == 0) {
				// SOUTHWEST
				makeFire(myX - 1, myY, myZ + 1);

				makeFire(myX - 1, myY - 1, myZ + 2);
				makeFire(myX - 2, myY - 1, myZ + 1);

				makeFire(myX - 1, myY, myZ + 2);
				makeFire(myX - 2, myY, myZ + 1);

				makeFire(myX - 1, myY + 1, myZ + 2);
				makeFire(myX - 2, myY + 1, myZ + 1);

				makeFire(myX - 1, myY - 2, myZ + 3);
				makeFire(myX - 2, myY - 2, myZ + 2);
				makeFire(myX - 3, myY - 2, myZ + 1);

				makeFire(myX - 1, myY - 1, myZ + 3);
				makeFire(myX - 2, myY - 1, myZ + 2);
				makeFire(myX - 3, myY - 1, myZ + 1);

				makeFire(myX - 1, myY, myZ + 3);
				makeFire(myX - 2, myY, myZ + 2);
				makeFire(myX - 3, myY, myZ + 1);

				makeFire(myX - 1, myY + 1, myZ + 3);
				makeFire(myX - 2, myY + 1, myZ + 2);
				makeFire(myX - 3, myY + 1, myZ + 1);

				makeFire(myX - 1, myY + 2, myZ + 3);
				makeFire(myX - 2, myY + 2, myZ + 2);
				makeFire(myX - 3, myY + 2, myZ + 1);

				makeFire(myX - 1, myY - 3, myZ + 4);
				makeFire(myX - 2, myY - 3, myZ + 3);
				makeFire(myX - 3, myY - 3, myZ + 2);
				makeFire(myX - 4, myY - 3, myZ + 1);

				makeFire(myX - 1, myY - 2, myZ + 4);
				makeFire(myX - 2, myY - 2, myZ + 3);
				makeFire(myX - 3, myY - 2, myZ + 2);
				makeFire(myX - 4, myY - 2, myZ + 1);

				makeFire(myX - 1, myY - 1, myZ + 4);
				makeFire(myX - 2, myY - 1, myZ + 3);
				makeFire(myX - 3, myY - 1, myZ + 2);
				makeFire(myX - 4, myY - 1, myZ + 1);

				makeFire(myX - 1, myY, myZ + 4);
				makeFire(myX - 2, myY, myZ + 3);
				makeFire(myX - 3, myY, myZ + 2);
				makeFire(myX - 4, myY, myZ + 1);

				makeFire(myX - 1, myY + 1, myZ + 4);
				makeFire(myX - 2, myY + 1, myZ + 3);
				makeFire(myX - 3, myY + 1, myZ + 2);
				makeFire(myX - 4, myY + 1, myZ + 1);

				makeFire(myX - 1, myY + 2, myZ + 4);
				makeFire(myX - 2, myY + 2, myZ + 3);
				makeFire(myX - 3, myY + 2, myZ + 2);
				makeFire(myX - 4, myY + 2, myZ + 1);

				makeFire(myX - 1, myY + 3, myZ + 4);
				makeFire(myX - 2, myY + 3, myZ + 3);
				makeFire(myX - 3, myY + 3, myZ + 2);
				makeFire(myX - 4, myY + 3, myZ + 1);

			}
			if (dir == 1) {
				// NORTHWEST
				makeFire(myX - 1, myY, myZ - 1);

				makeFire(myX - 1, myY - 1, myZ - 2);
				makeFire(myX - 2, myY - 1, myZ - 1);

				makeFire(myX - 1, myY, myZ - 2);
				makeFire(myX - 2, myY, myZ - 1);

				makeFire(myX - 1, myY + 1, myZ - 2);
				makeFire(myX - 2, myY + 1, myZ - 1);

				makeFire(myX - 1, myY - 2, myZ - 3);
				makeFire(myX - 2, myY - 2, myZ - 2);
				makeFire(myX - 3, myY - 2, myZ - 1);

				makeFire(myX - 1, myY - 1, myZ - 3);
				makeFire(myX - 2, myY - 1, myZ - 2);
				makeFire(myX - 3, myY - 1, myZ - 1);

				makeFire(myX - 1, myY, myZ - 3);
				makeFire(myX - 2, myY, myZ - 2);
				makeFire(myX - 3, myY, myZ - 1);

				makeFire(myX - 1, myY + 1, myZ - 3);
				makeFire(myX - 2, myY + 1, myZ - 2);
				makeFire(myX - 3, myY + 1, myZ - 1);

				makeFire(myX - 1, myY + 2, myZ - 3);
				makeFire(myX - 2, myY + 2, myZ - 2);
				makeFire(myX - 3, myY + 2, myZ - 1);

				makeFire(myX - 1, myY - 3, myZ - 4);
				makeFire(myX - 2, myY - 3, myZ - 3);
				makeFire(myX - 3, myY - 3, myZ - 2);
				makeFire(myX - 4, myY - 3, myZ - 1);

				makeFire(myX - 1, myY - 2, myZ - 4);
				makeFire(myX - 2, myY - 2, myZ - 3);
				makeFire(myX - 3, myY - 2, myZ - 2);
				makeFire(myX - 4, myY - 2, myZ - 1);

				makeFire(myX - 1, myY - 1, myZ - 4);
				makeFire(myX - 2, myY - 1, myZ - 3);
				makeFire(myX - 3, myY - 1, myZ - 2);
				makeFire(myX - 4, myY - 1, myZ - 1);

				makeFire(myX - 1, myY, myZ - 4);
				makeFire(myX - 2, myY, myZ - 3);
				makeFire(myX - 3, myY, myZ - 2);
				makeFire(myX - 4, myY, myZ - 1);

				makeFire(myX - 1, myY + 1, myZ - 4);
				makeFire(myX - 2, myY + 1, myZ - 3);
				makeFire(myX - 3, myY + 1, myZ - 2);
				makeFire(myX - 4, myY + 1, myZ - 1);

				makeFire(myX - 1, myY + 2, myZ - 4);
				makeFire(myX - 2, myY + 2, myZ - 3);
				makeFire(myX - 3, myY + 2, myZ - 2);
				makeFire(myX - 4, myY + 2, myZ - 1);

				makeFire(myX - 1, myY + 3, myZ - 4);
				makeFire(myX - 2, myY + 3, myZ - 3);
				makeFire(myX - 3, myY + 3, myZ - 2);
				makeFire(myX - 4, myY + 3, myZ - 1);

			}
			if (dir == 2) {
				// NORTHEAST
				makeFire(myX + 1, myY, myZ - 1);

				makeFire(myX + 1, myY - 1, myZ - 2);
				makeFire(myX + 2, myY - 1, myZ - 1);

				makeFire(myX + 1, myY, myZ - 2);
				makeFire(myX + 2, myY, myZ - 1);

				makeFire(myX + 1, myY + 1, myZ - 2);
				makeFire(myX + 2, myY + 1, myZ - 1);

				makeFire(myX + 1, myY - 2, myZ - 3);
				makeFire(myX + 2, myY - 2, myZ - 2);
				makeFire(myX + 3, myY - 2, myZ - 1);

				makeFire(myX + 1, myY - 1, myZ - 3);
				makeFire(myX + 2, myY - 1, myZ - 2);
				makeFire(myX + 3, myY - 1, myZ - 1);

				makeFire(myX + 1, myY, myZ - 3);
				makeFire(myX + 2, myY, myZ - 2);
				makeFire(myX + 3, myY, myZ - 1);

				makeFire(myX + 1, myY + 1, myZ - 3);
				makeFire(myX + 2, myY + 1, myZ - 2);
				makeFire(myX + 3, myY + 1, myZ - 1);

				makeFire(myX + 1, myY + 2, myZ - 3);
				makeFire(myX + 2, myY + 2, myZ - 2);
				makeFire(myX + 3, myY + 2, myZ - 1);

				makeFire(myX + 1, myY - 3, myZ - 4);
				makeFire(myX + 2, myY - 3, myZ - 3);
				makeFire(myX + 3, myY - 3, myZ - 2);
				makeFire(myX + 4, myY - 3, myZ - 1);

				makeFire(myX + 1, myY - 2, myZ - 4);
				makeFire(myX + 2, myY - 2, myZ - 3);
				makeFire(myX + 3, myY - 2, myZ - 2);
				makeFire(myX + 4, myY - 2, myZ - 1);

				makeFire(myX + 1, myY - 1, myZ - 4);
				makeFire(myX + 2, myY - 1, myZ - 3);
				makeFire(myX + 3, myY - 1, myZ - 2);
				makeFire(myX + 4, myY - 1, myZ - 1);

				makeFire(myX + 1, myY, myZ - 4);
				makeFire(myX + 2, myY, myZ - 3);
				makeFire(myX + 3, myY, myZ - 2);
				makeFire(myX + 4, myY, myZ - 1);

				makeFire(myX + 1, myY + 1, myZ - 4);
				makeFire(myX + 2, myY + 1, myZ - 3);
				makeFire(myX + 3, myY + 1, myZ - 2);
				makeFire(myX + 4, myY + 1, myZ - 1);

				makeFire(myX + 1, myY + 2, myZ - 4);
				makeFire(myX + 2, myY + 2, myZ - 3);
				makeFire(myX + 3, myY + 2, myZ - 2);
				makeFire(myX + 4, myY + 2, myZ - 1);

				makeFire(myX + 1, myY + 3, myZ - 4);
				makeFire(myX + 2, myY + 3, myZ - 3);
				makeFire(myX + 3, myY + 3, myZ - 2);
				makeFire(myX + 4, myY + 3, myZ - 1);

			} else {
				// SOUTHEAST
				makeFire(myX + 1, myY, myZ + 1);

				makeFire(myX + 1, myY - 1, myZ + 2);
				makeFire(myX + 2, myY - 1, myZ + 1);

				makeFire(myX + 1, myY, myZ + 2);
				makeFire(myX + 2, myY, myZ + 1);

				makeFire(myX + 1, myY + 1, myZ + 2);
				makeFire(myX + 2, myY + 1, myZ + 1);

				makeFire(myX + 1, myY - 2, myZ + 3);
				makeFire(myX + 2, myY - 2, myZ + 2);
				makeFire(myX + 3, myY - 2, myZ + 1);

				makeFire(myX + 1, myY - 1, myZ + 3);
				makeFire(myX + 2, myY - 1, myZ + 2);
				makeFire(myX + 3, myY - 1, myZ + 1);

				makeFire(myX + 1, myY, myZ + 3);
				makeFire(myX + 2, myY, myZ + 2);
				makeFire(myX + 3, myY, myZ + 1);

				makeFire(myX + 1, myY + 1, myZ + 3);
				makeFire(myX + 2, myY + 1, myZ + 2);
				makeFire(myX + 3, myY + 1, myZ + 1);

				makeFire(myX + 1, myY + 2, myZ + 3);
				makeFire(myX + 2, myY + 2, myZ + 2);
				makeFire(myX + 3, myY + 2, myZ + 1);

				makeFire(myX + 1, myY - 3, myZ + 4);
				makeFire(myX + 2, myY - 3, myZ + 3);
				makeFire(myX + 3, myY - 3, myZ + 2);
				makeFire(myX + 4, myY - 3, myZ + 1);

				makeFire(myX + 1, myY - 2, myZ + 4);
				makeFire(myX + 2, myY - 2, myZ + 3);
				makeFire(myX + 3, myY - 2, myZ + 2);
				makeFire(myX + 4, myY - 2, myZ + 1);

				makeFire(myX + 1, myY - 1, myZ + 4);
				makeFire(myX + 2, myY - 1, myZ + 3);
				makeFire(myX + 3, myY - 1, myZ + 2);
				makeFire(myX + 4, myY - 1, myZ + 1);

				makeFire(myX + 1, myY, myZ + 4);
				makeFire(myX + 2, myY, myZ + 3);
				makeFire(myX + 3, myY, myZ + 2);
				makeFire(myX + 4, myY, myZ + 1);

				makeFire(myX + 1, myY + 1, myZ + 4);
				makeFire(myX + 2, myY + 1, myZ + 3);
				makeFire(myX + 3, myY + 1, myZ + 2);
				makeFire(myX + 4, myY + 1, myZ + 1);

				makeFire(myX + 1, myY + 2, myZ + 4);
				makeFire(myX + 2, myY + 2, myZ + 3);
				makeFire(myX + 3, myY + 2, myZ + 2);
				makeFire(myX + 4, myY + 2, myZ + 1);

				makeFire(myX + 1, myY + 3, myZ + 4);
				makeFire(myX + 2, myY + 3, myZ + 3);
				makeFire(myX + 3, myY + 3, myZ + 2);
				makeFire(myX + 4, myY + 3, myZ + 1);
			}
		}
	}


	private void makeFire(int x, int y, int z) {
		double foo = world.rand.nextFloat() - 0.5F;
		double bar = world.rand.nextFloat() - 0.5F;
		double cow = world.rand.nextFloat() - 0.5F;
		world.spawnParticle(EnumParticleTypes.FLAME, x + foo, y + bar, z + cow, 0.0D, 0.0D, 0.0D);

		foo = world.rand.nextFloat() - 0.5F;
		bar = world.rand.nextFloat() - 0.5F;
		cow = world.rand.nextFloat() - 0.5F;
		world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL, x + foo, y + bar, z + cow, 0.0D, 0.0D, 0.0D);

		BlockPos where = new BlockPos(x, y, z);
		if (world.isAirBlock(where))
			world.setBlockState(where, Blocks.FIRE.getDefaultState());
		else if (world.getBlockState(where).getBlock() == Blocks.WATER) {
			playSound(SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE, 1.0f, 1.0f);
			world.setBlockState(where, Blocks.AIR.getDefaultState());
		}
	}


	@Override
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeEntityToNBT(par1NBTTagCompound);

		par1NBTTagCompound.setInteger("StoneStatus", flameTimer);
	}


	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readEntityFromNBT(par1NBTTagCompound);

		flameTimer = par1NBTTagCompound.getInteger("flameTimer");
	}
}
