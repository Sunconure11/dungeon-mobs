package com.gw.dm;

import java.util.LinkedList;

import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;

import com.gw.dm.network.ConfusionPacket;
import com.gw.dm.network.KnockBackPacket;
import com.gw.dm.network.NetworkHelper;
//import cpw.mods.fml.common.network.PacketDispatcher;
//import cpw.mods.fml.common.network.Player;

public class DungeonMobsHelper 
{
	//private static Item[] modItems;
	//private static Class[] modEntities;
	
	//private static boolean mobSpawnControls;
	//private static boolean moCreatures;
	
	private static NetworkHelper helper = new NetworkHelper("GW_DM", new Class[] { KnockBackPacket.class, ConfusionPacket.class });
	
	private static LinkedList confusedClientPlayers = new LinkedList<EntityClientPlayerMP>();
	private static LinkedList confusedPlayers = new LinkedList<EntityPlayerMP>();
	
	public static boolean displayedVersion = false;
	
	public static void printLists()
	{
		System.out.println("[DM] Printing " + confusedClientPlayers.size() + " client players.");
		
		for(int i = 0; i < confusedClientPlayers.size(); i++)
		{
			System.out.println("[DM] " + confusedClientPlayers.get(i).toString());
		}
		
		System.out.println("[DM] Printing " + confusedPlayers.size() + " players.");
		
		for(int i = 0; i < confusedPlayers.size(); i++)
		{
			System.out.println("[DM] " + confusedPlayers.get(i).toString());
		}
	}
	
	public static void sendConfusionPacket(EntityPlayerMP target, boolean b)
    {
        Entity test = (Entity)target;
        
        if(!(test instanceof FakePlayer))
        	helper.sendPacketToPlayer(new ConfusionPacket(b), target);
	}
	
	public static void makePlayerConfused(EntityClientPlayerMP player)
	{
		if(!confusedClientPlayers.contains(player))
			confusedClientPlayers.add(player);
	}
	
	public static boolean isPlayerConfused(EntityClientPlayerMP player)
	{
		return confusedClientPlayers.contains(player);
	}
	
	public static void makePlayerNormal(EntityClientPlayerMP player)
	{
		if(confusedClientPlayers.contains(player))
			confusedClientPlayers.remove(player);
	}
	
	/*
	 *  These three are local, don't deal with packet bullshit.
	 */
	public static void makePlayerConfused(EntityPlayerMP player)
	{
		if(!confusedPlayers.contains(player))
			confusedPlayers.add(player);
	}
	
	public static boolean isPlayerConfused(EntityPlayerMP player)
	{
		return confusedPlayers.contains(player);
	}
	
	public static void makePlayerNormal(EntityPlayerMP player)
	{
		if(confusedPlayers.contains(player))
			confusedPlayers.remove(player);
	}
	
	/*
	public static boolean isMobSpawnerNearby(EntityLiving ent)
	{
		/*
		for(int i = (int)ent.posX - 3; i < (int)ent.posX + 3; i++)
		{
			for(int j = (int)ent.posY - 3; j < (int)ent.posY + 3; j++)
			{
				for(int k = (int)ent.posZ - 3; k < (int)ent.posZ + 3; k++)
				{
					if(ent.worldObj.getBlockId(i, j, k) == Block.mobSpawner.blockID)
					{
						if(ent.worldObj.getBlockTileEntity(i, j, k) != null)
						{
							TileEntityMobSpawner foo = (TileEntityMobSpawner)ent.worldObj.getBlockTileEntity(i, j, k);
							
							if(!ent.worldObj.isRemote)
							{
								if(ent.getEntityName().compareTo(foo.getMobEntity().getEntityName()) == 0)
									return true;
							}
						}
					}
				}
			}
		}
		
		
		return false;
	}
	*/
	
