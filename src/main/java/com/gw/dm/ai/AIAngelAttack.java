package com.gw.dm.ai;

import com.gw.dm.ai.AIAngelWander.AngelicMoveHelper;
import com.gw.dm.entity.EntityFallenAngel;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;

import java.util.Random;

public class AIAngelAttack extends EntityAIBase {
	private AIAngelWander baseMotion;
	private EntityFallenAngel owner;
	private boolean movingIn;
	private int cooldown1;
	private int cooldown2;
	private int shots;


	public AIAngelAttack(AIAngelWander wanderIn, EntityFallenAngel angel) {
		baseMotion = wanderIn;
		owner = angel;
		movingIn = false;
		cooldown1 = cooldown2 = shots = 0;
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
		attackMovement();
		cooldown2 = 10 + owner.getRNG().nextInt(21);
	}


	@Override
	public void updateTask() {
		EntityLivingBase target = owner.getAttackTarget();
		attackMovement();
		if (--cooldown1 > 0) {
			owner.setSwingingArms(false);
			return;
		}
		if (!owner.canEntityBeSeen(target)) {
			// Pause attack if target can't be hit
			cooldown1 = 10 + owner.getRNG().nextInt(21);
			owner.setSwingingArms(false);
			return;
		}
		if (!owner.areArmsUp()) {
			owner.setSwingingArms(true);
			return;
		}
		if (--cooldown2 <= 0) {
			owner.attackEntityWithRangedAttack(target, 1.0f);
			cooldown2 = 3;
			shots++;
		}
		if ((owner.getRNG().nextInt(14) + 7) < shots) {
			shots = 0;
			switch (owner.world.getDifficulty()) {
				case EASY:
					cooldown1 = 70 + owner.getRNG().nextInt(31);
					break;
				case HARD:
					cooldown1 = 21 + owner.getRNG().nextInt(50);
					break;
				case NORMAL:
				default:
					cooldown1 = 30 + owner.getRNG().nextInt(71);
					break;
			}

		}
	}


	public void attackMovement() {
		if (baseMotion.needNewTarget()) {
			Random r = owner.getRNG();
			EntityLivingBase victim = owner.getAttackTarget();
			double dx = victim.posX - owner.posX;
			double dy = victim.posY - owner.posY;
			double dz = victim.posZ - owner.posZ;
			double dist = ((dx * dx) + (dy * dy) + (dz * dz));
			if (dist > 576) {
				movingIn = true;
			}
			else if (dist < 256) {
				movingIn = false;
			}
			dx *= r.nextDouble();
			dy *= r.nextDouble();
			dz *= r.nextDouble();
			if (movingIn && !(((AngelicMoveHelper) owner.getMoveHelper()).blocked)) {
				dist = Math.sqrt((dx * dx) + (dy * dy) + (dz * dz)) / 4;
				baseMotion.setTarget((dx / dist), (dy / dist), (dz / dist));
				baseMotion.callMoveHelper();
			}
			else if ((dist < 64) && !(((AngelicMoveHelper) owner.getMoveHelper()).blocked)) {
				dist = -(Math.sqrt((dx * dx) + (dy * dy) + (dz * dz)) / 4);
				baseMotion.setTarget(dx / dist, dy / dist, dz / dist);
				baseMotion.callMoveHelper();
			}
			else {
				baseMotion.startExecuting();
			}
		}
	}

}
