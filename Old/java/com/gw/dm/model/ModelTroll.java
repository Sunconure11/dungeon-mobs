package com.gw.dm.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelTroll extends ModelBase
{
	  //fields
	    ModelRenderer LegLeft;
	    ModelRenderer LegRight;
	    ModelRenderer Body;
	    ModelRenderer ArmLeft;
	    ModelRenderer Shape1;
	    ModelRenderer LegRightSocket;
	    ModelRenderer ArmRightJoint;
	    ModelRenderer LegLeftSocket;
	    ModelRenderer ArmLeftJoint;
	    ModelRenderer Nose;
	    ModelRenderer ArmRight;
	  
	  public ModelTroll()
	  {
	    textureWidth = 64;
	    textureHeight = 32;
	    
	      LegLeft = new ModelRenderer(this, 38, 0);
	      LegLeft.addBox(-2.5F, 0F, -2F, 5, 16, 5);
	      LegLeft.setRotationPoint(5F, 8F, 0F);
	      LegLeft.setTextureSize(64, 32);
	      LegLeft.mirror = true;
	      setRotation(LegLeft, 0F, 0F, 0F);
	      LegRight = new ModelRenderer(this, 40, 0);
	      LegRight.addBox(-2.5F, 0F, -2F, 5, 16, 5);
	      LegRight.setRotationPoint(-5F, 8F, 0F);
	      LegRight.setTextureSize(64, 32);
	      LegRight.mirror = true;
	      setRotation(LegRight, 0F, 0F, 0F);
	      Body = new ModelRenderer(this, 34, 0);
	      Body.addBox(-5F, -2F, -2F, 10, 20, 5);
	      Body.setRotationPoint(0F, -9F, 0F);
	      Body.setTextureSize(64, 32);
	      Body.mirror = true;
	      setRotation(Body, 0F, 0F, 0F);
	      ArmLeft = new ModelRenderer(this, 37, 13);
	      ArmLeft.addBox(-2.5F, 0F, -2F, 5, 14, 5);
	      ArmLeft.setRotationPoint(8F, -10F, 0F);
	      ArmLeft.setTextureSize(64, 32);
	      ArmLeft.mirror = true;
	      setRotation(ArmLeft, 0F, 0F, 0F);
	      Shape1 = new ModelRenderer(this, 0, 0);
	      Shape1.addBox(-4F, -2F, -8F, 8, 8, 8);
	      Shape1.setRotationPoint(0F, -10F, -1F);
	      Shape1.setTextureSize(64, 32);
	      Shape1.mirror = true;
	      setRotation(Shape1, 0F, 0F, 0F);
	      LegRightSocket = new ModelRenderer(this, 0, 20);
	      LegRightSocket.addBox(0F, 0F, 0F, 6, 5, 7);
	      LegRightSocket.setRotationPoint(-8F, 5F, -3F);
	      LegRightSocket.setTextureSize(64, 32);
	      LegRightSocket.mirror = true;
	      setRotation(LegRightSocket, 0F, 0F, 0F);
	      ArmRightJoint = new ModelRenderer(this, 0, 19);
	      ArmRightJoint.addBox(0F, 0F, 0F, 7, 6, 7);
	      ArmRightJoint.setRotationPoint(-11F, -13F, -3F);
	      ArmRightJoint.setTextureSize(64, 32);
	      ArmRightJoint.mirror = true;
	      setRotation(ArmRightJoint, 0F, 0F, 0F);
	      LegLeftSocket = new ModelRenderer(this, 0, 20);
	      LegLeftSocket.addBox(0F, 0F, 0F, 6, 5, 7);
	      LegLeftSocket.setRotationPoint(2F, 5F, -3F);
	      LegLeftSocket.setTextureSize(64, 32);
	      LegLeftSocket.mirror = true;
	      setRotation(LegLeftSocket, 0F, 0F, 0F);
	      ArmLeftJoint = new ModelRenderer(this, 0, 19);
	      ArmLeftJoint.addBox(0F, 0F, 0F, 7, 6, 7);
	      ArmLeftJoint.setRotationPoint(4F, -13F, -3F);
	      ArmLeftJoint.setTextureSize(64, 32);
	      ArmLeftJoint.mirror = true;
	      setRotation(ArmLeftJoint, 0F, 0F, 0F);
	      Nose = new ModelRenderer(this, 1, 19);
	      Nose.addBox(-1F, 0F, -10F, 2, 8, 2);
	      Nose.setRotationPoint(0F, -10F, -1F);
	      Nose.setTextureSize(64, 32);
	      Nose.mirror = true;
	      setRotation(Nose, 0F, 0F, 0F);
	      ArmRight = new ModelRenderer(this, 37, 13);
	      ArmRight.addBox(-2.5F, 0F, -2F, 5, 14, 5);
	      ArmRight.setRotationPoint(-8F, -10F, 0F);
	      ArmRight.setTextureSize(64, 32);
	      ArmRight.mirror = true;
	      setRotation(ArmRight, 0F, 0F, 0F);
	  }
	  
	  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	  {
	    super.render(entity, f, f1, f2, f3, f4, f5);
	    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	    LegLeft.render(f5);
	    LegRight.render(f5);
	    Body.render(f5);
	    ArmLeft.render(f5);
	    Shape1.render(f5);
	    LegRightSocket.render(f5);
	    ArmRightJoint.render(f5);
	    LegLeftSocket.render(f5);
	    ArmLeftJoint.render(f5);
	    Nose.render(f5);
	    ArmRight.render(f5);
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
		  
		  this.Shape1.rotateAngleY = f3 / 57.29578F;
		  this.Shape1.rotateAngleX = f4 / 57.29578F;
		  this.Nose.rotateAngleY = f3 / 57.29578F;
		  this.Nose.rotateAngleX = f4 / 57.29578F;
		  
		  this.LegLeft.rotateAngleX    = MathHelper.cos(f * 0.5662F) * 1.4F * f1;
		  this.LegRight.rotateAngleX   = MathHelper.cos(f * 0.5662F + (float)Math.PI) * 1.4F * f1;
		  
		  this.ArmRight.rotateAngleX   = MathHelper.cos(f * 0.5662F) * 1.4F * f1;
		  this.ArmLeft.rotateAngleX    = MathHelper.cos(f * 0.5662F + (float)Math.PI) * 1.4F * f1;
	  }

	}
