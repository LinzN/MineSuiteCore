package de.linzn.mineSuite.core.hashdata;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.HashSet;

public class TeleportDataTable {
	public static HashMap<String, Player> pendingTeleport = new HashMap<>();
	public static HashMap<String, Location> pendingTeleportLocations = new HashMap<>();
	public static HashSet<Player> ignoreTeleport = new HashSet<>();
	public static HashMap<Player, Location> lastTeleportLocation = new HashMap<>();

	public static void RemovePlayer(Player player) {
		pendingTeleport.remove(player.getName());
		pendingTeleportLocations.remove(player.getName());
		ignoreTeleport.remove(player);
		lastTeleportLocation.remove(player);
	}

}