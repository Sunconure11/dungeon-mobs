package com.gw.dm.network;

import com.gw.dm.util.DungeonMobsHelper;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ConfusionPacketHandler implements IMessageHandler<ConfusionPacket, IMessage> {

	@Override
	public IMessage onMessage(ConfusionPacket message, MessageContext ctx) {
		if (message.getToConfuse()) {
			DungeonMobsHelper.makePlayerConfused(ctx.getServerHandler().player);
		}
		else {
			DungeonMobsHelper.makePlayerNormal(ctx.getServerHandler().player);
		}
		return null;
	}

}
