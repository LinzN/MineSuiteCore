package de.nlinz.xeonSuite.bukkit.utils.languages;

import de.nlinz.xeonSuite.bukkit.configurations.XeonLanguages;

public class HomeLanguage {

	public static String Teleport_Home = null;

	public static void setup() {

		Teleport_Home = XeonLanguages.getGlobalLanguage().getLanguageString("TeleportSuccessHome",
				"§e" + "Willkommen an deinem Home!");

	}
}
