package com.gw.dm.ai;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.pathfinding.Path;

import com.gw.dm.entity.EntityLizalfos;

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
		if(out && (entityOwner.getTwin() != null)) {
			path = entityOwner.getNavigator().getPathToEntityLiving(entityOwner.getTwin());
		}
		return out && (path != null);
	}


	@Override
	public boolean shouldContinueExecuting() {
		if((path == null) || this.entityOwner.isTwinDead()
				|| (this.entityOwner.getAttackTarget() != null)) {
			return false;
		} else {
			double var1 = this.entityOwner.getDistanceSq(this.entityOwner.getTwin());
			return var1 >= 16.0D;
		}
	}


	@Override
	public void startExecuting() {
		this.cooldown = 10;
		if(path != null) {
			entityOwner.getNavigator().setPath(path, speed);
		}
	}


	@Override
	public void updateTask() {
		if (--this.cooldown <= 0) {
			this.cooldown = 10;
			path = entityOwner.getNavigator().getPathToEntityLiving(entityOwner.getTwin());
			if(path != null) {
				entityOwner.getNavigator().setPath(path, speed);
			}
		}
	}
}
