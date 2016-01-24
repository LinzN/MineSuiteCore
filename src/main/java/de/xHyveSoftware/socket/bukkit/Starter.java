package de.xHyveSoftware.socket.bukkit;

import de.kekshaus.cookieApi.bukkit.CookieApiBukkit;
import de.xHyveSoftware.socket.bukkit.discover.DiscoveryTable;
import de.xHyveSoftware.socket.bukkit.sockets.P2PServer;
import de.xHyveSoftware.socket.bukkit.sockets.P2PServers;

public class Starter {
	private static P2PServer server;

	public static void start() {
		String ip = CookieApiBukkit.getInstance().getConfig().getString("p2p.ip");
		Integer port = CookieApiBukkit.getInstance().getConfig().getInt("p2p.port");
		Integer multicastport = CookieApiBukkit.getInstance().getConfig().getInt("p2p.castport");
		;
		String networkInterface = "";
		String multicastip = CookieApiBukkit.getInstance().getConfig().getString("p2p.castip");

		P2PServers.init(multicastip, multicastport, networkInterface);
		server = new P2PServer(new DiscoveryTable(), ip, port);
		server.start();
	}

	public static void stop() {
		P2PServers.removeServer(server);
		for (P2PServer server : P2PServers.getServers()) {
			server.shutdown();
		}
	}
}
