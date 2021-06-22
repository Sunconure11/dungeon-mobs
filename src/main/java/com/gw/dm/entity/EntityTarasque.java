package com.gw.dm.entity;

import static com.gw.dm.util.ConfigHandler.destrachanIg;

import javax.annotation.Nullable;

import com.gw.dm.DungeonMobs;
import com.gw.dm.EntityDungeonMob;
import com.gw.dm.util.AudioHandler;
import com.gw.dm.util.ConfigHandler;
import com.gw.dm.util.DungeonMobsHelper;

import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.functions.SetDamage;

public class EntityTarasque extends EntityDungeonMob implements IBeMagicMob {
	private static String mobName = DungeonMobs.MODID + ":dmtarasque";
	private static boolean exists = false; // There can be only one!

	public EntityTarasque(World worldIn) {
		super(worldIn);
        this.isImmuneToFire = true;
        this.experienceValue = 75;
		// TODO Auto-generated constructor stub
	}


	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(200.0d 
				* ConfigHandler.healthx);
		getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0d);;
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5d);
		getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(20.0d);
		getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(20.0d);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(20.0d 
				* ConfigHandler.damagex + ConfigHandler.damageplus);
		getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(20.0d);
	}

	
	@Override
    public boolean isNonBoss() {
        return false;
    }


	@Override
	public boolean getCanSpawnHere() {
		if(exists) {
			return false;
		}
		if (destrachanIg || DungeonMobsHelper.isNearSpawner(world, this, mobName)) {
			return super.getCanSpawnHere();
		}
		if (world.canBlockSeeSky(new BlockPos(posX, posY, posZ)) && !ignoreHeight) {
			return false;
		}
		if (posY > 25.0D && !ignoreHeight) {
			return false;
		}
		return super.getCanSpawnHere();
	}
	
	
	@Override
    @Nullable
    public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
		exists = true;
		return super.onInitialSpawn(difficulty, livingdata);
	}
	
	
	@Override
	public void setDead() {
		exists = false;
		super.setDead();
	}
	

	@Override
	protected SoundEvent getAmbientSound() {
		if(rand.nextBoolean()) {
			return AudioHandler.entityTarasqueAmbient;
		} else {
			return AudioHandler.entityTarasqueHurt;
		}
	}


	@Override
	protected SoundEvent getHurtSound(DamageSource src) {
		return AudioHandler.entityTarasqueHurt;
	}


	@Override
	protected SoundEvent getDeathSound() {
		return AudioHandler.entityTarasqueHurt;
	}
	

}
