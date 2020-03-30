package com.gw.dm.render;

import com.gw.dm.DungeonMobs;
import com.gw.dm.model.ModelShrieker;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderShrieker extends RenderLiving {
	private static final ResourceLocation shriekerTextures = new ResourceLocation(DungeonMobs.MODID, "textures/entity/Shrieker.png");
	protected ModelShrieker model;


	public RenderShrieker(RenderManager manager, ModelShrieker modelS, float f) {
		super(manager, modelS, f);
		model = (ModelShrieker) mainModel;
	}


	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity) {
		return shriekerTextures;
	}
}