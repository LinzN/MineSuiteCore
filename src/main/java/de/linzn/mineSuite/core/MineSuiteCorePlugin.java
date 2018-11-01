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

import de.linzn.mineSuite.core.commands.MToolCommand;
import de.linzn.mineSuite.core.commands.VersionCommand;
import de.linzn.mineSuite.core.configurations.MineConfigs;
import de.linzn.mineSuite.core.database.mysql.MySQLConnectionSetup;
import de.linzn.mineSuite.core.listener.BukkitEventListener;
import de.linzn.mineSuite.core.socket.JClientBungeeListener;
import de.linzn.mineSuite.core.socket.MineJSocketClient;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class MineSuiteCorePlugin extends JavaPlugin {
    private static MineSuiteCorePlugin instance;
    private MineJSocketClient mineJSocketClient;
    private MineConfigs mineConfigs;
    private Economy econ = null;
    private Chat chat = null;

    public static MineSuiteCorePlugin getInstance() {
        return instance;
    }

    public static Economy getEconomy() {
        return getInstance().econ;
    }

    public static Chat getChat() {
        return getInstance().chat;
    }

    private void setupChat() {
        RegisteredServiceProvider<Chat> chatProvider = getServer().getServicesManager()
                .getRegistration(net.milkbowl.vault.chat.Chat.class);
        if (chatProvider != null) {
            chat = chatProvider.getProvider();
            this.getLogger().info("Using Vault for Chatprovider!");
        }
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        this.econ = rsp.getProvider();
        return this.econ != null;
    }


    @Override
    public void onEnable() {
        instance = this;
        this.mineConfigs = new MineConfigs(this);
        if (MySQLConnectionSetup.create()) {
            if (!setupEconomy()) {
                this.getLogger().warning(
                        String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
                getServer().getPluginManager().disablePlugin(this);
                return;
            }
            setupChat();
            registerListeners();
            getCommand("minesuite").setExecutor(new VersionCommand());
            MToolCommand mToolCommand = new MToolCommand(this);
            if (!mToolCommand.isLoaded())
                mToolCommand.loadCmd();
            getCommand("mtools").setExecutor(mToolCommand);
            this.mineJSocketClient = new MineJSocketClient();
            this.mineJSocketClient.jClientConnection1.setEnable();
            this.mineJSocketClient.jClientConnection1.registerIncomingDataListener("mineSuiteBungee", new JClientBungeeListener());
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
