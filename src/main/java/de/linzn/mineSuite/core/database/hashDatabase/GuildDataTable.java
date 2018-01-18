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

package de.linzn.mineSuite.core.database.hashDatabase;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

public class GuildDataTable {

    public static HashMap<String, Player> pendingGuild = new HashMap<>();
    public static HashMap<String, Location> pendingGuildLocations = new HashMap<>();
    public static HashSet<Player> ignoreGuild = new HashSet<>();
    public static HashMap<Player, Location> lastGuildLocation = new HashMap<>();
    public static HashMap<UUID, Long> expCache = new HashMap<>();
    private static HashSet<String> invite = new HashSet<>();

    public static void removePlayer(Player player) {
        pendingGuild.remove(player.getName());
        pendingGuildLocations.remove(player.getName());
        ignoreGuild.remove(player);
        lastGuildLocation.remove(player);
    }

    public static boolean hasInvite(String pName) {
        if (invite.contains(pName)) {
            return true;
        }
        return false;
    }

    public static void addInvite(String pName) {
        invite.add(pName);
    }

    public static void removeInvite(String pName) {
        invite.remove(pName);
    }

}