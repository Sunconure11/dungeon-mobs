package com.gw.dm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.event.ClickEvent;
import net.minecraft.network.play.client.C01PacketChatMessage;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatStyle;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import cpw.mods.fml.common.gameevent.TickEvent.PlayerTickEvent;
import cpw.mods.fml.common.network.FMLNetworkEvent.ClientConnectedToServerEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class DungeonMobsEventHandler
{
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void doStuff(ClientTickEvent event)
	{
		EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
		
		if(player != null)
		{
			if(player.isPotionActive(DungeonMobs.potionAddle))
			{
				if(!(player.movementInput instanceof InputConfusedMovement))
				{
					DungeonMobs.confusedMovementInput = new InputConfusedMovement(player.movementInput);
					
					int foo = DungeonMobsHelper.getDifficulty(player.worldObj);
					DungeonMobs.confusedMovementInput.setConfValue(foo);
					
					//confusedMovementInput = new InputConfusedMovement(player.movementInput);
					//DungeonMobs.confusedMovementInput.randomize();
					player.movementInput = DungeonMobs.confusedMovementInput;
					DungeonMobs.confusedMovementInput.setConfusion(true);
				}
				else
				{
					DungeonMobs.confusedMovementInput.setConfusion(true);
					//DungeonMobs.confusedMovementInput.randomize();
				}
				
				/*
				if(player.ticksExisted % 40 == 0)
					DungeonMobs.confusedMovementInput.randomize();
				*/
			}
			else if(player.movementInput instanceof InputConfusedMovement)
				DungeonMobs.confusedMovementInput.setConfusion(false);
			
			/*
			ItemStack held = player.getHeldItem();
			
			if(held == null)
				DungeonMobs.confusedMovementInput.setConfusion(false);
			else
				DungeonMobs.confusedMovementInput.setConfusion(true);
			*/
		}
	}
	
	/*
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void versionCheck(ClientConnectedToServerEvent event)
	{
		EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
		
		if(player != null)
		{
			if(DungeonMobs.updateExists == 1)
			{
				player.sendChatMessage("Dungeon Mobs is up to date.");
				
				//player.addChatMessage(new ChatComponentText("Dungeon Mobs is up to date."));
				
				//event.manager.scheduleOutboundPacket(new C01PacketChatMessage("Dungeon Mobs is up to date."));
			}
			else if(DungeonMobs.updateExists == 0)
			{
				event.manager.scheduleOutboundPacket(new C01PacketChatMessage("Dungeon Mobs is out of date! v" + DungeonMobs.updateVersion + " is available for download."));
			}
			else if(DungeonMobs.updateExists == -1)
			{
				event.manager.scheduleOutboundPacket(new C01PacketChatMessage("Dungeon Mobs was unable to check for updates."));
			}
		}
	}
	
	@SubscribeEvent
	public void versionCheck(RenderGameOverlayEvent.Post event) 
	{
		boolean flag = false;
		
		if(event.type == RenderGameOverlayEvent.ElementType.CHAT)
			flag = true;
		
		if(DungeonMobsHelper.displayedVersion == true)
			flag = false;
		
		if(flag)
		{
			EntityClientPlayerMP player = Minecraft.getMinecraft().thePlayer;
			
			if(player != null)
			{
				if(DungeonMobs.updateExists == 1)
				{
					player.addChatMessage(new ChatComponentText("Dungeon Mobs is up to date."));
					DungeonMobsHelper.displayedVersion = true;
					
					//event.manager.scheduleOutboundPacket(new C01PacketChatMessage("Dungeon Mobs is up to date."));
				}
				else if(DungeonMobs.updateExists == 0)
				{
					DungeonMobsHelper.displayedVersion = true;
					//event.manager.scheduleOutboundPacket(new C01PacketChatMessage("Dungeon Mobs is out of date! v" + DungeonMobs.updateVersion + " is available for download."));
				}
				else if(DungeonMobs.updateExists == -1)
				{
					DungeonMobsHelper.displayedVersion = true;
					//event.manager.scheduleOutboundPacket(new C01PacketChatMessage("Dungeon Mobs was unable to check for updates."));
				}
			}
		}
	}
	*/

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onEvent(PlayerTickEvent event)
	{
		if(!DungeonMobs.haveWarnedVersionOutOfDate && event.player.worldObj.isRemote)
		{
			String updateVersion = "";
		    int updateExists;
			
			try
	        {
		    	InputStreamReader fr = new InputStreamReader(new URL("https://dl.dropboxusercontent.com/s/5oukis0s8u04t8v/DungeonMobsVersion.txt").openStream(), "UTF-8");
		    	BufferedReader br = new BufferedReader(fr);
		    	updateVersion = "";
		    	updateVersion = br.readLine().trim();
		    	
		    	String clientVersion = DungeonMobs.VERSION.trim();
		    	
		    	if(updateVersion.compareTo(DungeonMobs.VERSION) == 0)
		    		updateExists = 0;
		    	else
		    		updateExists = 1;
		    }
		    catch (Throwable ex)
		    {
		    	ex.printStackTrace();
		    	updateExists = -1;
		    }
			
			if(updateExists == 1)
			{
				ClickEvent versionCheckChatClickEvent = new ClickEvent(ClickEvent.Action.OPEN_URL, "http://www.curse.com/mc-mods/minecraft/dungeon-mobs");
	
				ChatStyle clickableChatStyle = new ChatStyle().setChatClickEvent(versionCheckChatClickEvent);
	
				ChatComponentText versionWarningChatComponent = new ChatComponentText("Dungeon Mobs has a new update: v[" + updateVersion + "].");
	
				versionWarningChatComponent.setChatStyle(clickableChatStyle);
	
				event.player.addChatMessage(versionWarningChatComponent);
			}
			else if(updateExists == 0)
			{
				ChatComponentText versionWarningChatComponent = new ChatComponentText("Dungeon Mobs is up to date.");
				event.player.addChatMessage(versionWarningChatComponent);
			}
			else
			{
				ChatComponentText versionWarningChatComponent = new ChatComponentText("Dungeon Mobs was unable to check for updates.");
				event.player.addChatMessage(versionWarningChatComponent);
			}
			
			DungeonMobs.haveWarnedVersionOutOfDate = true;
		}
	}
}
