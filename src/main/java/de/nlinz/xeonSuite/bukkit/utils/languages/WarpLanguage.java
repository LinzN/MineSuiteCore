package de.nlinz.xeonSuite.bukkit.utils.languages;

import de.nlinz.xeonSuite.bukkit.configurations.XeonLanguages;

public class WarpLanguage {

	public static String Teleport_Warp = null;

	public static void setup() {

		Teleport_Warp = XeonLanguages.getGlobalLanguage().getLanguageString("TeleportSuccessWarp",
				"§e" + "Willkommen an dem Warppunkt!");

	}
}
