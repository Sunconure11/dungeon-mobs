package com.gw.dm.ai;

import com.gw.dm.entity.EntityLizalfos;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.Path;

public class EntityAIFollowTwin extends EntityAIBase {
	private EntityLizalfos entityOwner;
	private float speed;
	private int cooldown;
	private Path path;


	public EntityAIFollowTwin(EntityLizalfos entity, float par2) {
		this.entityOwner = entity;
		this.speed = par2;
	}


	@Override
	public boolean shouldExecute() {
		boolean out = !entityOwner.isTwinDead() || this.entityOwner.getAttackTarget() != null;
		EntityLizalfos twin = entityOwner.getTwin();
		if (out && (twin != null)) {
			path = entityOwner.getNavigator().getPathToEntityLiving(twin);
		}
		return out && (path != null);
	}


	@Override
	public boolean shouldContinueExecuting() {
		EntityLizalfos twin = entityOwner.getTwin();
		if ((path == null)
				|| entityOwner.isTwinDead()
				|| (entityOwner.getAttackTarget() != null)
				|| (twin == null)) {
			return false;
		} else {
			double var1 = this.entityOwner.getDistanceSq(this.entityOwner.getTwin());
			return var1 >= 16.0D;
		}
	}


	@Override
	public void startExecuting() {
		this.cooldown = 10;
		if (path != null) {
			entityOwner.getNavigator().setPath(path, speed);
		}
	}


	@Override
	public void updateTask() {
		if (--this.cooldown <= 0) {
			this.cooldown = 10;
			EntityLizalfos twin = entityOwner.getTwin();
			if (twin != null) {
				path = entityOwner.getNavigator().getPathToEntityLiving(twin);
				if (path != null) {
					entityOwner.getNavigator().setPath(path, speed);
				}
			}
		}
	}
}
