package com.raremoon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import com.raremoon.config.RareMoonConfigCommon;
import com.raremoon.network.packets.clientbound.SetMoonTypePacket;
import com.raremoon.network.packets.clientbound.SyncSavedDataPacket;
import com.raremoon.registration.deferred.GlobalLootModifierSerializers;
import com.raremoon.setup.client.ClientSetup;

import net.minecraft.SharedConstants;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

@Mod(RareMoon.MOD_ID)
public class RareMoon {
	public static final String MOD_ID = "raremoon";
	public static final String VERSION = "1.0.0-alpha1";
	public static final Logger LOGGER = LogManager.getLogger("RAREMOON");
	private static final Marker MARKER = MarkerManager.getMarker("LOADING");
	public static final String PROTOCOL_VERSION = "1";
	public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(new ResourceLocation(MOD_ID, "main"), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);
	public static int PACKET_ID = 0;

	public RareMoon() {
		LOGGER.info(MARKER, "RAREMOON " + VERSION + " NOW LOADING FOR MINECRAFT " + SharedConstants.getCurrentVersion().getName() + " ON " + FMLEnvironment.dist.toString() + " DISTRIBUTION");
		GlobalLootModifierSerializers.init();
		ModLoadingContext.get().registerConfig(Type.COMMON, RareMoonConfigCommon.COMMON_SPEC, "raremoon-common.toml");
		CHANNEL.registerMessage(PACKET_ID++, SetMoonTypePacket.class, SetMoonTypePacket::encode, SetMoonTypePacket::decode, SetMoonTypePacket::handle);
		CHANNEL.registerMessage(PACKET_ID++, SyncSavedDataPacket.class, SyncSavedDataPacket::encode, SyncSavedDataPacket::decode, SyncSavedDataPacket::handle);
		if (FMLEnvironment.dist.isClient()) {
			ClientSetup.init();
		}
	}
}
