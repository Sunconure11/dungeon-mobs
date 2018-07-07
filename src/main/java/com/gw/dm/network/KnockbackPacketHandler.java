package com.gw.dm.network;

import com.gw.dm.util.DungeonMobsHelper;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class KnockbackPacketHandler implements IMessageHandler<KnockBackPacket, IMessage> {

	@Override
	public IMessage onMessage(KnockBackPacket message, MessageContext ctx) {
		EntityPlayerMP serverPlayer = ctx.getServerHandler().player;
		DungeonMobsHelper.knockBack(serverPlayer, message.getXV(), message.getZV());
		return null;
	}

}
