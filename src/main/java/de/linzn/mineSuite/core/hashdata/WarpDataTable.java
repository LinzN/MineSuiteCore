package de.linzn.mineSuite.core.hashdata;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.HashSet;

public class WarpDataTable {
	public static HashMap<String, Player> pendingWarp = new HashMap<>();
	public static HashMap<String, Location> pendingWarpLocations = new HashMap<>();
	public static HashSet<Player> ignoreWarp = new HashSet<>();
	public static HashMap<Player, Location> lastWarpLocation = new HashMap<>();

	public static void removeWarpPlayer(Player player) {
		pendingWarp.remove(player.getName());
		pendingWarpLocations.remove(player.getName());
		ignoreWarp.remove(player);
		lastWarpLocation.remove(player);
	}

}