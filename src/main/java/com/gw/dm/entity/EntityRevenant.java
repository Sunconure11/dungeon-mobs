package com.gw.dm.entity;

import static com.gw.dm.util.ConfigHandler.revenantIg;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFleeSun;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAIRestrictSun;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityAIZombieAttack;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import com.gw.dm.DungeonMobs;
import com.gw.dm.util.DungeonMobsHelper;

public class EntityRevenant extends EntityZombie implements IBeMagicMob  {
	private static double HEALTH = 30.0;
	private static String mobName = DungeonMobs.MODID + ":dmrevenant";
	public boolean ignoreHeight;
	private boolean darkspawn;
	private boolean newspawn;
	private Potion buff;
	private Potion regen;

	public EntityRevenant(World worldIn) {
		super(worldIn);
		regen = Potion.getPotionFromResourceLocation("regeneration");
		experienceValue = 15;
		darkspawn = true;
		newspawn = true;
	}


	public boolean attackEntityAsMob(Entity victim) {
		if (super.attackEntityAsMob(victim)) {
			if (!isChild() && (victim instanceof EntityLivingBase)) {
				switch (world.getDifficulty()) {
					case EASY:
						((EntityLivingBase) victim)
								.addPotionEffect(new PotionEffect(MobEffects.WITHER, 80, 1));
						break;
					case HARD:
						((EntityLivingBase) victim)
								.addPotionEffect(new PotionEffect(MobEffects.WITHER, 120, 1));
						break;
					case NORMAL:
						((EntityLivingBase) victim)
								.addPotionEffect(new PotionEffect(MobEffects.WITHER, 100, 1));
						break;
					case PEACEFUL:
					default:
						break;

				}
			}
			return true;
		}
		return false;
	}


	protected void initEntityAI() {
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIRestrictSun(this));
		tasks.addTask(2, new EntityAILeapAtTarget(this, 0.8F));
		tasks.addTask(3, new EntityAIZombieAttack(this, 1.0D, false));
		tasks.addTask(4, new EntityAIFleeSun(this, 1.0D));
		tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
		tasks.addTask(7, new EntityAIWander(this, 1.0D));
		tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		tasks.addTask(8, new EntityAILookIdle(this));
		applyEntityAI();
	}


	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(HEALTH);
		getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE);
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4d);
		getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(10.0d);
		getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0d);
		getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(24.0d);
	}


	public void addBuff() {
		if (isChild() || ((rand.nextInt(16) + 24) < posY)) {
			buff = null;
			return;
		}
		switch (rand.nextInt(6)) {
			case 0:
				buff = Potion.getPotionFromResourceLocation("resistance");
				return;
			case 1:
				buff = Potion.getPotionFromResourceLocation("speed");
				return;
			case 2:
				buff = Potion.getPotionFromResourceLocation("strength");
				return;
			case 3:
				buff = Potion.getPotionFromResourceLocation("fire_resistance");
				return;
			case 4:
				buff = Potion.getPotionFromResourceLocation("jump_boost");
				return;
			case 5:
			default:
				buff = regen;
				return;
		}
	}


	@Override
	public boolean getCanSpawnHere() {
		if (revenantIg || DungeonMobsHelper.isNearSpawner(world, this, mobName)) {
			darkspawn = false;
			return super.getCanSpawnHere();
		}
		if (world.canBlockSeeSky(new BlockPos(posX, posY, posZ))
				|| (!world.isRemote && world.isDaytime())) {
			return false;
		}
		if (this.posY > 42.0D && !ignoreHeight) {
			return false;
		}
		return super.getCanSpawnHere();
	}


	@Override
	public void onUpdate() {
		// "Hacky" and inefficient, but its the option the vanilla code leaves
		if (newspawn) {
			addBuff();
			newspawn = false;
			darkspawn = !DungeonMobsHelper.isNearSpawner(world, this, mobName);
		}
		if (isInDay()) {
			setDead();
		}
		if (buff != null) {
			if (buff == regen) {
				regenerate();
			} else if (!isPotionActive(buff)) {
				addPotionEffect(new PotionEffect(buff, 60, 1));
			}
		}
		super.onUpdate();
	}


	public boolean isInDay() {
		return (darkspawn && !world.isRemote && world.isDaytime());
	}


	@Override
	protected void jump() {
		super.jump();
		// TODO: More forward
	}


	private void regenerate() {
		if (!isDead && (getHealth() > 0)
				&& (getHealth() < HEALTH) && !isBurning()) {
			setHealth(getHealth() + 0.05f);
		}
	}

}
