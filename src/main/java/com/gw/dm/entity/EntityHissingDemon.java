package com.gw.dm.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.gw.dm.DungeonMobs;
import com.gw.dm.EntityDungeonMob;
import com.gw.dm.ai.AIAngelAttack;
import com.gw.dm.ai.AIAngelWander;
import com.gw.dm.ai.TaskAngelAgroOnPlayer;
import com.gw.dm.util.AudioHandler;
import com.gw.dm.util.ConfigHandler;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer;
import net.minecraft.entity.ai.EntityAIFleeSun;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILeapAtTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIRestrictSun;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityAIZombieAttack;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SPacketAnimation;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class EntityHissingDemon extends EntityDungeonMob implements IMob, IRangedAttackMob, IBeMagicMob  {	
	private static String mobName = DungeonMobs.MODID + ":dmmaralith";
	private final NonNullList<ItemStack> hands = NonNullList.<ItemStack>withSize(6, ItemStack.EMPTY);
	private final List<Integer> handshuffler = new ArrayList<>();
	private int shuffle = 0;

	private static final DataParameter<Boolean> SWINGING_ARMS
			= EntityDataManager.<Boolean>createKey(EntityHissingDemon.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Integer> SWINGING_ARM
			= EntityDataManager.<Integer>createKey(EntityHissingDemon.class, DataSerializers.VARINT);
	private int armInUse = 0;

	public EntityHissingDemon(World worldIn) {
		super(worldIn);
        this.setSize(0.9F, 3.5F);
        this.isImmuneToFire = true;
        this.experienceValue = 50;
        for(int i = 0; i < 6; i++) {
        	handshuffler.add(i);
        }
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public IEntityLivingData onInitialSpawn(DifficultyInstance difficulty, IEntityLivingData livingdata) {
		setHandWeapons(difficulty);
		return super.onInitialSpawn(difficulty, livingdata);
	}


	@Override
	protected void initEntityAI() {
		//TODO: Stand-in code here, replace with specific
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, false));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
		targetTasks.addTask(1, new EntityAIHurtByTarget(this, false, new Class[0]));
        targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
	}


	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(66.0d 
				* ConfigHandler.healthx);
		getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.8d);;
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4d);
		getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(12.0d);
		getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(4.0d);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(9.0d 
				* ConfigHandler.damagex + ConfigHandler.damageplus);
		getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(32.0d);
	}
	

	@Override
	protected SoundEvent getAmbientSound() {
		if(rand.nextBoolean()) {
			return AudioHandler.entityMaralithAmbient1;
		} else {
			return AudioHandler.entityMaralithAmbient2;
		}
	}


	@Override
	protected SoundEvent getHurtSound(DamageSource src) {
		return AudioHandler.entityMaralithHurt;
	}


	@Override
	protected SoundEvent getDeathSound() {
		return AudioHandler.entityMaralithAmbient2;
	}
	
	
	@Override
	public ItemStack getHeldItemMainhand() {
		return hands.get(armInUse);
	}
	
	
	public ItemStack getHeldItem(final int hand) {
		//System.err.println("Item #" + hand  + " requested, returning " + hands.get(hand));
		return hands.get(hand);
	}
	
	
	private void setHandWeapons(DifficultyInstance difficulty) {
		for(int i = 0; i < hands.size(); i++) {
			hands.set(i, getHandWeapon(difficulty));
			//System.err.println(hands.get(i));
		}
	}
	
	
	private ItemStack getHandWeapon(DifficultyInstance difficulty) {
		int die = rand.nextInt(12);
		ItemStack out;
		if(die < 3) {
			if(rand.nextInt(3) < 2) {
				out = new ItemStack(Items.STONE_SWORD);
			} else {
				out = new ItemStack(Items.STONE_AXE);
			}		
		} else if(die < 8) {
			if(rand.nextInt(3) < 2) {
				out = new ItemStack(Items.IRON_SWORD);
			} else {
				out = new ItemStack(Items.IRON_AXE);
			}				
		} else if(die < 10) {
			if(rand.nextInt(3) < 2) {
				out = new ItemStack(Items.GOLDEN_SWORD);
			} else {
				out = new ItemStack(Items.GOLDEN_AXE);
			}		
		} else {
			if(rand.nextInt(3) < 2) {
				out = new ItemStack(Items.DIAMOND_SWORD);
			} else {
				out = new ItemStack(Items.DIAMOND_AXE);
			}		
		}
		float d = difficulty.getClampedAdditionalDifficulty();
		switch(world.getDifficulty()) {
			case EASY:
				if(rand.nextGaussian() < 0.25f * d) {
					out = EnchantmentHelper.addRandomEnchantment(rand, out, 5 + (int)((rand.nextFloat() * d) * 10), false);
				}
				break;
			case HARD:
				if(rand.nextGaussian() < 0.30f * d) {
					out = EnchantmentHelper.addRandomEnchantment(rand, out, 10 + (int)((rand.nextFloat() * d) * 20), false);
				}
				break;
			case NORMAL:
				if(rand.nextGaussian() < 0.35f * d) {
					out = EnchantmentHelper.addRandomEnchantment(rand, out, 10 + (int)((rand.nextFloat() * d) * 15), false);
				}
				break;
			case PEACEFUL:
				break;
			default:
				break;			
		}
		return out;
	}
	
	
	public NonNullList<ItemStack> getHands() {
		return hands;
	}
	
	
	public int getHandToSwing() {
		return armInUse;
	}
	
	
	public void nextHand() {
		shuffle++;
		if(shuffle > handshuffler.size() || shuffle < 0) {
			shuffle = 0;
			Collections.shuffle(handshuffler, rand);
		}
		armInUse = handshuffler.get(shuffle);
		setArmToSwing(armInUse);
	}


	@Override
	public void setSwingingArms(boolean swingingArms) {
		dataManager.set(SWINGING_ARMS, Boolean.valueOf(swingingArms));
	}


	public void setArmToSwing(int arm) {
		dataManager.set(SWINGING_ARM, Integer.valueOf(arm));
	}


	public boolean isMeleeSwinging() {
		return ((Boolean) dataManager.get(SWINGING_ARMS)).booleanValue();
	}


	public int getArmSwinging() {
		return ((Integer) dataManager.get(SWINGING_ARM)).intValue();
	}


	@Override
	public boolean canBreatheUnderwater() {
		return true;
	}


	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {
		// TODO Auto-generated method stub		
	}
	
	@Override
	public void writeEntityToNBT(NBTTagCompound compound) {
		super.writeEntityToNBT(compound);
        NBTTagList nbttaglist1 = new NBTTagList();
        for (ItemStack itemstack1 : hands) {
            NBTTagCompound nbttagcompound1 = new NBTTagCompound();
            if (!itemstack1.isEmpty()) {
                itemstack1.writeToNBT(nbttagcompound1);
            }
            nbttaglist1.appendTag(nbttagcompound1);
        }
        compound.setTag("SixHandItems", nbttaglist1);		
	}
	
	
	@Override
	public void readEntityFromNBT(NBTTagCompound compound) {
		super.readEntityFromNBT(compound);
        if (compound.hasKey("SixItems", 9)) {
            NBTTagList nbttaglist1 = compound.getTagList("HandItems", 10);
            for (int i = 0; i < hands.size(); i++) {
                hands.set(i, new ItemStack(nbttaglist1.getCompoundTagAt(i)));
            }
        }
	}
	
	@Override
	protected void dropEquipment(boolean wasRecentlyHit, int lootingModifier) {
		for(int i = 0; i < hands.size(); i++) {
			ItemStack itemstack = hands.get(i);
	        if (!itemstack.isEmpty() && !EnchantmentHelper.hasVanishingCurse(itemstack) && wasRecentlyHit 
	        			&& (double)(this.rand.nextFloat() - (float)lootingModifier * 0.01f) < 0.5f) {
	            if (itemstack.isItemStackDamageable()) {
	                itemstack.setItemDamage(itemstack.getMaxDamage() - this.rand.nextInt(1 + this.rand.nextInt(Math.max(itemstack.getMaxDamage() - 3, 1))));
	            }
	
	            this.entityDropItem(itemstack, 0.0f);
	        }
		}
		super.dropEquipment(wasRecentlyHit, lootingModifier);
	}

}
