package de.xHyveSoftware.socket.bukkit.packet;

import de.xHyveSoftware.socket.bukkit.api.PacketManager;
import de.xHyveSoftware.socket.bukkit.packet.protocol.DiscoveryTable;
import de.xHyveSoftware.socket.bukkit.packet.protocol.Message;
import de.xHyveSoftware.socket.bukkit.packet.protocol.PacketRegister;
import de.xHyveSoftware.socket.bukkit.sockets.P2PClient;

public class PacketHandler extends PacketController {
	private P2PClient client;

	public PacketHandler(P2PClient client) {
		this.client = client;
	}

	public void handle(PacketRegister packetRegister) {
		if (packetRegister.getMode() == 0) {
			client.getPacketsCache().addToCache(packetRegister.getChannel(), packetRegister.getPacket());
		} else {
			client.getPacketsCache().removeFromCache(packetRegister.getChannel(), packetRegister.getPacket());
		}
	}

	public void handle(DiscoveryTable discoveryTable) {
		for (String host : discoveryTable.getHosts()) {
			if (!client.getServer().getDiscoveryTable().isKnownHost(host)) {
				client.getServer().getDiscoveryTable().add(host);
			}
		}
	}

	public void handle(Message message) {
		PacketManager.addMessage(message);
	}
}
