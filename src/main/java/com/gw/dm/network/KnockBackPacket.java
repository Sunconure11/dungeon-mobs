package com.gw.dm.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class KnockBackPacket implements IMessage {
	private float xv;
	private float zv;

	public KnockBackPacket() {
	}


	public KnockBackPacket(float x, float z) {
		this.xv = x;
		this.zv = z;
	}


	@Override
	public void fromBytes(ByteBuf bytes) {
		bytes.writeFloat(this.xv);
		bytes.writeFloat(this.zv);
	}


	@Override
	public void toBytes(ByteBuf bytes) {
		this.xv = bytes.readFloat();
		this.zv = bytes.readFloat();
	}


	public float getXV() {
		return xv;
	}


	public float getZV() {
		return zv;
	}
}
