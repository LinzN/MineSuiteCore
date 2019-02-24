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

package de.linzn.mineSuite.core;

import de.linzn.mineProfile.MineProfilePlugin;
import de.linzn.mineProfile.core.PlayerDataAPI;
import de.linzn.mineProfile.utils.HashDB;
import de.linzn.mineSuite.core.configurations.YamlFiles.GeneralLanguage;
import de.linzn.mineSuite.core.socket.JClientBungeeOutput;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class CoreManager {

    public static void confirmTeleport(UUID playerUUID, String targetServer) {
        MineSuiteCorePlugin.getInstance().getLogger().info("Receive confirm request for: " + playerUUID.toString());
        String thisServer = MineSuiteCorePlugin.getInstance().getMineConfigs().generalConfig.BUNGEE_SERVER_NAME;
        Player player = Bukkit.getPlayer(playerUUID);
        if (player != null) {
            player.sendMessage(GeneralLanguage.teleport_PREPARE);
        }

        if (thisServer.equalsIgnoreCase(targetServer)) {
            MineSuiteCorePlugin.getInstance().getLogger().info("Send confirm callback for: " + playerUUID.toString());
            JClientBungeeOutput.sendTeleportConfirm(playerUUID, targetServer);
        } else {
            if (player != null) {
                if (!MineProfilePlugin.inst().getCookieConfig().disabledWorlds.contains(player.getWorld().getName())) {
                    PlayerDataAPI.unloadProfile(player, true);
                    HashDB.authLock.add(playerUUID);
                }
            }
            MineSuiteCorePlugin.getInstance().getLogger().info("Send confirm callback for: " + playerUUID.toString());
            JClientBungeeOutput.sendTeleportConfirm(playerUUID, targetServer);
        }
    }

    public static void cancelTeleport(UUID playerUUID, String targetServer) {
        String thisServer = MineSuiteCorePlugin.getInstance().getMineConfigs().generalConfig.BUNGEE_SERVER_NAME;
        MineSuiteCorePlugin.getInstance().getLogger().info("Receive cancel request for: " + playerUUID.toString());
        if (!thisServer.equalsIgnoreCase(targetServer)) {
            Player player = Bukkit.getPlayer(playerUUID);
            if (player != null) {
                if (!MineProfilePlugin.inst().getCookieConfig().disabledWorlds.contains(player.getWorld().getName())) {
                    PlayerDataAPI.loadProfile(player);
                }
            }
        }
    }
}
