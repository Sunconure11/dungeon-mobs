package com.gw.dm.entity;

import static com.gw.dm.util.ConfigHandler.fallenAngelIg;

import javax.annotation.Nullable;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderHell;

import com.gw.dm.DungeonMobs;
import com.gw.dm.EntityDungeonFlying;
import com.gw.dm.ai.AIAngelAttack;
import com.gw.dm.ai.AIAngelWander;
import com.gw.dm.ai.TaskAngelAgroOnPlayer;
import com.gw.dm.projectile.EntityLightball;
import com.gw.dm.util.AudioHandler;
import com.gw.dm.util.DungeonMobsHelper;

public class EntityFallenAngel extends EntityDungeonFlying implements IMob, IRangedAttackMob {

	private static final DataParameter<Boolean> SWINGING_ARMS
			= EntityDataManager.<Boolean>createKey(EntityFallenAngel.class, DataSerializers.BOOLEAN);
	private static String mobName = DungeonMobs.MODID + ":dmfallenangel";


	public EntityFallenAngel(World worldIn) {
		super(worldIn);
		setSize(0.75f, 1.95f);
		moveHelper = new AIAngelWander.AngelicMoveHelper(this);
		experienceValue = 65;
		isImmuneToFire = true;
	}


	@Override
	protected void initEntityAI() {
		AIAngelWander motion = new AIAngelWander(this, 1.0);
		tasks.addTask(2, new AIAngelAttack(motion, this));
		tasks.addTask(4, motion);
		tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		tasks.addTask(6, new EntityAILookIdle(this));
		targetTasks.addTask(2, new TaskAngelAgroOnPlayer(this, 24.0d));
		targetTasks.addTask(3, new EntityAIFindEntityNearestPlayer(this));
	}


	@Override
	protected void entityInit() {
		dataManager.register(SWINGING_ARMS, Boolean.valueOf(false));
		super.entityInit();
	}


	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(32.0d);
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(72.0d);
		getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(12.0d);
		getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(7.0d);
	}


	@Override
	public SoundCategory getSoundCategory() {
		return SoundCategory.HOSTILE;
	}


	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {
		if (!world.isRemote) {
			double dx = target.posX - posX;
			double dy = (target.posY + (target.height / 2)) - (posY + (height / 2));
			double dz = target.posZ - posZ;
			EntityLightball ball = new EntityLightball(world, this);
			ball.shoot(dx, dy, dz, 1.2f, 0.0f);
			world.spawnEntity(ball);
		}
	}


	@Override
	public void onUpdate() {
		if (!world.isRemote &&
				(world.getDifficulty() == EnumDifficulty.PEACEFUL)) {
			setDead();
		}
		super.onUpdate();
	}


	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		Entity entity = source.getTrueSource();
		if ((entity != null) && (entity instanceof EntityLivingBase)) {
			if ((getAttackTarget() == null)
					|| (rand.nextInt(20) < amount)
					|| (!canEntityBeSeen(getAttackTarget()))) {
				setAttackTarget((EntityLivingBase) entity);
			}
		}
		return super.attackEntityFrom(source, amount);
	}


	@Override
	public boolean canAttackClass(Class cls) {
		return true;
	}


	public int getTalkInterval() {
		return 60;
	}


	@Override
	protected SoundEvent getAmbientSound() {
		switch (rand.nextInt(4)) {
			case 0:
				return AudioHandler.entityFallenSing1;
			case 1:
				return AudioHandler.entityFallenSing2;
			case 2:
				return AudioHandler.entityFallenSing3;
			case 3:
				return AudioHandler.entityFallenSing4;
			default:
				return AudioHandler.entityFallenSing1;
		}
	}


	@Override
	protected SoundEvent getHurtSound(DamageSource src) {
		return null;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return null;
	}


	protected float getSoundVolume() {
		return 2.5f;
	}


	@Override
	@Nullable
	protected Item getDropItem() {
		return Item.getByNameOrId("gold_ingot"); // Item.getByNameOrId("diamond");
	}


	@Override
	public boolean getCanSpawnHere() {
		if(fallenAngelIg 
				|| (world.provider instanceof WorldProviderHell) 
				|| DungeonMobsHelper.isNearSpawner(world, this, mobName)) {
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
	public void dropFewItems(boolean wasRecentlyHit, int lootingModifier) {
		Item item = Item.getByNameOrId("glowstone_dust");
		int n = rand.nextInt(5) + 3 + lootingModifier;
		for (int i = 0; i < n; i++) {
			dropItem(item, 1);
		}
		if (rand.nextInt(DungeonMobsHelper.getDifficulty(world)) == 0) {
			ItemStack sword = new ItemStack(Items.GOLDEN_SWORD);
			Enchantment ench;
			sword.addEnchantment(Enchantment.getEnchantmentByLocation("smite"),
					rand.nextInt(3) + rand.nextInt(3) + 1);
		}
		super.dropFewItems(wasRecentlyHit, lootingModifier);
	}


	@Override
	public boolean canBreatheUnderwater() {
		return true;
	}


	@Override
	public void setSwingingArms(boolean swingingArms) {
		dataManager.set(SWINGING_ARMS, Boolean.valueOf(swingingArms));
	}


	public boolean areArmsUp() {
		return ((Boolean) dataManager.get(SWINGING_ARMS)).booleanValue();
	}


}
