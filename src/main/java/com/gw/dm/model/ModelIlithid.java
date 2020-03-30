package com.gw.dm.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

@SuppressWarnings({"WeakerAccess", "NullableProblems"})
public class ModelIlithid extends ModelBiped {
	public ModelRenderer bipedBody;
	public ModelRenderer bipedHead;
	public ModelRenderer BipedLeftLeg;
	public ModelRenderer bipedRightArm;
	public ModelRenderer bipedRightLeg;
	public ModelRenderer bipedLeftArm;
	public ModelRenderer capeA;
	public ModelRenderer bipedHeadwear;
	public ModelRenderer faceTentacleA1;
	public ModelRenderer faceTentacleB1;
	public ModelRenderer faceTentacleC1;
	public ModelRenderer faceTentacleA2;
	public ModelRenderer faceTentacleA3;
	public ModelRenderer faceTentacleB2;
	public ModelRenderer faceTentacleB3;
	public ModelRenderer faceTentacleC2;
	public ModelRenderer faceTentacleC3;
	public ModelRenderer shoulderPad;
	public ModelRenderer shoulderPad2;
	public ModelRenderer capeB;
	public ModelRenderer capeB1;
	public ModelRenderer capeB2;
	public ModelRenderer capeC;
	public ModelRenderer capeB4;
	public ModelRenderer capeB3;
	
