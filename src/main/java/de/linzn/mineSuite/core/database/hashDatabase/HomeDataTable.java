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

public class HomeDataTable {
    public static HashMap<String, Player> pendingHome = new HashMap<>();
    public static HashMap<String, Location> pendingHomeLocations = new HashMap<>();
    public static HashSet<Player> ignoreHome = new HashSet<>();
    public static HashMap<Player, Location> lastHomeLocation = new HashMap<>();

    public static void removePlayer(Player player) {
        pendingHome.remove(player.getName());
        pendingHomeLocations.remove(player.getName());
        ignoreHome.remove(player);
        lastHomeLocation.remove(player);
    }

}