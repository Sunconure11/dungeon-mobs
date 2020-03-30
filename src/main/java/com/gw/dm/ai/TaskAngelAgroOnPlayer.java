package com.gw.dm.ai;

import com.gw.dm.entity.EntityFallenAngel;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;

public class TaskAngelAgroOnPlayer extends EntityAIBase {
	EntityFallenAngel owner;
	double range;
	EntityLivingBase target;

	public TaskAngelAgroOnPlayer(EntityFallenAngel angel, double distance) {
		owner = angel;
		range = distance;
	}


	@Override
	public boolean shouldExecute() {
		target = owner.world.getNearestAttackablePlayer(owner, range, range);
		return ((target != null) && owner.canEntityBeSeen(target));
	}

	@Override
	public boolean shouldContinueExecuting() {
		return false;
	}

	@Override
	public void startExecuting() {
		owner.setAttackTarget(target);
	}

}
