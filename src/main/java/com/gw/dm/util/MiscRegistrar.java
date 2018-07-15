package com.gw.dm.util;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.potion.Potion;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import com.gw.dm.DungeonMobs;
import com.gw.dm.Items.ItemLavarock;
import com.gw.dm.blocks.BlockBladeTrap;
import com.gw.dm.blocks.BlockLavarock;
import com.gw.dm.blocks.TileEntityBladeTrap;
import com.gw.dm.potion.PotionAddle;

@Mod.EventBusSubscriber
public class MiscRegistrar {

	public static BlockLavarock blockLavarock;
	public static ItemLavarock itemLavarock;
	
	public static BlockBladeTrap blockBladeTrap;
	public static ItemBlock itemBladeTrap;

	public static PotionAddle potionAddle;


	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		event.getRegistry().register(blockLavarock = new BlockLavarock());
		event.getRegistry().register(blockBladeTrap = new BlockBladeTrap());
		registerTileEntities();
	}


	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		event.getRegistry().register(itemLavarock = new ItemLavarock(blockLavarock));
		//event.getRegistry().register(itemBladeTrap = new ItemBlock(blockBladeTrap));
	}


	@SubscribeEvent
	public static void registerPotions(RegistryEvent.Register<Potion> event) {
		event.getRegistry().register(potionAddle = new PotionAddle(true, 0x00000000));
	}


	public static void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityBladeTrap.class, 
				DungeonMobs.MODID + ".bladetrap");
	}

}
