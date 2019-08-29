package com.gw.dm.entity;

import static com.gw.dm.util.ConfigHandler.umberHulkIg;

import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

import com.gw.dm.DungeonMobs;
import com.gw.dm.EntityDungeonMob;
import com.gw.dm.util.AudioHandler;
import com.gw.dm.util.ConfigHandler;
import com.gw.dm.util.DungeonMobsHelper;

public class EntityUmberHulk extends EntityDungeonMob {
	private static String mobName = DungeonMobs.MODID + ":dmumberhulk";
	private boolean ignoreHeight;
	private int confuseTicks;

	public EntityUmberHulk(World par1World) {
		super(par1World);
		setSize(1.9F, 2.3F);
		experienceValue = 45;
		isImmuneToFire = true;
		ignoreHeight = false;

		confuseTicks = 0;

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
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(60.0D 
				* ConfigHandler.healthx);
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.18D);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(9.0D 
				* ConfigHandler.damagex + ConfigHandler.damageplus);
		getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0D);
	}


	@Override
	public int getTotalArmorValue() {
		return 12;
	}


	@Override
	protected SoundEvent getAmbientSound() {
		return AudioHandler.entityUmberHulkAmbient;
	}


	@Override
	protected SoundEvent getHurtSound(DamageSource src) {
		return AudioHandler.entityUmberHulkHurt;
	}


	@Override
	protected SoundEvent getDeathSound() {
		return null;
	}


	@Override
	public boolean getCanSpawnHere() {
		if (umberHulkIg || DungeonMobsHelper.isNearSpawner(world, this, mobName)) {
			return super.getCanSpawnHere();
		}
		if (world.canBlockSeeSky((new BlockPos(posX, posY, posZ)))) {
			return false;
		}
		if (posY > 40.0D && !ignoreHeight) {
			return false;
		}
		return super.getCanSpawnHere();
	}


	@Override
	public int getTalkInterval() {
		return 240;
	}


	@Override
	public void onLivingUpdate() {
		confuseTicks++;

		if (confuseTicks % 20 == 0) {
			List nearbyPlayers = world.getEntitiesWithinAABB(EntityPlayer.class,
					getEntityBoundingBox().expand(16.0D, 4.0D, 16.0D));
			Iterator iter = nearbyPlayers.iterator();

			while (iter.hasNext()) {
				EntityPlayer foo = (EntityPlayer) iter.next();

				if (canEntityBeSeen(foo) && !foo.capabilities.isCreativeMode) {
					foo.addPotionEffect(new
							PotionEffect(Potion
							.getPotionFromResourceLocation("nausea"), 100, 1));
				}
			}
			confuseTicks = 0;
		}
		super.onLivingUpdate();
	}


	@Override
	protected void jump() {
		motionY = 0.4D;

		Potion jump = Potion.getPotionFromResourceLocation("jump_boost");
		if (isPotionActive(jump))
			motionY += (double) ((float) (getActivePotionEffect(jump)
					.getAmplifier() + 1) * 0.1F);

		if (isSprinting()) {
			float var1 = rotationYaw * 0.017453292F;
			motionX -= (double) (MathHelper.sin(var1) * 0.2F);
			motionZ += (double) (MathHelper.cos(var1) * 0.2F);
		}

		isAirBorne = true;
		ForgeHooks.onLivingJump(this);
	}


	@Override
	protected void dropFewItems(boolean par1, int par2) {
		int var3;
		int var4;
		var3 = rand.nextInt(4) + 2 + (2 * par2);
		for (var4 = 0; var4 < var3; var4++) {
			dropItem(Items.LEATHER, 1);
		}
	}
}
