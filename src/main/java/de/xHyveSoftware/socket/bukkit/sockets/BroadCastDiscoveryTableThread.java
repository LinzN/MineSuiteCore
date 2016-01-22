package de.xHyveSoftware.socket.bukkit.sockets;

import de.xHyveSoftware.socket.bukkit.packet.protocol.DiscoveryTable;
import de.xHyveSoftware.socket.bukkit.util.Logger;

public class BroadCastDiscoveryTableThread implements Runnable {
	private P2PServer server;

	public BroadCastDiscoveryTableThread(P2PServer server) {
		this.server = server;
	}

	@Override
	public void run() {
		try {
			DiscoveryTable discoveryTable = new DiscoveryTable();
			discoveryTable.setHosts(server.getDiscoveryTable().dump());

			server.broadcast(discoveryTable);
		} catch (Exception e) {
			Logger.error("Could not broadcast Discovery Table", e);
		}
	}
}
