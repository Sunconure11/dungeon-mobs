package com.gw.dm.network;

import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

import com.gw.dm.DungeonMobsHelper;

public class ConfusionPacketHandler  implements IMessageHandler<ConfusionPacket, IMessage> {

	@Override
	public IMessage onMessage(ConfusionPacket message, MessageContext ctx) {
		  if(message.getToConfuse()) {
			  DungeonMobsHelper.makePlayerConfused(ctx.getServerHandler().player);
		  } else {
			  DungeonMobsHelper.makePlayerNormal(ctx.getServerHandler().player);
		  }
		  return null;
	 }

}
