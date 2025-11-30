package com.gw.dm.entity;

import static com.gw.dm.util.ConfigHandler.manticoreIg;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityTippedArrow;
import net.minecraft.init.Items;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import com.gw.dm.DungeonMobs;
import com.gw.dm.EntityDungeonMob;
import com.gw.dm.util.AudioHandler;
import com.gw.dm.util.ConfigHandler;
import com.gw.dm.util.DungeonMobsHelper;

public class EntityManticore extends EntityDungeonMob implements IRangedAttackMob {

	private static final String mobName = DungeonMobs.MODID + ":dmmanticore";

	public EntityManticore(World par1World) {
		super(par1World);
		experienceValue = 32;

		setSize(1.6F, 1.4F);

		tasks.addTask(1, new EntityAISwimming(this));
		tasks.addTask(2, new EntityAIAttackRanged(this, 0.3F, 10, 50, 40.0F));
		tasks.addTask(3, new EntityAIAttackMelee(this, 1.0F, false));
		tasks.addTask(4, new EntityAIWander(this, 1.0F));
		tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		tasks.addTask(5, new EntityAILookIdle(this));

		targetTasks.addTask(0, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(1, new EntityAINearestAttackableTarget(this,
				EntityPlayer.class, 0, true, false, null));
	}


	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase entityliving, float f) {
		int foo = rand.nextInt(6 - DungeonMobsHelper.getDifficulty(world));
		foo += (DungeonMobsHelper.getDifficulty(world) * 2);

		for (int i = 0; i < foo; i++) {
			EntityTippedArrow entityarrow = new EntityTippedArrow(this.world, this);

			entityarrow.posX += ((this.getRNG().nextFloat() - 0.5) / 2);
			entityarrow.posY += ((this.getRNG().nextFloat() - 0.5) / 2);
			entityarrow.posZ += ((this.getRNG().nextFloat() - 0.5) / 2);

			entityarrow.setDamage(Math.max(2, DungeonMobsHelper.getDifficulty(world) * 3));

			targetArrow(entityarrow);

			world.spawnEntity(entityarrow);
		}
		playSound(AudioHandler.entityManticoreAttack, 1.0f, 1.0f);
	}


	protected void targetArrow(EntityArrow entityarrow) {
		EntityLivingBase target = getAttackTarget();
		if (target == null) {
			return;
		}
		double vx = target.posX + (rand.nextFloat() - 0.5) - posX;
		double vy = target.getEntityBoundingBox().minY
				+ (double) (target.height / 2.0F)
				+ (rand.nextFloat() - 0.5)
				- entityarrow.posY;
		double vz = target.posZ + (rand.nextFloat() - 0.5) - posZ;
		double dist = MathHelper.sqrt((vx * vx) + (vz * vz));
		entityarrow.shoot(vx, vy + (dist / 5), vz, 1.6F,
				(16 - world.getDifficulty().getId() * 4));
	}


	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D 
				* ConfigHandler.healthx);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(3.0D
				* ConfigHandler.damagex + ConfigHandler.damageplus);
		this.getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(6.0D);
		this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.0D);
	}


	@Override
	protected SoundEvent getAmbientSound() {
		if (rand.nextInt(2) == 0) {
			return AudioHandler.entityManticoreA1;
		} else {
			return AudioHandler.entityManticoreA2;
		}
	}


	@Override
	protected SoundEvent getHurtSound(DamageSource ds) {
		return AudioHandler.entityManticoreHurt;
	}


	@Override
	protected SoundEvent getDeathSound() {
		return AudioHandler.entityManticoreDeath;
	}


	@Override
	public boolean getCanSpawnHere() {
		if (manticoreIg || DungeonMobsHelper.isNearSpawner(world, this, mobName)) {
			return super.getCanSpawnHere();
		}
		if (world.canBlockSeeSky(new BlockPos(posX, posY, posZ))) {
			return false;
		}
		if (this.posY > 42.0D && !this.ignoreHeight) {
			return false;
		}
		return super.getCanSpawnHere();
	}


	public int getTalkInterval() {
		return 100;
	}


	protected void dropFewItems(boolean par1, int par2) {
		this.dropItem(Items.ARROW, this.rand.nextInt(12) + this.rand.nextInt(12));
		this.dropItem(Items.LEATHER, this.rand.nextInt(3));
	}


	public boolean attackEntityFrom(DamageSource par1DamageSource, int par2) {
		if ((par1DamageSource.getTrueSource() != null)
				&& (par1DamageSource.getTrueSource().equals(this)))
			return false;

		return super.attackEntityFrom(par1DamageSource, par2);
	}


	@Override
	public void setSwingingArms(boolean swingingArms) {/*Do Nothing*/}
}
