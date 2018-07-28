package com.gw.dm.ai;

import com.gw.dm.entity.EntityFallenAngel;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;

import java.util.Random;

public class AIAngelWander extends EntityAIBase {
	private final EntityFallenAngel owner;
	private final double rate;
	private double targetx, targety, targetz, dtx, dty, dtz;
	private boolean wandering;


	public AIAngelWander(EntityFallenAngel it, double speed) {
		super();
		owner = it;
		rate = speed;
		Random random = owner.getRNG();
		dtx = (random.nextDouble() * 8.0) - 4.0;
		dty = (random.nextDouble() * 4.0);
		dtz = (random.nextDouble() * 8.0) - 4.0;
		targetx = owner.posX + dtx;
		targety = owner.posY + dty;
		targetz = owner.posZ + dtz;
	}


	@Override
	public boolean shouldExecute() {
		EntityLivingBase enemy = owner.getAttackTarget();
		return ((enemy == null) || enemy.isDead) && needNewTarget();
	}


	public boolean needNewTarget() {
		EntityMoveHelper helper = owner.getMoveHelper();
		if (helper.isUpdating()) {
			return true;
		} else {
			double dx = helper.getX() - owner.posX;
			double dy = helper.getY() - owner.posY;
			double dz = helper.getZ() - owner.posZ;
			double dist = ((dx * dx) + (dy * dy) + (dz * dz));
			return (dist < 1.0) || (dist > 432.0) || ((AngelicMoveHelper) helper).blocked;
		}
	}


	@Override
	public boolean shouldContinueExecuting() {
		return false;
	}


	@Override
	public void startExecuting() {
		//System.out.println("Starting execution");
		Random random = owner.getRNG();
		if (((AngelicMoveHelper) owner.getMoveHelper()).blocked) {
			dtx = (random.nextDouble() * 8.0) - 4.0;
			dty = (random.nextDouble() * 8.0) - 4.0;
			dtz = (random.nextDouble() * 8.0) - 4.0;
		} else if (random.nextBoolean()) {
			double change = random.nextDouble();
			double stable = change - 1;
			dtx = (dtx * stable) + (change * ((random.nextDouble() * 8.0) - 4.0));
			dty = (dty * stable) + (change * ((random.nextDouble() * 8.0) - 4.0));
			dtz = (dtz * stable) + (change * ((random.nextDouble() * 8.0) - 4.0));
		}
		targetx = owner.posX + dtx;
		targety = owner.posY + dty;
		targetz = owner.posZ + dtz;
		owner.getMoveHelper().setMoveTo(targetx, targety, targetz, rate);
	}


	private boolean atTarget() {
		EntityMoveHelper helper = owner.getMoveHelper();
		double dx = targetx - owner.posX;
		double dy = targety - owner.posY;
		double dz = targetz - owner.posZ;
		return (((dx * dx) + (dy * dy) + (dz * dz)) < 1.0);
	}


	void setTarget(double toX, double toY, double toZ) {
		targetx = owner.posX + toX;
		targety = owner.posY + toY;
		targetz = owner.posZ + toZ;
	}


	void callMoveHelper() {
		owner.getMoveHelper().setMoveTo(targetx, targety, targetz, rate);
	}

	
	/*---------------------------------------------------*
	 *                    MOVE HELPER                    *
	 *---------------------------------------------------*/


	public static class AngelicMoveHelper extends EntityMoveHelper {
		EntityFallenAngel owner;
		boolean blocked;

		public AngelicMoveHelper(EntityFallenAngel angel) {
			super(angel);
			owner = angel;
		}

		@Override
		public void onUpdateMoveHelper() {
			//System.out.println("Starting onUpdateMoveHelper, action = " + action);
			//action = EntityMoveHelper.Action.MOVE_TO;
			if (action == EntityMoveHelper.Action.MOVE_TO) {
				blocked = false;
				double dx = posX - owner.posX;
				double dy = posY - owner.posY;
				double dz = posZ - owner.posZ;
				double dist = ((dx * dx) + (dy * dy) + (dz * dz));
				if (isColliding(posX, posY, posZ, dist)) {
					//System.out.println("Colliding");
					blocked = true;
				} else {
					//System.out.println("Moving");
					dist = Math.sqrt(dist);
					double diffEffect;
					switch (owner.world.getDifficulty()) {
						case EASY:
							diffEffect = 0.1;
							break;
						case HARD:
							diffEffect = 0.15;
							break;
						case NORMAL:
						default:
							diffEffect = 0.125;
					}
					double ra = (speed / dist) * diffEffect;
					dx = dx * ra;
					dy = dy * ra;
					dz = dz * ra;
					owner.motionX += dx;
					owner.motionY += dy;
					owner.motionZ += dz;
					if (owner.getAttackTarget() != null) {
						dx = posX - owner.getAttackTarget().posX;
						dz = posZ - owner.getAttackTarget().posZ;
						dist = Math.sqrt((dx * dx) + (dz * dz));
						dx /= dist;
						dz /= dist;
						owner.rotationYaw = (float) (Math.toDegrees(MathHelper.atan2(dz, dx)) + 90f);
					} else if (dist > 1.5) {
						owner.rotationYaw = (float) (Math.toDegrees(MathHelper.atan2(dz, dx)) - 90f);
					}
				}
			} else {
				super.onUpdateMoveHelper();
			}
		}


		public boolean isColliding(double dx, double dy, double dz, double dist) {
			double dx0 = (dx - owner.posX) / dist;
			double dy0 = (dy - owner.posY) / dist;
			double dz0 = (dz - owner.posZ) / dist;
			AxisAlignedBB axisalignedbb = owner.getEntityBoundingBox();
			int i = 1;
			while (i < dist) {
				axisalignedbb = axisalignedbb.offset(dx0, dy0, dz0);
				if (!owner.world.getCollisionBoxes(owner, axisalignedbb).isEmpty()) {
					return true;
				}
				i++;
			}
			return false;
		}

	}


}
