package de.kekshaus.cookieApi.bukkit.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Mapped {
	private static HashMap<String, Boolean> isafk = new HashMap<String, Boolean>();
	private static HashMap<String, CookieInventory> inventory = new HashMap<String, CookieInventory>();
	private static List<String> isLoaded = new ArrayList<String>();

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

	public static boolean hasInventory(String player) {
		if (inventory.containsKey(player)) {
			return true;
		}
		return false;

	}

	public static CookieInventory getInventory(String player) {
		return inventory.get(player);
	}

	public static void addInventory(String player, CookieInventory cookieInventory) {
		inventory.put(player, cookieInventory);
	}

	public static boolean removeInventory(String player) {
		inventory.remove(player);
		if (hasInventory(player)) {
			return false;
		} else {
			return true;
		}
	}

	public static boolean isLoaded(String player) {
		if (isLoaded.contains(player)) {
			return true;
		}
		return false;

	}

	public static void setLoaded(String player) {
		if (!isLoaded.contains(player)) {
			isLoaded.add(player);
		}
	}

	public static void unsetLoaded(String player) {
		if (isLoaded.contains(player)) {
			isLoaded.remove(player);
		}
	}
}