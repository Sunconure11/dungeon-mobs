package com.gw.dm.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.gw.dm.DungeonMobs;
import com.gw.dm.EntityDungeonMob;
import com.gw.dm.util.AudioHandler;
import com.gw.dm.util.ConfigHandler;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.IMob;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.network.play.server.SPacketAnimation;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

public class EntityHissingDemon extends EntityDungeonMob implements IMob, IRangedAttackMob, IBeMagicMob  {	
	private static String mobName = DungeonMobs.MODID + ":dmmaralith";
	private final ItemStack[] hands = new ItemStack[6];
	private final List<Integer> handshuffler = new ArrayList<>();
	private int shuffle = 0;

	private static final DataParameter<Boolean> SWINGING_ARMS
			= EntityDataManager.<Boolean>createKey(EntityFallenAngel.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Integer> SWINGING_ARM
			= EntityDataManager.<Integer>createKey(EntityFallenAngel.class, DataSerializers.VARINT);
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
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(60.0d 
				* ConfigHandler.healthx);
		getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.8d);;
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5d);
		getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(12.0d);
		getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(8.0d);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(20.0d 
				* ConfigHandler.damagex + ConfigHandler.damageplus);
		getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(6.0d);
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
	
	
	private void equipHands(DifficultyInstance difficulty) {
		for(int i = 0; i < hands.length; i++) {
			hands[i] = getHandWeapon(difficulty);
		}
	}
	
	
	@Override
	public ItemStack getHeldItemMainhand() {
		return hands[armInUse];
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
	
	
	public void nextHand() {
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
	public void attackEntityWithRangedAttack(EntityLivingBase target, float distanceFactor) {
		// TODO Auto-generated method stub		
	}

}
