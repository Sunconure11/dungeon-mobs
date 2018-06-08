package com.gw.dm.blocks;

import java.util.Random;

import com.gw.dm.DungeonMobsHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
//import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
//import net.minecraft.util.Icon;
import net.minecraft.world.World;

public class BlockLavarock extends Block
{
	public BlockLavarock(Material par2Material) 
	{
		super(par2Material);
		this.setTickRandomly(true);
		
		this.setCreativeTab(CreativeTabs.tabBlock);
	}
	
	public BlockLavarock(int par1)
	{
		this(Material.rock);
	}
	
	/*
	@Override
	public void registerIcons(IconRegister ir)
	{
		this.blockIcon = ir.registerIcon("dungeonMobs:Lavarock");
	}
	
	@SideOnly(Side.CLIENT)
    public Icon getIcon(int par1, int par2) 
	{
		return this.blockIcon;
    }
    */
	
	@Override
	public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity) 
	{
		par5Entity.setFire(10);
	}
	
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        float f = 0.0625F;
        return AxisAlignedBB.getBoundingBox((double)((float)par2 + f), (double)par3, (double)((float)par4 + f), (double)((float)(par2 + 1) - f), (double)((float)(par3 + 1) - f), (double)((float)(par4 + 1) - f));
    }
	
	public void updateTick(World par1World, int par2, int par3, int par4, Random par5Random)
    {
        super.updateTick(par1World, par2, par3, par4, par5Random);

        int foo = par5Random.nextInt(DungeonMobsHelper.getDifficulty(par1World) + 1);
        
        if(foo < 1)
        {
        	int bar = par1World.getBlockMetadata(par2, par3, par4);
        	
        	par1World.setBlockMetadataWithNotify(par2, par3, par4, bar - 1, 3);
        }
        
        if(par1World.getBlockMetadata(par2, par3, par4) < 1)
        	par1World.setBlock(par2, par3, par4, Blocks.stone, 0, 3);
    }
	
	public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int meta)
	{
		world.setBlock(x, y, z, Blocks.lava, 0, 3);
		world.setBlockMetadataWithNotify(x, y, z, 0, 3);
		super.onBlockDestroyedByPlayer(world, x, y, z, meta);
	}
}
