package com.gw.dm.entity;

import static com.gw.dm.util.ConfigHandler.vampireIg;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFleeSun;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIRestrictSun;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.gw.dm.DungeonMobs;
import com.gw.dm.DungeonMobsDamageSource;
import com.gw.dm.EntityDungeonMob;
import com.gw.dm.ai.AIVampireAttack;
import com.gw.dm.util.ConfigHandler;
import com.gw.dm.util.DungeonMobsHelper;

public class EntityVampire extends EntityDungeonMob {
	private static String mobName = DungeonMobs.MODID + ":dmvampire";
	private boolean escaping;
	private boolean feelTrapped;

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
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(40.0d);
		getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE);
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.54d);
		getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(12.0d);
		getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0d);
		getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(16.0d);
	}


	protected void applyEntityAI() {
		targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
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
			} else if (!this.isDead && (getHealth() > 0)) {
				setHealth(getHealth() + 0.05f);
			}
		}
		super.onLivingUpdate();
	}


	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.UNDEAD;
	}


	public boolean attackEntityAsMob(Entity victim) {
		if (escaping) {
			setTrapped();
		}
		if(super.attackEntityAsMob(victim)) {
			switch (world.getDifficulty()) {
				case EASY:
					energyDrain(victim, -1, 8);
					break;
				case HARD:
					energyDrain(victim, -2, 12);
					break;
				case NORMAL:
					energyDrain(victim, -1, 10);
					break;
				case PEACEFUL:
				default:
					break;
			}
			return true;
		} else {
			return false;
		}
	}
	
	
	private void energyDrain(Entity victim, int levels, int exhaustion) {
		if(victim instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) victim;
			player.addExhaustion(exhaustion);
			if(ConfigHandler.hardcoreVampire) {
				if(player.experienceLevel < (0 - levels)) {
					player.attackEntityFrom(DungeonMobsDamageSource.energyDrain, 1024);
				}
				player.addExperienceLevel(levels);
			}
			heal(2.0f);
		} else if(victim instanceof EntityLiving) {
			EntityLiving mob = (EntityLiving)victim;
			mob.attackEntityFrom(DungeonMobsDamageSource.energyDrain, 
					(exhaustion / 2) - levels);
			heal(2.0f);
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
		if (vampireIg || DungeonMobsHelper.isNearSpawner(world, this, mobName)) {
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
