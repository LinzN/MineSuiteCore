package de.nlinz.xeonSuite.bukkit.utils.languages;

import de.nlinz.xeonSuite.bukkit.configurations.XeonLanguages;

public class TeleportLanguage {

	public static String Teleport_Teleport = null;

	public static void setup() {
		Teleport_Teleport = XeonLanguages.getGlobalLanguage().getLanguageString("TeleportSuccess",
				"§e" + "Du wurdest teleportiert!");
	}
}
