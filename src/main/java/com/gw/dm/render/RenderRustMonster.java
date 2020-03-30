package com.gw.dm.render;

import com.gw.dm.DungeonMobs;
import com.gw.dm.model.ModelRustMonster;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderRustMonster extends RenderLiving {
	protected static final ResourceLocation rustMonsterTexture = new ResourceLocation(DungeonMobs.MODID, "textures/entity/RustMonster.png");
	protected ModelRustMonster model;
	
	public RenderRustMonster(RenderManager manager, ModelRustMonster modelRM, float f) {
		super(manager, modelRM, f);
		model = modelRM;
	}
	
	// FIXME: Do I need to keep this?  I don't know what was planned for it!
	/*
	// Why is this here?
	// Because this is how you change a mob's size.
	// We'll use this later, but since I want to delete the stupid shit, 
	// this needs to be saved somewhere./
	protected void preRenderCallbackAirBoat(EntityAirBoat par1, float par2) {
		GL11.glScalef(2.0F, 2.0F, 2.0F);
	}
	*/
	
	@Override
	protected ResourceLocation getEntityTexture(Entity par1Entity) {
		return rustMonsterTexture;
	}
}