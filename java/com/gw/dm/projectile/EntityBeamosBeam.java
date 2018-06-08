package com.gw.dm.projectile;

import java.util.List;

import com.gw.dm.DungeonMobsHelper;
import com.gw.dm.entity.EntityRakshasaImage;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityBeamosBeam extends EntityLiving 
{
	private int xTile = -1;
    private int yTile = -1;
    private int zTile = -1;
    private int inTile = 0;
    private boolean inGround = false;
    public TileEntity shootingEntity;
    public EntityLiving shooterEntity;
    private int ticksAlive;
    private int ticksInAir = 0;
    public double accelerationX;
    public double accelerationY;
    public double accelerationZ;
    private EntityLivingBase myTarget;
	
	public EntityBeamosBeam(World par1World) 
	{
		super(par1World);
		this.setSize(1.0F, 1.0F);
		
		this.experienceValue = 0;
	}
	
	/*
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(100.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.0D);
        //this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(0.0D);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0D);
    }
    */
	
	public boolean canBeCollidedWith()
    {
        return false;
    }

    public float getCollisionBorderSize()
    {
        return 1.0F;
    }
    
    @SideOnly(Side.CLIENT)
	public boolean isInRangeToRenderDist(double par1)
    {
        double var3 = this.boundingBox.getAverageEdgeLength() * 4.0D;
        var3 *= 64.0D;
        return par1 < var3 * var3;
    }
	
	public EntityBeamosBeam(World par1World, double par2, double par4, double par6, double par8, double par10, double par12)
    {
        super(par1World);
        this.setSize(1.0F, 1.0F);
        this.setLocationAndAngles(par2, par4, par6, this.rotationYaw, this.rotationPitch);
        this.setPosition(par2, par4, par6);
        double var14 = (double)MathHelper.sqrt_double(par8 * par8 + par10 * par10 + par12 * par12);
        this.accelerationX = par8 / var14 * 0.2D;
        this.accelerationY = par10 / var14 * 0.2D;
        this.accelerationZ = par12 / var14 * 0.2D;
        this.experienceValue = 0;
    }
	
	public EntityBeamosBeam(World par1World, EntityLiving par2EntityLiving, double par3, double par5, double par7, EntityLivingBase par9)
    {
        super(par1World);
        this.shooterEntity = par2EntityLiving;
        this.setSize(1.0F, 1.0F);
        this.setLocationAndAngles(par2EntityLiving.posX, par2EntityLiving.posY, par2EntityLiving.posZ, par2EntityLiving.rotationYaw, par2EntityLiving.rotationPitch);
        //this.setPosition(this.posX, this.posY, this.posZ);
        this.yOffset = 0.0F;
        this.motionX = this.motionY = this.motionZ = 0.0D;
        par3 += this.rand.nextGaussian() * 0.005D;
        par5 += this.rand.nextGaussian() * 0.005D;
        par7 += this.rand.nextGaussian() * 0.005D;
        double var9 = (double)MathHelper.sqrt_double(par3 * par3 + par5 * par5 + par7 * par7);
        this.accelerationX = par3 / var9 * 0.2D;
        this.accelerationY = par5 / var9 * 0.2D;
        this.accelerationZ = par7 / var9 * 0.2D;
        this.experienceValue = 0;
        
        this.myTarget = par9;
    }
	
	public EntityBeamosBeam(World par1World, TileEntity par2EntityLiving, double par3, double par5, double par7)
    {
        super(par1World);
        this.shootingEntity = par2EntityLiving;
        this.setSize(1.0F, 1.0F);
        this.setLocationAndAngles(par2EntityLiving.xCoord, par2EntityLiving.yCoord, par2EntityLiving.zCoord, this.rotationYaw, this.rotationPitch);
        //this.setPosition(this.posX, this.posY, this.posZ);
        this.yOffset = 0.0F;
        this.motionX = this.motionY = this.motionZ = 0.0D;
        
        /*
        par3 += this.rand.nextGaussian() * 0.005D;
        par5 += this.rand.nextGaussian() * 0.005D;
        par7 += this.rand.nextGaussian() * 0.005D;
        */
        
        double var9 = (double)MathHelper.sqrt_double(par3 * par3 + par5 * par5 + par7 * par7);
        this.accelerationX = par3 / var9 * 0.2D;
        this.accelerationY = par5 / var9 * 0.2D;
        this.accelerationZ = par7 / var9 * 0.2D;
        this.experienceValue = 0;
    }
	
	protected float getMotionFactor()
    {
        return 0.9F + (DungeonMobsHelper.getDifficulty(this.worldObj) * 0.2F);
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
	
	protected void onImpact(MovingObjectPosition var1)
	{
		if (!this.worldObj.isRemote)
        {
            if (var1.entityHit != null)
            {
            	EntityLivingBase foo = (EntityLivingBase)var1.entityHit;
            	
            	foo.addPotionEffect(new PotionEffect(Potion.harm.id, 1, 1));
            }
        }
		
		this.setDead();
	}
	
	public void onUpdate()
    {
		//this.updateAcceleration();
		
		++this.ticksAlive;
		
        if (!this.worldObj.isRemote && ((this.shooterEntity != null || this.shootingEntity != null) && !this.worldObj.blockExists((int)this.posX, (int)this.posY, (int)this.posZ)))
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

            if (var3 != null)
                var2 = Vec3.createVectorHelper(var3.hitVec.xCoord, var3.hitVec.yCoord, var3.hitVec.zCoord);

            Entity var4 = null;
            List var5 = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
            double var6 = 0.0D;

            for (int var8 = 0; var8 < var5.size(); ++var8)
            {
                Entity var9 = (Entity)var5.get(var8);

                if (var9.canBeCollidedWith() && !(var9 instanceof EntityArrow) && !(var9 instanceof EntityThrowable) && !(var9 instanceof EntityEyeRay) && !(var9 instanceof EntityMagicMissile) && !(var9 instanceof EntityRakshasaImage))
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
                this.onImpact(var3);
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
                    this.worldObj.spawnParticle("smoke", this.posX - this.motionX * (double)var18, this.posY - this.motionY * (double)var18, this.posZ - this.motionZ * (double)var18, this.motionX, this.motionY, this.motionZ);
                }

                this.setDead();
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
	
	public int getTalkInterval()
    {
        return 20;
    }
	
	protected String getLivingSound()
	{
		return "dungeonmobs:be_be";
	}
}
