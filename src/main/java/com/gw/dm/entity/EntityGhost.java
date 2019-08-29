package com.gw.dm.entity;

import static com.gw.dm.util.ConfigHandler.ghostIg;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWanderAvoidWater;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import com.gw.dm.DungeonMobs;
import com.gw.dm.DungeonMobsDamageSource;
import com.gw.dm.EntityDungeonMob;
import com.gw.dm.util.AudioHandler;
import com.gw.dm.util.ConfigHandler;
import com.gw.dm.util.DungeonMobsHelper;

public class EntityGhost extends EntityDungeonMob  implements IBeMagicMob {
	private static String mobName = DungeonMobs.MODID + ":dmghost";

	public EntityGhost(World worldIn) {
		super(worldIn);
		experienceValue = 20;
	}


	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(35.0d 
				* ConfigHandler.healthx);
		getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE);
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.4d);
		getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(8.0d);
		getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS);
		getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(16.0d);
        getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0d 
				* ConfigHandler.damagex + ConfigHandler.damageplus);
	}

	
    protected void initEntityAI() {
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackMelee(this, 1.0D, false));
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWanderAvoidWater(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.applyEntityAI();
    }

    
    protected void applyEntityAI() {
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, 
        		EntityPlayer.class, true));
    }


	@SideOnly(Side.CLIENT)
	@Override
	public int getBrightnessForRender() {
		return 0xf000f0;
	}
	
	
	@Override
	public boolean canBreatheUnderwater() {
		return true;
	}


	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.UNDEAD;
	}


	@Override
	public boolean getCanSpawnHere() {
		if (world.canSeeSky(new BlockPos(posX, posY, posZ))) {
			return false;
		}
		if (ghostIg || DungeonMobsHelper.isNearSpawner(world, this, mobName)) {
			return super.getCanSpawnHere();
		}
		if (posY > 36.0D && !ignoreHeight) {
			return false;
		}
		return super.getCanSpawnHere();
	}
	
	
	@Override
	protected void damageEntity(DamageSource damageSrc, float damageAmount) {
		if(damageSrc.isMagicDamage() || damageSrc.isCreativePlayer() 
				|| (damageSrc == DungeonMobsDamageSource.LIGHT_BALL)) {
			super.damageEntity(damageSrc, damageAmount);
			return;
		}
		if(damageSrc.isFireDamage() || 
				(damageSrc.getTrueSource() instanceof IBeMagicMob)) {
			super.damageEntity(damageSrc, damageAmount /  2);
			return;
		}
		Entity attacker = damageSrc.getTrueSource();
		if(attacker instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer)attacker;
			ItemStack equipt = player.inventory.mainInventory.get(player.inventory.currentItem);
			if(equipt.isItemEnchanted()) {
				int highest = 0;
				NBTTagList enchants = equipt.getEnchantmentTagList();
				for(NBTBase tag : enchants) {
					NBTTagCompound compound = (NBTTagCompound)tag;
					if(compound.getShort("id") == 17) {
						super.damageEntity(damageSrc, damageAmount);
						return;
					} else {
						int tmp = compound.getShort("lvl");
						if(tmp > highest) {
							highest = tmp;
						}
					}
				}
				super.damageEntity(damageSrc, Math.min(damageAmount 
						+ rand.nextInt(2), highest));
			}
		}
	}

	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		Entity attacker = source.getTrueSource();
		EntityLivingBase target = getAttackTarget();
		if(((target == null) || target.isDead) 
				&& (attacker instanceof EntityLivingBase)) {
			setAttackTarget((EntityLivingBase)attacker);
		}		
		return super.attackEntityFrom(source, amount);
	}
	

	@Override
	protected SoundEvent getAmbientSound() {
		if(rand.nextBoolean()) {
			return AudioHandler.entityGhostAmbient1;
		} else {
			return AudioHandler.entityGhostAmbient2;
		}
	}


	@Override
	protected SoundEvent getHurtSound(DamageSource src) {
		return AudioHandler.entityGhostHurt;
	}


	@Override
	protected SoundEvent getDeathSound() {
		return AudioHandler.entityGhostDeath;
	}


	@Override
	public void onLivingUpdate() {
		if (!world.isRemote) {
			if (world.isDaytime() && (getBrightness() > 0.5f) && world.canSeeSky(getPosition())) {
				setFire(8);
			}
		}
		super.onLivingUpdate();
	}


	@Override
	protected void dropFewItems(boolean par1, int par2) {
		entityDropItem(new ItemStack(Items.DYE, rand.nextInt(4) + par2, 0xf), 0.0f);
		if (par1) {
			if(par2 > 2) {
				dropItem(Items.GLOWSTONE_DUST, rand.nextInt(2));
				dropItem(Items.GHAST_TEAR, rand.nextInt(par2));
			} else {
				dropItem(Items.GHAST_TEAR, rand.nextInt(2));
			}
		} else {
			dropItem(Items.GHAST_TEAR, rand.nextInt(2));
		}
	}
	

}
