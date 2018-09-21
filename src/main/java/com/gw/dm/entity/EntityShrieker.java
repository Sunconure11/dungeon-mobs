package com.gw.dm.entity;


import static com.gw.dm.util.ConfigHandler.shriekerIg;

import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

import com.gw.dm.DungeonMobs;
import com.gw.dm.EntityDungeonMob;
import com.gw.dm.util.AudioHandler;
import com.gw.dm.util.DungeonMobsHelper;

public class EntityShrieker extends EntityMob {
	private static ResourceLocation[] entityNames = {
			new ResourceLocation("minecraft", "zombie"),
			new ResourceLocation("minecraft", "skeleton"),
			new ResourceLocation("minecraft", "spider"),
			new ResourceLocation("minecraft", "cave_spider"),
			new ResourceLocation("minecraft", "creeper"),
			new ResourceLocation("minecraft", "witch"),
			new ResourceLocation("minecraft", "enderman")
	};
	private static String mobName = DungeonMobs.MODID + ":shrieker";
	private int screamTicks;
	private boolean hasScreamed;
	private int maxSummoned;
	private int cooldown;


	public EntityShrieker(World par1World) {
		super(par1World);
		experienceValue = 15;
		setSize(1.0F, 1.0F);
		screamTicks = 0;
		maxSummoned = 5;
		cooldown = 0;
	}

	public static void appendToSummonList(String location) {
		StringTokenizer tokens = new StringTokenizer(location, ":");
		String modid = tokens.nextToken(), name;
		if (tokens.hasMoreTokens()) {
			name = tokens.nextToken();
		} else {
			String msg = "Could not add mob "
					+ location + "; name malformed.";
			System.err.println(msg);
			Logger.getLogger("Error").log(Level.WARNING, msg);
			return;
		}
		ResourceLocation[] newlist
				= new ResourceLocation[entityNames.length + 1];
		int ctr;

		for (ctr = 0; ctr < entityNames.length; ctr++) {
			newlist[ctr] = entityNames[ctr];
		}

		newlist[ctr] = new ResourceLocation(modid, name);

		entityNames = null;
		entityNames = newlist;
	}

	private static void appendToSummonListNoGrow(String location, int index) {
		StringTokenizer tokens = new StringTokenizer(location, ":");
		String modid = tokens.nextToken(), name;
		if (tokens.hasMoreTokens()) {
			name = tokens.nextToken();
		} else {
			String msg = "Could not add mob "
					+ location + "; name malformed.";
			System.err.println(msg);
			Logger.getLogger("Error").log(Level.WARNING, msg);
			return;
		}

		entityNames[index] = new ResourceLocation(modid, name);
	}

