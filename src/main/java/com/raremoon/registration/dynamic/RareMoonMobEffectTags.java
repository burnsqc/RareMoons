package com.raremoon.registration.dynamic;

import com.raremoon.RareMoon;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;

public class RareMoonMobEffectTags {
	public static final TagKey<MobEffect> BLUE_MOON_RANDOMIZED = TagKey.create(Registries.MOB_EFFECT, new ResourceLocation(RareMoon.MOD_ID, "blue_moon_randomized"));
}
