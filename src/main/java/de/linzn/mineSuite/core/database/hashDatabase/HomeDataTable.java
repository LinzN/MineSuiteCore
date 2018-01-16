package de.linzn.mineSuite.core.database.hashDatabase;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.HashSet;

public class HomeDataTable {
	public static HashMap<String, Player> pendingHome = new HashMap<>();
	public static HashMap<String, Location> pendingHomeLocations = new HashMap<>();
	public static HashSet<Player> ignoreHome = new HashSet<>();
	public static HashMap<Player, Location> lastHomeLocation = new HashMap<>();

	public static void removePlayer(Player player) {
		pendingHome.remove(player.getName());
		pendingHomeLocations.remove(player.getName());
		ignoreHome.remove(player);
		lastHomeLocation.remove(player);
	}

}