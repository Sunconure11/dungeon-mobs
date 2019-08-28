package com.gw.dm.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

import com.gw.dm.DungeonMobs;
import com.gw.dm.entity.EntityGhost;
import com.gw.dm.entity.EntityVampire;

public class renderGhost extends RenderBiped<EntityGhost>  {
	public static final ResourceLocation VAMPIRE_TEXTURES =
			new ResourceLocation(DungeonMobs.MODID, "textures/entity/ghost.png");

	
	public renderGhost(RenderManager renderManagerIn, ModelBiped modelBipedIn,
			float shadowSize) {
		super(renderManagerIn, modelBipedIn, shadowSize);
	}

	
	@Override
	protected ResourceLocation getEntityTexture(EntityGhost entity) {
		return new ResourceLocation(DungeonMobs.MODID, "textures/entity/ghost.png");
	}
	
	
	@Override
	public void doRender(EntityGhost entity, double x, double y, double z,
			float entityYaw, float partialTicks) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 0.25F);
        GlStateManager.depthMask(false);
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, 
        		GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        GlStateManager.alphaFunc(516, 0.003921569F);
    
		super.doRender(entity, x, y, z, entityYaw, partialTicks);

        GlStateManager.enableLighting();
        GlStateManager.disableBlend();
        GlStateManager.alphaFunc(516, 0.1F);
        GlStateManager.depthMask(true);
	}

}
