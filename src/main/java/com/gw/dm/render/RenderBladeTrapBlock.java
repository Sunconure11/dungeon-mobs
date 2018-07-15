package com.gw.dm.render;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.util.ResourceLocation;

import com.gw.dm.DungeonMobs;
import com.gw.dm.blocks.TileEntityBladeTrap;
import com.gw.dm.model.ModelBladeTrap;

public class RenderBladeTrapBlock extends TileEntitySpecialRenderer<TileEntityBladeTrap> {
	private static final ResourceLocation bladeTrapTextures 
			= new ResourceLocation(DungeonMobs.MODID, "textures/entity/BladeTrap.png");
	private static final ModelBladeTrap model = new ModelBladeTrap();
	
	
	@Override
	public void render(TileEntityBladeTrap te, double x, double y, double z,
			float partialTicks, int destroyStage, float alpha) {
		
		//System.out.println("Rendering Bladetrap Block!!!!");
		
		bindTexture(bladeTrapTextures);
		
		GlStateManager.pushMatrix();
		GlStateManager.enableRescaleNormal();
		
		// Don't know if I need this yet...
		//GlStateManager.translate(0.5F, 0.5F, 0.5F);		
		//GlStateManager.rotate(0.0f, 0.0F, 1.0F, 0.0F);
		//GlStateManager.translate(-0.5F, -0.5F, -0.5F);
		
		GlStateManager.translate(0.5F, -0.5F, 0.5F); // DO I need this?
		model.renderAllTE(1.0f); // FIXME / TODO: What is the correct scale value?		

		GlStateManager.disableRescaleNormal();
		GlStateManager.popMatrix();
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
		
	}

}
