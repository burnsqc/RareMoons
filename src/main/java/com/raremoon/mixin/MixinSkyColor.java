package com.raremoon.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import com.raremoon.client.multiplayer.ClientLevelDataExtension;
import com.raremoon.config.RareMoonConfigClient;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;

@Mixin(ClientLevel.class)
public abstract class MixinSkyColor {
	@Inject(method = "getSkyColor(Lnet/minecraft/world/phys/Vec3;F)Lnet/minecraft/world/phys/Vec3;", at = @At(value = "RETURN"), cancellable = true)
	private void raremoon_getSkyColor(CallbackInfoReturnable<Vec3> cir) {
		Vec3 colorOrig = cir.getReturnValue();
		Minecraft minecraft = Minecraft.getInstance();
		long time = minecraft.level.getDayTime() % 24000L - 12000;
		float red = (ClientLevelDataExtension.getMoon() == 1) ? Mth.clamp(time < 12000 ? time / 4000F : -(time - 12000) / 4000F, 0.0F, 0.14F) : 0;
		float yellow = (ClientLevelDataExtension.getMoon() == 2) ? Mth.clamp(time < 12000 ? time / 4000F : -(time - 12000) / 4000F, 0.0F, 0.1F) : 0;
		float green = (ClientLevelDataExtension.getMoon() == 3) ? Mth.clamp(time < 12000 ? time / 4000F : -(time - 12000) / 4000F, 0.0F, 0.1F) : 0;
		float blue = (ClientLevelDataExtension.getMoon() == 4) ? Mth.clamp(time < 12000 ? time / 2000F : -(time - 12000) / 2000F, 0.0F, 0.2F) : 0;
		float factor = RareMoonConfigClient.MOON_COLOR_CORRECTION.get() / 100.0F;
		Vec3 add = new Vec3(factor * (red + yellow), factor * (green + yellow), factor * (blue));
		cir.setReturnValue(colorOrig.add(add));
	}
}
