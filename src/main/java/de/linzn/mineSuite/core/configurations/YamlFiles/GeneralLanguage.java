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
    public static String global_NO_NUMBER = ChatColor.RED + "" + ChatColor.BOLD + "Dies ist leider keine Zahl!";

    /* Teleport */
    public static String teleport_PREPARE = ChatColor.YELLOW + "" + ChatColor.BOLD + "Aktion wird vorbereitet. Nicht bewegen ;)";
    public static String teleport_TELEPORT_MOVE_CANCEL = ChatColor.RED + "" + ChatColor.BOLD + "Der Beam ist fehlgeschlagen. Die Partikel konnten nicht zugeordnet werden.";
    public static String teleport_success = ChatColor.GREEN + "" + ChatColor.BOLD + "Du wurdest erfolgreich gebeamt :D";

    public static String teleport_SETSPAWN_USAGE = ChatColor.RED + "" + ChatColor.BOLD + "Du musst einen SpawnType angeben. Type: ServerSpawn, lobby";
    public static String teleport_UNSETSPAWN_USAGE = ChatColor.RED + "" + ChatColor.BOLD + "Du musst einen SpawnType angeben. Type: ServerSpawn oder lobby";
    public static String teleport_TPA_USAGE = ChatColor.RED + "" + ChatColor.BOLD + "Du musst einen Spielernamen angeben. Beispiel: /tpa Hundemaunzen";
    public static String teleport_TPAHERE_USAGE = ChatColor.RED + "" + ChatColor.BOLD + "Du musst einen Spielernamen angeben. Beispiel: /tpahere Hundemaunzen";
    public static String teleport_TPHERE_USAGE = ChatColor.RED + "" + ChatColor.BOLD + "Du musst einen Spielernamen angeben. Beispiel: /tphere Kekshaus";

    public static String teleport_TP_0 = ChatColor.GREEN + "" + ChatColor.BOLD + "Teleport Befehle:";
    public static String teleport_TP_1 = ChatColor.YELLOW + "/tp <Playername>";
    public static String teleport_TP_2 = ChatColor.YELLOW + "/tp <Playername> <Playername>";
    public static String teleport_TP_3 = ChatColor.YELLOW + "/tp <X> <Y> <Z>";
    public static String teleport_TP_4 = ChatColor.YELLOW + "/tp <World> <X> <Y> <Z>";
    public static String teleport_TP_5 = ChatColor.YELLOW + "/tp <Server> <World> <X> <Y> <Z>";

    /* Ban */
    public static String ban_NO_VALID_TIME = ChatColor.RED + "" + ChatColor.BOLD + "Keine gültige Zeitangabe!";
    public static String ban_BAN_PERM_USAGE = ChatColor.RED + "" + ChatColor.BOLD + "/ban <Playername> <Grund>";
    public static String ban_KICK_USAGE = ChatColor.RED + "" + ChatColor.BOLD + "/kick <Playername> <Grund>";
    public static String ban_MUTE_PERM_USAGE = ChatColor.RED + "" + ChatColor.BOLD + "/mute <Playername> <Grund>";
    public static String ban_BAN_TEMP_USAGE = ChatColor.RED + "" + ChatColor.BOLD + "/tempban <Playername> <Dauer> <Grund>";
    public static String ban_MUTE_TEMP_USAGE = ChatColor.RED + "" + ChatColor.BOLD + "/tempmute <Playername> <Time> <Grund>";
    public static String ban_UNBAN_USAGE = ChatColor.RED + "" + ChatColor.BOLD + "/unban <Playername> <Grund>\"";
    public static String ban_UNMUTE_USAGE = ChatColor.RED + "" + ChatColor.BOLD + "/unmute <Playername> <Grund>";

    /* Chat */
    public static String chat_AFK_ON = ChatColor.GREEN + "" + ChatColor.BOLD + "Du wirst jetzt als abwesend makiert!";
    public static String chat_AFK_OFF = ChatColor.GREEN + "" + ChatColor.BOLD + "Du wirst jetzt nicht mehr als abwesend makiert!";
    public static String chat_SWITCH_DISABLED = ChatColor.RED + "" + ChatColor.BOLD + "Switch zu diesem Channel leider nicht möglich!";
    public static String chat_SWITCH = ChatColor.GREEN + "" + ChatColor.BOLD + "Du schreibst jetzt im " + ChatColor.YELLOW + "{channel} " + ChatColor.GREEN + "" + ChatColor.BOLD + "Chat!";
    public static String chat_NO_TEXT = ChatColor.RED + "" + ChatColor.BOLD + "Du musst schon einen Text angeben :D";
    public static String chat_MSG_USAGE = ChatColor.RED + "" + ChatColor.BOLD + "Benutze: /tell <Spieler> <Text>";

    /* Warp */
    public static String warp_NO_WARP_ARGUMENT = ChatColor.RED + "" + ChatColor.BOLD + "Du musst einen Warp angeben! Beispiel: /w lobby";
    public static String warp_SETWARP_USAGE = ChatColor.RED + "" + ChatColor.BOLD + "Du musst einen Warp angeben! Beispiel: /setwarp lobby";
    public static String warp_UNSETWARP_USAGE = ChatColor.RED + "" + ChatColor.BOLD + "Du musst einen Warp angeben! Beispiel: /unsetwarp lobby";

    /* Portal */
    public static String portal_SETPORTAL_USAGE = ChatColor.RED + "" + ChatColor.BOLD + "Benutze: /setportal [portalName] [portalType] [destination] <portalMaterial>";
    public static String portal_UNSETPORTAL_USAGE = ChatColor.RED + "" + ChatColor.BOLD + "Benutze: /unsetportal [portalName]";
    public static String portal_NO_SELECTION = ChatColor.RED + "" + ChatColor.BOLD + "Du hast keine Region ausgewählt!";
}
