package de.nlinz.xeonSuite.bukkit.utils.tables;

import java.util.HashMap;

public class ChatDataTable {
	private static HashMap<String, Boolean> isafk = new HashMap<String, Boolean>();

	public static boolean isAfk(String player) {
		if (isafk.containsKey(player)) {
			return true;
		}
		return false;

	}

	public static void setAfk(String player, boolean value) {
		if (value) {
			isafk.put(player, true);
		} else {
			isafk.remove(player);
		}
	}

}