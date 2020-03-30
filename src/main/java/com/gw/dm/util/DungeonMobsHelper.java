package com.gw.dm.util;

import com.gw.dm.network.ConfusionPacket;
import com.gw.dm.network.KnockBackPacket;
import com.gw.dm.network.NetworkHelper;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;

import java.util.LinkedList;
import java.util.Random;

public class DungeonMobsHelper {
	
	public static boolean displayedVersion = false;
	private static NetworkHelper helper = NetworkHelper.getNetworkHelper();
	private static LinkedList confusedClientPlayers = new LinkedList<EntityPlayerMP>();
	private static LinkedList confusedPlayers = new LinkedList<EntityPlayerMP>();
	
	public static void printLists() {
		System.out.println("[DM] Printing " + confusedClientPlayers.size() + " client players.");
		
		for (int i = 0; i < confusedClientPlayers.size(); i++) {
			System.out.println("[DM] " + confusedClientPlayers.get(i).toString());
		}
		
		System.out.println("[DM] Printing " + confusedPlayers.size() + " players.");
		
		for (int i = 0; i < confusedPlayers.size(); i++) {
			System.out.println("[DM] " + confusedPlayers.get(i).toString());
		}
	}
	
	
	public static void sendConfusionPacket(EntityPlayerMP target, boolean b) {
		Entity test = (Entity) target;
		
		if (!(test instanceof FakePlayer)) helper.sendPacketToPlayer(new ConfusionPacket(b), target);
	}
	
	// FIXME:  I don't know if these are needed with the current networking
	//         System.
	/*
	public static void makePlayerConfused(EntityClientPlayerMP player) {
		if(!confusedClientPlayers.contains(player))
			confusedClientPlayers.add(player);
	}
	
	
	public static boolean isPlayerConfused(EntityClientPlayerMP player)	{
		return confusedClientPlayers.contains(player);
	}
	
	
	public static void makePlayerNormal(EntityClientPlayerMP player) {
		if(confusedClientPlayers.contains(player))
			confusedClientPlayers.remove(player);
	}
	*/
	
	
	/*
	 *  These three are local, don't deal with packet bullshit.
	 */
	public static void makePlayerConfused(EntityPlayerMP player) {
		if (!confusedPlayers.contains(player)) confusedPlayers.add(player);
	}
	
	
	public static boolean isPlayerConfused(EntityPlayerMP player) {
		return confusedPlayers.contains(player);
	}
	
	
	public static void makePlayerNormal(EntityPlayerMP player) {
		if (confusedPlayers.contains(player)) confusedPlayers.remove(player);
	}
	
	
	public static void sendKnockBackPacket(EntityPlayerMP target, double xVel, double zVel) {
		Entity test = (Entity) target;
		
		if (!(test instanceof FakePlayer)) {
			helper.sendPacketToPlayer(new KnockBackPacket((float) xVel, (float) zVel), target);
		}
	}
	
	
	public static void knockBack(EntityLivingBase target, double x, double z) {
		target.isAirBorne = true;
		float normalizedPower = MathHelper.sqrt(x * x + z * z);
		float knockPower = 0.8F;
		target.motionX /= 2.0D;
		target.motionY /= 2.0D;
		target.motionZ /= 2.0D;
		target.motionX -= x / (double) normalizedPower * (double) knockPower;
		target.motionY += (double) knockPower;
		target.motionZ -= z / (double) normalizedPower * (double) knockPower;
		
		if (target.motionY > 0.4000000059604645D) {
			target.motionY = 0.4000000059604645D;
		}
	}
	
	
	public static int getDifficulty(World world) {
		if (world.getDifficulty() == EnumDifficulty.PEACEFUL) return 0;
		if (world.getDifficulty() == EnumDifficulty.EASY) return 1;
		if (world.getDifficulty() == EnumDifficulty.NORMAL) return 2;
		if (world.getDifficulty() == EnumDifficulty.HARD) return 3;
		
		return 0;
	}
	
	
	/**
	 * This method will tell whether a mob is near a spawner for that
	 * type of mob.  This if for spawning logic, to allow mobs to spawn
	 * from spawners that may be in locations where the mobs would not
	 * otherwise be allowed to spawn.
	 * <p>
	 * This might be a bit expensive, I hope not too much.
	 *
	 * @param world
	 * @param entity
	 * @param name
	 * @return
	 */
	public static boolean isNearSpawner(World world, EntityLiving entity, String name) {
		int minx = (int) (entity.posX - 4), maxx = (int) (entity.posX + 5);
		int miny = (int) (entity.posY - 1), maxy = (int) (entity.posY + 2);
		int minz = (int) (entity.posZ - 4), maxz = (int) (entity.posZ + 5);
		boolean out = false;
		BlockPos test;
		
		for (int i = minx; i < maxx; i++)
			for (int j = miny; j < maxy; j++)
				for (int k = minz; k < maxz; k++) {
					test = new BlockPos(i, j, k);
					if (world.getBlockState(test).getBlock() == Blocks.MOB_SPAWNER) {
						TileEntity te = world.getTileEntity(test);
						if (te instanceof TileEntityMobSpawner) {
							try {
								TileEntityMobSpawner mobSpawner = (TileEntityMobSpawner) te;
								NBTTagCompound nbt = new NBTTagCompound();
								mobSpawner.getSpawnerBaseLogic().writeToNBT(nbt);
								if (nbt.hasKey("SpawnData")) {
									NBTTagCompound spawnData = nbt.getCompoundTag("SpawnData");
									if (spawnData.hasKey("id")) {
										String mobSpawnEntityId = spawnData.getString("id");
										String id = (name);
										if (mobSpawnEntityId.equalsIgnoreCase(id)) return true;
									}
								}
							}
							catch (Exception e) {
							}
						}
					}
				}
		return out;
	}
	
	
	/**
	 * A replacement for the vanilla addRandomEnchantment methodm which is
	 * broken in Forge 1.12.2.
	 *
	 * @param stack
	 */
	public static void addEnchantment(ItemStack stack, Random rand) {
		int n = rand.nextInt(2) + rand.nextInt(2) + 1;
		for (int i = 0; i < n; i++) {
			Enchantment ench = Enchantment.REGISTRY.getRandomObject(rand);
			int l = ench.getMaxLevel();
			stack.addEnchantment(ench, l);
		}
	}
	
}
