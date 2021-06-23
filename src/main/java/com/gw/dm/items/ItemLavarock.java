package com.gw.dm.items;

import com.gw.dm.DungeonMobs;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;

public class ItemLavarock extends ItemBlock {

	public ItemLavarock(Block block) {
		super(block);
		setRegistryName(new ResourceLocation(DungeonMobs.MODID, "lavarock"));
		setUnlocalizedName(DungeonMobs.MODID + ".lavarock");
	}

}
