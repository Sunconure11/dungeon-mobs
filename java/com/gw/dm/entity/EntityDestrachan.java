package com.gw.dm.entity;

import com.gw.dm.EntityDungeonMob;
import com.gw.dm.projectile.EntitySonicBoom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class EntityDestrachan extends EntityDungeonMob implements IRangedAttackMob
{
	public EntityAIArrowAttack rangedAttack = new EntityAIArrowAttack(this, 0.4F, 10, 70, 20.0F);
    public EntityAIAttackOnCollide meleeAttack = new EntityAIAttackOnCollide(this, EntityPlayer.class, 0.4F, false);
    
    private int resetAttackTimer;
    
    public float attackDistance;
    public float visionDistance;
    public boolean isRanged;
    public boolean ignoreHeight;
	
	public EntityDestrachan(World par1World) 
	{
		super(par1World);
		this.experienceValue = 35;
		this.resetAttackTimer = 0;
		this.setSize(1.9F, 1.7F);
		this.ignoreHeight = false;
		
		//this.attackDistance = 8 + (this.worldObj.difficultySetting * 8);
		//this.visionDistance = 4 + (this.worldObj.difficultySetting * 4);
		
		this.isRanged = true;
		
		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, this.rangedAttack);
        this.tasks.addTask(3, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 12.0F));
        this.tasks.addTask(4, new EntityAILookIdle(this));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true));
	}
	
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(24.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.4D);
        this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setBaseValue(4.0D);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(0.0D);
    }

	protected boolean isAIEnabled()
	{
		return true;
	}
	
	public int getTotalArmorValue()
	{
		return 6;
	}
	
	public int getAttackStrength(Entity par1Entity)
	{
		return 4;
	}
	
	public int getTalkInterval()
    {
        return 100;
    }
	
	protected String getLivingSound()
	{
		return "dungeonmobs:d_l";
	}
	
	protected String getHurtSound()
	{
		return "dungeonmobs:d_h";
	}
	
	protected String getDeathSound()
	{
		return "dungeonmobs:d_d";
	}
	
	protected void playStepSound(int par1, int par2, int par3, int par4)
    {
        this.playSound("dungeonmobs:bt_s", 0.15F, 1.0F);
    }
	
	public boolean getCanSpawnHere()
	{
		if(this.worldObj.canBlockSeeTheSky((int)this.posX, (int)this.posY, (int)this.posZ))
			return false;
		
		if(this.posY > 36.0D && !this.ignoreHeight)
			return false;
		
		return super.getCanSpawnHere();
	}
	
	protected void dropFewItems(boolean par1, int par2)
	{
		int var3;
		int var4;
		
		var3 = this.rand.nextInt(2);

        for (var4 = 0; var4 < var3; var4++)
        {
        	switch(this.rand.nextInt(2))
        	{
	        	case(0):
	        	{
	        		this.dropItem(Items.record_13, 1);
	        		break;
	        	}
	        	case(1):
	        	{
	        		this.dropItem(Items.record_wait, 1);
	        		break;
	        	}
	        	default:
	        	{
	        		break;
	        	}
        	}
        	
        	/*
        	this.dropItem(Items.record13
        	
            this.dropItem(Item.record13.itemID + this.rand.nextInt(Item.recordWait.itemID - Item.record13.itemID + 1), 1);
            */
        }
	}

	public boolean isPotionApplicable(PotionEffect par1PotionEffect)
    {
        return par1PotionEffect.getPotionID() == Potion.blindness.id ? false : super.isPotionApplicable(par1PotionEffect);
    }
	
	public void resetAttackType()
    {
		this.tasks.removeTask(this.rangedAttack);
        this.tasks.removeTask(this.meleeAttack);

        if(this.getAITarget() == null)
        {
        	this.tasks.addTask(2, this.rangedAttack);
        	this.isRanged = true;
        }
        else
        {
        	double chkX = this.getAITarget().posX - this.posX;
        	double chkY = this.getAITarget().posY - this.posY;
        	double chkZ = this.getAITarget().posZ - this.posZ;
        	
        	if(((chkX * chkX) + (chkY * chkY) + (chkZ * chkZ)) < 12.0D)
        	{
        		this.tasks.addTask(2, this.meleeAttack);
        		this.isRanged = false;
        	}
        	else
        	{
        		this.tasks.addTask(2, this.rangedAttack);
        		this.isRanged = true;
        	}
        }
    }
	
	public void onLivingUpdate()
	{
		/*
		this.resetAttackTimer++;
		
		if(resetAttackTimer >= 65)
		{
			this.resetAttackType();
			this.resetAttackTimer = 0;
		}
		*/
		
		super.onLivingUpdate();
	}
	
	/*
	public boolean canEntityBeSeen(Entity ent)
	{
		if(super.canEntityBeSeen(ent))
		{
			if(ent.prevRotationPitch != ent.rotationPitch)
				return true;
			else if(ent.prevRotationYaw != ent.rotationYaw)
				return true;
			else if(ent.prevPosX != ent.posX)
				return true;
			else if(ent.prevPosY != ent.posY)
				return true;
			else if(ent.prevPosZ != ent.posZ)
				return true;
			else
				return false;
		}
			
		return super.canEntityBeSeen(ent);
	}
	*/
	
	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase var1, float lol) 
	{
		double var11 = var1.posX + var1.motionX - this.posX;
        double var13 = var1.boundingBox.minY + var1.motionY + var1.height - this.boundingBox.maxY;
        double var15 = var1.posZ + var1.motionZ  - this.posZ;
		
		EntitySonicBoom var2 = new EntitySonicBoom(this.worldObj, (EntityLiving)this, var11, var13, var15);
		this.worldObj.spawnEntityInWorld(var2);
		this.worldObj.playSoundAtEntity(var2, "dungeonmobs:d_s", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
	}
	
	public boolean canAttackClass(Class par1Class)
    {
        return EntityDestrachan.class != par1Class;
    }
	
	public boolean attackEntityFrom(DamageSource par1DamageSource, int par2)
    {
		// this function asks the damage source if it is an explosion.
		if(par1DamageSource.isExplosion())
			return false;

		return super.attackEntityFrom(par1DamageSource, par2);
    }
}
