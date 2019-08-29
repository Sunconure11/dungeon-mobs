package com.gw.dm.entity;

import com.gw.dm.util.ConfigHandler;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityRakshasaImage extends EntityCreature implements IRangedAttackMob {
	public EntityRakshasa owner;
	private int timeToLive;

	public EntityRakshasaImage(World par1World) {
		super(par1World);
		experienceValue = 0;
		timeToLive = 240;
		owner = null;

		tasks.addTask(1, new EntityAISwimming(this));
		tasks.addTask(2, new EntityAIAttackRanged(this, 1.0F, 40, 16.0F));
		tasks.addTask(3, new EntityAIWander(this, 1.0F));
		tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		tasks.addTask(5, new EntityAILookIdle(this));
		targetTasks.addTask(0, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(1, new EntityAINearestAttackableTarget(this,
				EntityPlayer.class, 0, true, false, null));
	}


	public EntityRakshasaImage(World par1World, int par2) {
		this(par1World);
		timeToLive = par2;
	}


	public EntityRakshasaImage(World par1World, EntityLivingBase foo) {
		this(par1World);
		setAttackTarget(foo);
	}


	public EntityRakshasaImage(World par1World, EntityLivingBase foo, EntityRakshasa bar) {
		this(par1World);
		setAttackTarget(foo);
		owner = bar;
	}


	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(42.0D
				* ConfigHandler.healthx);
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
		getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.0D);
	}


	@Override
	public boolean getCanSpawnHere() {
		return super.getCanSpawnHere();
	}


	@Override
	protected SoundEvent getAmbientSound() {
		return null;
	}


	@Override
	protected SoundEvent getHurtSound(DamageSource src) {
		return null;
	}


	@Override
	protected SoundEvent getDeathSound() {
		return null;
	}


	@Override
	public void onLivingUpdate() {
		timeToLive--;

		if (timeToLive == 0)
			setDead();

		super.onLivingUpdate();
	}


	public boolean attackEntityFrom(DamageSource par1DamageSource, float par2) {
		setDead();
		return false;
	}


	@Override
	protected void dropFewItems(boolean par1, int par2) {/*Do NOthing*/}


	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase var1,
	                                         float lol) {/*Do NOthing*/}


	@Override
	public void setDead() {
		world.spawnParticle(EnumParticleTypes.EXPLOSION_HUGE,
				posX, posY + 1.5D, posZ, 0.0D, 0.0D, 0.0D);

		for (int i = 0; i < 8; i++) {
			double foo = world.rand.nextDouble() - world.rand.nextDouble();

			world.spawnParticle(EnumParticleTypes.EXPLOSION_LARGE,
					posX + foo, posY + 1.0D + foo, posZ + foo, 0.0D, 0.0D, 0.0D);
		}

		playSound(SoundEvents.ENTITY_GENERIC_EXTINGUISH_FIRE,
				1.0F, 1.0F / (getRNG().nextFloat() * 0.4F + 0.8F));

		if (owner != null) {
			owner.destroyImage(this);
		}
		super.setDead();
	}


	@Override
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeEntityToNBT(par1NBTTagCompound);
		par1NBTTagCompound.setInteger("timeToLive", timeToLive);
	}


	@Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readEntityFromNBT(par1NBTTagCompound);
		timeToLive = par1NBTTagCompound.getInteger("timeToLive");
	}


	@Override
	public void setSwingingArms(boolean swingingArms) {/*DO Nothing*/}
}
