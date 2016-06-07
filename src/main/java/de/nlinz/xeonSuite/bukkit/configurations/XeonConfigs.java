package de.nlinz.xeonSuite.bukkit.configurations;

public class XeonConfigs {
	private static CustomConfig guild;

	public static void setGuildConfig(CustomConfig guildConfig) {
		guild = guildConfig;
	}

	public static CustomConfig getGuildConfig() {
		return guild;
	}
}
