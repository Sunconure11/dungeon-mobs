package com.gw.dm.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelUmberHulk extends ModelBase
{
	  //fields
	    ModelRenderer RightFoot;
	    ModelRenderer LeftFoot;
	    ModelRenderer LeftLegLow;
	    ModelRenderer RightLegLow;
	    ModelRenderer LeftLegMid;
	    ModelRenderer RightLegMid;
	    ModelRenderer RightLegHigh;
	    ModelRenderer BodyMain;
	    ModelRenderer BodyFront;
	    ModelRenderer BodyRear;
	    ModelRenderer BodyTop;
	    ModelRenderer Shoulders;
	    ModelRenderer BodyBack;
	    ModelRenderer Head;
	    ModelRenderer LeftPincer1;
	    //ModelRenderer LeftPincer2;
	    ModelRenderer RightPincer1;
	    //ModelRenderer RightPincer2;
	    ModelRenderer LeftArmBase;
	    ModelRenderer LeftArmLow;
	    ModelRenderer RightArmBase;
	    ModelRenderer LeftArmCara;
	    ModelRenderer LeftHand;
	    ModelRenderer RightArmCara;
	    ModelRenderer RightArmLow;
	    ModelRenderer RightHand;
	    ModelRenderer LittleEyeLeft;
	    ModelRenderer BigEyeLeft;
	    ModelRenderer LittleEyeRight;
	    ModelRenderer BigEyeRight;
	    ModelRenderer LeftLegHigh;
	  
	  public ModelUmberHulk()
	  {
	    textureWidth = 64;
	    textureHeight = 32;
	    
	      RightFoot = new ModelRenderer(this, 0, 0);
	      RightFoot.addBox(-3F, 15F, -11F, 6, 6, 6);
	      RightFoot.setRotationPoint(-8F, 3F, 15F);
	      RightFoot.setTextureSize(64, 32);
	      RightFoot.mirror = true;
	      setRotation(RightFoot, 0F, 0F, 0F);
	      LeftFoot = new ModelRenderer(this, 0, 0);
	      LeftFoot.addBox(-3F, 15F, -12F, 6, 6, 6);
	      LeftFoot.setRotationPoint(8F, 3F, 15F);
	      LeftFoot.setTextureSize(64, 32);
	      LeftFoot.mirror = true;
	      setRotation(LeftFoot, 0F, 0F, 0F);
	      LeftLegLow = new ModelRenderer(this, 0, 0);
	      LeftLegLow.addBox(-2.5F, 8F, -1.5F, 5, 10, 4);
	      LeftLegLow.setRotationPoint(-8F, 3F, 15F);
	      LeftLegLow.setTextureSize(64, 32);
	      LeftLegLow.mirror = true;
	      setRotation(LeftLegLow, -0.4363323F, 0F, 0F);
	      RightLegLow = new ModelRenderer(this, 0, 0);
	      RightLegLow.addBox(-2.5F, 8F, -2.5F, 5, 10, 4);
	      RightLegLow.setRotationPoint(8F, 3F, 15F);
	      RightLegLow.setTextureSize(64, 32);
	      RightLegLow.mirror = true;
	      setRotation(RightLegLow, -0.4363323F, 0F, 0F);
	      LeftLegMid = new ModelRenderer(this, 0, 0);
	      LeftLegMid.addBox(-2F, 7F, -12F, 4, 3, 11);
	      LeftLegMid.setRotationPoint(-8F, 3F, 15F);
	      LeftLegMid.setTextureSize(64, 32);
	      LeftLegMid.mirror = true;
	      setRotation(LeftLegMid, -0.1745329F, 0F, 0F);
	      RightLegMid = new ModelRenderer(this, 0, 0);
	      RightLegMid.addBox(-2F, 7F, -12F, 4, 3, 11);
	      RightLegMid.setRotationPoint(8F, 3F, 15F);
	      RightLegMid.setTextureSize(64, 32);
	      RightLegMid.mirror = true;
	      setRotation(RightLegMid, -0.1745329F, 0F, 0F);
	      RightLegHigh = new ModelRenderer(this, 0, 0);
	      RightLegHigh.addBox(-2F, 0F, -14F, 4, 3, 14);
	      RightLegHigh.setRotationPoint(8F, 3F, 15F);
	      RightLegHigh.setTextureSize(64, 32);
	      RightLegHigh.mirror = true;
	      setRotation(RightLegHigh, 0.3490659F, 0F, 0F);
	      BodyMain = new ModelRenderer(this, 3, 0);
	      BodyMain.addBox(-11F, 0F, 0F, 22, 20, 3);
	      BodyMain.setRotationPoint(0F, -8F, 1F);
	      BodyMain.setTextureSize(64, 32);
	      BodyMain.mirror = true;
	      setRotation(BodyMain, 0.7853982F, 0F, 0F);
	      BodyFront = new ModelRenderer(this, 0, 0);
	      BodyFront.addBox(-7F, 0F, 0F, 14, 15, 3);
	      BodyFront.setRotationPoint(0F, -6F, 1F);
	      BodyFront.setTextureSize(64, 32);
	      BodyFront.mirror = true;
	      setRotation(BodyFront, 0.7853982F, 0F, 0F);
	      BodyRear = new ModelRenderer(this, 0, 0);
	      BodyRear.addBox(-5F, 0F, 0F, 10, 8, 7);
	      BodyRear.setRotationPoint(0F, -5F, 11F);
	      BodyRear.setTextureSize(64, 32);
	      BodyRear.mirror = true;
	      setRotation(BodyRear, 0F, 0F, 0F);
	      BodyTop = new ModelRenderer(this, 0, 0);
	      BodyTop.addBox(-5F, 0F, 0F, 10, 4, 16);
	      BodyTop.setRotationPoint(0F, -12F, 2F);
	      BodyTop.setTextureSize(64, 32);
	      BodyTop.mirror = true;
	      setRotation(BodyTop, -0.4537856F, 0F, 0F);
	      Shoulders = new ModelRenderer(this, 0, 0);
	      Shoulders.addBox(-12F, 0F, 0F, 24, 4, 4);
	      Shoulders.setRotationPoint(0F, -11F, 1F);
	      Shoulders.setTextureSize(64, 32);
	      Shoulders.mirror = true;
	      setRotation(Shoulders, 0F, 0F, 0F);
	      BodyBack = new ModelRenderer(this, 0, 0);
	      BodyBack.addBox(-7F, 0F, 0F, 14, 17, 3);
	      BodyBack.setRotationPoint(0F, -7F, 4F);
	      BodyBack.setTextureSize(64, 32);
	      BodyBack.mirror = true;
	      setRotation(BodyBack, 0.7853982F, 0F, 0F);
	      Head = new ModelRenderer(this, 0, 0);
	      Head.addBox(-5F, -2F, -9F, 10, 9, 9);
	      Head.setRotationPoint(0F, -10F, 2F);
	      Head.setTextureSize(64, 32);
	      Head.mirror = true;
	      setRotation(Head, 0F, 0F, 0F);
	      LeftPincer1 = new ModelRenderer(this, 23, 15);
	      LeftPincer1.addBox(-1F, 4F, -18F, 2, 2, 10);
	      LeftPincer1.setRotationPoint(0F, -10F, 2F);
	      LeftPincer1.setTextureSize(64, 32);
	      LeftPincer1.mirror = false;
	      setRotation(LeftPincer1, 0F, -0.5759587F, 0F);
	      
	      /*
	      LeftPincer2 = new ModelRenderer(this, 25, 13);
	      LeftPincer2.addBox(12F, 4F, -21.3F, 2, 2, 10);
	      LeftPincer2.setRotationPoint(0F, -10F, 2F);
	      LeftPincer2.setTextureSize(64, 32);
	      LeftPincer2.mirror = true;
	      setRotation(LeftPincer2, 0F, 0.2617994F, 0F);
	      LeftPincer2.mirror = false;
	      */
	      
	      RightPincer1 = new ModelRenderer(this, 25, 13);
	      RightPincer1.addBox(-1F, 4F, -18F, 2, 2, 10);
	      RightPincer1.setRotationPoint(0F, -10F, 2F);
	      RightPincer1.setTextureSize(64, 32);
	      RightPincer1.mirror = true;
	      setRotation(RightPincer1, 0F, 0.5759587F, 0F);
	      
	      /*
	      RightPincer2 = new ModelRenderer(this, 25, 13);
	      RightPincer2.addBox(-14F, 4F, -21.3F, 2, 2, 10);
	      RightPincer2.setRotationPoint(0F, -10F, 2F);
	      RightPincer2.setTextureSize(64, 32);
	      RightPincer2.mirror = true;
	      setRotation(RightPincer2, 0F, -0.2617994F, 0F);
	      */
	      
	      LeftArmBase = new ModelRenderer(this, 0, 0);
	      LeftArmBase.addBox(-1.5F, 0F, -1.5F, 3, 14, 3);
	      LeftArmBase.setRotationPoint(10F, -8F, 2F);
	      LeftArmBase.setTextureSize(64, 32);
	      LeftArmBase.mirror = true;
	      setRotation(LeftArmBase, -0.3490659F, 0F, 0F);
	      LeftArmLow = new ModelRenderer(this, 0, 0);
	      LeftArmLow.addBox(-1.5F, 3.5F, 10.7F, 3, 16, 3);
	      LeftArmLow.setRotationPoint(10F, -8F, 2F);
	      LeftArmLow.setTextureSize(64, 32);
	      LeftArmLow.mirror = true;
	      setRotation(LeftArmLow, -1.570796F, 0F, 0F);
	      RightArmBase = new ModelRenderer(this, 0, 0);
	      RightArmBase.addBox(-1.5F, 0F, -1.5F, 3, 14, 3);
	      RightArmBase.setRotationPoint(-10F, -8F, 2F);
	      RightArmBase.setTextureSize(64, 32);
	      RightArmBase.mirror = true;
	      setRotation(RightArmBase, -0.3490659F, 0F, 0F);
	      LeftArmCara = new ModelRenderer(this, 0, 0);
	      LeftArmCara.addBox(1.5F, 3.5F, 8.7F, 1, 16, 7);
	      LeftArmCara.setRotationPoint(10F, -8F, 2F);
	      LeftArmCara.setTextureSize(64, 32);
	      LeftArmCara.mirror = true;
	      setRotation(LeftArmCara, -1.570796F, 0F, 0F);
	      LeftHand = new ModelRenderer(this, 0, 0);
	      LeftHand.addBox(-2F, 19.5F, 10.2F, 4, 4, 4);
	      LeftHand.setRotationPoint(10F, -8F, 2F);
	      LeftHand.setTextureSize(64, 32);
	      LeftHand.mirror = true;
	      setRotation(LeftHand, -1.570796F, 0F, 0F);
	      RightArmCara = new ModelRenderer(this, 0, 0);
	      RightArmCara.addBox(-2.5F, 3.5F, 8.7F, 1, 16, 7);
	      RightArmCara.setRotationPoint(-10F, -8F, 2F);
	      RightArmCara.setTextureSize(64, 32);
	      RightArmCara.mirror = true;
	      setRotation(RightArmCara, -1.570796F, 0F, 0F);
	      RightArmLow = new ModelRenderer(this, 0, 0);
	      RightArmLow.addBox(-1.5F, 3.5F, 10.7F, 3, 16, 3);
	      RightArmLow.setRotationPoint(-10F, -8F, 2F);
	      RightArmLow.setTextureSize(64, 32);
	      RightArmLow.mirror = true;
	      setRotation(RightArmLow, -1.570796F, 0F, 0F);
	      RightHand = new ModelRenderer(this, 0, 0);
	      RightHand.addBox(-2F, 19.5F, 10.2F, 4, 4, 4);
	      RightHand.setRotationPoint(-10F, -8F, 2F);
	      RightHand.setTextureSize(64, 32);
	      RightHand.mirror = true;
	      setRotation(RightHand, -1.570796F, 0F, 0F);
	      LittleEyeLeft = new ModelRenderer(this, 0, 28);
	      LittleEyeLeft.addBox(-4F, -2.5F, -9.5F, 2, 2, 2);
	      LittleEyeLeft.setRotationPoint(0F, -10F, 2F);
	      LittleEyeLeft.setTextureSize(64, 32);
	      LittleEyeLeft.mirror = true;
	      setRotation(LittleEyeLeft, 0F, 0F, 0F);
	      BigEyeLeft = new ModelRenderer(this, 9, 26);
	      BigEyeLeft.addBox(-4.4F, 0.5F, -9.5F, 4, 4, 2);
	      BigEyeLeft.setRotationPoint(0F, -10F, 2F);
	      BigEyeLeft.setTextureSize(64, 32);
	      BigEyeLeft.mirror = true;
	      setRotation(BigEyeLeft, 0F, 0F, 0F);
	      LittleEyeRight = new ModelRenderer(this, 0, 28);
	      LittleEyeRight.addBox(2F, -2.5F, -9.5F, 2, 2, 2);
	      LittleEyeRight.setRotationPoint(0F, -10F, 2F);
	      LittleEyeRight.setTextureSize(64, 32);
	      LittleEyeRight.mirror = true;
	      setRotation(LittleEyeRight, 0F, 0F, 0F);
	      BigEyeRight = new ModelRenderer(this, 9, 26);
	      BigEyeRight.addBox(0.4F, 0.5F, -9.5F, 4, 4, 2);
	      BigEyeRight.setRotationPoint(0F, -10F, 2F);
	      BigEyeRight.setTextureSize(64, 32);
	      BigEyeRight.mirror = true;
	      setRotation(BigEyeRight, 0F, 0F, 0F);
	      BigEyeRight.mirror = false;
	      LeftLegHigh = new ModelRenderer(this, 0, 0);
	      LeftLegHigh.addBox(-2F, 0F, -14F, 4, 3, 14);
	      LeftLegHigh.setRotationPoint(-8F, 3F, 15F);
	      LeftLegHigh.setTextureSize(64, 32);
	      LeftLegHigh.mirror = true;
	      setRotation(LeftLegHigh, 0.3490659F, 0F, 0F);
	  }
	  
	  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	  {
	    super.render(entity, f, f1, f2, f3, f4, f5);
	    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	    RightFoot.render(f5);
	    LeftFoot.render(f5);
	    LeftLegLow.render(f5);
	    RightLegLow.render(f5);
	    LeftLegMid.render(f5);
	    RightLegMid.render(f5);
	    RightLegHigh.render(f5);
	    BodyMain.render(f5);
	    BodyFront.render(f5);
	    BodyRear.render(f5);
	    BodyTop.render(f5);
	    Shoulders.render(f5);
	    BodyBack.render(f5);
	    Head.render(f5);
	    LeftPincer1.render(f5);
	    //LeftPincer2.render(f5);
	    RightPincer1.render(f5);
	    //RightPincer2.render(f5);
	    LeftArmBase.render(f5);
	    LeftArmLow.render(f5);
	    RightArmBase.render(f5);
	    LeftArmCara.render(f5);
	    LeftHand.render(f5);
	    RightArmCara.render(f5);
	    RightArmLow.render(f5);
	    RightHand.render(f5);
	    LittleEyeLeft.render(f5);
	    BigEyeLeft.render(f5);
	    LittleEyeRight.render(f5);
	    BigEyeRight.render(f5);
	    LeftLegHigh.render(f5);
	  }
	  
	  private void setRotation(ModelRenderer model, float x, float y, float z)
	  {
	    model.rotateAngleX = x;
	    model.rotateAngleY = y;
	    model.rotateAngleZ = z;
	  }
	  
	  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity ent)
	  {
		  //super.setRotationAngles(f, f1, f2, f3, f4, f5, ent);
		  
		  this.Head.rotateAngleY = f3 / 57.29578F;
		  this.Head.rotateAngleX = f4 / 57.29578F;
		  this.LittleEyeRight.rotateAngleY = f3 / 57.29578F;
		  this.LittleEyeRight.rotateAngleX = f4 / 57.29578F;
		  this.BigEyeRight.rotateAngleY = f3 / 57.29578F;
		  this.BigEyeRight.rotateAngleX = f4 / 57.29578F;
		  this.LittleEyeLeft.rotateAngleY = f3 / 57.29578F;
		  this.LittleEyeLeft.rotateAngleX = f4 / 57.29578F;
		  this.BigEyeLeft.rotateAngleY = f3 / 57.29578F;
		  this.BigEyeLeft.rotateAngleX = f4 / 57.29578F;
		  
		  this.LeftPincer1.rotateAngleY = -0.5759587F + (f3 / 57.29578F);
		  this.LeftPincer1.rotateAngleX = f4 / 57.29578F;
		  this.RightPincer1.rotateAngleY = 0.5759587F + (f3 / 57.29578F);
		  this.RightPincer1.rotateAngleX = f4 / 57.29578F;
		  
		  this.RightFoot.rotateAngleX    = MathHelper.cos(f * 0.5662F) * 1.4F * f1;
		  this.LeftFoot.rotateAngleX     = MathHelper.cos(f * 0.5662F + (float)Math.PI) * 1.4F * f1;
		  this.RightLegLow.rotateAngleX  = -0.4363323F - MathHelper.cos(f * 0.5662F) * 1.4F * f1;
		  this.LeftLegLow.rotateAngleX   = -0.4363323F - MathHelper.cos(f * 0.5662F + (float)Math.PI) * 1.4F * f1;
		  this.RightLegMid.rotateAngleX  = -0.1745329F - MathHelper.cos(f * 0.5662F) * 1.4F * f1;
		  this.LeftLegMid.rotateAngleX   = -0.1745329F - MathHelper.cos(f * 0.5662F + (float)Math.PI) * 1.4F * f1;
		  this.RightLegHigh.rotateAngleX =  0.3490659F - MathHelper.cos(f * 0.5662F) * 1.4F * f1;
		  this.LeftLegHigh.rotateAngleX  =  0.3490659F - MathHelper.cos(f * 0.5662F + (float)Math.PI) * 1.4F * f1;
		  
		  this.LeftArmBase.rotateAngleX  = -0.3490659F - MathHelper.cos(f * 0.5662F) * 1.4F * f1;
		  this.LeftArmLow.rotateAngleX   =  -1.570796F - MathHelper.cos(f * 0.5662F) * 1.4F * f1;
		  this.LeftArmCara.rotateAngleX  =  -1.570796F - MathHelper.cos(f * 0.5662F) * 1.4F * f1;
		  this.LeftHand.rotateAngleX     =  -1.570796F - MathHelper.cos(f * 0.5662F) * 1.4F * f1;
		  this.RightArmBase.rotateAngleX = -0.3490659F - MathHelper.cos(f * 0.5662F + (float)Math.PI) * 1.4F * f1;
		  this.RightArmLow.rotateAngleX  =  -1.570796F - MathHelper.cos(f * 0.5662F + (float)Math.PI) * 1.4F * f1;
		  this.RightArmCara.rotateAngleX =  -1.570796F - MathHelper.cos(f * 0.5662F + (float)Math.PI) * 1.4F * f1;
		  this.RightHand.rotateAngleX    =  -1.570796F - MathHelper.cos(f * 0.5662F + (float)Math.PI) * 1.4F * f1;
		  
		  /*
		  this.RightPincer2.rotateAngleY = -0.2617994F + ((f3 / 57.29578F) * 1.333F);
		  this.RightPincer2.rotateAngleX = ((f4 / 57.29578F) * 1.333F);
		  this.LeftPincer2.rotateAngleY = 0.2617994F + ((f3 / 57.29578F) * 1.333F);
		  this.LeftPincer2.rotateAngleX = ((f4 / 57.29578F) * 1.333F);
		  */
	  }

	}
