package com.raremoon.listeners;

import com.raremoon.RareMoon;
import com.raremoon.network.packets.clientbound.SyncSavedDataPacket;
import com.raremoon.world.level.saveddata.RareMoonOverworldExtension;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.network.PacketDistributor;

@Mod.EventBusSubscriber(bus = EventBusSubscriber.Bus.FORGE)
public class EntityJoinLevelEventListener {

	@SubscribeEvent
	public static void onEntityJoinLevel(final EntityJoinLevelEvent event) {
		if (event.getEntity() instanceof ServerPlayer serverPlayer) {
			if (serverPlayer.level().dimensionTypeId() == BuiltinDimensionTypes.OVERWORLD) {
				RareMoonOverworldExtension data = RareMoonOverworldExtension.getData(event.getEntity().getServer());
				RareMoon.CHANNEL.send(PacketDistributor.PLAYER.with(() -> serverPlayer), new SyncSavedDataPacket(data.getMoon()));
			}
		}
	}
}
