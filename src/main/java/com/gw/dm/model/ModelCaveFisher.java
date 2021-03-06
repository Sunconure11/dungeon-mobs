package com.gw.dm.model;

import com.gw.dm.entity.EntityCaveFisher;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelCaveFisher extends ModelBase {

	ModelRenderer Nose;
	ModelRenderer EyeLeft;
	ModelRenderer HeadMid;
	ModelRenderer HeadEnd;
	ModelRenderer TailTuft;
	ModelRenderer EyeRight;
	ModelRenderer BodyTopLeft4;
	ModelRenderer BodyTopRight4;
	ModelRenderer BodyTopLeft1;
	ModelRenderer BodyTopRight1;
	ModelRenderer BodyTopRight2;
	ModelRenderer BodyTopLeft2;
	ModelRenderer BodyTopRight3;
	ModelRenderer BodyTopLeft3;
	ModelRenderer HeadBase;
	ModelRenderer TailBase;
	ModelRenderer BodyLow2;
	ModelRenderer BodyLow1;
	ModelRenderer Spine5;
	ModelRenderer Spine1;
	ModelRenderer Spine2;
	ModelRenderer Spine3;
	ModelRenderer Spine4;
	ModelRenderer RightArmSeg4;
	ModelRenderer LeftArmSeg1;
	ModelRenderer LeftArmSeg3;
	ModelRenderer RightArmSeg2;
	ModelRenderer RightArmSeg1;
	ModelRenderer LeftArmSeg5;
	ModelRenderer LeftArmSeg2;
	ModelRenderer LeftClawTop;
	ModelRenderer RightArmSeg3;
	ModelRenderer RightArmSeg5;
	ModelRenderer LeftArmSeg4;
	ModelRenderer LeftClawBase;
	ModelRenderer RightClawBase;
	ModelRenderer LeftClawLow;
	ModelRenderer RightClawTop;
	ModelRenderer RightClawLow;
	ModelRenderer LBLeg1;
	ModelRenderer LBLeg3;
	ModelRenderer RBLeg1;
	ModelRenderer RBLeg3;
	ModelRenderer LBLeg2;
	ModelRenderer RBLeg2;
	ModelRenderer LBLeg4;
	ModelRenderer RBLeg4;
	ModelRenderer RBLeg5;
	ModelRenderer LBLeg6;
	ModelRenderer RBLeg6;
	ModelRenderer LBLeg5;
	ModelRenderer RFLeg1;
	ModelRenderer RFLeg2;
	ModelRenderer RFLeg3;
	ModelRenderer RFLeg4;
	ModelRenderer RFLeg5;
	ModelRenderer RFLeg6;
	ModelRenderer RMLeg1;
	ModelRenderer RMLeg2;
	ModelRenderer RMLeg3;
	ModelRenderer RMLeg4;
	ModelRenderer RMLeg5;
	ModelRenderer RMLeg6;
	ModelRenderer LFLeg1;
	ModelRenderer LFLeg2;
	ModelRenderer LFLeg3;
	ModelRenderer LFLeg4;
	ModelRenderer LFLeg5;
	ModelRenderer LFLeg6;
	ModelRenderer LMLeg1;
	ModelRenderer LMLeg2;
	ModelRenderer LMLeg4;
	ModelRenderer LMLeg3;
	ModelRenderer LMLeg5;
	ModelRenderer LMLeg6;

	public ModelCaveFisher() {
		textureWidth = 64;
		textureHeight = 32;

		Nose = new ModelRenderer(this, 0, 0);
		Nose.addBox(-0.5F, -0.5F, -12F, 1, 1, 6);
		Nose.setRotationPoint(0F, 19F, -4F);
		Nose.setTextureSize(64, 32);
		Nose.mirror = true;
		setRotation(Nose, 0F, 0F, 0F);

		EyeLeft = new ModelRenderer(this, 0, 28);
		EyeLeft.addBox(0.5F, -2.5F, -2.5F, 3, 2, 2);
		EyeLeft.setRotationPoint(0F, 19F, -4F);
		EyeLeft.setTextureSize(64, 32);
		EyeLeft.mirror = true;
		setRotation(EyeLeft, 0F, 0F, 0F);

		HeadMid = new ModelRenderer(this, 0, 0);
		HeadMid.addBox(-2.5F, -1.5F, -5F, 5, 3, 2);
		HeadMid.setRotationPoint(0F, 19F, -4F);
		HeadMid.setTextureSize(64, 32);
		HeadMid.mirror = true;
		setRotation(HeadMid, 0F, 0F, 0F);

		HeadEnd = new ModelRenderer(this, 0, 0);
		HeadEnd.addBox(-2F, -1F, -6F, 4, 2, 1);
		HeadEnd.setRotationPoint(0F, 19F, -4F);
		HeadEnd.setTextureSize(64, 32);
		HeadEnd.mirror = true;
		setRotation(HeadEnd, 0F, 0F, 0F);

		TailTuft = new ModelRenderer(this, 0, 23);
		TailTuft.addBox(-2F, -1F, 3F, 4, 1, 2);
		TailTuft.setRotationPoint(0F, 19F, 10F);
		TailTuft.setTextureSize(64, 32);
		TailTuft.mirror = true;
		setRotation(TailTuft, 0F, 0F, 0F);

		EyeRight = new ModelRenderer(this, 0, 28);
		EyeRight.addBox(-3.5F, -2.5F, -2.5F, 3, 2, 2);
		EyeRight.setRotationPoint(0F, 19F, -4F);
		EyeRight.setTextureSize(64, 32);
		EyeRight.mirror = true;
		setRotation(EyeRight, 0F, 0F, 0F);

		BodyTopLeft4 = new ModelRenderer(this, 0, 0);
		BodyTopLeft4.addBox(0F, 0F, 0F, 6, 3, 4);
		BodyTopLeft4.setRotationPoint(0F, 16.2F, 7F);
		BodyTopLeft4.setTextureSize(64, 32);
		BodyTopLeft4.mirror = true;
		setRotation(BodyTopLeft4, 0.1047198F, 0.1047198F, 0.1047198F);

		BodyTopRight4 = new ModelRenderer(this, 0, 0);
		BodyTopRight4.addBox(-5F, 0F, 0F, 6, 3, 4);
		BodyTopRight4.setRotationPoint(-1F, 16.2F, 7F);
		BodyTopRight4.setTextureSize(64, 32);
		BodyTopRight4.mirror = true;
		setRotation(BodyTopRight4, 0.1047198F, -0.1047198F, -0.1047198F);

		BodyTopLeft1 = new ModelRenderer(this, 0, 0);
		BodyTopLeft1.addBox(0F, 0F, 0F, 5, 3, 4);
		BodyTopLeft1.setRotationPoint(0F, 16F, -4F);
		BodyTopLeft1.setTextureSize(64, 32);
		BodyTopLeft1.mirror = true;
		setRotation(BodyTopLeft1, 0.1745329F, 0.1745329F, 0.1047198F);

		BodyTopRight1 = new ModelRenderer(this, 0, 0);
		BodyTopRight1.addBox(-5F, 0F, 0F, 5, 3, 4);
		BodyTopRight1.setRotationPoint(0F, 16F, -4F);
		BodyTopRight1.setTextureSize(64, 32);
		BodyTopRight1.mirror = true;
		setRotation(BodyTopRight1, 0.1745329F, -0.1745329F, -0.1047198F);

		BodyTopRight2 = new ModelRenderer(this, 0, 0);
		BodyTopRight2.addBox(-5F, 0F, 0F, 7, 3, 4);
		BodyTopRight2.setRotationPoint(-1F, 16F, -1F);
		BodyTopRight2.setTextureSize(64, 32);
		BodyTopRight2.mirror = true;
		setRotation(BodyTopRight2, 0.2094395F, -0.1745329F, -0.1047198F);

		BodyTopLeft2 = new ModelRenderer(this, 0, 0);
		BodyTopLeft2.addBox(-1F, 0F, 0F, 7, 3, 4);
		BodyTopLeft2.setRotationPoint(0F, 16F, -1F);
		BodyTopLeft2.setTextureSize(64, 32);
		BodyTopLeft2.mirror = true;
		setRotation(BodyTopLeft2, 0.2094395F, 0.1745329F, 0.1047198F);

		BodyTopRight3 = new ModelRenderer(this, 0, 0);
		BodyTopRight3.addBox(-5F, 0F, 0F, 6, 3, 4);
		BodyTopRight3.setRotationPoint(-1F, 16F, 3F);
		BodyTopRight3.setTextureSize(64, 32);
		BodyTopRight3.mirror = true;
		setRotation(BodyTopRight3, 0.1396263F, -0.1396263F, -0.1047198F);

		BodyTopLeft3 = new ModelRenderer(this, 0, 0);
		BodyTopLeft3.addBox(0F, 0F, 0F, 6, 3, 4);
		BodyTopLeft3.setRotationPoint(0F, 16F, 3F);
		BodyTopLeft3.setTextureSize(64, 32);
		BodyTopLeft3.mirror = true;
		setRotation(BodyTopLeft3, 0.1396263F, 0.1396263F, 0.1047198F);

		HeadBase = new ModelRenderer(this, 0, 0);
		HeadBase.addBox(-3F, -2F, -3F, 6, 4, 3);
		HeadBase.setRotationPoint(0F, 19F, -4F);
		HeadBase.setTextureSize(64, 32);
		HeadBase.mirror = true;
		setRotation(HeadBase, 0F, 0F, 0F);

		TailBase = new ModelRenderer(this, 0, 0);
		TailBase.addBox(-3F, -2F, 0F, 6, 3, 3);
		TailBase.setRotationPoint(0F, 19F, 10F);
		TailBase.setTextureSize(64, 32);
		TailBase.mirror = true;
		setRotation(TailBase, 0F, 0F, 0F);

		BodyLow2 = new ModelRenderer(this, 34, 0);
		BodyLow2.addBox(0F, 0F, 0F, 8, 2, 7);
		BodyLow2.setRotationPoint(-4F, 18.3F, 3F);
		BodyLow2.setTextureSize(64, 32);
		BodyLow2.mirror = true;
		setRotation(BodyLow2, 0F, 0F, 0F);

		BodyLow1 = new ModelRenderer(this, 34, 0);
		BodyLow1.addBox(0F, 0F, 0F, 8, 2, 7);
		BodyLow1.setRotationPoint(-4F, 18.7F, -4F);
		BodyLow1.setTextureSize(64, 32);
		BodyLow1.mirror = true;
		setRotation(BodyLow1, 0F, 0F, 0F);

		Spine5 = new ModelRenderer(this, 0, 0);
		Spine5.addBox(-0.5F, 0F, 0F, 1, 1, 4);
		Spine5.setRotationPoint(0F, 16F, 8.6F);
		Spine5.setTextureSize(64, 32);
		Spine5.mirror = true;
		setRotation(Spine5, 0F, 0F, 0F);

		Spine1 = new ModelRenderer(this, 0, 0);
		Spine1.addBox(-0.5F, 0F, 0F, 1, 1, 4);
		Spine1.setRotationPoint(0F, 16F, -4.2F);
		Spine1.setTextureSize(64, 32);
		Spine1.mirror = true;
		setRotation(Spine1, 0.2443461F, 0F, 0F);

		Spine2 = new ModelRenderer(this, 0, 0);
		Spine2.addBox(-0.5F, 0F, 0F, 1, 1, 5);
		Spine2.setRotationPoint(0F, 16F, -1.2F);
		Spine2.setTextureSize(64, 32);
		Spine2.mirror = true;
		setRotation(Spine2, 0.3141593F, 0F, 0F);

		Spine3 = new ModelRenderer(this, 0, 0);
		Spine3.addBox(-0.5F, 0F, 0F, 1, 1, 6);
		Spine3.setRotationPoint(0F, 16F, 1.8F);
		Spine3.setTextureSize(64, 32);
		Spine3.mirror = true;
		setRotation(Spine3, 0.2792527F, 0F, 0F);

		Spine4 = new ModelRenderer(this, 0, 0);
		Spine4.addBox(-0.5F, 0F, 0F, 1, 1, 8);
		Spine4.setRotationPoint(0F, 16F, 3.8F);
		Spine4.setTextureSize(64, 32);
		Spine4.mirror = true;
		setRotation(Spine4, 0.1745329F, 0F, 0F);

		RightArmSeg4 = new ModelRenderer(this, 0, 0);
		RightArmSeg4.addBox(-3.2F, -1F, -10.5F, 2, 2, 4);
		RightArmSeg4.setRotationPoint(-4.7F, 17.5F, -3F);
		RightArmSeg4.setTextureSize(64, 32);
		RightArmSeg4.mirror = true;
		setRotation(RightArmSeg4, 0F, 0.0872665F, 0F);

		LeftArmSeg1 = new ModelRenderer(this, 0, 13);
		LeftArmSeg1.addBox(-0.5F, -0.5F, -4F, 1, 1, 4);
		LeftArmSeg1.setRotationPoint(4.7F, 17.5F, -3F);
		LeftArmSeg1.setTextureSize(64, 32);
		LeftArmSeg1.mirror = true;
		setRotation(LeftArmSeg1, 0F, -0.5235988F, 0F);

		LeftArmSeg3 = new ModelRenderer(this, 0, 13);
		LeftArmSeg3.addBox(1F, -0.5F, -8F, 1, 1, 3);
		LeftArmSeg3.setRotationPoint(4.7F, 17.5F, -3F);
		LeftArmSeg3.setTextureSize(64, 32);
		LeftArmSeg3.mirror = true;
		setRotation(LeftArmSeg3, 0F, -0.1745329F, 0F);

		RightArmSeg2 = new ModelRenderer(this, 0, 0);
		RightArmSeg2.addBox(-1.5F, -1F, -6F, 2, 2, 4);
		RightArmSeg2.setRotationPoint(-4.7F, 17.5F, -3F);
		RightArmSeg2.setTextureSize(64, 32);
		RightArmSeg2.mirror = true;
		setRotation(RightArmSeg2, 0F, 0.3490659F, 0F);

		RightArmSeg1 = new ModelRenderer(this, 0, 13);
		RightArmSeg1.addBox(-0.5F, -0.5F, -4F, 1, 1, 4);
		RightArmSeg1.setRotationPoint(-4.7F, 17.5F, -3F);
		RightArmSeg1.setTextureSize(64, 32);
		RightArmSeg1.mirror = true;
		setRotation(RightArmSeg1, 0F, 0.5235988F, 0F);

		LeftArmSeg5 = new ModelRenderer(this, 0, 13);
		LeftArmSeg5.addBox(2.4F, -0.5F, -12F, 1, 1, 3);
		LeftArmSeg5.setRotationPoint(4.7F, 17.5F, -3F);
		LeftArmSeg5.setTextureSize(64, 32);
		LeftArmSeg5.mirror = true;
		setRotation(LeftArmSeg5, 0F, 0F, 0F);

		LeftArmSeg2 = new ModelRenderer(this, 0, 0);
		LeftArmSeg2.addBox(-0.5F, -1F, -6F, 2, 2, 4);
		LeftArmSeg2.setRotationPoint(4.7F, 17.5F, -3F);
		LeftArmSeg2.setTextureSize(64, 32);
		LeftArmSeg2.mirror = true;
		setRotation(LeftArmSeg2, 0F, -0.3490659F, 0F);

		LeftClawTop = new ModelRenderer(this, 15, 15);
		LeftClawTop.addBox(1.8F, 4.7F, -15F, 2, 2, 5);
		LeftClawTop.setRotationPoint(4.7F, 17.5F, -3F);
		LeftClawTop.setTextureSize(64, 32);
		LeftClawTop.mirror = true;
		setRotation(LeftClawTop, -0.5410521F, 0F, 0F);

		RightArmSeg3 = new ModelRenderer(this, 0, 13);
		RightArmSeg3.addBox(-2F, -0.5F, -8F, 1, 1, 3);
		RightArmSeg3.setRotationPoint(-4.7F, 17.5F, -3F);
		RightArmSeg3.setTextureSize(64, 32);
		RightArmSeg3.mirror = true;
		setRotation(RightArmSeg3, 0F, 0.1745329F, 0F);

		RightArmSeg5 = new ModelRenderer(this, 0, 13);
		RightArmSeg5.addBox(-3.6F, -0.5F, -12F, 1, 1, 3);
		RightArmSeg5.setRotationPoint(-4.7F, 17.5F, -3F);
		RightArmSeg5.setTextureSize(64, 32);
		RightArmSeg5.mirror = true;
		setRotation(RightArmSeg5, 0F, 0F, 0F);

		LeftArmSeg4 = new ModelRenderer(this, 0, 0);
		LeftArmSeg4.addBox(1.1F, -1F, -10.5F, 2, 2, 4);
		LeftArmSeg4.setRotationPoint(4.7F, 17.5F, -3F);
		LeftArmSeg4.setTextureSize(64, 32);
		LeftArmSeg4.mirror = true;
		setRotation(LeftArmSeg4, 0F, -0.0872665F, 0F);

		LeftClawBase = new ModelRenderer(this, 0, 0);
		LeftClawBase.addBox(1.8F, -1F, -13F, 2, 2, 2);
		LeftClawBase.setRotationPoint(4.7F, 17.5F, -3F);
		LeftClawBase.setTextureSize(64, 32);
		LeftClawBase.mirror = true;
		setRotation(LeftClawBase, 0F, 0F, 0F);

		RightClawBase = new ModelRenderer(this, 0, 0);
		RightClawBase.addBox(-4.2F, -1F, -13F, 2, 2, 2);
		RightClawBase.setRotationPoint(-4.7F, 17.5F, -3F);
		RightClawBase.setTextureSize(64, 32);
		RightClawBase.mirror = true;
		setRotation(RightClawBase, 0F, 0F, 0F);

		LeftClawLow = new ModelRenderer(this, 25, 25);
		LeftClawLow.addBox(1.8F, -4.3F, -15F, 2, 1, 4);
		LeftClawLow.setRotationPoint(4.7F, 17.5F, -3F);
		LeftClawLow.setTextureSize(64, 32);
		LeftClawLow.mirror = true;
		setRotation(LeftClawLow, 0.3490659F, 0F, 0F);

		RightClawTop = new ModelRenderer(this, 15, 15);
		RightClawTop.addBox(-4.2F, 4.7F, -15F, 2, 2, 5);
		RightClawTop.setRotationPoint(-4.7F, 17.5F, -3F);
		RightClawTop.setTextureSize(64, 32);
		RightClawTop.mirror = true;
		setRotation(RightClawTop, -0.5410521F, 0F, 0F);

		RightClawLow = new ModelRenderer(this, 25, 25);
		RightClawLow.addBox(-4.2F, -4.3F, -15F, 2, 1, 4);
		RightClawLow.setRotationPoint(-4.7F, 17.5F, -3F);
		RightClawLow.setTextureSize(64, 32);
		RightClawLow.mirror = true;
		setRotation(RightClawLow, 0.3490659F, 0F, 0F);

		LBLeg1 = new ModelRenderer(this, 0, 13);
		LBLeg1.addBox(0.5F, -0.5F, -0.5F, 3, 1, 1);
		LBLeg1.setRotationPoint(5F, 18F, 8.5F);
		LBLeg1.setTextureSize(64, 32);
		LBLeg1.mirror = true;
		setRotation(LBLeg1, 0F, 0F, -0.4363323F);

		LBLeg3 = new ModelRenderer(this, 2, 0);
		LBLeg3.addBox(5.1F, -1.5F, -1F, 3, 1, 2);
		LBLeg3.setRotationPoint(5F, 18F, 8.5F);
		LBLeg3.setTextureSize(64, 32);
		LBLeg3.mirror = true;
		setRotation(LBLeg3, 0F, 0F, -0.5759587F);

		RBLeg1 = new ModelRenderer(this, 0, 13);
		RBLeg1.addBox(-3.5F, -0.5F, -0.5F, 3, 1, 1);
		RBLeg1.setRotationPoint(-5F, 18F, 8.5F);
		RBLeg1.setTextureSize(64, 32);
		RBLeg1.mirror = true;
		setRotation(RBLeg1, 0F, 0F, 0.4363323F);

		RBLeg3 = new ModelRenderer(this, 2, 0);
		RBLeg3.addBox(-8.1F, -1.5F, -1F, 3, 1, 2);
		RBLeg3.setRotationPoint(-5F, 18F, 8.5F);
		RBLeg3.setTextureSize(64, 32);
		RBLeg3.mirror = true;
		setRotation(RBLeg3, 0F, 0F, 0.5759587F);

		LBLeg2 = new ModelRenderer(this, 0, 0);
		LBLeg2.addBox(2.5F, 0.5F, -1F, 3, 2, 2);
		LBLeg2.setRotationPoint(5F, 18F, 8.5F);
		LBLeg2.setTextureSize(64, 32);
		LBLeg2.mirror = true;
		setRotation(LBLeg2, 0F, 0F, -0.9599311F);

		RBLeg2 = new ModelRenderer(this, 0, 0);
		RBLeg2.addBox(-5.5F, 0.5F, -1F, 3, 2, 2);
		RBLeg2.setRotationPoint(-5F, 18F, 8.5F);
		RBLeg2.setTextureSize(64, 32);
		RBLeg2.mirror = true;
		setRotation(RBLeg2, 0F, 0F, 0.9599311F);

		LBLeg4 = new ModelRenderer(this, 0, 13);
		LBLeg4.addBox(5F, -3F, -0.5F, 1, 3, 1);
		LBLeg4.setRotationPoint(5F, 18F, 8.5F);
		LBLeg4.setTextureSize(64, 32);
		LBLeg4.mirror = true;
		setRotation(LBLeg4, 0F, 0F, -0.2094395F);

		RBLeg4 = new ModelRenderer(this, 0, 13);
		RBLeg4.addBox(-6F, -3F, -0.5F, 1, 3, 1);
		RBLeg4.setRotationPoint(-5F, 18F, 8.5F);
		RBLeg4.setTextureSize(64, 32);
		RBLeg4.mirror = true;
		setRotation(RBLeg4, 0F, 0F, 0.2094395F);

		RBLeg5 = new ModelRenderer(this, 0, 0);
		RBLeg5.addBox(-6.4F, -1F, -1F, 2, 6, 2);
		RBLeg5.setRotationPoint(-5F, 18F, 8.5F);
		RBLeg5.setTextureSize(64, 32);
		RBLeg5.mirror = true;
		setRotation(RBLeg5, 0F, 0F, 0.1047198F);

		LBLeg6 = new ModelRenderer(this, 0, 13);
		LBLeg6.addBox(5.5F, 3F, -0.5F, 1, 3, 1);
		LBLeg6.setRotationPoint(5F, 18F, 8.5F);
		LBLeg6.setTextureSize(64, 32);
		LBLeg6.mirror = true;
		setRotation(LBLeg6, 0F, 0F, 0F);

		RBLeg6 = new ModelRenderer(this, 0, 13);
		RBLeg6.addBox(-6.5F, 3F, -0.5F, 1, 3, 1);
		RBLeg6.setRotationPoint(-5F, 18F, 8.5F);
		RBLeg6.setTextureSize(64, 32);
		RBLeg6.mirror = true;
		setRotation(RBLeg6, 0F, 0F, 0F);

		LBLeg5 = new ModelRenderer(this, 0, 0);
		LBLeg5.addBox(4.6F, -1F, -1F, 2, 6, 2);
		LBLeg5.setRotationPoint(5F, 18F, 8.5F);
		LBLeg5.setTextureSize(64, 32);
		LBLeg5.mirror = true;
		setRotation(LBLeg5, 0F, 0F, -0.1047198F);

		RFLeg1 = new ModelRenderer(this, 0, 13);
		RFLeg1.addBox(-3.5F, -0.5F, -0.5F, 3, 1, 1);
		RFLeg1.setRotationPoint(-5F, 18F, 0.5F);
		RFLeg1.setTextureSize(64, 32);
		RFLeg1.mirror = true;
		setRotation(RFLeg1, 0F, 0F, 0.4363323F);

		RFLeg2 = new ModelRenderer(this, 0, 0);
		RFLeg2.addBox(-5.5F, 0.5F, -1F, 3, 2, 2);
		RFLeg2.setRotationPoint(-5F, 18F, 0.5F);
		RFLeg2.setTextureSize(64, 32);
		RFLeg2.mirror = true;
		setRotation(RFLeg2, 0F, 0F, 0.9599311F);

		RFLeg3 = new ModelRenderer(this, 2, 0);
		RFLeg3.addBox(-8.1F, -1.5F, -1F, 3, 1, 2);
		RFLeg3.setRotationPoint(-5F, 18F, 0.5F);
		RFLeg3.setTextureSize(64, 32);
		RFLeg3.mirror = true;
		setRotation(RFLeg3, 0F, 0F, 0.5759587F);

		RFLeg4 = new ModelRenderer(this, 0, 13);
		RFLeg4.addBox(-6F, -3F, -0.5F, 1, 3, 1);
		RFLeg4.setRotationPoint(-5F, 18F, 0.5F);
		RFLeg4.setTextureSize(64, 32);
		RFLeg4.mirror = true;
		setRotation(RFLeg4, 0F, 0F, 0.2094395F);

		RFLeg5 = new ModelRenderer(this, 0, 0);
		RFLeg5.addBox(-6.4F, -1F, -1F, 2, 6, 2);
		RFLeg5.setRotationPoint(-5F, 18F, 0.5F);
		RFLeg5.setTextureSize(64, 32);
		RFLeg5.mirror = true;
		setRotation(RFLeg5, 0F, 0F, 0.1047198F);

		RFLeg6 = new ModelRenderer(this, 0, 13);
		RFLeg6.addBox(-6.5F, 3F, -0.5F, 1, 3, 1);
		RFLeg6.setRotationPoint(-5F, 18F, 0.5F);
		RFLeg6.setTextureSize(64, 32);
		RFLeg6.mirror = true;
		setRotation(RFLeg6, 0F, 0F, 0F);

		RMLeg1 = new ModelRenderer(this, 0, 13);
		RMLeg1.addBox(-3.5F, -0.5F, -0.5F, 3, 1, 1);
		RMLeg1.setRotationPoint(-5F, 18F, 4.5F);
		RMLeg1.setTextureSize(64, 32);
		RMLeg1.mirror = true;
		setRotation(RMLeg1, 0F, 0F, 0.4363323F);

		RMLeg2 = new ModelRenderer(this, 0, 0);
		RMLeg2.addBox(-5.5F, 0.5F, -1F, 3, 2, 2);
		RMLeg2.setRotationPoint(-5F, 18F, 4.5F);
		RMLeg2.setTextureSize(64, 32);
		RMLeg2.mirror = true;
		setRotation(RMLeg2, 0F, 0F, 0.9599311F);

		RMLeg3 = new ModelRenderer(this, 2, 0);
		RMLeg3.addBox(-8.1F, -1.5F, -1F, 3, 1, 2);
		RMLeg3.setRotationPoint(-5F, 18F, 4.5F);
		RMLeg3.setTextureSize(64, 32);
		RMLeg3.mirror = true;
		setRotation(RMLeg3, 0F, 0F, 0.5759587F);

		RMLeg4 = new ModelRenderer(this, 0, 13);
		RMLeg4.addBox(-6F, -3F, -0.5F, 1, 3, 1);
		RMLeg4.setRotationPoint(-5F, 18F, 4.5F);
		RMLeg4.setTextureSize(64, 32);
		RMLeg4.mirror = true;
		setRotation(RMLeg4, 0F, 0F, 0.2094395F);

		RMLeg5 = new ModelRenderer(this, 0, 0);
		RMLeg5.addBox(-6.4F, -1F, -1F, 2, 6, 2);
		RMLeg5.setRotationPoint(-5F, 18F, 4.5F);
		RMLeg5.setTextureSize(64, 32);
		RMLeg5.mirror = true;
		setRotation(RMLeg5, 0F, 0F, 0.1047198F);

		RMLeg6 = new ModelRenderer(this, 0, 13);
		RMLeg6.addBox(-6.5F, 3F, -0.5F, 1, 3, 1);
		RMLeg6.setRotationPoint(-5F, 18F, 4.5F);
		RMLeg6.setTextureSize(64, 32);
		RMLeg6.mirror = true;
		setRotation(RMLeg6, 0F, 0F, 0F);

		LFLeg1 = new ModelRenderer(this, 0, 13);
		LFLeg1.addBox(0.5F, -0.5F, -0.5F, 3, 1, 1);
		LFLeg1.setRotationPoint(5F, 18F, 0.5F);
		LFLeg1.setTextureSize(64, 32);
		LFLeg1.mirror = true;
		setRotation(LFLeg1, 0F, 0F, -0.4363323F);

		LFLeg2 = new ModelRenderer(this, 0, 0);
		LFLeg2.addBox(2.5F, 0.5F, -1F, 3, 2, 2);
		LFLeg2.setRotationPoint(5F, 18F, 0.5F);
		LFLeg2.setTextureSize(64, 32);
		LFLeg2.mirror = true;
		setRotation(LFLeg2, 0F, 0F, -0.9599311F);

		LFLeg3 = new ModelRenderer(this, 2, 0);
		LFLeg3.addBox(5.1F, -1.5F, -1F, 3, 1, 2);
		LFLeg3.setRotationPoint(5F, 18F, 0.5F);
		LFLeg3.setTextureSize(64, 32);
		LFLeg3.mirror = true;
		setRotation(LFLeg3, 0F, 0F, -0.5759587F);

		LFLeg4 = new ModelRenderer(this, 0, 13);
		LFLeg4.addBox(5F, -3F, -0.5F, 1, 3, 1);
		LFLeg4.setRotationPoint(5F, 18F, 0.5F);
		LFLeg4.setTextureSize(64, 32);
		LFLeg4.mirror = true;
		setRotation(LFLeg4, 0F, 0F, -0.2094395F);

		LFLeg5 = new ModelRenderer(this, 0, 0);
		LFLeg5.addBox(4.6F, -1F, -1F, 2, 6, 2);
		LFLeg5.setRotationPoint(5F, 18F, 0.5F);
		LFLeg5.setTextureSize(64, 32);
		LFLeg5.mirror = true;
		setRotation(LFLeg5, 0F, 0F, -0.1047198F);

		LFLeg6 = new ModelRenderer(this, 0, 13);
		LFLeg6.addBox(5.5F, 3F, -0.5F, 1, 3, 1);
		LFLeg6.setRotationPoint(5F, 18F, 0.5F);
		LFLeg6.setTextureSize(64, 32);
		LFLeg6.mirror = true;
		setRotation(LFLeg6, 0F, 0F, 0F);

		LMLeg1 = new ModelRenderer(this, 0, 13);
		LMLeg1.addBox(0.5F, -0.5F, -0.5F, 3, 1, 1);
		LMLeg1.setRotationPoint(5F, 18F, 4.5F);
		LMLeg1.setTextureSize(64, 32);
		LMLeg1.mirror = true;
		setRotation(LMLeg1, 0F, 0F, -0.4363323F);

		LMLeg2 = new ModelRenderer(this, 0, 0);
		LMLeg2.addBox(2.5F, 0.5F, -1F, 3, 2, 2);
		LMLeg2.setRotationPoint(5F, 18F, 4.5F);
		LMLeg2.setTextureSize(64, 32);
		LMLeg2.mirror = true;
		setRotation(LMLeg2, 0F, 0F, -0.9599311F);

		LMLeg4 = new ModelRenderer(this, 0, 13);
		LMLeg4.addBox(5F, -3F, -0.5F, 1, 3, 1);
		LMLeg4.setRotationPoint(5F, 18F, 4.5F);
		LMLeg4.setTextureSize(64, 32);
		LMLeg4.mirror = true;
		setRotation(LMLeg4, 0F, 0F, -0.2094395F);

		LMLeg3 = new ModelRenderer(this, 2, 0);
		LMLeg3.addBox(5.1F, -1.5F, -1F, 3, 1, 2);
		LMLeg3.setRotationPoint(5F, 18F, 4.5F);
		LMLeg3.setTextureSize(64, 32);
		LMLeg3.mirror = true;
		setRotation(LMLeg3, 0F, 0F, -0.5759587F);

		LMLeg5 = new ModelRenderer(this, 0, 0);
		LMLeg5.addBox(4.6F, -1F, -1F, 2, 6, 2);
		LMLeg5.setRotationPoint(5F, 18F, 4.5F);
		LMLeg5.setTextureSize(64, 32);
		LMLeg5.mirror = true;
		setRotation(LMLeg5, 0F, 0F, -0.1047198F);

		LMLeg6 = new ModelRenderer(this, 0, 13);
		LMLeg6.addBox(5.5F, 3F, -0.5F, 1, 3, 1);
		LMLeg6.setRotationPoint(5F, 18F, 4.5F);
		LMLeg6.setTextureSize(64, 32);
		LMLeg6.mirror = true;
		setRotation(LMLeg6, 0F, 0F, 0F);
	}


	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Nose.render(f5);
		EyeLeft.render(f5);
		HeadMid.render(f5);
		HeadEnd.render(f5);
		TailTuft.render(f5);
		EyeRight.render(f5);
		BodyTopLeft4.render(f5);
		BodyTopRight4.render(f5);
		BodyTopLeft1.render(f5);
		BodyTopRight1.render(f5);
		BodyTopRight2.render(f5);
		BodyTopLeft2.render(f5);
		BodyTopRight3.render(f5);
		BodyTopLeft3.render(f5);
		HeadBase.render(f5);
		TailBase.render(f5);
		BodyLow2.render(f5);
		BodyLow1.render(f5);
		Spine5.render(f5);
		Spine1.render(f5);
		Spine2.render(f5);
		Spine3.render(f5);
		Spine4.render(f5);
		RightArmSeg4.render(f5);
		LeftArmSeg1.render(f5);
		LeftArmSeg3.render(f5);
		RightArmSeg2.render(f5);
		RightArmSeg1.render(f5);
		LeftArmSeg5.render(f5);
		LeftArmSeg2.render(f5);
		LeftClawTop.render(f5);
		RightArmSeg3.render(f5);
		RightArmSeg5.render(f5);
		LeftArmSeg4.render(f5);
		LeftClawBase.render(f5);
		RightClawBase.render(f5);
		LeftClawLow.render(f5);
		RightClawTop.render(f5);
		RightClawLow.render(f5);
		LBLeg1.render(f5);
		LBLeg3.render(f5);
		RBLeg1.render(f5);
		RBLeg3.render(f5);
		LBLeg2.render(f5);
		RBLeg2.render(f5);
		LBLeg4.render(f5);
		RBLeg4.render(f5);
		RBLeg5.render(f5);
		LBLeg6.render(f5);
		RBLeg6.render(f5);
		LBLeg5.render(f5);
		RFLeg1.render(f5);
		RFLeg2.render(f5);
		RFLeg3.render(f5);
		RFLeg4.render(f5);
		RFLeg5.render(f5);
		RFLeg6.render(f5);
		RMLeg1.render(f5);
		RMLeg2.render(f5);
		RMLeg3.render(f5);
		RMLeg4.render(f5);
		RMLeg5.render(f5);
		RMLeg6.render(f5);
		LFLeg1.render(f5);
		LFLeg2.render(f5);
		LFLeg3.render(f5);
		LFLeg4.render(f5);
		LFLeg5.render(f5);
		LFLeg6.render(f5);
		LMLeg1.render(f5);
		LMLeg2.render(f5);
		LMLeg4.render(f5);
		LMLeg3.render(f5);
		LMLeg5.render(f5);
		LMLeg6.render(f5);
	}


	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}


	@Override
	public void setRotationAngles(float f, float f1, float f2,
	                              float f3, float f4, float f5, Entity ent) {

		HeadBase.rotateAngleX = f4 / 57.29578F;
		HeadBase.rotateAngleY = f3 / 57.29578F;
		HeadMid.rotateAngleX = f4 / 57.29578F;
		HeadMid.rotateAngleY = f3 / 57.29578F;
		HeadEnd.rotateAngleX = f4 / 57.29578F;
		HeadEnd.rotateAngleY = f3 / 57.29578F;
		EyeLeft.rotateAngleX = f4 / 57.29578F;
		EyeLeft.rotateAngleY = f3 / 57.29578F;
		EyeRight.rotateAngleX = f4 / 57.29578F;
		EyeRight.rotateAngleY = f3 / 57.29578F;
		Nose.rotateAngleX = f4 / 57.29578F;
		Nose.rotateAngleY = f3 / 57.29578F;

		// leg movement
		// going to move them up and down, so... yeah.
		// rotate all leg pieces ON THE Z AXIS!
		EntityCaveFisher foo = (EntityCaveFisher) ent;
		float[] thisAngles = foo.myAngles;

		LFLeg1.rotateAngleZ = -0.4363323F - MathHelper.cos(f * 0.7662F) * 1.4F * f1;
		LFLeg2.rotateAngleZ = -0.9599311F - MathHelper.cos(f * 0.7662F) * 1.4F * f1;
		LFLeg3.rotateAngleZ = -0.5759587F - MathHelper.cos(f * 0.7662F) * 1.4F * f1;
		LFLeg4.rotateAngleZ = -0.2094395F - MathHelper.cos(f * 0.7662F) * 1.4F * f1;
		LFLeg5.rotateAngleZ = -0.1047198F - MathHelper.cos(f * 0.7662F) * 1.4F * f1;
		LFLeg6.rotateAngleZ = (MathHelper.cos(f * 0.3662F) * 1.4F * f1) * -1;

		RFLeg1.rotateAngleZ = 0.4363323F + MathHelper.cos(f * 0.7662F + (float) Math.PI) * 1.4F * f1;
		RFLeg2.rotateAngleZ = 0.9599311F + MathHelper.cos(f * 0.7662F + (float) Math.PI) * 1.4F * f1;
		RFLeg3.rotateAngleZ = 0.5759587F + MathHelper.cos(f * 0.7662F + (float) Math.PI) * 1.4F * f1;
		RFLeg4.rotateAngleZ = 0.2094395F + MathHelper.cos(f * 0.7662F + (float) Math.PI) * 1.4F * f1;
		RFLeg5.rotateAngleZ = 0.1047198F + MathHelper.cos(f * 0.7662F + (float) Math.PI) * 1.4F * f1;
		RFLeg6.rotateAngleZ = MathHelper.cos(f * 0.3662F + (float) Math.PI) * 1.4F * f1;

		LMLeg1.rotateAngleZ = -0.4363323F - thisAngles[1];
		LMLeg2.rotateAngleZ = -0.9599311F - thisAngles[1];
		LMLeg3.rotateAngleZ = -0.5759587F - thisAngles[1];
		LMLeg4.rotateAngleZ = -0.2094395F - thisAngles[1];
		LMLeg5.rotateAngleZ = -0.1047198F - thisAngles[1];
		LMLeg6.rotateAngleZ = thisAngles[1];

		RMLeg1.rotateAngleZ = 0.4363323F + thisAngles[0];
		RMLeg2.rotateAngleZ = 0.9599311F + thisAngles[0];
		RMLeg3.rotateAngleZ = 0.5759587F + thisAngles[0];
		RMLeg4.rotateAngleZ = 0.2094395F + thisAngles[0];
		RMLeg5.rotateAngleZ = 0.1047198F + thisAngles[0];
		RMLeg6.rotateAngleZ = thisAngles[0] * -1;

		LBLeg1.rotateAngleZ = -0.4363323F - thisAngles[2];
		LBLeg2.rotateAngleZ = -0.9599311F - thisAngles[2];
		LBLeg3.rotateAngleZ = -0.5759587F - thisAngles[2];
		LBLeg4.rotateAngleZ = -0.2094395F - thisAngles[2];
		LBLeg5.rotateAngleZ = -0.1047198F - thisAngles[2];
		LBLeg6.rotateAngleZ = thisAngles[2] * -1;

		RBLeg1.rotateAngleZ = 0.4363323F + thisAngles[3];
		RBLeg2.rotateAngleZ = 0.9599311F + thisAngles[3];
		RBLeg3.rotateAngleZ = 0.5759587F + thisAngles[3];
		RBLeg4.rotateAngleZ = 0.2094395F + thisAngles[3];
		RBLeg5.rotateAngleZ = 0.1047198F + thisAngles[3];
		RBLeg6.rotateAngleZ = thisAngles[3];

		foo.myAngles[3] = foo.myAngles[1];
		foo.myAngles[2] = foo.myAngles[0];
		foo.myAngles[1] = MathHelper.cos(f * 0.7662F + (float) Math.PI) * 1.4F * f1;
		foo.myAngles[0] = MathHelper.cos(f * 0.7662F) * 1.4F * f1;

		//super.setRotationAngles(f, f1, f2, f3, f4, f5, ent);
	}

}