	public ModelIlithid() {
		this.textureWidth = 128;
		this.textureHeight = 128;
		this.bipedRightLeg = new ModelRenderer(this, 92, 0);
		this.bipedRightLeg.setRotationPoint(2.0F, 9.5F, 0.0F);
		this.bipedRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 15, 4, 0.0F);
		this.faceTentacleA1 = new ModelRenderer(this, 100, 65);
		this.faceTentacleA1.setRotationPoint(0.0F, -2.5F, -2.5F);
		this.faceTentacleA1.addBox(-1.5F, -1.5F, -5.0F, 3, 3, 5, 0.0F);
		this.setRotateAngle(faceTentacleA1, 0.7853981633974483F, 0.0F, 0.0F);
		this.capeA = new ModelRenderer(this, 5, 45);
		this.capeA.setRotationPoint(0.0F, -6.0F, 3.0F);
		this.capeA.addBox(-5.0F, 0.0F, 0.0F, 10, 5, 0, 0.0F);
		this.setRotateAngle(capeA, 0.5235987755982988F, 0.0F, 0.0F);
		this.bipedHead = new ModelRenderer(this, 30, 0);
		this.bipedHead.setRotationPoint(0.0F, -5.5F, 0.0F);
		this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
		this.faceTentacleC3 = new ModelRenderer(this, 77, 100);
		this.faceTentacleC3.setRotationPoint(0.0F, 0.0F, -4.1F);
		this.faceTentacleC3.addBox(-0.5F, -0.5F, -4.0F, 1, 1, 4, 0.0F);
		this.setRotateAngle(faceTentacleC3, 0.3490658503988659F, 0.0F, 0.0F);
		this.shoulderPad = new ModelRenderer(this, 57, 19);
		this.shoulderPad.setRotationPoint(-2.0F, -2.0F, 0.0F);
		this.shoulderPad.addBox(0.0F, -1.0F, -3.5F, 6, 3, 7, 0.0F);
		this.setRotateAngle(shoulderPad, 0.0F, 0.0F, 0.08726646259971647F);
		this.capeB2 = new ModelRenderer(this, 60, 45);
		this.capeB2.setRotationPoint(3.5F, 0.0F, 0.0F);
		this.capeB2.addBox(0.0F, 0.0F, 0.0F, 5, 9, 0, 0.0F);
		this.setRotateAngle(capeB2, 0.0F, 0.36425021489121656F, 0.0F);
		this.capeB3 = new ModelRenderer(this, 50, 75);
		this.capeB3.setRotationPoint(0.0F, 9.0F, 0.0F);
		this.capeB3.addBox(0.0F, 0.0F, 0.0F, 6, 10, 0, 0.0F);
		this.bipedHeadwear = new ModelRenderer(this, 21, 16);
		this.bipedHeadwear.setRotationPoint(0.0F, 0.5F, 0.0F);
		this.bipedHeadwear.addBox(-4.5F, -9.0F, -4.5F, 9, 9, 9, 0.0F);
		this.faceTentacleB2 = new ModelRenderer(this, 77, 80);
		this.faceTentacleB2.mirror = true;
		this.faceTentacleB2.setRotationPoint(0.0F, 0.0F, -4.0F);
		this.faceTentacleB2.addBox(-0.5F, -1.0F, -5.0F, 1, 2, 5, 0.0F);
		this.setRotateAngle(faceTentacleB2, 0.5235987755982988F, 0.0F, 0.0F);
		this.faceTentacleC1 = new ModelRenderer(this, 90, 60);
		this.faceTentacleC1.setRotationPoint(-2.5F, -2.5F, -2.5F);
		this.faceTentacleC1.addBox(-1.0F, -1.0F, -5.0F, 2, 2, 5, 0.0F);
		this.setRotateAngle(faceTentacleC1, 0.7853981633974483F, 0.2617993877991494F, 0.0F);
		this.shoulderPad2 = new ModelRenderer(this, 83, 19);
		this.shoulderPad2.setRotationPoint(2.0F, -2.0F, 0.0F);
		this.shoulderPad2.addBox(-6.0F, -1.0F, -3.5F, 6, 3, 7, 0.0F);
		this.setRotateAngle(shoulderPad2, 0.0F, 0.0F, -0.08726646259971647F);
		this.capeB4 = new ModelRenderer(this, 30, 75);
		this.capeB4.setRotationPoint(0.0F, 9.0F, 0.0F);
		this.capeB4.addBox(-6.0F, 0.0F, 0.0F, 6, 10, 0, 0.0F);
		this.faceTentacleA3 = new ModelRenderer(this, 100, 100);
		this.faceTentacleA3.setRotationPoint(0.0F, 0.0F, -4.1F);
		this.faceTentacleA3.addBox(-0.5F, -0.5F, -4.0F, 1, 1, 4, 0.0F);
		this.setRotateAngle(faceTentacleA3, 0.3490658503988659F, 0.0F, 0.0F);
		this.faceTentacleC2 = new ModelRenderer(this, 77, 80);
		this.faceTentacleC2.setRotationPoint(0.0F, 0.0F, -4.0F);
		this.faceTentacleC2.addBox(-0.5F, -1.0F, -5.0F, 1, 2, 5, 0.0F);
		this.setRotateAngle(faceTentacleC2, 0.5235987755982988F, 0.0F, 0.0F);
		this.BipedLeftLeg = new ModelRenderer(this, 62, 0);
		this.BipedLeftLeg.mirror = true;
		this.BipedLeftLeg.setRotationPoint(-2.0F, 9.5F, 0.0F);
		this.BipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 15, 4, 0.0F);
		this.capeB1 = new ModelRenderer(this, 30, 45);
		this.capeB1.setRotationPoint(-3.5F, 0.0F, 0.0F);
		this.capeB1.addBox(-5.0F, 0.0F, 0.0F, 5, 9, 0, 0.0F);
		this.setRotateAngle(capeB1, 0.0F, -0.5462880558742251F, 0.0F);
		this.bipedLeftArm = new ModelRenderer(this, 108, 0);
		this.bipedLeftArm.setRotationPoint(-5.5F, -3.0F, 0.0F);
		this.bipedLeftArm.addBox(-2.0F, -2.0F, -2.0F, 3, 15, 4, 0.0F);
		this.bipedBody = new ModelRenderer(this, 0, 0);
		this.bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.bipedBody.addBox(-4.5F, -5.5F, -3.0F, 9, 16, 6, 0.0F);
		this.faceTentacleB3 = new ModelRenderer(this, 77, 100);
		this.faceTentacleB3.mirror = true;
		this.faceTentacleB3.setRotationPoint(0.0F, 0.0F, -4.1F);
		this.faceTentacleB3.addBox(-0.5F, -0.5F, -4.0F, 1, 1, 4, 0.0F);
		this.setRotateAngle(faceTentacleB3, 0.3490658503988659F, 0.0F, 0.0F);
		this.capeB = new ModelRenderer(this, 5, 70);
		this.capeB.setRotationPoint(0.0F, 5.0F, 0.0F);
		this.capeB.addBox(-3.5F, 0.0F, 0.0F, 7, 9, 0, 0.0F);
		this.setRotateAngle(capeB, -0.22759093446006054F, 0.0F, 0.0F);
		this.capeC = new ModelRenderer(this, 5, 100);
		this.capeC.setRotationPoint(0.0F, 9.0F, 0.0F);
		this.capeC.addBox(-3.5F, 0.0F, 0.0F, 7, 14, 0, 0.0F);
		this.setRotateAngle(capeC, -0.18203784098300857F, 0.0F, 0.0F);
		this.faceTentacleA2 = new ModelRenderer(this, 100, 80);
		this.faceTentacleA2.setRotationPoint(0.0F, 0.0F, -4.0F);
		this.faceTentacleA2.addBox(-1.0F, -1.0F, -5.0F, 2, 2, 5, 0.0F);
		this.setRotateAngle(faceTentacleA2, 0.5235987755982988F, 0.0F, 0.0F);
		this.faceTentacleB1 = new ModelRenderer(this, 90, 60);
		this.faceTentacleB1.setRotationPoint(2.5F, -2.5F, -2.5F);
		this.faceTentacleB1.addBox(-1.0F, -1.0F, -5.0F, 2, 2, 5, 0.0F);
		this.setRotateAngle(faceTentacleB1, 0.7853981633974483F, -0.2617993877991494F, 0.0F);
		this.bipedRightArm = new ModelRenderer(this, 78, 0);
		this.bipedRightArm.mirror = true;
		this.bipedRightArm.setRotationPoint(5.5F, -3.0F, 0.0F);
		this.bipedRightArm.addBox(-1.0F, -2.0F, -2.0F, 3, 15, 4, 0.0F);
		this.bipedBody.addChild(this.bipedRightLeg);
		this.bipedHead.addChild(this.faceTentacleA1);
		this.bipedBody.addChild(this.capeA);
		this.bipedBody.addChild(this.bipedHead);
		this.faceTentacleC2.addChild(this.faceTentacleC3);
		this.bipedRightArm.addChild(this.shoulderPad);
		this.capeB.addChild(this.capeB2);
		this.capeB2.addChild(this.capeB3);
		this.bipedHead.addChild(this.bipedHeadwear);
		this.faceTentacleB1.addChild(this.faceTentacleB2);
		this.bipedHead.addChild(this.faceTentacleC1);
		this.bipedLeftArm.addChild(this.shoulderPad2);
		this.capeB1.addChild(this.capeB4);
		this.faceTentacleA2.addChild(this.faceTentacleA3);
		this.faceTentacleC1.addChild(this.faceTentacleC2);
		this.bipedBody.addChild(this.BipedLeftLeg);
		this.capeB.addChild(this.capeB1);
		this.bipedBody.addChild(this.bipedLeftArm);
		this.faceTentacleB2.addChild(this.faceTentacleB3);
		this.capeA.addChild(this.capeB);
		this.capeB.addChild(this.capeC);
		this.faceTentacleA1.addChild(this.faceTentacleA2);
		this.bipedHead.addChild(this.faceTentacleB1);
		this.bipedBody.addChild(this.bipedRightArm);
	}
	
	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.bipedBody.render(f5);
	}
	
	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
