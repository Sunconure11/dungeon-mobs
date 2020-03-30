package com.gw.dm.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelBladeTrap extends ModelBase {
	
	ModelRenderer Base;
	ModelRenderer BotSpikeB;
	ModelRenderer BotSpikeC;
	ModelRenderer BotSpikeD;
	ModelRenderer BotSpikeA;
	ModelRenderer TopSpikeA;
	ModelRenderer TopSpikeC;
	ModelRenderer TopSpikeD;
	ModelRenderer TopSpikeB;
	ModelRenderer RerSpikeB;
	ModelRenderer RerSpikeC;
	ModelRenderer RerSpikeD;
	ModelRenderer RerSpikeA;
	ModelRenderer FrnSpikeA;
	ModelRenderer FrnSpikeC;
	ModelRenderer FrnSpikeD;
	ModelRenderer FrnSpikeB;
	ModelRenderer LftSpikeB;
	ModelRenderer LftSpikeC;
	ModelRenderer LftSpikeD;
	ModelRenderer LftSpikeA;
	ModelRenderer RitSpikeD;
	ModelRenderer RitSpikeC;
	ModelRenderer RitSpikeA;
	ModelRenderer RitSpikeB;
	
	
	public ModelBladeTrap() {
		textureWidth = 64;
		textureHeight = 32;
		
		Base = new ModelRenderer(this, 0, 0);
		Base.addBox(-2.5F, -2.5F, -2.5F, 5, 5, 5);
		Base.setRotationPoint(0F, 16.5F, 0F);
		Base.setTextureSize(64, 32);
		Base.mirror = true;
		setRotation(Base, 0F, 0F, 0F);
		
		BotSpikeB = new ModelRenderer(this, 2, 12);
		BotSpikeB.addBox(-0.5F, 0F, -0.5F, 1, 4, 1);
		BotSpikeB.setRotationPoint(1.5F, 19F, -1.5F);
		BotSpikeB.setTextureSize(64, 32);
		BotSpikeB.mirror = true;
		setRotation(BotSpikeB, 0F, 0F, 0F);
		
		BotSpikeC = new ModelRenderer(this, 2, 12);
		BotSpikeC.addBox(-0.5F, 0F, -0.5F, 1, 4, 1);
		BotSpikeC.setRotationPoint(-1.5F, 19F, 1.5F);
		BotSpikeC.setTextureSize(64, 32);
		BotSpikeC.mirror = true;
		setRotation(BotSpikeC, 0F, 0F, 0F);
		
		BotSpikeD = new ModelRenderer(this, 2, 12);
		BotSpikeD.addBox(-0.5F, 0F, -0.5F, 1, 4, 1);
		BotSpikeD.setRotationPoint(-1.5F, 19F, -1.5F);
		BotSpikeD.setTextureSize(64, 32);
		BotSpikeD.mirror = true;
		setRotation(BotSpikeD, 0F, 0F, 0F);
		
		BotSpikeA = new ModelRenderer(this, 2, 12);
		BotSpikeA.addBox(-0.5F, 0F, -0.5F, 1, 4, 1);
		BotSpikeA.setRotationPoint(1.5F, 19F, 1.5F);
		BotSpikeA.setTextureSize(64, 32);
		BotSpikeA.mirror = true;
		setRotation(BotSpikeA, 0F, 0F, 0F);
		
		TopSpikeA = new ModelRenderer(this, 0, 11);
		TopSpikeA.addBox(-0.5F, -4F, -0.5F, 1, 4, 1);
		TopSpikeA.setRotationPoint(1.5F, 14F, 1.5F);
		TopSpikeA.setTextureSize(64, 32);
		TopSpikeA.mirror = true;
		setRotation(TopSpikeA, 0F, 0F, 0F);
		
		TopSpikeC = new ModelRenderer(this, 0, 11);
		TopSpikeC.addBox(-0.5F, -4F, -0.5F, 1, 4, 1);
		TopSpikeC.setRotationPoint(-1.5F, 14F, 1.5F);
		TopSpikeC.setTextureSize(64, 32);
		TopSpikeC.mirror = true;
		setRotation(TopSpikeC, 0F, 0F, 0F);
		
		TopSpikeD = new ModelRenderer(this, 0, 11);
		TopSpikeD.addBox(-0.5F, -4F, -0.5F, 1, 4, 1);
		TopSpikeD.setRotationPoint(-1.5F, 14F, -1.5F);
		TopSpikeD.setTextureSize(64, 32);
		TopSpikeD.mirror = true;
		setRotation(TopSpikeD, 0F, 0F, 0F);
		
		TopSpikeB = new ModelRenderer(this, 0, 11);
		TopSpikeB.addBox(-0.5F, -4F, -0.5F, 1, 4, 1);
		TopSpikeB.setRotationPoint(1.5F, 14F, -1.5F);
		TopSpikeB.setTextureSize(64, 32);
		TopSpikeB.mirror = true;
		setRotation(TopSpikeB, 0F, 0F, 0F);
		
		RerSpikeB = new ModelRenderer(this, 2, 12);
		RerSpikeB.addBox(-4F, -0.5F, -0.5F, 4, 1, 1);
		RerSpikeB.setRotationPoint(-2F, 18F, 1.5F);
		RerSpikeB.setTextureSize(64, 32);
		RerSpikeB.mirror = true;
		setRotation(RerSpikeB, 0F, 0F, 0F);
		
		RerSpikeC = new ModelRenderer(this, 2, 12);
		RerSpikeC.addBox(-4F, -0.5F, -0.5F, 4, 1, 1);
		RerSpikeC.setRotationPoint(-2F, 15F, -1.5F);
		RerSpikeC.setTextureSize(64, 32);
		RerSpikeC.mirror = true;
		setRotation(RerSpikeC, 0F, 0F, 0F);
		
		RerSpikeD = new ModelRenderer(this, 2, 12);
		RerSpikeD.addBox(-4F, -0.5F, -0.5F, 4, 1, 1);
		RerSpikeD.setRotationPoint(-2F, 18F, -1.5F);
		RerSpikeD.setTextureSize(64, 32);
		RerSpikeD.mirror = true;
		setRotation(RerSpikeD, 0F, 0F, 0F);
		
		RerSpikeA = new ModelRenderer(this, 2, 12);
		RerSpikeA.addBox(-4F, -0.5F, -0.5F, 4, 1, 1);
		RerSpikeA.setRotationPoint(-2F, 15F, 1.5F);
		RerSpikeA.setTextureSize(64, 32);
		RerSpikeA.mirror = true;
		setRotation(RerSpikeA, 0F, 0F, 0F);
		
		FrnSpikeA = new ModelRenderer(this, 2, 12);
		FrnSpikeA.addBox(0F, -0.5F, -0.5F, 4, 1, 1);
		FrnSpikeA.setRotationPoint(2F, 15F, 1.5F);
		FrnSpikeA.setTextureSize(64, 32);
		FrnSpikeA.mirror = true;
		setRotation(FrnSpikeA, 0F, 0F, 0F);
		
		FrnSpikeC = new ModelRenderer(this, 2, 12);
		FrnSpikeC.addBox(0F, -0.5F, -0.5F, 4, 1, 1);
		FrnSpikeC.setRotationPoint(2F, 15F, -1.5F);
		FrnSpikeC.setTextureSize(64, 32);
		FrnSpikeC.mirror = true;
		setRotation(FrnSpikeC, 0F, 0F, 0F);
		
		FrnSpikeD = new ModelRenderer(this, 2, 12);
		FrnSpikeD.addBox(0F, -0.5F, -0.5F, 4, 1, 1);
		FrnSpikeD.setRotationPoint(2F, 18F, -1.5F);
		FrnSpikeD.setTextureSize(64, 32);
		FrnSpikeD.mirror = true;
		setRotation(FrnSpikeD, 0F, 0F, 0F);
		
		FrnSpikeB = new ModelRenderer(this, 2, 12);
		FrnSpikeB.addBox(0F, -0.5F, -0.5F, 4, 1, 1);
		FrnSpikeB.setRotationPoint(2F, 18F, 1.5F);
		FrnSpikeB.setTextureSize(64, 32);
		FrnSpikeB.mirror = true;
		setRotation(FrnSpikeB, 0F, 0F, 0F);
		
		LftSpikeB = new ModelRenderer(this, 2, 12);
		LftSpikeB.addBox(-0.5F, -0.5F, -4F, 1, 1, 4);
		LftSpikeB.setRotationPoint(1.5F, 15F, -2F);
		LftSpikeB.setTextureSize(64, 32);
		LftSpikeB.mirror = true;
		setRotation(LftSpikeB, 0F, 0F, 0F);
		
		LftSpikeC = new ModelRenderer(this, 2, 12);
		LftSpikeC.addBox(-0.5F, -0.5F, -4F, 1, 1, 4);
		LftSpikeC.setRotationPoint(-1.5F, 18F, -2F);
		LftSpikeC.setTextureSize(64, 32);
		LftSpikeC.mirror = true;
		setRotation(LftSpikeC, 0F, 0F, 0F);
		
		LftSpikeD = new ModelRenderer(this, 2, 12);
		LftSpikeD.addBox(-0.5F, -0.5F, -4F, 1, 1, 4);
		LftSpikeD.setRotationPoint(1.5F, 18F, -2F);
		LftSpikeD.setTextureSize(64, 32);
		LftSpikeD.mirror = true;
		setRotation(LftSpikeD, 0F, 0F, 0F);
		
		LftSpikeA = new ModelRenderer(this, 2, 12);
		LftSpikeA.addBox(-0.5F, -0.5F, -4F, 1, 1, 4);
		LftSpikeA.setRotationPoint(-1.5F, 15F, -2F);
		LftSpikeA.setTextureSize(64, 32);
		LftSpikeA.mirror = true;
		setRotation(LftSpikeA, 0F, 0F, 0F);
		
		RitSpikeD = new ModelRenderer(this, 2, 12);
		RitSpikeD.addBox(-0.5F, -0.5F, 0F, 1, 1, 4);
		RitSpikeD.setRotationPoint(1.5F, 18F, 2F);
		RitSpikeD.setTextureSize(64, 32);
		RitSpikeD.mirror = true;
		setRotation(RitSpikeD, 0F, 0F, 0F);
		
		RitSpikeC = new ModelRenderer(this, 2, 12);
		RitSpikeC.addBox(-0.5F, -0.5F, 0F, 1, 1, 4);
		RitSpikeC.setRotationPoint(-1.5F, 18F, 2F);
		RitSpikeC.setTextureSize(64, 32);
		RitSpikeC.mirror = true;
		setRotation(RitSpikeC, 0F, 0F, 0F);
		
		RitSpikeA = new ModelRenderer(this, 2, 12);
		RitSpikeA.addBox(-0.5F, -0.5F, 0F, 1, 1, 4);
		RitSpikeA.setRotationPoint(-1.5F, 15F, 2F);
		RitSpikeA.setTextureSize(64, 32);
		RitSpikeA.mirror = true;
		setRotation(RitSpikeA, 0F, 0F, 0F);
		
		RitSpikeB = new ModelRenderer(this, 2, 12);
		RitSpikeB.addBox(-0.5F, -0.5F, 0F, 1, 1, 4);
		RitSpikeB.setRotationPoint(1.5F, 15F, 2F);
		RitSpikeB.setTextureSize(64, 32);
		RitSpikeB.mirror = true;
		setRotation(RitSpikeB, 0F, 0F, 0F);
	}
	
	
	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		super.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
		setRotationAngles(limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale, entity);
		Base.render(scale);
		BotSpikeB.render(scale);
		BotSpikeC.render(scale);
		BotSpikeD.render(scale);
		BotSpikeA.render(scale);
		TopSpikeA.render(scale);
		TopSpikeC.render(scale);
		TopSpikeD.render(scale);
		TopSpikeB.render(scale);
		RerSpikeB.render(scale);
		RerSpikeC.render(scale);
		RerSpikeD.render(scale);
		RerSpikeA.render(scale);
		FrnSpikeA.render(scale);
		FrnSpikeC.render(scale);
		FrnSpikeD.render(scale);
		FrnSpikeB.render(scale);
		LftSpikeB.render(scale);
		LftSpikeC.render(scale);
		LftSpikeD.render(scale);
		LftSpikeA.render(scale);
		RitSpikeD.render(scale);
		RitSpikeC.render(scale);
		RitSpikeA.render(scale);
		RitSpikeB.render(scale);
	}
	
	@Override
	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity ent) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, ent);
	}
	
	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
	
	public void renderAllTE(float scale) {
		Base.render(scale);
		BotSpikeB.render(scale);
		BotSpikeC.render(scale);
		BotSpikeD.render(scale);
		BotSpikeA.render(scale);
		TopSpikeA.render(scale);
		TopSpikeC.render(scale);
		TopSpikeD.render(scale);
		TopSpikeB.render(scale);
		RerSpikeB.render(scale);
		RerSpikeC.render(scale);
		RerSpikeD.render(scale);
		RerSpikeA.render(scale);
		FrnSpikeA.render(scale);
		FrnSpikeC.render(scale);
		FrnSpikeD.render(scale);
		FrnSpikeB.render(scale);
		LftSpikeB.render(scale);
		LftSpikeC.render(scale);
		LftSpikeD.render(scale);
		LftSpikeA.render(scale);
		RitSpikeD.render(scale);
		RitSpikeC.render(scale);
		RitSpikeA.render(scale);
		RitSpikeB.render(scale);
	}
	
}
