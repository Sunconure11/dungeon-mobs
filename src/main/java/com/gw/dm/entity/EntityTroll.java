package com.gw.dm.entity;

import static com.gw.dm.util.ConfigHandler.trollIg;
import net.minecraft.entity.Entity;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIFleeSun;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIRestrictSun;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;

import com.gw.dm.DungeonMobs;
import com.gw.dm.util.AudioHandler;
import com.gw.dm.util.ConfigHandler;
import com.gw.dm.util.DungeonMobsHelper;

public class EntityTroll extends EntityMob {
	private static String mobName = DungeonMobs.MODID + ":dmtroll";
	public int stoneStatus = 1;
	public int stoneCounter = 0;
	public int regenTimer = 0;
	public static final float regen = (float)(0.3 + Math.log10(ConfigHandler.healthx));;

	public EntityTroll(World par1World) {
		super(par1World);
		this.experienceValue = 40;

		this.setSize(1.3F, 2.5F);

		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIRestrictSun(this));
		this.tasks.addTask(2, new EntityAIFleeSun(this, 1.0F));
		this.tasks.addTask(3, new EntityAIAttackMelee(this, 1.0F, false));
		this.tasks.addTask(5, new EntityAIWander(this, 1.0F));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(6, new EntityAIWatchClosest(this, EntityAnimal.class, 8.0F));
		this.tasks.addTask(6, new EntityAILookIdle(this));

		this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, false));
		this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this,
				EntityPlayer.class, 0, true, false, null));
		this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this,
				EntityAnimal.class, 0, true, false, null));
	}


	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D 
				* ConfigHandler.healthx);
		this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.22D);
		this.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D 
				* ConfigHandler.damagex + ConfigHandler.damageplus);
		this.getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.0D);
	}


	protected boolean isAIEnabled() {
		return true;
	}


	public int getTotalArmorValue() {
		return 6;
	}


	public int getAttackStrength(Entity par1Entity) {
		return 5;
	}


	@Override
	protected SoundEvent getAmbientSound() {
		return AudioHandler.entityTrollAmbient;
	}


	@Override
	protected SoundEvent getHurtSound(DamageSource ds) {
		return AudioHandler.entityTrollHurt;
	}


	@Override
	protected SoundEvent getDeathSound() {
		return AudioHandler.entityTrollDeath;
	}


	protected void playStepSound(int par1, int par2, int par3, int par4) {
		playSound(AudioHandler.dmbts, 1.0f, 1.0f);
	}


	public int getTalkInterval() {
		return 80;
	}


	public boolean getCanSpawnHere() {
		if (world.canBlockSeeSky(new BlockPos(posX, posY, posZ))) {
			return false;
		}
		if (trollIg || DungeonMobsHelper.isNearSpawner(world, this, mobName)) {
			return super.getCanSpawnHere();
		}
		if (!this.isValidLightLevel()) {
			return false;
		}

		return super.getCanSpawnHere();
	}


	@Override
	protected boolean isValidLightLevel() {
		int var1 = MathHelper.floor(this.posX);
		int var2 = MathHelper.floor(this.getEntityBoundingBox().minY);
		int var3 = MathHelper.floor(this.posZ);

		if (world.getLightFor(EnumSkyBlock.SKY, new BlockPos(var1, var2, var3)) > rand.nextInt(32)) {
			return false;
		} else {
			int var4 = world.getLight(new BlockPos(var1, var2, var3));

			if (this.world.isThundering()) {
				int var5 = world.getSkylightSubtracted();
				world.setSkylightSubtracted(10);
				var4 = world.getSkylightSubtracted();
				world.setSkylightSubtracted(var5);
			}

			return var4 <= this.rand.nextInt(5);
		}
	}


	public void onLivingUpdate() {
		// A much cleaner and simpler way to handle petrification and regen
		if (!world.isRemote) {
			if (world.isDaytime() && (getBrightness() > 0.5f) && world.canSeeSky(getPosition())) {
				if(getRNG().nextInt((int)getHealth() + 2) < ++stoneCounter) {
					playSound(AudioHandler.dmbts, 1.0f, 1.0f);
					this.turnToStone();				
				}
			} else if (!this.isDead && (getHealth() > 0) && !isBurning()) {
				setHealth(getHealth() + regen);
			}
		}
		super.onLivingUpdate();
	}


	private void turnToStone() {
		if (!world.isRemote) {
			regenTimer = -1;

			setDead();
			
			int sx = (int)(getEntityBoundingBox().minX + 
					  ((getEntityBoundingBox().maxX - getEntityBoundingBox().minX) / 2) - 0.5);

			int sz = (int)(getEntityBoundingBox().minZ + 
					  ((getEntityBoundingBox().maxZ - getEntityBoundingBox().minZ) / 2) - 0.5);

			world.setBlockState(new BlockPos(sx, (int) getEntityBoundingBox().minY, sz),
					Blocks.STONE.getDefaultState(), 3);
			world.setBlockState(new BlockPos(sx, (int) getEntityBoundingBox().minY + 1, sz),
					Blocks.STONE.getDefaultState(), 3);
			this.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL,
					this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
			this.world.spawnParticle(EnumParticleTypes.SMOKE_NORMAL,
					this.posX, this.posY + 1, this.posZ, 0.0D, 0.0D, 0.0D);
		}
	}


	protected void dropFewItems(boolean par1, int par2) {
		int var3;
		int var4;

		var3 = this.rand.nextInt(3);

		for (var4 = 0; var4 < var3; var4++) {
			this.dropItem(Items.LEATHER, 1);
		}
	}


	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeEntityToNBT(par1NBTTagCompound);

		par1NBTTagCompound.setInteger("StoneStatus", this.stoneStatus);
		par1NBTTagCompound.setInteger("StoneCounter", this.stoneCounter);
		par1NBTTagCompound.setInteger("RegenTimer", this.regenTimer);
	}


	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readEntityFromNBT(par1NBTTagCompound);

		this.stoneStatus = par1NBTTagCompound.getInteger("StoneStatus");
		this.stoneCounter = par1NBTTagCompound.getInteger("StoneCounter");
		this.regenTimer = par1NBTTagCompound.getInteger("RegenTimer");
	}


	public void setDead() {
		this.regenTimer = -1;

		super.setDead();
	}
}
