package com.gw.dm.entity;

import com.gw.dm.DungeonMobs;
import com.gw.dm.EntityDungeonMob;
import com.gw.dm.ai.EntityAIFollowTwin;
import com.gw.dm.ai.EntityAIMonsterPanic;
import com.gw.dm.util.AudioHandler;
import com.gw.dm.util.DungeonMobsHelper;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.List;

public class EntityLizalfos extends EntityDungeonMob {
	public boolean myTwinIsDead;
	public boolean goodToGo;
	public boolean isRetreating;
	public boolean hasRetreated;
	private int twinID;
	private int twinEntityID;
	private boolean isTwin;
	private boolean ignoreHeight;
	private EntityLizalfos myTwin;
	private WeakReference<EntityLizalfos> myTwinWeak;
	private boolean isLeaping;
	private int runTimer;

	private EntityAIFollowTwin followTwin;
	private EntityAIAttackMelee attack;
	private EntityAIAvoidEntity retreat;
	private EntityAIMonsterPanic panic;

	private EntityAIHurtByTarget revenge;
	private EntityAINearestAttackableTarget target;
	
	private static String mobName = DungeonMobs.MODID + ":lizalfos";


	/**
	 * The primary (and public) constructor, for creating the first 
	 * of each pair -- i.e., the normal constructor for core spawning.
	 * 
	 * @param par1World
	 */
	public EntityLizalfos(World par1World) {
		super(par1World);
		ignoreHeight = false;
		twinID = -1;
		isTwin = false;
		myTwin = null;
		myTwinIsDead = false;
		goodToGo = false;

		isRetreating = false;
		hasRetreated = false;

		setSize(1.4F, 2.5F);
		experienceValue = 30;

		runTimer = 0;

		followTwin = new EntityAIFollowTwin(this, 1.0F);
		attack = new EntityAIAttackMelee(this, 1.0F, false);
		retreat = new EntityAIAvoidEntity(this, EntityPlayer.class, 24.0F, 1.0F, 0.5F);
		panic = new EntityAIMonsterPanic(this, 1.0F);

		revenge = new EntityAIHurtByTarget(this, false);
		target = new EntityAINearestAttackableTarget(this,
				EntityPlayer.class, 0, true, false, null);

		tasks.addTask(1, new EntityAISwimming(this));
		tasks.addTask(2, attack);

		tasks.addTask(4, new EntityAIWander(this, 1.0F));
		tasks.addTask(5, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		tasks.addTask(5, new EntityAILookIdle(this));
		targetTasks.addTask(0, revenge);
		targetTasks.addTask(1, target);
	}
	
	
	/**
	 * A special private constructor, just for creating the twin.
	 * 
	 * @param world
	 * @param twin
	 * @param x
	 * @param z
	 * @param w
	 */
	private EntityLizalfos(World world, EntityLizalfos twin, 
				double x, double z, double w) {
		this(world);
		// TODO: Make it work!
		// I need to look more carefully at the old, broken system, so I can 
		// take its code out as I replace it with this system.
	}


	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.35D);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(7.0D);
		getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(10.0D);
		getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.0D);
	}


	@Override
	public int getTalkInterval() {
		return 80;
	}


	@Override
	protected SoundEvent getAmbientSound() {
		if (isTwin()) {
			return AudioHandler.entityLizalfosAmbient1;
		} else {
			return AudioHandler.entityLizalfosAmbient2;
		}
	}


	@Override
	protected SoundEvent getHurtSound(DamageSource src) {
		return AudioHandler.entityLizalfosHurt;
	}


	@Override
	protected SoundEvent getDeathSound() {
		return AudioHandler.entityLizalfosDeath;
	}


	public int getTwinID() {
		return twinID;
	}


	public boolean isTwin() {
		return isTwin;
	}


	public void setIsTwin(boolean foo) {
		isTwin = foo;
	}


	public void setTwinID(int i) {
		twinID = i;
	}


	public void setTwin(EntityLizalfos foo) {
		myTwinWeak = new WeakReference<EntityLizalfos>(foo);
		//myTwin = foo;
	}


	public EntityLizalfos getTwin() {
		return myTwinWeak.get();
	}


	public void killTwin() {
		myTwinIsDead = true;
		myTwinWeak = null;
	}


	@Override
	public boolean getCanSpawnHere() {
		if (isTwin()) {
			return world.checkNoEntityCollision(getEntityBoundingBox())
					&& world.getEntitiesInAABBexcluding(this, getEntityBoundingBox(), null).isEmpty()
					&& !world.containsAnyLiquid(getEntityBoundingBox());
		}
		if (DungeonMobsHelper.isNearSpawner(world, this, mobName)) {
			return super.getCanSpawnHere();
		}
		if (world.canBlockSeeSky(new BlockPos(posX, posY, posZ))) {
			return false;
		}
		if (posY > 32.0D && !ignoreHeight) {
			return false;
		}
		return super.getCanSpawnHere();
	}


	public boolean isTwinDead() {
		return myTwinIsDead;
	}


	public void setDead() {
		if (myTwinWeak != null && !isTwinDead())
			myTwinWeak.get().killTwin();
		super.setDead();
	}


	@Override
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeEntityToNBT(par1NBTTagCompound);

		par1NBTTagCompound.setInteger("twinID", twinID);
		par1NBTTagCompound.setBoolean("isTwin", isTwin);
		par1NBTTagCompound.setBoolean("twinDead", myTwinIsDead);
		par1NBTTagCompound.setBoolean("hasFled", hasRetreated);
		par1NBTTagCompound.setBoolean("isRunning", isRetreating);
		par1NBTTagCompound.setInteger("runTimer", runTimer);
	}


	@Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readEntityFromNBT(par1NBTTagCompound);

		twinID = par1NBTTagCompound.getInteger("twinID");
		isTwin = par1NBTTagCompound.getBoolean("isTwin");
		myTwinIsDead = par1NBTTagCompound.getBoolean("twinDead");
		hasRetreated = par1NBTTagCompound.getBoolean("hasFled");
		isRetreating = par1NBTTagCompound.getBoolean("isRunning");
		runTimer = par1NBTTagCompound.getInteger("runTimer");

		if (isRetreating) {
			retreat = new EntityAIAvoidEntity(this, EntityPlayer.class, 12.0F, 1.0F, 0.4F);

			tasks.removeTask(attack);
			tasks.removeTask(followTwin);

			tasks.addTask(3, retreat);
		}
	}


	@Override
	protected void entityInit() {
		if (twinID == -1 && !world.isRemote) {
			boolean twinSpawned = false;

			twinID = world.rand.nextInt(16000);

			int spawnAttempts = 0;

			while (!twinSpawned && spawnAttempts < 6) {
				WeakReference<EntityLizalfos> bar
						= new WeakReference<EntityLizalfos>(new EntityLizalfos(world));

				EntityLizalfos foo = bar.get();

				double randX = posX + ((world.rand.nextFloat() - 0.5F) * 8.0F);
				double randY = posY + 0.5F;
				double randZ = posZ + ((world.rand.nextFloat() - 0.5F) * 8.0F);

				foo.setLocationAndAngles(randX, randY, randZ, rotationYaw, rotationPitch);

				foo.setIsTwin(true);
				foo.setTwinID(twinID);

				if (foo.getCanSpawnHere()) {
					foo.setTwin(this);
					setTwin(foo);

					twinEntityID = getTwin().getEntityId();
					getTwin().twinEntityID = getEntityId();

					foo.entityInit();
					world.spawnEntity(foo);
					twinSpawned = true;
				} else {
					spawnAttempts++;
				}
			}

			if (spawnAttempts > 5)
				setDead();
		}
		super.entityInit();
	}


	public void forceTwinSpawn() {
		if (twinID == -1 && !world.isRemote) {
			boolean twinSpawned = false;

			twinID = world.rand.nextInt(16000);

			int spawnAttempts = 0;

			while (!twinSpawned && spawnAttempts < 6) {
				WeakReference<EntityLizalfos> bar
						= new WeakReference<EntityLizalfos>(new EntityLizalfos(world));

				EntityLizalfos foo = bar.get();

				double randX = posX + ((world.rand.nextFloat() - 0.5F) * 8.0F);
				double randY = posY + 0.5F;
				double randZ = posZ + ((world.rand.nextFloat() - 0.5F) * 8.0F);

				foo.setLocationAndAngles(randX, randY, randZ, rotationYaw, rotationPitch);

				foo.setIsTwin(true);
				foo.setTwinID(twinID);

				if (foo.getCanSpawnHere()) {
					foo.setTwin(this);
					setTwin(foo);

					twinEntityID = getTwin().getEntityId();
					getTwin().twinEntityID = getEntityId();

					foo.entityInit();
					world.spawnEntity(foo);
					twinSpawned = true;
				} else {
					spawnAttempts++;
				}
			}

			if (spawnAttempts > 5)
				setDead();
		}
	}


	public void onLivingUpdate() {
		if (!isTwinDead() && myTwinWeak == null)
			goodToGo = false;

		if (!goodToGo && !isTwinDead()) {
			findTwin();
			goodToGo = true;
		}

		if (runTimer > 0) {
			runTimer--;

			if (runTimer < 1)
				cancelRetreat();

			if (isTwinDead()) {
				runTimer = 0;
				cancelRetreat();
			}
		}

		if (getAttackTarget() != null) {
			tasks.removeTask(followTwin);
		} else if (isTwin() && !isRetreating && !isTwinDead()) {
			tasks.addTask(3, followTwin);
		}
		if (!isTwinDead()/* && !world.isRemote*/ && !(myTwin == null)) {
			if (getAttackTarget() != null && myTwinWeak.get().getAttackTarget() == null)
				myTwinWeak.get().setAttackTarget(getAttackTarget());
		}

		if (getHealth() < (getMaxHealth() / 2)
				&& !hasRetreated && !isRetreating && !isTwinDead()) {
			runAway();

			if (!isTwinDead() && myTwin != null)
				myTwinWeak.get().cancelRetreat();
		}

		super.onLivingUpdate();
	}


	@Override
	public boolean attackEntityFrom(DamageSource src, float amount) {
		if (src == DamageSource.FALL) {
			return false;
		} else if (src.isProjectile()) {
			playSound(AudioHandler.entityLizalfosBlock,
					1.0F, (world.rand.nextFloat() - world.rand.nextFloat()) * 0.2F + 1.0F);
			return false;
		} else {
			return super.attackEntityFrom(src, amount);
		}
	}


	@Override
	protected void jump() {
		motionY = 0.5D;

		Potion jump = Potion.getPotionFromResourceLocation("jump_boost");
		if (isPotionActive(jump))
			motionY += (double) ((float) (getActivePotionEffect(jump)
					.getAmplifier() + 1) * 0.1F);

		if (isSprinting()) {
			float var1 = rotationYaw * 0.017453292F;
			motionX -= (double) (MathHelper.sin(var1) * 0.2F);
			motionZ += (double) (MathHelper.cos(var1) * 0.2F);
		}

		isAirBorne = true;
		ForgeHooks.onLivingJump(this);
	}


	private void findTwin() {
		boolean skipFlag = false;

		Entity foo = world.getEntityByID(twinEntityID);

		if (foo instanceof EntityLizalfos) {
			EntityLizalfos bar = (EntityLizalfos) foo;

			if (bar.getTwinID() == twinID && !bar.equals(this)) {
				setTwin(bar);
				bar.setTwin(this);
				skipFlag = true;
			}
		}

		if (!world.isRemote && !skipFlag) {
			List allEntities;

			try {
				allEntities = world.getLoadedEntityList();
			} catch (NoSuchMethodError e) {
				allEntities = world.getEntitiesWithinAABB(EntityLizalfos.class,
						getEntityBoundingBox().expand(48.0F, 48.0F, 48.0F));
			}

			Iterator iter = allEntities.iterator();

			boolean foundFlag = false;

			while (iter.hasNext() && !foundFlag) {
				foo = (Entity) iter.next();

				if (foo instanceof EntityLizalfos && !foo.equals(this)) {
					EntityLizalfos bar = (EntityLizalfos) foo;

					if (twinID == bar.getTwinID()) {
						setTwin(bar);
						bar.setTwin(this);

						twinEntityID = getTwin().getEntityId();
						getTwin().twinEntityID = getEntityId();

						foundFlag = true;
					}
				}
			}

			if (isTwin()) {
				tasks.addTask(3, followTwin);
			}
			allEntities = null;
		}
	}


	public void cancelRetreat() {
		if (isRetreating) {
			tasks.removeTask(retreat);
			tasks.removeTask(panic);

			tasks.addTask(2, attack);

			targetTasks.addTask(0, revenge);
			targetTasks.addTask(1, target);

			isRetreating = false;
			hasRetreated = true;
			runTimer = 0;
		}
	}


	public void runAway() {
		isRetreating = true;

		tasks.removeTask(followTwin);
		tasks.removeTask(attack);

		targetTasks.removeTask(revenge);
		targetTasks.removeTask(target);

		tasks.addTask(2, panic);
		tasks.addTask(3, retreat);
		runTimer = 480;

		addPotionEffect(new PotionEffect(Potion
				.getPotionFromResourceLocation("regeneration"), 240, 1));
	}


	@Override
	protected void dropFewItems(boolean par1, int par2) {
		if (DungeonMobsHelper.getDifficulty(world) > 0) {
			int var2 = rand.nextInt(DungeonMobsHelper.getDifficulty(world) + 1);

			if (var2 == 0) {
				Item aSword = Items.IRON_SWORD;
				ItemStack mySword = new ItemStack(aSword);
				EnchantmentHelper.addRandomEnchantment(world.rand, mySword, 15, true);

				EntityItem itemEnt = new EntityItem(world, posX, posY, posZ, mySword);
				world.spawnEntity(itemEnt);
			}
		}
	}


	@Override
	public void fall(float dist, float multiplier) {
		dist = ForgeHooks.onLivingFall(this, dist, multiplier)[0];

		if (dist <= 0) {
			return;
		}
		int var2 = MathHelper.ceil(dist - 3.0F);

		if (var2 > 0) {
			BlockPos where = new BlockPos(MathHelper.floor(posX),
					MathHelper.floor(posY - 0.20000000298023224D),
					MathHelper.floor(posZ));
			Block block = world.getBlockState(where).getBlock();

			if (block != Blocks.AIR) {
				SoundType sound = block.getSoundType();

				playSound(sound.getFallSound(), sound.getVolume() * 0.5F, sound.getPitch() * 0.75F);
			}
		}
	}
}
