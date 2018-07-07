package com.gw.dm.network;

import com.gw.dm.DungeonMobs;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

public class ConfusionPacket implements NetworkHelper.IPacket
{
  private boolean doConfuse;

  public ConfusionPacket()
  {
  }

  public ConfusionPacket(boolean b)
  {
    this.doConfuse = b;
  }

  public void writeBytes(ChannelHandlerContext ctx, ByteBuf bytes)
  {
    bytes.writeBoolean(this.doConfuse);
  }

  public void readBytes(ChannelHandlerContext ctx, ByteBuf bytes)
  {
    this.doConfuse = bytes.readBoolean();

    DungeonMobs.packetProxy.onConfusionPacket(this.doConfuse);
  }
}