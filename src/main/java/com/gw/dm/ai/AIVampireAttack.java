package com.gw.dm.ai;

import com.gw.dm.entity.EntityVampire;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIAvoidEntity;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.player.EntityPlayer;

public class AIVampireAttack extends EntityAIBase {
	private static final float THRESHHOLD = 16f;
	private EntityAIAttackMelee attack;
	private EntityAIAvoidEntity run;
	private EntityVampire owner;


	public AIVampireAttack(EntityVampire vampire) {
		owner = vampire;
		attack = new EntityAIAttackMelee(vampire, 1.0d, false);
		run = new EntityAIAvoidEntity(vampire, EntityPlayer.class, 16.0f, 1.5d, 1.5d);
	}


	@Override
	public boolean shouldExecute() {
		if (shouldAttack()) {
			return attack.shouldExecute();
		}
		else {
			return run.shouldExecute();
		}
	}


	@Override
	public boolean shouldContinueExecuting() {
		if (shouldAttack()) {
			return attack.shouldContinueExecuting();
		}
		else {
			return run.shouldContinueExecuting();
		}
	}


	@Override
	public void startExecuting() {
		if (shouldAttack()) {
			attack.startExecuting();
		}
		else {
			run.startExecuting();
		}
	}


	@Override
	public void resetTask() {
		if (shouldAttack()) {
			attack.resetTask();
		}
		else {
			run.resetTask();
		}
	}


	@Override
	public void updateTask() {
		if (shouldAttack()) {
			attack.updateTask();
		}
	}


	private boolean shouldAttack() {
		boolean out = (owner.getHealth() > THRESHHOLD) || owner.isTrapped() || !(owner.getTags() instanceof EntityPlayer);
		owner.setRunning(!out);
		return out;
	}

}
