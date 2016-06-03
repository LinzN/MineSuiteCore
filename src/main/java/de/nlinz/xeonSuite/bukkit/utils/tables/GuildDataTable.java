package de.nlinz.xeonSuite.bukkit.utils.tables;

import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class GuildDataTable {

	private static HashSet<String> invite = new HashSet<String>();
	public static HashMap<String, Player> pendingGuild = new HashMap<String, Player>();
	public static HashMap<String, Location> pendingGuildLocations = new HashMap<String, Location>();
	public static HashSet<Player> ignoreGuild = new HashSet<Player>();
	public static HashMap<Player, Location> lastGuildLocation = new HashMap<Player, Location>();
	public static HashMap<UUID, Long> expCache = new HashMap<UUID, Long>();

	public static void removePlayer(Player player) {
		pendingGuild.remove(player.getName());
		pendingGuildLocations.remove(player.getName());
		ignoreGuild.remove(player);
		lastGuildLocation.remove(player);
	}

	public static boolean hasInvite(String pName) {
		if (invite.contains(pName)) {
			return true;
		}
		return false;
	}

	public static void addInvite(String pName) {
		invite.add(pName);
	}

	public static void removeInvite(String pName) {
		invite.remove(pName);
	}

}