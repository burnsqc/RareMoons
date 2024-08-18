package com.raremoon.registration.dynamic;

import com.raremoon.RareMoon;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class RareMoonItemTags {
	public static final TagKey<Item> FORTUNE_MOON_INCREASED = TagKey.create(Registries.ITEM, new ResourceLocation(RareMoon.MOD_ID, "fortune_moon_increased"));
	public static final TagKey<Item> HARVEST_MOON_INCREASED = TagKey.create(Registries.ITEM, new ResourceLocation(RareMoon.MOD_ID, "harvest_moon_increased"));
}
