package com.gw.dm.render;

import com.gw.dm.DungeonMobs;
import com.gw.dm.model.ModelThoqqua;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderThoqqua extends RenderLiving {
	private static final ResourceLocation thoqquaTextures
			= new ResourceLocation(DungeonMobs.MODID, "textures/entity/Thoqqua.png");
	protected ModelThoqqua model;

	public RenderThoqqua(RenderManager manager, ModelThoqqua modelT, float f) {
		super(manager, modelT, f);
		model = (ModelThoqqua) mainModel;
	}


	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity) {
		return thoqquaTextures;
	}
}
