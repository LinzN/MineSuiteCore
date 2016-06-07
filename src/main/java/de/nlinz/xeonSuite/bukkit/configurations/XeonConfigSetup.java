package de.nlinz.xeonSuite.bukkit.configurations;

import org.bukkit.plugin.java.JavaPlugin;

public class XeonConfigSetup {

	public XeonConfigSetup(JavaPlugin plugin) {
		XeonCustomConfig guildConfig = new XeonCustomConfig(plugin, "guildConfig.yml");

		XeonConfigs.setGuildConfig(guildConfig);
	}
}
