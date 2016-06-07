package de.nlinz.xeonSuite.bukkit.configurations;

public class XeonLanguages {
	private static CustomConfig guild;
	private static CustomConfig chat;
	private static CustomConfig home;
	private static CustomConfig portal;
	private static CustomConfig teleport;
	private static CustomConfig warp;
	private static CustomConfig ban;

	public static void setGuildLanguage(CustomConfig guildLanguage) {
		guild = guildLanguage;
	}

	public static void setChatLanguage(CustomConfig chatLanguage) {
		chat = chatLanguage;
	}

	public static void setHomeLanguage(CustomConfig homeLanguage) {
		home = homeLanguage;
	}

	public static void setPortalLanguage(CustomConfig portalLanguage) {
		portal = portalLanguage;
	}

	public static void setTeleportLanguage(CustomConfig teleportLanguage) {
		teleport = teleportLanguage;
	}

	public static void setWarpLanguage(CustomConfig warpLanguage) {
		warp = warpLanguage;
	}

	public static void setBanLanguage(CustomConfig banLanguage) {
		ban = banLanguage;
	}

	public static CustomConfig getGuildLanguage() {
		return guild;
	}

	public static CustomConfig getChatLanguage() {
		return chat;
	}

	public static CustomConfig getHomeLanguage() {
		return home;
	}

	public static CustomConfig getPortalLanguage() {
		return portal;
	}

	public static CustomConfig getTeleportLanguage() {
		return teleport;
	}

	public static CustomConfig getWarpLanguage() {
		return warp;
	}

	public static CustomConfig getBanLanguage() {
		return ban;
	}
}
