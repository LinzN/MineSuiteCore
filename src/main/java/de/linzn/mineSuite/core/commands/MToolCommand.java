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

package de.linzn.mineSuite.core.commands;

import com.google.common.collect.Maps;
import de.linzn.mineSuite.core.MineSuiteCorePlugin;
import de.linzn.mineSuite.core.utils.LanguageDB;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.TreeMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MToolCommand implements CommandExecutor {

    private ThreadPoolExecutor cmdThread;
    private MineSuiteCorePlugin plugin;
    private boolean isLoaded = false;
    private TreeMap<String, ICommand> cmdMap = Maps.newTreeMap();

    public MToolCommand(MineSuiteCorePlugin plugin) {
        this.plugin = plugin;
        this.cmdThread = new ThreadPoolExecutor(1, 1, 250L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());

    }

    @Override
    public boolean onCommand(final CommandSender sender, final Command cmd, String label, final String[] args) {
        cmdThread.submit(() -> {
            if (args.length == 0) {
                getCmdMap().get("help").runCmd(sender, args);
            } else if (getCmdMap().containsKey(args[0])) {
                String command = args[0];
                if (!getCmdMap().get(command).runCmd(sender, args)) {
                    sender.sendMessage(LanguageDB.NO_COMMAND.replace("{command}", "/mtools help"));
                }

            } else {
                sender.sendMessage(LanguageDB.NO_COMMAND.replace("{command}", "/mtools help"));
            }

        });
        return true;
    }

    private TreeMap<String, ICommand> getCmdMap() {
        return cmdMap;
    }

    public void loadCmd() {
        try {
            this.cmdMap.put("help", new MTools_HELP(this.plugin, "mineSuite.tools.help"));
            this.cmdMap.put("checkip", new MTools_CHECKIP(this.plugin, "mineSuite.tools.checkip"));
            this.cmdMap.put("lastseen", new MTools_LASTSEEN(this.plugin, "mineSuite.tools.lastseen"));
            this.isLoaded = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isLoaded() {
        return this.isLoaded;
    }
}
