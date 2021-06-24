package com.gw.dm.entity;


import static com.gw.dm.util.ConfigHandler.hookHorrorIg;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

import com.gw.dm.DungeonMobs;
import com.gw.dm.EntityDungeonMob;
import com.gw.dm.util.AudioHandler;
import com.gw.dm.util.ConfigHandler;
import com.gw.dm.util.DungeonMobsHelper;

public class EntityHookHorror extends EntityDungeonMob {
	private static String mobName = DungeonMobs.MODID + ":dmhookhorror";
	private boolean ignoreHeight;

	public EntityHookHorror(World par1World) {
		super(par1World);
		this.experienceValue = 35;
		this.ignoreHeight = false;

		this.setSize(1.0F, 2.8F);

		this.tasks.addTask(1, new EntityAISwimming(this));
		this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0F, false));
		this.tasks.addTask(3, new EntityAIWander(this, 1.0F));
		this.tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(4, new EntityAILookIdle(this));
		this.targetTasks.addTask(0, new EntityAIHurtByTarget(this, false));
		this.targetTasks.addTask(1, new EntityAINearestAttackableTarget(this,
				EntityPlayer.class, 0, true, false, null));
	}


	protected boolean isAIEnabled() {
		return true;
	}


	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(35.0D 
				* ConfigHandler.healthx);
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.28D);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(5.0D 
				* ConfigHandler.damagex + ConfigHandler.damageplus);
		getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(9.0D);
		getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.0D);
	}


	@Override
	protected SoundEvent getAmbientSound() {
		switch (rand.nextInt(5)) {
			case 0:
				return AudioHandler.dmbts;
			case 1:
			case 2:
				return AudioHandler.entityHookHorrorA2;
			case 3:
			case 4:
			default:
				return AudioHandler.entityHookHorrorA1;
		}
	}


	@Override
	protected SoundEvent getHurtSound(DamageSource ds) {
		return AudioHandler.entityHookHorrorHurt;
	}


	@Override
	protected SoundEvent getDeathSound() {
		return AudioHandler.entityHookHorrorHurt;
	}


	@Override
	protected SoundEvent getFallSound(int heightIn) {
		return AudioHandler.dmbts;
	}


	public int getTalkInterval() {
		return 60;
	}


	// TODO: Fix this when moving to the new (JSON based) drop system
	@Override
	protected Item getDropItem() {
		return Item.getItemFromBlock(Blocks.TRIPWIRE_HOOK);
	}


	@Override
	protected void dropFewItems(boolean par1, int par2) {
		int var3;
		int var4;
		int var5;

		var3 = this.rand.nextInt(4);

		for (var5 = 0; var5 < var3; var5++) {
			this.dropItem(Items.BONE, 1);
		}
	}


	@Override
	public boolean getCanSpawnHere() {
		if(hookHorrorIg || DungeonMobsHelper.isNearSpawner(world, this, mobName)) {
			return super.getCanSpawnHere();
		}

		if (world.canBlockSeeSky(new BlockPos(posX, posY, posZ))) {
			return false;
		}

		if (this.posY > 45.0D && !this.ignoreHeight) {
			return false;
		}

		return super.getCanSpawnHere();
	}


	@Override
	public boolean attackEntityAsMob(Entity entity) {
		if (entity instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) entity;

			for (ItemStack armorPiece : player.getArmorInventoryList()) {
				if (armorPiece != null) {
					int lawlz;
					if (armorPiece.isItemEnchanted()) {
						lawlz = rand.nextInt(6) + 5;
					} else {
						lawlz = rand.nextInt(6) + 25;
					}
					armorPiece.damageItem(lawlz, this);
				}
			}

			int moo = 3;

			if (world.getDifficulty() == EnumDifficulty.NORMAL) {
				moo -= 1;
			} else if (world.getDifficulty() == EnumDifficulty.HARD) {
				moo -= 2;
			}

			int bar = rand.nextInt(4 - moo);

			if (bar == 0) {
				ItemStack weapon = player.inventory.getStackInSlot(player.inventory.currentItem);

				if (weapon != null) {
					EntityItem drop = player.dropItem(player.inventory
							.decrStackSize(player.inventory.currentItem, 1), false);

					if (drop != null) {
						drop.setPickupDelay(60);
					}
				}
			}
		}
		return super.attackEntityAsMob(entity);
	}


	@Override
	public void onEntityUpdate() {
		if (hurtTime > 0) {
			int moo = 0;

			if (world.getDifficulty() == EnumDifficulty.EASY)
				moo = 1;
			if (world.getDifficulty() == EnumDifficulty.NORMAL)
				moo = 2;
			if (world.getDifficulty() == EnumDifficulty.HARD)
				moo = 3;

			if (this.rand.nextInt(5) < moo)
				hurtTime -= (moo * 3);
		}

		super.onEntityUpdate();
	}
	

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}
	
}
