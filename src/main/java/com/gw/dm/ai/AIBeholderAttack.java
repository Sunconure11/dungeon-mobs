package com.gw.dm.ai;

import com.gw.dm.entity.EntityAhriman;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;

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
		owner.doAttack();
	}
	
	
	public void attackMovement() {
		owner.getMoveHelper().onUpdateMoveHelper();
	}
	
}
