package com.gw.dm.render;

import com.gw.dm.DungeonMobs;
import com.gw.dm.model.ModelIllithid;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderIllithid extends RenderLiving {

	private static final ResourceLocation illithidTextures
			= new ResourceLocation(DungeonMobs.MODID, "textures/entity/Illithid.png");
	protected ModelIllithid model;


	public RenderIllithid(RenderManager manager, ModelIllithid modelI, float f) {
		super(manager, modelI, f);
		model = (ModelIllithid) mainModel;
	}


	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity) {
		return illithidTextures;
	}
}
