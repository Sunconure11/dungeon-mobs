package com.gw.dm.model;


import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelCockatrice extends ModelBase
{
	  //fields
	   // ModelRenderer LeftClawBack;
	    //ModelRenderer LeftClawRight;
	    ModelRenderer LeftLegTop;
	    ModelRenderer LeftLegLow;
	    //ModelRenderer LeftClawLeft;
	    //ModelRenderer LeftClawCenter;
	    //ModelRenderer RightClawBack;
	    //ModelRenderer RightClawRight;
	    //ModelRenderer RightClawCenter;
	    //ModelRenderer RightClawLeft;
	    ModelRenderer RightLegLow;
	    ModelRenderer BodyFrontTop;
	    ModelRenderer RightLegTop;
	    ModelRenderer RightThigh;
	    ModelRenderer LeftThigh;
	    ModelRenderer Tail3;
	    ModelRenderer BodyFrontLow;
	    ModelRenderer BodyFrontMid;
	    ModelRenderer NeckTop;
	    //ModelRenderer BeakLow;
	    ModelRenderer NeckBase;
	    ModelRenderer HeadBase;
	    ModelRenderer BeakBase;
	    ModelRenderer BeakNose;
	    ModelRenderer BodyBase;
	    ModelRenderer TailBase;
	    ModelRenderer Tail1;
	    ModelRenderer Tail2;
	    ModelRenderer RightWing;
	    ModelRenderer LeftWing;
	  
	  public ModelCockatrice()
	  {
	    textureWidth = 64;
	    textureHeight = 32;
	    
	    /*
	      LeftClawBack = new ModelRenderer(this, 0, 25);
	      LeftClawBack.addBox(-0.5F, 7F, 0F, 1, 1, 2);
	      LeftClawBack.setRotationPoint(4F, 16F, 0F);
	      LeftClawBack.setTextureSize(64, 32);
	      LeftClawBack.mirror = true;
	      setRotation(LeftClawBack, 0F, 0F, 0F);
	      LeftClawRight = new ModelRenderer(this, 0, 28);
	      LeftClawRight.addBox(-0.5F, 7F, -3F, 1, 1, 3);
	      LeftClawRight.setRotationPoint(4F, 16F, 0F);
	      LeftClawRight.setTextureSize(64, 32);
	      LeftClawRight.mirror = true;
	      setRotation(LeftClawRight, 0F, 0.6981317F, 0F);
	      LeftClawLeft = new ModelRenderer(this, 0, 28);
	      LeftClawLeft.addBox(-0.5F, 7F, -3F, 1, 1, 3);
	      LeftClawLeft.setRotationPoint(4F, 16F, 0F);
	      LeftClawLeft.setTextureSize(64, 32);
	      LeftClawLeft.mirror = true;
	      setRotation(LeftClawLeft, 0F, -0.6981317F, 0F);
	      LeftClawCenter = new ModelRenderer(this, 0, 28);
	      LeftClawCenter.addBox(-0.5F, 7F, -3F, 1, 1, 3);
	      LeftClawCenter.setRotationPoint(4F, 16F, 0F);
	      LeftClawCenter.setTextureSize(64, 32);
	      LeftClawCenter.mirror = true;
	      setRotation(LeftClawCenter, 0F, 0F, 0F);
	      RightClawBack = new ModelRenderer(this, 0, 25);
	      RightClawBack.addBox(-0.5F, 7F, 0F, 1, 1, 2);
	      RightClawBack.setRotationPoint(-3.4F, 16F, 0F);
	      RightClawBack.setTextureSize(64, 32);
	      RightClawBack.mirror = true;
	      setRotation(RightClawBack, 0F, 0F, 0F);
	      RightClawRight = new ModelRenderer(this, 0, 28);
	      RightClawRight.addBox(-0.5F, 7F, -3F, 1, 1, 3);
	      RightClawRight.setRotationPoint(-3.4F, 16F, 0F);
	      RightClawRight.setTextureSize(64, 32);
	      RightClawRight.mirror = true;
	      setRotation(RightClawRight, 0F, 0.6981317F, 0F);
	      RightClawCenter = new ModelRenderer(this, 0, 28);
	      RightClawCenter.addBox(-0.5F, 7F, -3F, 1, 1, 3);
	      RightClawCenter.setRotationPoint(-3.4F, 16F, 0F);
	      RightClawCenter.setTextureSize(64, 32);
	      RightClawCenter.mirror = true;
	      setRotation(RightClawCenter, 0F, 0F, 0F);
	      RightClawLeft = new ModelRenderer(this, 0, 28);
	      RightClawLeft.addBox(-0.5F, 7F, -3F, 1, 1, 3);
	      RightClawLeft.setRotationPoint(-3.4F, 16F, 0F);
	      RightClawLeft.setTextureSize(64, 32);
	      RightClawLeft.mirror = true;
	      setRotation(RightClawLeft, 0F, -0.6981317F, 0F);
	      */
	      LeftLegTop = new ModelRenderer(this, 60, 26);
	      LeftLegTop.addBox(-0.5F, 0.2F, -0.5F, 1, 4, 1);
	      LeftLegTop.setRotationPoint(4F, 16F, 0F);
	      LeftLegTop.setTextureSize(64, 32);
	      LeftLegTop.mirror = true;
	      setRotation(LeftLegTop, 0.4363323F, 0F, 0F);
	      LeftLegLow = new ModelRenderer(this, 60, 26);
	      LeftLegLow.addBox(-0.5F, 1.9F, 2.8F, 1, 5, 1);
	      LeftLegLow.setRotationPoint(4F, 16F, 0F);
	      LeftLegLow.setTextureSize(64, 32);
	      LeftLegLow.mirror = true;
	      setRotation(LeftLegLow, -0.5235988F, 0F, 0F);
	      RightLegLow = new ModelRenderer(this, 60, 26);
	      RightLegLow.addBox(-0.5F, 1.9F, 2.8F, 1, 5, 1);
	      RightLegLow.setRotationPoint(-3.4F, 16F, 0F);
	      RightLegLow.setTextureSize(64, 32);
	      RightLegLow.mirror = true;
	      setRotation(RightLegLow, -0.5235988F, 0F, 0F);
	      BodyFrontTop = new ModelRenderer(this, 40, 0);
	      BodyFrontTop.addBox(1.5F, 0F, 0F, 4, 4, 4);
	      BodyFrontTop.setRotationPoint(-3.2F, 9F, -7F);
	      BodyFrontTop.setTextureSize(64, 32);
	      BodyFrontTop.mirror = true;
	      setRotation(BodyFrontTop, 0F, 0F, 0F);
	      RightLegTop = new ModelRenderer(this, 60, 26);
	      RightLegTop.addBox(-0.5F, 0.2F, -0.5F, 1, 4, 1);
	      RightLegTop.setRotationPoint(-3.4F, 16F, 0F);
	      RightLegTop.setTextureSize(64, 32);
	      RightLegTop.mirror = true;
	      setRotation(RightLegTop, 0.4363323F, 0F, 0F);
	      RightThigh = new ModelRenderer(this, 0, 0);
	      RightThigh.addBox(0F, 0F, 0F, 2, 5, 3);
	      RightThigh.setRotationPoint(-4.2F, 14F, -2F);
	      RightThigh.setTextureSize(64, 32);
	      RightThigh.mirror = true;
	      setRotation(RightThigh, 0.4363323F, 0F, 0F);
	      LeftThigh = new ModelRenderer(this, 0, 0);
	      LeftThigh.addBox(0F, 0F, 0F, 2, 5, 3);
	      LeftThigh.setRotationPoint(2.8F, 14F, -2F);
	      LeftThigh.setTextureSize(64, 32);
	      LeftThigh.mirror = true;
	      setRotation(LeftThigh, 0.4363323F, 0F, 0F);
	      Tail3 = new ModelRenderer(this, 48, 17);
	      Tail3.addBox(0F, 0F, 0F, 2, 2, 6);
	      Tail3.setRotationPoint(-0.7F, 16.2F, 10.3F);
	      Tail3.setTextureSize(64, 32);
	      Tail3.mirror = true;
	      setRotation(Tail3, -0.8901179F, 0F, 0F);
	      BodyFrontLow = new ModelRenderer(this, 40, 0);
	      BodyFrontLow.addBox(0.5F, 0F, 0F, 6, 5, 5);
	      BodyFrontLow.setRotationPoint(-3.2F, 11F, -4F);
	      BodyFrontLow.setTextureSize(64, 32);
	      BodyFrontLow.mirror = true;
	      setRotation(BodyFrontLow, 0F, 0F, 0F);
	      BodyFrontMid = new ModelRenderer(this, 40, 0);
	      BodyFrontMid.addBox(1F, 0F, 0F, 5, 5, 4);
	      BodyFrontMid.setRotationPoint(-3.2F, 10F, -6F);
	      BodyFrontMid.setTextureSize(64, 32);
	      BodyFrontMid.mirror = true;
	      setRotation(BodyFrontMid, 0F, 0F, 0F);
	      NeckTop = new ModelRenderer(this, 0, 16);
	      NeckTop.addBox(-1F, -8.5F, 2.9F, 2, 5, 2);
	      NeckTop.setRotationPoint(0.3F, 9.3F, -5.8F);
	      NeckTop.setTextureSize(64, 32);
	      NeckTop.mirror = true;
	      setRotation(NeckTop, 0.9599311F, 0F, 0F);
	      /*
	      BeakLow = new ModelRenderer(this, 22, 0);
	      BeakLow.addBox(2.3F, -1.5F, -4.9F, 3, 1, 3);
	      BeakLow.setRotationPoint(0.3F, 1.8F, -9.8F);
	      BeakLow.setTextureSize(64, 32);
	      BeakLow.mirror = true;
	      setRotation(BeakLow, 0.2617994F, 0.7853982F, 0.2617994F);
	      */
	      NeckBase = new ModelRenderer(this, 0, 16);
	      NeckBase.addBox(-1F, -6F, -1F, 2, 6, 2);
	      NeckBase.setRotationPoint(0.3F, 9.3F, -5.8F);
	      NeckBase.setTextureSize(64, 32);
	      NeckBase.mirror = true;
	      setRotation(NeckBase, 0.1745329F, 0F, 0F);
	      HeadBase = new ModelRenderer(this, 0, 8);
	      HeadBase.addBox(-2F, -2.5F, -4.9F, 4, 3, 5);
	      HeadBase.setRotationPoint(0.3F, 1.8F, -9.8F);
	      HeadBase.setTextureSize(64, 32);
	      HeadBase.mirror = true;
	      setRotation(HeadBase, 0F, 0F, 0F);
	      BeakBase = new ModelRenderer(this, 10, 0);
	      BeakBase.addBox(1.8F, -0.5F, -5.8F, 4, 1, 4);
	      BeakBase.setRotationPoint(0.3F, 1.8F, -9.8F);
	      BeakBase.setTextureSize(64, 32);
	      BeakBase.mirror = true;
	      setRotation(BeakBase, 0F, 0.7853982F, 0F);
	      BeakNose = new ModelRenderer(this, 10, 5);
	      BeakNose.addBox(-1F, 2.3F, -4.4F, 2, 2, 1);
	      BeakNose.setRotationPoint(0.3F, 1.8F, -9.8F);
	      BeakNose.setTextureSize(64, 32);
	      BeakNose.mirror = true;
	      setRotation(BeakNose, -0.8726646F, 0F, 0F);
	      BodyBase = new ModelRenderer(this, 40, 0);
	      BodyBase.addBox(0F, 0F, 0F, 7, 5, 5);
	      BodyBase.setRotationPoint(-3.2F, 12F, -2F);
	      BodyBase.setTextureSize(64, 32);
	      BodyBase.mirror = true;
	      setRotation(BodyBase, 0F, 0F, 0F);
	      TailBase = new ModelRenderer(this, 40, 0);
	      TailBase.addBox(0F, 0F, 0F, 5, 3, 3);
	      TailBase.setRotationPoint(-2.2F, 13F, 2F);
	      TailBase.setTextureSize(64, 32);
	      TailBase.mirror = true;
	      setRotation(TailBase, 0F, 0F, 0F);
	      Tail1 = new ModelRenderer(this, 48, 10);
	      Tail1.addBox(0F, 0F, 0F, 4, 2, 4);
	      Tail1.setRotationPoint(-1.7F, 13.5F, 4F);
	      Tail1.setTextureSize(64, 32);
	      Tail1.mirror = true;
	      setRotation(Tail1, -0.2094395F, 0F, 0F);
	      Tail2 = new ModelRenderer(this, 44, 25);
	      Tail2.addBox(0F, 0F, 0F, 3, 2, 5);
	      Tail2.setRotationPoint(-1.2F, 14.2F, 7F);
	      Tail2.setTextureSize(64, 32);
	      Tail2.mirror = true;
	      setRotation(Tail2, -0.5235988F, 0F, 0F);
	      RightWing = new ModelRenderer(this, 8, 20);
	      RightWing.addBox(-0.5F, -2F, 0F, 1, 4, 8);
	      RightWing.setRotationPoint(-2.2F, 10F, -4F);
	      RightWing.setTextureSize(64, 32);
	      RightWing.mirror = true;
	      setRotation(RightWing, 0.1047198F, 0.1047198F, 0.4363323F);
	      LeftWing = new ModelRenderer(this, 8, 20);
	      LeftWing.addBox(-0.5F, -2F, 0F, 1, 4, 8);
	      LeftWing.setRotationPoint(2.8F, 10F, -4F);
	      LeftWing.setTextureSize(64, 32);
	      LeftWing.mirror = true;
	      setRotation(LeftWing, 0.1047198F, -0.1047198F, -0.4363323F);
	  }
	  
	  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	  {
	    super.render(entity, f, f1, f2, f3, f4, f5);
	    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	    //LeftClawBack.render(f5);
	    //LeftClawRight.render(f5);
	    LeftLegTop.render(f5);
	    LeftLegLow.render(f5);
	    //LeftClawLeft.render(f5);
	    //LeftClawCenter.render(f5);
	    //RightClawBack.render(f5);
	    //RightClawRight.render(f5);
	    //RightClawCenter.render(f5);
	    //RightClawLeft.render(f5);
	    RightLegLow.render(f5);
	    BodyFrontTop.render(f5);
	    RightLegTop.render(f5);
	    RightThigh.render(f5);
	    LeftThigh.render(f5);
	    Tail3.render(f5);
	    BodyFrontLow.render(f5);
	    BodyFrontMid.render(f5);
	    NeckTop.render(f5);
	    //BeakLow.render(f5);
	    NeckBase.render(f5);
	    HeadBase.render(f5);
	    BeakBase.render(f5);
	    BeakNose.render(f5);
	    BodyBase.render(f5);
	    TailBase.render(f5);
	    Tail1.render(f5);
	    Tail2.render(f5);
	    RightWing.render(f5);
	    LeftWing.render(f5);
	  }
	  
	  private void setRotation(ModelRenderer model, float x, float y, float z)
	  {
	    model.rotateAngleX = x;
	    model.rotateAngleY = y;
	    model.rotateAngleZ = z;
	  }
	  
	  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity ent)
	  {
		  // Math.abs(MathHelper.cos(f * 0.5662F) * 1.4F * f1);
		  
		  this.HeadBase.rotateAngleY = f3 / 57.29578F;
		  this.BeakBase.rotateAngleY = 0.7853982F + f3 / 57.29578F;
		  this.BeakNose.rotateAngleY = f3 / 57.29578F;
		  
		  this.LeftLegTop.rotateAngleX     = 0.4363323F + Math.abs(MathHelper.cos(f * 0.5662F) * 1.4F * f1);
		  this.LeftLegLow.rotateAngleX     = -0.5235988F + Math.abs(MathHelper.cos(f * 0.5662F) * 1.4F * f1);
		  
		  this.RightLegTop.rotateAngleX     = 0.4363323F + Math.abs(MathHelper.sin(f * 0.5662F) * 1.4F * f1);
		  this.RightLegLow.rotateAngleX     = -0.5235988F + Math.abs(MathHelper.sin(f * 0.5662F) * 1.4F * f1);
		    
		  
	  }

	}
