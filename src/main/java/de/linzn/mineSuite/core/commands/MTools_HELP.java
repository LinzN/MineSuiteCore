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
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MTools_HELP implements ICommand {
    private MineSuiteCorePlugin plugin;
    private String permission;


    public MTools_HELP(MineSuiteCorePlugin plugin, String permission) {
        this.plugin = plugin;
        this.permission = permission;
    }

    @Override
    public boolean runCmd(CommandSender sender, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(LanguageDB.NO_CONSOLE);
            return true;
        }
        Player player = (Player) sender;

        if (!player.hasPermission(permission)) {
            player.sendMessage(LanguageDB.NO_PERMISSIONS);
            return true;
        }
        if (args.length >= 2) {
            if (args[1].equalsIgnoreCase("2")) {
                player.sendMessage(("§6§lCommon help: "));
                player.sendMessage(" §2Compare IP: §e/mtools checkip <firstPlayer> <secondPlayer>");
                player.sendMessage(" §2Switch Debugging: §e/mtools debug");
                return true;
            }
        }

        player.sendMessage("§e§n§6§l-===============[§2§lMSuite Tools§r§6§l]===============-");
        player.sendMessage("§2 Check last online: §e/mtools lastseen <player>");
        player.sendMessage("§2 MineSuite versions: §e/minesuite");
        player.sendMessage("§6§lOverview MSuite tools help pages:");
        player.sendMessage(" §2Common help §a/mtools help 1 - 2");
        return true;


    }
}
