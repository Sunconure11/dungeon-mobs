package com.gw.dm.render;

import com.gw.dm.DungeonMobs;
import com.gw.dm.model.ModelDestrachan;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderDestrachan extends RenderLiving {
	private static final ResourceLocation destrachanTextures = new ResourceLocation(DungeonMobs.MODID, "textures/entity/Destrachan.png");
	protected ModelDestrachan model;
	
	
	public RenderDestrachan(RenderManager manager, ModelDestrachan modelD, float f) {
		super(manager, modelD, f);
		model = (ModelDestrachan) mainModel;
	}
	
	
	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity) {
		return destrachanTextures;
	}
}