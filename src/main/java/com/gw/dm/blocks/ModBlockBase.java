package com.gw.dm.blocks;

import com.gw.dm.DungeonMobs;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;

public abstract class ModBlockBase extends Block implements IHaveModel {

	public ModBlockBase(Material materialIn) {
		super(materialIn);
	}


	@Override
	public void registerModel() {
		DungeonMobs.proxy.registerItemRender(Item.getItemFromBlock(this), 0, "inventory");
	}

}
