package com.gw.dm.blocks;

import com.gw.dm.entity.EntityBladeTrap;
import com.gw.dm.util.MiscRegistrar;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class TileEntityBladeTrap extends TileEntity implements ITickable {
	private int spawnTicks = 0;
	private EntityBladeTrap trap;

	public EntityBladeTrap getTrap() {
		if (trap == null) {
			trap = new EntityBladeTrap(world);
		}
		return trap;
	}


	@Override
	public void update() {
		if (spawnTicks < 40)
			spawnTicks++;

		BlockPos pos = getPos();
		int xCoord = pos.getX();
		int yCoord = pos.getY();
		int zCoord = pos.getZ();

		int foo = isPlayerNearby(world, xCoord, yCoord, zCoord);

		if (foo > -1 && spawnTicks > 30) {
			if (world.getBlockState(pos).getBlock() == MiscRegistrar.blockBladeTrap) {
				BlockBladeTrap bar = (BlockBladeTrap) world.getBlockState(pos).getBlock();
				bar.spawnBladeTrapEntity(world, xCoord, yCoord, zCoord, foo);
			}
		}
	}


	public int isPlayerNearby(World w, int x, int y, int z) {
		int sqDist;
		switch (w.getDifficulty()) {
			case EASY:
				sqDist = 400;
				break;
			case HARD:
				sqDist = 1024;
				break;
			case NORMAL:
				sqDist = 576;
				break;
			case PEACEFUL:
				sqDist = 256;
				break;
			default:
				sqDist = 576;
				break;
		}

		List<EntityPlayer> players = new ArrayList<>();
		for (EntityPlayer player : world.playerEntities) {
			if (((player.posX - x) * (player.posX - x)) +
					((player.posY - y) * (player.posY - y)) +
					((player.posZ - z) * (player.posZ - z)) < sqDist) {
				players.add(player);
			}
		}

		List<EntityPlayer> playersCanSee = new ArrayList();
		List<EntityPlayer> playersInLine = new ArrayList();

		if (players.isEmpty()) {
			return -1;
		} else {
			Iterator iter = players.iterator();

			while (iter.hasNext()) {
				EntityPlayer foo = (EntityPlayer) iter.next();

				if (canEntityBeSeen(w, x, y, z, foo) && !foo.capabilities.isCreativeMode)
					playersCanSee.add(foo);
			}
			if (playersCanSee.isEmpty()) {
				return -1;
			}
		}

		Iterator iter = playersCanSee.iterator();

		int thisX = x;
		int thisY = y;
		int thisZ = z;

		while (iter.hasNext()) {
			EntityPlayer foo = (EntityPlayer) iter.next();
			int bar = 0;

			int entX = (int) Math.floor(foo.posX);
			int entY = (int) Math.floor(foo.posY);
			int entZ = (int) Math.floor(foo.posZ);

			if (thisX == entX)
				bar++;

			if (thisY == entY)
				bar++;

			if (thisZ == entZ)
				bar++;

			if (bar > 1)
				playersInLine.add(foo);
		}

		if (playersInLine.isEmpty()) {
			return -1;
		} else {
			Random rand = new Random();

			EntityPlayer select = playersInLine.get(rand.nextInt(playersInLine.size()));

			int entX = (int) Math.floor(select.posX);
			int entY = (int) Math.floor(select.posY);
			int entZ = (int) Math.floor(select.posZ);

			if (thisX != entX) {
				if (thisX > entX) {
					return 2;
				} else {
					return 3;
				}
			} else if (thisY != entY) {
				if (thisY > entY) {
					return 0;
				} else {
					return 1;
				}
			} else {
				if (thisZ > entZ) {
					return 4;
				} else {
					return 5;
				}
			}
		}
	}


	public boolean canEntityBeSeen(World world,
	                               int x, int y, int z, Entity par1Entity) {
		int entX = (int) par1Entity.posX;
		int entY = (int) par1Entity.posY;
		int entZ = (int) par1Entity.posZ;

		int thisX = x;
		int thisY = y;
		int thisZ = z;

		if (entX != thisX) {
			for (int i = thisX + 1; i < entX; i++) {
				if (world.isAirBlock(new BlockPos(i, thisY, thisZ)))
					return false;
			}
		} else if (entY != thisY) {
			for (int i = thisY + 1; i < entY; i++) {
				if (world.isAirBlock(new BlockPos(thisX, i, thisZ)))
					return false;
			}
		} else if (entZ != thisZ) {
			for (int i = thisZ + 1; i < entZ; i++) {
				if (world.isAirBlock(new BlockPos(thisX, thisY, i)))
					return false;
			}
		}
		return true;
	}


	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound par1) {
		return super.writeToNBT(par1);
	}


	@Override
	public void readFromNBT(NBTTagCompound par1) {
		super.readFromNBT(par1);
	}
}
