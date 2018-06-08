package com.gw.dm.projectile;

import java.util.List;

import com.gw.dm.DungeonMobsHelper;
import com.gw.dm.entity.EntityDestrachan;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntitySonicBoom extends Entity
{
	private int xTile = -1;
    private int yTile = -1;
    private int zTile = -1;
    private int inTile;
    private boolean inGround = false;
    public EntityLiving shootingEntity;
    private int ticksAlive = 0;
    private int ticksInAir = 0;
    public double accelerationX;
    public double accelerationY;
    public double accelerationZ;
    private int explodeCount = 0;
    private int lastExplosionTick;
	
	public EntitySonicBoom(World par1World) 
	{
		super(par1World);
		this.isImmuneToFire = true;
	}
	
	public EntitySonicBoom(World par1World, EntityLiving par2EntityLiving, double par3, double par5, double par7)
    {
        super(par1World);
        this.shootingEntity = par2EntityLiving;
        this.setSize(1.0F, 1.0F);
        this.setLocationAndAngles(par2EntityLiving.posX, par2EntityLiving.posY + 1, par2EntityLiving.posZ, par2EntityLiving.rotationYaw, par2EntityLiving.rotationPitch);
        this.setPosition(this.posX, this.posY, this.posZ);
        this.yOffset = 0.0F;
        this.motionX = this.motionY = this.motionZ = 0.0D;
        par3 += this.rand.nextGaussian() * 0.4D;
        par5 += this.rand.nextGaussian() * 0.4D;
        par7 += this.rand.nextGaussian() * 0.4D;
        double var9 = (double)MathHelper.sqrt_double(par3 * par3 + par5 * par5 + par7 * par7);
        this.accelerationX = par3 / var9 * 0.1D;
        this.accelerationY = par5 / var9 * 0.1D;
        this.accelerationZ = par7 / var9 * 0.1D;
    }
	
	protected void onImpact(boolean lastAttempt)
	{
		if(this.ticksAlive > (this.lastExplosionTick + 10))
		{
			this.worldObj.createExplosion((Entity)this, this.posX, this.posY, this.posZ, 2F, true);
			this.lastExplosionTick = this.ticksAlive;
			this.explodeCount++;
		}
		
		if(this.explodeCount > DungeonMobsHelper.getDifficulty(this.worldObj))
			this.setDead();
		
		if(lastAttempt)
			this.setDead();
	}
	
	protected float getMotionFactor()
    {
        return 0.8F;
    }
	
	@SideOnly(Side.CLIENT)
	public boolean isInRangeToRenderDist(double par1)
    {
        return false;
    }
	
	public void onUpdate()
    {
		this.worldObj.spawnParticle("spell", this.posX, this.posY, this.posZ, 1.0D, 1.0D, 1.0D);
		++this.ticksAlive;

        if(this.ticksAlive % 50 == 0)
			this.onImpact(false);
		
        if (!this.worldObj.isRemote && (this.shootingEntity != null && this.shootingEntity.isDead || !this.worldObj.blockExists((int)this.posX, (int)this.posY, (int)this.posZ)))
        {
            this.setDead();
        }
        else if(!this.isDead)
        {
            super.onUpdate();

            if (this.inGround)
            {
                int var1 = Block.getIdFromBlock(this.worldObj.getBlock(this.xTile, this.yTile, this.zTile));

                if (var1 == this.inTile)
                {
                    if (this.ticksAlive == 600)
                    {
                        this.setDead();
                    }

                    return;
                }

                this.inGround = false;
                this.motionX *= (double)(this.rand.nextFloat() * 0.2F);
                this.motionY *= (double)(this.rand.nextFloat() * 0.2F);
                this.motionZ *= (double)(this.rand.nextFloat() * 0.2F);
                this.ticksAlive = 0;
                this.ticksInAir = 0;
            }
            else
            {
                ++this.ticksInAir;
            }

            Vec3 var15 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
            Vec3 var2 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
            MovingObjectPosition var3 = this.worldObj.rayTraceBlocks(var15, var2);
            var15 = Vec3.createVectorHelper(this.posX, this.posY, this.posZ);
            var2 = Vec3.createVectorHelper(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
            
            /*
            Vec3 var15 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX, this.posY, this.posZ);
            Vec3 var2 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
            MovingObjectPosition var3 = this.worldObj.clip(var15, var2);
            var15 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX, this.posY, this.posZ);
            var2 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
			*/

            if (var3 != null)
            {
                var2 = Vec3.createVectorHelper(var3.hitVec.xCoord, var3.hitVec.yCoord, var3.hitVec.zCoord);
            }

            Entity var4 = null;
            List var5 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
            double var6 = 0.0D;

            for (int var8 = 0; var8 < var5.size(); ++var8)
            {
                Entity var9 = (Entity)var5.get(var8);

                if (var9.canBeCollidedWith() && !var9.isEntityEqual(this.shootingEntity) && !(var9 instanceof EntityDestrachan) && !(var9 instanceof EntityArrow) && !(var9 instanceof EntityThrowable) && !(var9 instanceof EntityEyeRay))
                {
                    float var10 = 0.3F;
                    AxisAlignedBB var11 = var9.boundingBox.expand((double)var10, (double)var10, (double)var10);
                    MovingObjectPosition var12 = var11.calculateIntercept(var15, var2);

                    if (var12 != null)
                    {
                        double var13 = var15.distanceTo(var12.hitVec);

                        if (var13 < var6 || var6 == 0.0D)
                        {
                            var4 = var9;
                            var6 = var13;
                        }
                    }
                }
            }

            if (var4 != null)
            {
                var3 = new MovingObjectPosition(var4);
            }

            if (var3 != null)
            {
                this.onImpact(true);
            }

            this.posX += this.motionX;
            this.posY += this.motionY;
            this.posZ += this.motionZ;
            float var16 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
            this.rotationYaw = (float)(Math.atan2(this.motionZ, this.motionX) * 180.0D / Math.PI) + 90.0F;

            for (this.rotationPitch = (float)(Math.atan2((double)var16, this.motionY) * 180.0D / Math.PI) - 90.0F; this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F)
            {
                ;
            }

            while (this.rotationPitch - this.prevRotationPitch >= 180.0F)
            {
                this.prevRotationPitch += 360.0F;
            }

            while (this.rotationYaw - this.prevRotationYaw < -180.0F)
            {
                this.prevRotationYaw -= 360.0F;
            }

            while (this.rotationYaw - this.prevRotationYaw >= 180.0F)
            {
                this.prevRotationYaw += 360.0F;
            }

            this.rotationPitch = this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F;
            this.rotationYaw = this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F;
            float var17 = this.getMotionFactor();

            if (this.isInWater())
            {
                for (int var19 = 0; var19 < 4; ++var19)
                {
                    float var18 = 0.25F;
                    this.worldObj.spawnParticle("bubble", this.posX - this.motionX * (double)var18, this.posY - this.motionY * (double)var18, this.posZ - this.motionZ * (double)var18, this.motionX, this.motionY, this.motionZ);
                }

                var17 = 0.8F;
            }

            this.motionX += this.accelerationX;
            this.motionY += this.accelerationY;
            this.motionZ += this.accelerationZ;
            this.motionX *= (double)var17;
            this.motionY *= (double)var17;
            this.motionZ *= (double)var17;
            this.setPosition(this.posX, this.posY, this.posZ);
        }
    }
	
	public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        par1NBTTagCompound.setShort("xTile", (short)this.xTile);
        par1NBTTagCompound.setShort("yTile", (short)this.yTile);
        par1NBTTagCompound.setShort("zTile", (short)this.zTile);
        par1NBTTagCompound.setByte("inTile", (byte)this.inTile);
        par1NBTTagCompound.setByte("inGround", (byte)(this.inGround ? 1 : 0));
        par1NBTTagCompound.setTag("direction", this.newDoubleNBTList(new double[] {this.motionX, this.motionY, this.motionZ}));
        par1NBTTagCompound.setInteger("explosionCount", this.explodeCount);
        par1NBTTagCompound.setInteger("lastExplode", this.lastExplosionTick);
        par1NBTTagCompound.setInteger("ticksAlive", this.ticksAlive);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        this.xTile = par1NBTTagCompound.getShort("xTile");
        this.yTile = par1NBTTagCompound.getShort("yTile");
        this.zTile = par1NBTTagCompound.getShort("zTile");
        this.inTile = par1NBTTagCompound.getByte("inTile") & 255;
        this.inGround = par1NBTTagCompound.getByte("inGround") == 1;

        if (par1NBTTagCompound.hasKey("direction"))
        {
        	NBTTagList var2 = par1NBTTagCompound.getTagList("direction", 6);
        	
            this.motionX = var2.func_150309_d(0);
            this.motionY = var2.func_150309_d(1);
            this.motionZ = var2.func_150309_d(2);
        }
        else
            this.setDead();
        
        this.explodeCount = par1NBTTagCompound.getInteger("explosionCount");
        this.lastExplosionTick = par1NBTTagCompound.getInteger("lastExplode");
        this.ticksAlive = par1NBTTagCompound.getInteger("ticksAlive");
    }

    /**
     * Returns true if other Entities should be prevented from moving through this Entity.
     */
    public boolean canBeCollidedWith()
    {
        return false;
    }

    public float getCollisionBorderSize()
    {
        return 1.0F;
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource par1DamageSource, int par2)
    {
        return false;
    }

    @SideOnly(Side.CLIENT)
    public float getShadowSize()
    {
        return 0.0F;
    }

    /**
     * Gets how bright this entity is.
     */
    public float getBrightness(float par1)
    {
        return 1.0F;
    }

    @SideOnly(Side.CLIENT)
    public int getBrightnessForRender(float par1)
    {
        return 15728880;
    }

	@Override
	protected void entityInit() 
	{
		;
	}
}
