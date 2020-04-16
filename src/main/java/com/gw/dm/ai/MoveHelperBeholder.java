package com.gw.dm.ai;

import com.gw.dm.entity.EntityAhriman;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;

import java.util.Random;

public class MoveHelperBeholder extends EntityMoveHelper {
	EntityAhriman owner;
	boolean blocked;

	public MoveHelperBeholder(EntityLiving entitylivingIn) {
		super(entitylivingIn);
		owner = (EntityAhriman) entitylivingIn;
	}


	@Override
	public void onUpdateMoveHelper() {
		Random rand = owner.getRNG();
		EntityLivingBase traget = owner.getAttackTarget();
		double dx = owner.waypointX - owner.posX;
		double dy = owner.waypointY - owner.posY;
		double dz = owner.waypointZ - owner.posZ;
		double dist2 = ((dx * dx) + (dy * dy) + (dz * dz));

		if (dist2 < 1.0D || dist2 > 3600.0D) {
			owner.waypointX = owner.posX + (double) ((rand.nextFloat()
					* 2.0F - 1.0F) * 16.0F);
			owner.waypointY = owner.posY + (double) ((rand.nextFloat()
					* 2.0F - 1.0F) * 16.0F);
			owner.waypointZ = owner.posZ + (double) ((rand.nextFloat()
					* 2.0F - 1.0F) * 16.0F);

			if (owner.getAttackTarget() != null && !owner.getAttackTarget().isDead) {
				double fooX = rand.nextFloat() * 10.0F;
				double fooY = rand.nextFloat() * 10.0F;
				double fooZ = rand.nextFloat() * 10.0F;

				owner.waypointX = traget.posX + (fooX * (rand.nextInt(5) - 2));
				owner.waypointY = traget.posY + (fooY * (rand.nextInt(5) - 2));
				owner.waypointZ = traget.posZ + (fooZ * (rand.nextInt(5) - 2));
			}
		}

		if (owner.courseChangeCooldown-- <= 0) {
			owner.courseChangeCooldown += rand.nextInt(5) + 2;
			dist2 = (double) MathHelper.sqrt(dist2);

			if (!isColliding(owner.waypointX, owner.waypointY, owner.waypointZ, dist2)) {
				owner.motionX += dx / dist2 * 0.1D;
				owner.motionY += dy / dist2 * 0.1D;
				owner.motionZ += dz / dist2 * 0.1D;
			} else {
				owner.waypointX = owner.posX;
				owner.waypointY = owner.posY;
				owner.waypointZ = owner.posZ;
			}
		}
	}


	/**
	 * Borrowed from Doomlike Dungeons II Fallen Angel and similar to vanilla
	 * ghast code and original Dungeons Mobs "ahriman" code.  This determines
	 * if the way is block (the reverse of being traversable).
	 *
	 * @param dx
	 * @param dy
	 * @param dz
	 * @param dist
	 * @return
	 */
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