	public static void sendKnockBackPacket(EntityPlayerMP target, double xVel, double zVel)
    {
        Entity test = (Entity)target;
        
        if(!(test instanceof FakePlayer))
	        helper.sendPacketToPlayer(new KnockBackPacket((float)xVel, (float)zVel), target);
        
        //helper.sendPacketToPlayer(new KnockBackPacket(xVel, zVel), target);
        
        //PacketDispatcher.sendPacketToPlayer(ForgePacketWrapper.createPacket("GW_DM", 1, toSend), (Player)target);
    }
	
	public static void knockBack(EntityLivingBase target, double x, double z)
	{
        target.isAirBorne = true;
        float normalizedPower = MathHelper.sqrt_double(x * x + z * z);
        float knockPower = 0.8F;
        target.motionX /= 2.0D;
        target.motionY /= 2.0D;
        target.motionZ /= 2.0D;
        target.motionX -= x / (double)normalizedPower * (double)knockPower;
        target.motionY += (double)knockPower;
        target.motionZ -= z / (double)normalizedPower * (double)knockPower;

        if (target.motionY > 0.4000000059604645D)
        {
            target.motionY = 0.4000000059604645D;
        }
    }
	
	/*
	public static void sendStunPacket(EntityPlayer target)
    {
		boolean sure = true;
		Object[] toSend = { sure };
        PacketDispatcher.sendPacketToPlayer(ForgePacketWrapper.createPacket("GW_DM", 2, toSend), (Player)target);
    }
    */
	
	/*
	public static void setItems(Item[] list)
	{
		modItems = list;
	}
	
	public static Item[] getItemList()
	{
		return modItems;
	}
	
	public static void appendToItemsList(Item[] newItems)
	{
		Item[] newList = new Item[modItems.length + newItems.length];
		
		int ctr;
		
		for(ctr = 0; ctr < modItems.length; ctr++)
		{
			newList[ctr] = modItems[ctr];
		}
		
		for(int i = 0; i < newList.length; i++)
		{
			newList[ctr + i] = newItems[i];
		}
		
		modItems = null;
		modItems = newList;
	}
	
	public static Item getItemListAt(int loc)
	{
		return modItems[loc];
	}
	
	public static Item getItemByName(String s)
	{
		for(int i = 0; i < modItems.length; i++)
		{
			if(modItems[i].getUnlocalizedName().compareTo("item." + s) == 0)
				return modItems[i];
		}
		
		return null;
	}
	
	public static int getItemIDByName(String s)
	{
		boolean flag = false;
		
		for(int i = 0; i < modItems.length; i++)
		{
			if(modItems[i].getUnlocalizedName().compareTo("item." + s) == 0)
			{
				flag = true;
				break;
			}
		}
		
		if(!flag)
			return -1;
		else
		{
			for(int i = 0; i < Item.itemsList.length; i++)
			{
				if(Item.itemsList[i].getUnlocalizedName().compareTo("item." + s) == 0)
					return i;
			}
		}
		
		return -1;
	}
	
	public static void setMoCreatures(boolean b)
	{
		moCreatures = b;
	}
	
	public static boolean isMoCreaturesActive()
	{
		return moCreatures;
	}
	
	public static void setEntities(Class[] list)
	{
		modEntities = list;
	}
	
	public static Class[] getEntityList()
	{
		return modEntities;
	}
	
	public static Class getEntityClassAt(int loc)
	{
		return modEntities[loc];
	}
	
	public static void setMSC(boolean foo)
	{
		mobSpawnControls = foo;
	}
	
	public static boolean getMSC()
	{
		return mobSpawnControls;
	}
	*/
	
	public static int getDifficulty(World w)
	{
		if(w.difficultySetting == EnumDifficulty.PEACEFUL)
			return 0;
		if(w.difficultySetting == EnumDifficulty.EASY)
			return 1;
		if(w.difficultySetting == EnumDifficulty.NORMAL)
			return 2;
		if(w.difficultySetting == EnumDifficulty.HARD)
			return 3;
		
		return 0;
	}
}
