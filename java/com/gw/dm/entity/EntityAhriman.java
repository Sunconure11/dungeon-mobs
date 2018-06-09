package com.gw.dm.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

import com.gw.dm.EntityDungeonFlying;
import com.gw.dm.ai.AIBeholderAttack;
import com.gw.dm.ai.AIBeholderWander;
import com.gw.dm.ai.TaskBeholderAgro;
import com.gw.dm.projectile.EntityEyeRay;
import com.gw.dm.util.AudioHandler;

/*
 * FIXME: This mob is hopelessly broken; has no behavior and takes no damage!
 */
public class EntityAhriman extends EntityDungeonFlying implements IMob, IRangedAttackMob {
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
		experienceValue = 40;
		ignoreHeight = false;
		setSize(1.2F, 1.2F);

		//FIXME: Do I need to do anything with this?
		/*
		this.tasks.addTask(0, new EntityAIArrowAttack(this, 0.5, 60, 16.0F));
		this.tasks.addTask(1, new EntityAIFlyCasual(this, 0.5));
		this.tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(2, new EntityAILookIdle(this));

		this.targetTasks.addTask(0, new EntityAIHurtByTarget(this, false));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this, EntityPlayer.class, 16.0F, 0, true));
		*/
	}

	protected void applyEntityAttributes(){
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(32.0d);
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5d);
		getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(10.d);
        getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(0.0d);
	}

	
	@Override
	protected void initEntityAI()   {
		tasks.addTask(2, new AIBeholderAttack(this));
		tasks.addTask(4, new AIBeholderWander());
		tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		tasks.addTask(6, new EntityAILookIdle(this));
		targetTasks.addTask(2, new TaskBeholderAgro());
		targetTasks.addTask(3, new EntityAIFindEntityNearestPlayer(this));
	}


	@Override
	protected void entityInit() {
		super.entityInit();
	}
	
	
	public PotionEffect[] defineEyeRay() {
		if(this.getAttackTarget() instanceof EntityPlayer) {
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

		int num = rand.nextInt(3) + bar;

		PotionEffect[] theEffect = new PotionEffect[num];

		for(int i = 0; i < num; i++) {
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
				theEffect[i] = new PotionEffect(Potion
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

			if(i > 0) {
				if(theEffect[i - 1].equals(theEffect[i]))
					i--;
			}
		}

		return theEffect;
	}
	
	
	@Override
	public void onUpdate() {
		if(!world.isRemote && 
				(world.getDifficulty() == EnumDifficulty.PEACEFUL)) {
			setDead();
		}
		super.onUpdate();
	}
	

	public int getTalkInterval(){
		return 300;
	}
	

	public boolean getCanSpawnHere() {
		if(this.world.canSeeSky(new BlockPos((int)this.posX, (int)this.posY, (int)this.posZ)))
			return false;

		if(this.posY > 32.0D && !ignoreHeight)
			return false;

		return super.getCanSpawnHere();
	}
	
	
	// TODO: New loot dropping system
	protected void dropFewItems(boolean par1, int par2)	{
		int var3;
		int var4;
		int var5;
		var3 = this.rand.nextInt(3);
		for (var4 = 0; var4 < var3; var4++){
			this.dropItem(Items.SPIDER_EYE, 1);
		}
	}

	
	// FIXME: Currently, it takes *NO* damage!
	public boolean attackEntityFrom(DamageSource par1DamageSource, int par2) {
		if(par1DamageSource.getTrueSource() != null && (par1DamageSource.getTrueSource() 
				instanceof EntityLiving && !(par1DamageSource.getTrueSource() instanceof EntityEyeRay)))
		{
			if(par1DamageSource.getImmediateSource() instanceof EntityThrowable)
			{
				EntityThrowable foo = (EntityThrowable)par1DamageSource.getImmediateSource();
				this.targetedEntity = (Entity)foo.getThrower();
			}
			else
				this.targetedEntity = par1DamageSource.getTrueSource();
		}

		if(par1DamageSource.getImmediateSource() != null 
				&& par1DamageSource.getImmediateSource() instanceof EntityEyeRay)
			return false;

		return super.attackEntityFrom(par1DamageSource, par2);
	}

	
	@Override
	protected SoundEvent getAmbientSound(){
		return AudioHandler.entityAhrimanAmbient;
	}

	
	@Override
	protected SoundEvent getHurtSound(DamageSource ds) {
		return null;
	}

	
	@Override
	protected SoundEvent getDeathSound() {
		return null;
	}

	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase target,
			float distanceFactor) {
		// TODO Fire an eye ray!
	}

	@Override
	public void setSwingingArms(boolean swingingArms) {}
}
