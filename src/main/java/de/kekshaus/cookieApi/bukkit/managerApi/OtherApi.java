package de.kekshaus.cookieApi.bukkit.managerApi;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.kekshaus.cookieApi.bukkit.CookieApiBukkit;
import de.kekshaus.cookieApi.bukkit.tasks.SendBungeeTeleportMessage;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

public class OtherApi {
	public static HashMap<String, Player> pendingTeleports = new HashMap<String, Player>();
	public static HashMap<String, Location> pendingTeleportLocations = new HashMap<String, Location>();
	public static HashMap<String, String> pendingStringMessage = new HashMap<String, String>();
	public static HashSet<Player> ignoreTeleport = new HashSet<Player>();
	public static HashMap<Player, Location> lastLocation = new HashMap<Player, Location>();

	public static void RemovePlayer(Player player) {
		pendingTeleports.remove(player.getName());
		pendingStringMessage.remove(player.getName());
		pendingTeleportLocations.remove(player.getName());
		ignoreTeleport.remove(player);
		lastLocation.remove(player);
	}

	public static void sendDeathBackLocation(Player p) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try {
			out.writeUTF("PlayersDeathBackLocation");
			out.writeUTF(p.getName());
			Location l = p.getLocation();
			out.writeUTF(l.getWorld().getName());
			out.writeDouble(l.getX());
			out.writeDouble(l.getY());
			out.writeDouble(l.getZ());
			out.writeFloat(l.getYaw());
			out.writeFloat(l.getPitch());
		} catch (IOException e) {
			e.printStackTrace();
		}

		CookieApiBukkit.getInstance().getServer().getScheduler().runTaskAsynchronously(CookieApiBukkit.getInstance(),
				new SendBungeeTeleportMessage(b));
	}

	public static void sendPlayerBack(final CommandSender sender) {
		final Player player = Bukkit.getPlayer(sender.getName());

		player.saveData();
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try {
			out.writeUTF("SendPlayerBack");
			out.writeUTF(sender.getName());
			out.writeBoolean(true);
			out.writeBoolean(false);
		} catch (IOException e) {
			e.printStackTrace();
		}

		CookieApiBukkit.getInstance().getServer().getScheduler().runTaskAsynchronously(CookieApiBukkit.getInstance(),
				new SendBungeeTeleportMessage(b));
	}

	public static void toggleTeleports(String name) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try {
			out.writeUTF("ToggleTeleports");
			out.writeUTF(name);
		} catch (IOException e) {
			e.printStackTrace();
		}

		CookieApiBukkit.getInstance().getServer().getScheduler().runTaskAsynchronously(CookieApiBukkit.getInstance(),
				new SendBungeeTeleportMessage(b));
	}

	public static void sendOtherServer(String player, String server) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try {
			out.writeUTF("TeleportToServer");
			out.writeUTF(player);
			out.writeUTF(server);
		} catch (IOException e) {
			e.printStackTrace();
		}

		CookieApiBukkit.getInstance().getServer().getScheduler().runTaskAsynchronously(CookieApiBukkit.getInstance(),
				new SendBungeeTeleportMessage(b));
	}

}
