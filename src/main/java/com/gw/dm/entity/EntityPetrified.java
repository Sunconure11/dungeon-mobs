package com.gw.dm.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

public class EntityPetrified extends EntityLiving {
	public ItemStack[] stuff;

	public EntityPetrified(World par1World) {
		super(par1World);
		experienceValue = 0;
		stuff = null;
		isImmuneToFire = true;
	}


	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.0D);
		getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(100.0D);
	}


	@Override
	public void jump() {/*Do Nothing*/}


	@Override
	public int getTotalArmorValue() {
		return 0;
	}


	@Override
	public boolean getCanSpawnHere() {
		return super.getCanSpawnHere();
	}


	@Override
	protected SoundEvent getHurtSound(DamageSource src) {
		return SoundEvents.BLOCK_STONE_HIT;
	}


	@Override
	protected SoundEvent getAmbientSound() {
		return null;
	}


	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.BLOCK_STONE_BREAK;
	}


	public void setStuff(EntityPlayer player) {
		stuff = new ItemStack[40];

		for (int i = 0; i < 40; i++) {
			if (player.inventory.getStackInSlot(i) != null)
				stuff[i] = player.inventory.getStackInSlot(i);
		}

		player.inventory = new InventoryPlayer(player);
	}


	@Override
	protected void dropFewItems(boolean par1, int par2) {
		if (stuff != null) {
			for (int i = 0; i < 40; i++) {
				if (stuff[i] != null) {
					EntityItem itemEnt = new EntityItem(world, posX, posY, posZ, stuff[i]);
					world.spawnEntity(itemEnt);
				}
			}
		}
	}


	@Override
	public boolean canBreatheUnderwater() {
		return true;
	}


	@Override
	protected boolean canDespawn() {
		return false;
	}


	@Override
	public void faceEntity(Entity par1Entity, float par2, float par3) {/*Do Nothing*/}


	public boolean isPotionApplicable(PotionEffect par1PotionEffect) {
		return false;
	}


	public void knockBack(Entity par1Entity, int par2,
	                      double par3, double par5) {/*Do Nothing*/}


	@Override
	public boolean attackEntityFrom(DamageSource src, float amount) {
		if (src.getTrueSource() instanceof EntityPlayer) {
			if (((EntityPlayer) src.getTrueSource())
					.getHeldItemMainhand()
					.getItem()
					.canHarvestBlock(Blocks.STONE.getDefaultState())) {
				return super.attackEntityFrom(src, amount);
			}
		}
		return super.attackEntityFrom(src, amount / 10);
	}


	@Override
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeEntityToNBT(par1NBTTagCompound);

		NBTTagList nbttaglist = new NBTTagList();

		for (int i = 0; i < stuff.length; ++i) {
			if (stuff[i] != null) {
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				stuff[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}

		par1NBTTagCompound.setTag("Items", nbttaglist);
	}


	@Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readEntityFromNBT(par1NBTTagCompound);

		NBTTagList nbttaglist = par1NBTTagCompound.getTagList("Items", 10);
		stuff = new ItemStack[40];

		for (int i = 0; i < nbttaglist.tagCount(); ++i) {
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			int j = nbttagcompound1.getByte("slot") & 255;

			if (j >= 0 && j < stuff.length) {
				stuff[j] = new ItemStack(nbttagcompound1);
			}
		}
	}
}
