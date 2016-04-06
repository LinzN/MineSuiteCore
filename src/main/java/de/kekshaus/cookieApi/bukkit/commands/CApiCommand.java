package de.kekshaus.cookieApi.bukkit.commands;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import net.md_5.bungee.api.ChatColor;

public class CApiCommand implements CommandExecutor {
	public ThreadPoolExecutor executorServiceCommands = new ThreadPoolExecutor(1, 1, 250L, TimeUnit.MILLISECONDS,
			new LinkedBlockingQueue<Runnable>());

	public boolean onCommand(final CommandSender sender, Command cmnd, String label, final String[] args) {
		this.executorServiceCommands.submit(new Runnable() {
			public void run() {
				Plugin bukkit = Bukkit.getPluginManager().getPlugin("cookieApiBukkit");
				Plugin ban = Bukkit.getPluginManager().getPlugin("cookieApiBan");
				Plugin chat = Bukkit.getPluginManager().getPlugin("cookieApiChat");
				Plugin guild = Bukkit.getPluginManager().getPlugin("cookieApiGuild");
				Plugin home = Bukkit.getPluginManager().getPlugin("cookieApiHome");
				Plugin portal = Bukkit.getPluginManager().getPlugin("cookieApiPortal");
				Plugin teleport = Bukkit.getPluginManager().getPlugin("cookieApiTeleport");
				Plugin warp = Bukkit.getPluginManager().getPlugin("cookieApiWarp");
				sender.sendMessage(ChatColor.DARK_PURPLE + "CookieApi Version:");
				if (bukkit != null) {
					sender.sendMessage(ChatColor.DARK_GREEN + "CookieApiBukkit: " + ChatColor.DARK_PURPLE
							+ ChatColor.BOLD + bukkit.getDescription().getVersion());
				}
				if (ban != null) {
					sender.sendMessage(ChatColor.DARK_GREEN + "CookieApiBan: " + ChatColor.DARK_PURPLE + ChatColor.BOLD
							+ ban.getDescription().getVersion());
				}
				if (chat != null) {
					sender.sendMessage(ChatColor.DARK_GREEN + "CookieApiChat: " + ChatColor.DARK_PURPLE + ChatColor.BOLD
							+ chat.getDescription().getVersion());
				}
				if (guild != null) {
					sender.sendMessage(ChatColor.DARK_GREEN + "CookieApiGuild: " + ChatColor.DARK_PURPLE
							+ ChatColor.BOLD + guild.getDescription().getVersion());
				}
				if (home != null) {
					sender.sendMessage(ChatColor.DARK_GREEN + "CookieApiHome: " + ChatColor.DARK_PURPLE + ChatColor.BOLD
							+ home.getDescription().getVersion());
				}
				if (portal != null) {
					sender.sendMessage(ChatColor.DARK_GREEN + "CookieApiPortal: " + ChatColor.DARK_PURPLE
							+ ChatColor.BOLD + portal.getDescription().getVersion());
				}
				if (teleport != null) {
					sender.sendMessage(ChatColor.DARK_GREEN + "CookieApiTeleport: " + ChatColor.DARK_PURPLE
							+ ChatColor.BOLD + teleport.getDescription().getVersion());
				}
				if (warp != null) {
					sender.sendMessage(ChatColor.DARK_GREEN + "CookieApiWarp: " + ChatColor.DARK_PURPLE + ChatColor.BOLD
							+ warp.getDescription().getVersion());
				}
			}
		});

		return false;
	}
}