	public static void appendToSummonList(List<String> locations) {
		ResourceLocation[] newlist
				= new ResourceLocation[entityNames.length + locations.size()];

		int ctr;

		for (ctr = 0; ctr < entityNames.length; ctr++) {
			newlist[ctr] = entityNames[ctr];
		}

		entityNames = newlist;

		for (String location : locations) {
			appendToSummonListNoGrow(location, ctr++);
		}
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(10.0D);
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.0D);
		getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0D);
	}

	@Override
	public int getTotalArmorValue() {
		return 0;
	}


	@Override
	public boolean canBePushed() {
		return false;
	}


	@Override
	public void onLivingUpdate() {
		if (world.isAirBlock(new BlockPos(posX, posY - 1, posZ))) {
			setDead();
		} else {
			float light = getBrightness();
			hasScreamed = false;

			if (light > 0.08F) {
				screamTicks++;

				if (screamTicks % 70 == 0) {
					playSound(AudioHandler.entityShrieker, 1.0f, 1.0f);
					hasScreamed = true;
				}

				if (screamTicks % 150 == 0) {
					if (world.getClosestPlayerToEntity(this, 32.0D) != null)
						summonMobs();
					screamTicks = 0;
				}
			}
		}
		super.onLivingUpdate();
	}


	@Override
	public boolean attackEntityFrom(DamageSource src, float amount) {
		if (src.getTrueSource() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) src.getTrueSource();

			if (!player.capabilities.isCreativeMode) {
				playSound(AudioHandler.entityShrieker, 1.0f, 1.0f);

				int var = 0;

				switch (world.getDifficulty()) {
					case EASY:
						var = 1;
						break;
					case HARD:
						var = 3;
						break;
					case NORMAL:
						var = 2;
						break;
					case PEACEFUL:
					default:
						break;
				}
				if (rand.nextInt(4 - var) == 0)
					summonMobs();
			}
		}
		return super.attackEntityFrom(src, amount);
	}


	@Override
	protected void collideWithEntity(Entity par1Entity) {/*Do Nothing*/}


	@Override
	public void onCollideWithPlayer(EntityPlayer ent) {
		if (!ent.capabilities.isCreativeMode) {
			playSound(AudioHandler.entityShrieker, 1.0f, 1.0f);
			summonMobs();
		}
	}


	@Override
	public void faceEntity(Entity par1Entity, float par2, float par3) {/*Nothing*/}


	@Override
	protected void dropFewItems(boolean par1, int par2) {
		int var3;
		int var4;

		var3 = rand.nextInt(2 + par2);

		for (var4 = 0; var4 < var3; var4++) {
			dropItem(Items.NETHER_WART, 1);
		}
	}


	@Override
	public boolean getCanSpawnHere() {
		if (shriekerIg || DungeonMobsHelper.isNearSpawner(world, this, mobName)) {
			return super.getCanSpawnHere();
		}
		BlockPos here = new BlockPos(posX, posY, posZ);
		if (world.canBlockSeeSky(here)) {
			return false;
		}
		if (posY > 50.0D) {
			return false;
		}
		if (world.getLight(here) > 4) {
			return false;
		}
		return super.getCanSpawnHere();
	}


	protected void summonMobs() {

		int numMobs = rand.nextInt(4) + 1;

		if (world.getDifficulty() == EnumDifficulty.NORMAL) {
			numMobs++;
			cooldown -= 5;
		}
		if (world.getDifficulty() == EnumDifficulty.HARD) {
			numMobs += 3;
			cooldown -= 5;
		}
		if (!world.isRemote) {
			for (int i = 0; i < numMobs; i++) {
				int fubar = rand.nextInt(entityNames.length);

				EntityLiving attracted = (EntityLiving) EntityList
						.createEntityByIDFromName(entityNames[fubar], world);

				if (attracted == null) {
					return;
				}

				double spawnx = posX + (rand.nextDouble() * 8.0D) - (rand.nextDouble() * 8.0D);
				double spawny = posY - 1.0 + (rand.nextDouble() * 3);
				double spawnz = posZ + (rand.nextDouble() * 8.0D) - (rand.nextDouble() * 8.0D);

				if (Math.abs(Math.abs(spawnx) - Math.abs(posX)) < 2) {
					if (spawnx < 0)
						spawnx -= 2.0D;
					else
						spawnx += 2.0D;
				}

				if (Math.abs(Math.abs(spawnz) - Math.abs(posZ)) < 2) {
					if (spawnz < 0)
						spawnz -= 2.0D;
					else
						spawnz += 2.0D;
				}

				makeSummonIgnoreHeight(attracted);

				attracted.setLocationAndAngles(spawnx, spawny + 1, spawnz,
						MathHelper.wrapDegrees(world.rand.nextFloat() * 360.0F), 0.0F);
				attracted.rotationYawHead = attracted.rotationYaw;
				attracted.renderYawOffset = attracted.rotationYaw;

				//FIXME: Mostly spawns mobs into walls!
				if (attracted.getCanSpawnHere() && !world.isRemote && attracted.isNotColliding()) {
					world.spawnEntity(attracted);

					// FIXME: Lizalfos
					//if(attracted instanceof EntityLizalfos)
					//	((EntityLizalfos)attracted).forceTwinSpawn();
				}
			}
		}
	}


	public boolean canAttackClass(Class par1Class) {
		return false;
	}


	private void makeSummonIgnoreHeight(EntityLiving ent) {
		if (ent instanceof EntityDungeonMob) {
			EntityDungeonMob foo = (EntityDungeonMob) ent;
			foo.setIgnoreHeight(true);
		} else if (ent instanceof EntityGhoul) {
			EntityGhoul foo = (EntityGhoul) ent;
			foo.setIgnoreHeight(true);
		}
	}
}
