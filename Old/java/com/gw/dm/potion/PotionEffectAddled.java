package com.gw.dm.potion;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class PotionEffectAddled extends PotionEffect
{

	public PotionEffectAddled(int p_i1574_1_, int p_i1574_2_) 
	{
		super(p_i1574_1_, p_i1574_2_);
	}
	
	public PotionEffectAddled(int p_i1575_1_, int p_i1575_2_, int p_i1575_3_)
    {
        super(p_i1575_1_, p_i1575_2_, p_i1575_3_, false);
    }
}
