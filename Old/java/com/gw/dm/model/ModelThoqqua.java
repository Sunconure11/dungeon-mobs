package com.gw.dm.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelThoqqua extends ModelBase
{
  //fields
    ModelRenderer BodyMid;
    ModelRenderer HeadConeEnd;
    ModelRenderer HeadConeLow;
    ModelRenderer HeadConeMid;
    ModelRenderer TailEnd;
    ModelRenderer BodyFront;
    ModelRenderer TailBase;
    ModelRenderer TailMid;
    ModelRenderer SpineRF;
    ModelRenderer SpineRA;
    ModelRenderer SpineRB;
    ModelRenderer SpineRC;
    ModelRenderer SpineRD;
    ModelRenderer SpineRE;
    ModelRenderer SpineA;
    ModelRenderer SpineB;
    ModelRenderer SpineC;
    ModelRenderer SpineD;
    ModelRenderer SpineE;
    ModelRenderer SpineF;
    ModelRenderer SpineLA;
    ModelRenderer SpineLD;
    ModelRenderer SpineLC;
    ModelRenderer SpineLB;
    ModelRenderer SpineLE;
    ModelRenderer SpineLF;
  
  public ModelThoqqua()
  {
    textureWidth = 64;
    textureHeight = 32;
    
      BodyMid = new ModelRenderer(this, 34, 17);
      BodyMid.addBox(0F, 0F, 0F, 5, 5, 10);
      BodyMid.setRotationPoint(-2.5F, 19F, -6F);
      BodyMid.setTextureSize(64, 32);
      BodyMid.mirror = true;
      setRotation(BodyMid, 0F, 0F, 0F);
      HeadConeEnd = new ModelRenderer(this, 0, 30);
      HeadConeEnd.addBox(-0.5F, -0.5F, -7F, 1, 1, 1);
      HeadConeEnd.setRotationPoint(0F, 21.5F, -9F);
      HeadConeEnd.setTextureSize(64, 32);
      HeadConeEnd.mirror = true;
      setRotation(HeadConeEnd, 0F, 0F, 0F);
      HeadConeLow = new ModelRenderer(this, 4, 28);
      HeadConeLow.addBox(-1F, -1F, -6F, 2, 2, 2);
      HeadConeLow.setRotationPoint(0F, 21.5F, -9F);
      HeadConeLow.setTextureSize(64, 32);
      HeadConeLow.mirror = true;
      setRotation(HeadConeLow, 0F, 0F, 0F);
      HeadConeMid = new ModelRenderer(this, 12, 25);
      HeadConeMid.addBox(-1.5F, -1.5F, -4F, 3, 3, 4);
      HeadConeMid.setRotationPoint(0F, 21.5F, -9F);
      HeadConeMid.setTextureSize(64, 32);
      HeadConeMid.mirror = true;
      setRotation(HeadConeMid, 0F, 0F, 0F);
      TailEnd = new ModelRenderer(this, 21, 16);
      TailEnd.addBox(-1F, 0F, 7F, 2, 2, 4);
      TailEnd.setRotationPoint(0F, 22F, 3F);
      TailEnd.setTextureSize(64, 32);
      TailEnd.mirror = true;
      setRotation(TailEnd, 0F, 0F, 0F);
      BodyFront = new ModelRenderer(this, 46, 0);
      BodyFront.addBox(0F, 0F, 0F, 4, 4, 5);
      BodyFront.setRotationPoint(-2F, 19.5F, -10F);
      BodyFront.setTextureSize(64, 32);
      BodyFront.mirror = true;
      setRotation(BodyFront, 0F, 0F, 0F);
      TailBase = new ModelRenderer(this, 26, 0);
      TailBase.addBox(-2F, -2F, 0F, 4, 4, 5);
      TailBase.setRotationPoint(0F, 22F, 3F);
      TailBase.setTextureSize(64, 32);
      TailBase.mirror = true;
      setRotation(TailBase, 0F, 0F, 0F);
      TailMid = new ModelRenderer(this, 0, 7);
      TailMid.addBox(-1.5F, -1F, 4F, 3, 3, 4);
      TailMid.setRotationPoint(0F, 22F, 3F);
      TailMid.setTextureSize(64, 32);
      TailMid.mirror = true;
      setRotation(TailMid, 0F, 0F, 0F);
      SpineRF = new ModelRenderer(this, 19, 14);
      SpineRF.addBox(-0.5F, -0.5F, 0F, 1, 1, 6);
      SpineRF.setRotationPoint(-0.5F, 22.8F, 6F);
      SpineRF.setTextureSize(64, 32);
      SpineRF.mirror = true;
      setRotation(SpineRF, 0F, -0.1745329F, 0F);
      SpineRA = new ModelRenderer(this, 19, 14);
      SpineRA.addBox(-0.5F, -0.5F, 0F, 1, 1, 7);
      SpineRA.setRotationPoint(-1.5F, 22F, -10F);
      SpineRA.setTextureSize(64, 32);
      SpineRA.mirror = true;
      setRotation(SpineRA, 0F, -0.1745329F, 0F);
      SpineRB = new ModelRenderer(this, 19, 14);
      SpineRB.addBox(-0.5F, -0.5F, 0F, 1, 1, 6);
      SpineRB.setRotationPoint(-1.5F, 22F, -6F);
      SpineRB.setTextureSize(64, 32);
      SpineRB.mirror = true;
      setRotation(SpineRB, 0F, -0.1745329F, 0F);
      SpineRC = new ModelRenderer(this, 19, 14);
      SpineRC.addBox(-0.5F, -0.5F, 0F, 1, 1, 6);
      SpineRC.setRotationPoint(-1.5F, 22F, -3F);
      SpineRC.setTextureSize(64, 32);
      SpineRC.mirror = true;
      setRotation(SpineRC, 0F, -0.1745329F, 0F);
      SpineRD = new ModelRenderer(this, 19, 14);
      SpineRD.addBox(-0.5F, -0.5F, 0F, 1, 1, 6);
      SpineRD.setRotationPoint(-1.5F, 22.2F, 0F);
      SpineRD.setTextureSize(64, 32);
      SpineRD.mirror = true;
      setRotation(SpineRD, 0F, -0.1745329F, 0F);
      SpineRE = new ModelRenderer(this, 19, 14);
      SpineRE.addBox(-0.5F, -0.5F, 0F, 1, 1, 6);
      SpineRE.setRotationPoint(-1F, 22.4F, 3F);
      SpineRE.setTextureSize(64, 32);
      SpineRE.mirror = true;
      setRotation(SpineRE, 0F, -0.1745329F, 0F);
      SpineA = new ModelRenderer(this, 19, 14);
      SpineA.addBox(-0.5F, -0.5F, 0F, 1, 1, 7);
      SpineA.setRotationPoint(0F, 20F, -10F);
      SpineA.setTextureSize(64, 32);
      SpineA.mirror = true;
      setRotation(SpineA, 0.1745329F, 0F, 0F);
      SpineB = new ModelRenderer(this, 19, 14);
      SpineB.addBox(-0.5F, -0.5F, 0F, 1, 1, 6);
      SpineB.setRotationPoint(0F, 20F, -6F);
      SpineB.setTextureSize(64, 32);
      SpineB.mirror = true;
      setRotation(SpineB, 0.1745329F, 0F, 0F);
      SpineC = new ModelRenderer(this, 19, 14);
      SpineC.addBox(-0.5F, -0.5F, 0F, 1, 1, 6);
      SpineC.setRotationPoint(0F, 20F, -3F);
      SpineC.setTextureSize(64, 32);
      SpineC.mirror = true;
      setRotation(SpineC, 0.1745329F, 0F, 0F);
      SpineD = new ModelRenderer(this, 19, 14);
      SpineD.addBox(-0.5F, -0.5F, 0F, 1, 1, 6);
      SpineD.setRotationPoint(0F, 20F, 0F);
      SpineD.setTextureSize(64, 32);
      SpineD.mirror = true;
      setRotation(SpineD, 0.1745329F, 0F, 0F);
      SpineE = new ModelRenderer(this, 19, 14);
      SpineE.addBox(-0.5F, -0.5F, 0F, 1, 1, 6);
      SpineE.setRotationPoint(0F, 20.7F, 3F);
      SpineE.setTextureSize(64, 32);
      SpineE.mirror = true;
      setRotation(SpineE, 0.1745329F, 0F, 0F);
      SpineF = new ModelRenderer(this, 19, 14);
      SpineF.addBox(-0.5F, -0.5F, 0F, 1, 1, 6);
      SpineF.setRotationPoint(0F, 21.7F, 6F);
      SpineF.setTextureSize(64, 32);
      SpineF.mirror = true;
      setRotation(SpineF, 0.1745329F, 0F, 0F);
      SpineLA = new ModelRenderer(this, 19, 14);
      SpineLA.addBox(-0.5F, -0.5F, 0F, 1, 1, 7);
      SpineLA.setRotationPoint(1.5F, 22F, -10F);
      SpineLA.setTextureSize(64, 32);
      SpineLA.mirror = true;
      setRotation(SpineLA, 0F, 0.1745329F, 0F);
      SpineLD = new ModelRenderer(this, 19, 14);
      SpineLD.addBox(-0.5F, -0.5F, 0F, 1, 1, 6);
      SpineLD.setRotationPoint(1.5F, 22.2F, 0F);
      SpineLD.setTextureSize(64, 32);
      SpineLD.mirror = true;
      setRotation(SpineLD, 0F, 0.1745329F, 0F);
      SpineLC = new ModelRenderer(this, 19, 14);
      SpineLC.addBox(-0.5F, -0.5F, 0F, 1, 1, 6);
      SpineLC.setRotationPoint(1.5F, 22F, -3F);
      SpineLC.setTextureSize(64, 32);
      SpineLC.mirror = true;
      setRotation(SpineLC, 0F, 0.1745329F, 0F);
      SpineLB = new ModelRenderer(this, 19, 14);
      SpineLB.addBox(-0.5F, -0.5F, 0F, 1, 1, 6);
      SpineLB.setRotationPoint(1.5F, 22F, -6F);
      SpineLB.setTextureSize(64, 32);
      SpineLB.mirror = true;
      setRotation(SpineLB, 0F, 0.1745329F, 0F);
      SpineLE = new ModelRenderer(this, 19, 14);
      SpineLE.addBox(-0.5F, -0.5F, 0F, 1, 1, 6);
      SpineLE.setRotationPoint(1F, 22.4F, 3F);
      SpineLE.setTextureSize(64, 32);
      SpineLE.mirror = true;
      setRotation(SpineLE, 0F, 0.1745329F, 0F);
      SpineLF = new ModelRenderer(this, 19, 14);
      SpineLF.addBox(-0.5F, -0.5F, 0F, 1, 1, 6);
      SpineLF.setRotationPoint(0.5F, 22.8F, 6F);
      SpineLF.setTextureSize(64, 32);
      SpineLF.mirror = true;
      setRotation(SpineLF, 0F, 0.1745329F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    BodyMid.render(f5);
    HeadConeEnd.render(f5);
    HeadConeLow.render(f5);
    HeadConeMid.render(f5);
    TailEnd.render(f5);
    BodyFront.render(f5);
    TailBase.render(f5);
    TailMid.render(f5);
    SpineRF.render(f5);
    SpineRA.render(f5);
    SpineRB.render(f5);
    SpineRC.render(f5);
    SpineRD.render(f5);
    SpineRE.render(f5);
    SpineA.render(f5);
    SpineB.render(f5);
    SpineC.render(f5);
    SpineD.render(f5);
    SpineE.render(f5);
    SpineF.render(f5);
    SpineLA.render(f5);
    SpineLD.render(f5);
    SpineLC.render(f5);
    SpineLB.render(f5);
    SpineLE.render(f5);
    SpineLF.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity ent)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, ent);
    
        HeadConeEnd.rotateAngleY = (f3 / 57.29578F) * .2F;
	    HeadConeEnd.rotateAngleX = (f4 / 57.29578F) * .2F;
	    
	    HeadConeLow.rotateAngleY = (f3 / 57.29578F) * .2F;
	    HeadConeLow.rotateAngleX = (f4 / 57.29578F) * .2F;
	    
	    HeadConeMid.rotateAngleY = (f3 / 57.29578F) * .2F;
	    HeadConeMid.rotateAngleX = (f4 / 57.29578F) * .2F;
  }

}