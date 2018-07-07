package com.gw.dm.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class ConfusionPacket implements IMessage {
	private boolean doConfuse;

	public ConfusionPacket() {
	}


	public ConfusionPacket(boolean b) {
		doConfuse = b;
	}


	@Override
	public void fromBytes(ByteBuf bytes) {
		bytes.writeBoolean(this.doConfuse);
	}


	@Override
	public void toBytes(ByteBuf bytes) {
		doConfuse = bytes.readBoolean();
	}


	public boolean getToConfuse() {
		return doConfuse;
	}

}
