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

public class WarpDataTable {
    public static HashMap<String, Player> pendingWarp = new HashMap<>();
    public static HashMap<String, Location> pendingWarpLocations = new HashMap<>();
    public static HashSet<Player> ignoreWarp = new HashSet<>();
    public static HashMap<Player, Location> lastWarpLocation = new HashMap<>();

    public static void removeWarpPlayer(Player player) {
        pendingWarp.remove(player.getName());
        pendingWarpLocations.remove(player.getName());
        ignoreWarp.remove(player);
        lastWarpLocation.remove(player);
    }

}