package de.kekshaus.cookieApi.bukkit.managerApi;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.kekshaus.cookieApi.bukkit.MessageDB;
import de.kekshaus.cookieApi.bukkit.CookieApiBukkit;
import de.kekshaus.cookieApi.bukkit.tasks.SendBungeeTeleportMessage;
import de.kekshaus.cookieApi.bukkit.utils.LocationUtil;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class TeleportApi {

	public static void tpAll(CommandSender sender, String targetPlayer) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try {
			out.writeUTF("TpAll");
			out.writeUTF(sender.getName());
			out.writeUTF(targetPlayer);
		} catch (IOException e) {
			e.printStackTrace();
		}

		CookieApiBukkit.getInstance().getServer().getScheduler().runTaskAsynchronously(CookieApiBukkit.getInstance(),
				new SendBungeeTeleportMessage(b));
	}

	public static void tpaRequest(CommandSender sender, String targetPlayer) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try {
			out.writeUTF("TpaRequest");
			out.writeUTF(sender.getName());
			out.writeUTF(targetPlayer);
		} catch (IOException e) {
			e.printStackTrace();
		}

		CookieApiBukkit.getInstance().getServer().getScheduler().runTaskAsynchronously(CookieApiBukkit.getInstance(),
				new SendBungeeTeleportMessage(b));
	}

	public static void tpaHereRequest(CommandSender sender, String targetPlayer) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try {
			out.writeUTF("TpaHereRequest");
			out.writeUTF(sender.getName());
			out.writeUTF(targetPlayer);
		} catch (IOException e) {
			e.printStackTrace();
		}
		CookieApiBukkit.getInstance().getServer().getScheduler().runTaskAsynchronously(CookieApiBukkit.getInstance(),
				new SendBungeeTeleportMessage(b));
	}

	public static void tpAccept(final CommandSender sender) {
		final Player player = Bukkit.getPlayer(sender.getName());

		player.saveData();
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try {
			out.writeUTF("TpAccept");
			out.writeUTF(sender.getName());
		} catch (IOException e) {
			e.printStackTrace();
		}

		CookieApiBukkit.getInstance().getServer().getScheduler().runTaskAsynchronously(CookieApiBukkit.getInstance(),
				new SendBungeeTeleportMessage(b));
	}

	public static void tpDeny(String sender) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try {
			out.writeUTF("TpDeny");
			out.writeUTF(sender);
		} catch (IOException e) {
			e.printStackTrace();
		}

		CookieApiBukkit.getInstance().getServer().getScheduler().runTaskAsynchronously(CookieApiBukkit.getInstance(),
				new SendBungeeTeleportMessage(b));
	}

	public static void finishTPA(final Player player, final String target) {
		if (!player.hasPermission("cookieApi.bypass")) {
			OtherApi.lastLocation.put(player, player.getLocation());
			player.sendMessage(
					MessageDB.TELEPORT_TIMER.replace("{TIME}", String.valueOf(CookieApiBukkit.getWarmUpTime())));
			CookieApiBukkit.getInstance().getServer().getScheduler().runTaskLater(CookieApiBukkit.getInstance(),
					new Runnable() {
						@Override
						public void run() {

							Location loc = OtherApi.lastLocation.get(player);
							OtherApi.lastLocation.remove(player);
							if ((loc != null) && (loc.getBlock().equals(player.getLocation().getBlock()))) {
								player.saveData();
								ByteArrayOutputStream b = new ByteArrayOutputStream();
								DataOutputStream out = new DataOutputStream(b);
								try {
									out.writeUTF("TeleportToPlayer");
									out.writeUTF(player.getName());
									out.writeUTF(target);
									out.writeBoolean(false);
									out.writeBoolean(true);
								} catch (IOException e) {
									e.printStackTrace();
								}

								CookieApiBukkit.getInstance().getServer().getScheduler().runTaskAsynchronously(
										CookieApiBukkit.getInstance(), new SendBungeeTeleportMessage(b));

							} else {
								player.sendMessage(MessageDB.TELEPORT_MOVE_CANCEL);

							}
						}
					}, 20L * CookieApiBukkit.getWarmUpTime());
		} else {
			player.saveData();
			ByteArrayOutputStream b = new ByteArrayOutputStream();
			DataOutputStream out = new DataOutputStream(b);
			try {
				out.writeUTF("TeleportToPlayer");
				out.writeUTF(player.getName());
				out.writeUTF(target);
				out.writeBoolean(false);
				out.writeBoolean(true);
			} catch (IOException e) {
				e.printStackTrace();
			}

			CookieApiBukkit.getInstance().getServer().getScheduler()
					.runTaskAsynchronously(CookieApiBukkit.getInstance(), new SendBungeeTeleportMessage(b));

		}
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

	public static void teleportPlayerToPlayer(final String player, String target) {
		Player p = Bukkit.getPlayer(player);
		Player t = Bukkit.getPlayer(target);
		if (p != null) {
			p.teleport(t);
		} else {
			OtherApi.pendingTeleports.put(player, t);
			// clear pending teleport if they dont connect
			Bukkit.getScheduler().runTaskLaterAsynchronously(CookieApiBukkit.getInstance(), new Runnable() {
				@Override
				public void run() {
					if (OtherApi.pendingTeleports.containsKey(player)) {
						OtherApi.pendingTeleports.remove(player);
					}

				}
			}, 100L);
		}
	}

	public static void teleportToLocation(final String player, String world, double x, double y, double z, float yaw,
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
		String sendTeleport = ChatColor.GREEN + "Du wurdest teleportiert!";
		if (p != null) {
			// Check if Block is safe
			if (LocationUtil.isBlockUnsafe(t.getWorld(), t.getBlockX(), t.getBlockY(), t.getBlockZ())) {
				try {
					Location l = LocationUtil.getSafeDestination(p, t);
					if (l != null) {
						p.teleport(l);
						p.sendMessage(sendTeleport);

					} else {
						p.sendMessage(ChatColor.RED + "Unable to find a safe location for teleport.");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				p.teleport(t);
				p.sendMessage(sendTeleport);
				return;
			}
		} else {
			OtherApi.pendingTeleportLocations.put(player, t);
			OtherApi.pendingStringMessage.put(player, sendTeleport);
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
		// p.sendMessage(ChatColor.GREEN + "Du wurdest teleportiert!");

	}

	public static void teleportToPlayer(final String playerName, final String target) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try {
			out.writeUTF("TeleportToPlayer");
			out.writeUTF(playerName);
			out.writeUTF(target);
			out.writeBoolean(true);
			out.writeBoolean(true);
		} catch (IOException e) {
			e.printStackTrace();
		}

		CookieApiBukkit.getInstance().getServer().getScheduler().runTaskAsynchronously(CookieApiBukkit.getInstance(),
				new SendBungeeTeleportMessage(b));
	}

	public static void sendTeleportToLocationOut(String player, String server, String world, Double x, Double y,
			Double z, Float yaw, Float pitch) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try {
			out.writeUTF("TeleportToLocation");
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
