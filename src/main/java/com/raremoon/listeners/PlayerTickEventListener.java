package com.raremoon.listeners;

import java.util.List;
import java.util.Random;

import com.raremoon.config.RareMoonConfigCommon;
import com.raremoon.registration.dynamic.RareMoonMobEffectTags;
import com.raremoon.world.level.saveddata.RareMoonOverworldExtension;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraftforge.event.TickEvent.PlayerTickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = EventBusSubscriber.Bus.FORGE)
public class PlayerTickEventListener {

	@SubscribeEvent
	public static void onPlayerTickEvent(final PlayerTickEvent event) {
		if (event.player instanceof ServerPlayer) {
			RareMoonOverworldExtension data = RareMoonOverworldExtension.getData(event.player.level().getServer());
			if (data.getMoon() == 4) {
				if (!event.player.getAbilities().instabuild && !event.player.isSpectator() && event.player.getActiveEffects().isEmpty() && event.player.level().getGameTime() % (RareMoonConfigCommon.BLUE_MOON_DURATION.get() * 20 + RareMoonConfigCommon.BLUE_MOON_COOLDOWN.get() * 20) == 0) {
					List<MobEffect> mobEffectsFiltered = ForgeRegistries.MOB_EFFECTS.getValues().stream().filter(effect -> ForgeRegistries.MOB_EFFECTS.tags().getTag(RareMoonMobEffectTags.BLUE_MOON_RANDOMIZED).contains(effect)).toList();
					int random = new Random().nextInt(mobEffectsFiltered.size());
					event.player.addEffect(new MobEffectInstance((MobEffect) mobEffectsFiltered.toArray()[random], RareMoonConfigCommon.BLUE_MOON_DURATION.get() * 20, 1));
				}
			}
		}
	}
}
