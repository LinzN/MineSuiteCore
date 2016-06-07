package de.nlinz.xeonSuite.bukkit.utils.languages;

import de.nlinz.xeonSuite.bukkit.configurations.XeonLanguages;

public class GlobalLanguage {

	public static String TELEPORT_TIMER = null;
	public static String TELEPORT_MOVE_CANCEL = null;
	public static String NO_PERMISSIONS = null;

	public static void setup() {
		TELEPORT_TIMER = XeonLanguages.getGlobalLanguage().getLanguageString("TeleportTimer",
				"§2" + "Du wirst in §e{TIME}§2 Sekunden teleportiert! Nicht bewegen!");
		TELEPORT_MOVE_CANCEL = XeonLanguages.getGlobalLanguage().getLanguageString("TeleportMoveCancel",
				"§6" + "Abgebrochen! Du hast dich bewegt.");
		NO_PERMISSIONS = XeonLanguages.getGlobalLanguage().getLanguageString("NoPermissions",
				"§4" + "Du hast dafür keine Berechtigung!");

	}
}
