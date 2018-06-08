package com.gw.dm.model;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelGhoul extends ModelBiped
{
	public ModelGhoul()
	{
		super(0.0F);
		
		this.bipedHead = null;
		this.bipedHead = new ModelRenderer(this, 0, 0);
        this.bipedHead.addBox(-4.0F, -4.0F, -7.0F, 8, 8, 8, 0.0F);
        this.bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);
	}
}
