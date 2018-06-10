package com.gw.dm.ai;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;

import com.gw.dm.entity.EntityAhriman;

public class AIBeholderAttack extends EntityAIBase {
	EntityAhriman owner;
	private int cooldown;
	
	
	public AIBeholderAttack(EntityAhriman it) {
		owner = it;
	}
	

	@Override
	public boolean shouldExecute() {
		return (owner.getAttackTarget() != null) && !owner.getAttackTarget().isDead;
	}
	
	
	@Override
	public boolean shouldContinueExecuting() {
		return shouldExecute();
	}
	
	
	@Override
	public void startExecuting() {
		cooldown = 10 + owner.getRNG().nextInt(30);
		updateTask();
	}
	
	
	@Override
	public void updateTask() {
		EntityLivingBase target = owner.getAttackTarget();
		attackMovement();
		/*if(cooldown-- < 1) {
			owner.attackEntityWithRangedAttack(owner.getAttackTarget(), 1.0f);
			cooldown = 10 + owner.getRNG().nextInt(20); // Not sure that is the right delay
		}*/
		owner.doAttack();
	}
	
	
	public void attackMovement() {
		owner.getMoveHelper().onUpdateMoveHelper();
	}

}
