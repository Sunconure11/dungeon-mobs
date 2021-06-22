package com.gw.dm.model;

import com.gw.dm.entity.EntityFallenAngel;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;

public class ModelFallenAngel extends ModelBase {
	private static final float WINGRX = (float) Math.PI / 3;
	private static final float WINGRY = (float) Math.PI / 4;
	private static final float WINGRZ = (float) Math.PI / 6;

	private static final float R_UP = (float) ((Math.PI * 135.0) / 180);
	private static final float L_UP = (float) ((Math.PI * 225.0) / 180);

	public ModelRenderer head;
	public ModelRenderer torso;
	public ModelRenderer rightArm;
	public ModelRenderer leftArm;
	// Top of skirt
	public ModelRenderer base1;
	// Middle of skirt
	public ModelRenderer base2;
	// Bottom of skirt
	public ModelRenderer base3;

	public ModelRenderer rightWing;
	public ModelRenderer leftWing;


	public ModelFallenAngel() {
		textureWidth = 64;
		textureHeight = 68;

		head = new ModelRenderer(this, 0, 0);
		head.addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8);
		head.setRotationPoint(0.0f, 0.0f, 0.0f);

		torso = new ModelRenderer(this, 16, 16);
		torso.addBox(-4.0f, 0.0f, -2.0f, 8, 12, 4);
		torso.setRotationPoint(0.0f, 0.0f, 0.0f);

		rightArm = new ModelRenderer(this, 40, 16);
		rightArm.addBox(-3.0f, -2.0f, -2.0f, 4, 12, 4);
		rightArm.setRotationPoint(-5.0f, 2.0f, 0.0f);

		leftArm = new ModelRenderer(this, 40, 16);
		leftArm.addBox(-1.0f, -2.0f, -2.0f, 4, 12, 4);
		leftArm.setRotationPoint(5.0f, 2.0f, 0.0f);
		leftArm.mirror = true;

		base1 = new ModelRenderer(this, 0, 32);
		base1.addBox(-5.0f, 0.0f, -3.0f, 10, 4, 6);
		base1.setRotationPoint(0.0f, 12.0f, 0.0f);

		base2 = new ModelRenderer(this, 0, 42);
		base2.addBox(-6.0f, 0.0f, -4.0f, 12, 4, 8);
		base2.setRotationPoint(0.0f, 16.0f, 0.0f);

		base3 = new ModelRenderer(this, 0, 54);
		base3.addBox(-7.0f, 0.0f, -5.0f, 14, 4, 10);
		base3.setRotationPoint(0.0f, 20.0f, 0.0f);

		rightWing = new ModelRenderer(this, 42, 32);
		rightWing.addBox(0.0f, 0.0f, 0.0f, 1, 6, 10);
		rightWing.setRotationPoint(-4.0f, 2.0f, 2.0f);
		rightWing.rotateAngleX = (float) WINGRX;
		rightWing.rotateAngleY = (float) -WINGRY;
		rightWing.rotateAngleZ = (float) -WINGRZ;

		leftWing = new ModelRenderer(this, 42, 32);
		leftWing.mirror = true;
		leftWing.addBox(0.0f, 0.0f, 0.0f, 1, 6, 10);
		leftWing.setRotationPoint(4.0f, 2.0f, 2.0f);
		leftWing.rotateAngleX = (float) WINGRX;
		leftWing.rotateAngleY = (float) WINGRY;
		leftWing.rotateAngleZ = (float) WINGRZ;
	}


	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount,
	                   float age, float headYaw, float headPitch, float scale) {
		GlStateManager.pushMatrix(); {
			head.render(scale);
			torso.render(scale);
			rightArm.render(scale);
			leftArm.render(scale);
			base1.render(scale);
			base2.render(scale);
			base3.render(scale);
			rightWing.render(scale);
			leftWing.render(scale);
		} GlStateManager.popMatrix();
	}


	@Override
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks,
	                              float netHeadYaw, float headPitch, float scaleFactor, Entity entityIn) {
		head.rotateAngleY = netHeadYaw * 0.017453292F;
		head.rotateAngleX = headPitch * 0.017453292F;

		EntityFallenAngel angel = (EntityFallenAngel) entityIn;

		float wingMovement1 = (float) Math.toRadians(Math.sin(ageInTicks * 0.2) * 15.0);
		float wingMovement2 = wingMovement1 * 2;
		rightWing.rotateAngleX = (float) WINGRX - wingMovement2;
		rightWing.rotateAngleY = (float) -(WINGRY + wingMovement2);
		rightWing.rotateAngleZ = (float) -(WINGRZ - wingMovement1);
		leftWing.rotateAngleX = (float) WINGRX - wingMovement2;
		leftWing.rotateAngleY = (float) WINGRY + wingMovement2;
		leftWing.rotateAngleZ = (float) WINGRZ - wingMovement1;

		if (angel.areArmsUp()) {
			rightArm.rotateAngleZ = R_UP;
			leftArm.rotateAngleZ = L_UP;
		} else {
			rightArm.rotateAngleZ = 0.0f;
			leftArm.rotateAngleZ = 0.0f;
		}
	}


}
