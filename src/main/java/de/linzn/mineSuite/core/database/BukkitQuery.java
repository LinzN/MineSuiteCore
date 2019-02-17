package de.linzn.mineSuite.core.database;

import de.linzn.mineSuite.core.database.mysql.MySQLConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class BukkitQuery {

    public static UUID getUUID(String player) {
        MySQLConnectionManager manager = MySQLConnectionManager.DEFAULT;
        UUID uuid = null;

        try {
            Connection conn = manager.getConnection("mineSuiteCore");
            PreparedStatement sql = conn.prepareStatement("SELECT UUID FROM core_uuidcache WHERE NAME = '" + player + "';");
            ResultSet result = sql.executeQuery();
            if (result.next()) {
                uuid = UUID.fromString(result.getString(1));
            }
            result.close();
            sql.close();
            manager.release("mineSuiteCore", conn);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return uuid;
    }

    public static String getPlayerName(UUID playerUUID) {
        MySQLConnectionManager manager = MySQLConnectionManager.DEFAULT;
        String playerName = null;

        try {
            Connection conn = manager.getConnection("mineSuiteCore");
            PreparedStatement sql = conn.prepareStatement("SELECT NAME FROM core_uuidcache WHERE UUID = '" + playerUUID + "';");
            ResultSet result = sql.executeQuery();
            if (result.next()) {
                playerName = result.getString(1);
            }
            result.close();
            sql.close();
            manager.release("mineSuiteCore", conn);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return playerName;
    }

    public static String getLastIPAddress(String playerName) {
        UUID playerUUID = getUUID(playerName);
        if (playerUUID != null) {
            return getLastIPAddress(playerUUID);
        }
        return null;
    }

    public static String getLastIPAddress(UUID playerUUID) {
        MySQLConnectionManager manager = MySQLConnectionManager.DEFAULT;
        String lastIP = null;

        try {
            Connection conn = manager.getConnection("mineSuiteCore");
            PreparedStatement sql = conn.prepareStatement("SELECT LASTIP FROM core_uuidcache WHERE UUID = '" + playerUUID + "';");
            ResultSet result = sql.executeQuery();
            if (result.next()) {
                lastIP = result.getString(1);
            }
            result.close();
            sql.close();
            manager.release("mineSuiteCore", conn);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lastIP;
    }

    public static long getLastPlayerLogin(UUID uuid) {
        MySQLConnectionManager manager = MySQLConnectionManager.DEFAULT;
        long lastLogin = 0;
        try {
            Connection conn = manager.getConnection("mineSuiteCore");
            PreparedStatement sql = conn.prepareStatement("SELECT TIMESTAMP FROM core_uuidcache WHERE UUID = '" + uuid + "';");
            ResultSet result = sql.executeQuery();

            if (result.next()) {
                lastLogin = result.getLong(1);
            }
            result.close();
            sql.close();
            manager.release("mineSuiteCore", conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lastLogin;
    }

}
