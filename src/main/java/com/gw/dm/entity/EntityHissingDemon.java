package com.gw.dm.entity;

import com.gw.dm.EntityDungeonMob;
import com.gw.dm.util.AudioHandler;

import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityHissingDemon extends EntityDungeonMob implements IBeMagicMob {

	public EntityHissingDemon(World worldIn) {
		super(worldIn);
		// TODO Auto-generated constructor stub
	}
	

	@Override
	protected SoundEvent getAmbientSound() {
		if(rand.nextBoolean()) {
			return AudioHandler.entityMaralithAmbient1;
		} else {
			return AudioHandler.entityMaralithAmbient2;
		}
	}


	@Override
	protected SoundEvent getHurtSound(DamageSource src) {
		return AudioHandler.entityMaralithHurt;
	}


	@Override
	protected SoundEvent getDeathSound() {
		return AudioHandler.entityMaralithHurt;
	}

}
