package com.gw.dm.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelAhriman extends ModelBase
{
	  //fields
	    ModelRenderer BodyBottom2;
	    ModelRenderer BodyBase;
	    ModelRenderer BodyBottom1;
	    ModelRenderer BodyBack1;
	    ModelRenderer BodyBack2;
	    ModelRenderer BodyTop1;
	    ModelRenderer BodyTop2;
	    ModelRenderer BodyLeft1;
	    ModelRenderer BodyLeft2;
	    ModelRenderer BodyFront1;
	    ModelRenderer BodyFront2;
	    ModelRenderer BodyRight1;
	    ModelRenderer BodyRight2;
	    ModelRenderer Maw;
	    ModelRenderer Eye;
	    ModelRenderer Stalk3;
	    ModelRenderer Stalk5;
	    ModelRenderer Stalk7;
	    ModelRenderer StalkEye3;
	    ModelRenderer Stalk1;
	    ModelRenderer StalkEye8;
	    ModelRenderer StalkEye6;
	    ModelRenderer StalkEye1;
	    ModelRenderer Stalk8;
	    ModelRenderer StalkEye4;
	    ModelRenderer Stalk2;
	    ModelRenderer StalkEye2;
	    ModelRenderer Stalk6;
	    ModelRenderer StalkEye7;
	    ModelRenderer Stalk4;
	    ModelRenderer StalkEye5;
	  
	  public ModelAhriman()
	  {
	    textureWidth = 64;
	    textureHeight = 32;
	    
	      BodyBottom2 = new ModelRenderer(this, 12, 11);
	      BodyBottom2.addBox(-5F, -5F, -5F, 10, 10, 10);
	      BodyBottom2.setRotationPoint(0F, 6F, -2F);
	      BodyBottom2.setTextureSize(64, 32);
	      BodyBottom2.mirror = true;
	      setRotation(BodyBottom2, 0F, 0F, 0F);
	      BodyBase = new ModelRenderer(this, 0, 0);
	      BodyBase.addBox(-8F, -8F, -8F, 16, 16, 16);
	      BodyBase.setRotationPoint(0F, 0F, 0F);
	      BodyBase.setTextureSize(64, 32);
	      BodyBase.mirror = true;
	      setRotation(BodyBase, 0F, 0F, 0F);
	      BodyBottom1 = new ModelRenderer(this, 12, 6);
	      BodyBottom1.addBox(-6.5F, -6.5F, -6.5F, 13, 13, 13);
	      BodyBottom1.setRotationPoint(0F, 3F, -1F);
	      BodyBottom1.setTextureSize(64, 32);
	      BodyBottom1.mirror = true;
	      setRotation(BodyBottom1, 0F, 0F, 0F);
	      BodyBack1 = new ModelRenderer(this, 12, 6);
	      BodyBack1.addBox(-6.5F, -6.5F, -6.5F, 13, 13, 13);
	      BodyBack1.setRotationPoint(0F, 0F, 3F);
	      BodyBack1.setTextureSize(64, 32);
	      BodyBack1.mirror = true;
	      setRotation(BodyBack1, 0F, 0F, 0F);
	      BodyBack2 = new ModelRenderer(this, 12, 12);
	      BodyBack2.addBox(-5F, -5F, -5F, 10, 10, 10);
	      BodyBack2.setRotationPoint(0F, 0F, 6F);
	      BodyBack2.setTextureSize(64, 32);
	      BodyBack2.mirror = true;
	      setRotation(BodyBack2, 0F, 0F, 0F);
	      BodyTop1 = new ModelRenderer(this, 12, 6);
	      BodyTop1.addBox(-6.5F, -6.5F, -6.5F, 13, 13, 13);
	      BodyTop1.setRotationPoint(0F, -3F, 1F);
	      BodyTop1.setTextureSize(64, 32);
	      BodyTop1.mirror = true;
	      setRotation(BodyTop1, 0F, 0F, 0F);
	      BodyTop2 = new ModelRenderer(this, 12, 12);
	      BodyTop2.addBox(-5F, -5F, -5F, 10, 10, 10);
	      BodyTop2.setRotationPoint(0F, -6F, 2F);
	      BodyTop2.setTextureSize(64, 32);
	      BodyTop2.mirror = true;
	      setRotation(BodyTop2, 0F, 0F, 0F);
	      BodyLeft1 = new ModelRenderer(this, 12, 6);
	      BodyLeft1.addBox(-6.5F, -6.5F, -6.5F, 13, 13, 13);
	      BodyLeft1.setRotationPoint(3F, 0F, 0F);
	      BodyLeft1.setTextureSize(64, 32);
	      BodyLeft1.mirror = true;
	      setRotation(BodyLeft1, 0F, 0F, 0F);
	      BodyLeft2 = new ModelRenderer(this, 12, 12);
	      BodyLeft2.addBox(-5F, -5F, -5F, 10, 10, 10);
	      BodyLeft2.setRotationPoint(6F, 0F, 0F);
	      BodyLeft2.setTextureSize(64, 32);
	      BodyLeft2.mirror = true;
	      setRotation(BodyLeft2, 0F, 0F, 0F);
	      BodyFront1 = new ModelRenderer(this, 7, 5);
	      BodyFront1.addBox(-6.5F, -6.5F, -6.5F, 13, 14, 13);
	      BodyFront1.setRotationPoint(0F, -1F, -3F);
	      BodyFront1.setTextureSize(64, 32);
	      BodyFront1.mirror = true;
	      setRotation(BodyFront1, 0F, 0F, 0F);
	      BodyFront2 = new ModelRenderer(this, 11, 10);
	      BodyFront2.addBox(-5F, -5F, -5F, 10, 12, 10);
	      BodyFront2.setRotationPoint(0F, -2F, -6F);
	      BodyFront2.setTextureSize(64, 32);
	      BodyFront2.mirror = true;
	      setRotation(BodyFront2, 0F, 0F, 0F);
	      BodyRight1 = new ModelRenderer(this, 12, 6);
	      BodyRight1.addBox(-6.5F, -6.5F, -6.5F, 13, 13, 13);
	      BodyRight1.setRotationPoint(-3F, 0F, 0F);
	      BodyRight1.setTextureSize(64, 32);
	      BodyRight1.mirror = true;
	      setRotation(BodyRight1, 0F, 0F, 0F);
	      BodyRight2 = new ModelRenderer(this, 12, 12);
	      BodyRight2.addBox(-5F, -5F, -5F, 10, 10, 10);
	      BodyRight2.setRotationPoint(-6F, 0F, 0F);
	      BodyRight2.setTextureSize(64, 32);
	      BodyRight2.mirror = true;
	      setRotation(BodyRight2, 0F, 0F, 0F);
	      Maw = new ModelRenderer(this, 0, 7);
	      Maw.addBox(-3F, 0F, 0.5F, 6, 6, 3);
	      Maw.setRotationPoint(0F, 2F, -13F);
	      Maw.setTextureSize(64, 32);
	      Maw.mirror = true;
	      setRotation(Maw, 0.2792527F, 0F, 0F);
	      Eye = new ModelRenderer(this, 0, 0);
	      Eye.addBox(-3F, 0F, 0.5F, 6, 6, 2);
	      Eye.setRotationPoint(0F, -6F, -12F);
	      Eye.setTextureSize(64, 32);
	      Eye.mirror = true;
	      setRotation(Eye, 0F, 0F, 0F);
	      Stalk3 = new ModelRenderer(this, 16, 16);
	      Stalk3.addBox(-0.5F, -6F, -0.5F, 1, 6, 1);
	      Stalk3.setRotationPoint(1F, -11F, -2F);
	      Stalk3.setTextureSize(64, 32);
	      Stalk3.mirror = true;
	      setRotation(Stalk3, 0F, 0F, 0F);
	      Stalk5 = new ModelRenderer(this, 15, 18);
	      Stalk5.addBox(-0.5F, -6F, -0.5F, 1, 6, 1);
	      Stalk5.setRotationPoint(-6F, -8F, -7F);
	      Stalk5.setTextureSize(64, 32);
	      Stalk5.mirror = true;
	      setRotation(Stalk5, 0F, 0F, 0F);
	      Stalk7 = new ModelRenderer(this, 15, 15);
	      Stalk7.addBox(-0.5F, -6F, -0.5F, 1, 6, 1);
	      Stalk7.setRotationPoint(9F, -6.5F, -4F);
	      Stalk7.setTextureSize(64, 32);
	      Stalk7.mirror = true;
	      setRotation(Stalk7, 0F, 0F, 0F);
	      StalkEye3 = new ModelRenderer(this, 56, 0);
	      StalkEye3.addBox(-1F, -8F, -1F, 2, 2, 2);
	      StalkEye3.setRotationPoint(1F, -11F, -2F);
	      StalkEye3.setTextureSize(64, 32);
	      StalkEye3.mirror = true;
	      setRotation(StalkEye3, 0F, 0F, 0F);
	      Stalk1 = new ModelRenderer(this, 33, 11);
	      Stalk1.addBox(-0.5F, -6F, -0.5F, 1, 6, 1);
	      Stalk1.setRotationPoint(-4F, -6.5F, 9F);
	      Stalk1.setTextureSize(64, 32);
	      Stalk1.mirror = true;
	      setRotation(Stalk1, 0F, 0F, 0F);
	      StalkEye8 = new ModelRenderer(this, 56, 0);
	      StalkEye8.addBox(-1F, -8F, -1F, 2, 2, 2);
	      StalkEye8.setRotationPoint(-10.5F, -5F, -1F);
	      StalkEye8.setTextureSize(64, 32);
	      StalkEye8.mirror = true;
	      setRotation(StalkEye8, 0F, 0F, 0F);
	      StalkEye6 = new ModelRenderer(this, 56, 0);
	      StalkEye6.addBox(-1F, -8F, -1F, 2, 2, 2);
	      StalkEye6.setRotationPoint(7F, -8F, 6F);
	      StalkEye6.setTextureSize(64, 32);
	      StalkEye6.mirror = true;
	      setRotation(StalkEye6, 0F, 0F, 0F);
	      StalkEye1 = new ModelRenderer(this, 56, 0);
	      StalkEye1.addBox(-1F, -8F, -1F, 2, 2, 2);
	      StalkEye1.setRotationPoint(-4F, -6.5F, 9F);
	      StalkEye1.setTextureSize(64, 32);
	      StalkEye1.mirror = true;
	      setRotation(StalkEye1, 0F, 0F, 0F);
	      Stalk8 = new ModelRenderer(this, 17, 17);
	      Stalk8.addBox(-0.5F, -6F, -0.5F, 1, 6, 1);
	      Stalk8.setRotationPoint(-10.5F, -5F, -1F);
	      Stalk8.setTextureSize(64, 32);
	      Stalk8.mirror = true;
	      setRotation(Stalk8, 0F, 0F, 0F);
	      StalkEye4 = new ModelRenderer(this, 56, 0);
	      StalkEye4.addBox(-1F, -8F, -1F, 2, 2, 2);
	      StalkEye4.setRotationPoint(-3F, -11F, 2F);
	      StalkEye4.setTextureSize(64, 32);
	      StalkEye4.mirror = true;
	      setRotation(StalkEye4, 0F, 0F, 0F);
	      Stalk2 = new ModelRenderer(this, 20, 5);
	      Stalk2.addBox(-0.5F, -6F, -0.5F, 1, 6, 1);
	      Stalk2.setRotationPoint(3F, -11F, 5F);
	      Stalk2.setTextureSize(64, 32);
	      Stalk2.mirror = true;
	      setRotation(Stalk2, 0F, 0F, 0F);
	      StalkEye2 = new ModelRenderer(this, 56, 0);
	      StalkEye2.addBox(-1F, -8F, -1F, 2, 2, 2);
	      StalkEye2.setRotationPoint(3F, -11F, 5F);
	      StalkEye2.setTextureSize(64, 32);
	      StalkEye2.mirror = true;
	      setRotation(StalkEye2, 0F, 0F, 0F);
	      Stalk6 = new ModelRenderer(this, 23, 23);
	      Stalk6.addBox(-0.5F, -6F, -0.5F, 1, 6, 1);
	      Stalk6.setRotationPoint(7F, -8F, 6F);
	      Stalk6.setTextureSize(64, 32);
	      Stalk6.mirror = true;
	      setRotation(Stalk6, 0F, 0F, 0F);
	      StalkEye7 = new ModelRenderer(this, 56, 0);
	      StalkEye7.addBox(-1F, -8F, -1F, 2, 2, 2);
	      StalkEye7.setRotationPoint(9F, -6.5F, -4F);
	      StalkEye7.setTextureSize(64, 32);
	      StalkEye7.mirror = true;
	      setRotation(StalkEye7, 0F, 0F, 0F);
	      Stalk4 = new ModelRenderer(this, 19, 11);
	      Stalk4.addBox(-0.5F, -6F, -0.5F, 1, 6, 1);
	      Stalk4.setRotationPoint(-3F, -11F, 2F);
	      Stalk4.setTextureSize(64, 32);
	      Stalk4.mirror = true;
	      setRotation(Stalk4, 0F, 0F, 0F);
	      StalkEye5 = new ModelRenderer(this, 56, 0);
	      StalkEye5.addBox(-1F, -8F, -1F, 2, 2, 2);
	      StalkEye5.setRotationPoint(-6F, -8F, -7F);
	      StalkEye5.setTextureSize(64, 32);
	      StalkEye5.mirror = true;
	      setRotation(StalkEye5, 0F, 0F, 0F);
	  }
	  
	  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	  {
	    super.render(entity, f, f1, f2, f3, f4, f5);
	    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	    BodyBottom2.render(f5);
	    BodyBase.render(f5);
	    BodyBottom1.render(f5);
	    BodyBack1.render(f5);
	    BodyBack2.render(f5);
	    BodyTop1.render(f5);
	    BodyTop2.render(f5);
	    BodyLeft1.render(f5);
	    BodyLeft2.render(f5);
	    BodyFront1.render(f5);
	    BodyFront2.render(f5);
	    BodyRight1.render(f5);
	    BodyRight2.render(f5);
	    Maw.render(f5);
	    Eye.render(f5);
	    Stalk3.render(f5);
	    Stalk5.render(f5);
	    Stalk7.render(f5);
	    StalkEye3.render(f5);
	    Stalk1.render(f5);
	    StalkEye8.render(f5);
	    StalkEye6.render(f5);
	    StalkEye1.render(f5);
	    Stalk8.render(f5);
	    StalkEye4.render(f5);
	    Stalk2.render(f5);
	    StalkEye2.render(f5);
	    Stalk6.render(f5);
	    StalkEye7.render(f5);
	    Stalk4.render(f5);
	    StalkEye5.render(f5);
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
		  
		  float var8 = 0.1F;
		  
		  float moo = 6.5F;
		  
		  this.Stalk1.rotateAngleX = (MathHelper.sin((float)ent.ticksExisted * var8) * moo * (float)Math.PI / 180.0F) - (MathHelper.cos((float)ent.ticksExisted * var8) * moo * (float)Math.PI / 180.0F);
		  //this.Stalk1.rotateAngleY = MathHelper.cos((float)ent.ticksExisted * var8) * moo;
		  this.Stalk1.rotateAngleZ = MathHelper.sin((float)ent.ticksExisted * var8) * moo * (float)Math.PI / 180.0F;
		  
		  this.StalkEye1.rotateAngleX = (MathHelper.sin((float)ent.ticksExisted * var8) * moo * (float)Math.PI / 180.0F) - (MathHelper.cos((float)ent.ticksExisted * var8) * moo * (float)Math.PI / 180.0F);
		  //this.StalkEye1.rotateAngleY = MathHelper.cos((float)ent.ticksExisted * var8) * moo;
		  this.StalkEye1.rotateAngleZ = MathHelper.sin((float)ent.ticksExisted * var8) * moo * (float)Math.PI / 180.0F;
		  
		  this.Stalk2.rotateAngleX = (MathHelper.cos((float)ent.ticksExisted * var8) * moo * (float)Math.PI / 180.0F) - (MathHelper.sin((float)ent.ticksExisted * var8) * moo * (float)Math.PI / 180.0F);
		  //this.Stalk2.rotateAngleY = MathHelper.cos((float)ent.ticksExisted * var8) * moo * (float)Math.PI / 180.0F;
		  this.Stalk2.rotateAngleZ = MathHelper.cos((float)ent.ticksExisted * var8) * moo * (float)Math.PI / 180.0F;
		  
		  this.StalkEye2.rotateAngleX = (MathHelper.cos((float)ent.ticksExisted * var8) * moo * (float)Math.PI / 180.0F) - (MathHelper.sin((float)ent.ticksExisted * var8) * moo * (float)Math.PI / 180.0F);
		  //this.StalkEye2.rotateAngleY = MathHelper.cos((float)ent.ticksExisted * var8) * moo * (float)Math.PI / 180.0F;
		  this.StalkEye2.rotateAngleZ = MathHelper.cos((float)ent.ticksExisted * var8) * moo * (float)Math.PI / 180.0F;
		  
		  this.Stalk3.rotateAngleX = (MathHelper.sin((float)ent.ticksExisted * var8) * moo * (float)Math.PI / 180.0F) - (MathHelper.cos((float)ent.ticksExisted * var8) * moo * (float)Math.PI / 180.0F);
		  //this.Stalk3.rotateAngleY = MathHelper.sin((float)ent.ticksExisted * var8) * moo * (float)Math.PI / 180.0F;
		  this.Stalk3.rotateAngleZ = MathHelper.sin((float)ent.ticksExisted * var8) * moo * (float)Math.PI / 180.0F;
		  
		  this.StalkEye3.rotateAngleX = (MathHelper.sin((float)ent.ticksExisted * var8) * moo * (float)Math.PI / 180.0F) - (MathHelper.cos((float)ent.ticksExisted * var8) * moo * (float)Math.PI / 180.0F);
		  //this.StalkEye3.rotateAngleY = MathHelper.sin((float)ent.ticksExisted * var8) * moo * (float)Math.PI / 180.0F;
		  this.StalkEye3.rotateAngleZ = MathHelper.sin((float)ent.ticksExisted * var8) * moo * (float)Math.PI / 180.0F;
		  
		  this.Stalk4.rotateAngleX = (MathHelper.cos((float)ent.ticksExisted * var8) * moo * (float)Math.PI / 180.0F) - (MathHelper.sin((float)ent.ticksExisted * var8) * moo * (float)Math.PI / 180.0F);
		  //this.Stalk4.rotateAngleY = MathHelper.sin((float)ent.ticksExisted * var8) * moo * (float)Math.PI / 180.0F;
		  this.Stalk4.rotateAngleZ = MathHelper.cos((float)ent.ticksExisted * var8) * moo * (float)Math.PI / 180.0F;
		  
		  this.StalkEye4.rotateAngleX = (MathHelper.cos((float)ent.ticksExisted * var8) * moo * (float)Math.PI / 180.0F) - (MathHelper.sin((float)ent.ticksExisted * var8) * moo * (float)Math.PI / 180.0F);
		  //this.StalkEye4.rotateAngleY = MathHelper.sin((float)ent.ticksExisted * var8) * moo * (float)Math.PI / 180.0F;
		  this.StalkEye4.rotateAngleZ = MathHelper.cos((float)ent.ticksExisted * var8) * moo * (float)Math.PI / 180.0F;
		  
		  this.Stalk5.rotateAngleX = (MathHelper.sin((float)ent.ticksExisted * var8) * moo * (float)Math.PI / 180.0F) - (MathHelper.cos((float)ent.ticksExisted * var8) * moo * (float)Math.PI / 180.0F);
		  //this.Stalk5.rotateAngleY = MathHelper.sin((float)ent.ticksExisted * var8) * moo * (float)Math.PI / 180.0F;
		  this.Stalk5.rotateAngleZ = MathHelper.sin((float)ent.ticksExisted * var8) * moo * (float)Math.PI / 180.0F;
		  
		  this.StalkEye5.rotateAngleX = (MathHelper.sin((float)ent.ticksExisted * var8) * moo * (float)Math.PI / 180.0F) - (MathHelper.cos((float)ent.ticksExisted * var8) * moo * (float)Math.PI / 180.0F);
		  //this.StalkEye5.rotateAngleY = MathHelper.sin((float)ent.ticksExisted * var8) * moo * (float)Math.PI / 180.0F;
		  this.StalkEye5.rotateAngleZ = MathHelper.sin((float)ent.ticksExisted * var8) * moo * (float)Math.PI / 180.0F;
		  
		  this.Stalk6.rotateAngleX = (MathHelper.cos((float)ent.ticksExisted * var8) * moo * (float)Math.PI / 180.0F) - (MathHelper.sin((float)ent.ticksExisted * var8) * moo * (float)Math.PI / 180.0F);
		  //this.Stalk6.rotateAngleY = MathHelper.sin((float)ent.ticksExisted * var8) * moo * (float)Math.PI / 180.0F;
		  this.Stalk6.rotateAngleZ = MathHelper.cos((float)ent.ticksExisted * var8) * moo * (float)Math.PI / 180.0F;
		  
		  this.StalkEye6.rotateAngleX = (MathHelper.cos((float)ent.ticksExisted * var8) * moo * (float)Math.PI / 180.0F) - (MathHelper.sin((float)ent.ticksExisted * var8) * moo * (float)Math.PI / 180.0F);
		  //this.StalkEye6.rotateAngleY = MathHelper.sin((float)ent.ticksExisted * var8) * moo * (float)Math.PI / 180.0F;
		  this.StalkEye6.rotateAngleZ = MathHelper.cos((float)ent.ticksExisted * var8) * moo * (float)Math.PI / 180.0F;
		  
		  this.Stalk7.rotateAngleX = (MathHelper.sin((float)ent.ticksExisted * var8) * moo * (float)Math.PI / 180.0F) - (MathHelper.cos((float)ent.ticksExisted * var8) * moo * (float)Math.PI / 180.0F);
		  //this.Stalk7.rotateAngleY = MathHelper.sin((float)ent.ticksExisted * var8) * moo * (float)Math.PI / 180.0F;
		  this.Stalk7.rotateAngleZ = MathHelper.sin((float)ent.ticksExisted * var8) * moo * (float)Math.PI / 180.0F;
		  
		  this.StalkEye7.rotateAngleX = (MathHelper.sin((float)ent.ticksExisted * var8) * moo * (float)Math.PI / 180.0F) - (MathHelper.cos((float)ent.ticksExisted * var8) * moo * (float)Math.PI / 180.0F);
		  //this.StalkEye7.rotateAngleY = MathHelper.sin((float)ent.ticksExisted * var8) * moo * (float)Math.PI / 180.0F;
		  this.StalkEye7.rotateAngleZ = MathHelper.sin((float)ent.ticksExisted * var8) * moo * (float)Math.PI / 180.0F;
		  
		  this.Stalk8.rotateAngleX = (MathHelper.cos((float)ent.ticksExisted * var8) * moo * (float)Math.PI / 180.0F) - (MathHelper.sin((float)ent.ticksExisted * var8) * moo * (float)Math.PI / 180.0F);
		  //this.Stalk8.rotateAngleY = MathHelper.sin((float)ent.ticksExisted * var8) * moo * (float)Math.PI / 180.0F;
		  this.Stalk8.rotateAngleZ = MathHelper.cos((float)ent.ticksExisted * var8) * moo * (float)Math.PI / 180.0F;
		  
		  this.StalkEye8.rotateAngleX = (MathHelper.cos((float)ent.ticksExisted * var8) * moo * (float)Math.PI / 180.0F) - (MathHelper.sin((float)ent.ticksExisted * var8) * moo * (float)Math.PI / 180.0F);
		  //this.StalkEye8.rotateAngleY = MathHelper.sin((float)ent.ticksExisted * var8) * moo * (float)Math.PI / 180.0F;
		  this.StalkEye8.rotateAngleZ = MathHelper.cos((float)ent.ticksExisted * var8) * moo * (float)Math.PI / 180.0F;
	  }

	}