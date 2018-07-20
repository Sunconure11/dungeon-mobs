package com.gw.dm.util;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.minecraftforge.fml.common.registry.GameRegistry;


public class DMGenerationHandler implements IWorldGenerator {	
	private static final Set<Block> targets = new HashSet<Block>();
	
	public DMGenerationHandler() {
		GameRegistry.registerWorldGenerator(this, 100);
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {	
		if(world.isRemote) {
			return;
		}
		generateTraps(chunkGenerator, world, world.rand, chunkX, chunkZ);
	}


	private void generateTraps(IChunkGenerator iChunkGenerator, 
			World world, Random rand, int chunkX, int chunkZ) {
		int posX = chunkX << 4;
		int posZ = chunkZ << 4;

		BlockPos foo = iChunkGenerator.getNearestStructurePos(world, "Stronghold", 
				new BlockPos(posX, 64, posZ), false);
		
		boolean sh = (((foo.getX() >> 4) == chunkX) && ((foo.getZ() >> 4) == chunkZ)); 

		int max = 0;

		EnumDifficulty diff = world.getDifficulty();
		
		if(diff != EnumDifficulty.PEACEFUL) {
			max = rand.nextInt(8) + rand.nextInt(3) + 2;

			if(diff == EnumDifficulty.NORMAL)
				max += rand.nextInt(3) + 3;
			if(diff == EnumDifficulty.HARD)
			{
				max += rand.nextInt(3) + 3;
				max += rand.nextInt(3) + 3;
			}
		}
		
		int x, z, lower, upper;
		int match = world.rand.nextInt(10 - DungeonMobsHelper.getDifficulty(world));
		
		for(int i = 0; i < max; i++) {
			x = posX + world.rand.nextInt(16);
			z = posZ + world.rand.nextInt(16);
			lower = 10;
			upper = 64;
			if(((i % 3) == match) || (sh && ((i % 2) == 0))) {
				do {
					if(targets.contains(world.getBlockState(new BlockPos(x, lower, z)).getBlock())
							&& world.isAirBlock(new BlockPos(x, lower + 1, z))) {
						world.setBlockState(new BlockPos(x, lower + 1, z), 
								MiscRegistrar.blockBladeTrap.getDefaultState(), 3);
						break;
					}
					lower++;
				} while(lower < upper); 
			} else {
				if(sh) {
					do {
						if(targets.contains(world.getBlockState(new BlockPos(x, upper, z)).getBlock())
								&& world.isAirBlock(new BlockPos(x, upper + 1, z))) {
							world.setBlockState(new BlockPos(x, upper + 1, z), 
									MiscRegistrar.blockBladeTrap.getDefaultState(), 3);
							break;
						}
						upper--;
					} while(lower < upper); 
				} else {
					lower = rand.nextInt(54) + 10;
					if(targets.contains(world.getBlockState(new BlockPos(x, lower, z)).getBlock())
							&& world.isAirBlock(new BlockPos(x, lower + 1, z))) {
						world.setBlockState(new BlockPos(x, lower + 1, z), 
								MiscRegistrar.blockBladeTrap.getDefaultState(), 3);
					}
				} 
			}
		}
	}
	
	
	public static void initTargetBlocks() {
		targets.add(Blocks.STONEBRICK);
		targets.add(Blocks.STONE_BRICK_STAIRS);
		targets.add(Blocks.COBBLESTONE);
	}
	
}
