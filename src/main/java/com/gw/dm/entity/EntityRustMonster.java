package com.gw.dm.entity;

import static com.gw.dm.util.ConfigHandler.rustMonsterIg;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

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
import com.gw.dm.util.ConfigHandler;
import com.gw.dm.util.DungeonMobsHelper;
import com.gw.dm.util.RMFoodItem;

public class EntityRustMonster extends EntityDungeonMob {

	private static String mobName = DungeonMobs.MODID + ":dmrustmonster";

	private static Set<RMFoodItem> foods = new HashSet<RMFoodItem>(((List<RMFoodItem>)
			Arrays.asList(new RMFoodItem[]{
					new RMFoodItem(Item.getItemFromBlock(Blocks.GOLD_ORE)),
					new RMFoodItem(Item.getItemFromBlock(Blocks.IRON_ORE)),
					new RMFoodItem(Item.getItemFromBlock(Blocks.RAIL)),
					new RMFoodItem(Item.getItemFromBlock(Blocks.GOLDEN_RAIL)),
					new RMFoodItem(Item.getItemFromBlock(Blocks.PISTON)),
					new RMFoodItem(Item.getItemFromBlock(Blocks.GOLD_BLOCK)),
					new RMFoodItem(Item.getItemFromBlock(Blocks.IRON_BLOCK)),
					new RMFoodItem(Item.getItemFromBlock(Blocks.IRON_BARS)),
					new RMFoodItem(Item.getItemFromBlock(Blocks.TRIPWIRE_HOOK)),
					new RMFoodItem(Item.getItemFromBlock(Blocks.ACTIVATOR_RAIL)),
					new RMFoodItem(Items.IRON_AXE),
					new RMFoodItem(Items.IRON_DOOR),
					new RMFoodItem(Items.IRON_HOE),
					new RMFoodItem(Items.IRON_HORSE_ARMOR),
					new RMFoodItem(Items.IRON_INGOT),
					new RMFoodItem(Items.IRON_NUGGET),
					new RMFoodItem(Items.IRON_PICKAXE),
					new RMFoodItem(Items.IRON_SHOVEL),
					new RMFoodItem(Items.IRON_SWORD),
					new RMFoodItem(Items.IRON_BOOTS),
					new RMFoodItem(Items.IRON_CHESTPLATE),
					new RMFoodItem(Items.IRON_HELMET),
					new RMFoodItem(Items.IRON_LEGGINGS),
					new RMFoodItem(Items.GOLDEN_AXE),
					new RMFoodItem(Items.GOLDEN_CARROT),
					new RMFoodItem(Items.GOLDEN_APPLE),
					new RMFoodItem(Items.GOLDEN_HOE),
					new RMFoodItem(Items.GOLDEN_HORSE_ARMOR),
					new RMFoodItem(Items.GOLD_INGOT),
					new RMFoodItem(Items.GOLD_NUGGET),
					new RMFoodItem(Items.GOLDEN_PICKAXE),
					new RMFoodItem(Items.GOLDEN_SHOVEL),
					new RMFoodItem(Items.GOLDEN_SWORD),
					new RMFoodItem(Items.GOLDEN_BOOTS),
					new RMFoodItem(Items.GOLDEN_CHESTPLATE),
					new RMFoodItem(Items.GOLDEN_HELMET),
					new RMFoodItem(Items.GOLDEN_LEGGINGS)
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

	public static void setFoods(List<String> list) {
		foods = new HashSet<>();
		appendToFoods(list);
	}

	public static void appendToFoods(List<String> list) {
		for (String rl : list) {
			StringTokenizer tokens = new StringTokenizer(rl, ":");
			Item item = Item.REGISTRY.getObject(new ResourceLocation(tokens.nextToken(),
					tokens.nextToken()));
			if (tokens.hasMoreTokens()) {
				foods.add(new RMFoodItem(item, Integer.parseInt(tokens.nextToken())));
			} else {
				foods.add(new RMFoodItem(item));
			}
		}
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D
				* ConfigHandler.healthx);
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4D);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D 
				* ConfigHandler.damagex + ConfigHandler.damageplus);
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
		if (rustMonsterIg || DungeonMobsHelper.isNearSpawner(world, this, mobName)) {
			return super.getCanSpawnHere();
		}
		if (world.canBlockSeeSky(new BlockPos(posX, posY, posZ)) && !ignoreHeight) {
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


	// Manipulate food list with strings

	@Override
	public boolean attackEntityFrom(DamageSource src, float amount) {
		if (src.getTrueSource() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) src.getTrueSource();
			ItemStack stack = player.getHeldItemMainhand();

			if ((stack != null) && (stack.getItem() != null)
					&& foods.contains(new RMFoodItem(stack.getItem(),
					stack.getItemDamage()))) {
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
					if (foods.contains(new RMFoodItem(armorPiece.getItem()))) {
						int lawlz = getRNG().nextInt(11) + 15;
						armorPiece.damageItem(lawlz, this);
					}
				}
			}
		}
		return super.attackEntityAsMob(ent);
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
