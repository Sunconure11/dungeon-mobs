package com.gw.dm.util;

import com.gw.dm.DungeonMobs;
import com.gw.dm.Items.ItemLavarock;
import com.gw.dm.blocks.BlockBladeTrap;
import com.gw.dm.blocks.BlockLavarock;
import com.gw.dm.blocks.IHaveModel;
import com.gw.dm.blocks.TileEntityBladeTrap;
import com.gw.dm.potion.PotionAddle;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.potion.Potion;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.ArrayList;
import java.util.List;

@Mod.EventBusSubscriber(modid = DungeonMobs.MODID)
public class MiscRegistrar {
	private static final List<Block> BLOCKS = new ArrayList<>();
	private static final List<Item> ITEMS = new ArrayList<>();

	public static BlockLavarock blockLavarock;
	public static ItemLavarock itemLavarock;

	public static BlockBladeTrap blockBladeTrap;
	public static ItemBlock itemBladeTrap;

	public static PotionAddle potionAddle;


	public static void initBlocks() {
		blockLavarock = new BlockLavarock();
		blockBladeTrap = new BlockBladeTrap();
	}


	public static void initItems() {/*Do Nothing! -- at least so far....*/}


	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		IForgeRegistry<Block> regs = event.getRegistry();
		for (Block block : BLOCKS) {
			regs.register(block);
		}
		registerTileEntities();
	}


	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) {
		IForgeRegistry<Item> regs = event.getRegistry();
		for (Item item : ITEMS) {
			regs.register(item);
		}
	}


	@SubscribeEvent
	public static void registerPotions(RegistryEvent.Register<Potion> event) {
		event.getRegistry().register(potionAddle = new PotionAddle(true, 0x00000000));
	}


	@SubscribeEvent
	public static void registerModels(ModelRegistryEvent event) {
		for (Item item : ITEMS) {
			if (item instanceof IHaveModel) {
				((IHaveModel) item).registerModel();
			}
		}
		for (Block block : BLOCKS) {
			if (block instanceof IHaveModel) {
				((IHaveModel) block).registerModel();
			}
		}
	}


	public static void registerTileEntities() {		
		GameRegistry.registerTileEntity(TileEntityBladeTrap.class,
				DungeonMobs.MODID + ".bladetrap");
	}


	public static void addBlock(Block in) {
		BLOCKS.add(in);
	}


	public static void addItem(Item in) {
		ITEMS.add(in);
	}

}
