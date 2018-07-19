package com.gw.dm.util;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import com.gw.dm.DungeonMobs;

/*
 * FIXME: This should probably just be a basic world gen hooks, similar 
 * to placing an ore or dungeons, not a special subscribed event!
 */


public class DungeonMobsWorldGenEvent {
	
	@SubscribeEvent
	public void onPopulateChunk(PopulateChunkEvent.Populate event) {
		if(ConfigHandler.spawnBladeTrap) {
			// FIXME: Support configurable dimensions
			// FIXME: Using switch as basic if?! Sorry Gnome, but just bad! :(
			switch (event.getWorld().provider.getDimension()) {
			case 0:
				if (event.getType() == PopulateChunkEvent.Populate.EventType.ICE) {
						generateTraps(event.getGenerator(), event.getWorld(), 
							event.getRand(), event.getChunkX(), event.getChunkZ());
					break;
				} 
				else
					break;
			}
		}
	}


	private void generateTraps(IChunkGenerator iChunkGenerator, 
			World world, Random rand, int chunkX, int chunkZ) {
		int posX = chunkX << 4;
		int posZ = chunkZ << 4;

		BlockPos foo = iChunkGenerator.getNearestStructurePos(world, "Stronghold", 
				new BlockPos(posX, 64, posZ), false);

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
		
		// TODO: Actually place the MOFOs in the world.
	}
	
}
