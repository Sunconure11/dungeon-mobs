package com.gw.dm.entity;

import static com.gw.dm.util.ConfigHandler.beholderIg;
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

import com.gw.dm.DungeonMobs;
import com.gw.dm.EntityDungeonFlying;
import com.gw.dm.ai.AIBeholderAttack;
import com.gw.dm.ai.AIBeholderWander;
import com.gw.dm.ai.MoveHelperBeholder;
import com.gw.dm.ai.TaskBeholderAgro;
import com.gw.dm.projectile.EntityEyeRay;
import com.gw.dm.util.AudioHandler;
import com.gw.dm.util.DungeonMobsHelper;


public class EntityAhriman extends EntityDungeonFlying implements IMob, IRangedAttackMob {
	private static final double ADIST2 = 24.0d * 24.0d;
	public static String mobName = DungeonMobs.MODID + ":dmahriman";
	public int courseChangeCooldown = 0;
	public double waypointX;
	public double waypointY;
	public double waypointZ;
	public int prevAttackCounter = 0;
	public int attackCounter = 0;
	public boolean ignoreHeight;
	private MoveHelperBeholder moveHelper;
	private int aggroCooldown = 0;

	public EntityAhriman(World par1World) {
		super(par1World);
		experienceValue = 40;
		ignoreHeight = false;
		setSize(1.2F, 2.0F);
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(24.0d);
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(32.0d);
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5d);
		getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(10.d);
		getAttributeMap().registerAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(0.0d);
	}


	@Override
	protected void initEntityAI() {
		moveHelper = new MoveHelperBeholder(this);
		tasks.addTask(2, new AIBeholderAttack(this));
		tasks.addTask(4, new AIBeholderWander(this));
		tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		tasks.addTask(6, new EntityAILookIdle(this));
		targetTasks.addTask(2, new TaskBeholderAgro());
		targetTasks.addTask(3, new EntityAIFindEntityNearestPlayer(this));
	}


	@Override
	protected void entityInit() {
		super.entityInit();
	}


	/**
	 * A convenience method to create potion effects (also good for debugging).
	 *
	 * @param id
	 * @param duration
	 * @param level
	 * @return
	 */
	private PotionEffect makePotionEffect(String id, int duration, int level) {
		//System.out.println("Creating potion effect " + id);
		return new PotionEffect(Potion
				.getPotionFromResourceLocation(id), duration, level);
	}


	public PotionEffect[] defineEyeRay() {
		if (this.getAttackTarget() instanceof EntityPlayer) {
			EntityPlayer foo = (EntityPlayer) this.getAttackTarget();
			if (foo.capabilities.isCreativeMode)
				return new PotionEffect[0];
		}

		int bar = 0;

		if (this.world.getDifficulty() == EnumDifficulty.EASY)
			bar = 1;
		if (this.world.getDifficulty() == EnumDifficulty.NORMAL)
			bar = 2;
		if (this.world.getDifficulty() == EnumDifficulty.HARD)
			bar = 3;

		int num = rand.nextInt(3) + bar;

		PotionEffect[] theEffect = new PotionEffect[num];

		for (int i = 0; i < num; i++) {
			int eff = this.rand.nextInt(11);

			if (eff == 0)
				theEffect[i] = makePotionEffect("slowness", 120, 2);
			else if (eff == 1)
				theEffect[i] = makePotionEffect("mining_fatigue", 160, 2);
			else if (eff == 2)
				theEffect[i] = makePotionEffect("instant_damage", 1, 1);
			else if (eff == 3)
				theEffect[i] = makePotionEffect("nausea", 280, 1);
			else if (eff == 4)
				theEffect[i] = makePotionEffect("resistance", 100, -2);
			else if (eff == 5)
				theEffect[i] = makePotionEffect("blindness", 120, 1);
			else if (eff == 6)
				theEffect[i] = makePotionEffect("hunger", 400, 3);
			else if (eff == 7)
				theEffect[i] = makePotionEffect("weakness", 120, 1);
			else if (eff == 8)
				theEffect[i] = makePotionEffect("poison", 200, 1);
			else if (eff == 9)
				theEffect[i] = makePotionEffect("wither", 100, 1);
			else
				theEffect[i] = makePotionEffect("levitation", 100, 1);
			//new PotionEffectAddled(DungeonMobs.potionAddleID, 180, 0);
			// Change back?

			if (i > 0) {
				if (theEffect[i - 1].equals(theEffect[i]))
					i--;
			}
			//System.out.println("The Effect: " + theEffect + "; Contains: " + theEffect[i]);
		}

		return theEffect;
	}


	@Override
	public void onUpdate() {
		if (!world.isRemote &&
				(world.getDifficulty() == EnumDifficulty.PEACEFUL)) {
			setDead();
		}
		super.onUpdate();
	}


	public int getTalkInterval() {
		return 300;
	}


	@Override
	public boolean getCanSpawnHere() {
		if (beholderIg || DungeonMobsHelper.isNearSpawner(world, this, mobName)) {
			return super.getCanSpawnHere();
		}
		if (world.canSeeSky(new BlockPos(posX, posY, posZ))) {
			return false;
		}
		if (posY > 32.0D && !ignoreHeight) {
			return false;
		}
		return (world.getDifficulty()
				!= EnumDifficulty.PEACEFUL)
				&& isValidLightLevel();
	}


	protected boolean isValidLightLevel() {
		BlockPos pos = new BlockPos(this.posX, getEntityBoundingBox().minY, this.posZ);
		return world.getLight(pos, true) < 8;
	}


	// TODO: New loot dropping system
	protected void dropFewItems(boolean par1, int par2) {
		int var3;
		int var4;
		int var5;
		var3 = this.rand.nextInt(3);
		for (var4 = 0; var4 < var3; var4++) {
			this.dropItem(Items.SPIDER_EYE, 1);
		}
	}


	public boolean attackEntityFrom(DamageSource par1DamageSource, int par2) {
		EntityLivingBase target = getAttackTarget();
		if (par1DamageSource.getTrueSource() != null && (par1DamageSource.getTrueSource()
				instanceof EntityLiving
				&& !(par1DamageSource.getTrueSource() instanceof EntityEyeRay))) {
			if (par1DamageSource.getImmediateSource() instanceof EntityThrowable) {
				EntityThrowable foo = (EntityThrowable) par1DamageSource.getImmediateSource();
				target = (EntityLivingBase) foo.getThrower();
			} else {
				target = (EntityLivingBase) par1DamageSource.getTrueSource();
			}
		}

		if (par1DamageSource.getImmediateSource() != null
				&& par1DamageSource.getImmediateSource() instanceof EntityEyeRay)
			return false;

		return super.attackEntityFrom(par1DamageSource, par2);
	}


	@Override
	protected SoundEvent getAmbientSound() {
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
	}

	@Override
	public void setSwingingArms(boolean swingingArms) {
	}


	public MoveHelperBeholder getMoveHelper() {
		return moveHelper;
	}


	public void doAttack() {
		EntityLivingBase target = getAttackTarget();


		if (target != null && target.getDistanceSq(this) < ADIST2) {
			faceEntity(target, 10.0F, (float) getVerticalFaceSpeed());

			double var11 = target.posX + target.motionX - posX;
			double var13 = target.getEntityBoundingBox().minY
					+ target.motionY
					+ (double) (target.height / 4.0F) - posY;
			double var15 = target.posZ + target.motionZ - posZ;
			renderYawOffset = rotationYaw = -((float) Math.atan2(var11, var15)) * 180.0F / (float) Math.PI;

			if (this.canEntityBeSeen(target)) {
				this.attackCounter++;

				int bar = 0;

				if (world.getDifficulty() == EnumDifficulty.EASY)
					bar = 1;
				if (world.getDifficulty() == EnumDifficulty.NORMAL)
					bar = 2;
				if (world.getDifficulty() == EnumDifficulty.HARD)
					bar = 3;

				if (this.attackCounter >= (15 - (bar * 2))) {
					PotionEffect[] foo = this.defineEyeRay();
					EntityEyeRay ray = new EntityEyeRay(world, this);
					ray.setEffects(foo);
					ray.shoot(var11, var13, var15, 1.25f, 0.0f);
					world.spawnEntity(ray);
					attackCounter = (-55 + (bar * 5));
				}
			} else if (this.attackCounter > 0) {
				--attackCounter;
			}
		} else {
			renderYawOffset = rotationYaw =
					-((float) Math.atan2(motionX, motionZ)) * 180.0F / (float) Math.PI;

			if (attackCounter > 0)
				--attackCounter;
		}
	}
}
