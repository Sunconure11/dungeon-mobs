package com.gw.dm;

import java.util.Random;

import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
//import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.terraingen.PopulateChunkEvent;

public class DungeonMobsWorldGenEvent
{
	//private WorldGenBladeTraps bladeTrapGen = new WorldGenBladeTraps();
	
	/*
	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) 
	{
		//chunkProvider.strongholdGenerator.hasStructureAt(coords);
		
		ChunkPosition foo = chunkProvider.findClosestStructure(world, "Stronghold", 16, 16, 16);
	}
	*/

	@SubscribeEvent
	public void onPopulateChunk(PopulateChunkEvent.Populate event) 
	{
		if(DungeonMobs.spawnBladeTrap)
		{
			switch (event.world.provider.dimensionId) 
			{
				case 0:
					if (event.type == PopulateChunkEvent.Populate.EventType.ICE)
					{
						this.generateTraps(event.chunkProvider, event.world, event.rand, event.chunkX, event.chunkZ);
						break;
					} 
					else
						break;
			}
		}
	}
	
	private void generateTraps(IChunkProvider chunkProvider, World world, Random rand, int chunkX, int chunkZ)
	{
		int posX = chunkX << 4;
		int posZ = chunkZ << 4;
		
		/*
		int max = rand.nextInt(8) + 8;
		
		for(int i = 0; i < max; i++)
		{
			System.out.println("DEBUG: Loop " + i + " out of " + max + ".");
			
			int barX = posX + (rand.nextInt(33) - 16);
			int barY = -1;
			int barZ = posZ + (rand.nextInt(33) - 16);
			
			int upperY = 64;
			int lowerY = 0;
			
			boolean hitStone = false;
			
			do
			{
				if(world.getBlockId(barX, upperY, barZ) == 98 || world.getBlockId(barX, upperY, barZ) == 97)
				{
					System.out.println("DEBUG: Upper bound has hit valid height.");
					hitStone = true;
				}
				else
				{
					upperY--;
					System.out.println("DEBUG: Upper bound reduced to " + upperY + ".");
				}
			}while(!hitStone && upperY > 0);
			
			hitStone = false;
			
			do
			{
				if(world.getBlockId(barX, lowerY, barZ) == 98 || world.getBlockId(barX, lowerY, barZ) == 97)
				{
					System.out.println("DEBUG: Lower bound has hit valid height.");
					hitStone = true;
				}
				else
				{
					lowerY++;
					System.out.println("DEBUG: Lower bound increased to " + lowerY + ".");
				}
			}while(!hitStone && lowerY < 64);
			
			int tryCounter = 0;
			
			if(lowerY < upperY)
			{
				do
				{
					System.out.println("DEBUG: Attempting to generate random height within bounds...");
					barY = rand.nextInt(upperY) + lowerY;
					tryCounter++;
				}while(world.getBlockId(barX, barY, barZ) != 0 && tryCounter < 15);
			}
			
			if(barY > -1 && !world.canBlockSeeTheSky(barX, barY, barZ))
			{
				System.out.println("DEBUG: Placing trap. [X: " + barX + ", Y: " + barY + ", Z: " + barZ + "]");
				world.setBlock(barX, barY, barZ, DungeonMobs.bladeTrap.blockID, 0, 3);
			}
			else
				System.out.println("DEBUG: No trap placed.");
		}
		*/
		
								    // is findClosestStructure
		ChunkPosition foo = chunkProvider.func_147416_a(world, "Stronghold", posX, 64, posZ);
		
		if(posX < (foo.chunkPosX + 128) && posX > (foo.chunkPosX - 128) && posZ < (foo.chunkPosZ + 128) && posZ > (foo.chunkPosZ - 128))
		{
			//int xDist = Math.abs(Math.abs(posX) - Math.abs(foo.x));
			//int zDist = Math.abs(Math.abs(posZ) - Math.abs(foo.z));
			
			int max = 0;
			
			if(world.difficultySetting != EnumDifficulty.PEACEFUL)
			{
				max = rand.nextInt(8) + rand.nextInt(3) + 2;
				
				if(world.difficultySetting == EnumDifficulty.NORMAL)
					max += rand.nextInt(3) + 3;
				if(world.difficultySetting == EnumDifficulty.HARD)
				{
					max += rand.nextInt(3) + 3;
					max += rand.nextInt(3) + 3;
				}
			}
			
			for(int i = 0; i < max; i++)
			{
				int barX = foo.chunkPosX + (rand.nextInt(129) - 64);
				int barY = -1;
				int barZ = foo.chunkPosZ + (rand.nextInt(129) - 64);
				
				int upperY = 64;
				int lowerY = 0;
				
				boolean hitStone = false;
				
				do
				{
					if(Block.getIdFromBlock(world.getBlock(barX, upperY, barZ)) == 98 || Block.getIdFromBlock(world.getBlock(barX, upperY, barZ)) == 97 || Block.getIdFromBlock(world.getBlock(barX, upperY, barZ)) == 48)
					
						hitStone = true;
					else
						upperY--;
				}while(!hitStone && upperY > 0);
				
				hitStone = false;
				
				do
				{
					if(Block.getIdFromBlock(world.getBlock(barX, lowerY, barZ)) == 98 || Block.getIdFromBlock(world.getBlock(barX, lowerY, barZ)) == 97 || Block.getIdFromBlock(world.getBlock(barX, lowerY, barZ)) == 48)
						hitStone = true;
					else
						lowerY++;
				}while(!hitStone && lowerY < 64);
				
				int tryCounter = 0;
				
				if(lowerY < upperY)
				{
					do
					{
						barY = rand.nextInt(upperY - lowerY) + lowerY;
						tryCounter++;
					}while(Block.getIdFromBlock(world.getBlock(barX, barY, barZ)) != 0 && tryCounter < 15 && tryCounter < (upperY - lowerY));
				}
				
				if(barY > -1 && !world.canBlockSeeTheSky(barX, barY, barZ) && (world.getBlock(barX, barY, barZ) == Blocks.air || world.getBlock(barX, barY, barZ) == Blocks.stonebrick)); 
					world.setBlock(barX, barY, barZ, DungeonMobs.bladeTrap, 0, 3);
			}
		}
	}
}
