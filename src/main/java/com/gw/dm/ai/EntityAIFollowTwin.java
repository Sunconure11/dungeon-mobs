package com.gw.dm.ai;

import com.gw.dm.entity.EntityLizalfos;
import net.minecraft.entity.ai.EntityAIBase;

public class EntityAIFollowTwin extends EntityAIBase {
	private EntityLizalfos entityOwner;
	private float speed;
	private int field_75345_d;


	public EntityAIFollowTwin(EntityLizalfos entity, float par2) {
		this.entityOwner = entity;
		this.speed = par2;
	}


	@Override
	public boolean shouldExecute() {
		return !entityOwner.isTwinDead() || this.entityOwner.getAttackTarget() != null;
	}


	@Override
	public boolean shouldContinueExecuting() {
		if (this.entityOwner.isTwinDead()
				|| this.entityOwner.getAttackTarget() != null) {
			return false;
		} else {
			double var1 = this.entityOwner.getDistanceSq(this.entityOwner.getTwin());
			return var1 >= 16.0D/* && var1 <= 256.0D*/;
		}
	}


	@Override
	public void startExecuting() {
		this.field_75345_d = 0;
	}


	@Override
	public void updateTask() {
		if (--this.field_75345_d <= 0) {
			this.field_75345_d = 10;
			this.entityOwner.getNavigator().tryMoveToEntityLiving(this.entityOwner.getTwin(), this.speed);
		}
	}
}
