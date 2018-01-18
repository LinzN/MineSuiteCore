/*
 * Copyright (C) 2018. MineGaming - All Rights Reserved
 * You may use, distribute and modify this code under the
 * terms of the LGPLv3 license, which unfortunately won't be
 * written for another century.
 *
 *  You should have received a copy of the LGPLv3 license with
 *  this file. If not, please write to: niklas.linz@enigmar.de
 *
 */

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
        TELEPORT_TIMER = generalConfig.getString("TeleportTimer",
                "§2" + "Du wirst in §e{TIME}§2 Sekunden teleportiert! Nicht bewegen!");
        TELEPORT_MOVE_CANCEL = generalConfig.getString("TeleportMoveCancel",
                "§6" + "Abgebrochen! Du hast dich bewegt.");
        NO_PERMISSIONS = generalConfig.getString("NoPermissions",
                "§4" + "Du hast dafür keine Berechtigung!");

        Teleport_Home = generalConfig.getString("TeleportSuccessHome",
                "§e" + "Willkommen an deinem Home!");

        Teleport_Teleport = generalConfig.getString("TeleportSuccess",
                "§e" + "Du wurdest teleportiert!");

        Teleport_Warp = generalConfig.getString("TeleportSuccessWarp",
                "§e" + "Willkommen an dem Warppunkt!");


    }
}
