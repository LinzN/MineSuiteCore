package de.nlinz.xeonSuite.bukkit.configurations;

public class XeonLanguages {
	private static CustomConfig guild;

	public static void setGuildLanguage(CustomConfig guildLanguage) {
		guild = guildLanguage;
	}

	public static CustomConfig getGuildLanguage() {
		return guild;
	}
}
