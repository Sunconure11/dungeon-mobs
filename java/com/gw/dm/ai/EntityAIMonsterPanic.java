package com.gw.dm.ai;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.util.Vec3;

public class EntityAIMonsterPanic extends EntityAIBase
{
    private EntityMob theEntityCreature;
    private float speed;
    private double randPosX;
    private double randPosY;
    private double randPosZ;

    public EntityAIMonsterPanic(EntityMob par1EntityCreature, float par2)
    {
        this.theEntityCreature = par1EntityCreature;
        this.speed = par2;
        this.setMutexBits(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        if (this.theEntityCreature.getAITarget() == null && !this.theEntityCreature.isBurning())
        {
            return false;
        }
        else
        {
            Vec3 var1 = RandomPositionGenerator.findRandomTarget(this.theEntityCreature, 5, 4);

            if (var1 == null)
            {
                return false;
            }
            else
            {
                this.randPosX = var1.xCoord;
                this.randPosY = var1.yCoord;
                this.randPosZ = var1.zCoord;
                return true;
            }
        }
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting()
    {
        this.theEntityCreature.getNavigator().tryMoveToXYZ(this.randPosX, this.randPosY, this.randPosZ, this.speed);
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting()
    {
        return !this.theEntityCreature.getNavigator().noPath();
    }
}
