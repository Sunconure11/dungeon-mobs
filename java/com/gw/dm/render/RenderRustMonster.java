package com.gw.dm.render;

import org.lwjgl.opengl.GL11;

import com.gw.dm.DungeonMobs;
import com.gw.dm.entity.EntityRustMonster;
import com.gw.dm.model.ModelRustMonster;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

public class RenderRustMonster extends RenderLiving
{
  protected ModelRustMonster model;
  protected static final ResourceLocation rustMonsterTexture = new ResourceLocation(DungeonMobs.MODID + ":textures/entity/RustMonster.png");

  public RenderRustMonster(ModelRustMonster modelRM, float f)
  {
    super(modelRM, f);
    this.model = ((ModelRustMonster)this.mainModel);
  }

  /*
   * Why is this here?
   * Because this is how you change a mob's size.
   * We'll use this later, but since I want to delete the stupid shit, this needs to be saved somewhere.
   * 
  protected void preRenderCallbackAirBoat(EntityAirBoat par1, float par2)
	{
	    GL11.glScalef(2.0F, 2.0F, 2.0F);
	}
	*/
  
  public void renderRustMonster(EntityRustMonster entity, double par2, double par4, double par6, float par8, float par9)
  {
    super.doRender(entity, par2, par4, par6, par8, par9);
  }

  public void doRenderLiving(EntityLiving par1, double par2, double par4, double par6, float par8, float par9)
  {
    renderRustMonster((EntityRustMonster)par1, par2, par4, par6, par8, par9);
  }

  public void doRender(Entity par1, double par2, double par4, double par6, float par8, float par9)
  {
    renderRustMonster((EntityRustMonster)par1, par2, par4, par6, par8, par9);
  }

  protected ResourceLocation getEntityTexture(Entity par1Entity)
  {
    return rustMonsterTexture;
  }
}