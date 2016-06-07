package de.nlinz.xeonSuite.bukkit.configurations;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;

public class YamlInitialisation {

	public YamlInitialisation(JavaPlugin plugin) {
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
		XeonLanguages.setChatLanguage(chatLanguage);

		CustomConfig homeLanguage = new CustomConfig(plugin, languageDirectory, "homeLanguage.yml");
		XeonLanguages.setHomeLanguage(homeLanguage);

		CustomConfig portalLanguage = new CustomConfig(plugin, languageDirectory, "portalLanguage.yml");
		XeonLanguages.setPortalLanguage(portalLanguage);

		CustomConfig teleportLanguage = new CustomConfig(plugin, languageDirectory, "teleportLanguage.yml");
		XeonLanguages.setTeleportLanguage(teleportLanguage);

		CustomConfig warpLanguage = new CustomConfig(plugin, languageDirectory, "warpLanguage.yml");
		XeonLanguages.setWarpLanguage(warpLanguage);

		CustomConfig banLanguage = new CustomConfig(plugin, languageDirectory, "banLanguage.yml");
		XeonLanguages.setBanLanguage(banLanguage);
	}
}
