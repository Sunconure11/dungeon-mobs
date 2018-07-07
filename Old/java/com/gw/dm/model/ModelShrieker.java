package com.gw.dm.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelShrieker extends ModelBase
{
  //fields
    ModelRenderer Stem;
    ModelRenderer CapTop;
    ModelRenderer CapOverRear;
    ModelRenderer CapMid;
    ModelRenderer CapBase;
    ModelRenderer CapOverLeft;
    ModelRenderer CapOverFront;
    ModelRenderer CapOverRight;
  
  public ModelShrieker()
  {
	  
	  
    textureWidth = 64;
    textureHeight = 32;
    
      Stem = new ModelRenderer(this, 0, 0);
      Stem.addBox(-3F, 0F, -3F, 6, 8, 6);
      Stem.setRotationPoint(0F, 16F, 0F);
      Stem.setTextureSize(64, 32);
      Stem.mirror = true;
      setRotation(Stem, 0F, 0F, 0F);
      CapTop = new ModelRenderer(this, 16, 19);
      CapTop.addBox(-5F, 0F, -5F, 10, 1, 10);
      CapTop.setRotationPoint(0F, 10F, 0F);
      CapTop.setTextureSize(64, 32);
      CapTop.mirror = true;
      setRotation(CapTop, 0F, 0F, 0F);
      CapOverRear = new ModelRenderer(this, 25, 0);
      CapOverRear.addBox(-8F, 0F, 0F, 16, 2, 3);
      CapOverRear.setRotationPoint(0F, 15F, 5F);
      CapOverRear.setTextureSize(64, 32);
      CapOverRear.mirror = true;
      setRotation(CapOverRear, 0F, 0F, 0F);
      CapMid = new ModelRenderer(this, 12, 17);
      CapMid.addBox(-6F, 0F, -6F, 12, 2, 12);
      CapMid.setRotationPoint(0F, 11F, 0F);
      CapMid.setTextureSize(64, 32);
      CapMid.mirror = true;
      setRotation(CapMid, 0F, 1.570796F, 0F);
      CapBase = new ModelRenderer(this, 8, 15);
      CapBase.addBox(-7F, 0F, -7F, 14, 3, 14);
      CapBase.setRotationPoint(0F, 13F, 0F);
      CapBase.setTextureSize(64, 32);
      CapBase.mirror = true;
      setRotation(CapBase, 0F, 0F, 0F);
      CapOverLeft = new ModelRenderer(this, 25, 0);
      CapOverLeft.addBox(-8F, 0F, -3F, 16, 2, 3);
      CapOverLeft.setRotationPoint(5F, 15F, 0F);
      CapOverLeft.setTextureSize(64, 32);
      CapOverLeft.mirror = true;
      setRotation(CapOverLeft, 0F, -1.570796F, 0F);
      CapOverFront = new ModelRenderer(this, 25, 0);
      CapOverFront.addBox(-8F, 0F, -3F, 16, 2, 3);
      CapOverFront.setRotationPoint(0F, 15F, -5F);
      CapOverFront.setTextureSize(64, 32);
      CapOverFront.mirror = true;
      setRotation(CapOverFront, 0F, 0F, 0F);
      CapOverRight = new ModelRenderer(this, 25, 0);
      CapOverRight.addBox(-8F, 0F, -3F, 16, 2, 3);
      CapOverRight.setRotationPoint(-5F, 15F, 0F);
      CapOverRight.setTextureSize(64, 32);
      CapOverRight.mirror = true;
      setRotation(CapOverRight, 0F, 1.570796F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    Stem.render(f5);
    CapTop.render(f5);
    CapOverRear.render(f5);
    CapMid.render(f5);
    CapBase.render(f5);
    CapOverLeft.render(f5);
    CapOverFront.render(f5);
    CapOverRight.render(f5);
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
  }

}
