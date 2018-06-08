package com.gw.dm.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

import com.gw.dm.DungeonMobsDamageSource;
import com.gw.dm.entity.EntityBladeTrap;
//import net.minecraft.client.renderer.texture.IconRegister;
//import net.minecraft.util.Icon;

public class BlockBladeTrap extends BlockContainer
{
	public static boolean fallInstantly = false;
	
	public int[][] dir = 
		{
			{0,-1,0},
			{0,1,0},
			{-1,0,0},
			{1,0,0},
			{0,0,-1},
			{0,0,1}
		};
	
	public BlockBladeTrap(Material par2Material) 
	{
		super(par2Material);
		this.setCreativeTab(CreativeTabs.tabCombat);
	}
	
	@Override
	public TileEntity createNewTileEntity(World w, int i) 
	{
		return this.createNewTileEntity(w);
	}
	
	public TileEntity createNewTileEntity(World par1World)
	{
		return new TileEntityBladeTrap();
	}
	
	/*
	@Override
	public void registerIcons(IIconRegister ir)
	{
		this.blockIcon = ir.registerIcon("dungeonMobs:BladeTrapIron");
	}
	*/
	
	@Override
	public int getRenderType()
    {
        return 1;
    }
	
	@Override
	public boolean isOpaqueCube()
    {
        return false;
    }
	
	/*
	@SideOnly(Side.CLIENT)
    public Icon getIcon(int par1, int par2) 
	{
		return this.blockIcon;
    }
    */
	
	@Override
	public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity) 
	{
		int dmgValue = 0;
		
		if(par1World.difficultySetting == EnumDifficulty.EASY)
			dmgValue = 2;
		else if(par1World.difficultySetting == EnumDifficulty.NORMAL)
			dmgValue = 5;
		else if(par1World.difficultySetting == EnumDifficulty.HARD)
			dmgValue = 8;
		
		if(par5Entity != null && !par1World.isRemote)
		{
			if(par5Entity instanceof EntityPlayer)
				par5Entity.attackEntityFrom(DungeonMobsDamageSource.bladeTrap, dmgValue);
		}
	}
	
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World par1World, int par2, int par3, int par4)
    {
        float f = 0.0625F;
        return AxisAlignedBB.getBoundingBox((double)((float)par2 + f), (double)par3, (double)((float)par4 + f), (double)((float)(par2 + 1) - f), (double)((float)(par3 + 1) - f), (double)((float)(par4 + 1) - f));
    }
	
	public void onBlockDestroyedByPlayer(World world, int x, int y, int z, int meta)
	{
		world.playSoundEffect((double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D, "dungeonmobs:bl_b", 1.0F, (world.rand.nextFloat() - world.rand.nextFloat()) * 0.2F + 1.0F);
		world.removeTileEntity(x, y, z);
		this.dropStuff(world, x, y, z);
		this.dropXpOnBlockBreak(world, x, y, z, 40);
		super.onBlockDestroyedByPlayer(world, x, y, z, meta);
	}
	
	public void onBlockDestroyedByExplosion(World world, int x, int y, int z, Explosion e)
	{
		world.playSoundEffect((double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D, "dungeonmobs:bl_b", 1.0F, (world.rand.nextFloat() - world.rand.nextFloat()) * 0.2F + 1.0F);
		world.removeTileEntity(x, y, z);
		this.dropStuff(world, x, y, z);
		this.dropXpOnBlockBreak(world, x, y, z, 40);
		super.onBlockDestroyedByExplosion(world, x, y, z, e);
	}
	
	public void dropStuff(World w, int x, int y, int z)
	{
		Random rand = new Random();
		
		int a = rand.nextInt(2) + 2;
		int b = rand.nextInt(3) + 1;
		int c = rand.nextInt(2);
		
		for(int i = 0; i < a; i++)
		{
			Item bar = Items.iron_ingot;
			ItemStack foo = new ItemStack(bar);
			
			EntityItem itemEnt = new EntityItem(w, x, y, z, foo);
			
			if(!w.isRemote)
				w.spawnEntityInWorld(itemEnt);
		}
		
		for(int i = 0; i < b; i++)
		{
			Item bar = Items.redstone;
			ItemStack foo = new ItemStack(bar);
			
			EntityItem itemEnt = new EntityItem(w, x, y, z, foo);
			
			if(!w.isRemote)
				w.spawnEntityInWorld(itemEnt);
		}
		
		for(int i = 0; i < c; i++)
		{
			Block bar = Blocks.stone;
			ItemStack foo = new ItemStack(bar);
			
			EntityItem itemEnt = new EntityItem(w, x, y, z, foo);
			
			if(!w.isRemote)
				w.spawnEntityInWorld(itemEnt);
		}
	}
	
	@Override
	public int quantityDropped(Random par1Random)
    {
        return 0;
    }
	
	/*
	@Override
	public int idDropped(int par1, Random par2Random, int par3)
    {
        return 0;
    }
    */
	
	public static boolean canMoveInto(World par0World, int par1, int par2, int par3)
    {
		Block l = par0World.getBlock(par1, par2, par3);

        if (l == Blocks.air || l == Blocks.fire || l == Blocks.water || l == Blocks.lava)
            return true;

        return false;
    }
	
	public void spawnBladeTrapEntity(World w, int x, int y, int z, int d)
	{
		if(canMoveInto(w, x + dir[d][0], y + dir[d][1], z + dir[d][2]) && y >= 0)
        {
			if (!w.isRemote)
            {
				w.removeTileEntity(x, y, z);
				w.setBlock(x, y, z, Blocks.air);
				
				EntityBladeTrap entity = new EntityBladeTrap(w);
                entity.setDirection(dir[d]);
                entity.setLocationAndAngles((double)((float)x + 0.5F), (double)((float)y + 0.1F), (double)((float)z + 0.5F), MathHelper.wrapAngleTo180_float(w.rand.nextFloat() * 360.0F), 0.0F);
                entity.rotationYawHead = entity.rotationYaw;
                entity.renderYawOffset = entity.rotationYaw;
                w.spawnEntityInWorld(entity);
            }
        }
	}
}
