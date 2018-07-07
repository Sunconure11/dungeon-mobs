package com.gw.dm.util;

import com.gw.dm.Items.ItemLavarock;
import com.gw.dm.blocks.BlockLavarock;
import com.gw.dm.potion.PotionAddle;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class MiscRegistrar {

	public static BlockLavarock blockLavarock;
	public static ItemLavarock itemLavarock;

	public static PotionAddle potionAddle;


	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		event.getRegistry().register(blockLavarock = new BlockLavarock());
		registerTileEntities();
	}


	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().register(itemLavarock = new ItemLavarock(blockLavarock));
	}


	@SubscribeEvent
	public static void registerPotions(RegistryEvent.Register<Potion> event) {
		event.getRegistry().register(potionAddle = new PotionAddle(true, 0x00000000));
	}


	public static void registerTileEntities() {
	}

}
