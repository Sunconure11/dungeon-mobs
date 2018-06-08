package com.gw.dm.render;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;

import com.gw.dm.blocks.TileEntityBeamos;
import com.gw.dm.model.ModelBeamos;

public class RenderBeamosTile extends TileEntitySpecialRenderer 
{
	private ModelBeamos modelBeamos;
	private static final ResourceLocation beamosTexture = new ResourceLocation("dungeonmobs:textures/models/Beamos.png");
	
	public RenderBeamosTile()
	{
		this.modelBeamos = new ModelBeamos();
	}
	
	@Override
	public void renderTileEntityAt(TileEntity p_147500_1_, double p_147500_2_, double p_147500_4_, double p_147500_6_, float p_147500_8_) 
	{
		GL11.glPushMatrix();
		modelBeamos.renderModel(1.0F);
		GL11.glPopMatrix();
	}
	
	public void doRender(TileEntity beamos, double d1, double d2, double d3, float f1)
    {
		if(beamos instanceof TileEntityBeamos)
		{
			TileEntityBeamos foo = (TileEntityBeamos)beamos;
			this.modelBeamos.isAttacking = foo.isAiming;
		}
		
		renderTileEntityAt((TileEntityBeamos)beamos, d1, d2, d3, f1);
    }
	
	protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
		return beamosTexture;
    }
}
