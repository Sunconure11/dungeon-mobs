package com.gw.dm.potion;

import com.gw.dm.DungeonMobs;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PotionAddle extends Potion {
	private static final ResourceLocation addledIcon
			= new ResourceLocation(DungeonMobs.MODID + ":textures/gui/potionicons.png");

	public PotionAddle(int par1, boolean par2, int par3) {
		super(par2, par3);
		this.setIconIndex(0, 0);
		setRegistryName(DungeonMobs.MODID, "potionAddle");
		setPotionName("addled");
	}

	public PotionAddle(boolean par2, int par3) {
		// FIXME?: Not sure if this match up with older verions
		super(par2, par3);
		this.setIconIndex(0, 0);
		setRegistryName(DungeonMobs.MODID, "potionAddle");
		setPotionName("addled");
	}

	@Override
	public Potion setIconIndex(int par1, int par2) {
		super.setIconIndex(par1, par2);
		return this;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean hasStatusIcon() {
		ITextureObject texture = Minecraft.getMinecraft().renderEngine.getTexture(addledIcon);
		Minecraft.getMinecraft().renderEngine.bindTexture(addledIcon);
		return true;
	}
}
