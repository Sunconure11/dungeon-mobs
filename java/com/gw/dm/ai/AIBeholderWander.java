package com.gw.dm.ai;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;

import com.gw.dm.entity.EntityAhriman;

// Should this exist?
public class AIBeholderWander extends EntityAIBase {
	EntityAhriman owner;
	
	
	public AIBeholderWander(EntityAhriman it) {
		owner = it;
	}
	
	
	@Override
	public boolean shouldExecute() {
		EntityLivingBase target = owner.getAttackTarget();
		return ((target == null) || (target.isDead)) && (owner.getRNG().nextInt(10) == 0);
	}
	
	
	@Override
	public boolean shouldContinueExecuting() {
		EntityLivingBase target = owner.getAttackTarget();
		return ((target == null) || (!target.isDead)) && (owner.courseChangeCooldown > 1);
	}
	
	
	@Override
	public void startExecuting() {
		updateTask();
	}
	
	
	@Override
	public void updateTask() {
		owner.getMoveHelper().onUpdateMoveHelper();
	}

}
