package com.gw.dm.model;

import com.gw.dm.entity.EntityDestrachan;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelDestrachan extends ModelBase {

	ModelRenderer LeftFootBigClaw;
	ModelRenderer LeftLegLow;
	ModelRenderer LeftLegMid;
	ModelRenderer LeftLegCalf;
	ModelRenderer LeftLegHighTop;
	ModelRenderer LeftFoot;
	ModelRenderer LeftFootLtlClaw;
	ModelRenderer RightFootBigClaw;
	ModelRenderer RightFootLtlClaw;
	ModelRenderer RightFoot;
	ModelRenderer RightLegLow;
	ModelRenderer LeftThighHigh;
	ModelRenderer RightLegCalf;
	ModelRenderer RightLegMid;
	ModelRenderer RightLegHighTop;
	ModelRenderer LeftThighLow;
	ModelRenderer RightThighHigh;
	ModelRenderer RightThighLow;
	ModelRenderer LeftBodyBackBase;
	ModelRenderer RightBodyBackBase;
	ModelRenderer HeadLeft1;
	ModelRenderer SpineBase;
	ModelRenderer SpineFront1;
	ModelRenderer SpineFront2;
	ModelRenderer SpineFront3;
	ModelRenderer SpineFront4;
	ModelRenderer SpineLow4;
	ModelRenderer HeadMid;
	ModelRenderer HeadLow1;
	ModelRenderer MouthLipBot;
	ModelRenderer HeadLeft2;
	ModelRenderer LeftEarLipBot;
	ModelRenderer MouthLipLeft;
	ModelRenderer MouthLipTop;
	ModelRenderer MouthBase;
	ModelRenderer MouthLipRight;
	ModelRenderer HeadRight1;
	ModelRenderer HeadRight2;
	ModelRenderer HeadBase;
	ModelRenderer RightEarLipBot;
	ModelRenderer RightEarLipBack;
	ModelRenderer RightEarLipTop;
	ModelRenderer RightEarBase;
	ModelRenderer LeftEarLipBack;
	ModelRenderer LeftEarLipTop;
	ModelRenderer LeftEarBase;
	ModelRenderer TailBaseTop;
	ModelRenderer SpineFront5;
	ModelRenderer SpineLow1;
	ModelRenderer BodyPart2;
	ModelRenderer SpineLow3;
	ModelRenderer BodyBotBase;
	ModelRenderer TailBaseMidHigh;
	ModelRenderer TailBase;
	ModelRenderer TailPart2;
	ModelRenderer SpineLow2;
	ModelRenderer BodyPart4;
	ModelRenderer BodyPart1;
	ModelRenderer BodyPart6;
	ModelRenderer BodyPart3;
	ModelRenderer BodyPartLast;
	ModelRenderer LeftArmBase;
	ModelRenderer TailBaseMidLow;
	ModelRenderer TailPart3;
	ModelRenderer TailPart1;
	ModelRenderer BodyPart5;
	ModelRenderer RightArmBase;
	ModelRenderer LeftArmLow;
	ModelRenderer LeftArmHigh;
	ModelRenderer RightArmHigh;
	ModelRenderer RightFingerLtl;
	ModelRenderer RightArmLow;
	ModelRenderer LeftFingerLtl;
	ModelRenderer RightFingerBig;
	ModelRenderer LeftFingerBig;


	public ModelDestrachan() {
		textureWidth = 64;
		textureHeight = 32;

		LeftFootBigClaw = new ModelRenderer(this, 0, 28);
		LeftFootBigClaw.addBox(-1.3F, 11F, -14F, 1, 1, 3);
		LeftFootBigClaw.setRotationPoint(6F, 12F, 11F);
		LeftFootBigClaw.setTextureSize(64, 32);
		LeftFootBigClaw.mirror = true;
		setRotation(LeftFootBigClaw, 0F, 0F, 0F);

		LeftLegLow = new ModelRenderer(this, 0, 0);
		LeftLegLow.addBox(-1.5F, 1F, -14F, 3, 2, 6);
		LeftLegLow.setRotationPoint(6F, 12F, 11F);
		LeftLegLow.setTextureSize(64, 32);
		LeftLegLow.mirror = true;
		setRotation(LeftLegLow, 0.7853982F, 0F, 0F);

		LeftLegMid = new ModelRenderer(this, 0, 0);
		LeftLegMid.addBox(-1F, 8F, -3.6F, 2, 2, 6);
		LeftLegMid.setRotationPoint(6F, 12F, 11F);
		LeftLegMid.setTextureSize(64, 32);
		LeftLegMid.mirror = true;
		setRotation(LeftLegMid, -0.7504916F, 0F, 0F);

		LeftLegCalf = new ModelRenderer(this, 0, 0);
		LeftLegCalf.addBox(-0.5F, 7F, -2.6F, 1, 1, 3);
		LeftLegCalf.setRotationPoint(6F, 12F, 11F);
		LeftLegCalf.setTextureSize(64, 32);
		LeftLegCalf.mirror = true;
		setRotation(LeftLegCalf, -0.7504916F, 0F, 0F);

		LeftLegHighTop = new ModelRenderer(this, 0, 0);
		LeftLegHighTop.addBox(-2F, -1F, -8.7F, 3, 2, 8);
		LeftLegHighTop.setRotationPoint(6F, 12F, 11F);
		LeftLegHighTop.setTextureSize(64, 32);
		LeftLegHighTop.mirror = true;
		setRotation(LeftLegHighTop, 0.5235988F, 0F, 0F);

		LeftFoot = new ModelRenderer(this, 0, 0);
		LeftFoot.addBox(-2F, 10F, -11F, 4, 2, 4);
		LeftFoot.setRotationPoint(6F, 12F, 11F);
		LeftFoot.setTextureSize(64, 32);
		LeftFoot.mirror = true;
		setRotation(LeftFoot, 0F, 0F, 0F);

		LeftFootLtlClaw = new ModelRenderer(this, 0, 28);
		LeftFootLtlClaw.addBox(0.3F, 11F, -13F, 1, 1, 2);
		LeftFootLtlClaw.setRotationPoint(6F, 12F, 11F);
		LeftFootLtlClaw.setTextureSize(64, 32);
		LeftFootLtlClaw.mirror = true;
		setRotation(LeftFootLtlClaw, 0F, 0F, 0F);

		RightFootBigClaw = new ModelRenderer(this, 0, 28);
		RightFootBigClaw.addBox(0.3F, 11F, -14F, 1, 1, 3);
		RightFootBigClaw.setRotationPoint(-6F, 12F, 11F);
		RightFootBigClaw.setTextureSize(64, 32);
		RightFootBigClaw.mirror = true;
		setRotation(RightFootBigClaw, 0F, 0F, 0F);

		RightFootLtlClaw = new ModelRenderer(this, 0, 28);
		RightFootLtlClaw.addBox(-1.3F, 11F, -13F, 1, 1, 2);
		RightFootLtlClaw.setRotationPoint(-6F, 12F, 11F);
		RightFootLtlClaw.setTextureSize(64, 32);
		RightFootLtlClaw.mirror = true;
		setRotation(RightFootLtlClaw, 0F, 0F, 0F);

		RightFoot = new ModelRenderer(this, 0, 0);
		RightFoot.addBox(-2F, 10F, -11F, 4, 2, 4);
		RightFoot.setRotationPoint(-6F, 12F, 11F);
		RightFoot.setTextureSize(64, 32);
		RightFoot.mirror = true;
		setRotation(RightFoot, 0F, 0F, 0F);

		RightLegLow = new ModelRenderer(this, 0, 0);
		RightLegLow.addBox(-1.5F, 1F, -14F, 3, 2, 6);
		RightLegLow.setRotationPoint(-6F, 12F, 11F);
		RightLegLow.setTextureSize(64, 32);
		RightLegLow.mirror = true;
		setRotation(RightLegLow, 0.7853982F, 0F, 0F);

		LeftThighHigh = new ModelRenderer(this, 0, 0);
		LeftThighHigh.addBox(-3F, -1F, -8.7F, 4, 4, 8);
		LeftThighHigh.setRotationPoint(6.2F, 10F, 13F);
		LeftThighHigh.setTextureSize(64, 32);
		LeftThighHigh.mirror = true;
		setRotation(LeftThighHigh, 0.5235988F, 0F, 0F);

		RightLegCalf = new ModelRenderer(this, 0, 0);
		RightLegCalf.addBox(-0.5F, 7F, -2.6F, 1, 1, 3);
		RightLegCalf.setRotationPoint(-6F, 12F, 11F);
		RightLegCalf.setTextureSize(64, 32);
		RightLegCalf.mirror = true;
		setRotation(RightLegCalf, -0.7504916F, 0F, 0F);

		RightLegMid = new ModelRenderer(this, 0, 0);
		RightLegMid.addBox(-1F, 8F, -3.6F, 2, 2, 6);
		RightLegMid.setRotationPoint(-6F, 12F, 11F);
		RightLegMid.setTextureSize(64, 32);
		RightLegMid.mirror = true;
		setRotation(RightLegMid, -0.7504916F, 0F, 0F);

		RightLegHighTop = new ModelRenderer(this, 0, 0);
		RightLegHighTop.addBox(-1F, -1F, -8.7F, 3, 2, 8);
		RightLegHighTop.setRotationPoint(-6F, 12F, 11F);
		RightLegHighTop.setTextureSize(64, 32);
		RightLegHighTop.mirror = true;
		setRotation(RightLegHighTop, 0.5235988F, 0F, 0F);

		LeftThighLow = new ModelRenderer(this, 16, 23);
		LeftThighLow.addBox(-2F, -1F, -8.4F, 3, 2, 7);
		LeftThighLow.setRotationPoint(5.6F, 12.3F, 14.3F);
		LeftThighLow.setTextureSize(64, 32);
		LeftThighLow.mirror = true;
		setRotation(LeftThighLow, 0.5235988F, 0F, 0F);

		RightThighHigh = new ModelRenderer(this, 0, 0);
		RightThighHigh.addBox(-1F, -1F, -8.7F, 4, 4, 8);
		RightThighHigh.setRotationPoint(-6.2F, 10F, 13F);
		RightThighHigh.setTextureSize(64, 32);
		RightThighHigh.mirror = true;
		setRotation(RightThighHigh, 0.5235988F, 0F, 0F);

		RightThighLow = new ModelRenderer(this, 16, 23);
		RightThighLow.addBox(-1F, -1F, -8.4F, 3, 2, 7);
		RightThighLow.setRotationPoint(-5.7F, 12.3F, 14.3F);
		RightThighLow.setTextureSize(64, 32);
		RightThighLow.mirror = true;
		setRotation(RightThighLow, 0.5235988F, 0F, 0F);

		LeftBodyBackBase = new ModelRenderer(this, 0, 0);
		LeftBodyBackBase.addBox(0F, -1.5F, -2F, 6, 3, 4);
		LeftBodyBackBase.setRotationPoint(5F, 12F, 11F);
		LeftBodyBackBase.setTextureSize(64, 32);
		LeftBodyBackBase.mirror = true;
		setRotation(LeftBodyBackBase, 0F, 0F, -2.268928F);

		RightBodyBackBase = new ModelRenderer(this, 0, 0);
		RightBodyBackBase.addBox(0F, -1.5F, -2F, 6, 3, 4);
		RightBodyBackBase.setRotationPoint(-5F, 12F, 11F);
		RightBodyBackBase.setTextureSize(64, 32);
		RightBodyBackBase.mirror = true;
		setRotation(RightBodyBackBase, 0F, 0F, -0.8726646F);

		HeadLeft1 = new ModelRenderer(this, 42, 0);
		HeadLeft1.addBox(0.5F, 0F, -10F, 2, 3, 9);
		HeadLeft1.setRotationPoint(0F, 3.8F, -13F);
		HeadLeft1.setTextureSize(64, 32);
		HeadLeft1.mirror = true;
		setRotation(HeadLeft1, 0F, 0F, 0.2268928F);

		SpineBase = new ModelRenderer(this, 0, 0);
		SpineBase.addBox(-2.5F, 0F, 0F, 5, 2, 4);
		SpineBase.setRotationPoint(0F, 6.4F, 9F);
		SpineBase.setTextureSize(64, 32);
		SpineBase.mirror = true;
		setRotation(SpineBase, 0F, 0F, 0F);

		SpineFront1 = new ModelRenderer(this, 0, 0);
		SpineFront1.addBox(-2F, 0F, 0F, 4, 2, 8);
		SpineFront1.setRotationPoint(0F, 3.4F, 3F);
		SpineFront1.setTextureSize(64, 32);
		SpineFront1.mirror = true;
		setRotation(SpineFront1, -0.4363323F, 0F, 0F);

		SpineFront2 = new ModelRenderer(this, 0, 0);
		SpineFront2.addBox(-2.5F, 0F, 0F, 5, 2, 8);
		SpineFront2.setRotationPoint(0F, -0.6F, -1F);
		SpineFront2.setTextureSize(64, 32);
		SpineFront2.mirror = true;
		setRotation(SpineFront2, -0.6806784F, 0F, 0F);

		SpineFront3 = new ModelRenderer(this, 0, 0);
		SpineFront3.addBox(-3F, 0F, 0F, 6, 2, 5);
		SpineFront3.setRotationPoint(0F, -1.1F, -6F);
		SpineFront3.setTextureSize(64, 32);
		SpineFront3.mirror = true;
		setRotation(SpineFront3, -0.1064651F, 0F, 0F);

		SpineFront4 = new ModelRenderer(this, 0, 0);
		SpineFront4.addBox(-2F, 0F, 0F, 4, 2, 5);
		SpineFront4.setRotationPoint(0F, 1.9F, -10F);
		SpineFront4.setTextureSize(64, 32);
		SpineFront4.mirror = true;
		setRotation(SpineFront4, 0.6108652F, 0F, 0F);

		SpineLow4 = new ModelRenderer(this, 0, 0);
		SpineLow4.addBox(-2F, -0.2F, 0F, 4, 2, 8);
		SpineLow4.setRotationPoint(0F, 10.3F, 0.7F);
		SpineLow4.setTextureSize(64, 32);
		SpineLow4.mirror = true;
		setRotation(SpineLow4, -0.1570796F, 0F, 0F);

		HeadMid = new ModelRenderer(this, 27, 0);
		HeadMid.addBox(-2.5F, -2F, -9.6F, 5, 1, 5);
		HeadMid.setRotationPoint(0F, 3.8F, -13F);
		HeadMid.setTextureSize(64, 32);
		HeadMid.mirror = true;
		setRotation(HeadMid, 0.2094395F, 0F, 0F);

		HeadLow1 = new ModelRenderer(this, 0, 0);
		HeadLow1.addBox(-0.5F, 0F, -11F, 1, 2, 10);
		HeadLow1.setRotationPoint(0F, 3.8F, -13F);
		HeadLow1.setTextureSize(64, 32);
		HeadLow1.mirror = true;
		setRotation(HeadLow1, 0.3141593F, 0F, 0F);

		MouthLipBot = new ModelRenderer(this, 0, 24);
		MouthLipBot.addBox(-2.5F, 3F, -10.8F, 5, 1, 1);
		MouthLipBot.setRotationPoint(0F, 3.8F, -13F);
		MouthLipBot.setTextureSize(64, 32);
		MouthLipBot.mirror = true;
		setRotation(MouthLipBot, 0F, 0F, 0F);

		HeadLeft2 = new ModelRenderer(this, 58, 0);
		HeadLeft2.addBox(1.4F, -0.4F, -7F, 1, 1, 2);
		HeadLeft2.setRotationPoint(0F, 3.8F, -13F);
		HeadLeft2.setTextureSize(64, 32);
		HeadLeft2.mirror = true;
		setRotation(HeadLeft2, 0F, 0F, 0.1745329F);

		LeftEarLipBot = new ModelRenderer(this, 46, 21);
		LeftEarLipBot.addBox(-1.5F, 1F, -3.3F, 8, 1, 1);
		LeftEarLipBot.setRotationPoint(0F, 3.8F, -13F);
		LeftEarLipBot.setTextureSize(64, 32);
		LeftEarLipBot.mirror = true;
		setRotation(LeftEarLipBot, 0F, -0.9599311F, -0.4363323F);

		MouthLipLeft = new ModelRenderer(this, 0, 20);
		MouthLipLeft.addBox(1.5F, 1F, -10.8F, 1, 2, 1);
		MouthLipLeft.setRotationPoint(0F, 3.8F, -13F);
		MouthLipLeft.setTextureSize(64, 32);
		MouthLipLeft.mirror = true;
		setRotation(MouthLipLeft, 0F, 0F, 0F);

		MouthLipTop = new ModelRenderer(this, 0, 24);
		MouthLipTop.addBox(-2.5F, 0F, -10.8F, 5, 1, 1);
		MouthLipTop.setRotationPoint(0F, 3.8F, -13F);
		MouthLipTop.setTextureSize(64, 32);
		MouthLipTop.mirror = true;
		setRotation(MouthLipTop, 0F, 0F, 0F);

		MouthBase = new ModelRenderer(this, 0, 15);
		MouthBase.addBox(-2.5F, 0F, -10.5F, 5, 4, 1);
		MouthBase.setRotationPoint(0F, 3.8F, -13F);
		MouthBase.setTextureSize(64, 32);
		MouthBase.mirror = true;
		setRotation(MouthBase, 0F, 0F, 0F);

		MouthLipRight = new ModelRenderer(this, 0, 20);
		MouthLipRight.addBox(-2.5F, 1F, -10.8F, 1, 2, 1);
		MouthLipRight.setRotationPoint(0F, 3.8F, -13F);
		MouthLipRight.setTextureSize(64, 32);
		MouthLipRight.mirror = true;
		setRotation(MouthLipRight, 0F, 0F, 0F);

		HeadRight1 = new ModelRenderer(this, 52, 0);
		HeadRight1.addBox(-2.5F, 0F, -10F, 2, 3, 9);
		HeadRight1.setRotationPoint(0F, 3.8F, -13F);
		HeadRight1.setTextureSize(64, 32);
		HeadRight1.mirror = true;
		setRotation(HeadRight1, 0F, 0F, -0.2268928F);

		HeadRight2 = new ModelRenderer(this, 57, 0);
		HeadRight2.addBox(-2.4F, -0.4F, -7F, 1, 1, 2);
		HeadRight2.setRotationPoint(0F, 3.8F, -13F);
		HeadRight2.setTextureSize(64, 32);
		HeadRight2.mirror = true;
		setRotation(HeadRight2, 0F, 0F, -0.1745329F);

		HeadBase = new ModelRenderer(this, 0, 0);
		HeadBase.addBox(-2.5F, -1F, -5F, 5, 2, 5);
		HeadBase.setRotationPoint(0F, 3.8F, -13F);
		HeadBase.setTextureSize(64, 32);
		HeadBase.mirror = true;
		setRotation(HeadBase, 0F, 0F, 0F);

		RightEarLipBot = new ModelRenderer(this, 46, 21);
		RightEarLipBot.addBox(-6.5F, 1F, -3.3F, 8, 1, 1);
		RightEarLipBot.setRotationPoint(0F, 3.8F, -13F);
		RightEarLipBot.setTextureSize(64, 32);
		RightEarLipBot.mirror = true;
		setRotation(RightEarLipBot, 0F, 0.9599311F, 0.4363323F);

		RightEarLipBack = new ModelRenderer(this, 42, 29);
		RightEarLipBack.addBox(-6.5F, -1F, -3.3F, 1, 2, 1);
		RightEarLipBack.setRotationPoint(0F, 3.8F, -13F);
		RightEarLipBack.setTextureSize(64, 32);
		RightEarLipBack.mirror = true;
		setRotation(RightEarLipBack, 0F, 0.9599311F, 0.4363323F);

		RightEarLipTop = new ModelRenderer(this, 46, 21);
		RightEarLipTop.addBox(-6.5F, -2F, -3.3F, 8, 1, 1);
		RightEarLipTop.setRotationPoint(0F, 3.8F, -13F);
		RightEarLipTop.setTextureSize(64, 32);
		RightEarLipTop.mirror = true;
		setRotation(RightEarLipTop, 0F, 0.9599311F, 0.4363323F);

		RightEarBase = new ModelRenderer(this, 48, 27);
		RightEarBase.addBox(-6.5F, -2F, -3F, 8, 4, 1);
		RightEarBase.setRotationPoint(0F, 3.8F, -13F);
		RightEarBase.setTextureSize(64, 32);
		RightEarBase.mirror = true;
		setRotation(RightEarBase, 0F, 0.9599311F, 0.4363323F);

		LeftEarLipBack = new ModelRenderer(this, 42, 29);
		LeftEarLipBack.addBox(5.5F, -1F, -3.3F, 1, 2, 1);
		LeftEarLipBack.setRotationPoint(0F, 3.8F, -13F);
		LeftEarLipBack.setTextureSize(64, 32);
		LeftEarLipBack.mirror = true;
		setRotation(LeftEarLipBack, 0F, -0.9599311F, -0.4363323F);

		LeftEarLipTop = new ModelRenderer(this, 46, 21);
		LeftEarLipTop.addBox(-1.5F, -2F, -3.3F, 8, 1, 1);
		LeftEarLipTop.setRotationPoint(0F, 3.8F, -13F);
		LeftEarLipTop.setTextureSize(64, 32);
		LeftEarLipTop.mirror = true;
		setRotation(LeftEarLipTop, 0F, -0.9599311F, -0.4363323F);

		LeftEarBase = new ModelRenderer(this, 48, 27);
		LeftEarBase.addBox(-1.5F, -2F, -3F, 8, 4, 1);
		LeftEarBase.setRotationPoint(0F, 3.8F, -13F);
		LeftEarBase.setTextureSize(64, 32);
		LeftEarBase.mirror = true;
		setRotation(LeftEarBase, 0F, -0.9599311F, -0.4363323F);

		TailBaseTop = new ModelRenderer(this, 0, 0);
		TailBaseTop.addBox(-2F, 0F, 0F, 4, 1, 2);
		TailBaseTop.setRotationPoint(0F, 7F, 12F);
		TailBaseTop.setTextureSize(64, 32);
		TailBaseTop.mirror = true;
		setRotation(TailBaseTop, 0F, 0F, 0F);

		SpineFront5 = new ModelRenderer(this, 0, 0);
		SpineFront5.addBox(-2F, 0F, 0F, 4, 2, 5);
		SpineFront5.setRotationPoint(0F, 2.9F, -14F);
		SpineFront5.setTextureSize(64, 32);
		SpineFront5.mirror = true;
		setRotation(SpineFront5, 0.2617994F, 0F, 0F);

		SpineLow1 = new ModelRenderer(this, 0, 0);
		SpineLow1.addBox(-2F, 0F, 0F, 4, 2, 5);
		SpineLow1.setRotationPoint(0F, 4.9F, -13.8F);
		SpineLow1.setTextureSize(64, 32);
		SpineLow1.mirror = true;
		setRotation(SpineLow1, -0.0872665F, 0F, 0F);

		BodyPart2 = new ModelRenderer(this, 0, 0);
		BodyPart2.addBox(0.9F, -3.6F, 0F, 2, 6, 7);
		BodyPart2.setRotationPoint(0F, 8.5F, 2.7F);
		BodyPart2.setTextureSize(64, 32);
		BodyPart2.mirror = true;
		setRotation(BodyPart2, -0.296706F, 0F, 0F);

		SpineLow3 = new ModelRenderer(this, 0, 0);
		SpineLow3.addBox(-2F, -0.2F, 0F, 4, 2, 6);
		SpineLow3.setRotationPoint(0F, 7.2F, -3.5F);
		SpineLow3.setTextureSize(64, 32);
		SpineLow3.mirror = true;
		setRotation(SpineLow3, -0.6108652F, 0F, 0F);

		BodyBotBase = new ModelRenderer(this, 0, 0);
		BodyBotBase.addBox(-5F, 0F, 0F, 10, 3, 2);
		BodyBotBase.setRotationPoint(0F, 10F, 10F);
		BodyBotBase.setTextureSize(64, 32);
		BodyBotBase.mirror = true;
		setRotation(BodyBotBase, 0F, 0F, 0F);

		TailBaseMidHigh = new ModelRenderer(this, 0, 0);
		TailBaseMidHigh.addBox(-3F, 0F, 0F, 6, 1, 2);
		TailBaseMidHigh.setRotationPoint(0F, 8F, 12F);
		TailBaseMidHigh.setTextureSize(64, 32);
		TailBaseMidHigh.mirror = true;
		setRotation(TailBaseMidHigh, 0F, 0F, 0F);

		TailBase = new ModelRenderer(this, 0, 0);
		TailBase.addBox(-5F, 0F, 0F, 10, 2, 2);
		TailBase.setRotationPoint(0F, 11F, 12F);
		TailBase.setTextureSize(64, 32);
		TailBase.mirror = true;
		setRotation(TailBase, 0F, 0F, 0F);

		TailPart2 = new ModelRenderer(this, 0, 0);
		TailPart2.addBox(-1.5F, -1.5F, 4F, 3, 3, 5);
		TailPart2.setRotationPoint(0F, 10.5F, 14F);
		TailPart2.setTextureSize(64, 32);
		TailPart2.mirror = true;
		setRotation(TailPart2, 0F, 0F, 0F);

		SpineLow2 = new ModelRenderer(this, 0, 0);
		SpineLow2.addBox(-2F, -0.2F, 0F, 4, 2, 6);
		SpineLow2.setRotationPoint(0F, 5.5F, -9.3F);
		SpineLow2.setTextureSize(64, 32);
		SpineLow2.mirror = true;
		setRotation(SpineLow2, -0.296706F, 0F, 0F);

		BodyPart4 = new ModelRenderer(this, 0, 0);
		BodyPart4.addBox(0.9F, -5.6F, 0F, 2, 6, 8);
		BodyPart4.setRotationPoint(0.3F, 6.5F, -4.4F);
		BodyPart4.setTextureSize(64, 32);
		BodyPart4.mirror = true;
		setRotation(BodyPart4, -0.5235988F, 0F, 0F);

		BodyPart1 = new ModelRenderer(this, 0, 0);
		BodyPart1.addBox(-2.9F, -3.6F, 0F, 2, 6, 7);
		BodyPart1.setRotationPoint(0F, 8.5F, 2.7F);
		BodyPart1.setTextureSize(64, 32);
		BodyPart1.mirror = true;
		setRotation(BodyPart1, -0.296706F, 0F, 0F);

		BodyPart6 = new ModelRenderer(this, 0, 0);
		BodyPart6.addBox(-3.5F, -6.4F, 0F, 2, 7, 5);
		BodyPart6.setRotationPoint(4.7F, 5.5F, -8.4F);
		BodyPart6.setTextureSize(64, 32);
		BodyPart6.mirror = true;
		setRotation(BodyPart6, -0.3665191F, 0F, 0F);

		BodyPart3 = new ModelRenderer(this, 0, 0);
		BodyPart3.addBox(-2.9F, -5.6F, 0F, 2, 6, 8);
		BodyPart3.setRotationPoint(-0.3F, 6.5F, -4.4F);
		BodyPart3.setTextureSize(64, 32);
		BodyPart3.mirror = true;
		setRotation(BodyPart3, -0.5235988F, 0F, 0F);

		BodyPartLast = new ModelRenderer(this, 0, 0);
		BodyPartLast.addBox(-2.25F, -6.4F, 0F, 5, 4, 8);
		BodyPartLast.setRotationPoint(-0.3F, 9F, -13.4F);
		BodyPartLast.setTextureSize(64, 32);
		BodyPartLast.mirror = true;
		setRotation(BodyPartLast, 0.2094395F, 0F, 0F);

		LeftArmBase = new ModelRenderer(this, 0, 0);
		LeftArmBase.addBox(0F, -3F, -3F, 3, 6, 6);
		LeftArmBase.setRotationPoint(1.7F, 5.5F, -4.4F);
		LeftArmBase.setTextureSize(64, 32);
		LeftArmBase.mirror = true;
		setRotation(LeftArmBase, -0.2617994F, 0F, 0F);

		TailBaseMidLow = new ModelRenderer(this, 0, 0);
		TailBaseMidLow.addBox(-4F, 0F, 0F, 8, 2, 2);
		TailBaseMidLow.setRotationPoint(0F, 9F, 12F);
		TailBaseMidLow.setTextureSize(64, 32);
		TailBaseMidLow.mirror = true;
		setRotation(TailBaseMidLow, 0F, 0F, 0F);

		TailPart3 = new ModelRenderer(this, 0, 0);
		TailPart3.addBox(-1F, -1F, 9F, 2, 2, 6);
		TailPart3.setRotationPoint(0F, 10.5F, 14F);
		TailPart3.setTextureSize(64, 32);
		TailPart3.mirror = true;
		setRotation(TailPart3, 0F, 0F, 0F);

		TailPart1 = new ModelRenderer(this, 0, 0);
		TailPart1.addBox(-2F, -2F, 0F, 4, 4, 4);
		TailPart1.setRotationPoint(0F, 10.5F, 14F);
		TailPart1.setTextureSize(64, 32);
		TailPart1.mirror = true;
		setRotation(TailPart1, 0F, 0F, 0F);

		BodyPart5 = new ModelRenderer(this, 0, 0);
		BodyPart5.addBox(-2.9F, -6.4F, 0F, 2, 7, 5);
		BodyPart5.setRotationPoint(-0.3F, 5.5F, -8.4F);
		BodyPart5.setTextureSize(64, 32);
		BodyPart5.mirror = true;
		setRotation(BodyPart5, -0.3665191F, 0F, 0F);

		RightArmBase = new ModelRenderer(this, 0, 0);
		RightArmBase.addBox(-3F, -3F, -3F, 3, 6, 6);
		RightArmBase.setRotationPoint(-1.8F, 5.5F, -4.4F);
		RightArmBase.setTextureSize(64, 32);
		RightArmBase.mirror = true;
		setRotation(RightArmBase, -0.2617994F, 0F, 0F);

		LeftArmLow = new ModelRenderer(this, 0, 0);
		LeftArmLow.addBox(1.5F, 4.2F, -0.1F, 2, 7, 3);
		LeftArmLow.setRotationPoint(1.7F, 5.5F, -4.4F);
		LeftArmLow.setTextureSize(64, 32);
		LeftArmLow.mirror = true;
		setRotation(LeftArmLow, -0.7853982F, 0F, -0.1570796F);

		LeftArmHigh = new ModelRenderer(this, 0, 0);
		LeftArmHigh.addBox(1F, -1.8F, -2.6F, 3, 8, 4);
		LeftArmHigh.setRotationPoint(1.7F, 5.5F, -4.4F);
		LeftArmHigh.setTextureSize(64, 32);
		LeftArmHigh.mirror = true;
		setRotation(LeftArmHigh, -0.2617994F, 0F, -0.1570796F);

		RightArmHigh = new ModelRenderer(this, 0, 0);
		RightArmHigh.addBox(-5F, -1.8F, -2.6F, 3, 8, 4);
		RightArmHigh.setRotationPoint(-1.8F, 5.5F, -4.4F);
		RightArmHigh.setTextureSize(64, 32);
		RightArmHigh.mirror = true;
		setRotation(RightArmHigh, -0.2617994F, 0F, 0.1570796F);

		RightFingerLtl = new ModelRenderer(this, 0, 28);
		RightFingerLtl.addBox(-4.4F, 11.2F, 1.4F, 1, 2, 1);
		RightFingerLtl.setRotationPoint(-1.8F, 5.5F, -4.4F);
		RightFingerLtl.setTextureSize(64, 32);
		RightFingerLtl.mirror = true;
		setRotation(RightFingerLtl, -0.7853982F, 0F, 0.1570796F);

		RightArmLow = new ModelRenderer(this, 0, 0);
		RightArmLow.addBox(-4.5F, 4.2F, -0.1F, 2, 7, 3);
		RightArmLow.setRotationPoint(-1.8F, 5.5F, -4.4F);
		RightArmLow.setTextureSize(64, 32);
		RightArmLow.mirror = true;
		setRotation(RightArmLow, -0.7853982F, 0F, 0.1570796F);

		LeftFingerLtl = new ModelRenderer(this, 0, 28);
		LeftFingerLtl.addBox(2.4F, 11.2F, 1.4F, 1, 2, 1);
		LeftFingerLtl.setRotationPoint(1.7F, 5.5F, -4.4F);
		LeftFingerLtl.setTextureSize(64, 32);
		LeftFingerLtl.mirror = true;
		setRotation(LeftFingerLtl, -0.7853982F, 0F, -0.1570796F);

		RightFingerBig = new ModelRenderer(this, 0, 28);
		RightFingerBig.addBox(-4.4F, 11.2F, 0.1F, 1, 3, 1);
		RightFingerBig.setRotationPoint(-1.8F, 5.5F, -4.4F);
		RightFingerBig.setTextureSize(64, 32);
		RightFingerBig.mirror = true;
		setRotation(RightFingerBig, -0.7853982F, 0F, 0.1570796F);

		LeftFingerBig = new ModelRenderer(this, 0, 28);
		LeftFingerBig.addBox(2.4F, 11.2F, 0.1F, 1, 3, 1);
		LeftFingerBig.setRotationPoint(1.7F, 5.5F, -4.4F);
		LeftFingerBig.setTextureSize(64, 32);
		LeftFingerBig.mirror = true;
		setRotation(LeftFingerBig, -0.7853982F, 0F, -0.1570796F);
	}


	@Override
	public void render(Entity entity, float f, float f1, float f2,
	                   float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		LeftFootBigClaw.render(f5);
		LeftLegLow.render(f5);
		LeftLegMid.render(f5);
		LeftLegCalf.render(f5);
		LeftLegHighTop.render(f5);
		LeftFoot.render(f5);
		LeftFootLtlClaw.render(f5);
		RightFootBigClaw.render(f5);
		RightFootLtlClaw.render(f5);
		RightFoot.render(f5);
		RightLegLow.render(f5);
		LeftThighHigh.render(f5);
		RightLegCalf.render(f5);
		RightLegMid.render(f5);
		RightLegHighTop.render(f5);
		LeftThighLow.render(f5);
		RightThighHigh.render(f5);
		RightThighLow.render(f5);
		LeftBodyBackBase.render(f5);
		RightBodyBackBase.render(f5);
		HeadLeft1.render(f5);
		SpineBase.render(f5);
		SpineFront1.render(f5);
		SpineFront2.render(f5);
		SpineFront3.render(f5);
		SpineFront4.render(f5);
		SpineLow4.render(f5);
		HeadMid.render(f5);
		HeadLow1.render(f5);
		MouthLipBot.render(f5);
		HeadLeft2.render(f5);
		LeftEarLipBot.render(f5);
		MouthLipLeft.render(f5);
		MouthLipTop.render(f5);
		MouthBase.render(f5);
		MouthLipRight.render(f5);
		HeadRight1.render(f5);
		HeadRight2.render(f5);
		HeadBase.render(f5);
		RightEarLipBot.render(f5);
		RightEarLipBack.render(f5);
		RightEarLipTop.render(f5);
		RightEarBase.render(f5);
		LeftEarLipBack.render(f5);
		LeftEarLipTop.render(f5);
		LeftEarBase.render(f5);
		TailBaseTop.render(f5);
		SpineFront5.render(f5);
		SpineLow1.render(f5);
		BodyPart2.render(f5);
		SpineLow3.render(f5);
		BodyBotBase.render(f5);
		TailBaseMidHigh.render(f5);
		TailBase.render(f5);
		TailPart2.render(f5);
		SpineLow2.render(f5);
		BodyPart4.render(f5);
		BodyPart1.render(f5);
		BodyPart6.render(f5);
		BodyPart3.render(f5);
		BodyPartLast.render(f5);
		LeftArmBase.render(f5);
		TailBaseMidLow.render(f5);
		TailPart3.render(f5);
		TailPart1.render(f5);
		BodyPart5.render(f5);
		RightArmBase.render(f5);
		LeftArmLow.render(f5);
		LeftArmHigh.render(f5);
		RightArmHigh.render(f5);
		RightFingerLtl.render(f5);
		RightArmLow.render(f5);
		LeftFingerLtl.render(f5);
		RightFingerBig.render(f5);
		LeftFingerBig.render(f5);
	}


	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}


	@Override
	public void setRotationAngles(float f, float f1, float f2, float
			f3, float f4, float f5, Entity ent) {
		EntityDestrachan d = (EntityDestrachan) ent;

		// left is ranged, right is melee
		if (d.isRanged) {
			RightArmHigh.rotateAngleX = -0.2617994F;
			RightFingerLtl.rotateAngleX = -0.7853982F;
			RightArmLow.rotateAngleX = -0.7853982F;
			RightFingerBig.rotateAngleX = -0.7853982F;
			LeftArmLow.rotateAngleX = -0.7853982F;
			LeftArmHigh.rotateAngleX = -0.2617994F;
			LeftFingerLtl.rotateAngleX = -0.7853982F;
			LeftFingerBig.rotateAngleX = -0.7853982F;
		} else {
			RightArmHigh.rotateAngleX = -0.8726646F;
			RightFingerLtl.rotateAngleX = -1.22173F;
			RightArmLow.rotateAngleX = -1.22173F;
			RightFingerBig.rotateAngleX = -1.22173F;
			LeftArmLow.rotateAngleX = -1.22173F;
			LeftArmHigh.rotateAngleX = -0.8726646F;
			LeftFingerLtl.rotateAngleX = -1.22173F;
			LeftFingerBig.rotateAngleX = -1.22173F;
		}

		HeadBase.rotateAngleY = f3 / 57.29578F;
		HeadBase.rotateAngleX = f4 / 57.29578F;
		HeadLeft1.rotateAngleY = f3 / 57.29578F;
		HeadLeft1.rotateAngleX = (f4 / 57.29578F) / 4;
		HeadLeft2.rotateAngleY = f3 / 57.29578F;
		HeadLeft2.rotateAngleX = (f4 / 57.29578F) / 4;
		HeadRight1.rotateAngleY = f3 / 57.29578F;
		HeadRight1.rotateAngleX = (f4 / 57.29578F) / 4;
		HeadRight2.rotateAngleY = f3 / 57.29578F;
		HeadRight2.rotateAngleX = (f4 / 57.29578F) / 4;
		HeadMid.rotateAngleY = f3 / 57.29578F;
		HeadMid.rotateAngleX = 0.2094395F + (f4 / 57.29578F);
		HeadLow1.rotateAngleY = f3 / 57.29578F;
		HeadLow1.rotateAngleX = 0.3141593F + (f4 / 57.29578F);
		MouthBase.rotateAngleY = f3 / 57.29578F;
		MouthBase.rotateAngleX = f4 / 57.29578F;
		MouthLipTop.rotateAngleY = f3 / 57.29578F;
		MouthLipTop.rotateAngleX = f4 / 57.29578F;
		MouthLipBot.rotateAngleY = f3 / 57.29578F;
		MouthLipBot.rotateAngleX = f4 / 57.29578F;
		MouthLipLeft.rotateAngleY = f3 / 57.29578F;
		MouthLipLeft.rotateAngleX = f4 / 57.29578F;
		MouthLipRight.rotateAngleY = f3 / 57.29578F;
		MouthLipRight.rotateAngleX = f4 / 57.29578F;
		RightEarLipBot.rotateAngleY = 0.9599311F + ((f3 / 57.29578F) / 2);
		RightEarLipBot.rotateAngleX = f4 / 57.29578F / 2;
		RightEarLipTop.rotateAngleY = 0.9599311F + ((f3 / 57.29578F) / 2);
		RightEarLipTop.rotateAngleX = f4 / 57.29578F / 2;
		RightEarLipBack.rotateAngleY = 0.9599311F + ((f3 / 57.29578F) / 2);
		RightEarLipBack.rotateAngleX = f4 / 57.29578F / 2;
		RightEarBase.rotateAngleY = 0.9599311F + ((f3 / 57.29578F) / 2);
		RightEarBase.rotateAngleX = f4 / 57.29578F / 2;
		LeftEarLipBot.rotateAngleY = -0.9599311F - ((f3 / 57.29578F) / 2);
		LeftEarLipBot.rotateAngleX = f4 / 57.29578F / 2;
		LeftEarLipTop.rotateAngleY = -0.9599311F - ((f3 / 57.29578F) / 2);
		LeftEarLipTop.rotateAngleX = f4 / 57.29578F / 2;
		LeftEarLipBack.rotateAngleY = -0.9599311F - ((f3 / 57.29578F) / 2);
		LeftEarLipBack.rotateAngleX = f4 / 57.29578F / 2;
		LeftEarBase.rotateAngleY = -0.9599311F - ((f3 / 57.29578F) / 2);
		LeftEarBase.rotateAngleX = f4 / 57.29578F / 2;

		LeftFootBigClaw.rotateAngleX = 0 - Math.abs(MathHelper.cos(f * 0.5662F) * 1.4F * f1);
		LeftFoot.rotateAngleX = 0 - Math.abs(MathHelper.cos(f * 0.5662F) * 1.4F * f1);
		LeftFootLtlClaw.rotateAngleX = 0 - Math.abs(MathHelper.cos(f * 0.5662F) * 1.4F * f1);
		LeftLegLow.rotateAngleX = 0.7853982F - Math.abs(MathHelper.cos(f * 0.5662F) * 1.4F * f1);
		LeftLegMid.rotateAngleX = -0.7504916F - Math.abs(MathHelper.cos(f * 0.5662F) * 1.4F * f1);
		LeftLegCalf.rotateAngleX = -0.7504916F - Math.abs(MathHelper.cos(f * 0.5662F) * 1.4F * f1);
		LeftLegHighTop.rotateAngleX = 0.5235988F - Math.abs(MathHelper.cos(f * 0.5662F) * 1.4F * f1);

		RightFootBigClaw.rotateAngleX = 0 - Math.abs(MathHelper.sin(f * 0.5662F) * 1.4F * f1);
		RightFoot.rotateAngleX = 0 - Math.abs(MathHelper.sin(f * 0.5662F) * 1.4F * f1);
		RightFootLtlClaw.rotateAngleX = 0 - Math.abs(MathHelper.sin(f * 0.5662F) * 1.4F * f1);
		RightLegLow.rotateAngleX = 0.7853982F - Math.abs(MathHelper.sin(f * 0.5662F) * 1.4F * f1);
		RightLegMid.rotateAngleX = -0.7504916F - Math.abs(MathHelper.sin(f * 0.5662F) * 1.4F * f1);
		RightLegCalf.rotateAngleX = -0.7504916F - Math.abs(MathHelper.sin(f * 0.5662F) * 1.4F * f1);
		RightLegHighTop.rotateAngleX = 0.5235988F - Math.abs(MathHelper.sin(f * 0.5662F) * 1.4F * f1);
	}

}
