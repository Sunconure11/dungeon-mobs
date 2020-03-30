package com.gw.dm.render;

import com.gw.dm.DungeonMobs;
import com.gw.dm.model.ModelTroll;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderTroll extends RenderLiving {

	private static final ResourceLocation trollTextures = new ResourceLocation(DungeonMobs.MODID, "textures/entity/Troll.png");
	protected ModelTroll model;

	public RenderTroll(RenderManager renderManager, ModelTroll modelT, float f) {
		super(renderManager, modelT, f);
		model = (ModelTroll) mainModel;
	}


	protected ResourceLocation getEntityTexture(Entity par1Entity) {
		return trollTextures;
	}
}