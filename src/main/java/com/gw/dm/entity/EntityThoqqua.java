package com.gw.dm.entity;

import com.gw.dm.DungeonMobs;
import com.gw.dm.EntityDungeonMob;
import com.gw.dm.util.DungeonMobsHelper;
import com.gw.dm.util.MiscRegistrar;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderHell;


public class EntityThoqqua extends EntityDungeonMob {
	public int lavaResetTimer;
	public int setShitOnFire;
	public int attackTime;
	private static String mobName = DungeonMobs.MODID + ":dmthoqqua";

	public EntityThoqqua(World par1World) {
		super(par1World);
		setSize(1.8F, 0.5F);
		experienceValue = 30;
		isImmuneToFire = true;
		ignoreHeight = false;
		lavaResetTimer = 0;
		setShitOnFire = 0;
		attackTime = 0;
	}


	@Override
	public boolean getCanSpawnHere() {
		if (DungeonMobsHelper.isNearSpawner(world, this, mobName)) {
			return super.getCanSpawnHere();
		}
		if (world.provider instanceof WorldProviderHell)
			return super.getCanSpawnHere();

		if (world.canBlockSeeSky(new BlockPos(posX, posY, posZ)))
			return false;

		if (posY > 32.0D && !ignoreHeight)
			return false;

		return super.getCanSpawnHere();
	}


	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(25.0D);
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4D);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
		getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.0D);
	}

	protected void initEntityAI() {
		tasks.addTask(4, new EntityAIAttackMelee(this, 1.0D, true));
		tasks.addTask(5, new EntityAIWanderAvoidWater(this, 1.0D));
		tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
		tasks.addTask(7, new EntityAILookIdle(this));
		targetTasks.addTask(3, new EntityAIHurtByTarget(this, true, new Class[0]));
	}


	@Override
	public int getTotalArmorValue() {
		return 8;
	}


	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.ENTITY_SILVERFISH_AMBIENT;
	}


	@Override
	protected SoundEvent getHurtSound(DamageSource src) {
		return SoundEvents.ENTITY_SILVERFISH_HURT;
	}


	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_SILVERFISH_DEATH;
	}


	@Override
	protected void dropFewItems(boolean par1, int par2) {
		int var3;
		int var4;

		var3 = rand.nextInt(3);

		for (var4 = 0; var4 < var3; var4++) {
			dropItem(Items.FIRE_CHARGE, 1);
		}
	}


	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		setShitOnFire++;
		attackTime--;

		int foo;

		if (DungeonMobsHelper.getDifficulty(world) < 1)
			foo = 0;
		else if (DungeonMobsHelper.getDifficulty(world) < 3)
			foo = 1;
		else
			foo = 2;

		if (setShitOnFire > 39) {
			for (int a = (int) posX - foo; a < (int) posX + foo; a++)
				for (int b = (int) posY - foo; b < (int) posY + foo; b++)
					for (int c = (int) posZ - foo; c < (int) posZ + foo; c++) {
						makeFire(a, b, c);
					}
			setShitOnFire = 0;
		}
		updateEntityGnomeState();
	}


	private void makeFire(int x, int y, int z) {
		double foo = world.rand.nextFloat() - 0.5F;
		double bar = world.rand.nextFloat() - 0.5F;
		double cow = world.rand.nextFloat() - 0.5F;
		world.spawnParticle(EnumParticleTypes.FLAME,
				x + foo, y + bar, z + cow, 0.0D, 0.0D, 0.0D);

		foo = world.rand.nextFloat() - 0.5F;
		bar = world.rand.nextFloat() - 0.5F;
		cow = world.rand.nextFloat() - 0.5F;
		world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL,
				x + foo, y + bar, z + cow, 0.0D, 0.0D, 0.0D);

		BlockPos where = new BlockPos(x, y, z);
		if (world.isAirBlock(where)
				&& world.isSideSolid(new BlockPos(x, y - 1, z), EnumFacing.UP)) {
			world.setBlockState(where, Blocks.FIRE.getDefaultState());
		} else if (world.getBlockState(where).getBlock() == Blocks.WATER) {
			world.playSound(x + 0.5D, y + 0.5D, z + 0.5D,
					SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE,
					null, 1.0F, 1.0F / (getRNG().nextFloat() * 0.4F + 0.8F), false);
			world.setBlockState(where, Blocks.COBBLESTONE.getDefaultState());
		}
	}


	protected void updateEntityGnomeState() {
		super.updateEntityActionState();

		if (!world.isRemote) {
			if (getAttackTarget() == null && !hasPath()) {
				int i;
				int j;
				int k;
				Block l;

				i = MathHelper.floor(posX);
				j = MathHelper.floor(posY + 0.5D);
				k = MathHelper.floor(posZ);

				for (int l1 = 0; l1 < 6; l1++) {
					// Pretty sure this is not a faithful update of the original logic... :(
					BlockPos bp = new BlockPos(i + motionX, j + motionY, k + motionZ);
					l = world.getBlockState(bp).getBlock();

					if (l == Blocks.STONE) {
						world.setBlockState(bp,
								MiscRegistrar.blockLavarock.getDefaultState(), 3);
					}
				}
			} else if (getAttackTarget() != null && !hasPath())
				setAttackTarget(null);
		}
	}


	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeEntityToNBT(par1NBTTagCompound);

		par1NBTTagCompound.setInteger("LavaTimer", lavaResetTimer);
		par1NBTTagCompound.setInteger("FireTimer", setShitOnFire);
	}


	@Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readEntityFromNBT(par1NBTTagCompound);
		lavaResetTimer = par1NBTTagCompound.getInteger("LavaTimer");
		setShitOnFire = par1NBTTagCompound.getInteger("FireTimer");
	}


	protected Entity findPlayerToAttack() {
		double d0 = 16.0D;
		return world.getClosestPlayerToEntity(this, d0);
	}


	protected void attackEntity(Entity par1Entity, float par2) {
		if (attackTime <= 0 && par2 < 1.2F && getEntityBoundingBox().expand(1.5D, 1.5D, 1.5D)
				.intersects(par1Entity.getEntityBoundingBox())) {
			attackTime = 20;
			attackEntityAsMob(par1Entity);
		}
	}


	public float getBlockPathWeight(int x, int y, int z) {
		BlockPos where = new BlockPos(x, y, z);
		return world.getBlockState(where).getBlock() == Blocks.STONE
				? 10.0F : super.getBlockPathWeight(where);
	}


	public boolean canRenderOnFire() {
		return false;
	}
}
