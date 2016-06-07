package de.nlinz.xeonSuite.bukkit.configurations;

public class XeonConfigs {
	private static XeonCustomConfig guild;

	public static void setGuildConfig(XeonCustomConfig guildConfig) {
		guild = guildConfig;
	}

	public static XeonCustomConfig getGuildConfig() {
		return guild;
	}
}
