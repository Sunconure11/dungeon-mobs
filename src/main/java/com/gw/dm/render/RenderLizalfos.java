package com.gw.dm.render;

import com.gw.dm.DungeonMobs;
import com.gw.dm.model.ModelLizalfos;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderLizalfos extends RenderLiving {
	private static final ResourceLocation lizalfosTextures
			= new ResourceLocation(DungeonMobs.MODID, "textures/entity/Lizalfos.png");
	protected ModelLizalfos model;

	public RenderLizalfos(RenderManager manager, ModelLizalfos modelL, float f) {
		super(manager, modelL, f);
		model = (ModelLizalfos) mainModel;
	}


	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity) {
		return lizalfosTextures;
	}
}
