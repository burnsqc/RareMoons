package com.raremoon.setup.common;

import com.raremoon.server.commands.MoonTypeCommand;

import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@Mod.EventBusSubscriber(bus = EventBusSubscriber.Bus.FORGE)
public class RegisterCommandsEventListener {

	@SubscribeEvent
	public static void onRegisterCommandsEvent(final RegisterCommandsEvent event) {
		MoonTypeCommand.register(event.getDispatcher());
	}
}
