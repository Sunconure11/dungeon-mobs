package com.gw.dm.model;

import com.gw.dm.entity.EntityIllithid;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelIllithid extends ModelBase
{
	  //fields
	    ModelRenderer HeadBack;
	    ModelRenderer HeadMid;
	    ModelRenderer LFBodyMid;
	    ModelRenderer Tentacle2;
	    ModelRenderer TentacleBase;
	    ModelRenderer MawBase;
	    ModelRenderer Tentacle4;
	    ModelRenderer Tentacle1;
	    ModelRenderer Tentacle3;
	    ModelRenderer EyeLeft;
	    ModelRenderer RFBodyMid;
	    ModelRenderer RBBodyMid;
	    ModelRenderer LBBodyMid;
	    ModelRenderer Head;
	    ModelRenderer EyeRight;
	    ModelRenderer RBBodyTop;
	    ModelRenderer RFBodyTop;
	    ModelRenderer LBBodyTop;
	    ModelRenderer LFBodyTop;
	    ModelRenderer LLegB;
	    ModelRenderer LLegT;
	    ModelRenderer LArmT;
	    ModelRenderer LArmB;
	    ModelRenderer RLegT;
	    ModelRenderer RLegB;
	    ModelRenderer RArmT;
	    ModelRenderer RArmB;
	  
	  public ModelIllithid()
	  {
	    textureWidth = 64;
	    textureHeight = 32;
	    
	      HeadBack = new ModelRenderer(this, 24, 0);
	      HeadBack.addBox(-4.5F, -12F, 1F, 9, 6, 9);
	      HeadBack.setRotationPoint(0F, -12F, -2F);
	      HeadBack.setTextureSize(64, 32);
	      HeadBack.mirror = true;
	      setRotation(HeadBack, 0F, 0F, 0F);
	      HeadMid = new ModelRenderer(this, 20, 0);
	      HeadMid.addBox(-6.5F, -11F, -3F, 13, 8, 9);
	      HeadMid.setRotationPoint(0F, -12F, -2F);
	      HeadMid.setTextureSize(64, 32);
	      HeadMid.mirror = true;
	      setRotation(HeadMid, 0F, 0F, 0F);
	      LFBodyMid = new ModelRenderer(this, 22, 20);
	      LFBodyMid.addBox(-7F, -8F, -6F, 6, 9, 3);
	      LFBodyMid.setRotationPoint(7F, 5F, 1F);
	      LFBodyMid.setTextureSize(64, 32);
	      LFBodyMid.mirror = true;
	      setRotation(LFBodyMid, 0F, 0F, 0F);
	      Tentacle2 = new ModelRenderer(this, 0, 15);
	      Tentacle2.addBox(0.8F, 6F, -10.4F, 1, 6, 1);
	      Tentacle2.setRotationPoint(0F, -12F, -2F);
	      Tentacle2.setTextureSize(64, 32);
	      Tentacle2.mirror = true;
	      setRotation(Tentacle2, -0.3141593F, 0F, 0F);
	      TentacleBase = new ModelRenderer(this, 24, 0);
	      TentacleBase.addBox(-2F, -2F, -8F, 4, 6, 2);
	      TentacleBase.setRotationPoint(0F, -12F, -2F);
	      TentacleBase.setTextureSize(64, 32);
	      TentacleBase.mirror = true;
	      setRotation(TentacleBase, -0.5759587F, 0F, 0F);
	      MawBase = new ModelRenderer(this, 0, 22);
	      MawBase.addBox(-2.5F, 3.5F, -9F, 5, 6, 4);
	      MawBase.setRotationPoint(0F, -12F, -2F);
	      MawBase.setTextureSize(64, 32);
	      MawBase.mirror = true;
	      setRotation(MawBase, -0.5759587F, 0F, 0F);
	      Tentacle4 = new ModelRenderer(this, 0, 15);
	      Tentacle4.addBox(0.8F, 5F, -9.5F, 1, 6, 1);
	      Tentacle4.setRotationPoint(0F, -12F, -2F);
	      Tentacle4.setTextureSize(64, 32);
	      Tentacle4.mirror = true;
	      setRotation(Tentacle4, -0.0872665F, 0F, 0F);
	      Tentacle1 = new ModelRenderer(this, 0, 15);
	      Tentacle1.addBox(-2.2F, 6F, -10.4F, 1, 6, 1);
	      Tentacle1.setRotationPoint(0F, -12F, -2F);
	      Tentacle1.setTextureSize(64, 32);
	      Tentacle1.mirror = true;
	      setRotation(Tentacle1, -0.3141593F, 0F, 0F);
	      Tentacle3 = new ModelRenderer(this, 0, 15);
	      Tentacle3.addBox(-2.2F, 5F, -9.5F, 1, 6, 1);
	      Tentacle3.setRotationPoint(0F, -12F, -2F);
	      Tentacle3.setTextureSize(64, 32);
	      Tentacle3.mirror = true;
	      setRotation(Tentacle3, -0.0872665F, 0F, 0F);
	      EyeLeft = new ModelRenderer(this, 4, 15);
	      EyeLeft.addBox(3.9F, -7F, -1.9F, 3, 3, 4);
	      EyeLeft.setRotationPoint(0F, -12F, -2F);
	      EyeLeft.setTextureSize(64, 32);
	      EyeLeft.mirror = true;
	      setRotation(EyeLeft, 0F, 0.715585F, 0F);
	      RFBodyMid = new ModelRenderer(this, 22, 20);
	      RFBodyMid.addBox(-7F, -8F, -6F, 6, 9, 3);
	      RFBodyMid.setRotationPoint(1F, 5F, 1F);
	      RFBodyMid.setTextureSize(64, 32);
	      RFBodyMid.mirror = true;
	      setRotation(RFBodyMid, 0F, 0F, 0F);
	      RBBodyMid = new ModelRenderer(this, 21, 20);
	      RBBodyMid.addBox(-7F, -8F, -6F, 6, 9, 3);
	      RBBodyMid.setRotationPoint(1F, 5F, 4F);
	      RBBodyMid.setTextureSize(64, 32);
	      RBBodyMid.mirror = true;
	      setRotation(RBBodyMid, 0F, 0F, 0F);
	      LBBodyMid = new ModelRenderer(this, 21, 20);
	      LBBodyMid.addBox(-7F, -8F, -6F, 6, 9, 3);
	      LBBodyMid.setRotationPoint(7F, 5F, 4F);
	      LBBodyMid.setTextureSize(64, 32);
	      LBBodyMid.mirror = true;
	      setRotation(LBBodyMid, 0F, 0F, 0F);
	      Head = new ModelRenderer(this, 32, 0);
	      Head.addBox(-4F, -8F, -6F, 8, 8, 8);
	      Head.setRotationPoint(0F, -12F, -2F);
	      Head.setTextureSize(64, 32);
	      Head.mirror = true;
	      setRotation(Head, 0F, 0F, 0F);
	      EyeRight = new ModelRenderer(this, 4, 15);
	      EyeRight.addBox(-6.9F, -7F, -1.9F, 3, 3, 4);
	      EyeRight.setRotationPoint(0F, -12F, -2F);
	      EyeRight.setTextureSize(64, 32);
	      EyeRight.mirror = true;
	      setRotation(EyeRight, 0F, -0.715585F, 0F);
	      RBBodyTop = new ModelRenderer(this, 0, 0);
	      RBBodyTop.addBox(-7F, -8F, -6F, 7, 9, 3);
	      RBBodyTop.setRotationPoint(0F, -4F, 4F);
	      RBBodyTop.setTextureSize(64, 32);
	      RBBodyTop.mirror = true;
	      setRotation(RBBodyTop, 0F, 0F, 0F);
	      RFBodyTop = new ModelRenderer(this, 0, 0);
	      RFBodyTop.addBox(-7F, -8F, -6F, 7, 9, 3);
	      RFBodyTop.setRotationPoint(0F, -4F, 1F);
	      RFBodyTop.setTextureSize(64, 32);
	      RFBodyTop.mirror = true;
	      setRotation(RFBodyTop, 0F, 0F, 0F);
	      LBBodyTop = new ModelRenderer(this, 0, 0);
	      LBBodyTop.addBox(-7F, -8F, -6F, 7, 9, 3);
	      LBBodyTop.setRotationPoint(7F, -4F, 4F);
	      LBBodyTop.setTextureSize(64, 32);
	      LBBodyTop.mirror = true;
	      setRotation(LBBodyTop, 0F, 0F, 0F);
	      LFBodyTop = new ModelRenderer(this, 0, 0);
	      LFBodyTop.addBox(-7F, -8F, -6F, 7, 9, 3);
	      LFBodyTop.setRotationPoint(7F, -4F, 1F);
	      LFBodyTop.setTextureSize(64, 32);
	      LFBodyTop.mirror = true;
	      setRotation(LFBodyTop, 0F, 0F, 0F);
	      LLegB = new ModelRenderer(this, 39, 20);
	      LLegB.addBox(-1.5F, 9F, -1.5F, 3, 9, 3);
	      LLegB.setRotationPoint(4F, 6F, -2F);
	      LLegB.setTextureSize(64, 32);
	      LLegB.mirror = true;
	      setRotation(LLegB, 0F, 0F, 0F);
	      LLegT = new ModelRenderer(this, 52, 20);
	      LLegT.addBox(-1.5F, 0F, -1.5F, 3, 9, 3);
	      LLegT.setRotationPoint(4F, 6F, -2F);
	      LLegT.setTextureSize(64, 32);
	      LLegT.mirror = true;
	      setRotation(LLegT, 0F, 0F, 0F);
	      LArmT = new ModelRenderer(this, 52, 20);
	      LArmT.addBox(0F, -1F, -1.5F, 3, 9, 3);
	      LArmT.setRotationPoint(7F, -10F, -2F);
	      LArmT.setTextureSize(64, 32);
	      LArmT.mirror = true;
	      setRotation(LArmT, 0F, 0F, 0F);
	      LArmB = new ModelRenderer(this, 39, 20);
	      LArmB.addBox(0F, 8F, -1.5F, 3, 9, 3);
	      LArmB.setRotationPoint(7F, -10F, -2F);
	      LArmB.setTextureSize(64, 32);
	      LArmB.mirror = true;
	      setRotation(LArmB, 0F, 0F, 0F);
	      RLegT = new ModelRenderer(this, 52, 20);
	      RLegT.addBox(-1.5F, 0F, -1.5F, 3, 9, 3);
	      RLegT.setRotationPoint(-4F, 6F, -2F);
	      RLegT.setTextureSize(64, 32);
	      RLegT.mirror = true;
	      setRotation(RLegT, 0F, 0F, 0F);
	      RLegB = new ModelRenderer(this, 39, 20);
	      RLegB.addBox(-1.5F, 9F, -1.5F, 3, 9, 3);
	      RLegB.setRotationPoint(-4F, 6F, -2F);
	      RLegB.setTextureSize(64, 32);
	      RLegB.mirror = true;
	      setRotation(RLegB, 0F, 0F, 0F);
	      RArmT = new ModelRenderer(this, 52, 20);
	      RArmT.addBox(-3F, -1F, -1.5F, 3, 9, 3);
	      RArmT.setRotationPoint(-7F, -10F, -2F);
	      RArmT.setTextureSize(64, 32);
	      RArmT.mirror = true;
	      setRotation(RArmT, 0F, 0F, 0F);
	      RArmB = new ModelRenderer(this, 39, 20);
	      RArmB.addBox(-3F, 8F, -1.5F, 3, 9, 3);
	      RArmB.setRotationPoint(-7F, -10F, -2F);
	      RArmB.setTextureSize(64, 32);
	      RArmB.mirror = true;
	      setRotation(RArmB, 0F, 0F, 0F);
	  }
	  
	  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	  {
	    super.render(entity, f, f1, f2, f3, f4, f5);
	    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	    HeadBack.render(f5);
	    HeadMid.render(f5);
	    LFBodyMid.render(f5);
	    Tentacle2.render(f5);
	    TentacleBase.render(f5);
	    MawBase.render(f5);
	    Tentacle4.render(f5);
	    Tentacle1.render(f5);
	    Tentacle3.render(f5);
	    EyeLeft.render(f5);
	    RFBodyMid.render(f5);
	    RBBodyMid.render(f5);
	    LBBodyMid.render(f5);
	    Head.render(f5);
	    EyeRight.render(f5);
	    RBBodyTop.render(f5);
	    RFBodyTop.render(f5);
	    LBBodyTop.render(f5);
	    LFBodyTop.render(f5);
	    LLegB.render(f5);
	    LLegT.render(f5);
	    LArmT.render(f5);
	    LArmB.render(f5);
	    RLegT.render(f5);
	    RLegB.render(f5);
	    RArmT.render(f5);
	    RArmB.render(f5);
	  }
	  
	  private void setRotation(ModelRenderer model, float x, float y, float z)
	  {
	    model.rotateAngleX = x;
	    model.rotateAngleY = y;
	    model.rotateAngleZ = z;
	  }
	  
	  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity ent)
	  {
		  //this.Head.rotateAngleX         = f4 / 57.29578F;
		  this.Head.rotateAngleY         = f3 / 57.29578F;
		  //this.HeadBack.rotateAngleX	 = f4 / 57.29578F;
		  this.HeadBack.rotateAngleY	 = f3 / 57.29578F;
		  //this.HeadMid.rotateAngleX		 = f4 / 57.29578F;
		  this.HeadMid.rotateAngleY		 = f3 / 57.29578F;
		 // this.MawBase.rotateAngleX		 = -0.5759587F + f4 / 57.29578F;
		  this.MawBase.rotateAngleY		 = f3 / 57.29578F;
		  //this.TentacleBase.rotateAngleX = -0.5759587F + f4 / 57.29578F;
		  this.TentacleBase.rotateAngleY = f3 / 57.29578F;
		 // this.EyeLeft.rotateAngleX		 = f4 / 57.29578F;
		  this.EyeLeft.rotateAngleY		 = 0.715585F + f3 / 57.29578F;
		  //this.EyeRight.rotateAngleX	 = f4 / 57.29578F;
		  this.EyeRight.rotateAngleY	 = -0.715585F + f3 / 57.29578F;
		  
		  float var8 = 0.01F * (float)(ent.getEntityId() % 10);
		  
		  this.Tentacle1.rotateAngleX = -0.3141593F + (MathHelper.sin((float)ent.ticksExisted * var8) * 4.5F * (float)Math.PI / 180.0F);// + f4 / 57.29578F; 
		  this.Tentacle1.rotateAngleY = f3 / 57.29578F;
		  this.Tentacle1.rotateAngleZ = MathHelper.sin((float)ent.ticksExisted * var8) * 2.5F * (float)Math.PI / 180.0F;
		  this.Tentacle2.rotateAngleX = -0.3141593F + (MathHelper.sin((float)ent.ticksExisted * var8) * 4.5F * (float)Math.PI / 180.0F);// + f4 / 57.29578F;
		  this.Tentacle2.rotateAngleY = f3 / 57.29578F;
		  this.Tentacle2.rotateAngleZ = MathHelper.cos((float)ent.ticksExisted * var8) * 2.5F * (float)Math.PI / 180.0F;
		  this.Tentacle3.rotateAngleX = -0.0872665F + (MathHelper.sin((float)ent.ticksExisted * var8) * 4.5F * (float)Math.PI / 180.0F);// + f4 / 57.29578F;
		  this.Tentacle3.rotateAngleY = f3 / 57.29578F;
		  this.Tentacle3.rotateAngleZ = MathHelper.cos((float)ent.ticksExisted * var8) * 2.5F * (float)Math.PI / 180.0F;
		  this.Tentacle4.rotateAngleX = -0.0872665F + (MathHelper.sin((float)ent.ticksExisted * var8) * 4.5F * (float)Math.PI / 180.0F);// + f4 / 57.29578F;
		  this.Tentacle4.rotateAngleY = f3 / 57.29578F;
		  this.Tentacle4.rotateAngleZ = MathHelper.sin((float)ent.ticksExisted * var8) * 2.5F * (float)Math.PI / 180.0F;
		  
		  EntityIllithid foo = (EntityIllithid)ent;
		  
		  if(!foo.isGrappling)
		  {
			  this.LLegB.rotateAngleX    = MathHelper.cos(f * 0.5662F) * 1.4F * f1 * 0.5F;
			  this.LLegT.rotateAngleX    = MathHelper.cos(f * 0.5662F) * 1.4F * f1 * 0.5F;
			  this.RLegB.rotateAngleX   = MathHelper.cos(f * 0.5662F + (float)Math.PI) * 1.4F * f1 * 0.5F;
			  this.RLegT.rotateAngleX   = MathHelper.cos(f * 0.5662F + (float)Math.PI) * 1.4F * f1 * 0.5F;
			  
			  this.RArmB.rotateAngleX   = MathHelper.cos(f * 0.5662F) * 1.4F * f1 * 0.5F;
			  this.RArmT.rotateAngleX   = MathHelper.cos(f * 0.5662F) * 1.4F * f1 * 0.5F;
			  this.LArmB.rotateAngleX    = MathHelper.cos(f * 0.5662F + (float)Math.PI) * 1.4F * f1 * 0.5F;
			  this.LArmT.rotateAngleX    = MathHelper.cos(f * 0.5662F + (float)Math.PI) * 1.4F * f1 * 0.5F;
		  }
		  else
		  {
			  this.RArmB.rotateAngleX   = -0.87266F;
			  this.RArmT.rotateAngleX   = -0.87266F;
			  this.LArmB.rotateAngleX    = -0.87266F;
			  this.LArmT.rotateAngleX    = -0.87266F;
		  }
	  }

	}