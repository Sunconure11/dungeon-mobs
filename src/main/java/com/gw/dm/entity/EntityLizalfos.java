package com.gw.dm.entity;

import com.gw.dm.DungeonMobs;
import com.gw.dm.EntityDungeonMob;
import com.gw.dm.ai.EntityAIFollowTwin;
import com.gw.dm.ai.EntityAIMonsterPanic;
import com.gw.dm.util.AudioHandler;
import com.gw.dm.util.ConfigHandler;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import static com.gw.dm.util.ConfigHandler.lizalfosIg;

public class EntityLizalfos extends EntityDungeonMob {
	private static final List<LocOffset> locations = new ArrayList<>();
	private static String mobName = DungeonMobs.MODID + ":lizalfos";
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
	private boolean newSpawn;
	private EntityAIHurtByTarget revenge;
	private EntityAINearestAttackableTarget target;
	
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
		
		newSpawn = true;
		
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
		target = new EntityAINearestAttackableTarget(this, EntityPlayer.class, 0, true, false, null);
		
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
	private EntityLizalfos(World world, EntityLizalfos twin, double x, double y, double z) {
		this(world);
		newSpawn = false;
		
		twin.setTwin(this);
		setTwin(twin);
		
		isTwin = true;
		
		twinEntityID = twin.getEntityId();
		twin.twinEntityID = getEntityId();
		
		setLocationAndAngles(x, y, z, this.rotationYaw, this.rotationPitch);
		
		setIsTwin(true);
		setTwinID(this.twinID);
	}
	
	public static void initLocations() {
		for (int i = -2; i < 3; i++)
			for (int j = -2; j < 3; j++) {
				if ((i != 0) && (j != 0)) {
					locations.add(new LocOffset(i, j));
				}
			}
	}
	
	@Override
	public int getTalkInterval() {
		return 80;
	}
	
