package com.gw.dm.blocks;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.Vec3;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

import com.gw.dm.DungeonMobs;
import com.gw.dm.DungeonMobsHelper;
import com.gw.dm.projectile.EntityBeamosBeam;

public class TileEntityBeamos extends TileEntity 
{
	public boolean isAiming;
	private int beamTicks;
	public EntityPlayer target;
	
	public TileEntityBeamos()
	{
		this.isAiming = false;
		this.beamTicks = 0;
		this.target = null;
	}
	
	public void updateEntity()
	{
		int foo = this.isPlayerNearby(this.worldObj, this.xCoord, this.yCoord, this.zCoord);
		
		if(foo > -1)
		{
			if(this.worldObj.getBlock(this.xCoord, this.yCoord, this.zCoord) == DungeonMobs.beamosBlock)
			{
				if(this.beamTicks < 1 && this.target == null)
				{
					this.worldObj.playSoundEffect((double)this.xCoord + 0.5D, (double)this.yCoord + 0.5D, (double)this.zCoord + 0.5D, "dungeonmobs:be_pl", 1.0F, 1.0F / (this.worldObj.rand.nextFloat() * 0.4F + 0.8F));
					//this.worldObj.playSoundAtEntity((Entity)this, "dungeonmobs:be_pl", 1.0F, 1.0F / (this.worldObj.rand.nextFloat() * 0.4F + 0.8F));
					target = this.getRandomNearbyPlayer(this.worldObj, this.xCoord, this.yCoord, this.zCoord);
					this.fireBeam(target);
					this.beamTicks = 6 - DungeonMobsHelper.getDifficulty(this.worldObj);
				}
				else if(this.beamTicks < 1)
				{
					this.fireBeam(target);
					this.beamTicks = 6 - DungeonMobsHelper.getDifficulty(this.worldObj);
				}
				else
					this.beamTicks--;
			}
		}
		else
			this.target = null;
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
			else
				return 0;
		}
	}
	
	public EntityPlayer getRandomNearbyPlayer(World w, int x, int y, int z)
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
		
		if(players.isEmpty())
			return null;
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
			{
				return playersCanSee.get(w.rand.nextInt(playersCanSee.size()));
			}
			else
				return null;
		}
	}
	
	public boolean canEntityBeSeen(World worldObj, int x, int y, int z, Entity par1Entity)
    {
		return worldObj.rayTraceBlocks(Vec3.createVectorHelper(x, y, z), Vec3.createVectorHelper(par1Entity.posX, par1Entity.posY + (double)par1Entity.getEyeHeight(), par1Entity.posZ)) == null;
    }
	
	public void fireBeam(EntityPlayer player)
	{
		double var11 = player.posX + player.motionX - this.xCoord;
        double var13 = player.boundingBox.minY + player.motionY  + (double)(player.height / 4.0F) - this.yCoord;
        double var15 = player.posZ + player.motionZ  - this.zCoord;
        
        EntityBeamosBeam ray = new EntityBeamosBeam(this.worldObj, this, var11, var13, var15);
        this.worldObj.spawnEntityInWorld(ray);
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
