package com.gw.dm.render;

import com.gw.dm.DungeonMobs;
import com.gw.dm.model.ModelManticore;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderManticore extends RenderLiving {
	private static final ResourceLocation manticoreTextures
			= new ResourceLocation(DungeonMobs.MODID + ":textures/entity/Manticore.png");
	protected ModelManticore model;

	public RenderManticore(RenderManager manager, ModelManticore modelM, float f) {
		super(manager, modelM, f);
		model = (ModelManticore) mainModel;
	}


	protected ResourceLocation getEntityTexture(Entity par1Entity) {
		return manticoreTextures;
	}
}
