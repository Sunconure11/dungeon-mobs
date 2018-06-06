package com.gw.dm;

import java.util.LinkedList;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;

import com.gw.dm.network.ConfusionPacket;
import com.gw.dm.network.KnockBackPacket;
import com.gw.dm.network.NetworkHelper;

public class DungeonMobsHelper {
	
	private static NetworkHelper helper = NetworkHelper.getNetworkHelper();
	
	private static LinkedList confusedClientPlayers = new LinkedList<EntityPlayerMP>();
	private static LinkedList confusedPlayers = new LinkedList<EntityPlayerMP>();
	
	public static boolean displayedVersion = false;
	
	
	public static void printLists()	{
		System.out.println("[DM] Printing " + confusedClientPlayers.size() + " client players.");
		
		for(int i = 0; i < confusedClientPlayers.size(); i++) {
			System.out.println("[DM] " + confusedClientPlayers.get(i).toString());
		}
		
		System.out.println("[DM] Printing " + confusedPlayers.size() + " players.");
		
		for(int i = 0; i < confusedPlayers.size(); i++) {
			System.out.println("[DM] " + confusedPlayers.get(i).toString());
		}
	}
	
	
	public static void sendConfusionPacket(EntityPlayerMP target, boolean b) {
        Entity test = (Entity)target;
        
        if(!(test instanceof FakePlayer))
        	helper.sendPacketToPlayer(new ConfusionPacket(b), target);
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
		if(!confusedPlayers.contains(player))
			confusedPlayers.add(player);
	}
	
	
	public static boolean isPlayerConfused(EntityPlayerMP player) {
		return confusedPlayers.contains(player);
	}
	
	
	public static void makePlayerNormal(EntityPlayerMP player) {
		if(confusedPlayers.contains(player))
			confusedPlayers.remove(player);
	}

	
	public static void sendKnockBackPacket(EntityPlayerMP target, double xVel, double zVel) {
        Entity test = (Entity)target;
        
        if(!(test instanceof FakePlayer)) {
	        helper.sendPacketToPlayer(new KnockBackPacket((float)xVel, (float)zVel), target);
        }
    }
	
	
	public static void knockBack(EntityLivingBase target, double x, double z) {
        target.isAirBorne = true;
        float normalizedPower = MathHelper.sqrt(x * x + z * z);
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

	
	public static int getDifficulty(World world) {
		if(world.getDifficulty() == EnumDifficulty.PEACEFUL)
			return 0;
		if(world.getDifficulty() == EnumDifficulty.EASY)
			return 1;
		if(world.getDifficulty() == EnumDifficulty.NORMAL)
			return 2;
		if(world.getDifficulty() == EnumDifficulty.HARD)
			return 3;
		
		return 0;
	}
}
