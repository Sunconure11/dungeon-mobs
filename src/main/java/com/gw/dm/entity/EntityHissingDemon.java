package com.gw.dm.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.gw.dm.DungeonMobs;
import com.gw.dm.EntityDungeonMob;
import com.gw.dm.ai.AIHissingDemonAttack;
import com.gw.dm.util.AudioHandler;
import com.gw.dm.util.ConfigHandler;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Enchantments;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class EntityHissingDemon extends EntityDungeonMob implements IMob, IRangedAttackMob, IBeMagicMob, IAnimatable  {	
	private static String mobName = DungeonMobs.MODID + ":dmmaralith";
    private AnimationFactory factory = new AnimationFactory(this);
	private final NonNullList<ItemStack> hands = NonNullList.<ItemStack>withSize(6, ItemStack.EMPTY);
	private final List<Integer> handshuffler = new ArrayList<>();
	private int shuffle = 0;

	private static final DataParameter<Boolean> SWINGING_ARMS
			= EntityDataManager.<Boolean>createKey(EntityHissingDemon.class, DataSerializers.BOOLEAN);
	private volatile int armInUse = 0, attackNum = -1;
	private static final String[] attacks = new String[] {"animation.hd.swinga1", "animation.hd.swinga2", 
			"animation.hd.swinga3", "animation.hd.swinga4", "animation.hd.swinga5", "animation.hd.swinga6"};
	private AnimationController attackController; 
	private static final String MOVE_ANIM = "animation.hd.tail";
	private AnimationController moveController; 

	public EntityHissingDemon(World worldIn) {
		super(worldIn);
        this.setSize(0.9F, 3.5F);
        this.isImmuneToFire = true;
        this.experienceValue = 50;
        for(int i = 0; i < 6; i++) {
        	handshuffler.add(i);
        }
        this.ignoreFrustumCheck = true;
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
        this.tasks.addTask(2, new AIHissingDemonAttack(this, 1.0d, false));
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
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(1.0d 
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
		return hands.get(hand);
	}
	
	
	private void setHandWeapons(DifficultyInstance difficulty) {
		for(int i = 0; i < hands.size(); i++) {
			hands.set(i, getHandWeapon(difficulty));
			shuffle = 0;
			Collections.shuffle(handshuffler, rand);
			armInUse = handshuffler.get(shuffle);
		}
	}
	
	
	private ItemStack getHandWeapon(DifficultyInstance difficulty) {
		int die = rand.nextInt(10);
		ItemStack out;
		if(die < 2) {
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
		} else if(die < 9) {
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
				if(rand.nextGaussian() < 0.35f * d) {
					out = EnchantmentHelper.addRandomEnchantment(rand, out, 15 + (int)((rand.nextFloat() * d) * 15), false);
				}
				break;
			case NORMAL:
				if(rand.nextGaussian() < 0.30f * d) {
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
	
	
	@Override
	public boolean attackEntityAsMob(Entity entity) {		
		float damage;
		int knockback = 0;
		boolean hit = false;
		attackNum = armInUse;
		ItemStack weaponStack = getHeldItemMainhand();
		Item weapon = weaponStack.getItem();
		boolean isSword = weapon instanceof ItemSword; 
		if(isSword) {
			damage = ((ItemSword)weapon).getAttackDamage() + 1.0f;
		} else {
			damage = 10.0f;
		}
		if(entity instanceof EntityLivingBase) {
			EntityLivingBase victim = (EntityLivingBase)entity;			
	        if (victim instanceof EntityLivingBase) {
	            damage += EnchantmentHelper.getModifierForCreature(this.getHeldItemMainhand(), ((EntityLivingBase)victim).getCreatureAttribute());
	            knockback += EnchantmentHelper.getKnockbackModifier(this);
	        }
	        hit = victim.attackEntityFrom(DamageSource.causeMobDamage(this).setDifficultyScaled(), damage);
	        if(hit) {
	        	int burn;
	        	if((burn = EnchantmentHelper.getMaxEnchantmentLevel(Enchantments.FIRE_ASPECT, victim)) > 0) {
	        		victim.setFire(burn * 4);
	        	}
	            if (victim instanceof EntityPlayer) {
	                EntityPlayer player = (EntityPlayer)victim;
	                ItemStack itemstack = player.isHandActive() ? player.getActiveItemStack() : ItemStack.EMPTY;
	                if(!itemstack.isEmpty() && itemstack.getItem().isShield(itemstack, player)) {
		                if (isSword) {
		                    if (this.rand.nextFloat() < 0.20f) {
		                        player.getCooldownTracker().setCooldown(itemstack.getItem(), 50 + rand.nextInt(50));
		                        world.setEntityState(player, (byte)30);
		                    }
		                } else {
		                    float chance = 0.50F + (float)EnchantmentHelper.getEfficiencyModifier(this) * 0.05F;
		                    if (this.rand.nextFloat() < chance) {
		                        player.getCooldownTracker().setCooldown(itemstack.getItem(), 100 + rand.nextInt(50));
		                        world.setEntityState(player, (byte)30);
		                    }
		                }
	                }	                
	            }
	            applyEnchantments(this, entity);
	        }
		} else {
			hit = entity.attackEntityFrom(DamageSource.causeMobDamage(this), damage);
		}
		nextHand();
		return hit;
	}
	
	
	public void nextHand() {
		shuffle++;
		if(shuffle >= handshuffler.size() || shuffle < 0) {
			shuffle = 0;
			Collections.shuffle(handshuffler, rand);
		}
		armInUse = handshuffler.get(shuffle);
	}


	@Override
	public void setSwingingArms(boolean swingingArms) {
		dataManager.set(SWINGING_ARMS, Boolean.valueOf(swingingArms));
	}


	public boolean isMeleeSwinging() {
		return ((Boolean) dataManager.get(SWINGING_ARMS)).booleanValue();
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
	

    private <E extends IAnimatable> PlayState pmove(AnimationEvent<E> event) {
        event.getController().setAnimation(new AnimationBuilder().addAnimation(MOVE_ANIM, true));
        return PlayState.CONTINUE;
    }
    

    private <E extends IAnimatable> PlayState pattack(AnimationEvent<E> event) {
    	System.out.println("Runngin pattack!, attackNum is " + attackNum + ".");
    	if(attackNum > -1) {
    		event.getController().setAnimation(new AnimationBuilder().addAnimation(attacks[attackNum], true));
    	}
        return PlayState.STOP;
    }


	@Override
	public void registerControllers(AnimationData data) {
		attackController = new AnimationController(this, "attack", 0, this::pattack);
		data.addAnimationController(attackController);
		moveController = new AnimationController(this, "move", 0, this::pmove);
		data.addAnimationController(moveController);
	}


	@Override
	public AnimationFactory getFactory() {
		return factory;
	}

}
