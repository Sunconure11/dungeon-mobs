package com.gw.dm.entity;

import static com.gw.dm.util.ConfigHandler.rakshasaIg;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackMelee;
import net.minecraft.entity.ai.EntityAIAttackRanged;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import com.gw.dm.DungeonMobs;
import com.gw.dm.EntityDungeonMob;
import com.gw.dm.projectile.EntityMagicMissile;
import com.gw.dm.util.AudioHandler;
import com.gw.dm.util.DungeonMobsHelper;

public class EntityRakshasa extends EntityDungeonMob implements IRangedAttackMob, IBeMagicMob  {

	private static final ResourceLocation rakshasaTextures
			= new ResourceLocation(DungeonMobs.MODID, "textures/entity/Rakshasa.png");
	private static String mobName = DungeonMobs.MODID + ":dmrakshasa";
	public boolean ignoreHeight;
	public String currentName;
	public ResourceLocation currentSkin;
	public boolean hasIllusion;
	private int castingTimer;
	private int hasteTimer;
	private int illusionTimer;
	private int imageTimer;
	private EntityAIAttackRanged rangeAttack
			= new EntityAIAttackRanged(this, 0.3F, 5, 40, 24.0F);
	private EntityAIAttackMelee meleeAttack
			= new EntityAIAttackMelee(this, 0.3F, false);

	private List myImages;


