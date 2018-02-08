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

import de.linzn.mineSuite.core.MineSuiteCorePlugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

public class PendingTeleportsData {

    public static HashMap<UUID, Location> pendingLocations = new HashMap<>();
    public static HashSet<UUID> ignoreActions = new HashSet<>();
    public static HashSet<UUID> playerCommand = new HashSet<>();

    public static void addCommandSpam(UUID playerUUID) {
        playerCommand.add(playerUUID);
        Bukkit.getScheduler().runTaskLaterAsynchronously(MineSuiteCorePlugin.getInstance(), () -> playerCommand.remove(playerUUID), 40L);
    }


    public static void cleanPlayer(UUID playerUUID) {
        pendingLocations.remove(playerUUID);
        ignoreActions.remove(playerUUID);
        playerCommand.remove(playerUUID);
    }

}