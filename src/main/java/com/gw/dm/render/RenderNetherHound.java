package com.gw.dm.render;

import com.gw.dm.DungeonMobs;
import com.gw.dm.model.ModelNetherHound;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;


public class RenderNetherHound extends RenderLiving {
	private static final ResourceLocation hellHoundTextures
			= new ResourceLocation(DungeonMobs.MODID, "textures/entity/HellHound.png");
	protected ModelNetherHound model;


	public RenderNetherHound(RenderManager manager, ModelNetherHound modelNH, float f) {
		super(manager, modelNH, f);
		model = (ModelNetherHound) mainModel;
	}


	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity) {
		return hellHoundTextures;
	}
}
