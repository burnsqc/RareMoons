package com.raremoon.network.packets.clientbound;

import java.util.function.Supplier;

import com.raremoon.network.packethandlers.ClientboundPacketHandlers;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

public class SyncSavedDataPacket {
	private int moonType;

	public SyncSavedDataPacket(int moonTypeIn) {
		moonType = moonTypeIn;
	}

	public static void encode(SyncSavedDataPacket msg, FriendlyByteBuf buf) {
		buf.writeInt(msg.moonType);
	}

	public static SyncSavedDataPacket decode(FriendlyByteBuf buf) {
		return new SyncSavedDataPacket(buf.readInt());
	}

	public static void handle(SyncSavedDataPacket packet, final Supplier<NetworkEvent.Context> context) {
		context.get().enqueueWork(() -> {
			ClientboundPacketHandlers.handleSyncSavedData(packet, context);
		});
		context.get().setPacketHandled(true);
	}

	public int getMoonType() {
		return moonType;
	}
}
