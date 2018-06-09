package com.gw.dm.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

import com.gw.dm.EntityDungeonFlying;

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
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(32.0D);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
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

		if(this.world.getDifficulty() == EnumDifficulty.EASY)
			bar = 1;
		if(this.world.getDifficulty() == EnumDifficulty.NORMAL)
			bar = 2;
		if(this.world.getDifficulty() == EnumDifficulty.HARD)
			bar = 3;

		int num = this.rand.nextInt(3) + bar;

		PotionEffect[] theEffect = new PotionEffect[num];

		for(int i = 0; i < num; i++)
		{
			int eff = this.rand.nextInt(11);

			if(eff == 0)
				theEffect[i] = new PotionEffect(Potion
						.getPotionFromResourceLocation("slowness"), 120, 2);
			else if(eff == 1)
				theEffect[i] = new PotionEffect(Potion
						.getPotionFromResourceLocation("mining_fatigue"), 160, 2);
			else if(eff == 2)
				theEffect[i] = new PotionEffect(Potion
						.getPotionFromResourceLocation("instant_damage"), 1, 1);
			else if(eff == 3)
				theEffect[i] = new PotionEffect(Potion // Was confusion; change back or leave?
						.getPotionFromResourceLocation("nausea"), 280, 1);
			else if(eff == 4)
				theEffect[i] = new PotionEffect(Potion
						.getPotionFromResourceLocation("resustance"), 100, -2);
			else if(eff == 5)
				theEffect[i] = new PotionEffect(Potion
						.getPotionFromResourceLocation("blindness"), 120, 1);
			else if(eff == 6)
				theEffect[i] = new PotionEffect(Potion
						.getPotionFromResourceLocation("hunger"), 400, 3);
			else if(eff == 7)
				theEffect[i] = new PotionEffect(Potion
						.getPotionFromResourceLocation("weakness"), 120, 1);
			else if(eff == 8)
				theEffect[i] = new PotionEffect(Potion
						.getPotionFromResourceLocation("poison"), 200, 1);
			else if(eff == 9)
				theEffect[i] = new PotionEffect(Potion
						.getPotionFromResourceLocation("wither"), 100, 1);
			else
				theEffect[i] = new PotionEffect(Potion
						.getPotionFromResourceLocation("levitate"), 100, 1);
						//new PotionEffectAddled(DungeonMobs.potionAddleID, 180, 0);
						// Change back?

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

	// TODO: Sound
	/*
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
	*/

	public boolean getCanSpawnHere() {
		if(this.world.canSeeSky(new BlockPos((int)this.posX, (int)this.posY, (int)this.posZ)))
			return false;

		if(this.posY > 32.0D && !ignoreHeight)
			return false;

		return super.getCanSpawnHere();
	}

	
	@Override
	public void onUpdate() {		
		if (!this.world.isRemote && this.world.getDifficulty() == EnumDifficulty.PEACEFUL)
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
			var7 = (double)MathHelper.sqrt(var7);

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
			this.targetedEntity = this.world.getClosestPlayerToEntity(this, 24.0D);

		double var9 = 24.0D;

		if (this.targetedEntity != null && this.targetedEntity.getDistanceSq(this) < var9 * var9)
		{
			this.faceEntity(this.targetedEntity, 10.0F, (float)this.getVerticalFaceSpeed());

			double var11 = targetedEntity.posX + this.targetedEntity.motionX - this.posX;
			double var13 = targetedEntity.getCollisionBox(targetedEntity).minY 
					+ this.targetedEntity.motionY  
					+ (double)(this.targetedEntity.height / 4.0F) - this.posY;
			double var15 = targetedEntity.posZ + this.targetedEntity.motionZ  - this.posZ;
			this.renderYawOffset = this.rotationYaw = 
					-((float)Math.atan2(var11, var15)) * 180.0F / (float)Math.PI;

			if (this.canEntityBeSeen(this.targetedEntity))
			{
				this.attackCounter++;

				int bar = 0;

				if(world.getDifficulty() == EnumDifficulty.EASY)
					bar = 1;
				if(world.getDifficulty() == EnumDifficulty.NORMAL)
					bar = 2;
				if(world.getDifficulty() == EnumDifficulty.HARD)
					bar = 3;

				if (this.attackCounter >= (15 - (bar * 2)))
				{
					PotionEffect[] foo = this.defineEyeRay();
					//EntityEyeRay ray = new EntityEyeRay(this.worldObj, this, var11, var13, var15);
					//ray.setEffects(foo);
					//world.spawnEntityInWorld(ray);
					attackCounter = (-55 + (bar * 5));
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
		AxisAlignedBB var15 = getCollisionBox(this);

		for (int var16 = 1; (double)var16 < par7; ++var16)
		{
			var15.offset(var9, var11, var13);

			if (!world.getCollisionBoxes(this, var15).isEmpty())
				return false;
		}

		return true;
	}

	
	protected void dropFewItems(boolean par1, int par2)	{
		int var3;
		int var4;
		int var5;

		var3 = this.rand.nextInt(3);

		for (var4 = 0; var4 < var3; var4++)
		{
			this.dropItem(Items.SPIDER_EYE, 1);
		}
	}

	
	public boolean attackEntityFrom(DamageSource par1DamageSource, int par2) {
		if(par1DamageSource.getTrueSource() != null && (par1DamageSource.getTrueSource() 
				instanceof EntityLiving /*&& !(par1DamageSource.getTrueSource() instanceof EntityEyeRay))*/))
		{
			if(par1DamageSource.getImmediateSource() instanceof EntityThrowable)
			{
				EntityThrowable foo = (EntityThrowable)par1DamageSource.getImmediateSource();
				this.targetedEntity = (Entity)foo.getThrower();
			}
			else
				this.targetedEntity = par1DamageSource.getTrueSource();
		}

		//if(par1DamageSource.getImmediateSource() != null 
		//		&& par1DamageSource.getImmediateSource() instanceof EntityEyeRay)
		//	return false;

		return super.attackEntityFrom(par1DamageSource, par2);
	}
}
