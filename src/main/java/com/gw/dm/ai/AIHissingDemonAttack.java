package com.gw.dm.ai;

import com.gw.dm.entity.EntityHissingDemon;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.util.EnumHand;

public class AIHissingDemonAttack extends EntityAIAttackMelee {
	EntityHissingDemon demon;

	public AIHissingDemonAttack(EntityHissingDemon creature, double speedIn, boolean useLongMemory) {
		super(creature, speedIn, useLongMemory);
		demon = creature;
	}
	
	// TODO: Archvile-like attack
	// TODO: List of extra enemies
	// TODO: Re-write to not depend on or extend EntityAIAttackMelee?
	
	
	@Override
    protected void checkAndPerformAttack(EntityLivingBase victim, double dist) {
        double distance = this.getAttackReachSqr(victim);

        if (dist <= distance && this.attackTick <= 0)
        {
            this.attackTick = 4;
            this.attacker.swingArm(EnumHand.MAIN_HAND);
            this.attacker.attackEntityAsMob(victim);
        }
    }
	

}
