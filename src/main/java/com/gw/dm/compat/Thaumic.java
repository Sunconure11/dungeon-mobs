package com.gw.dm.compat;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.AspectRegistryEvent;

import com.gw.dm.util.MiscRegistrar;


// The model code I was shown for this lacks the annotation, 
// yet everything I've been told says you need it.
@Mod.EventBusSubscriber(modid="dungeonmobs")
public class Thaumic {

	// Hopefully this will work and allow this to still work without 
	// Thaumcraft!  Otherwise this cannot stay.
	@Optional.Method(modid = "thaumcraft")
	@SuppressWarnings("deprecation")
	@SubscribeEvent
	public static void aspectRegistrationEvent(AspectRegistryEvent evt) {

		ThaumcraftApi.registerEntityTag("dungeonmobs.dmghoul", 
				new AspectList().add(Aspect.UNDEAD, 10)
						 		.add(Aspect.MAN, 10)
						 		.add(Aspect.EARTH, 5));

		ThaumcraftApi.registerEntityTag("dungeonmobs.dmbeholder", 
				new AspectList().add(Aspect.MAGIC, 25)
						 		.add(Aspect.ELDRITCH, 20)
						 		.add(Aspect.AVERSION, 20)
						 		.add(Aspect.SENSES, 10));

		ThaumcraftApi.registerEntityTag("dungeonmobs.dmtroll", 
				new AspectList().add(Aspect.MAGIC, 15)
						 		.add(Aspect.LIFE, 10)
						 		.add(Aspect.DARKNESS, 5));

		ThaumcraftApi.registerEntityTag("dungeonmobs.dmmanticore", 
				new AspectList().add(Aspect.BEAST, 15)
						 		.add(Aspect.AVERSION, 15)
						 		.add(Aspect.AIR, 5));

		ThaumcraftApi.registerEntityTag("dungeonmobs.dmrevenant", 
				new AspectList().add(Aspect.SOUL, 15)
						 		.add(Aspect.UNDEAD, 20)
						 		.add(Aspect.ENTROPY, 10));

		ThaumcraftApi.registerEntityTag("dungeonmobs.dmvampire", 
				new AspectList().add(Aspect.MAN, 20)
						 		.add(Aspect.UNDEAD, 20)
						 		.add(Aspect.DARKNESS, 10));

		ThaumcraftApi.registerEntityTag("dungeonmobs.dmhookhorror", 
				new AspectList().add(Aspect.ELDRITCH, 15)
						 		.add(Aspect.AVERSION, 20)
						 		.add(Aspect.TRAP, 5));

		ThaumcraftApi.registerEntityTag("dungeonmobs.dmdestrachan", 
				new AspectList().add(Aspect.SENSES, 15)
						 		.add(Aspect.BEAST, 10)
						 		.add(Aspect.DARKNESS, 5)
						 		.add(Aspect.AVERSION, 10));

		ThaumcraftApi.registerEntityTag("dungeonmobs.dmcavefisher", 
				new AspectList().add(Aspect.BEAST, 10)
						 		.add(Aspect.TRAP, 25)
						 		.add(Aspect.AVERSION, 10));

		ThaumcraftApi.registerEntityTag("dungeonmobs.dmrustmonster", 
				new AspectList().add(Aspect.BEAST, 10)
						 		.add(Aspect.METAL, 20)
						 		.add(Aspect.ENTROPY, 20));

		ThaumcraftApi.registerEntityTag("dungeonmobs.dmumberhulk", 
				new AspectList().add(Aspect.BEAST, 20)
						 		.add(Aspect.AVERSION, 25)
						 		.add(Aspect.SENSES, 10));

		ThaumcraftApi.registerEntityTag("dungeonmobs.dmcockatrice", 
				new AspectList().add(Aspect.BEAST, 15)
						 		.add(Aspect.AVERSION, 15)
						 		.add(Aspect.EARTH, 25));

		ThaumcraftApi.registerEntityTag("dungeonmobs.dmshrieker", 
				new AspectList().add(Aspect.PLANT, 15)
						 		.add(Aspect.SENSES, 10));

		ThaumcraftApi.registerEntityTag("dungeonmobs.dmthoqqua", 
				new AspectList().add(Aspect.FIRE, 30)
						 		.add(Aspect.EARTH, 20)
						 		.add(Aspect.BEAST, 10));

		ThaumcraftApi.registerEntityTag("dungeonmobs.dmvescavor", 
				new AspectList().add(Aspect.ELDRITCH, 20)
						 		.add(Aspect.SENSES, 20)
						 		.add(Aspect.MIND, 10));

		ThaumcraftApi.registerEntityTag("dungeonmobs.dmillithid", 
				new AspectList().add(Aspect.ELDRITCH, 20)
						 		.add(Aspect.MIND, 25)
						 		.add(Aspect.AVERSION, 10));

		ThaumcraftApi.registerEntityTag("dungeonmobs.dmlizalfos", 
				new AspectList().add(Aspect.BEAST, 15)
						 		.add(Aspect.MAN, 15)
						 		.add(Aspect.WATER, 10));

		ThaumcraftApi.registerEntityTag("dungeonmobs.dmeldermob", 
				new AspectList().add(Aspect.ELDRITCH, 25)
						 		.add(Aspect.MAGIC, 20)
						 		.add(Aspect.SENSES, 10)
						 		.add(Aspect.WATER, 10));

		ThaumcraftApi.registerEntityTag("dungeonmobs.dmrakshasaImage", 
				new AspectList().add(Aspect.SENSES, 5)
						 		.add(Aspect.MAGIC, 5));
		
		
		// Demons -- perhaps different of if Bewitchment is available
		ThaumcraftApi.registerEntityTag("dungeonmobs.dmrakshasa", 
				new AspectList().add(Aspect.SOUL, 25)
						 		.add(Aspect.MAGIC, 20)
						 		.add(Aspect.AVERSION, 25)
						 		.add(Aspect.MAN, 8)
						 		.add(Aspect.BEAST, 8));

		ThaumcraftApi.registerEntityTag("dungeonmobs.dmnetherhound", 
				new AspectList().add(Aspect.BEAST, 15)
						 		.add(Aspect.FIRE, 25)
						 		.add(Aspect.AVERSION, 20));

		ThaumcraftApi.registerEntityTag("dungeonmobs.dmfallenangel", 
				new AspectList().add(Aspect.SOUL, 25)
						 		.add(Aspect.LIGHT, 20)
						 		.add(Aspect.AVERSION, 20)
						 		.add(Aspect.FLIGHT, 20));
		
		// Blocks
		evt.register.registerObjectTag(new ItemStack(MiscRegistrar.blockBladeTrap), 
				new AspectList().add(Aspect.MECHANISM, 20)
				                .add(Aspect.METAL, 10)
				                .add(Aspect.AVERSION, 10));
		
		evt.register.registerObjectTag(new ItemStack(MiscRegistrar.blockLavarock), 
				new AspectList().add(Aspect.FIRE, 5)
				                .add(Aspect.EARTH, 5));
	}
	
	
	
}
