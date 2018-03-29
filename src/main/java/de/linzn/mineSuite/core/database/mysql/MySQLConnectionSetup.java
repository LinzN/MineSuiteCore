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

package de.linzn.mineSuite.core.database.mysql;

import de.linzn.mineSuite.core.MineSuiteCorePlugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.sql.Connection;
import java.sql.Statement;

public class MySQLConnectionSetup {
    public static boolean create() {
        return mysql();

    }

    public static boolean mysql() {
        String db = MineSuiteCorePlugin.getInstance().getMineConfigs().generalConfig.SQL_DATABASE;
        int port = MineSuiteCorePlugin.getInstance().getMineConfigs().generalConfig.SQL_PORT;
        String host = MineSuiteCorePlugin.getInstance().getMineConfigs().generalConfig.SQL_HOST;
        String url = "jdbc:mysql://" + host + ":" + port + "/" + db;
        String username = MineSuiteCorePlugin.getInstance().getMineConfigs().generalConfig.SQL_USER;
        String password = MineSuiteCorePlugin.getInstance().getMineConfigs().generalConfig.SQL_PASSWORD;
        MySQLConnectionFactory factory = new MySQLConnectionFactory(url, username, password);
        MySQLConnectionManager manager = MySQLConnectionManager.DEFAULT;
        MySQLConnectionHandler mineSuiteCoreHandler = manager.getHandler("MineSuiteCore", factory);
        manager.getHandler("mineSuiteGuild", factory);

        try {
            Connection connection = mineSuiteCoreHandler.getConnection();
            Statement action = connection.createStatement();
            action.close();
            mineSuiteCoreHandler.release(connection);
            return true;

        } catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "=================MineSuiteCore-ERROR================");
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Unable to connect to database.");
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "Pls check you mysql connection in general.yml.");
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "=================MineSuiteCore-ERROR================");
            e.printStackTrace();
            return false;
        }

    }

}
