package com.gw.dm.entity;

import com.gw.dm.DungeonMobsDamageSource;
import com.gw.dm.blocks.BlockBladeTrap;
import com.gw.dm.util.AudioHandler;
import com.gw.dm.util.ConfigHandler;
import com.gw.dm.util.MiscRegistrar;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.Iterator;
import java.util.List;


public class EntityBladeTrap extends EntityLiving {
	public int moveTime = 0;
	public int attackTimer = 0;
	private int[] dir = {0, 0, 0};

	public EntityBladeTrap(World par1World) {
		super(par1World);
		setSize(0.98F, 0.98F);
	}

	public EntityBladeTrap(World w, double x, double y, double z) {
		super(w);
		setSize(0.98F, 0.98F);
		posX = x;
		posY = y;
		posZ = z;
	}

	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(100.0D 
				* ConfigHandler.healthx);
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.0D);
		getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0D);
	}


	public void setDirection(int[] d) {
		dir = d;
	}


	protected boolean canTriggerWalking() {
		return false;
	}


	public boolean canBeCollidedWith() {
		return false;
	}


	public void onUpdate() {
		prevPosX = posX;
		prevPosY = posY;
		prevPosZ = posZ;
		++moveTime;
		++attackTimer;

		if (attackTimer > 11)
			attackTimer = 11;

		motionX += (0.12D * dir[0]);
		motionY += (0.12D * dir[1]);
		motionZ += (0.12D * dir[2]);

		move(MoverType.SELF, motionX, motionY, motionZ);
		motionX *= 0.9800000190734863D;
		motionY *= 0.9800000190734863D;
		motionZ *= 0.9800000190734863D;

		BlockPos where;
		if (!world.isRemote) {
			int i = MathHelper.floor(posX);
			int j = MathHelper.floor(posY);
			int k = MathHelper.floor(posZ);

			where = new BlockPos(i, j, k);

			if (!BlockBladeTrap.canMoveInto(world, i + dir[0], j + dir[1], k + dir[2])) {
				world.setBlockState(where, MiscRegistrar.blockBladeTrap.getDefaultState(), 3);

				setDead();
			} else if (moveTime > 40) {
				world.setBlockState(where, MiscRegistrar.blockBladeTrap.getDefaultState(), 3);

				setDead();
			}
		}

		if (attackTimer > 10) {
			boolean flag = false;

			List<Entity> foo = null;

			try {
				foo = world.getEntitiesWithinAABBExcludingEntity(this, getEntityBoundingBox());
			} catch (NullPointerException e) {
				flag = true;
			}

			if (!flag && !(foo == null) && !foo.isEmpty()) {
				Iterator iter = foo.iterator();

				while (iter.hasNext()) {
					Entity bar = (Entity) iter.next();
					playSound(AudioHandler.entityBladeTrapBlade, 1.0F,
							(world.rand.nextFloat() - world.rand.nextFloat()) * 0.2F + 1.0F);

					float dmgValue = ConfigHandler.damagex;

					switch (world.getDifficulty()) {
						case EASY:
							dmgValue = 2 * ConfigHandler.damagex;
							break;
						case HARD:
							dmgValue = 8 * ConfigHandler.damagex;
							break;
						case NORMAL:
							dmgValue = 5 * ConfigHandler.damagex;
							break;
						case PEACEFUL:
						default:
							break;
					}
					bar.attackEntityFrom(DungeonMobsDamageSource.BLADE_TRAP, dmgValue);
				}
				attackTimer = 0;
			}
		}
	}


	@Override
	public void jump() {/*Do Nothing*/}


	@Override
	public void fall(float distance, float damageMultiplier) {/*NA*/}


	@Override
	public boolean canRenderOnFire() {
		return false;
	}


	@Override
	public void onCollideWithPlayer(EntityPlayer par1EntityPlayer) {
		int dmgValue = 4;

		switch (world.getDifficulty()) {
			case EASY:
				dmgValue += 2;
				break;
			case HARD:
				dmgValue += 8;
				break;
			case NORMAL:
				dmgValue += 5;
				break;
			case PEACEFUL:
			default:
				break;
		}
		par1EntityPlayer.attackEntityFrom(DungeonMobsDamageSource.BLADE_TRAP, dmgValue);
	}


	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		return false;
	}


	@Override
	protected void entityInit() {
		super.entityInit();
		// FIXME: What did this do, and do I need it?
		//dataWatcher.addObject(16, new Byte((byte)0));
	}


	@Override
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound) {
		super.writeEntityToNBT(par1NBTTagCompound);

		par1NBTTagCompound.setInteger("moveTime", moveTime);
		par1NBTTagCompound.setInteger("atkTime", attackTimer);

		par1NBTTagCompound.setInteger("d0", dir[0]);
		par1NBTTagCompound.setInteger("d1", dir[1]);
		par1NBTTagCompound.setInteger("d2", dir[2]);
	}


	@Override
	public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound) {
		super.readEntityFromNBT(par1NBTTagCompound);

		moveTime = par1NBTTagCompound.getInteger("moveTime");
		attackTimer = par1NBTTagCompound.getInteger("atkTime");

		dir[0] = par1NBTTagCompound.getInteger("d0");
		dir[1] = par1NBTTagCompound.getInteger("d1");
		dir[2] = par1NBTTagCompound.getInteger("d2");
	}


	@Override
	protected SoundEvent getAmbientSound() {
		return AudioHandler.entityBladeTrapAmbient;
	}


	@Override
	protected SoundEvent getHurtSound(DamageSource src) {
		return AudioHandler.entityBladeTrapHurt;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return AudioHandler.entityBladeTrapDeath;
	}


	@Override
	public int getTalkInterval() {
		return 20;
	}


	/*
	 * Apparently these once had spawning logic, but it was removed. 
	 * This is probably the way it should be -- they should be placed 
	 * by world-gen (in block for), not spawn in randomly in explored 
	 * areas (or someone's base). 
	 */
	@Override
	public boolean getCanSpawnHere() {
		return false;

		/*
		System.out.println("DEBUG: Blade Trap attempting to spawn. [X: " + posX + ", Y: " + posY + ", Z: " + posZ + "]");

		//ChunkPosition foo = world.findClosestStructure("Stronghold", 8, 8, 8);
		//ChunkPosition bar = world.findClosestStructure("Dungeon", 8, 8, 8);

		ChunkPosition foo = world.findClosestStructure("Stronghold", (int)posX, (int)posY, (int)posZ);

		if(foo == null)
			foo = world.findClosestStructure("Dungeon", 8, 8, 8);

		if(foo != null)
			System.out.println("DEBUG: ChunkPosition foo. [X: " + foo.x + ", Y: " + foo.y + ", Z: " + foo.z + "]");

		boolean spawned = false;

		if(foo.x < (posX - 32) || foo.x > (posX + 32))
			return false;
		if(foo.z < (posZ - 32) || foo.z > (posZ + 32))
			return false;

		if(foo != null)
		{
			System.out.println("DEBUG: Blade Trap found a place to spawn. [X: " + posX + ", Y: " + posY + ", Z: " + posZ + "]");

			int barX;
			int barY;
			int barZ;

			for(int i = 0; i < 10 && !spawned; i++)
			{
				barX = foo.x + (getRNG().nextInt(33) - 16);
				barY = foo.y + (getRNG().nextInt(33) - 16);
				barZ = foo.z + (getRNG().nextInt(33) - 16);

				if(barY < 1)
					barY = 1;

				if(barY > 50)
					barY = 50;

				while(world.canBlockSeeTheSky(barX, barY, barZ))
				{
					barY--;
				}

				if(world.getBlockId(barX, barY, barZ) == 0)
				{
					if(findNearbyMoss(barX, barY, barZ))
					{
						posX = barX;
						posY = barY;
						posZ = barZ;
						spawned = true;

						System.out.println("DEBUG: Blade Trap is spawning. [X: " + posX + ", Y: " + posY + ", Z: " + posZ + "]");
					}
				}
			}
		}
		 */
		/*
		else
			return false;
		 */

		/*
		if(tooManyTrapsNearby((int)posX, (int)posY, (int)posZ))
			return false;

		if(world.canBlockSeeTheSky((int)posX, (int)posY, (int)posZ))
			return false;

		if(world.canBlockSeeTheSky((int)posX, (int)posY + 1, (int)posZ))
			return false;

		if(!spawned)
			return false;
		else
		{
			System.out.println("DEBUG: Blade Trap has landed.");
			System.out.println("DEBUG: Blade Trap final position. [X: " + posX + ", Y: " + posY + ", Z: " + posZ + "]");
			moveTime = 90;
			return super.getCanSpawnHere();
		}
		 */
	}


	private boolean tooManyTrapsNearby(int x, int y, int z) {
		int count = 0;
		int max = 4;

		for (int i = -6; i < 6; i++)
			for (int j = -6; j < 6; j++)
				for (int k = -6; k < 6; k++) {
					int foo = y + j;

					if (foo < 1) {
						foo = 1;
					}
					if (world.getBlockState(new BlockPos(x + i, foo, z + k)).getBlock()
							== MiscRegistrar.blockBladeTrap) {
						count++;
					}
				}

		/*
		List<EntityBladeTrap> foo = world.getEntitiesWithinAABB(EntityBladeTrap.class, getBoundingBox().expand(4.0D, 4.0D, 4.0D));

		if(foo != null)
		{
			count += foo.size();
			max += 3;
		}
		 */

		if (count > max)
			return true;
		else
			return false;
	}


	public boolean findNearbyMoss(int x, int y, int z) {
		for (int i = -4; i < 4; i++) {
			for (int j = -4; j < 4; j++) {
				for (int k = -4; k < 4; k++) {
					Block toTest = world.getBlockState(new
							BlockPos(x + i, y + j, z + k)).getBlock();
					if (toTest == Blocks.STONEBRICK) {
						return true;
					}
				}
			}
		}
		return false;
	}

}