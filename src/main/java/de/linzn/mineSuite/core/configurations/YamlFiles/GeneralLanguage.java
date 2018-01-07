package de.linzn.mineSuite.core.configurations.YamlFiles;

import de.linzn.mineSuite.core.utils.CustomYamlConfig;

public class GeneralLanguage {

    public String TELEPORT_TIMER;
    public String TELEPORT_MOVE_CANCEL;
    public String NO_PERMISSIONS;

    public String Teleport_Home;

    public String Teleport_Teleport;

    public String Teleport_Warp;

    public GeneralLanguage(CustomYamlConfig generalConfig) {
        TELEPORT_TIMER = generalConfig.getLanguageString("TeleportTimer",
                "§2" + "Du wirst in §e{TIME}§2 Sekunden teleportiert! Nicht bewegen!");
        TELEPORT_MOVE_CANCEL = generalConfig.getLanguageString("TeleportMoveCancel",
                "§6" + "Abgebrochen! Du hast dich bewegt.");
        NO_PERMISSIONS = generalConfig.getLanguageString("NoPermissions",
                "§4" + "Du hast dafür keine Berechtigung!");

        Teleport_Home = generalConfig.getLanguageString("TeleportSuccessHome",
                "§e" + "Willkommen an deinem Home!");

        Teleport_Teleport = generalConfig.getLanguageString("TeleportSuccess",
                "§e" + "Du wurdest teleportiert!");

        Teleport_Warp = generalConfig.getLanguageString("TeleportSuccessWarp",
                "§e" + "Willkommen an dem Warppunkt!");


    }
}
