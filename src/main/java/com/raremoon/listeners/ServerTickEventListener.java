package com.raremoon.listeners;

import com.raremoon.RareMoon;
import com.raremoon.setup.common.RareMoonConfigCommon;
import com.raremoon.world.level.saveddata.RareMoonOverworldExtension;

import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.event.TickEvent.LevelTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@Mod.EventBusSubscriber(bus = EventBusSubscriber.Bus.FORGE)
public class ServerTickEventListener {
	private static boolean flag = true;

	@SubscribeEvent
	public static void onServerTickEvent(final LevelTickEvent event) {
		if (event.level instanceof ServerLevel) {
			if (event.level.getDayTime() < 12000 && flag) {
				RareMoonOverworldExtension data = RareMoonOverworldExtension.getData(event.level.getServer());
				data.setMoon(0);
				flag = false;
			}

			if (event.level.getDayTime() >= 12000 && !flag) {
				flag = true;
				RareMoonOverworldExtension data = RareMoonOverworldExtension.getData(event.level.getServer());
				if (event.level.getRandom().nextInt(100) < RareMoonConfigCommon.RARE_MOON_RARITY.get()) {
					int moon = event.level.getRandom().nextInt(1, 5);
					data.setMoon(moon);
					RareMoon.LOGGER.info("SET RARE MOON " + moon);
				} else {
					data.setMoon(0);
					RareMoon.LOGGER.info("SET " + 0);
				}
			}
		}
	}
}
