package com.gw.dm.potion;

import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class PotionEffectAddled extends PotionEffect {

	public PotionEffectAddled(Potion potionIn, int durationIn) {
		super(potionIn, durationIn);
	}
	
	public PotionEffectAddled(Potion potionIn, int durationIn, int amplifierIn) {
        super(potionIn, durationIn, amplifierIn, false, false);
    }
}
