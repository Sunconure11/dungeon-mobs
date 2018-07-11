package com.gw.dm.entity;

import com.gw.dm.DungeonMobs;
import com.gw.dm.util.DungeonMobsHelper;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

public class EntityRevenant extends EntityZombie {
	public boolean ignoreHeight;
	private static String mobName = DungeonMobs.MODID + ":dmrevenant";

	public EntityRevenant(World worldIn) {
		super(worldIn);
		experienceValue = 15;
	}


	public boolean attackEntityAsMob(Entity victim) {
		if (super.attackEntityAsMob(victim)) {
			if ((victim instanceof EntityLivingBase) && !isChild()) {
				switch (world.getDifficulty()) {
					case EASY:
						((EntityLivingBase) victim)
								.addPotionEffect(new PotionEffect(MobEffects.WITHER, 80));
						break;
					case HARD:
						((EntityLivingBase) victim)
								.addPotionEffect(new PotionEffect(MobEffects.WITHER, 120));
						break;
					case NORMAL:
						((EntityLivingBase) victim)
								.addPotionEffect(new PotionEffect(MobEffects.WITHER, 100));
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
		tasks.addTask(2, new EntityAILeapAtTarget(this, 0.6F));
		tasks.addTask(3, new EntityAIZombieAttack(this, 1.0D, false));
		tasks.addTask(4, new EntityAIFleeSun(this, 1.0D));
		tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
		tasks.addTask(7, new EntityAIWander(this, 1.0D));
		tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		tasks.addTask(8, new EntityAILookIdle(this));
		applyEntityAI();
	}


	protected void applyEntityAttributes() {
		getAttributeMap().registerAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0d);
		getAttributeMap().registerAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE);
		getAttributeMap().registerAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4d);
		getAttributeMap().registerAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(10.0d);
		getAttributeMap().registerAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS);
		getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0d);
		getAttributeMap().registerAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(24.0d);
		getAttributeMap().registerAttribute(SPAWN_REINFORCEMENTS_CHANCE).setBaseValue(this.rand.nextDouble()
				* net.minecraftforge.common.ForgeModContainer.zombieSummonBaseChance);
	}


	public String getName() {
		if (this.hasCustomName()) {
			return this.getCustomNameTag();
		}
		return I18n.translateToLocal("entity.rpgdungeonsjbg.revenant.name");
	}


	@Override
	public boolean getCanSpawnHere() {
		if (DungeonMobsHelper.isNearSpawner(world, this, mobName)) {
			return super.getCanSpawnHere();
		}
		if (world.canBlockSeeSky(new BlockPos(posX, posY, posZ))) {
			return false;
		}
		if (this.posY > 42.0D && !ignoreHeight) {
			return false;
		}
		return super.getCanSpawnHere();
	}

}
