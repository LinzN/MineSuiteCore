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

import de.linzn.mineSuite.core.MineSuiteCorePlugin;
import de.linzn.mineSuite.core.utils.LanguageDB;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MTools_DEBUG implements ICommand {
    private MineSuiteCorePlugin plugin;
    private String permission;


    public MTools_DEBUG(MineSuiteCorePlugin plugin, String permission) {
        this.plugin = plugin;
        this.permission = permission;
    }

    @Override
    public boolean runCmd(CommandSender sender, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (!player.hasPermission(permission)) {
                player.sendMessage(LanguageDB.NO_PERMISSIONS);
                return true;
            }
        }

        if (this.plugin.getDebugMode()) {
            sender.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "MineSuiteCore debugger disabled");
            this.plugin.setDebugMode(false);
        } else {
            sender.sendMessage(ChatColor.DARK_RED + "" + ChatColor.BOLD + "MineSuiteCore debugger enabled");
            this.plugin.setDebugMode(true);
        }
        return true;
    }
}
