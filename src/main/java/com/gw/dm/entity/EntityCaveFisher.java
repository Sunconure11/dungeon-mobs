package com.gw.dm.entity;

import com.gw.dm.util.AudioHandler;
import com.gw.dm.util.DungeonMobsHelper;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;

import java.util.Iterator;
import java.util.List;


public class EntityCaveFisher extends EntitySpider {

	public boolean ignoreHeight;
	public float myAngles[] = new float[4];
	private int stringTimer;
	private int grabTimer;
	private EntityPlayer myTarget;
	// prev left, prev right, moar prev left, moar prev right

	public EntityCaveFisher(World par1World) {
		super(par1World);
		setSize(1.4F, 0.9F);
		experienceValue = 20;
		stringTimer = 0;
		grabTimer = 0;
		ignoreHeight = false;

		for (int i = 0; i < 4; i++) {
			myAngles[i] = 0.0F;
		}
	}


	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(30.0D);
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.6D);
		getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE).setBaseValue(4.0D);
		getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(0.0D);
	}


	@Override
	public int getTotalArmorValue() {
		return 8;
	}


	public int getAttackStrength(Entity entity) {
		return 4;
	}


	@Override
	protected SoundEvent getAmbientSound() {
		return AudioHandler.entityCaveFisherAmbient;
	}


	@Override
	protected SoundEvent getHurtSound(DamageSource src) {
		return AudioHandler.entityCaveFisherHurt;
	}


	@Override
	protected SoundEvent getDeathSound() {
		return AudioHandler.entityCaveFisherDeath;
	}

	@Override
	public int getTalkInterval() {
		return 60;
	}

	@Override
	public EnumCreatureAttribute getCreatureAttribute() {
		return EnumCreatureAttribute.ARTHROPOD;
	}


	@Override
	public boolean getCanSpawnHere() {
		if (DungeonMobsHelper.isNearSpawner(world, this)) {
			return super.getCanSpawnHere();
		}
		if (world.canBlockSeeSky(new BlockPos(posX, posY, posZ))) {
			return false;
		}
		if (posY > 48.0D && !ignoreHeight) {
			return false;
		}
		return super.getCanSpawnHere();
	}


	@Override
	protected void dropFewItems(boolean par1, int par2) {
		int var3;
		int var4;

		var3 = rand.nextInt(5) + 1;

		for (var4 = 0; var4 < var3; var4++) {
			dropItem(Items.STRING, 1);
		}
	}


	protected Entity findPlayerToAttack() {
		double var2 = 16.0D;

		EntityPlayer foo = world.getClosestPlayerToEntity(this, var2);

		if (foo == null) {
			List bar = world.getEntitiesWithinAABB(EntityBat.class,
					getEntityBoundingBox().expand(16.0F, 8.0F, 16.0F));
			Iterator iter = bar.iterator();

			if (iter.hasNext())
				return (Entity) iter.next();

			bar = null;
		}

		return foo;
	}


	public void onLivingUpdate() {
		stringTimer++;

		int foo = 160 - (DungeonMobsHelper.getDifficulty(world) * 20);

		if (stringTimer - foo > 0) {
			if (getAttackTarget() != null && canEntityBeSeen(getAttackTarget())) {
				int tarX = (int) getAttackTarget().posX;
				int tarY = (int) (getAttackTarget().posY + 0.5);
				int tarZ = (int) getAttackTarget().posZ;

				if (world.isAirBlock(new BlockPos(tarX, tarY, tarZ))) {
					world.setBlockState(new BlockPos(tarX, tarY, tarZ),
							Blocks.WEB.getDefaultState(), 3);

					if (DungeonMobsHelper.getDifficulty(world) > 2) {
						if (world.isAirBlock(new BlockPos(tarX - 1, tarY, tarZ))) {
							world.setBlockState(new BlockPos(tarX - 1, tarY, tarZ),
									Blocks.WEB.getDefaultState(), 3);
						}
						if (world.isAirBlock(new BlockPos(tarX + 1, tarY, tarZ))) {
							world.setBlockState(new BlockPos(tarX + 1, tarY, tarZ),
									Blocks.WEB.getDefaultState(), 3);
						}
						if (world.isAirBlock(new BlockPos(tarX, tarY, tarZ - 1))) {
							world.setBlockState(new BlockPos(tarX, tarY, tarZ - 1),
									Blocks.WEB.getDefaultState(), 3);
						}
						if (world.isAirBlock(new BlockPos(tarX, tarY, tarZ + 1))) {
							world.setBlockState(new BlockPos(tarX, tarY, tarZ + 1),
									Blocks.WEB.getDefaultState(), 3);
						}
						if (world.isAirBlock(new BlockPos(tarX, tarY + 1, tarZ))) {
							world.setBlockState(new BlockPos(tarX, tarY + 1, tarZ),
									Blocks.WEB.getDefaultState(), 3);
						}
					}
				}
			} else {
				int tarX = (int) posX;
				int tarY = (int) (posY + 0.5);
				int tarZ = (int) posZ;

				if (world.isAirBlock(new BlockPos(tarX, tarY, tarZ))) {
					world.setBlockState(new BlockPos(tarX, tarY, tarZ),
							Blocks.WEB.getDefaultState(), 3);
				}
				if (world.isAirBlock(new BlockPos(tarX - 1, tarY, tarZ))) {
					world.setBlockState(new BlockPos(tarX - 1, tarY, tarZ),
							Blocks.WEB.getDefaultState(), 3);
				}
				if (world.isAirBlock(new BlockPos(tarX + 1, tarY, tarZ))) {
					world.setBlockState(new BlockPos(tarX + 1, tarY, tarZ),
							Blocks.WEB.getDefaultState(), 3);
				}
				if (world.isAirBlock(new BlockPos(tarX, tarY, tarZ - 1))) {
					world.setBlockState(new BlockPos(tarX, tarY, tarZ - 1),
							Blocks.WEB.getDefaultState(), 3);
				}
				if (world.isAirBlock(new BlockPos(tarX, tarY, tarZ + 1))) {
					world.setBlockState(new BlockPos(tarX, tarY, tarZ + 1),
							Blocks.WEB.getDefaultState(), 3);
				}
				if (world.isAirBlock(new BlockPos(tarX, tarY + 1, tarZ))) {
					world.setBlockState(new BlockPos(tarX, tarY + 1, tarZ),
							Blocks.WEB.getDefaultState(), 3);
				}
			}

			stringTimer = 0;
		}

		if (getAttackTarget() != null && canEntityBeSeen(getAttackTarget())) {
			grabTimer++;

			int bork = rand.nextInt(4 - DungeonMobsHelper.getDifficulty(world));

			if (grabTimer >= (foo * 3)) {
				if (bork == 0) {
					double difX = getAttackTarget().posX - posX;
					double difZ;
					for (difZ = getAttackTarget().posZ - posZ;
					     difX * difX + difZ * difZ < 1.0E-4D;
					     difZ = (Math.random() - Math.random()) * 0.01D) {
						difX = (Math.random() - Math.random()) * 0.01D;
					}

					if (!(getAttackTarget() instanceof EntityPlayer)) {
						grabTarget(getAttackTarget(), difX, difZ);
					} else {
						EntityPlayer crap = (EntityPlayer) getAttackTarget();

						if (!crap.capabilities.isCreativeMode) {
							//if(FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT)
							if (!(getAttackTarget() instanceof EntityPlayerMP)) {
								int moo = FMLClientHandler.instance().getClient().player.getEntityId();
								int cow = getAttackTarget().getEntityId();

								if (moo == cow) {
									grabTarget(FMLClientHandler.instance().getClient().player,
											difX, difZ);
								}
							} else {
								DungeonMobsHelper.sendKnockBackPacket((EntityPlayerMP) getAttackTarget(),
										difX, difZ);
							}
						}
					}
				}

				grabTimer = 0;
			}
		}

		super.onLivingUpdate();
	}


	public void grabTarget(EntityOtherPlayerMP ent, double x, double z) {
		ent.isAirBorne = true;
		float var7 = MathHelper.sqrt(x * x + z * z);
		float var8 = 1.0F;
		ent.motionX /= 2.0D;
		ent.motionY /= 2.0D;
		ent.motionZ /= 2.0D;
		ent.motionX -= x / (double) var7 * (double) var8;
		ent.motionY += (double) var8;
		ent.motionZ -= z / (double) var7 * (double) var8;

		if (ent.motionY > 0.4000000059604645D) {
			ent.motionY = 0.4000000059604645D;
		}
	}


	public void grabTarget(EntityPlayerMP ent, double x, double z) {
		// PURE!
		ent.isAirBorne = true;
		float var7 = MathHelper.sqrt(x * x + z * z);
		float var8 = 1.0F;
		ent.motionX /= 2.0D;
		ent.motionY /= 2.0D;
		ent.motionZ /= 2.0D;
		ent.motionX -= x / (double) var7 * (double) var8;
		ent.motionY += (double) var8;
		ent.motionZ -= z / (double) var7 * (double) var8;

		if (ent.motionY > 0.4000000059604645D) {
			ent.motionY = 0.4000000059604645D;
		}
	}


	public void grabTarget(Entity ent, double x, double z) {
		ent.isAirBorne = true;
		float var7 = MathHelper.sqrt(x * x + z * z);
		float var8 = 1.0F;
		ent.motionX /= 2.0D;
		ent.motionY /= 2.0D;
		ent.motionZ /= 2.0D;
		ent.motionX -= x / (double) var7 * (double) var8;
		ent.motionY += (double) var8;
		ent.motionZ -= z / (double) var7 * (double) var8;

		if (ent.motionY > 0.4000000059604645D) {
			ent.motionY = 0.4000000059604645D;
		}
	}


	@Override
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeEntityToNBT(par1NBTTagCompound);

		par1NBTTagCompound.setInteger("StringTimer", stringTimer);
		par1NBTTagCompound.setInteger("GrabTimer", grabTimer);
	}


	@Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readEntityFromNBT(par1NBTTagCompound);

		stringTimer = par1NBTTagCompound.getInteger("StringTimer");
		grabTimer = par1NBTTagCompound.getInteger("GrabTimer");
	}


	@Override
	public boolean attackEntityFrom(DamageSource src, float amount) {
		if (src.getTrueSource() instanceof EntityLivingBase) {
			setAttackTarget((EntityLivingBase) src.getTrueSource());
		}
		return super.attackEntityFrom(src, amount);
	}
}
