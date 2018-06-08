package com.gw.dm.entity;

import com.gw.dm.DungeonMobs;
import com.gw.dm.EntityDungeonFlying;
import com.gw.dm.potion.PotionEffectAddled;
import com.gw.dm.projectile.EntityEyeRay;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityAhriman extends EntityDungeonFlying implements IMob
{
	public int courseChangeCooldown = 0;
    public double waypointX;
    public double waypointY;
    public double waypointZ;
    private Entity targetedEntity = null;
    
    private int aggroCooldown = 0;
    public int prevAttackCounter = 0;
    public int attackCounter = 0;
    
    public boolean ignoreHeight;
	
	public EntityAhriman(World par1World) 
	{
		super(par1World);
		//this.moveSpeed = 0.5F;
		this.experienceValue = 40;
		ignoreHeight = false;
		this.setSize(1.2F, 1.2F);
		
		/*
		this.tasks.addTask(0, new EntityAIArrowAttack(this, this.moveSpeed, 60, 16.0F));
		this.tasks.addTask(1, new EntityAIFlyCasual(this, this.moveSpeed));
		this.tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(2, new EntityAILookIdle(this));
		
		this.targetTasks.addTask(0, new EntityAIHurtByTarget(this, false));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 16.0F, 0, true));
		*/
	}
	
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(32.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.5D);
    }
	
	protected boolean isAIEnabled()
	{
		return false;
	}
	
	public int getTotalArmorValue()
	{
		return 10;
	}
	
	public int getAttackStrength(Entity par1Entity)
	{
		return 0;
	}

	public PotionEffect[] defineEyeRay() 
	{
		if(this.getAttackTarget() instanceof EntityPlayer)
		{
			EntityPlayer foo = (EntityPlayer)this.getAttackTarget();
			if(foo.capabilities.isCreativeMode)
				return new PotionEffect[0];
		}
		
		int bar = 0;
		
		if(this.worldObj.difficultySetting == EnumDifficulty.EASY)
			bar = 1;
		if(this.worldObj.difficultySetting == EnumDifficulty.NORMAL)
			bar = 2;
		if(this.worldObj.difficultySetting == EnumDifficulty.HARD)
			bar = 3;
			
		int num = this.rand.nextInt(3) + bar;
		
		PotionEffect[] theEffect = new PotionEffect[num];
		
		for(int i = 0; i < num; i++)
		{
			int eff = this.rand.nextInt(11);
		
			if(eff == 0)
				theEffect[i] = new PotionEffect(Potion.moveSlowdown.id, 120, 2);
			else if(eff == 1)
				theEffect[i] = new PotionEffect(Potion.digSlowdown.id, 160, 2);
			else if(eff == 2)
				theEffect[i] = new PotionEffect(Potion.harm.id, 1, 1);
			else if(eff == 3)
				theEffect[i] = new PotionEffect(Potion.confusion.id, 280, 1);
			else if(eff == 4)
				theEffect[i] = new PotionEffect(Potion.resistance.id, 100, -2);
			else if(eff == 5)
				theEffect[i] = new PotionEffect(Potion.blindness.id, 120, 1);
			else if(eff == 6)
				theEffect[i] = new PotionEffect(Potion.hunger.id, 400, 3);
			else if(eff == 7)
				theEffect[i] = new PotionEffect(Potion.weakness.id, 120, 1);
			else if(eff == 8)
				theEffect[i] = new PotionEffect(Potion.poison.id, 200, 1);
			else if(eff == 9)
				theEffect[i] = new PotionEffect(Potion.wither.id, 100, 1);
			else
				theEffect[i] = new PotionEffectAddled(DungeonMobs.potionAddleID, 180, 0);
			
			if(i > 0)
			{
				if(theEffect[i - 1].equals(theEffect[i]))
					i--;
			}
		}
		
		return theEffect;
	}
	
	public int getTalkInterval()
    {
        return 300;
    }
	
	protected String getLivingSound()
	{
		return "dungeonmobs:a_l";
	}
	
	protected String getHurtSound()
	{
		return null;
	}
	
	protected String getDeathSound()
	{
		return null;
	}
	
	public boolean getCanSpawnHere()
	{
		if(this.worldObj.canBlockSeeTheSky((int)this.posX, (int)this.posY, (int)this.posZ))
			return false;
		
		if(this.posY > 32.0D && !ignoreHeight)
			return false;
		
		return super.getCanSpawnHere();
	}
	
	protected void updateEntityActionState()
    {
		if (!this.worldObj.isRemote && this.worldObj.difficultySetting == EnumDifficulty.PEACEFUL)
            this.setDead();

        this.despawnEntity();
        this.prevAttackCounter = this.attackCounter;
        double var1 = this.waypointX - this.posX;
        double var3 = this.waypointY - this.posY;
        double var5 = this.waypointZ - this.posZ;
        double var7 = var1 * var1 + var3 * var3 + var5 * var5;

        if (var7 < 1.0D || var7 > 3600.0D)
        {
            this.waypointX = this.posX + (double)((this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F);
            this.waypointY = this.posY + (double)((this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F);
            this.waypointZ = this.posZ + (double)((this.rand.nextFloat() * 2.0F - 1.0F) * 16.0F);
            
            if (this.targetedEntity != null && !this.targetedEntity.isDead)
            {
            	double fooX = this.rand.nextFloat() * 10.0F;
            	double fooY = this.rand.nextFloat() * 10.0F;
            	double fooZ = this.rand.nextFloat() * 10.0F;
            	
            	this.waypointX = this.targetedEntity.posX + (fooX * (this.rand.nextInt(5) - 2));
            	this.waypointY = this.targetedEntity.posY + (fooY * (this.rand.nextInt(5) - 2));
            	this.waypointZ = this.targetedEntity.posZ + (fooZ * (this.rand.nextInt(5) - 2));
            }
        }

        if (this.courseChangeCooldown-- <= 0)
        {
            this.courseChangeCooldown += this.rand.nextInt(5) + 2;
            var7 = (double)MathHelper.sqrt_double(var7);

            if (this.isCourseTraversable(this.waypointX, this.waypointY, this.waypointZ, var7))
            {
                this.motionX += var1 / var7 * 0.1D;
                this.motionY += var3 / var7 * 0.1D;
                this.motionZ += var5 / var7 * 0.1D;
            }
            else
            {
                this.waypointX = this.posX;
                this.waypointY = this.posY;
                this.waypointZ = this.posZ;
            }
        }

        if (this.targetedEntity != null && this.targetedEntity.isDead)
            this.targetedEntity = null;

        if (this.targetedEntity == null)
            this.targetedEntity = this.worldObj.getClosestVulnerablePlayerToEntity(this, 24.0D);

        double var9 = 24.0D;

        if (this.targetedEntity != null && this.targetedEntity.getDistanceSqToEntity(this) < var9 * var9)
        {
        	this.faceEntity(this.targetedEntity, 10.0F, (float)this.getVerticalFaceSpeed());
        	
        	double var11 = this.targetedEntity.posX + this.targetedEntity.motionX - this.posX;
            double var13 = this.targetedEntity.boundingBox.minY + this.targetedEntity.motionY  + (double)(this.targetedEntity.height / 4.0F) - this.posY;
            double var15 = this.targetedEntity.posZ + this.targetedEntity.motionZ  - this.posZ;
            this.renderYawOffset = this.rotationYaw = -((float)Math.atan2(var11, var15)) * 180.0F / (float)Math.PI;

            if (this.canEntityBeSeen(this.targetedEntity))
            {
                this.attackCounter++;
                
                int bar = 0;
        		
        		if(this.worldObj.difficultySetting == EnumDifficulty.EASY)
        			bar = 1;
        		if(this.worldObj.difficultySetting == EnumDifficulty.NORMAL)
        			bar = 2;
        		if(this.worldObj.difficultySetting == EnumDifficulty.HARD)
        			bar = 3;

                if (this.attackCounter >= (15 - (bar * 2)))
                {
                	PotionEffect[] foo = this.defineEyeRay();
                	EntityEyeRay ray = new EntityEyeRay(this.worldObj, this, var11, var13, var15);
                	ray.setEffects(foo);
                	//double var18 = 4.0D;
                    //Vec3 var20 = this.getLook(1.0F);
                    //ray.setLocationAndAngles(this.posX + var20.xCoord * var18, this.posY + (double)(this.height / 2.0F) + 0.5D, this.posZ + var20.zCoord * var18, MathHelper.wrapAngleTo180_float(this.worldObj.rand.nextFloat() * 360.0F), 0.0F);
                    this.worldObj.spawnEntityInWorld(ray);
                    this.attackCounter = (-55 + (bar * 5));
                }
            }
            else if (this.attackCounter > 0)
            {
                --this.attackCounter;
            }
        }
        else
        {
            this.renderYawOffset = this.rotationYaw = -((float)Math.atan2(this.motionX, this.motionZ)) * 180.0F / (float)Math.PI;

            if (this.attackCounter > 0)
                --this.attackCounter;
        }
    }
	
	private boolean isCourseTraversable(double par1, double par3, double par5, double par7)
    {
        double var9 = (this.waypointX - this.posX) / par7;
        double var11 = (this.waypointY - this.posY) / par7;
        double var13 = (this.waypointZ - this.posZ) / par7;
        AxisAlignedBB var15 = this.boundingBox.copy();

        for (int var16 = 1; (double)var16 < par7; ++var16)
        {
            var15.offset(var9, var11, var13);

            if (!this.worldObj.getCollidingBoundingBoxes(this, var15).isEmpty())
                return false;
        }

        return true;
    }
	
	protected void dropFewItems(boolean par1, int par2)
	{
		int var3;
		int var4;
		int var5;
		
		var3 = this.rand.nextInt(3);

        for (var4 = 0; var4 < var3; var4++)
        {
            this.dropItem(Items.spider_eye, 1);
        }
	}
	
	public boolean attackEntityFrom(DamageSource par1DamageSource, int par2)
    {
		if(par1DamageSource.getEntity() != null && (par1DamageSource.getEntity() instanceof EntityLiving && !(par1DamageSource.getEntity() instanceof EntityEyeRay)))
		{
			if(par1DamageSource.getEntity() instanceof EntityThrowable)
			{
				EntityThrowable foo = (EntityThrowable)par1DamageSource.getEntity();
				this.targetedEntity = (Entity)foo.getThrower();
			}
			else
				this.targetedEntity = par1DamageSource.getEntity();
		}
		
		if(par1DamageSource.getEntity() != null && par1DamageSource.getEntity() instanceof EntityEyeRay)
			return false;

		return super.attackEntityFrom(par1DamageSource, par2);
    }
}
