package com.raremoon;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.raremoon.network.packets.clientbound.SetMoonTypePacket;
import com.raremoon.network.packets.clientbound.SyncSavedDataPacket;
import com.raremoon.registration.deferred.GlobalLootModifierSerializers;
import com.raremoon.setup.client.ClientSetup;
import com.raremoon.setup.common.RareMoonConfigCommon;

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
	public static final Logger LOGGER = LogManager.getLogger();
	public static final String PROTOCOL_VERSION = "1";
	public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(new ResourceLocation(MOD_ID, "main"), () -> PROTOCOL_VERSION, PROTOCOL_VERSION::equals, PROTOCOL_VERSION::equals);
	public static int PACKET_ID = 0;

	public RareMoon() {
		LOGGER.info("RAREMOON NOW LOADING FOR DISTRIBUTION - " + FMLEnvironment.dist.toString());
		GlobalLootModifierSerializers.init();
		ModLoadingContext.get().registerConfig(Type.COMMON, RareMoonConfigCommon.COMMON_SPEC, "raremoon-common.toml");
		CHANNEL.registerMessage(PACKET_ID++, SetMoonTypePacket.class, SetMoonTypePacket::encode, SetMoonTypePacket::decode, SetMoonTypePacket::handle);
		CHANNEL.registerMessage(PACKET_ID++, SyncSavedDataPacket.class, SyncSavedDataPacket::encode, SyncSavedDataPacket::decode, SyncSavedDataPacket::handle);
		if (FMLEnvironment.dist.isClient()) {
			ClientSetup.init();
		}
	}
}
