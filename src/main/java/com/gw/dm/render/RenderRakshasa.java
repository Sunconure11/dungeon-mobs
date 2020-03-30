package com.gw.dm.render;

import com.gw.dm.DungeonMobs;
import com.gw.dm.entity.EntityRakshasa;
import com.gw.dm.model.ModelRakshasa;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderRakshasa extends RenderLiving {
	private static final ResourceLocation rakshasaTextures = new ResourceLocation(DungeonMobs.MODID, "textures/entity/Rakshasa.png");
	protected ModelRakshasa model;

	public RenderRakshasa(RenderManager manager, ModelRakshasa modelR, float f) {
		super(manager, modelR, f);
		model = (ModelRakshasa) mainModel;
	}


	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		if (entity instanceof EntityRakshasa) {
			return ((EntityRakshasa) entity).getTexture();
		}
		return rakshasaTextures;
	}
}

