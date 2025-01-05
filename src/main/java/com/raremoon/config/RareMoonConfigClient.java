package com.raremoon.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public final class RareMoonConfigClient {
	public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
	public static final ForgeConfigSpec CLIENT_SPEC;

	public static final ForgeConfigSpec.ConfigValue<Boolean> RARE_MOON_NOTIFICATION;
	public static final ForgeConfigSpec.ConfigValue<Integer> MOON_COLOR_CORRECTION;

	static {
		BUILDER.comment("RAREMOON CLIENT CONFIG\n");

		BUILDER.comment("In single-player, these settings will affect only you.");
		BUILDER.comment("In multi-player, these settings will still affect only you.");
		BUILDER.comment("These settings have been set to defaults selected by the RareMoon development team.\n");

		BUILDER.push("NOTIFICATIONS");
		RARE_MOON_NOTIFICATION = BUILDER.comment("true - Display a message when a rare moon appears.\nfalse - Do not display a message when a rare moon appears.").define("Notifications", true);
		BUILDER.pop();

		BUILDER.push("COLOR CORRECTION");
		MOON_COLOR_CORRECTION = BUILDER.comment("Color correction factor.  50 is intended for vanilla, but higher numbers might be better if using shaders.").defineInRange("Factor", 50, 0, 100);
		BUILDER.pop();

		CLIENT_SPEC = BUILDER.build();
	}
}
