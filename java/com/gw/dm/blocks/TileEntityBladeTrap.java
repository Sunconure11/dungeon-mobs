package com.gw.dm.blocks;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.gw.dm.DungeonMobs;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class TileEntityBladeTrap extends TileEntity
{
	private int spawnTicks = 0;
	
	public void updateEntity()
	{
		if(this.spawnTicks < 40)
			this.spawnTicks++;
		
		int foo = this.isPlayerNearby(this.worldObj, this.xCoord, this.yCoord, this.zCoord);
			
		if(foo > -1 && this.spawnTicks > 30)
		{
			if(this.worldObj.getBlock(this.xCoord, this.yCoord, this.zCoord) == DungeonMobs.bladeTrap)
			{
				BlockBladeTrap bar = (BlockBladeTrap)this.worldObj.getBlock(this.xCoord, this.yCoord, this.zCoord);
				bar.spawnBladeTrapEntity(this.worldObj, this.xCoord, this.yCoord, this.zCoord, foo);
			}
		}
	}
	
	public int isPlayerNearby(World w, int x, int y, int z)
	{
		int dist = 16;
		
		if(w.difficultySetting == EnumDifficulty.EASY)
			dist += 4;
		else if(w.difficultySetting == EnumDifficulty.NORMAL)
			dist += 8;
		else if(w.difficultySetting == EnumDifficulty.HARD)
			dist += 16;
		
		List<EntityPlayer> players = w.getEntitiesWithinAABB(EntityPlayer.class, AxisAlignedBB.getBoundingBox(x - dist, y - dist, z - dist, x + dist, y + dist, z + dist));
		List<EntityPlayer> playersCanSee = new ArrayList();
		List<EntityPlayer> playersInLine = new ArrayList();
		
		if(players.isEmpty())
			return -1;
		else
		{
			Iterator iter = players.iterator();
			
			while(iter.hasNext())
			{
				EntityPlayer foo = (EntityPlayer)iter.next();
				
				if(this.canEntityBeSeen(w, x, y, z, foo) && !foo.capabilities.isCreativeMode)
					playersCanSee.add(foo);
			}
			
			if(playersCanSee.isEmpty())
				return -1;
		}
			
		Iterator iter = playersCanSee.iterator();
		
		int thisX = x;
		int thisY = y;
		int thisZ = z;
		
		while(iter.hasNext())
		{
			EntityPlayer foo = (EntityPlayer)iter.next();
			int bar = 0;
			
			int entX = (int)Math.floor(foo.posX);
			int entY = (int)Math.floor(foo.posY);
			int entZ = (int)Math.floor(foo.posZ);
			
			if(thisX == entX)
				bar++;
			
			if(thisY == entY)
				bar++;
			
			if(thisZ == entZ)
				bar++;
			
			if(bar > 1)
				playersInLine.add(foo);
		}
		
		if(playersInLine.isEmpty())
			return -1;
		else
		{
			Random rand = new Random();
			
			EntityPlayer select = playersInLine.get(rand.nextInt(playersInLine.size()));
			
			int entX = (int)Math.floor(select.posX);
			int entY = (int)Math.floor(select.posY);
			int entZ = (int)Math.floor(select.posZ);
			
			if(thisX != entX)
			{
				if(thisX > entX)
					return 2;
				else
					return 3;
			}
			else if(thisY != entY)
			{
				if(thisY > entY)
					return 0;
				else
					return 1;
			}
			else
			{
				if(thisZ > entZ)
					return 4;
				else
					return 5;
			}
		}
	}
	
	public boolean canEntityBeSeen(World worldObj, int x, int y, int z, Entity par1Entity)
    {
		int entX = (int)par1Entity.posX;
		int entY = (int)par1Entity.posY;
		int entZ = (int)par1Entity.posZ;

		int thisX = x;
		int thisY = y;
		int thisZ = z;
		
		if(entX != thisX)
		{
			for(int i = thisX + 1; i < entX; i++)
			{
				if(worldObj.getBlock(i, thisY, thisZ) != Blocks.air)
					return false;
			}
		}
		else if(entY != thisY)
		{
			for(int i = thisY + 1; i < entY; i++)
			{
				if(worldObj.getBlock(thisX, i, thisZ) != Blocks.air)
					return false;
			}
		}
		else if(entZ != thisZ)
		{
			for(int i = thisZ + 1; i < entZ; i++)
			{
				if(worldObj.getBlock(thisX, thisY, i) != Blocks.air)
					return false;
			}
		}
		/*
		else
			System.out.println("ERROR: BT-01. Please report to GnomeWorks.");
		*/
		
		return true;
    }
	
	public void writeToNBT(NBTTagCompound par1)
	{
		super.writeToNBT(par1);
	}

	public void readFromNBT(NBTTagCompound par1)
	{
		super.readFromNBT(par1);
	}
}
