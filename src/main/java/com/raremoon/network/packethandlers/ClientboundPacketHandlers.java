package com.raremoon.network.packethandlers;

import java.util.function.Supplier;

import com.raremoon.RareMoon;
import com.raremoon.client.multiplayer.ClientLevelDataExtension;
import com.raremoon.config.RareMoonConfigClient;
import com.raremoon.network.packets.clientbound.SetMoonTypePacket;
import com.raremoon.network.packets.clientbound.SyncSavedDataPacket;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraftforge.network.NetworkEvent;

public class ClientboundPacketHandlers {
	private static final ResourceLocation MOON_LOCATION = new ResourceLocation("textures/environment/moon_phases.png");
	private static final ResourceLocation BLOOD_MOON_LOCATION = new ResourceLocation(RareMoon.MOD_ID, "textures/environment/blood_moon_phases.png");
	private static final ResourceLocation FORTUNE_MOON_LOCATION = new ResourceLocation(RareMoon.MOD_ID, "textures/environment/fortune_moon_phases.png");
	private static final ResourceLocation HARVEST_MOON_LOCATION = new ResourceLocation(RareMoon.MOD_ID, "textures/environment/harvest_moon_phases.png");
	private static final ResourceLocation BLUE_MOON_LOCATION = new ResourceLocation(RareMoon.MOD_ID, "textures/environment/blue_moon_phases.png");

	public static void handleSetMoonType(SetMoonTypePacket packet, final Supplier<NetworkEvent.Context> context) {
		ClientLevelDataExtension data = new ClientLevelDataExtension();
		data.setMoon(packet.getMoonType());

		if (RareMoonConfigClient.RARE_MOON_NOTIFICATION.get()) {
			Minecraft mc = Minecraft.getInstance();
			LocalPlayer player = mc.player;
			int moon = packet.getMoonType();
			String message = "";

			if (moon == 0) {
				LevelRenderer.MOON_LOCATION = MOON_LOCATION;
			} else if (moon == 1) {
				message = "raremoon.rise.blood";
				LevelRenderer.MOON_LOCATION = BLOOD_MOON_LOCATION;
				player.level().playLocalSound(player.getX(), player.getY(), player.getZ(), SoundEvents.END_PORTAL_SPAWN, SoundSource.BLOCKS, 0.2F, 0.2F, false);
				player.level().playLocalSound(player.getX(), player.getY(), player.getZ(), SoundEvents.BLAZE_DEATH, SoundSource.BLOCKS, 0.2F, 0.1F, false);
			} else if (moon == 2) {
				message = "raremoon.rise.fortune";
				LevelRenderer.MOON_LOCATION = FORTUNE_MOON_LOCATION;
				player.level().playLocalSound(player.getX(), player.getY(), player.getZ(), SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.BLOCKS, 10.0F, 0.2F, false);
				player.level().playLocalSound(player.getX(), player.getY(), player.getZ(), SoundEvents.PLAYER_LEVELUP, SoundSource.BLOCKS, 0.1F, 2.0F, false);
			} else if (moon == 3) {
				message = "raremoon.rise.harvest";
				LevelRenderer.MOON_LOCATION = HARVEST_MOON_LOCATION;
				player.level().playLocalSound(player.getX(), player.getY(), player.getZ(), SoundEvents.CAMPFIRE_CRACKLE, SoundSource.BLOCKS, 10.0F, 0.1F, false);
				player.level().playLocalSound(player.getX(), player.getY(), player.getZ(), SoundEvents.CAT_PURR, SoundSource.BLOCKS, 10.0F, 0.1F, false);
			} else if (moon == 4) {
				message = "raremoon.rise.blue";
				LevelRenderer.MOON_LOCATION = BLUE_MOON_LOCATION;
				player.level().playLocalSound(player.getX(), player.getY(), player.getZ(), SoundEvents.BELL_RESONATE, SoundSource.BLOCKS, 5.0F, 0.1F, false);
				player.level().playLocalSound(player.getX(), player.getY(), player.getZ(), SoundEvents.BELL_RESONATE, SoundSource.BLOCKS, 5.0F, 0.3F, false);
			}

			player.displayClientMessage(Component.translatable(message), true);
		}
	}

	public static void handleSyncSavedData(SyncSavedDataPacket packet, final Supplier<NetworkEvent.Context> context) {
		ClientLevelDataExtension data = new ClientLevelDataExtension();
		int moon = packet.getMoonType();
		data.setMoon(moon);
		if (moon == 0) {
			LevelRenderer.MOON_LOCATION = MOON_LOCATION;
		} else if (moon == 1) {
			LevelRenderer.MOON_LOCATION = BLOOD_MOON_LOCATION;
		} else if (moon == 2) {
			LevelRenderer.MOON_LOCATION = FORTUNE_MOON_LOCATION;
		} else if (moon == 3) {
			LevelRenderer.MOON_LOCATION = HARVEST_MOON_LOCATION;
		} else if (moon == 4) {
			LevelRenderer.MOON_LOCATION = BLUE_MOON_LOCATION;
		}
	}
}
