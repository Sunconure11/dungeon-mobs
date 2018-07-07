package com.gw.dm.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelNetherHound extends ModelBase
{
	  //fields
	    ModelRenderer MuzzleLow;
	    ModelRenderer MuzzleTop;
	    ModelRenderer EarRight;
	    ModelRenderer EarLeft;
	    ModelRenderer HeadBase;
	    ModelRenderer BodyUp2;
	    ModelRenderer BodyBase;
	    ModelRenderer BodyUp1;
	    ModelRenderer LBLegMid;
	    ModelRenderer LFPaw;
	    ModelRenderer LFShoulder;
	    ModelRenderer LFLeg;
	    ModelRenderer RFShoulder;
	    ModelRenderer RFLeg;
	    ModelRenderer LBPaw;
	    ModelRenderer LBThigh;
	    ModelRenderer LBLegLow;
	    ModelRenderer LBLegTop;
	    ModelRenderer RFPaw;
	    ModelRenderer RBThigh;
	    ModelRenderer RBLegTop;
	    ModelRenderer RBLegMid;
	    ModelRenderer RBLegLow;
	    ModelRenderer RBPaw;
	  
	  public ModelNetherHound()
	  {
	    textureWidth = 64;
	    textureHeight = 32;
	    
	      MuzzleLow = new ModelRenderer(this, 46, 8);
	      MuzzleLow.addBox(-2F, -1.5F, -13F, 4, 1, 6);
	      MuzzleLow.setRotationPoint(0F, 14F, 0F);
	      MuzzleLow.setTextureSize(64, 32);
	      MuzzleLow.mirror = true;
	      setRotation(MuzzleLow, 0.4363323F, 0F, 0F);
	      MuzzleTop = new ModelRenderer(this, 46, 0);
	      MuzzleTop.addBox(-2F, -0.5F, -13F, 4, 3, 5);
	      MuzzleTop.setRotationPoint(0F, 14F, 0F);
	      MuzzleTop.setTextureSize(64, 32);
	      MuzzleTop.mirror = true;
	      setRotation(MuzzleTop, 0F, 0F, 0F);
	      EarRight = new ModelRenderer(this, 0, 12);
	      EarRight.addBox(-4F, -4.7F, -5F, 1, 5, 3);
	      EarRight.setRotationPoint(0F, 14F, -1F);
	      EarRight.setTextureSize(64, 32);
	      EarRight.mirror = true;
	      setRotation(EarRight, -0.3490659F, 0F, 0F);
	      EarLeft = new ModelRenderer(this, 0, 12);
	      EarLeft.addBox(3F, -4.7F, -5F, 1, 5, 3);
	      EarLeft.setRotationPoint(0F, 14F, -1F);
	      EarLeft.setTextureSize(64, 32);
	      EarLeft.mirror = true;	      
	      setRotation(EarLeft, -0.3490659F, 0F, 0F);
	      HeadBase = new ModelRenderer(this, 0, 0);
	      HeadBase.addBox(-3F, -2.5F, -7F, 6, 5, 7);
	      HeadBase.setRotationPoint(0F, 14F, -1F);
	      HeadBase.setTextureSize(64, 32);
	      HeadBase.mirror = true;
	      setRotation(HeadBase, 0F, 0F, 0F);
	      BodyUp2 = new ModelRenderer(this, 23, 6);
	      BodyUp2.addBox(-3F, -3.5F, -7F, 6, 1, 9);
	      BodyUp2.setRotationPoint(0F, 12F, 7F);
	      BodyUp2.setTextureSize(64, 32);
	      BodyUp2.mirror = true;
	      setRotation(BodyUp2, 0F, 0F, 0F);
	      BodyBase = new ModelRenderer(this, 15, 0);
	      BodyBase.addBox(-4F, -3.5F, -7F, 8, 6, 15);
	      BodyBase.setRotationPoint(0F, 14F, 6F);
	      BodyBase.setTextureSize(64, 32);
	      BodyBase.mirror = true;
	      setRotation(BodyBase, 0F, 0F, 0F);
	      BodyUp1 = new ModelRenderer(this, 19, 3);
	      BodyUp1.addBox(-3.5F, -3.5F, -7F, 7, 1, 12);
	      BodyUp1.setRotationPoint(0F, 13F, 6.5F);
	      BodyUp1.setTextureSize(64, 32);
	      BodyUp1.mirror = true;
	      setRotation(BodyUp1, 0F, 0F, 0F);
	      LBLegMid = new ModelRenderer(this, 45, 23);
	      LBLegMid.addBox(0.6F, 2.5F, -6.3F, 2, 6, 2);
	      LBLegMid.setRotationPoint(4F, 12.3F, 11F);
	      LBLegMid.setTextureSize(64, 32);
	      LBLegMid.mirror = true;
	      setRotation(LBLegMid, 0.9424778F, 0F, 0F);
	      LFPaw = new ModelRenderer(this, 0, 20);
	      LFPaw.addBox(0F, 10F, -2.5F, 3, 1, 4);
	      LFPaw.setRotationPoint(4F, 13F, 1F);
	      LFPaw.setTextureSize(64, 32);
	      LFPaw.mirror = true;
	      setRotation(LFPaw, 0F, 0F, 0F);
	      LFShoulder = new ModelRenderer(this, 0, 26);
	      LFShoulder.addBox(0F, -1.5F, -1.5F, 4, 3, 3);
	      LFShoulder.setRotationPoint(3F, 13F, 1F);
	      LFShoulder.setTextureSize(64, 32);
	      LFShoulder.mirror = true;
	      setRotation(LFShoulder, 0F, 0F, 0F);
	      LFLeg = new ModelRenderer(this, 16, 21);
	      LFLeg.addBox(0.5F, 1.5F, -1F, 2, 9, 2);
	      LFLeg.setRotationPoint(4F, 13F, 1F);
	      LFLeg.setTextureSize(64, 32);
	      LFLeg.mirror = true;
	      setRotation(LFLeg, 0F, 0F, 0F);
	      RFShoulder = new ModelRenderer(this, 0, 26);
	      RFShoulder.addBox(-3F, -1.5F, -1.5F, 4, 3, 3);
	      RFShoulder.setRotationPoint(-4F, 13F, 1F);
	      RFShoulder.setTextureSize(64, 32);
	      RFShoulder.mirror = true;
	      setRotation(RFShoulder, 0F, 0F, 0F);
	      RFLeg = new ModelRenderer(this, 16, 21);
	      RFLeg.addBox(-2.5F, 1.5F, -1F, 2, 9, 2);
	      RFLeg.setRotationPoint(-4F, 13F, 1F);
	      RFLeg.setTextureSize(64, 32);
	      RFLeg.mirror = true;
	      setRotation(RFLeg, 0F, 0F, 0F);
	      LBPaw = new ModelRenderer(this, 0, 20);
	      LBPaw.addBox(0.1F, 10F, -1.5F, 3, 1, 4);
	      LBPaw.setRotationPoint(4F, 13F, 11F);
	      LBPaw.setTextureSize(64, 32);
	      LBPaw.mirror = true;
	      setRotation(LBPaw, 0F, 0F, 0F);
	      LBThigh = new ModelRenderer(this, 25, 24);
	      LBThigh.addBox(-1F, -1.5F, -1.5F, 4, 4, 4);
	      LBThigh.setRotationPoint(4F, 12.3F, 11F);
	      LBThigh.setTextureSize(64, 32);
	      LBThigh.mirror = true;
	      setRotation(LBThigh, 0F, 0F, 0F);
	      LBLegLow = new ModelRenderer(this, 56, 23);
	      LBLegLow.addBox(0.6F, 3.7F, 8.5F, 2, 3, 1);
	      LBLegLow.setRotationPoint(4F, 12.3F, 11F);
	      LBLegLow.setTextureSize(64, 32);
	      LBLegLow.mirror = true;
	      setRotation(LBLegLow, -0.7504916F, 0F, 0F);
	      LBLegTop = new ModelRenderer(this, 45, 22);
	      LBLegTop.addBox(0.5F, -0.5F, -0.5F, 2, 7, 3);
	      LBLegTop.setRotationPoint(4F, 12.3F, 11F);
	      LBLegTop.setTextureSize(64, 32);
	      LBLegTop.mirror = true;
	      setRotation(LBLegTop, -0.5235988F, 0F, 0F);
	      RFPaw = new ModelRenderer(this, 0, 20);
	      RFPaw.addBox(-3F, 10F, -2.5F, 3, 1, 4);
	      RFPaw.setRotationPoint(-4F, 13F, 1F);
	      RFPaw.setTextureSize(64, 32);
	      RFPaw.mirror = true;
	      setRotation(RFPaw, 0F, 0F, 0F);
	      RBThigh = new ModelRenderer(this, 25, 24);
	      RBThigh.addBox(-3F, -1.5F, -1.5F, 4, 4, 4);
	      RBThigh.setRotationPoint(-4F, 12.3F, 11F);
	      RBThigh.setTextureSize(64, 32);
	      RBThigh.mirror = true;
	      setRotation(RBThigh, 0F, 0F, 0F);
	      RBLegTop = new ModelRenderer(this, 45, 22);
	      RBLegTop.addBox(-2.5F, -0.5F, -0.5F, 2, 7, 3);
	      RBLegTop.setRotationPoint(-4F, 12.3F, 11F);
	      RBLegTop.setTextureSize(64, 32);
	      RBLegTop.mirror = true;
	      setRotation(RBLegTop, -0.5235988F, 0F, 0F);
	      RBLegMid = new ModelRenderer(this, 45, 23);
	      RBLegMid.addBox(-2.6F, 2.5F, -6.3F, 2, 6, 2);
	      RBLegMid.setRotationPoint(-4F, 12.3F, 11F);
	      RBLegMid.setTextureSize(64, 32);
	      RBLegMid.mirror = true;
	      setRotation(RBLegMid, 0.9424778F, 0F, 0F);
	      RBLegLow = new ModelRenderer(this, 56, 23);
	      RBLegLow.addBox(-2.6F, 3.7F, 8.5F, 2, 3, 1);
	      RBLegLow.setRotationPoint(-4F, 12.3F, 11F);
	      RBLegLow.setTextureSize(64, 32);
	      RBLegLow.mirror = true;
	      setRotation(RBLegLow, -0.7504916F, 0F, 0F);
	      RBPaw = new ModelRenderer(this, 0, 20);
	      RBPaw.addBox(-3.1F, 10F, -1.5F, 3, 1, 4);
	      RBPaw.setRotationPoint(-4F, 13F, 11F);
	      RBPaw.setTextureSize(64, 32);
	      RBPaw.mirror = true;
	      setRotation(RBPaw, 0F, 0F, 0F);
	  }
	  
	  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	  {
	    super.render(entity, f, f1, f2, f3, f4, f5);
	    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	    MuzzleLow.render(f5);
	    MuzzleTop.render(f5);
	    EarRight.render(f5);
	    EarLeft.render(f5);
	    HeadBase.render(f5);
	    BodyUp2.render(f5);
	    BodyBase.render(f5);
	    BodyUp1.render(f5);
	    LBLegMid.render(f5);
	    LFPaw.render(f5);
	    LFShoulder.render(f5);
	    LFLeg.render(f5);
	    RFShoulder.render(f5);
	    RFLeg.render(f5);
	    LBPaw.render(f5);
	    LBThigh.render(f5);
	    LBLegLow.render(f5);
	    LBLegTop.render(f5);
	    RFPaw.render(f5);
	    RBThigh.render(f5);
	    RBLegTop.render(f5);
	    RBLegMid.render(f5);
	    RBLegLow.render(f5);
	    RBPaw.render(f5);
	  }
	  
	  private void setRotation(ModelRenderer model, float x, float y, float z)
	  {
	    model.rotateAngleX = x;
	    model.rotateAngleY = y;
	    model.rotateAngleZ = z;
	  }
	  
	  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity ent)
	  {
		  float var8 = 0.01F * (float)(ent.getEntityId() % 10);
		  
		  this.MuzzleLow.rotateAngleX = 0.4363323F + (f4 / 57.29578F) + (MathHelper.sin((float)ent.ticksExisted * var8) * 4.5F * (float)Math.PI / 180.0F);
		  this.MuzzleLow.rotateAngleY = f3 / 57.29578F;
		  this.MuzzleTop.rotateAngleX = f4 / 57.29578F;
		  this.MuzzleTop.rotateAngleY = f3 / 57.29578F;
		  this.EarRight.rotateAngleX  = f4 / 57.29578F;
		  this.EarRight.rotateAngleY  = f3 / 57.29578F;
		  this.EarRight.rotateAngleZ  = MathHelper.cos((float)ent.ticksExisted * var8) * 2.5F * (float)Math.PI / 180.0F;
		  this.EarLeft.rotateAngleX   = f4 / 57.29578F;
		  this.EarLeft.rotateAngleY   = f3 / 57.29578F;
		  this.EarLeft.rotateAngleZ   = MathHelper.cos((float)ent.ticksExisted * var8) * 2.5F * (float)Math.PI / 180.0F;
		  this.HeadBase.rotateAngleX  = f4 / 57.29578F;
		  this.HeadBase.rotateAngleY  = f3 / 57.29578F;
		  
		  this.LBLegMid.rotateAngleX    = 0.9424778F + MathHelper.cos(f * 0.5662F) * 1.4F * f1 * 0.5F;
		  this.LFPaw.rotateAngleX       = MathHelper.cos(f * 0.5662F + (float)Math.PI) * 1.4F * f1 * 0.5F;
		  this.LFLeg.rotateAngleX       = MathHelper.cos(f * 0.5662F + (float)Math.PI) * 1.4F * f1 * 0.5F;
		  this.LBPaw.rotateAngleX       = MathHelper.cos(f * 0.5662F) * 1.4F * f1 * 0.5F;
		  this.LBThigh.rotateAngleX     = MathHelper.cos(f * 0.5662F) * 1.4F * f1 * 0.5F;
		  this.LBLegLow.rotateAngleX    = -0.7504916F + MathHelper.cos(f * 0.5662F) * 1.4F * f1 * 0.5F;
		  this.LBLegTop.rotateAngleX    = -0.5235988F + MathHelper.cos(f * 0.5662F) * 1.4F * f1 * 0.5F;
		  
		  this.RFLeg.rotateAngleX       = MathHelper.cos(f * 0.5662F) * 1.4F * f1 * 0.5F;
		  this.RFPaw.rotateAngleX       = MathHelper.cos(f * 0.5662F) * 1.4F * f1 * 0.5F;
		  this.RBThigh.rotateAngleX     = MathHelper.cos(f * 0.5662F + (float)Math.PI) * 1.4F * f1 * 0.5F;
		  this.RBLegTop.rotateAngleX    = -0.5235988F + MathHelper.cos(f * 0.5662F + (float)Math.PI) * 1.4F * f1 * 0.5F;
		  this.RBLegMid.rotateAngleX    = 0.9424778F + MathHelper.cos(f * 0.5662F + (float)Math.PI) * 1.4F * f1 * 0.5F;
		  this.RBLegLow.rotateAngleX    = -0.7504916F + MathHelper.cos(f * 0.5662F + (float)Math.PI) * 1.4F * f1 * 0.5F;
		  this.RBPaw.rotateAngleX       = MathHelper.cos(f * 0.5662F + (float)Math.PI) * 1.4F * f1 * 0.5F;
	  }

	}
