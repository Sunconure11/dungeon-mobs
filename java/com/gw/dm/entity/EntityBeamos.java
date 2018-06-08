package com.gw.dm.entity;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

import com.gw.dm.EntityDungeonMob;
import com.gw.dm.projectile.EntityMagicMissile;

public class EntityBeamos extends EntityDungeonMob implements IRangedAttackMob
{
	public int attackTimer;

	private EntityAIArrowAttack rangeAttack = new EntityAIArrowAttack(this, 0F, 5, 40, 24.0F);
	
	public EntityBeamos(World p_i1595_1_) 
	{
		super(p_i1595_1_);
		this.attackTimer = 0;
		this.setSize(0.98F, 0.98F);
		
		this.tasks.addTask(0, rangeAttack);
		this.tasks.addTask(1, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(2, new EntityAILookIdle(this));
        
        this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
	}

	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(60.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.0D);
        //this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(0.0D);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0D);
    }
	
	protected boolean canTriggerWalking()
    {
        return false;
    }
    
    public boolean canBeCollidedWith()
    {
        return false;
    }
    
    @Override
	public void jump()
	{
	}    
	
	@Override
	protected void fall(float par1)
    {
        // don't fall, mothafucka!
    }
	
	public boolean canRenderOnFire()
    {
        return false;
    }
	
	protected boolean isAIEnabled()
	{
		return true;
	}
	
	public boolean attackEntityFrom(DamageSource par1DamageSource, int par2)
    {
		if(par1DamageSource.isExplosion())
		{
			System.out.println("OH SHIT I'M DYING");
			this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, 2.0F, true);
			this.setDead();
			return false;
		}
		else
			return false;
    }
	
	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase var1, float lol) 
	{
		double var11 = var1.posX + var1.motionX - this.posX;
        double var13 = var1.boundingBox.minY + var1.motionY + var1.height - this.boundingBox.maxY;
        double var15 = var1.posZ + var1.motionZ  - this.posZ;
		
        EntityMagicMissile var2 = new EntityMagicMissile(this.worldObj, (EntityLiving)this, var11, var13, var15, var1);
        
        //EntityBeamosBeam var2 = new EntityBeamosBeam(this.worldObj, (EntityLiving)this, var11, var13, var15, var1);
        //EntityBeamosBeam var2 = new EntityBeamosBeam(this.worldObj, (EntityLiving)this, var11, var13, var15, var1);
		//EntityBeamosBeam var2 = new EntityBeamosBeam(this.worldObj, (EntityLiving)this, var11, var13, var15, var1);
		this.worldObj.spawnEntityInWorld(var2);
	}
}
