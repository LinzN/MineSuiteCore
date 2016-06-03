package de.nlinz.xeonSuite.bukkit.database;

import java.sql.Connection;
import java.sql.Statement;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import de.nlinz.xeonSuite.bukkit.XeonSuiteBukkit;

public class XeonConnectionSetup {
	public static boolean create() {
		return mysql();

	}

	public static boolean mysql() {
		String db = XeonSuiteBukkit.getDataBase();
		String port = XeonSuiteBukkit.getPort();
		String host = XeonSuiteBukkit.getHost();
		String url = "jdbc:mysql://" + host + ":" + port + "/" + db;
		String username = XeonSuiteBukkit.getUsername();
		String password = XeonSuiteBukkit.getPassword();
		XeonConnectionFactory factory = new XeonConnectionFactory(url, username, password);
		XeonConnectionManager manager = XeonConnectionManager.DEFAULT;
		XeonConnectionHandler handler = manager.getHandler("XeonSuite", factory);

		try {
			Connection connection = handler.getConnection();
			String warp = "CREATE TABLE IF NOT EXISTS warps (player VARCHAR(100), warp_name VARCHAR(100), server VARCHAR(100), world text, x double, y double, z double, yaw float, pitch float, visible int, PRIMARY KEY (`warp_name`));";
			String teleport = "CREATE TABLE IF NOT EXISTS spawns (Id int NOT NULL AUTO_INCREMENT, spawntype VARCHAR(100), server VARCHAR(100), world text, x double, y double, z double, yaw float, pitch float, visible int, PRIMARY KEY (Id));";
			String portal = "CREATE TABLE IF NOT EXISTS portals (portalname VARCHAR(100), server VARCHAR(100), type VARCHAR(20), destination VARCHAR(100), world VARCHAR(100), filltype VARCHAR(100) DEFAULT 'AIR', xmax INT(11), xmin INT(11), ymax INT(11), ymin INT(11), zmax INT(11), zmin INT(11), CONSTRAINT pk_portalname PRIMARY KEY (portalname));";
			String home = "CREATE TABLE IF NOT EXISTS homes (player VARCHAR(100), home_name VARCHAR(100), server VARCHAR(100), world text, x double, y double, z double, yaw float, pitch float, PRIMARY KEY (`player`,`home_name`,`server`));";
			String guild1 = "CREATE TABLE IF NOT EXISTS guilds (GuildUUID VARCHAR(100), GuildName VARCHAR(100), Level int, Experience bigint, PRIMARY KEY (GuildName));";
			String guild2 = "CREATE TABLE IF NOT EXISTS guildSpawns (GuildUUID VARCHAR(100), Server text, World text, CordX double, CordY double, CordZ double, Yaw float, Pitch float, PRIMARY KEY (GuildUUID));";
			String guild3 = "CREATE TABLE IF NOT EXISTS guildPlayers (PlayerName VARCHAR(100), UUID text, GuildUUID text, GuildRang text, PRIMARY KEY (PlayerName));";
			Statement action = connection.createStatement();
			action.executeUpdate(warp);
			action.executeUpdate(teleport);
			action.executeUpdate(portal);
			action.executeUpdate(home);
			action.executeUpdate(guild1);
			action.executeUpdate(guild2);
			action.executeUpdate(guild3);
			action.close();
			handler.release(connection);

			return true;

		} catch (Exception e) {
			Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "=================XeonSuite-ERROR================");
			Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Unable to connect to database.");
			Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Pls check you mysql connection in config.yml.");
			Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "=================XeonSuite-ERROR================");
			if (XeonSuiteBukkit.isDebugmode()) {
				e.printStackTrace();
			}
			return false;
		}

	}

}
