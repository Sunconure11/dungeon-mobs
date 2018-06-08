package com.gw.dm.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelVescavor extends ModelBase
{

	 ModelRenderer head;
	 ModelRenderer eye;
	 ModelRenderer TailA;
	 ModelRenderer TailB;
	 ModelRenderer TailC;
	 ModelRenderer RLegMid;
	 ModelRenderer RLegFront;
	 ModelRenderer RLegRear;
	 ModelRenderer LLegMid;
	 ModelRenderer LLegFront;
	 ModelRenderer LLegRear;
	 ModelRenderer WingBaseL;
	 ModelRenderer WingBaseR;
	 ModelRenderer WingRA;
	 ModelRenderer WingLA;
	 ModelRenderer WingRB;
	 ModelRenderer WingLB;

	public ModelVescavor()
	{
		 textureWidth = 64;
		 textureHeight = 32;
		 
		   head = new ModelRenderer(this, 0, 0);
		   head.addBox(-3F, -3F, -3F, 6, 6, 6);
		   head.setRotationPoint(0F, 18F, -4F);
		   head.setTextureSize(64, 32);
		   head.mirror = true;
		   setRotation(head, 0F, 0F, 0F);
		   eye = new ModelRenderer(this, 0, 13);
		   eye.addBox(-1F, -1.5F, -1F, 2, 3, 2);
		   eye.setRotationPoint(0F, 17.5F, -6.5F);
		   eye.setTextureSize(64, 32);
		   eye.mirror = true;
		   setRotation(eye, 0F, 0F, 0F);
		   TailA = new ModelRenderer(this, 0, 24);
		   TailA.addBox(-2F, -2F, 0F, 4, 4, 4);
		   TailA.setRotationPoint(0F, 18F, -1F);
		   TailA.setTextureSize(64, 32);
		   TailA.mirror = true;
		   setRotation(TailA, 0F, 0F, 0.7853982F);
		   TailB = new ModelRenderer(this, 16, 25);
		   TailB.addBox(-1.5F, -1.5F, 4F, 3, 3, 4);
		   TailB.setRotationPoint(0F, 18F, -1F);
		   TailB.setTextureSize(64, 32);
		   TailB.mirror = true;
		   setRotation(TailB, 0F, 0F, 0.7853982F);
		   TailC = new ModelRenderer(this, 30, 27);
		   TailC.addBox(-1F, -1F, 8F, 2, 2, 3);
		   TailC.setRotationPoint(0F, 18F, -1F);
		   TailC.setTextureSize(64, 32);
		   TailC.mirror = true;
		   setRotation(TailC, 0F, 0F, 0.7853982F);
		   RLegMid = new ModelRenderer(this, 0, 20);
		   RLegMid.addBox(-9F, -0.5F, -0.5F, 9, 1, 1);
		   RLegMid.setRotationPoint(-2F, 18F, -4F);
		   RLegMid.setTextureSize(64, 32);
		   RLegMid.mirror = true;
		   setRotation(RLegMid, 0F, 0F, -0.3490659F);
		   RLegFront = new ModelRenderer(this, 0, 20);
		   RLegFront.addBox(-9F, -0.5F, -0.5F, 9, 1, 1);
		   RLegFront.setRotationPoint(-2F, 18F, -5.5F);
		   RLegFront.setTextureSize(64, 32);
		   RLegFront.mirror = true;
		   setRotation(RLegFront, 0F, -0.2617994F, -0.3490659F);
		   RLegRear = new ModelRenderer(this, 0, 20);
		   RLegRear.addBox(-9F, -0.5F, -0.5F, 9, 1, 1);
		   RLegRear.setRotationPoint(-2F, 18F, -2.5F);
		   RLegRear.setTextureSize(64, 32);
		   RLegRear.mirror = true;
		   setRotation(RLegRear, 0F, 0.2617994F, -0.3490659F);
		   LLegMid = new ModelRenderer(this, 0, 20);
		      LLegMid.addBox(0F, -0.5F, -0.5F, 9, 1, 1);
		      LLegMid.setRotationPoint(2F, 18F, -4F);
		      LLegMid.setTextureSize(64, 32);
		      LLegMid.mirror = true;
		      setRotation(LLegMid, 0F, 0F, 0.3490659F);
		   LLegFront = new ModelRenderer(this, 0, 20);
		   LLegFront.addBox(0F, -0.5F, -0.5F, 9, 1, 1);
		   LLegFront.setRotationPoint(2F, 18F, -5.5F);
		   LLegFront.setTextureSize(64, 32);
		   LLegFront.mirror = true;
		   setRotation(LLegFront, 0F, 0.2617994F, 0.3490659F);
		   LLegRear = new ModelRenderer(this, 0, 20);
		      LLegRear.addBox(0F, -0.5F, -0.5F, 9, 1, 1);
		      LLegRear.setRotationPoint(2F, 18F, -2.5F);
		      LLegRear.setTextureSize(64, 32);
		      LLegRear.mirror = true;
		      setRotation(LLegRear, 0F, -0.2617994F, 0.3490659F);
		   WingBaseL = new ModelRenderer(this, 0, 0);
		   WingBaseL.addBox(-0.5F, -0.5F, -1F, 1, 1, 2);
		   WingBaseL.setRotationPoint(1.5F, 15F, -3F);
		   WingBaseL.setTextureSize(64, 32);
		   WingBaseL.mirror = true;
		   setRotation(WingBaseL, 0F, 0F, 0.7853982F);
		   WingBaseR = new ModelRenderer(this, 0, 0);
		   WingBaseR.addBox(-0.5F, -0.5F, -1F, 1, 1, 2);
		   WingBaseR.setRotationPoint(-1.5F, 15F, -3F);
		   WingBaseR.setTextureSize(64, 32);
		   WingBaseR.mirror = true;
		   setRotation(WingBaseR, 0F, 0F, 0.7853982F);
		   WingRA = new ModelRenderer(this, 26, 0);
		   WingRA.addBox(0F, -0.5F, 0F, 0, 1, 8);
		   WingRA.setRotationPoint(-1.5F, 15F, -3.2F);
		   WingRA.setTextureSize(64, 32);
		   WingRA.mirror = true;
		   setRotation(WingRA, 0.9250245F, 0F, -0.2617994F);
		   WingLA = new ModelRenderer(this, 26, 0);
		   WingLA.addBox(0F, -0.5F, 0F, 0, 1, 8);
		   WingLA.setRotationPoint(1.5F, 15F, -3.2F);
		   WingLA.setTextureSize(64, 32);
		   WingLA.mirror = true;
		   setRotation(WingLA, 0.9250245F, 0F, 0.2617994F);
		   WingRB = new ModelRenderer(this, 26, 0);
		   WingRB.addBox(0F, -0.5F, 0F, 0, 1, 8);
		   WingRB.setRotationPoint(-1.5F, 15F, -3.2F);
		   WingRB.setTextureSize(64, 32);
		   WingRB.mirror = true;
		   setRotation(WingRB, 0.6806784F, 0F, -0.6806784F);
		   WingLB = new ModelRenderer(this, 26, 0);
		   WingLB.addBox(0F, -0.5F, 0F, 0, 1, 8);
		   WingLB.setRotationPoint(1.5F, 15F, -3.2F);
		   WingLB.setTextureSize(64, 32);
		   WingLB.mirror = true;
		   setRotation(WingLB, 0.6806784F, 0F, 0.6806784F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		 super.render(entity, f, f1, f2, f3, f4, f5);
		 setRotationAngles(f, f1, f2, f3, f4, f5);
		 head.render(f5);
		 eye.render(f5);
		 TailA.render(f5);
		 TailB.render(f5);
		 TailC.render(f5);
		 RLegMid.render(f5);
		 RLegFront.render(f5);
		 RLegRear.render(f5);
		 LLegMid.render(f5);
		 LLegFront.render(f5);
		 LLegRear.render(f5);
		 WingBaseL.render(f5);
		 WingBaseR.render(f5);
		 WingRA.render(f5);
		 WingLA.render(f5);
		 WingRB.render(f5);
		 WingLB.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z)
	{
		 model.rotateAngleX = x;
		 model.rotateAngleY = y;
		 model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
	{
		//super.setRotationAngles(f, f1, f2, f3, f4, f5);
		
		this.LLegFront.rotateAngleZ = 0.3490659F - MathHelper.cos(f * 0.7662F) * 1.4F * f1;
		this.LLegMid.rotateAngleZ = 0.3490659F + MathHelper.cos(f * 0.7662F) * 1.4F * f1;
		this.LLegRear.rotateAngleZ = 0.3490659F - MathHelper.cos(f * 0.7662F) * 1.4F * f1;
		
		this.RLegFront.rotateAngleZ = -0.3490659F + MathHelper.cos(f * 0.7662F) * 1.4F * f1;
		this.RLegMid.rotateAngleZ = -0.3490659F - MathHelper.cos(f * 0.7662F) * 1.4F * f1;
		this.RLegRear.rotateAngleZ = -0.3490659F + MathHelper.cos(f * 0.7662F) * 1.4F * f1;
	}

}
