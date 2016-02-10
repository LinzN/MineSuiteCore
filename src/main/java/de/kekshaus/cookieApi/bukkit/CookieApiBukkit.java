package de.kekshaus.cookieApi.bukkit;

import org.bukkit.World;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;
import de.kekshaus.cookieApi.bukkit.commands.CApiCommand;
import de.kekshaus.cookieApi.bukkit.listeners.BukkitListener;
import de.kekshaus.cookieApi.bukkit.listeners.server.PipeLiveStreamListener;
import de.kekshaus.cookieApi.bukkit.listeners.server.ServerStreamOtherListener;
import de.kekshaus.cookieApi.bukkit.listeners.server.ServerStreamTeleportListener;
import de.kekshaus.cookieApi.bukkit.socketEvents.BungeeStreamDataEvent;
import de.kekshaus.cookieApi.bukkit.socketEvents.BungeeStreamOtherEvent;
import de.kekshaus.cookieApi.bukkit.socketEvents.BungeeStreamTeleportEvent;
import de.kekshaus.cookieApi.bukkit.socketEvents.PipeLiveStreamEvent;
import de.kekshaus.cookieApi.bukkit.socketEvents.ServerStreamOtherEvent;
import de.kekshaus.cookieApi.bukkit.socketEvents.ServerStreamTeleportEvent;
import de.kekshaus.cookieApi.bukkit.tasks.PipeStreamScheduler;
import de.xHyveSoftware.socket.bukkit.Starter;
import de.xHyveSoftware.socket.bukkit.api.PacketManager;

public class CookieApiBukkit extends JavaPlugin {
	private static CookieApiBukkit instance;
	private static String servername;
	private static String database;
	private static String host;
	private static String port;
	private static String username;
	private static String password;
	private static boolean debugmode;
	private static int warmuptime;

	@Override
	public void onEnable() {
		instance = this;
		saveDefaultConfig();
		registerListeners();
		getCommand("capi").setExecutor(new CApiCommand());
		PacketManager.registerPacket(BungeeStreamTeleportEvent.class);
		PacketManager.registerPacket(BungeeStreamDataEvent.class);
		PacketManager.registerPacket(BungeeStreamOtherEvent.class);
		PacketManager.registerPacket(ServerStreamTeleportEvent.class);
		PacketManager.registerPacket(ServerStreamOtherEvent.class);
		PacketManager.registerPacket(PipeLiveStreamEvent.class);
		PacketManager.registerListener(new ServerStreamTeleportListener());
		PacketManager.registerListener(new ServerStreamOtherListener());
		PacketManager.registerListener(new PipeLiveStreamListener());
		Starter.start();
		new PipeStreamScheduler();
		servername = this.getConfig().getString("plugin.servername");
		database = this.getConfig().getString("sql.database");
		host = this.getConfig().getString("sql.host");
		port = this.getConfig().getString("sql.port");
		username = this.getConfig().getString("sql.username");
		password = this.getConfig().getString("sql.password");
		debugmode = this.getConfig().getBoolean("sql.debugmode");
		warmuptime = this.getConfig().getInt("plugin.warmuptime");
	}

	@Override
	public void onDisable() {
		Starter.stop();
		HandlerList.unregisterAll(instance);
	}

	private void registerListeners() {
		getServer().getPluginManager().registerEvents(new BukkitListener(), this);
	}

	public static CookieApiBukkit getInstance() {
		return instance;
	}

	public static String getServerName() {
		return servername;
	}

	public static String getDataBase() {
		return database;

	}

	public static String getHost() {
		return host;
	}

	public static String getPort() {
		return port;
	}

	public static String getUsername() {
		return username;
	}

	public static String getPassword() {
		return password;
	}

	public static boolean isDebugmode() {
		return debugmode;
	}

	public static int getWarmUpTime() {
		return warmuptime;
	}

	public static boolean isWorldAllowed(World world) {
		if (CookieApiBukkit.getInstance().getConfig().getStringList("plugin.disabled-worlds")
				.contains(world.getName())) {
			return false;
		} else {
			return true;
		}

	}
}
