package de.nlinz.xeonSuite.bukkit.configurations;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;

public class ConfigSetup {

	public ConfigSetup(JavaPlugin plugin) {
		File configDirectory = new File(plugin.getDataFolder(), "configs");
		if (!configDirectory.exists()) {
			configDirectory.mkdir();
		}

		File languageDirectory = new File(plugin.getDataFolder(), "languages");
		if (!languageDirectory.exists()) {
			languageDirectory.mkdir();
		}

		/* Configs */
		CustomConfig guildConfig = new CustomConfig(plugin, configDirectory, "guildConfig.yml");
		XeonConfigs.setGuildConfig(guildConfig);
		/* Languages */
		CustomConfig guildLanguage = new CustomConfig(plugin, languageDirectory, "guildLanguage.yml");
		XeonLanguages.setGuildLanguage(guildLanguage);

		CustomConfig chatLanguage = new CustomConfig(plugin, languageDirectory, "chatLanguage.yml");
		XeonLanguages.setGuildLanguage(chatLanguage);

		CustomConfig homeLanguage = new CustomConfig(plugin, languageDirectory, "homeLanguage.yml");
		XeonLanguages.setGuildLanguage(homeLanguage);

		CustomConfig portalLanguage = new CustomConfig(plugin, languageDirectory, "portalLanguage.yml");
		XeonLanguages.setGuildLanguage(portalLanguage);

		CustomConfig teleportLanguage = new CustomConfig(plugin, languageDirectory, "teleportLanguage.yml");
		XeonLanguages.setGuildLanguage(teleportLanguage);

		CustomConfig warpLanguage = new CustomConfig(plugin, languageDirectory, "warpLanguage.yml");
		XeonLanguages.setGuildLanguage(warpLanguage);

		CustomConfig banLanguage = new CustomConfig(plugin, languageDirectory, "banLanguage.yml");
		XeonLanguages.setGuildLanguage(banLanguage);
	}
}
