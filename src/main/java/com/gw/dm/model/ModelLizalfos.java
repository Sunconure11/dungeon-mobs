package com.gw.dm.model;

import com.gw.dm.entity.EntityLizalfos;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelLizalfos extends ModelBase {

	ModelRenderer ChestOutTop;
	ModelRenderer LegLeftMid;
	ModelRenderer TailMid;
	ModelRenderer FootLeftClaws;
	ModelRenderer LegLeftTop;
	ModelRenderer LegLeftLow;
	ModelRenderer FootLeft;
	ModelRenderer ArmRightLow;
	ModelRenderer LegRightMid;
	ModelRenderer FootRightClaws;
	ModelRenderer FootRight;
	ModelRenderer LegRightLow;
	ModelRenderer HipLeft;
	ModelRenderer ArmLeftGuard;
	ModelRenderer TailEnd;
	ModelRenderer TailHelper;
	ModelRenderer Pelvis;
	ModelRenderer StomachLow;
	ModelRenderer SpineLow;
	ModelRenderer SpineMid;
	ModelRenderer StomachTop;
	ModelRenderer TailBase;
	ModelRenderer HipRight;
	ModelRenderer ShoulderRight;
	ModelRenderer LegRightTop;
	ModelRenderer ArmLeftTop;
	ModelRenderer ArmRightTop;
	ModelRenderer ShoulderLeft;
	ModelRenderer ArmLeft;
	ModelRenderer Neck;
	ModelRenderer Head5;
	ModelRenderer Head1;
	ModelRenderer HeadEyeLeft;
	ModelRenderer Head4;
	ModelRenderer HeadEyeRight;
	ModelRenderer Head2;
	ModelRenderer Head7;
	ModelRenderer Head6;
	ModelRenderer ChestTop;
	ModelRenderer ChestLow;
	ModelRenderer ChestOutLow;
	ModelRenderer Head3;

	ModelRenderer SwordBladeTop;
	ModelRenderer SwordHilt;
	ModelRenderer SwordCrossbar;
	ModelRenderer SwordBladeBase;
	ModelRenderer SwordBladeMid;
	ModelRenderer SwordBladePoint;


	public ModelLizalfos() {
		textureWidth = 64;
		textureHeight = 32;

		ChestOutTop = new ModelRenderer(this, 35, 23);
		ChestOutTop.addBox(0F, 0.5F, 0.5F, 7, 5, 4);
		ChestOutTop.setRotationPoint(-3.5F, -7.9F, -2.3F);
		ChestOutTop.setTextureSize(64, 32);
		ChestOutTop.mirror = true;
		setRotation(ChestOutTop, 0F, 0F, 0F);

		LegLeftMid = new ModelRenderer(this, 9, 12);
		LegLeftMid.addBox(-1.5F, 1F, -7.5F, 3, 8, 3);
		LegLeftMid.setRotationPoint(7.8F, 9F, 2F);
		LegLeftMid.setTextureSize(64, 32);
		LegLeftMid.mirror = true;
		setRotation(LegLeftMid, 0.6981317F, 0F, 0F);

		TailMid = new ModelRenderer(this, 41, 2);
		TailMid.addBox(-2F, -2F, 6F, 4, 4, 6);
		TailMid.setRotationPoint(0F, 9.5F, 5F);
		TailMid.setTextureSize(64, 32);
		TailMid.mirror = true;
		setRotation(TailMid, 0F, 0F, 0F);

		FootLeftClaws = new ModelRenderer(this, 15, 23);
		FootLeftClaws.addBox(-1F, 13.8F, -7.8F, 2, 1, 2);
		FootLeftClaws.setRotationPoint(7.8F, 9F, 2F);
		FootLeftClaws.setTextureSize(64, 32);
		FootLeftClaws.mirror = true;
		setRotation(FootLeftClaws, 0F, 0F, 0F);

		LegLeftTop = new ModelRenderer(this, 9, 11);
		LegLeftTop.addBox(-2F, 0F, -2F, 4, 8, 4);
		LegLeftTop.setRotationPoint(7.8F, 9F, 2F);
		LegLeftTop.setTextureSize(64, 32);
		LegLeftTop.mirror = true;
		setRotation(LegLeftTop, -0.8726646F, 0F, 0F);

		LegLeftLow = new ModelRenderer(this, 27, 13);
		LegLeftLow.addBox(-1F, 7F, 5F, 2, 6, 2);
		LegLeftLow.setRotationPoint(7.8F, 9F, 2F);
		LegLeftLow.setTextureSize(64, 32);
		LegLeftLow.mirror = true;
		setRotation(LegLeftLow, -0.6108652F, 0F, 0F);

		FootLeft = new ModelRenderer(this, 43, 5);
		FootLeft.addBox(-1.5F, 13F, -6.5F, 3, 2, 5);
		FootLeft.setRotationPoint(7.8F, 9F, 2F);
		FootLeft.setTextureSize(64, 32);
		FootLeft.mirror = true;
		setRotation(FootLeft, 0F, 0F, 0F);

		ArmRightLow = new ModelRenderer(this, 13, 13);
		ArmRightLow.addBox(-1.5F, 4.4F, -14F, 2, 2, 8);
		ArmRightLow.setRotationPoint(-5.8F, -6F, 0F);
		ArmRightLow.setTextureSize(64, 32);
		ArmRightLow.mirror = true;
		setRotation(ArmRightLow, 0F, 0F, 0F);

		LegRightMid = new ModelRenderer(this, 20, 12);
		LegRightMid.addBox(-1.5F, 1F, -7.5F, 3, 8, 3);
		LegRightMid.setRotationPoint(-7.8F, 9F, 2F);
		LegRightMid.setTextureSize(64, 32);
		LegRightMid.mirror = true;
		setRotation(LegRightMid, 0.6981317F, 0F, 0F);

		FootRightClaws = new ModelRenderer(this, 15, 23);
		FootRightClaws.addBox(-1F, 13.8F, -7.8F, 2, 1, 2);
		FootRightClaws.setRotationPoint(-7.8F, 9F, 2F);
		FootRightClaws.setTextureSize(64, 32);
		FootRightClaws.mirror = true;
		setRotation(FootRightClaws, 0F, 0F, 0F);

		FootRight = new ModelRenderer(this, 43, 5);
		FootRight.addBox(-1.5F, 13F, -6.5F, 3, 2, 5);
		FootRight.setRotationPoint(-7.8F, 9F, 2F);
		FootRight.setTextureSize(64, 32);
		FootRight.mirror = true;
		setRotation(FootRight, 0F, 0F, 0F);

		LegRightLow = new ModelRenderer(this, 22, 13);
		LegRightLow.addBox(-1F, 7F, 5F, 2, 6, 2);
		LegRightLow.setRotationPoint(-7.8F, 9F, 2F);
		LegRightLow.setTextureSize(64, 32);
		LegRightLow.mirror = true;
		setRotation(LegRightLow, -0.6108652F, 0F, 0F);

		HipLeft = new ModelRenderer(this, 0, 0);
		HipLeft.addBox(0F, 0F, 0F, 5, 5, 6);
		HipLeft.setRotationPoint(5F, 6F, 0F);
		HipLeft.setTextureSize(64, 32);
		HipLeft.mirror = true;
		setRotation(HipLeft, 0F, 0F, 0F);

		ArmLeftGuard = new ModelRenderer(this, 0, 26);
		ArmLeftGuard.addBox(0F, 0F, 0F, 10, 3, 3);
		ArmLeftGuard.setRotationPoint(0.5F, -3.9F, -10.8F);
		ArmLeftGuard.setTextureSize(64, 32);
		ArmLeftGuard.mirror = true;
		setRotation(ArmLeftGuard, -0.0174533F, -0.2617994F, -0.122173F);

		TailEnd = new ModelRenderer(this, 40, 0);
		TailEnd.addBox(-1.5F, -1.5F, 12F, 3, 3, 8);
		TailEnd.setRotationPoint(0F, 9.5F, 5F);
		TailEnd.setTextureSize(64, 32);
		TailEnd.mirror = true;
		setRotation(TailEnd, 0F, 0F, 0F);

		TailHelper = new ModelRenderer(this, 39, 0);
		TailHelper.addBox(-2F, -4.5F, -6F, 4, 3, 8);
		TailHelper.setRotationPoint(0F, 9.5F, 5F);
		TailHelper.setTextureSize(64, 32);
		TailHelper.mirror = true;
		setRotation(TailHelper, -0.7330383F, 0F, 0F);

		Pelvis = new ModelRenderer(this, 0, 0);
		Pelvis.addBox(0F, 0.5F, 0.5F, 14, 6, 5);
		Pelvis.setRotationPoint(-7F, 6F, 0F);
		Pelvis.setTextureSize(64, 32);
		Pelvis.mirror = true;
		setRotation(Pelvis, 0F, 0F, 0F);

		StomachLow = new ModelRenderer(this, 32, 21);
		StomachLow.addBox(0F, 0.5F, 0.5F, 10, 5, 5);
		StomachLow.setRotationPoint(-5F, 8F, -2F);
		StomachLow.setTextureSize(64, 32);
		StomachLow.mirror = true;
		setRotation(StomachLow, 0F, 0F, 0F);

		SpineLow = new ModelRenderer(this, 0, 1);
		SpineLow.addBox(-4F, -11F, 0F, 8, 11, 3);
		SpineLow.setRotationPoint(0F, 7F, 2F);
		SpineLow.setTextureSize(64, 32);
		SpineLow.mirror = true;
		setRotation(SpineLow, 0.1745329F, 0F, 0F);

		SpineMid = new ModelRenderer(this, 0, 6);
		SpineMid.addBox(-4F, -5F, 0F, 8, 5, 3);
		SpineMid.setRotationPoint(0F, -4F, 0F);
		SpineMid.setTextureSize(64, 32);
		SpineMid.mirror = true;
		setRotation(SpineMid, 0.0698132F, 0F, 0F);

		StomachTop = new ModelRenderer(this, 30, 20);
		StomachTop.addBox(0F, 0.5F, 0.5F, 11, 6, 6);
		StomachTop.setRotationPoint(-5.5F, 2F, -3F);
		StomachTop.setTextureSize(64, 32);
		StomachTop.mirror = true;
		setRotation(StomachTop, 0F, 0F, 0F);

		TailBase = new ModelRenderer(this, 40, 2);
		TailBase.addBox(-2.5F, -2.5F, 0F, 5, 5, 6);
		TailBase.setRotationPoint(0F, 9.5F, 5F);
		TailBase.setTextureSize(64, 32);
		TailBase.mirror = true;
		setRotation(TailBase, 0F, 0F, 0F);

		HipRight = new ModelRenderer(this, 0, 0);
		HipRight.addBox(0F, 0F, 0F, 5, 5, 6);
		HipRight.setRotationPoint(-10F, 6F, 0F);
		HipRight.setTextureSize(64, 32);
		HipRight.mirror = true;
		setRotation(HipRight, 0F, 0F, 0F);

		ShoulderRight = new ModelRenderer(this, 0, 0);
		ShoulderRight.addBox(0F, 0F, 0F, 4, 4, 5);
		ShoulderRight.setRotationPoint(-8F, -9F, -1.6F);
		ShoulderRight.setTextureSize(64, 32);
		ShoulderRight.mirror = true;
		setRotation(ShoulderRight, 0F, 0F, 0F);

		LegRightTop = new ModelRenderer(this, 17, 11);
		LegRightTop.addBox(-2F, 0F, -2F, 4, 8, 4);
		LegRightTop.setRotationPoint(-7.8F, 9F, 2F);
		LegRightTop.setTextureSize(64, 32);
		LegRightTop.mirror = true;
		setRotation(LegRightTop, -0.8726646F, 0F, 0F);

		ArmLeftTop = new ModelRenderer(this, 10, 11);
		ArmLeftTop.addBox(-2F, 0F, -2F, 3, 9, 3);
		ArmLeftTop.setRotationPoint(6.8F, -6F, 0F);
		ArmLeftTop.setTextureSize(64, 32);
		ArmLeftTop.mirror = true;
		setRotation(ArmLeftTop, -1.308997F, -0.8726646F, 0.2617994F);

		ArmRightTop = new ModelRenderer(this, 20, 11);
		ArmRightTop.addBox(-2F, 0F, -2F, 3, 9, 3);
		ArmRightTop.setRotationPoint(-5.8F, -6F, 0F);
		ArmRightTop.setTextureSize(64, 32);
		ArmRightTop.mirror = true;
		setRotation(ArmRightTop, -0.8726646F, 0F, 0F);

		ShoulderLeft = new ModelRenderer(this, 0, 0);
		ShoulderLeft.addBox(0F, 0F, 0F, 4, 4, 5);
		ShoulderLeft.setRotationPoint(4F, -9F, -1.6F);
		ShoulderLeft.setTextureSize(64, 32);
		ShoulderLeft.mirror = true;
		setRotation(ShoulderLeft, 0F, 0F, 0F);

		ArmLeft = new ModelRenderer(this, 16, 13);
		ArmLeft.addBox(0F, 0F, 1F, 5, 2, 2);
		ArmLeft.setRotationPoint(9F, -4.3F, -9.5F);
		ArmLeft.setTextureSize(64, 32);
		ArmLeft.mirror = true;
		setRotation(ArmLeft, -0.0174533F, -0.6981317F, -0.122173F);

		Neck = new ModelRenderer(this, 24, 15);
		Neck.addBox(-1F, -4F, -2F, 2, 5, 2);
		Neck.setRotationPoint(0F, -8F, 0F);
		Neck.setTextureSize(64, 32);
		Neck.mirror = true;
		setRotation(Neck, 0.6457718F, 0F, 0F);

		Head5 = new ModelRenderer(this, 33, 24);
		Head5.addBox(-2F, -0.7F, -6.8F, 4, 2, 5);
		Head5.setRotationPoint(0F, -9.6F, -2F);
		Head5.setTextureSize(64, 32);
		Head5.mirror = true;
		setRotation(Head5, 0.1919862F, 0F, 0F);

		Head1 = new ModelRenderer(this, 45, 7);
		Head1.addBox(-2F, -2F, -2F, 4, 3, 2);
		Head1.setRotationPoint(0F, -9.6F, -2F);
		Head1.setTextureSize(64, 32);
		Head1.mirror = true;
		setRotation(Head1, 0F, 0F, 0F);

		HeadEyeLeft = new ModelRenderer(this, 4, 20);
		HeadEyeLeft.addBox(2.1F, -8F, -4F, 1, 3, 3);
		HeadEyeLeft.setRotationPoint(0F, -9.6F, -2F);
		HeadEyeLeft.setTextureSize(64, 32);
		HeadEyeLeft.mirror = true;
		setRotation(HeadEyeLeft, 0.7853982F, 0.1919862F, 0F);

		Head4 = new ModelRenderer(this, 47, 13);
		Head4.addBox(-1.5F, -4.8F, -8F, 3, 2, 5);
		Head4.setRotationPoint(0F, -9.6F, -2F);
		Head4.setTextureSize(64, 32);
		Head4.mirror = true;
		setRotation(Head4, 0.1919862F, 0F, 0F);

		HeadEyeRight = new ModelRenderer(this, 0, 20);
		HeadEyeRight.addBox(-3.1F, 1F, -8F, 1, 3, 3);
		HeadEyeRight.setRotationPoint(0F, -9.6F, -2F);
		HeadEyeRight.setTextureSize(64, 32);
		HeadEyeRight.mirror = true;
		setRotation(HeadEyeRight, -0.7853982F, -0.1919862F, 0F);

		Head2 = new ModelRenderer(this, 38, 1);
		Head2.addBox(-3F, -3F, -9F, 6, 4, 7);
		Head2.setRotationPoint(0F, -9.6F, -2F);
		Head2.setTextureSize(64, 32);
		Head2.mirror = true;
		setRotation(Head2, 0F, 0F, 0F);

		Head7 = new ModelRenderer(this, 46, 13);
		Head7.addBox(-2F, -5F, -12F, 4, 1, 5);
		Head7.setRotationPoint(0F, -9.6F, -2F);
		Head7.setTextureSize(64, 32);
		Head7.mirror = true;
		setRotation(Head7, 0.3490659F, 0F, 0F);

		Head6 = new ModelRenderer(this, 36, 24);
		Head6.addBox(-1.5F, 1F, -13.5F, 3, 1, 7);
		Head6.setRotationPoint(0F, -9.6F, -2F);
		Head6.setTextureSize(64, 32);
		Head6.mirror = true;
		setRotation(Head6, 0F, 0F, 0F);

		ChestTop = new ModelRenderer(this, 0, 0);
		ChestTop.addBox(0F, 0.5F, 0.5F, 9, 5, 4);
		ChestTop.setRotationPoint(-4.5F, -8F, -2F);
		ChestTop.setTextureSize(64, 32);
		ChestTop.mirror = true;
		setRotation(ChestTop, 0F, 0F, 0F);

		ChestLow = new ModelRenderer(this, 0, 1);
		ChestLow.addBox(0F, 0.5F, 0.5F, 9, 5, 4);
		ChestLow.setRotationPoint(-4.5F, -3F, -2F);
		ChestLow.setTextureSize(64, 32);
		ChestLow.mirror = true;
		setRotation(ChestLow, 0F, 0F, 0F);

		ChestOutLow = new ModelRenderer(this, 36, 23);
		ChestOutLow.addBox(0F, 0.5F, 0.5F, 8, 5, 4);
		ChestOutLow.setRotationPoint(-4F, -3F, -2.4F);
		ChestOutLow.setTextureSize(64, 32);
		ChestOutLow.mirror = true;
		setRotation(ChestOutLow, 0F, 0F, 0F);

		Head3 = new ModelRenderer(this, 46, 13);
		Head3.addBox(-2F, -1F, -14F, 4, 2, 5);
		Head3.setRotationPoint(0F, -9.6F, -2F);
		Head3.setTextureSize(64, 32);
		Head3.mirror = true;
		setRotation(Head3, 0F, 0F, 0F);

		SwordBladePoint = new ModelRenderer(this, 39, 17);
		SwordBladePoint.addBox(-1F, 12.5F, -10F, 1, 2, 1);
		SwordBladePoint.setRotationPoint(-5.8F, -6F, 0F);
		SwordBladePoint.setTextureSize(64, 32);
		SwordBladePoint.mirror = true;
		setRotation(SwordBladePoint, -1.570796F, 0F, 0F);

		SwordHilt = new ModelRenderer(this, 34, 0);
		SwordHilt.addBox(-1F, 4F, -13.5F, 1, 4, 1);
		SwordHilt.setRotationPoint(-5.8F, -6F, 0F);
		SwordHilt.setTextureSize(64, 32);
		SwordHilt.mirror = true;
		setRotation(SwordHilt, 0F, 0F, 0F);

		SwordCrossbar = new ModelRenderer(this, 34, 0);
		SwordCrossbar.addBox(-1F, 11F, 3F, 1, 4, 1);
		SwordCrossbar.setRotationPoint(-5.8F, -6F, 0F);
		SwordCrossbar.setTextureSize(64, 32);
		SwordCrossbar.mirror = true;
		setRotation(SwordCrossbar, -1.570796F, 0F, 0F);

		SwordBladeBase = new ModelRenderer(this, 36, 13);
		SwordBladeBase.addBox(-1F, 11.5F, -1F, 1, 3, 4);
		SwordBladeBase.setRotationPoint(-5.8F, -6F, 0F);
		SwordBladeBase.setTextureSize(64, 32);
		SwordBladeBase.mirror = true;
		setRotation(SwordBladeBase, -1.570796F, 0F, 0F);

		SwordBladeMid = new ModelRenderer(this, 36, 13);
		SwordBladeMid.addBox(-1F, 11.5F, -5F, 1, 3, 4);
		SwordBladeMid.setRotationPoint(-5.8F, -6F, 0F);
		SwordBladeMid.setTextureSize(64, 32);
		SwordBladeMid.mirror = true;
		setRotation(SwordBladeMid, -1.570796F, 0F, 0F);

		SwordBladeTop = new ModelRenderer(this, 36, 13);
		SwordBladeTop.addBox(-1F, 11.5F, -9F, 1, 3, 4);
		SwordBladeTop.setRotationPoint(-5.8F, -6F, 0F);
		SwordBladeTop.setTextureSize(64, 32);
		SwordBladeTop.mirror = true;
		setRotation(SwordBladeTop, -1.570796F, 0F, 0F);
	}


	@Override
	public void render(Entity entity, float f, float f1,
	                   float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		ChestOutTop.render(f5);
		LegLeftMid.render(f5);
		TailMid.render(f5);
		FootLeftClaws.render(f5);
		LegLeftTop.render(f5);
		LegLeftLow.render(f5);
		FootLeft.render(f5);
		ArmRightLow.render(f5);
		LegRightMid.render(f5);
		FootRightClaws.render(f5);
		FootRight.render(f5);
		LegRightLow.render(f5);
		HipLeft.render(f5);
		ArmLeftGuard.render(f5);
		TailEnd.render(f5);
		TailHelper.render(f5);
		Pelvis.render(f5);
		StomachLow.render(f5);
		SpineLow.render(f5);
		SpineMid.render(f5);
		StomachTop.render(f5);
		TailBase.render(f5);
		HipRight.render(f5);
		ShoulderRight.render(f5);
		LegRightTop.render(f5);
		ArmLeftTop.render(f5);
		ArmRightTop.render(f5);
		ShoulderLeft.render(f5);
		ArmLeft.render(f5);
		Neck.render(f5);
		Head5.render(f5);
		Head1.render(f5);
		HeadEyeLeft.render(f5);
		Head4.render(f5);
		HeadEyeRight.render(f5);
		Head2.render(f5);
		Head7.render(f5);
		Head6.render(f5);
		ChestTop.render(f5);
		ChestLow.render(f5);
		ChestOutLow.render(f5);
		Head3.render(f5);

		SwordBladePoint.render(f5);
		SwordHilt.render(f5);
		SwordCrossbar.render(f5);
		SwordBladeBase.render(f5);
		SwordBladeMid.render(f5);
		SwordBladeTop.render(f5);
	}


	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}


	@Override
	public void setRotationAngles(float f, float f1,
	                              float f2, float f3, float f4, float f5, Entity ent) {
		// let's do this!
		// LEEEEEEEEEEROOOOOOOOOOOOOOOOOOOY JENKINS!

		float var8 = 0.1F * (float) (ent.getEntityId() % 10);

		TailBase.rotateAngleY = MathHelper.cos((float) ent.ticksExisted * var8) * 16.0F * (float) Math.PI / 180.0F;
		TailMid.rotateAngleY = MathHelper.cos((float) ent.ticksExisted * var8) * 24.0F * (float) Math.PI / 180.0F;
		TailEnd.rotateAngleY = MathHelper.cos((float) ent.ticksExisted * var8) * 32.0F * (float) Math.PI / 180.0F;
		TailHelper.rotateAngleY = MathHelper.cos((float) ent.ticksExisted * var8) * 16.0F * (float) Math.PI / 180.0F;

		// H'okay, let's do legs!

		LegLeftLow.rotateAngleX = -0.6108652F + MathHelper.cos(f * 0.5662F) * 1.4F * f1;
		LegLeftMid.rotateAngleX = 0.6981317F + MathHelper.cos(f * 0.5662F) * 1.4F * f1;
		LegLeftTop.rotateAngleX = -0.8726646F + MathHelper.cos(f * 0.5662F) * 1.4F * f1;
		FootLeft.rotateAngleX = MathHelper.cos(f * 0.5662F) * 1.4F * f1;
		FootLeftClaws.rotateAngleX = MathHelper.cos(f * 0.5662F) * 1.4F * f1;

		LegRightLow.rotateAngleX = -0.6108652F + MathHelper.cos(f * 0.5662F + (float) Math.PI) * 1.4F * f1;
		LegRightMid.rotateAngleX = 0.6981317F + MathHelper.cos(f * 0.5662F + (float) Math.PI) * 1.4F * f1;
		LegRightTop.rotateAngleX = -0.8726646F + MathHelper.cos(f * 0.5662F + (float) Math.PI) * 1.4F * f1;
		FootRight.rotateAngleX = MathHelper.cos(f * 0.5662F + (float) Math.PI) * 1.4F * f1;
		FootRightClaws.rotateAngleX = MathHelper.cos(f * 0.5662F + (float) Math.PI) * 1.4F * f1;

		// And head!

		Head1.rotateAngleX = f4 / 57.29578F;
		Head1.rotateAngleY = f3 / 57.29578F;
		Head2.rotateAngleX = f4 / 57.29578F;
		Head2.rotateAngleY = f3 / 57.29578F;
		Head3.rotateAngleX = f4 / 57.29578F;
		Head3.rotateAngleY = f3 / 57.29578F;
		Head4.rotateAngleX = 0.1919862F + f4 / 57.29578F;
		Head4.rotateAngleY = f3 / 57.29578F;
		Head5.rotateAngleX = 0.1919862F + f4 / 57.29578F;
		Head5.rotateAngleY = f3 / 57.29578F;
		Head6.rotateAngleX = f4 / 57.29578F;
		Head6.rotateAngleY = f3 / 57.29578F;
		Head7.rotateAngleX = 0.3490659F + f4 / 57.29578F;
		Head7.rotateAngleY = f3 / 57.29578F;

		HeadEyeLeft.rotateAngleX = 0.7853982F + f4 / 57.29578F;
		HeadEyeLeft.rotateAngleY = 0.1919862F + f3 / 57.29578F;
		HeadEyeRight.rotateAngleX = -0.7853982F + f4 / 57.29578F;
		HeadEyeRight.rotateAngleY = -0.1919862F + f3 / 57.29578F;

		EntityLizalfos foo = (EntityLizalfos) ent;

		if (foo.getAttackTarget() != null) {
			SwordBladePoint.rotateAngleX = -1.570796F + f4 / 57.29578F;
			SwordHilt.rotateAngleX = f4 / 57.29578F;
			SwordCrossbar.rotateAngleX = -1.570796F + f4 / 57.29578F;
			SwordBladeBase.rotateAngleX = -1.570796F + f4 / 57.29578F;
			SwordBladeMid.rotateAngleX = -1.570796F + f4 / 57.29578F;
			SwordBladeTop.rotateAngleX = -1.570796F + f4 / 57.29578F;
			ArmRightTop.rotateAngleX = -0.8726646F + f4 / 57.29578F;
			ArmRightLow.rotateAngleX = f4 / 57.29578F;
		}
	}

}

