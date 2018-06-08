package com.gw.dm.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelBeamos extends ModelBase
{
	ModelRenderer BaseBottom;
	ModelRenderer BaseMid;
	ModelRenderer BaseTop;
	ModelRenderer EyeBase;
	ModelRenderer Eye;

	public boolean isAttacking;
	
	public ModelBeamos()
	{
		textureWidth = 64;
		textureHeight = 32;
    
		BaseBottom = new ModelRenderer(this, 0, 0);
		BaseBottom.addBox(-8F, 0F, -8F, 16, 2, 16);
		BaseBottom.setRotationPoint(0F, 22F, 0F);
		BaseBottom.setTextureSize(64, 32);
		BaseBottom.mirror = true;
		setRotation(BaseBottom, 0F, 0F, 0F);
		BaseMid = new ModelRenderer(this, 0, 0);
		BaseMid.addBox(-7F, 0F, -7F, 14, 2, 14);
		BaseMid.setRotationPoint(0F, 20F, 0F);
		BaseMid.setTextureSize(64, 32);
		BaseMid.mirror = true;
		setRotation(BaseMid, 0F, 0F, 0F);
		BaseTop = new ModelRenderer(this, 0, 0);
		BaseTop.addBox(-6F, 0F, -6F, 12, 2, 12);
		BaseTop.setRotationPoint(0F, 18F, 0F);
		BaseTop.setTextureSize(64, 32);
		BaseTop.mirror = true;
		setRotation(BaseTop, 0F, 0F, 0F);
		EyeBase = new ModelRenderer(this, 19, 18);
		EyeBase.addBox(-3F, -4F, -3F, 6, 8, 6);
		EyeBase.setRotationPoint(0F, 14F, 0F);
		EyeBase.setTextureSize(64, 32);
		EyeBase.mirror = true;
		setRotation(EyeBase, 0F, 0F, 0F);
		Eye = new ModelRenderer(this, 0, 22);
		Eye.addBox(-2F, -3F, -4.5F, 4, 6, 4);
		Eye.setRotationPoint(0F, 14F, 0F);
		Eye.setTextureSize(64, 32);
		Eye.mirror = true;
		setRotation(Eye, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
	{
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5);
		BaseBottom.render(f5);
		BaseMid.render(f5);
		BaseTop.render(f5);
		EyeBase.render(f5);
		Eye.render(f5);
	}
	
	public void renderModel(float f5)
	{
		//super.render(entity, f, f1, f2, f3, f4, f5);
		//setRotationAngles(f, f1, f2, f3, f4, f5);
		BaseBottom.render(f5);
		BaseMid.render(f5);
		BaseTop.render(f5);
		EyeBase.render(f5);
		Eye.render(f5);
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
		
		if(!this.isAttacking)
			this.Eye.rotateAngleY = f;
	}
}