	@Override
	protected SoundEvent getAmbientSound() {
		if (isTwin()) {
			return AudioHandler.entityLizalfosAmbient1;
		}
		else {
			return AudioHandler.entityLizalfosAmbient2;
		}
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
	
	public int getTwinID() {
		return twinID;
	}
	
	public void setTwinID(int i) {
		twinID = i;
	}
	
	public boolean isTwin() {
		return isTwin;
	}
	
	public void setIsTwin(boolean foo) {
		isTwin = foo;
	}
	
	public EntityLizalfos getTwin() {
		if (myTwinWeak != null) {
			return myTwinWeak.get();
		}
		else return null;
	}
	
	public void setTwin(EntityLizalfos foo) {
		myTwinWeak = new WeakReference<EntityLizalfos>(foo);
	}
	
	public void killTwin() {
		myTwinIsDead = true;
		myTwinWeak = null;
	}
	
	public boolean isTwinDead() {
		return myTwinIsDead;
	}
	
	public void setDead() {
		if (this.myTwin != null && myTwinWeak != null && !isTwinDead()) myTwinWeak.get().killTwin();
		super.setDead();
	}
	
	// FIXME: This should probably by in onUpdate(), not onLivingUpdate()...?
	public void onLivingUpdate() {
		if (newSpawn) {
			spawnTwin();
		}
		
		if (!goodToGo && !isTwinDead()) {
			findTwin();
			goodToGo = true;
		}
		
		if (runTimer > 0) {
			runTimer--;
			
			if (runTimer < 1) cancelRetreat();
			
			if (isTwinDead()) {
				runTimer = 0;
				cancelRetreat();
			}
		}
		
		if (getAttackTarget() != null) {
			tasks.removeTask(followTwin);
		}
		else if (isTwin() && !isRetreating && !isTwinDead()) {
			tasks.addTask(3, followTwin);
		}
		if (!isTwinDead()/* && !world.isRemote*/ && !(myTwin == null)) {
			if (getAttackTarget() != null && myTwinWeak.get().getAttackTarget() == null) myTwinWeak.get().setAttackTarget(getAttackTarget());
		}
		
		if (getHealth() < (getMaxHealth() / 2) && !hasRetreated && !isRetreating && !isTwinDead()) {
			runAway();
			
			if (!isTwinDead() && myTwin != null) myTwinWeak.get().cancelRetreat();
		}
		
		super.onLivingUpdate();
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
		par1NBTTagCompound.setBoolean("newSpawn", newSpawn);
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
		newSpawn = par1NBTTagCompound.getBoolean("newSpawn");
		
		if (isRetreating) {
			retreat = new EntityAIAvoidEntity(this, EntityPlayer.class, 12.0F, 1.0F, 0.4F);
			
			tasks.removeTask(attack);
			tasks.removeTask(followTwin);
			
			tasks.addTask(3, retreat);
		}
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource src, float amount) {
		if (src == DamageSource.FALL) {
			return false;
		}
		else if (src.isProjectile()) {
			playSound(AudioHandler.entityLizalfosBlock, 1.0F, (world.rand.nextFloat() - world.rand.nextFloat()) * 0.2F + 1.0F);
			return false;
		}
		else {
			return super.attackEntityFrom(src, amount);
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
	
	@Override
	public boolean getCanSpawnHere() {
		if (isTwin()) {
			return world.checkNoEntityCollision(getEntityBoundingBox()) && world.getEntitiesInAABBexcluding(this, getEntityBoundingBox(), null).isEmpty() && !world.containsAnyLiquid(getEntityBoundingBox());
		}
		if (lizalfosIg || DungeonMobsHelper.isNearSpawner(world, this, mobName)) {
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
	
	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D * ConfigHandler.healthx);
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.35D);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(7.0D * ConfigHandler.damagex + ConfigHandler.damageplus);
		getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(10.0D);
		getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.0D);
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
			}
			catch (NoSuchMethodError e) {
				allEntities = world.getEntitiesWithinAABB(EntityLizalfos.class, getEntityBoundingBox().expand(48.0F, 48.0F, 48.0F));
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
		
		addPotionEffect(new PotionEffect(Potion.getPotionFromResourceLocation("regeneration"), 240, 1));
	}
	
	@Override
	public void fall(float dist, float multiplier) {
		dist = ForgeHooks.onLivingFall(this, dist, multiplier)[0];
		
		if (dist <= 0) {
			return;
		}
		int var2 = MathHelper.ceil(dist - 3.0F);
		
		if (var2 > 0) {
			BlockPos where = new BlockPos(MathHelper.floor(posX), MathHelper.floor(posY - 0.20000000298023224D), MathHelper.floor(posZ));
			Block block = world.getBlockState(where).getBlock();
			
			if (block != Blocks.AIR) {
				SoundType sound = block.getSoundType();
				
				playSound(sound.getFallSound(), sound.getVolume() * 0.5F, sound.getPitch() * 0.75F);
			}
		}
	}
	
	@Override
	protected void jump() {
		motionY = 0.5D;
		
		Potion jump = Potion.getPotionFromResourceLocation("jump_boost");
		if (isPotionActive(jump)) motionY += (double) ((float) (getActivePotionEffect(jump).getAmplifier() + 1) * 0.1F);
		
		if (isSprinting()) {
			float var1 = rotationYaw * 0.017453292F;
			motionX -= (double) (MathHelper.sin(var1) * 0.2F);
			motionZ += (double) (MathHelper.cos(var1) * 0.2F);
		}
		
		isAirBorne = true;
		ForgeHooks.onLivingJump(this);
	}
	
	private void spawnTwin() {
		newSpawn = false;
		if (world.isRemote) {
			return;
		}
		boolean successful = false;
		Collections.shuffle(locations);
		EntityLizalfos twin = new EntityLizalfos(world, this, posX, posY, posZ);
		for (LocOffset offset : locations) {
			twin.setLocationAndAngles(posX + offset.x, posY, posZ + offset.z, rotationYaw, rotationPitch);
			if (twin.isNotColliding()) {
				world.spawnEntity(twin);
				twinEntityID = twin.getEntityId();
				twin.twinEntityID = getEntityId();
				successful = true;
				break;
			}
		}
		if (!successful) {
			twin.setDead();
			setDead();
		}
	}
	
	private static class LocOffset {
		public double x, z;
		
		public LocOffset(int x, int z) {
			this.x = (double) x;
			this.z = (double) x;
		}
	}
}
