package com.gw.dm.ai;

/* 
 * EntityAIAttackAnythingWanted
 * Action AI.
 * Gets around problems in EntityAIAttackOnCollide, allowing the task owner
 * to attack any entity (such as items or minecarts), rather than just living ones.
 * 
 * (c) GnomeWorks 2013
 * Do not redistribute without permission.
 */

/*
 * Modified with permission by JaredBGreat
 */

import com.gw.dm.entity.EntityRustMonster;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class EntityAIAttackAnythingWanted extends EntityAIAttackMelee {
	World world;
	EntityCreature attacker;
	Entity entityTarget;

	int attackTick;
	float field_75440_e;
	boolean field_75437_f;

	Path entityPathEntity;
	Class classTarget;
	private int field_75445_i;

	public EntityAIAttackAnythingWanted(EntityCreature par1EntityLiving,
	                                    Class par2Class, float par3, boolean par4) {
		super(par1EntityLiving, par3, par4);
		classTarget = par2Class;
		attackTick = 0;
		attacker = par1EntityLiving;
		world = par1EntityLiving.world;
		field_75440_e = par3;
		field_75437_f = par4;
		setMutexBits(3);
	}


	@Override
	public boolean shouldExecute() {
		EntityRustMonster monster = (EntityRustMonster) attacker;
		Entity entity = monster.getTarget();

		if (entity == null)
			return false;
		else {
			entityTarget = entity;
			entityPathEntity = attacker.getNavigator()
					.getPathToXYZ(entity.posX,
							entity.getEntityBoundingBox().minY,
							entity.posZ);
			return entityPathEntity != null;
		}
	}

	@Override
	public boolean shouldContinueExecuting() {
		EntityRustMonster foo = (EntityRustMonster) attacker;
		Entity var1 = foo.getTarget();
		return var1 != null;
	}

	@Override
	public void updateTask() {
		attacker.getLookHelper().setLookPositionWithEntity(entityTarget, 30.0F, 30.0F);

		if ((field_75437_f
				|| attacker.getEntitySenses().canSee(entityTarget))
				&& --field_75445_i <= 0) {
			field_75445_i = 4 + attacker.getRNG().nextInt(7);
			attacker.getNavigator().tryMoveToXYZ(entityTarget.posX,
					entityTarget.getEntityBoundingBox().minY,
					entityTarget.posZ, field_75440_e);
		}

		attackTick = Math.max(attackTick - 1, 0);
		double var1 = (double) (attacker.width * 2.0F * attacker.width * 2.0F);

		if (attacker.getDistanceSq(entityTarget.posX,
				entityTarget.getEntityBoundingBox().minY,
				entityTarget.posZ) <= var1) {
			if (attackTick <= 0) {
				attackTick = 20;

				if (attacker.getHeldItemMainhand() != null)
					attacker.swingArm(EnumHand.MAIN_HAND);

				attacker.attackEntityAsMob(entityTarget);
			}
		}
	}
}
