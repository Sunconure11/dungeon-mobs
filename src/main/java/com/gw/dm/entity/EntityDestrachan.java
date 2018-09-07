package com.gw.dm.entity;

import com.gw.dm.DungeonMobs;
import com.gw.dm.EntityDungeonMob;
import com.gw.dm.projectile.EntitySonicBoom;
import com.gw.dm.util.AudioHandler;
import com.gw.dm.util.DungeonMobsHelper;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.StringTokenizer;

public class EntityDestrachan extends EntityDungeonMob implements IRangedAttackMob {
	private static String mobName = DungeonMobs.MODID + ":dmdestrachan";
	public EntityAIAttackRanged rangedAttack = new EntityAIAttackRanged(this, 0.4F, 10, 70, 20.0F);
	public EntityAIAttackMelee meleeAttack = new EntityAIAttackMelee(this, 0.4F, false);
	public float attackDistance;
	public float visionDistance;
	public boolean isRanged;
	public boolean ignoreHeight;
	private int resetAttackTimer;

	public EntityDestrachan(World par1World) {
		super(par1World);
		experienceValue = 35;
		resetAttackTimer = 0;
		setSize(1.9F, 1.7F);
		ignoreHeight = false;

		isRanged = true;

		tasks.addTask(1, new EntityAISwimming(this));
		tasks.addTask(2, rangedAttack);
		tasks.addTask(3, new EntityAIWander(this, 1.0D));
		tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 12.0F));
		tasks.addTask(4, new EntityAILookIdle(this));
		targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this,
				EntityPlayer.class, 0, true, false, null));
	}


	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(24.0D);
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4D);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
		getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.0D);
	}


	protected boolean isAIEnabled() {
		return true;
	}


	@Override
	public int getTotalArmorValue() {
		return 6;
	}


	public int getAttackStrength(Entity par1Entity) {
		return 4;
	}


	@Override
	public int getTalkInterval() {
		return 100;
	}


	@Override
	protected SoundEvent getAmbientSound() {
		if (rand.nextInt(4) == 0) {
			return AudioHandler.entityDestrachanDeath;
		}
		return AudioHandler.entityDestrachanAmbient;
	}


	@Override
	protected SoundEvent getHurtSound(DamageSource src) {
		return AudioHandler.entityDestrachanHurt;
	}


	@Override
	protected SoundEvent getDeathSound() {
		return AudioHandler.entityDestrachanDeath;
	}


	@Override
	public boolean getCanSpawnHere() {
		if (DungeonMobsHelper.isNearSpawner(world, this, mobName)) {
			return super.getCanSpawnHere();
		}
		if (world.canBlockSeeSky(new BlockPos(posX, posY, posZ))) {
			return false;
		}
		if (posY > 36.0D && !ignoreHeight) {
			return false;
		}
		return super.getCanSpawnHere();
	}


	@Override
	protected void dropFewItems(boolean par1, int par2) {
		int var3;
		int var4;

		var3 = rand.nextInt(2);

		for (var4 = 0; var4 < var3; var4++) {
			switch (rand.nextInt(2)) {
				case (0):
					dropItem(Items.RECORD_13, 1);
					break;
				case (1):
					dropItem(Items.RECORD_WAIT, 1);
					break;
				default:
					break;
			}
		}
	}


	@Override
	public boolean isPotionApplicable(PotionEffect par1PotionEffect) {
		return par1PotionEffect.getEffectName().equals("blindness") ? false :
				super.isPotionApplicable(par1PotionEffect);
	}


	public void resetAttackType() {
		tasks.removeTask(rangedAttack);
		tasks.removeTask(meleeAttack);

		EntityLivingBase theTarget = getAttackTarget();
		if (theTarget == null) {
			tasks.addTask(2, rangedAttack);
			isRanged = true;
		} else {
			double chkX = theTarget.posX - posX;
			double chkY = theTarget.posY - posY;
			double chkZ = theTarget.posZ - posZ;

			if (((chkX * chkX) + (chkY * chkY) + (chkZ * chkZ)) < 12.0D) {
				tasks.addTask(2, meleeAttack);
				isRanged = false;
			} else {
				tasks.addTask(2, rangedAttack);
				isRanged = true;
			}
		}
	}


	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase target, float lol) {
		double vx = target.posX + target.motionX - posX;
		double vy = target.getEntityBoundingBox().minY + target.motionY + target.height
				- getEntityBoundingBox().maxY;
		double vz = target.posZ + target.motionZ - posZ;

		EntitySonicBoom sonicBoom = new EntitySonicBoom(world, (EntityLiving) this, vx, vy, vz);
		world.spawnEntity(sonicBoom);
		playSound(AudioHandler.entityDestrachanStep, 1.0f, 1.0f);
	}


	@Override
	public boolean canAttackClass(Class par1Class) {
		return EntityDestrachan.class != par1Class;
	}


	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (source.isExplosion()) {
			return false;
		}
		return super.attackEntityFrom(source, amount);
	}

	@Override
	public void setSwingingArms(boolean swingingArms) {
	}


	public String getRegistryName() {
		if (mobName == null) fixNameIfNull();
		return mobName;
	}


	public void setRegistryName(String name) {
		mobName = (DungeonMobs.MODID + ":" + name).trim().toLowerCase();
	}


	private void fixNameIfNull() {
		StringTokenizer fixer = new StringTokenizer(this.getName()
				.trim().toLowerCase(), ".");
		do {
			mobName = fixer.nextToken();
		} while (fixer.hasMoreTokens());
	}
}
