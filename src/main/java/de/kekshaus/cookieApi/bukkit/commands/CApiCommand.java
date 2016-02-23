package de.kekshaus.cookieApi.bukkit.commands;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import de.kekshaus.cookieApi.bukkit.GlobalMessageDB;

public class CApiCommand implements CommandExecutor {
	public ThreadPoolExecutor executorServiceCommands = new ThreadPoolExecutor(1, 1, 250L, TimeUnit.MILLISECONDS,
			new LinkedBlockingQueue<Runnable>());

	public boolean onCommand(final CommandSender sender, Command cmnd, String label, final String[] args) {
		if (sender.hasPermission("cookieApi.api.command")) {
			this.executorServiceCommands.submit(new Runnable() {
				public void run() {
					sender.sendMessage("CookieApi Helpseite:");
				}
			});
		} else {
			sender.sendMessage(GlobalMessageDB.NO_PERMISSIONS);
		}
		return false;
	}
}
