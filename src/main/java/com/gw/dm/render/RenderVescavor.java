package com.gw.dm.render;

import com.gw.dm.DungeonMobs;
import com.gw.dm.model.ModelVescavor;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderVescavor extends RenderLiving {
	private static final ResourceLocation vescavorTextures
			= new ResourceLocation(DungeonMobs.MODID, "textures/entity/Vescavor.png");
	protected ModelVescavor model;


	public RenderVescavor(RenderManager manager, ModelVescavor modelV, float f) {
		super(manager, modelV, f);
		model = (ModelVescavor) mainModel;
	}


	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity) {
		return vescavorTextures;
	}
}