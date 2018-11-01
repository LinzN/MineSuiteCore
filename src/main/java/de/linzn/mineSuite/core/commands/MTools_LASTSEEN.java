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
import de.linzn.mineSuite.core.socket.JClientBungeeOutput;
import de.linzn.mineSuite.core.utils.LanguageDB;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MTools_LASTSEEN implements ICommand {
    private MineSuiteCorePlugin plugin;
    private String permission;


    public MTools_LASTSEEN(MineSuiteCorePlugin plugin, String permission) {
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
        if (args.length < 2) {
            player.sendMessage(LanguageDB.COMMAND_USAGE.replace("{command}", "/mtools lastseen <player>"));
            return true;
        }

        String targetPlayer = args[1];
        JClientBungeeOutput.request_last_seen(player.getUniqueId(), targetPlayer);

        return true;
    }
}
