package com.gw.dm.render;

import com.gw.dm.DungeonMobs;
import com.gw.dm.model.ModelGhoul;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderGhoul extends RenderLiving {
	private static final ResourceLocation ghoulTextures
			= new ResourceLocation(DungeonMobs.MODID, "textures/entity/ghoul.png");
	protected ModelGhoul model;

	public RenderGhoul(RenderManager renderManager, ModelGhoul modelG, float f) {
		super(renderManager, modelG, f);
		mainModel = model = modelG;
	}


	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity) {
		return ghoulTextures;
	}
}