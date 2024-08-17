package com.raremoon.network.packets.clientbound;

import java.util.function.Supplier;

import com.raremoon.network.packethandlers.ClientboundPacketHandlers;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public class SetMoonTypePacket {
	private int moonType;

	public SetMoonTypePacket(int moonTypeIn) {
		moonType = moonTypeIn;
	}

	public static void encode(SetMoonTypePacket msg, FriendlyByteBuf buf) {
		buf.writeInt(msg.moonType);
	}

	public static SetMoonTypePacket decode(FriendlyByteBuf buf) {
		return new SetMoonTypePacket(buf.readInt());
	}

	public static void handle(SetMoonTypePacket packet, final Supplier<NetworkEvent.Context> context) {
		context.get().enqueueWork(() -> {
			ClientboundPacketHandlers.handleSetMoonType(packet, context);
		});
		context.get().setPacketHandled(true);
	}

	public int getMoonType() {
		return moonType;
	}
}
