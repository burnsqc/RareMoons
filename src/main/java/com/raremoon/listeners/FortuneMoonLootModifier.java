package com.raremoon.listeners;

import java.util.function.Supplier;

import javax.annotation.Nonnull;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.raremoon.registration.dynamic.RareMoonItemTags;
import com.raremoon.world.level.saveddata.RareMoonOverworldExtension;

import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;

public class FortuneMoonLootModifier extends LootModifier {
	public static final Supplier<Codec<FortuneMoonLootModifier>> CODEC = Suppliers.memoize(() -> RecordCodecBuilder.create(inst -> codecStart(inst).apply(inst, FortuneMoonLootModifier::new)));

	public FortuneMoonLootModifier(final LootItemCondition[] conditions) {
		super(conditions);
	}

	@Nonnull
	@Override
	protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
		if (context.getLevel().dimensionTypeId() == BuiltinDimensionTypes.OVERWORLD) {
			RareMoonOverworldExtension data = RareMoonOverworldExtension.getData(context.getLevel().getServer());
			if (data.getMoon() == 2) {
				for (ItemStack loot : generatedLoot) {
					if (loot.is(RareMoonItemTags.FORTUNE_MOON_INCREASED)) {
						loot.setCount(loot.getCount() * 2);
					}
				}
			}
		}
		return generatedLoot;
	}

	@Override
	public Codec<? extends IGlobalLootModifier> codec() {
		return CODEC.get();
	}
}
