package com.raremoon.setup.client;

import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig.Type;

public class ClientSetup {
	public static void init() {
		ModLoadingContext.get().registerConfig(Type.CLIENT, RareMoonConfigClient.CLIENT_SPEC, "raremoon-client.toml");
	}
}
