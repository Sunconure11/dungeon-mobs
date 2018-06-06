package com.gw.dm.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

import com.gw.dm.DungeonMobs;

public class KnockBackPacket {
	  private float xv;
	  private float zv;

	  public KnockBackPacket() {}

	  
	  public KnockBackPacket(float x, float z) {
		  this.xv = x;
		  this.zv = z;
	  }

	  
	  public void writeBytes(ChannelHandlerContext ctx, ByteBuf bytes) {
		  bytes.writeFloat(this.xv);
		  bytes.writeFloat(this.zv);
	  }

	  public void readBytes(ChannelHandlerContext ctx, ByteBuf bytes) {
		  this.xv = bytes.readFloat();
		  this.zv = bytes.readFloat();

		  //DungeonMobs.packetProxy.onKnockBackPacket(this.xv, this.zv);
	  }
}
