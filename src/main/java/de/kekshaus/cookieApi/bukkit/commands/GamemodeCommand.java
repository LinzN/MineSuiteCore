package de.kekshaus.cookieApi.bukkit.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.kekshaus.cookieApi.bukkit.MessageDB;

public class GamemodeCommand implements CommandExecutor {

	public boolean onCommand(final CommandSender sender, Command cmnd, String label, final String[] args) {
		if (sender.hasPermission("cookieApi.api.gamemode")) {

			Player player = null;
			GameMode gamemode = null;
			if (args.length == 1) {
				player = (Player) sender;

			} else if (args.length == 2) {
				player = Bukkit.getPlayer(args[1]);
				if (player == null) {
					return false;
				}

			}

			if (args[0].equalsIgnoreCase("0") && args[0].equalsIgnoreCase("survival")) {
				gamemode = GameMode.SURVIVAL;
			} else if (args[0].equalsIgnoreCase("1") && args[0].equalsIgnoreCase("creative")) {
				gamemode = GameMode.CREATIVE;
			} else if (args[0].equalsIgnoreCase("2") && args[0].equalsIgnoreCase("adventure")) {
				gamemode = GameMode.ADVENTURE;
			} else if (args[0].equalsIgnoreCase("3") && args[0].equalsIgnoreCase("spectator")) {
				gamemode = GameMode.SPECTATOR;
			}
			player.setGameMode(gamemode);
			player.sendMessage("§2Gamemode zu §e" + gamemode.getDeclaringClass().getName() + "§2 geändert!");

		} else {
			sender.sendMessage(MessageDB.NO_PERMISSIONS);
		}
		return false;
	}
}
