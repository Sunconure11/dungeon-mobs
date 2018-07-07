package com.gw.dm.blocks;

import com.gw.dm.DungeonMobs;
import com.gw.dm.util.DungeonMobsHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import java.util.Random;

public class BlockLavarock extends Block {

	public static final PropertyInteger TIME_TO_LIVE = PropertyInteger.create("age", 0, 15);

	public BlockLavarock() {
		super(Material.ROCK);
		setTickRandomly(true);
		setRegistryName(new ResourceLocation(DungeonMobs.MODID, "lavarock"));
		setUnlocalizedName(DungeonMobs.MODID + ".lavarock");
		setDefaultState(blockState.getBaseState().withProperty(TIME_TO_LIVE, Integer.valueOf(6)));
		setLightLevel(0.4375f);
	}


	@Override
	public void onEntityCollidedWithBlock(World worldIn, BlockPos pos, IBlockState state, Entity entity) {
		entity.setFire(10);
		if ((worldIn.getTotalWorldTime() % 10) == 0) entity.attackEntityFrom(DamageSource.LAVA, 4);
	}


	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState blockState,
	                                             IBlockAccess world, BlockPos pos) {
		float f = 0.0625F;
		return new AxisAlignedBB((double) ((float) pos.getX() + f), (double) pos.getX(),
				(double) ((float) pos.getY() + f), (double) ((float) (pos.getY() + 1) - f),
				(double) (float) (pos.getZ() + f), (double) (float) (pos.getZ() + f));
	}


	@Override
	public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
		super.updateTick(worldIn, pos, state, rand);

		int foo = rand.nextInt(DungeonMobsHelper.getDifficulty(worldIn) + 1);

		if (foo < 1) {
			int age = state.getValue(TIME_TO_LIVE).intValue();
			if (age > 0) {
				worldIn.setBlockState(pos, getDefaultState()
						.withProperty(TIME_TO_LIVE, Integer.valueOf(age - 1)));
			} else {
				worldIn.setBlockState(pos, Blocks.STONE.getDefaultState(), 3);
			}
		}
	}


	private boolean byWater(World world, BlockPos pos) {
		for (EnumFacing enumfacing : EnumFacing.values()) {
			if (world.getBlockState(pos.offset(enumfacing)).getMaterial()
					== Material.WATER) {
				return true;
			}
		}
		return false;
	}


	@Override
	public void onBlockDestroyedByPlayer(World world, BlockPos pos,
	                                     IBlockState state) {
		world.setBlockState(pos, Blocks.LAVA.getDefaultState(), 3);
		super.onBlockDestroyedByPlayer(world, pos, state);
	}


	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, new IProperty[]{TIME_TO_LIVE});
	}


	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState().withProperty(TIME_TO_LIVE, meta);
	}


	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(TIME_TO_LIVE).intValue();
	}


	@Override
	public int damageDropped(IBlockState state) {
		return getMetaFromState(state);
	}
}
