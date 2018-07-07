package com.gw.dm.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BlockBeamos extends BlockContainer
{
	public BlockBeamos(Material p_i45386_1_) 
	{
		super(p_i45386_1_);
		this.setCreativeTab(CreativeTabs.tabCombat);
	}

	@Override
	public TileEntity createNewTileEntity(World w, int i) 
	{
		return this.createNewTileEntity(w);
	}
	
	public TileEntity createNewTileEntity(World par1World)
	{
		return new TileEntityBeamos();
	}
	
	@Override
	public boolean isOpaqueCube()
    {
        return false;
    }
	
	public boolean renderAsNormalBlock()
    {
        return false;
    }
	
	public int getRenderType()
    {
        return -1;
    }
}
