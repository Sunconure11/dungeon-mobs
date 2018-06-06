package com.gw.dm.network;


import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;

import java.util.EnumMap;
import java.util.HashSet;

import com.gw.dm.DungeonMobs;

import net.minecraftforge.fml.common.network.FMLEmbeddedChannel;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;

public class NetworkHelper {
	
	// HELP! I think I'll need to set this up as a 1.7.10 
	// project in order to figure out what some of this 
	// was originally supposed to do so I can then figure out 
	// the new way!
	
	public static abstract interface IPacket  {
		public static final SimpleNetworkWrapper NETWRAPPER 
				= NetworkRegistry.INSTANCE.newSimpleChannel(DungeonMobs.MODID);
		
		public abstract void writeBytes(ChannelHandlerContext paramChannelHandlerContext, ByteBuf paramByteBuf);
	    public abstract void readBytes(ChannelHandlerContext paramChannelHandlerContext, ByteBuf paramByteBuf);
	}
	
	private final FMLEmbeddedChannel clientOutboundChannel;
	private final FMLEmbeddedChannel serverOutboundChannel;
	private final HashSet<Class<? extends IPacket>> registeredClasses;
	private boolean isCurrentlySendingSemaphor;
	


	  @SafeVarargs
	public NetworkHelper(String channelName, Class<? extends IPacket> ... handledPacketClasses) {
		// FIXME: Use the modern methods of networking; I don't think ths is even needed!
		EnumMap<Side, FMLEmbeddedChannel> channelPair 
			= NetworkRegistry.INSTANCE.newChannel(channelName, 
				new ChannelCodec(handledPacketClasses), new ChannelHandler());
		  
		clientOutboundChannel = ((FMLEmbeddedChannel)channelPair.get(Side.CLIENT));
		serverOutboundChannel = ((FMLEmbeddedChannel)channelPair.get(Side.SERVER));

		registeredClasses = new HashSet(handledPacketClasses.length);
		for (Class c : handledPacketClasses) {
			registeredClasses.add(c);
		}
	  }

}
