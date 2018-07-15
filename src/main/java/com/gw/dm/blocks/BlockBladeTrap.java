package com.gw.dm.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.gw.dm.DungeonMobs;
import com.gw.dm.DungeonMobsDamageSource;
import com.gw.dm.entity.EntityBladeTrap;


public class BlockBladeTrap extends BlockContainer {
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

	
	public BlockBladeTrap() {
		super(Material.IRON);
		setCreativeTab(CreativeTabs.COMBAT);
		setHardness(20.0F);
		setResistance(20F);
		setTickRandomly(true);
		setUnlocalizedName("bladetrap");
		setRegistryName(DungeonMobs.MODID, "bladetrap");
	}

	
	@Override
	public TileEntity createNewTileEntity(World w, int i) {
		return createNewTileEntity(w);
	}

	
	public TileEntity createNewTileEntity(World par1World) {
		return new TileEntityBladeTrap();
	}
	
	
	@Override
	public boolean isOpaqueCube(net.minecraft.block.state.IBlockState state) {
		return false;
	}

	
	@Override
	public void onEntityCollidedWithBlock(World world, 
			BlockPos pos, IBlockState state, Entity entity) {
		int dmgValue = 0;

		switch(world.getDifficulty()) {
		case EASY:
			dmgValue = 2;
			break;
		case HARD:
			dmgValue = 8;
			break;
		case NORMAL:
			dmgValue = 5;
			break;
		case PEACEFUL:
			dmgValue = 0;
			break;
		default:
			dmgValue = 5;
			break;		
		}

		if(entity != null && !world.isRemote) {
			if(entity instanceof EntityPlayer)
				entity.attackEntityFrom(DungeonMobsDamageSource.bladeTrap, dmgValue);
		}
	}

	
	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState, 
			IBlockAccess worldIn, BlockPos pos) {
		float f = 0.0625F;
		return new AxisAlignedBB(
				(double)((float)pos.getX() + f),       (double)pos.getX(), 
				(double)((float)pos.getY() + f),       (double)((float)(pos.getY() + 1) - f), 
				(double)((float)(pos.getZ() + 1) - f), (double)((float)(pos.getZ() + 1) - f));
	}

	
	@Override
	public void onBlockDestroyedByPlayer(World world, 
			BlockPos pos, IBlockState state) {
		int x = pos.getX();
		int y = pos.getY();
		int z = pos.getZ();
		//TODO: Sound
		//world.playSoundEffect((double)pos.getX() + 0.5D, 
		//		(double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, 
		//		"dungeonmobs:bl_b", 1.0F, 
		//		(world.rand.nextFloat() - world.rand.nextFloat()) * 0.2F + 1.0F);
		world.removeTileEntity(pos);
		dropStuff(world, x, y, z);
		dropXpOnBlockBreak(world, pos, 40);
		super.onBlockDestroyedByPlayer(world, pos, state);
	}

	
	@Override
	public void onBlockDestroyedByExplosion(World world, BlockPos pos, Explosion x) {
		// TODO: Sound
		//world.playSoundEffect((double)x + 0.5D, (double)y + 0.5D, (double)z + 0.5D, "dungeonmobs:bl_b", 1.0F, (world.rand.nextFloat() - world.rand.nextFloat()) * 0.2F + 1.0F);
		world.removeTileEntity(pos);
		dropStuff(world, pos.getX(), pos.getY(), pos.getZ());
		dropXpOnBlockBreak(world, pos, 40);
		super.onBlockDestroyedByExplosion(world, pos, x);
	}

	
	public void dropStuff(World w, int x, int y, int z) {
		Random rand = new Random();

		int a = rand.nextInt(2) + 2;
		int b = rand.nextInt(3) + 1;
		int c = rand.nextInt(2);

		for(int i = 0; i < a; i++) {
			Item bar = Items.IRON_INGOT;
			ItemStack foo = new ItemStack(bar);

			EntityItem itemEnt = new EntityItem(w, x, y, z, foo);

			if(!w.isRemote) {
				w.spawnEntity(itemEnt);
			}
		}

		for(int i = 0; i < b; i++) {
			Item bar = Items.REDSTONE;
			ItemStack foo = new ItemStack(bar);

			EntityItem itemEnt = new EntityItem(w, x, y, z, foo);

			if(!w.isRemote) {
				w.spawnEntity(itemEnt);
			}
		}

		for(int i = 0; i < c; i++) {
			Block bar = Blocks.STONE;
			ItemStack foo = new ItemStack(bar);

			EntityItem itemEnt = new EntityItem(w, x, y, z, foo);

			if(!w.isRemote) {
				w.spawnEntity(itemEnt);
			}
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

	
	public static boolean canMoveInto(World par0World, int par1, int par2, int par3) {
		Block l = par0World.getBlockState(new BlockPos(par1, par2, par3)).getBlock();
		return ((l == Blocks.AIR)   || (l == Blocks.FIRE) 
			 || (l == Blocks.WATER) || (l == Blocks.LAVA));
	}

	
	public void spawnBladeTrapEntity(World world, int x, int y, int z, int d) {
		if(canMoveInto(world, x + dir[d][0], y + dir[d][1], z + dir[d][2]) && y >= 0) {
			BlockPos pos = new BlockPos(x, y, z);
			if (!world.isRemote) {
				world.removeTileEntity(pos);
				world.setBlockState(pos, Blocks.AIR.getDefaultState());

				EntityBladeTrap entity = new EntityBladeTrap(world);
				entity.setDirection(dir[d]);
				entity.setLocationAndAngles((double)((float)x + 0.5F), 
						(double)((float)y + 0.1F), 
						(double)((float)z + 0.5F), 
						MathHelper.wrapDegrees(world.rand.nextFloat() * 360.0F), 
						0.0F);
				entity.rotationYawHead = entity.rotationYaw;
				entity.renderYawOffset = entity.rotationYaw;
				world.spawnEntity(entity);
			}
		}
	}
}
