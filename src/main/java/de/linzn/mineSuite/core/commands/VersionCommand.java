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

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class VersionCommand implements CommandExecutor {
    public ThreadPoolExecutor executorServiceCommands = new ThreadPoolExecutor(1, 1, 250L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<>());

    @Override
    public boolean onCommand(final CommandSender sender, Command cmnd, String label, final String[] args) {
        this.executorServiceCommands.submit(() -> {
            Plugin corePlugin = Bukkit.getPluginManager().getPlugin("MineSuiteCore");
            Plugin ban = Bukkit.getPluginManager().getPlugin("MineSuiteBan");
            Plugin chat = Bukkit.getPluginManager().getPlugin("MineSuiteChat");
            Plugin guild = Bukkit.getPluginManager().getPlugin("MineSuiteGuild");
            Plugin home = Bukkit.getPluginManager().getPlugin("MineSuiteHome");
            Plugin portal = Bukkit.getPluginManager().getPlugin("MineSuitePortal");
            Plugin teleport = Bukkit.getPluginManager().getPlugin("MineSuiteSpawn");
            Plugin warp = Bukkit.getPluginManager().getPlugin("MineSuiteWarp");
            sender.sendMessage(ChatColor.DARK_PURPLE + "MineSuite Version:");


            if (corePlugin != null) {
                sender.sendMessage(ChatColor.DARK_GREEN + "MineSuiteCore: " + ChatColor.DARK_PURPLE
                        + ChatColor.BOLD + corePlugin.getDescription().getVersion());
            }
            if (ban != null) {
                sender.sendMessage(ChatColor.DARK_GREEN + "MineSuiteBan: " + ChatColor.DARK_PURPLE + ChatColor.BOLD
                        + ban.getDescription().getVersion());
            }
            if (chat != null) {
                sender.sendMessage(ChatColor.DARK_GREEN + "MineSuiteChat: " + ChatColor.DARK_PURPLE + ChatColor.BOLD
                        + chat.getDescription().getVersion());
            }
            if (guild != null) {
                sender.sendMessage(ChatColor.DARK_GREEN + "MineSuiteGuild: " + ChatColor.DARK_PURPLE
                        + ChatColor.BOLD + guild.getDescription().getVersion());
            }
            if (home != null) {
                sender.sendMessage(ChatColor.DARK_GREEN + "MineSuiteHome: " + ChatColor.DARK_PURPLE + ChatColor.BOLD
                        + home.getDescription().getVersion());
            }
            if (portal != null) {
                sender.sendMessage(ChatColor.DARK_GREEN + "MineSuitePortal: " + ChatColor.DARK_PURPLE
                        + ChatColor.BOLD + portal.getDescription().getVersion());
            }
            if (teleport != null) {
                sender.sendMessage(ChatColor.DARK_GREEN + "MineSuiteSpawn: " + ChatColor.DARK_PURPLE
                        + ChatColor.BOLD + teleport.getDescription().getVersion());
            }
            if (warp != null) {
                sender.sendMessage(ChatColor.DARK_GREEN + "MineSuiteWarp: " + ChatColor.DARK_PURPLE + ChatColor.BOLD
                        + warp.getDescription().getVersion());
            }

            sender.sendMessage(ChatColor.GREEN + "Written with love by Kekshaus");
            sender.sendMessage(ChatColor.GREEN + "For more MineSuite informations, check this out:");
            sender.sendMessage(ChatColor.GREEN + "MineGaming - " + ChatColor.YELLOW + ChatColor.BOLD
                    + "https://www.MineGaming.de");
            sender.sendMessage(ChatColor.GREEN + "You want cookies? Sorry they're all out :(");
        });

        return false;
    }
}
