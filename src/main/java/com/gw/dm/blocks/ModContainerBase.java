package com.gw.dm.blocks;

import com.gw.dm.DungeonMobs;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public abstract class ModContainerBase extends BlockContainer implements IHaveModel {

	protected ModContainerBase(Material materialIn) {
		super(materialIn);
	}

	@Override
	public void registerModel() {
		DungeonMobs.proxy.registerItemRender(Item
				.getItemFromBlock(this), 0, "inventory");
	}

}
