package com.raremoon.setup.client;

import com.raremoon.client.renderer.RareMoonOverworldRenderer;

import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterDimensionSpecialEffectsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@Mod.EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class RegisterDimensionSpecialEffectsEventListener {

	@SubscribeEvent
	public static void onRegisterDimensionSpecialEffectsEvent(final RegisterDimensionSpecialEffectsEvent event) {
		event.register(BuiltinDimensionTypes.OVERWORLD_EFFECTS, new RareMoonOverworldRenderer());
	}
}
