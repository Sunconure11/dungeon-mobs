package com.gw.dm.render;

import com.gw.dm.DungeonMobs;
import com.gw.dm.entity.EntityIllithid;
import com.gw.dm.model.ModelIllithid;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderIllithid extends RenderLiving
{
	protected ModelIllithid model;
	
	public RenderIllithid(ModelIllithid modelI, float f)
	{
		super(modelI, f);
		model = (ModelIllithid)mainModel;
	}
	
	public void renderIllithid(EntityIllithid entity, double par2, double par4, double par6, float par8, float par9)
	{
		super.doRender(entity, par2, par4, par6, par8, par9);
	}
	
	public void doRenderLiving(EntityLiving par1, double par2, double par4, double par6, float par8, float par9)
	{
		renderIllithid((EntityIllithid)par1, par2, par4, par6, par8, par9);
	}
	
	public void doRender(Entity par1, double par2, double par4, double par6, float par8, float par9)
	{
		renderIllithid((EntityIllithid)par1, par2, par4, par6, par8, par9);
	}
	
private static final ResourceLocation illithidTextures = new ResourceLocation(DungeonMobs.MODID + ":textures/entity/Illithid.png");
	
	protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
		return illithidTextures;
    }
}
