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

import de.linzn.mineSuite.core.commands.VersionCommand;
import de.linzn.mineSuite.core.configurations.MineConfigs;
import de.linzn.mineSuite.core.database.mysql.MySQLConnectionSetup;
import de.linzn.mineSuite.core.listener.BukkitEventListener;
import de.linzn.mineSuite.core.socket.MineJSocketClient;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public class MineSuiteCorePlugin extends JavaPlugin {
    private static MineSuiteCorePlugin instance;
    private MineJSocketClient mineJSocketClient;
    private MineConfigs mineConfigs;

    public static MineSuiteCorePlugin getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        this.mineConfigs = new MineConfigs(this);
        if (MySQLConnectionSetup.create()) {
            registerListeners();
            getCommand("minesuite").setExecutor(new VersionCommand());
            this.mineJSocketClient = new MineJSocketClient();
            this.mineJSocketClient.jClientConnection1.setEnable();
        } else {
            this.setEnabled(false);
        }
    }

    @Override
    public void onDisable() {
        this.mineJSocketClient.jClientConnection1.setDisable();
        HandlerList.unregisterAll(instance);
    }

    private void registerListeners() {
        getServer().getPluginManager().registerEvents(new BukkitEventListener(), this);
    }

    public MineConfigs getMineConfigs() {
        return mineConfigs;
    }

    public MineJSocketClient getMineJSocketClient() {
        return mineJSocketClient;
    }

}