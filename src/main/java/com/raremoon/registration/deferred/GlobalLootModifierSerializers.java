package com.raremoon.registration.deferred;

import com.mojang.serialization.Codec;
import com.raremoon.RareMoon;
import com.raremoon.listeners.BlueMoonLootModifier;
import com.raremoon.listeners.FortuneMoonLootModifier;
import com.raremoon.listeners.HarvestMoonLootModifier;

import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class GlobalLootModifierSerializers {
	public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> GLOBAL_LOOT_MODIFIER_SERIALIZERS = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, RareMoon.MOD_ID);

	public static void init() {
		GLOBAL_LOOT_MODIFIER_SERIALIZERS.register(FMLJavaModLoadingContext.get().getModEventBus());
	}

	public static final RegistryObject<Codec<BlueMoonLootModifier>> BLUE_MOON_LOOT_MODIFIER = GLOBAL_LOOT_MODIFIER_SERIALIZERS.register("gameplay/fishing/blue_moon_loot_modifier", BlueMoonLootModifier.CODEC);
	public static final RegistryObject<Codec<FortuneMoonLootModifier>> FORTUNE_MOON_LOOT_MODIFIER = GLOBAL_LOOT_MODIFIER_SERIALIZERS.register("blocks/fortune_moon_loot_modifier", FortuneMoonLootModifier.CODEC);
	public static final RegistryObject<Codec<HarvestMoonLootModifier>> HARVEST_MOON_LOOT_MODIFIER = GLOBAL_LOOT_MODIFIER_SERIALIZERS.register("blocks/harvest_moon_loot_modifier", HarvestMoonLootModifier.CODEC);
}
