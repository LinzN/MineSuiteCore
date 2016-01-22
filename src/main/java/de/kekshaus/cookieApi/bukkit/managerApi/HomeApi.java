package de.kekshaus.cookieApi.bukkit.managerApi;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import de.kekshaus.cookieApi.bukkit.CookieApiBukkit;
import de.kekshaus.cookieApi.bukkit.tasks.SendBungeeTeleportMessage;
import de.kekshaus.cookieApi.bukkit.utils.LocationUtil;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class HomeApi {

	public static void teleportToHome(final String player, String world, double x, double y, double z, float yaw,
			float pitch) {
		World w = Bukkit.getWorld(world);
		Location t;

		if (w != null) {
			t = new Location(w, x, y, z, yaw, pitch);
		} else {
			w = Bukkit.getWorlds().get(0);
			t = w.getSpawnLocation();
		}
		Player p = Bukkit.getPlayer(player);
		String sendHome = ChatColor.GREEN + "Du wurdest zu deinem Home teleportiert!";
		if (p != null) {
			// Check if Block is safe
			if (LocationUtil.isBlockUnsafe(t.getWorld(), t.getBlockX(), t.getBlockY(), t.getBlockZ())) {
				try {
					Location l = LocationUtil.getSafeDestination(p, t);
					if (l != null) {
						p.teleport(l);
						p.sendMessage(sendHome);
						return;
					} else {
						p.sendMessage(ChatColor.RED + "Unable to find a safe location for teleport.");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				p.teleport(t);
				p.sendMessage(sendHome);
				return;
			}

		} else {

			OtherApi.pendingTeleportLocations.put(player, t);
			OtherApi.pendingStringMessage.put(player, sendHome);

			// clear pending teleport if they dont connect
			Bukkit.getScheduler().runTaskLaterAsynchronously(CookieApiBukkit.getInstance(), new Runnable() {
				@Override
				public void run() {
					if (OtherApi.pendingTeleportLocations.containsKey(player)) {
						OtherApi.pendingTeleportLocations.remove(player);
					}
					if (OtherApi.pendingStringMessage.containsKey(player)) {
						OtherApi.pendingStringMessage.remove(player);
					}
				}
			}, 100L);
		}
	}

	public static void sendTeleportToHomeOut(String player, String server, String world, Double x, Double y, Double z,
			Float yaw, Float pitch) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try {
			out.writeUTF("TeleportToHome");
			out.writeUTF(player);
			out.writeUTF(server);
			out.writeUTF(world);
			out.writeDouble(x);
			out.writeDouble(y);
			out.writeDouble(z);
			out.writeFloat(yaw);
			out.writeFloat(pitch);
		} catch (IOException e) {
			e.printStackTrace();
		}

		CookieApiBukkit.getInstance().getServer().getScheduler().runTaskAsynchronously(CookieApiBukkit.getInstance(),
				new SendBungeeTeleportMessage(b));
	}

}
