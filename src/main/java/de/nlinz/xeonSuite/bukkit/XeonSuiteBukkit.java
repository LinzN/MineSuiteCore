package de.nlinz.xeonSuite.bukkit;

import org.bukkit.World;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import de.nlinz.xeonSuite.bukkit.commands.CApiCommand;

public class XeonSuiteBukkit extends JavaPlugin {
	private static XeonSuiteBukkit instance;
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
		HandlerList.unregisterAll(instance);
	}

	private void registerListeners() {
		getServer().getPluginManager().registerEvents(new OtherListener(), this);
	}

	public static XeonSuiteBukkit getInstance() {
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
		if (XeonSuiteBukkit.getInstance().getConfig().getStringList("plugin.disabled-worlds")
				.contains(world.getName())) {
			return false;
		} else {
			return true;
		}

	}
}
