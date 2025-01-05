package com.raremoon.listeners;

import com.raremoon.config.RareMoonConfigCommon;
import com.raremoon.world.level.saveddata.RareMoonOverworldExtension;

import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@Mod.EventBusSubscriber(bus = EventBusSubscriber.Bus.FORGE)
public class LivingHurtEventListener {

	@SubscribeEvent
	public static void onLivingHurtEvent(final LivingHurtEvent event) {
		if (event.getEntity().level().dimensionTypeId() == BuiltinDimensionTypes.OVERWORLD) {
			RareMoonOverworldExtension data = RareMoonOverworldExtension.getData(event.getEntity().level().getServer());
			if (data.getMoon() == 1) {
				event.setAmount((float) (event.getAmount() * RareMoonConfigCommon.BLOOD_MOON_MULTIPLIER.get()));
			}
		}
	}
}
