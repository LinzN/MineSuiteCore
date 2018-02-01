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


import net.md_5.bungee.api.ChatColor;

public class GeneralLanguage {
    public static String global_NO_PERMISSIONS = ChatColor.RED + "" + ChatColor.BOLD + "Du hast dafür keine Berechtigung!";
    public static String global_COMMAND_PENDING = ChatColor.RED + "" + ChatColor.BOLD + "Bitte warte einen Augenblick :)";

    public static String teleport_TELEPORT_TIMER = ChatColor.YELLOW + "" + ChatColor.BOLD + "Du wirst gleich gebeamt! Achtung nicht bewegen ;)";
    public static String teleport_TELEPORT_MOVE_CANCEL = ChatColor.RED + "" + ChatColor.BOLD + "Der Beam ist fehlgeschlagen. Die Partikel konnten nicht zugeordnet werden.";
    public static String teleport_success = ChatColor.GREEN + "" + ChatColor.BOLD + "Du wurdest erfolgreich gebeamt :D";
    /* Warp */
    public static String warp_NO_WARP_ARGUMENT = ChatColor.RED + "" + ChatColor.BOLD + "Du musst einen Warp angeben!";

}
