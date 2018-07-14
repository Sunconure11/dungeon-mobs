package com.gw.dm.entity;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;

import com.gw.dm.DungeonMobs;
import com.gw.dm.EntityDungeonMob;
import com.gw.dm.ai.EntityAIAttackAnythingWanted;
import com.gw.dm.ai.EntityAIFindItem;
import com.gw.dm.util.AudioHandler;
import com.gw.dm.util.DungeonMobsHelper;

public class EntityRustMonster extends EntityDungeonMob {
	
	private static String mobName = DungeonMobs.MODID + ":dmrustmonster";

	private static Set<Item> foods = new HashSet<Item>(((List<Item>) Arrays.asList(new Item[]{
			Item.getItemFromBlock(Blocks.GOLD_ORE),
			Item.getItemFromBlock(Blocks.IRON_ORE),
			Item.getItemFromBlock(Blocks.RAIL),
			Item.getItemFromBlock(Blocks.GOLDEN_RAIL),
			Item.getItemFromBlock(Blocks.PISTON),
			Item.getItemFromBlock(Blocks.GOLD_BLOCK),
			Item.getItemFromBlock(Blocks.IRON_BLOCK),
			Item.getItemFromBlock(Blocks.IRON_BARS),
			Item.getItemFromBlock(Blocks.TRIPWIRE_HOOK),
			Item.getItemFromBlock(Blocks.ACTIVATOR_RAIL),
			Items.IRON_AXE,
			Items.IRON_DOOR,
			Items.IRON_HOE,
			Items.IRON_HORSE_ARMOR,
			Items.IRON_INGOT,
			Items.IRON_NUGGET,
			Items.IRON_PICKAXE,
			Items.IRON_SHOVEL,
			Items.IRON_SWORD,
			Items.IRON_BOOTS,
			Items.IRON_CHESTPLATE,
			Items.IRON_HELMET,
			Items.IRON_LEGGINGS,
			Items.GOLDEN_AXE,
			Items.GOLDEN_CARROT,
			Items.GOLDEN_APPLE,
			Items.GOLDEN_HOE,
			Items.GOLDEN_HORSE_ARMOR,
			Items.GOLD_INGOT,
			Items.GOLD_NUGGET,
			Items.GOLDEN_PICKAXE,
			Items.GOLDEN_SHOVEL,
			Items.GOLDEN_SWORD,
			Items.GOLDEN_BOOTS,
			Items.GOLDEN_CHESTPLATE,
			Items.GOLDEN_HELMET,
			Items.GOLDEN_LEGGINGS
	})));

	private Entity myTarget;
	private boolean pissed;
	private boolean ignoreHeight;


