package com.raremoon.listeners;

import com.raremoon.world.level.saveddata.RareMoonOverworldExtension;

import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@Mod.EventBusSubscriber(bus = EventBusSubscriber.Bus.FORGE)
public class HarvestMoonListener {

	@SubscribeEvent
	public static void onCropGrowEventPre(final BlockEvent.CropGrowEvent.Pre event) {
		if (event.getLevel() == BuiltinDimensionTypes.OVERWORLD) {
			RareMoonOverworldExtension data = RareMoonOverworldExtension.getData(event.getLevel().getServer());
			if (data.getMoon() == 3) {
				event.setResult(Result.ALLOW);
			}
		}
	}

	@SubscribeEvent
	public static void onCropGrowEventPost(final BlockEvent.CropGrowEvent.Post event) {
		if (event.getLevel() == BuiltinDimensionTypes.OVERWORLD) {
			RareMoonOverworldExtension data = RareMoonOverworldExtension.getData(event.getLevel().getServer());
			if (data.getMoon() == 3) {
				if (event.getState().getBlock() instanceof CropBlock crop) {
					int maxAge = crop.getMaxAge();
					int age = crop.getAge(event.getState());
					if (age < maxAge) {
						event.getLevel().setBlock(event.getPos(), crop.getStateForAge(age + 1), 2);
					}
				}
			}
		}
	}
}
