package com.raremoon.listeners;

import com.raremoon.client.multiplayer.ClientLevelDataExtension;
import com.raremoon.config.RareMoonConfigClient;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@Mod.EventBusSubscriber(bus = EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ViewportEventListener {

	@SubscribeEvent
	public static void onViewportEvent$RenderFog(final ViewportEvent.ComputeFogColor event) {
		Minecraft minecraft = Minecraft.getInstance();
		long time = minecraft.level.getDayTime() % 24000L - 12000;
		float red = (ClientLevelDataExtension.getMoon() == 1) ? Mth.clamp(time < 6000 ? time / 4000F : -(time - 12000) / 4000F, 0.0F, 0.03F) : 0;
		float yellow = (ClientLevelDataExtension.getMoon() == 2) ? Mth.clamp(time < 6000 ? time / 4000F : -(time - 12000) / 4000F, 0.0F, 0.02F) : 0;
		float green = (ClientLevelDataExtension.getMoon() == 3) ? Mth.clamp(time < 6000 ? time / 4000F : -(time - 12000) / 400F, 0.0F, 0.02F) : 0;
		float blue = (ClientLevelDataExtension.getMoon() == 4) ? Mth.clamp(time < 6000 ? time / 2000F : -(time - 12000) / 2000F, 0.0F, 0.05F) : 0;

		ClientLevel level = minecraft.level;

		Vec3 vec3 = level.getSkyColor(minecraft.gameRenderer.getMainCamera().getPosition(), minecraft.getPartialTick());
		float f = (float) vec3.x;
		float f1 = (float) vec3.y;
		float f2 = (float) vec3.z;
		float factor = RareMoonConfigClient.MOON_COLOR_CORRECTION.get() / 100.0F;
		event.setRed(f + factor * (red + yellow));
		event.setGreen(f1 + factor * (green + yellow));
		event.setBlue(f2 + factor * (blue));
	}
}