	public EntityRustMonster(World par1World) {
		super(par1World);
		setSize(1.4F, 1.4F);
		experienceValue = 20;
		ignoreHeight = false;
		pissed = false;
		tasks.addTask(1, new EntityAISwimming(this));
		tasks.addTask(2, new EntityAIAttackAnythingWanted(this, Entity.class, 1.0F, false));
		tasks.addTask(3, new EntityAIWander(this, 1.0D));
		targetTasks.addTask(0, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(2, new EntityAIFindItem(this, 24, foods));
	}
	

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4D);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
		getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.0D);
	}

	@Override
	public int getTotalArmorValue() {
		return 9;
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return AudioHandler.entityRustMonsterAmbient;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource src) {
		return AudioHandler.entityRustMonsterHurt;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return AudioHandler.entityRustMonsterDeath;
	}

	protected void dropFewItems(boolean par1, int par2) {
		if (par1) {
			dropItem(Items.COAL, rand.nextInt(2) + par2);
		}
	}

	@Override
	public boolean getCanSpawnHere() {
		if (DungeonMobsHelper.isNearSpawner(world, this, mobName)) {
			return super.getCanSpawnHere();
		}
		if (world.canBlockSeeSky(new BlockPos(posX, posY, posZ))) {
			return false;
		}

		if (!ignoreHeight) {
			for (int i = -24; i < 24; i++)
				for (int j = -24; j < 24; j++)
					for (int k = -24; k < 24; k++) {
						int checkX = (int) posX + i;
						int checkY = (int) posY + j;
						int checkZ = (int) posZ + k;

						if (checkY < 1)
							checkY = 1;

						BlockPos where = new BlockPos(checkX, checkY, checkZ);
						if (world.getBlockState(where).getBlock() == Blocks.IRON_ORE)
							return super.getCanSpawnHere();

						if (world.getBlockState(where).getBlock() == Blocks.GOLD_ORE)
							return super.getCanSpawnHere();
					}
			return false;
		} else return super.getCanSpawnHere();
	}

	@Override
	public int getTalkInterval() {
		return 120;
	}

	@Override
	protected void jump() {
		motionY = 0.4D;

		if (isPotionActive(Potion.getPotionFromResourceLocation("jump_boost")))
			motionY += (double) ((float) (getActivePotionEffect(Potion
					.getPotionFromResourceLocation("jump_boost"))
					.getAmplifier() + 1) * 0.1F);

		if (isSprinting()) {
			float var1 = rotationYaw * 0.017453292F;
			motionX -= (double) (MathHelper.sin(var1) * 0.2F);
			motionZ += (double) (MathHelper.cos(var1) * 0.2F);
		}

		isAirBorne = true;
		ForgeHooks.onLivingJump(this);
	}

	@Override
	public boolean attackEntityFrom(DamageSource src, float amount) {
		if (src.getTrueSource() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) src.getTrueSource();
			ItemStack stack = player.getHeldItemMainhand();

			if ((stack != null) && (stack.getItem() != null)
					&& foods.contains(stack.getItem())) {
				if (player.getHeldItemMainhand().isItemStackDamageable()) {
					int yar = rand.nextInt(11) + 15;
					player.getHeldItemMainhand().damageItem(yar, this);
				} else {
					int numToDestroy = getRNG().nextInt(6) + 1;
					for (int m = 0; m < numToDestroy; m++) {
						if (stack.getCount() == 1) {
							m = numToDestroy;
						}
						stack.setCount(stack.getCount() - 1);
					}
				}
			}
		}
		return super.attackEntityFrom(src, amount);
	}

	@Override
	public boolean attackEntityAsMob(Entity ent) {
		if (ent instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) ent;
			Iterable<ItemStack> armors = player.getArmorInventoryList();

			for (ItemStack armorPiece : armors) {
				if ((armorPiece != null) && (armorPiece.getItem() != null)) {
					if (foods.contains(armorPiece.getItem())) {
						int lawlz = getRNG().nextInt(11) + 15;
						armorPiece.damageItem(lawlz, this);
					}
				}
			}
		}
		return super.attackEntityAsMob(ent);
	}

	// Manipulate food list with items
	
	public void setFoods(Set<Item> list) {
		foods = list;
	}

	public static void appendToFoods(Item single) {
		foods.add(single);
	}

	public static void appendToFoods(Collection<Item> list) {
		foods.addAll(list);
	}

	public static void appendToFoods(Item[] list) {
		foods.addAll((List<Item>) Arrays.asList(list));
	}
	
	// Manipulate food list with strings
	
	public static void setFoods(List<String> list) {
		Set<Item> set = new HashSet<>();
		for(String rl : list) {
			set.add(Item.REGISTRY.getObject(new ResourceLocation(rl)));
		}
		foods = set;
	}
	
	
	public static void appendToFoods(String single) {
		foods.add(Item.REGISTRY.getObject(new ResourceLocation(single)));
	}
	
	public static void appendToFoods(List<String> list) {
		Set<Item> set = new HashSet<>();
		for(String rl : list) {
			set.add(Item.REGISTRY.getObject(new ResourceLocation(rl)));
		}
		foods.addAll(set);
	}

	public Entity getTarget() {
		if (pissed) {
			return this.getAttackTarget();
		}
		if (getAttackTarget() == null) {
			return this.myTarget;
		} else {
			return this.getAttackTarget();
		}
	}


	public void setTarget(Entity targetEntity, boolean livingFlag) {
		myTarget = targetEntity;
	}
}
