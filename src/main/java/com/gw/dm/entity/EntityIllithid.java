package com.gw.dm.entity;

import com.gw.dm.DungeonMobs;
import com.gw.dm.EntityDungeonMob;
import com.gw.dm.util.AudioHandler;
import com.gw.dm.util.DungeonMobsHelper;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.*;

public class EntityIllithid extends EntityDungeonMob {
	public boolean isGrappling;
	private boolean ignoreHeight;
	private int tentacleCounter;
	private EntityLivingBase stunnedEntity;
	private int stunDuration;

	private int mindBlastTicks;

	private List dominatedEntities;
	private Map ownerIDs;
	
	private static String mobName = DungeonMobs.MODID + ":dmillithid";
	

	private EntityAIAttackMelee grapple = new EntityAIAttackMelee(this, 1.0F, false);

	public EntityIllithid(World par1World) {
		super(par1World);
		ignoreHeight = false;

		experienceValue = 50;
		tentacleCounter = 0;

		setSize(1.1F, 2.9F);

		stunnedEntity = null;
		stunDuration = 0;
		mindBlastTicks = 0;

		dominatedEntities = new LinkedList<EntityTameable>();
		ownerIDs = new HashMap<EntityTameable, EntityLivingBase>();

		tasks.addTask(1, new EntityAISwimming(this));
		tasks.addTask(3, new EntityAIWander(this, 1.0F));
		tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		tasks.addTask(5, new EntityAIWatchClosest2(this, EntityIllithid.class, 5.0F, 0.02F));
		tasks.addTask(5, new EntityAIWatchClosest2(this, EntityRakshasa.class, 5.0F, 0.02F));
		tasks.addTask(5, new EntityAILookIdle(this));
		targetTasks.addTask(0, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(1, new EntityAINearestAttackableTarget(this,
				EntityPlayer.class, 0, true, false, null));
	}


	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(45.0D);
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0D);
		getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.0D);
	}


	@Override
	public int getTotalArmorValue() {
		return 5;
	}


	@Override
	public int getTalkInterval() {
		return 180;
	}


	@Override
	protected SoundEvent getAmbientSound() {
		return AudioHandler.entityIllithidAmbient;
	}


	@Override
	protected SoundEvent getHurtSound(DamageSource src) {
		return SoundEvents.ENTITY_ZOMBIE_HURT;
	}


	@Override
	protected SoundEvent getDeathSound() {
		return AudioHandler.entityIllithidDeath;
	}


	@Override
	protected void dropFewItems(boolean par1, int par2) {
		int var3;
		int var4;

		var3 = rand.nextInt(4);

		for (var4 = 0; var4 < var3; var4++) {
			dropItem(Items.SLIME_BALL, 1);
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
		if (posY > 32.0D && !ignoreHeight) {
			return false;
		}
		return super.getCanSpawnHere();
	}


	@Override
	public void onLivingUpdate() {
		if (getAttackTarget() == null) {
			Iterator thralls = dominatedEntities.iterator();

			while (thralls.hasNext()) {
				EntityTameable winner = (EntityTameable) thralls.next();
				winner.setAttackTarget(getAttackTarget());
			}
		}

		mindBlastTicks++;

		if (mindBlastTicks >= (60 - ((DungeonMobsHelper.getDifficulty(world) - 1) * 20))) {
			List tameables = world.getEntitiesWithinAABB(EntityTameable.class,
					getEntityBoundingBox().expand(24.0F, 24.0F, 24.0F));
			List want = new LinkedList<EntityTameable>();

			for (int i = 0; i < tameables.size(); i++) {
				EntityTameable thrall = (EntityTameable) tameables.get(i);

				if (thrall.isTamed() && canEntityBeSeen(thrall)) {
					if (thrall.getOwner() != null)
						want.add(thrall);
				}
			}

			Iterator iter = want.iterator();

			if (iter.hasNext()) {
				EntityTameable target = (EntityTameable) iter.next();

				if (getAttackTarget() != null && getAttackTarget().equals((EntityLivingBase) target)) {
					setAttackTarget(null);
				}

				int ownerID = target.getOwner().getEntityId();

				EntityLivingBase victim = target.getOwner();

				target.setTamed(false);

				target.setRevengeTarget(getAttackTarget());

				if (getAttackTarget() == null) {
					target.setAttackTarget((EntityLivingBase) world.getEntityByID(ownerID));
				} else {
					target.setAttackTarget(getAttackTarget());
				}

				dominatedEntities.add(target);
				ownerIDs.put(target, victim);
			} else {
				if (getAttackTarget() != null) {
					Potion mf = Potion.getPotionFromResourceLocation("mining_fatigue");
					if (!getAttackTarget().isPotionActive(mf)) {
						getAttackTarget().addPotionEffect(new PotionEffect(mf, 240, 3));
					}
					Potion slow = Potion.getPotionFromResourceLocation("slowness");
					if (!getAttackTarget().isPotionActive(slow)) {
						getAttackTarget().addPotionEffect(new PotionEffect(slow, 240, 3));
					}
					if (getAttackTarget() instanceof EntityPlayer)
						stunnedEntity = getAttackTarget();
				}
			}
			mindBlastTicks = 0;
		}

		if (getAttackTarget() == null) {
			stunnedEntity = null;
		}

		if (stunnedEntity != null) {
			tasks.addTask(2, grapple);

			if (!stunnedEntity.isPotionActive(Potion
					.getPotionFromResourceLocation("mining_fatigue"))
					&& !stunnedEntity.isPotionActive(Potion
					.getPotionFromResourceLocation("slowness"))) {
				tasks.removeTask(grapple);
				stunnedEntity = null;
			} else if (stunnedEntity instanceof EntityPlayer) {
				EntityPlayer foo = (EntityPlayer) stunnedEntity;

				if (getEntityBoundingBox().expand(2.0F, 2.0F, 2.0F)
						.intersects(foo.getEntityBoundingBox())) {

					isGrappling = true;
					stunDuration++;

					ItemStack hat = null;
					Iterable<ItemStack> armors = foo.getArmorInventoryList();
					for (ItemStack slot : armors) {
						if ((slot != null) && (slot.getItem() instanceof ItemArmor)) {
							ItemArmor armor = (ItemArmor) slot.getItem();
							if (armor.getEquipmentSlot() == EntityEquipmentSlot.HEAD) {
								hat = slot;
								break;
							}
						}
					}

					if (stunDuration % 30 == 0 && hat == null) {
						tentacleCounter++;

						if (tentacleCounter > 4) {
							tentacleCounter = 4;
						}
					}
				} else {
					tentacleCounter = 0;
					stunDuration = 0;
					isGrappling = false;
				}

				if (tentacleCounter == 4) {
					getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(100.0D);
				} else {
					getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0D);
				}
			}
		}
		super.onLivingUpdate();
	}


	@Override
	public boolean attackEntityAsMob(Entity ent) {
		if (ent instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) ent;

			if (!player.capabilities.isCreativeMode) {
				if (player.inventory.armorInventory.get(3) != null) {
					if (world.rand.nextInt(4 - DungeonMobsHelper.getDifficulty(world)) == 0) {
						this.playSound(AudioHandler.entityIllithidPower,
								1.0F, 1.0F / (getRNG().nextFloat() * 0.4F + 0.8F));
						EntityItem helm = ent.entityDropItem(player.inventory.armorItemInSlot(3), 1.5F);
						player.setItemStackToSlot(EntityEquipmentSlot.HEAD, ItemStack.EMPTY);
					}
				}
			}
		}
		return super.attackEntityAsMob(ent);
	}


	public void stunTarget(EntityPlayer bar) {
		bar.motionX *= 0.0D;
		bar.motionY *= 0.0D;
		bar.motionZ *= 0.0D;
	}


	@Override
	public void onDeath(DamageSource par1DamageSource) {
		Iterator iter1 = dominatedEntities.iterator();

		while (iter1.hasNext()) {
			EntityTameable thrall = (EntityTameable) iter1.next();

			EntityLivingBase bar = (EntityLivingBase) ownerIDs.get(thrall);

			thrall.setOwnerId(bar.getUniqueID());
			thrall.setTamed(true);

			thrall.setAttackTarget(null);
		}

		super.onDeath(par1DamageSource);
	}


	@Override
	public void writeEntityToNBT(NBTTagCompound nbt) {
		super.writeEntityToNBT(nbt);

		nbt.setInteger("TentacleCounter", tentacleCounter);
		nbt.setInteger("MindBlast", mindBlastTicks);
	}


	@Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readEntityFromNBT(par1NBTTagCompound);

		tentacleCounter = par1NBTTagCompound.getInteger("TentacleCounter");
		mindBlastTicks = par1NBTTagCompound.getInteger("MindBlast");
	}
}
