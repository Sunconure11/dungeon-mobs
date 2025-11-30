package com.gw.dm.Items;

import com.gw.dm.DungeonMobs;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;

public class ItemLavarock extends ItemBlock {

	public ItemLavarock(Block block) {
		super(block);
		setRegistryName(new ResourceLocation(DungeonMobs.MODID, "lavarock"));
		setTranslationKey(DungeonMobs.MODID + ".lavarock");
	}

}
