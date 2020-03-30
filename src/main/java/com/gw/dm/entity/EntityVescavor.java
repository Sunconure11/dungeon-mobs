package com.gw.dm.entity;

import com.gw.dm.DungeonMobs;
import com.gw.dm.EntityDungeonMob;
import com.gw.dm.potion.PotionEffectAddled;
import com.gw.dm.util.AudioHandler;
import com.gw.dm.util.ConfigHandler;
import com.gw.dm.util.DungeonMobsHelper;
import com.gw.dm.util.MiscRegistrar;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

import java.util.Iterator;
import java.util.List;

import static com.gw.dm.util.ConfigHandler.vescavorIg;

public class EntityVescavor extends EntityDungeonMob {
	private static String mobName = DungeonMobs.MODID + ":dmvescavor";
	private int confuseTicks;
	
	public EntityVescavor(World par1World) {
		super(par1World);
		setSize(0.8F, 0.9F);
		experienceValue = 20;
		ignoreHeight = false;
		confuseTicks = 0;
		
		tasks.addTask(0, new EntityAISwimming(this));
		tasks.addTask(1, new EntityAIAttackMelee(this, 1.0F, false));
		tasks.addTask(2, new EntityAIWander(this, 1.0F));
		tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		tasks.addTask(4, new EntityAILookIdle(this));
		
		targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true, false, null));
	}
	
	@Override
	public int getTotalArmorValue() {
		return 0;
	}
	
	@Override
	protected void jump() {
		motionY = 0.7D;
		
		Potion jump = Potion.getPotionFromResourceLocation("jump_boost");
		if (isPotionActive(jump)) {
			motionY += (double) ((float) (getActivePotionEffect(jump).getAmplifier() + 1) * 0.1F);
		}
		
		if (isSprinting()) {
			float var1 = rotationYaw * 0.017453292F;
			motionX -= (double) (MathHelper.sin(var1) * 0.2F);
			motionZ += (double) (MathHelper.cos(var1) * 0.2F);
		}
		
		isAirBorne = true;
		ForgeHooks.onLivingJump(this);
	}
	
	@Override
	protected SoundEvent getHurtSound(DamageSource src) {
		return AudioHandler.entityVescavorHurt;
	}
	
	@Override
	protected SoundEvent getDeathSound() {
		return AudioHandler.entityVescavorDeath;
	}
	
	@Override
	public boolean getCanSpawnHere() {
		if (vescavorIg || DungeonMobsHelper.isNearSpawner(world, this, mobName)) {
			return super.getCanSpawnHere();
		}
		if (world.canBlockSeeSky(new BlockPos(posX, posY, posZ))) return false;
		
		if (posY > 36.0D && !ignoreHeight) return false;
		
		return super.getCanSpawnHere();
	}
	
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(12.0d * ConfigHandler.healthx);
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(2.0D * ConfigHandler.damagex + ConfigHandler.damageplus);
		getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.0D);
	}
	
	public int getTalkInterval() {
		return 100;
	}
	
	@Override
	protected SoundEvent getAmbientSound() {
		if (rand.nextBoolean()) {
			return AudioHandler.entityVescavorAmbient1;
		}
		else {
			return AudioHandler.entityVescavorAmbient2;
		}
	}
	
	@Override
	protected void dropFewItems(boolean par1, int par2) {
		int var3;
		int var4;
		int var5;
		
		var3 = rand.nextInt(4);
		
		for (var5 = 0; var5 < var3; var5++) {
			dropItem(Items.EMERALD, 1);
		}
	}
	
	@Override
	public void onLivingUpdate() {
		confuseTicks++;
		
		if (confuseTicks % 20 == 0) {
			List nearbyPlayers = world.getEntitiesWithinAABB(EntityPlayer.class, getEntityBoundingBox().expand(16.0D, 4.0D, 16.0D));
			Iterator iter = nearbyPlayers.iterator();
			
			while (iter.hasNext()) {
				EntityPlayer foo = (EntityPlayer) iter.next();
				
				if (canEntityBeSeen(foo) && !foo.capabilities.isCreativeMode) {
					foo.addPotionEffect(new PotionEffectAddled(MiscRegistrar.potionAddle, 360, 0));
				}
			}
			confuseTicks = 0;
		}
		super.onLivingUpdate();
	}
}