	public EntityRakshasa(World par1World) {
		super(par1World);

		ignoreHeight = false;
		experienceValue = 60;

		castingTimer = 0;
		hasteTimer = 0;
		illusionTimer = 0;
		imageTimer = 0;
		currentName = "Rakshasa";
		currentSkin = rakshasaTextures;
		hasIllusion = false;
		myImages = new LinkedList<EntityRakshasaImage>();

		tasks.addTask(1, new EntityAISwimming(this));
		tasks.addTask(2, rangeAttack);
		tasks.addTask(3, new EntityAIWander(this, 1.0F));
		tasks.addTask(4, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		tasks.addTask(5, new EntityAIWatchClosest2(this, EntityRakshasa.class, 5.0F, 0.02F));
		tasks.addTask(5, new EntityAIWatchClosest2(this, EntityIllithid.class, 5.0F, 0.02F));
		tasks.addTask(5, new EntityAILookIdle(this));
		targetTasks.addTask(0, new EntityAIHurtByTarget(this, false));
		targetTasks.addTask(1, new EntityAINearestAttackableTarget(this,
				EntityPlayer.class, 0, true, false, null));
	}


	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(42.0D);
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.3D);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(6.0D);
		getEntityAttribute(SharedMonsterAttributes.ARMOR).setBaseValue(8.0D);
		getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).setBaseValue(1.0D);
		getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.0D);
	}


	@Override
	public int getTalkInterval() {
		return 80;
	}


	@Override
	protected SoundEvent getAmbientSound() {
		return AudioHandler.entityRakshasaAmbient;
	}


	@Override
	protected SoundEvent getHurtSound(DamageSource src) {
		return AudioHandler.entityRakshasaHurt;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return AudioHandler.entityRakshasaHurt;
	}


	public ResourceLocation getTexture() {
		return currentSkin;
	}


	public String getEntityName() {
		return currentName;
	}


	@Override
	/**
	 * Drop a randomly enchanted book.
	 *
	 * Unlike for many other mobs, this will not be replaced with
	 * the JSON based system, as its needs its own special logic,
	 * notable for enchanting (unless there is more I don't know 
	 * about the new system, or they enhance it).
	 */
	protected void dropFewItems(boolean par1, int par2) {
		int var2 = rand.nextInt(DungeonMobsHelper.getDifficulty(world));
		if (var2 == 0) {
			ItemStack myBook = new ItemStack(Items.ENCHANTED_BOOK, 1);
			//EnchantmentHelper.addRandomEnchantment(world.rand, myBook, 30, true);
			DungeonMobsHelper.addEnchantment(myBook, rand);
			EntityItem itemEnt = new EntityItem(world, posX, posY, posZ, myBook);
			world.spawnEntity(itemEnt);
		}
	}


	@Override
	public boolean getCanSpawnHere() {
		if (rakshasaIg || DungeonMobsHelper.isNearSpawner(world, this, mobName)) {
			return super.getCanSpawnHere();
		}
		if (world.canBlockSeeSky(new BlockPos(posX, posY, posZ))) {
			return false;
		}
		if (posY > 38.0D && !ignoreHeight) {
			return false;
		}
		return super.getCanSpawnHere();
	}


	@Override
	public void attackEntityWithRangedAttack(EntityLivingBase var1, float lol) {
		double var11 = var1.posX + var1.motionX - posX;
		double var13 = var1.getEntityBoundingBox().minY + var1.motionY
				+ var1.height - getEntityBoundingBox().maxY;
		double var15 = var1.posZ + var1.motionZ - posZ;

		EntityMagicMissile mm = new EntityMagicMissile(world, this);
		targetArrow(mm);
		world.spawnEntity(mm);
	}


	protected void targetArrow(EntityArrow entityarrow) {
		EntityLivingBase target = getAttackTarget();
		if (target == null) {
			return;
		}
		double vx = target.posX - posX;
		double vy = target.getEntityBoundingBox().minY
				+ (double) (target.height / 2.0F)
				+ (rand.nextFloat() - 0.5)
				- entityarrow.posY;
		double vz = target.posZ - posZ;
		double dist = (double) MathHelper.sqrt((vx * vx) + (vz * vz));
		entityarrow.shoot(vx, vy + (dist / 5), vz, 1.6F, 0.0f);
	}


	@Override
	public void onLivingUpdate() {
		castingTimer++;

		if (imageTimer > 0) {
			imageTimer--;
			if (imageTimer % 40 == 0) {
				switchPosition();
			}
		}

		if (hasteTimer > 0) {
			if (hasteTimer % 4 == 0) {
				castingTimer++;
			}
			hasteTimer--;
		}

		if (castingTimer >= 120 && !world.isRemote) {
			playSound(AudioHandler.entityRakshasael, 1.0F,
					1.0F / (getRNG().nextFloat() * 0.4F + 0.8F));

			boolean castFlag = false;
			int castSelect;
			int castAttempts = 0;

			Potion spell;

			do {
				castSelect = world.rand.nextInt(5);
				switch (castSelect) {
					case 0:
						spell = Potion.getPotionFromResourceLocation("invisibility");
						if (!isPotionActive(spell)) {
							addPotionEffect(new PotionEffect(spell, 240, 1));
							castFlag = true;
						} else {
							castAttempts++;
						}
						break;
					case 1:
						spell = Potion.getPotionFromResourceLocation("resistance");
						if (!isPotionActive(spell)) {
							addPotionEffect(new PotionEffect(spell, 240, 3));
							castFlag = true;
						} else {
							castAttempts++;
						}
						break;
					case 2:
						spell = Potion.getPotionFromResourceLocation("speed");
						if (!isPotionActive(spell) && hasteTimer < 30) {
							addPotionEffect(new PotionEffect(spell, 240, 2));
							hasteTimer = 240;
							castFlag = true;
						} else {
							castAttempts++;
						}
						break;
					case 3:
						if (imageTimer < 30) {
							createImages();
							castFlag = true;
						} else {
							castAttempts++;
						}
						break;
					case 4:
						if (getAttackTarget() != null) {
							if (canEntityBeSeen(getAttackTarget())) {
								attackEntityWithRangedAttack(getAttackTarget(), 0F);
								castFlag = true;
							} else {
								castAttempts++;
							}
						} else {
							castAttempts++;
						}
						break;
					default:
						castAttempts++;
						break;
				}
			} while (!castFlag && castAttempts < 6);

			castingTimer -= 120;
		}

		super.onLivingUpdate();
	}


	private void createImages() {
		imageTimer = 240;

		int imageAttempts = 0;

		int imageCount = world.rand.nextInt(3) + DungeonMobsHelper.getDifficulty(world);

		for (int i = 0; i < imageCount && imageAttempts < 3 + imageCount; i++) {
			double imageX = posX + (world.rand.nextFloat() * 4.0F);
			double imageY = posY;
			double imageZ = posZ + (world.rand.nextFloat() * 4.0F);

			EntityRakshasaImage bar = new EntityRakshasaImage(world, (EntityLivingBase) getAttackTarget(), this);
			bar.setLocationAndAngles(imageX, imageY, imageZ, rotationYaw, rotationPitch);

			if (bar.getCanSpawnHere() && !world.isRemote) {
				if (!world.isRemote) {
					world.spawnEntity(bar);
					myImages.add(bar);
					if (getAttackTarget() != null)
						bar.setAttackTarget(getAttackTarget());
				} else {
					imageCount--;
					imageAttempts++;
				}
			} else {
				imageCount--;
				imageAttempts++;
			}
		}
	}


	private void switchPosition() {
		if (!world.isRemote) {
			if (!myImages.isEmpty()) {
				EntityRakshasaImage foo = (EntityRakshasaImage) myImages
						.get(world.rand.nextInt(myImages.size()));

				double prevX = posX;
				double prevY = posY;
				double prevZ = posZ;

				posX = foo.posX;
				posY = foo.posY;
				posZ = foo.posZ;

				foo.posX = prevX;
				foo.posY = prevY;
				foo.posZ = prevZ;
			}
		}
	}


	public void destroyImage(EntityRakshasaImage foo) {
		Iterator iter = myImages.iterator();
		while (iter.hasNext()) {
			if (foo.equals((EntityRakshasaImage) iter.next())) {
				myImages.remove(foo);
				break;
			}
		}
	}


	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeEntityToNBT(par1NBTTagCompound);

		par1NBTTagCompound.setInteger("CastTimer", castingTimer);
		par1NBTTagCompound.setInteger("HasteTimer", hasteTimer);
		par1NBTTagCompound.setInteger("ImageTimer", imageTimer);
	}


	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readEntityFromNBT(par1NBTTagCompound);
		castingTimer = par1NBTTagCompound.getInteger("CastTimer");
		hasteTimer = par1NBTTagCompound.getInteger("HasteTimer");
		imageTimer = par1NBTTagCompound.getInteger("ImageTimer");
	}


	@Override
	public void setSwingingArms(boolean swingingArms) {/*Do Nothing*/}
}
