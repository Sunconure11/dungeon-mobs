package com.gw.dm.ai;

import com.gw.dm.entity.EntityHissingDemon;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.util.EnumHand;

public class AIHissingDemonMelee extends EntityAIAttackMelee {
	EntityHissingDemon demon;

	public AIHissingDemonMelee(EntityHissingDemon creature, double speedIn, boolean useLongMemory) {
		super(creature, speedIn, useLongMemory);
		demon = creature;
	}
	
	
	@Override
	protected void checkAndPerformAttack(EntityLivingBase target, double distance) {
        double dist = this.getAttackReachSqr(target);

        if (distance <= dist && this.attackTick <= 0) {
            attackTick = 3;
            demon.nextHand();
            demon.swingArm(EnumHand.MAIN_HAND);
            demon.attackEntityAsMob(target);
        }
    }

}
