package com.gw.dm.entity;

import com.gw.dm.DungeonMobs;
import com.gw.dm.EntityDungeonMob;
import com.gw.dm.ai.AIVampireAttack;
import com.gw.dm.util.DungeonMobsHelper;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

public class EntityVampire extends EntityDungeonMob {
	private boolean escaping;
	private boolean feelTrapped;
	private static String mobName = DungeonMobs.MODID + ":dmvampire";

	public EntityVampire(World worldIn) {
		super(worldIn);
		experienceValue = 20;
		feelTrapped = false;
	}


	@Override
	public boolean isEntityUndead() {
		return true;
	}


	@Override
	protected void initEntityAI() {
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIRestrictSun(this));
		tasks.addTask(2, new EntityAIFleeSun(this, 1.0d));
		tasks.addTask(3, new EntityAIMoveTowardsRestriction(this, 1.0d));
		tasks.addTask(5, new AIVampireAttack(this));
		tasks.addTask(7, new EntityAIWander(this, 1.0d));
		tasks.addTask(7, new EntityAIMoveThroughVillage(this, 1.0d, false));
		tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		tasks.addTask(8, new EntityAILookIdle(this));
		applyEntityAI();
	}


	@Override
	protected void applyEntityAttributes() {
		getAttributeMap().registerAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0d);
		getAttributeMap().registerAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE);
		getAttributeMap().registerAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.54d);
		getAttributeMap().registerAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(12.0d);
		getAttributeMap().registerAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS);
		getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0d);
		getAttributeMap().registerAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(16.0d);
	}


	protected void applyEntityAI() {
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
		targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityVillager.class, false));
		targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityIronGolem.class, true));
	}


	@Override
	public void onLivingUpdate() {
		if (!world.isRemote) {
			if (world.isDaytime() && (getBrightness() > 0.5f) && world.canSeeSky(getPosition())) {
				setFire(8);
				setHealth(getHealth() - 1.0f);
			} else if(!this.isDead && (getHealth() > 0)) {
				setHealth(getHealth() + 0.05f);
			}
		}
		super.onLivingUpdate();
	}


	public String getName() {
		if (this.hasCustomName()) {
			return this.getCustomNameTag();
		}
		return I18n.translateToLocal("entity.rpgdungeonsjbg.vampire.name");
	}


	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.UNDEAD;
	}


	public boolean attackEntityAsMob(Entity victim) {
		if (escaping) {
			setTrapped();
		}
		if (super.attackEntityAsMob(victim)) {
			// Energy drain power on the victim (which heals the vampire)
			if (victim instanceof EntityPlayer) {
				EntityPlayer player = (EntityPlayer) victim;
				switch (world.getDifficulty()) {
					case EASY:
						player.addExhaustion(8);
						break;
					case HARD:
						player.addExhaustion(12);
						player.setHealth(player.getHealth() - 1);
						if (player.getHealth() < 0.0f) {
							player.setHealth(0.0f);
						}
						break;
					case NORMAL:
						player.addExhaustion(10);
						break;
					case PEACEFUL:
					default:
						break;
				}
				heal(2.0f);
			}
			return true;
		} else {
			return false;
		}
	}
	

	@Override
	protected void dropFewItems(boolean par1, int par2) {
		// They drop some cash, OK....
		dropItem(Items.GOLD_INGOT, rand.nextInt(2) + par2);
		if (par1) {
			dropItem(Items.EMERALD, rand.nextInt(2) + par2);
		}
	}


	@Override
	public boolean getCanSpawnHere() {
		if (world.canSeeSky(new BlockPos(posX, posY, posZ))) {
			return false;
		}
		if (DungeonMobsHelper.isNearSpawner(world, this, mobName)) {
			return super.getCanSpawnHere();
		}
		if (posY > 32.0D && !ignoreHeight) {
			return false;
		}
		return super.getCanSpawnHere();
	}


	public void setRunning(boolean escape) {
		escaping = escape;
	}


	public void setTrapped() {
		feelTrapped = true;
	}


	public boolean isEscaping() {
		return escaping;
	}


	public boolean isTrapped() {
		return feelTrapped;
	}
}
