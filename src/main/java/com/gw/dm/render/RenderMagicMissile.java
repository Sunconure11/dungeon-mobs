package com.gw.dm.render;

import com.gw.dm.DungeonMobs;
import com.gw.dm.projectile.EntityMagicMissile;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderMagicMissile extends RenderArrow<EntityMagicMissile> {
	public static final ResourceLocation MAGIC_ARROW
			= new ResourceLocation(DungeonMobs.MODID, "textures/entity/magicmissile.png");

	public RenderMagicMissile(RenderManager renderManagerIn) {
		super(renderManagerIn);
	}

	@Override
	protected ResourceLocation getEntityTexture(EntityMagicMissile entity) {
		return MAGIC_ARROW;
	}

}
