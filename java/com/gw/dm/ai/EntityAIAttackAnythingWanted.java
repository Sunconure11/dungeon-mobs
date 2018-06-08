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

import com.gw.dm.entity.EntityRustMonster;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class EntityAIAttackAnythingWanted extends EntityAIAttackOnCollide 
{
	World worldObj;
    EntityCreature attacker;
    Entity entityTarget;
    
    int attackTick;
    float field_75440_e;
    boolean field_75437_f;

    PathEntity entityPathEntity;
    Class classTarget;
    private int field_75445_i;
	
	public EntityAIAttackAnythingWanted(EntityCreature par1EntityLiving, Class par2Class, float par3, boolean par4)
    {
        super(par1EntityLiving, par3, par4);
        this.classTarget = par2Class;
        this.attackTick = 0;
        this.attacker = par1EntityLiving;
        this.worldObj = par1EntityLiving.worldObj;
        this.field_75440_e = par3;
        this.field_75437_f = par4;
        this.setMutexBits(3);
    }
	
	@Override
	public boolean shouldExecute()
    {
		//EntityLivingBase var1 = (EntityRustMonster)this.attacker;
		
		EntityRustMonster foo = (EntityRustMonster)this.attacker;
		Entity var1 = foo.getTarget();
		
		//Entity var1 = this.attacker.getAITarget();
		
		//this.attacker.
		
		//EntityRustMonster foo = (EntityRustMonster)this.attacker;
		//Entity var1 = foo.getTarget();
		
		if (var1 == null)
            return false;
        //else if (this.classTarget != null && !this.classTarget.isAssignableFrom(var1.getClass()))
        //    return false;
        else
        {
            this.entityTarget = var1;
            this.entityPathEntity = this.attacker.getNavigator().getPathToXYZ(var1.posX, var1.boundingBox.minY, var1.posZ);
            return this.entityPathEntity != null;
        }
    }
	
	@Override
	public boolean continueExecuting()
    {
		EntityRustMonster foo = (EntityRustMonster)this.attacker;
        Entity var1 = foo.getTarget();
        
        //EntityLivingBase var1 = this.attacker.getAttackTarget();
        return var1 != null;
        //return var1 == null ? false : (!this.entityTarget.isEntityAlive() ? false : (!this.field_75437_f ? !this.attacker.getNavigator().noPath() : this.attacker.isWithinHomeDistance(MathHelper.floor_double(this.entityTarget.posX), MathHelper.floor_double(this.entityTarget.posY), MathHelper.floor_double(this.entityTarget.posZ))));
    }
	
	@Override
	public void updateTask()
    {
        this.attacker.getLookHelper().setLookPositionWithEntity(this.entityTarget, 30.0F, 30.0F);

        if ((this.field_75437_f || this.attacker.getEntitySenses().canSee(this.entityTarget)) && --this.field_75445_i <= 0)
        {
            this.field_75445_i = 4 + this.attacker.getRNG().nextInt(7);
            this.attacker.getNavigator().tryMoveToXYZ(this.entityTarget.posX, this.entityTarget.boundingBox.minY, this.entityTarget.posZ, this.field_75440_e);
        }

        this.attackTick = Math.max(this.attackTick - 1, 0);
        double var1 = (double)(this.attacker.width * 2.0F * this.attacker.width * 2.0F);

        if (this.attacker.getDistanceSq(this.entityTarget.posX, this.entityTarget.boundingBox.minY, this.entityTarget.posZ) <= var1)
        {
            if (this.attackTick <= 0)
            {
                this.attackTick = 20;

                if (this.attacker.getHeldItem() != null)
                    this.attacker.swingItem();

                this.attacker.attackEntityAsMob(this.entityTarget);
            }
        }
    }
}
