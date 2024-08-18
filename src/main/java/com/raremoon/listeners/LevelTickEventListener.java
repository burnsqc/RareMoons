package com.raremoon.listeners;

import com.raremoon.setup.common.RareMoonConfigCommon;
import com.raremoon.world.level.saveddata.RareMoonOverworldExtension;

import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.event.TickEvent.LevelTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@Mod.EventBusSubscriber(bus = EventBusSubscriber.Bus.FORGE)
public class LevelTickEventListener {
	private static boolean flag = true;

	@SubscribeEvent
	public static void onServerTickEvent(final LevelTickEvent event) {
		if (event.level instanceof ServerLevel) {
			if (event.level.getDayTime() % 24000L < 12000 && flag) {
				RareMoonOverworldExtension data = RareMoonOverworldExtension.getData(event.level.getServer());
				data.setMoon(0);
				flag = false;
			}

			if (event.level.getDayTime() % 24000L >= 12000 && !flag) {
				flag = true;
				RareMoonOverworldExtension data = RareMoonOverworldExtension.getData(event.level.getServer());
				if (event.level.getRandom().nextInt(100) < RareMoonConfigCommon.RARE_MOON_RARITY.get() && RareMoonConfigCommon.BLOOD_MOON_WEIGHT.get() + RareMoonConfigCommon.FORTUNE_MOON_WEIGHT.get() + RareMoonConfigCommon.HARVEST_MOON_WEIGHT.get() + RareMoonConfigCommon.BLUE_MOON_WEIGHT.get() != 0) {
					int randomInt = event.level.getRandom().nextInt(RareMoonConfigCommon.BLOOD_MOON_WEIGHT.get() + RareMoonConfigCommon.FORTUNE_MOON_WEIGHT.get() + RareMoonConfigCommon.HARVEST_MOON_WEIGHT.get() + RareMoonConfigCommon.BLUE_MOON_WEIGHT.get());
					if (randomInt < RareMoonConfigCommon.BLOOD_MOON_WEIGHT.get()) {
						data.setMoon(1);
					} else if (randomInt >= RareMoonConfigCommon.BLOOD_MOON_WEIGHT.get() && randomInt < (RareMoonConfigCommon.BLOOD_MOON_WEIGHT.get() + RareMoonConfigCommon.FORTUNE_MOON_WEIGHT.get())) {
						data.setMoon(2);
					} else if (randomInt >= (RareMoonConfigCommon.BLOOD_MOON_WEIGHT.get() + RareMoonConfigCommon.FORTUNE_MOON_WEIGHT.get()) && randomInt < (RareMoonConfigCommon.BLOOD_MOON_WEIGHT.get() + RareMoonConfigCommon.FORTUNE_MOON_WEIGHT.get() + RareMoonConfigCommon.HARVEST_MOON_WEIGHT.get())) {
						data.setMoon(3);
					} else {
						data.setMoon(4);
					}
				} else {
					data.setMoon(0);
				}
			}
		}
	}
}
