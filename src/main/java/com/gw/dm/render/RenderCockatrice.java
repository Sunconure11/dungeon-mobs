package com.gw.dm.render;

import com.gw.dm.DungeonMobs;
import com.gw.dm.model.ModelCockatrice;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderCockatrice extends RenderLiving {
	private static final ResourceLocation cockatriceTextures = new ResourceLocation(DungeonMobs.MODID + ":textures/entity/Cockatrice.png");
	protected ModelCockatrice model;

	public RenderCockatrice(RenderManager manager, ModelCockatrice modelC, float f) {
		super(manager, modelC, f);
		model = (ModelCockatrice) mainModel;
	}


	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity) {
		return cockatriceTextures;
	}
}