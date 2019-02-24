package de.linzn.mineSuite.core.database;

import de.linzn.mineSuite.core.database.cache.RequestMojangAPI;
import org.bukkit.Bukkit;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CacheManager {
    private static Map<UUID, String> playerCache = new HashMap<>();

    public static UUID getPlayerUUID(String playerName, boolean localOnly) {
        if (playerCache.containsValue(playerName)) {
            for (UUID uuid : playerCache.keySet()) {
                if (playerCache.get(uuid).equalsIgnoreCase(playerName)) {
                    return uuid;
                }
            }
        }

        UUID playerUUID = BukkitQuery.getUUID(playerName);

        if (playerUUID != null) {
            playerCache.put(playerUUID, playerName);
            return playerUUID;
        } else {
            if(!localOnly) {
                playerUUID = new RequestMojangAPI().fetchUUID(playerName);
            }
        }

        //if (playerUUID == null) {
        //    playerUUID = Bukkit.getOfflinePlayer(playerName).getUniqueId();
        //}

        if (playerUUID != null) {
            playerCache.put(playerUUID, playerName);
        }
        return playerUUID;
    }

    public UUID[] getPlayerUUIDs(String[] playerNames, boolean localOnly) {
        UUID[] playerUUIDS = new UUID[playerNames.length];
        for (int i = 0; i < playerNames.length; i++) {
            playerNames[i] = getPlayerName(playerUUIDS[i], localOnly);
        }
        return playerUUIDS;
    }

    public static String getPlayerName(UUID playerUUID, boolean localOnly) {
        if (playerCache.containsKey(playerUUID)) {
            return playerCache.get(playerUUID);
        }
        String playerName = BukkitQuery.getPlayerName(playerUUID);
        if (playerName != null) {
            playerCache.put(playerUUID, playerName);
            return playerName;
        } else {
            if(!localOnly) {
                playerName = new RequestMojangAPI().fetchName(playerUUID);
            }
        }

        //if (playerName == null) {
        //    playerName = Bukkit.getOfflinePlayer(playerUUID).getName();
        //}+

        if (playerName != null) {
            playerCache.put(playerUUID, playerName);
        }
        return playerName;
    }

    public String[] getPlayerNames(UUID[] playerUUIDs, boolean localOnly) {
        String[] playerNames = new String[playerUUIDs.length];
        for (int i = 0; i < playerNames.length; i++) {
            playerNames[i] = getPlayerName(playerUUIDs[i], localOnly);
        }
        return playerNames;
    }
}
