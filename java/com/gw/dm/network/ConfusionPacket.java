package com.gw.dm.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import com.gw.dm.DungeonMobs;

public class ConfusionPacket implements NetworkHelper.IPacket {	
	private boolean doConfuse;
	  
	public ConfusionPacket() {}

	  
	public ConfusionPacket(boolean b) {
		doConfuse = b;
	}

	@Override
	public void writeBytes(ChannelHandlerContext paramChannelHandlerContext,
				ByteBuf bytes) {
		bytes.writeBoolean(this.doConfuse);		
	}

	@Override
	public void readBytes(ChannelHandlerContext paramChannelHandlerContext,
				ByteBuf bytes) {
		doConfuse = bytes.readBoolean();
	    //DungeonMobs.packetProxy.onConfusionPacket(this.doConfuse);		
	}

}
