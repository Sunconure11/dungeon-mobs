package com.gw.dm.projectile;

import java.util.List;

import com.gw.dm.DungeonMobsHelper;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class EntityEyeRay extends EntityLiving
{
	PotionEffect[] myEffect;
	
	private int xTile = -1;
    private int yTile = -1;
    private int zTile = -1;
    private int inTile = 0;
    private boolean inGround = false;
    public EntityLiving shootingEntity;
    private int ticksAlive;
    private int ticksInAir = 0;
    public double accelerationX;
    public double accelerationY;
    public double accelerationZ;

	public EntityEyeRay(World par1World) 
	{
		super(par1World);
		this.setSize(1.0F, 1.0F);
		
		this.experienceValue = 0;
	}
	
	protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(100.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.0D);
        //this.getEntityAttribute(SharedMonsterAttributes.attackDamage).setAttribute(0.0D);
        this.getEntityAttribute(SharedMonsterAttributes.knockbackResistance).setBaseValue(1.0D);
    }
	
	public void setEffectsSize(int num)
	{
		myEffect = new PotionEffect[num];
	}
	
	public void setEffect(PotionEffect pot, int num)
	{
		this.myEffect[num] = pot;
	}
	
	public void setEffects(PotionEffect[] pots)
	{
		this.myEffect = pots;
	}
	
	public boolean canBeCollidedWith()
    {
        return false;
    }

    public float getCollisionBorderSize()
    {
        return 1.0F;
    }

    /*
	@Override
	protected void entityInit() 
	{
	}
	*/
	
	@SideOnly(Side.CLIENT)
	
	public boolean isInRangeToRenderDist(double par1)
    {
        double var3 = this.boundingBox.getAverageEdgeLength() * 4.0D;
        var3 *= 64.0D;
        return par1 < var3 * var3;
    }
	
	public EntityEyeRay(World par1World, double par2, double par4, double par6, double par8, double par10, double par12)
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
	
	public EntityEyeRay(World par1World, EntityLiving par2EntityLiving, double par3, double par5, double par7)
    {
        super(par1World);
        this.shootingEntity = par2EntityLiving;
        this.setSize(1.0F, 1.0F);
        this.setLocationAndAngles(par2EntityLiving.posX, par2EntityLiving.posY, par2EntityLiving.posZ, par2EntityLiving.rotationYaw, par2EntityLiving.rotationPitch);
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
	
	public void onUpdate()
    {
		if (!this.worldObj.isRemote && (this.shootingEntity != null && this.shootingEntity.isDead || !this.worldObj.blockExists((int)this.posX, (int)this.posY, (int)this.posZ)))
            this.setDead();
        else
        {
        	if(this.worldObj.getBlock((int)this.posX, (int)this.posY, (int)this.posZ) != Blocks.air)
        	{
        		Block foo = this.worldObj.getBlock((int)this.posX, (int)this.posY, (int)this.posZ);
        		
        		//this.worldObj.spawnParticle("smoke", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
    			
        		if(!(foo == Blocks.water || foo == Blocks.lava) && !this.worldObj.isRemote)
        		{
        			this.worldObj.playSoundAtEntity(this, "dungeonmobs:er_d",  1.0F,  1.0F);
        			//this.worldObj.setBlockWithNotify((int)this.posX, (int)this.posY, (int)this.posZ, 0);
        			this.worldObj.createExplosion(this, this.posX, this.posY, this.posZ, 1.2F, true);
        			this.setDead();
        		}
        		else
        		{
        			this.worldObj.spawnParticle("smoke", this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D);
        			this.setDead();
        		}
        	}
        	
        	if (this.inGround)
            {
                int var1 = Block.getIdFromBlock(this.worldObj.getBlock(this.xTile, this.yTile, this.zTile));

                if (var1 == this.inTile)
                {
                    this.ticksAlive++;

                    if (this.ticksAlive == 600)
                    	this.setDead();

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
                this.ticksInAir++;

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

                if (var9.canBeCollidedWith() && (!var9.isEntityEqual(this.shootingEntity) || this.ticksInAir >= 25) && !(var9 instanceof EntityEyeRay) && !(var9 instanceof EntityArrow) && !(var9 instanceof EntityThrowable))
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

            this.motionX += this.accelerationX;
            this.motionY += this.accelerationY;
            this.motionZ += this.accelerationZ;
            this.motionX *= (double)var17;
            this.motionY *= (double)var17;
            this.motionZ *= (double)var17;
            this.worldObj.spawnParticle("mobSpell", this.posX, this.posY + 1.0D, this.posZ, 0.0D, 0.0D, 0.0D);
            this.setPosition(this.posX, this.posY, this.posZ);
        }
    }
	
	protected float getMotionFactor()
    {
        return 1.0F;
    }
	
	protected void onImpact(MovingObjectPosition var1)
	{
		if (!this.worldObj.isRemote)
        {
            if (var1.entityHit != null)
            {
            	Entity foo = var1.entityHit;
            	boolean badIdea = false;
            	
            	if(foo instanceof EntityPlayer)
            	{
            		EntityPlayer bar = (EntityPlayer)foo;
            		if(bar.capabilities.isCreativeMode)
            			badIdea = true;
            	}
            	
            	if(foo instanceof EntityLivingBase && !badIdea)
            	{
            		this.worldObj.playSoundAtEntity(this, "dungeonmobs:er_d",  1.0F,  1.0F);
            		
            		EntityLivingBase bar = (EntityLivingBase)foo;
            		
            		for(int i = 0; i < myEffect.length; i++)
            		{
            			bar.addPotionEffect(this.myEffect[i]);
            		}
            	}
            	
            	if(foo instanceof EntityPlayer && !badIdea)
            	{
            		EntityPlayer bar = (EntityPlayer)foo;
            		InventoryPlayer theStuff = bar.inventory;
            		
            		int var2 = DungeonMobsHelper.getDifficulty(this.worldObj);
            		
            		int moo;
            		
            		if(var2 != 0)
            			moo = this.rand.nextInt(var2 * 2) + var2 - 1;
            		else
            			moo = 0;
            		
            		for(int i = 0; i < moo; i++)
            		{
            			int ctr = this.rand.nextInt(40);
            			
            			ItemStack thing = theStuff.getStackInSlot(ctr);
            			
            			if(thing != null)
            			{
            				if(thing.isItemEnchanted())
            					thing.getTagCompound().removeTag("ench");
            			}
            		}
            	}
            }

            this.setDead();
        }
	}

	@Override
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
        
        if (par1NBTTagCompound.hasKey("Effects"))
        {
        	NBTTagList var2 = par1NBTTagCompound.getTagList("Effects", 12);

        	this.myEffect = new PotionEffect[var2.tagCount()];
        	
            for (int var3 = 0; var3 < var2.tagCount(); var3++)
            {
            	NBTTagCompound var4 = var2.getCompoundTagAt(var3);
                PotionEffect var5 = PotionEffect.readCustomPotionEffectFromNBT(var4);
                this.myEffect[var3] = var5;
            }
        }
        else
        	this.setDead();
	}

	@Override
	public void writeEntityToNBT(NBTTagCompound var1) 
	{
		var1.setShort("xTile", (short)this.xTile);
        var1.setShort("yTile", (short)this.yTile);
        var1.setShort("zTile", (short)this.zTile);
        var1.setByte("inTile", (byte)this.inTile);
        var1.setByte("inGround", (byte)(this.inGround ? 1 : 0));
        var1.setTag("direction", this.newDoubleNBTList(new double[] {this.motionX, this.motionY, this.motionZ}));
        
        NBTTagList var2 = new NBTTagList();
        
        for(int i = 0; i < this.myEffect.length; i++)
        {
        	PotionEffect var3 = this.myEffect[i];
        	var2.appendTag(var3.writeCustomPotionEffectToNBT(new NBTTagCompound()));
        }
        
        var1.setTag("Effects", var2);
	}
	
	@Override
	protected void updateFallState(double par1, boolean par3)
    {
        ;
    }
	
	@Override
	public void onDeath(DamageSource par1DamageSource)
    {
        ;
    }
	
	@Override
	protected void fall(float par1)
    {
        ;
    }
	
	@Override
	public boolean isOnLadder()
    {
        return false;
    }
	
	protected String getLivingSound()
	{
		return null;
	}
	
	protected String getHurtSound()
	{
		return null;
	}
	
	protected String getDeathSound()
	{
		return null;
	}
	
	public boolean getCanSpawnHere()
	{
		return false;
	}
}
