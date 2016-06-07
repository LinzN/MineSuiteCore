package de.nlinz.xeonSuite.bukkit.commands;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import net.md_5.bungee.api.ChatColor;

public class SuiteVersionCommand implements CommandExecutor {
	public ThreadPoolExecutor executorServiceCommands = new ThreadPoolExecutor(1, 1, 250L, TimeUnit.MILLISECONDS,
			new LinkedBlockingQueue<Runnable>());

	@Override
	public boolean onCommand(final CommandSender sender, Command cmnd, String label, final String[] args) {
		this.executorServiceCommands.submit(new Runnable() {
			@Override
			public void run() {
				Plugin bukkit = Bukkit.getPluginManager().getPlugin("XeonSuiteBukkit");
				Plugin ban = Bukkit.getPluginManager().getPlugin("XeonSuiteBan");
				Plugin chat = Bukkit.getPluginManager().getPlugin("XeonSuiteChat");
				Plugin guild = Bukkit.getPluginManager().getPlugin("XeonSuiteGuild");
				Plugin home = Bukkit.getPluginManager().getPlugin("XeonSuiteHome");
				Plugin portal = Bukkit.getPluginManager().getPlugin("XeonSuitePortal");
				Plugin teleport = Bukkit.getPluginManager().getPlugin("XeonSuiteTeleport");
				Plugin warp = Bukkit.getPluginManager().getPlugin("XeonSuiteWarp");
				Plugin land = Bukkit.getPluginManager().getPlugin("XeonSuiteLand");
				sender.sendMessage(ChatColor.DARK_PURPLE + "XeonSuite Version:");
				if (bukkit != null) {
					sender.sendMessage(ChatColor.DARK_GREEN + "XeonSuiteBukkit: " + ChatColor.DARK_PURPLE
							+ ChatColor.BOLD + bukkit.getDescription().getVersion());
				}
				if (ban != null) {
					sender.sendMessage(ChatColor.DARK_GREEN + "XeonSuiteBan: " + ChatColor.DARK_PURPLE + ChatColor.BOLD
							+ ban.getDescription().getVersion());
				}
				if (chat != null) {
					sender.sendMessage(ChatColor.DARK_GREEN + "XeonSuiteChat: " + ChatColor.DARK_PURPLE + ChatColor.BOLD
							+ chat.getDescription().getVersion());
				}
				if (guild != null) {
					sender.sendMessage(ChatColor.DARK_GREEN + "XeonSuiteGuild: " + ChatColor.DARK_PURPLE
							+ ChatColor.BOLD + guild.getDescription().getVersion());
				}
				if (home != null) {
					sender.sendMessage(ChatColor.DARK_GREEN + "XeonSuiteHome: " + ChatColor.DARK_PURPLE + ChatColor.BOLD
							+ home.getDescription().getVersion());
				}
				if (portal != null) {
					sender.sendMessage(ChatColor.DARK_GREEN + "XeonSuitePortal: " + ChatColor.DARK_PURPLE
							+ ChatColor.BOLD + portal.getDescription().getVersion());
				}
				if (teleport != null) {
					sender.sendMessage(ChatColor.DARK_GREEN + "XeonSuiteTeleport: " + ChatColor.DARK_PURPLE
							+ ChatColor.BOLD + teleport.getDescription().getVersion());
				}
				if (warp != null) {
					sender.sendMessage(ChatColor.DARK_GREEN + "XeonSuiteWarp: " + ChatColor.DARK_PURPLE + ChatColor.BOLD
							+ warp.getDescription().getVersion());
				}
				if (land != null) {
					sender.sendMessage(ChatColor.DARK_GREEN + "XeonSuiteLand: " + ChatColor.DARK_PURPLE + ChatColor.BOLD
							+ land.getDescription().getVersion());
				}
			}
		});

		return false;
	}
}
