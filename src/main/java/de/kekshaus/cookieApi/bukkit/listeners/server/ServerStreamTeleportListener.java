package de.kekshaus.cookieApi.bukkit.listeners.server;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import de.kekshaus.cookieApi.bukkit.CookieApiBukkit;
import de.kekshaus.cookieApi.bukkit.managerApi.HomeApi;
import de.kekshaus.cookieApi.bukkit.managerApi.TeleportApi;
import de.kekshaus.cookieApi.bukkit.managerApi.WarpApi;
import de.kekshaus.cookieApi.bukkit.socketEvents.ServerStreamTeleportEvent;
import de.xHyveSoftware.socket.bukkit.api.annotation.Channel;
import de.xHyveSoftware.socket.bukkit.api.annotation.PacketHandler;
import de.xHyveSoftware.socket.bukkit.api.listener.AbstractPacketListener;

@Channel("ServerStreamTeleport")
public class ServerStreamTeleportListener extends AbstractPacketListener {
	@PacketHandler
	public void onCookieApiMessageEvent(final ServerStreamTeleportEvent event) {
		CookieApiBukkit.getInstance().getServer().getScheduler().runTask(CookieApiBukkit.getInstance(), new Runnable() {

			public void run() {
				DataInputStream in = new DataInputStream(new ByteArrayInputStream(event.getData()));
				String servername = null;
				String ServerConfigName = CookieApiBukkit.getServerName();
				String task = null;
				try {
					servername = in.readUTF();
					if (!servername.equalsIgnoreCase("ALL")) {
						if (!servername.equalsIgnoreCase(ServerConfigName)) {
							return;
						}
					}

					task = in.readUTF();

					if (task.equals("TeleportToHome")) {
						HomeApi.teleportToHome(in.readUTF(), in.readUTF(), in.readDouble(), in.readDouble(),
								in.readDouble(), in.readFloat(), in.readFloat());
					}
					if (task.equals("TeleportToWarp")) {
						WarpApi.teleportToWarp(in.readUTF(), in.readUTF(), in.readDouble(), in.readDouble(),
								in.readDouble(), in.readFloat(), in.readFloat());
					}
					if (task.equals("TeleportToLocation")) {
						TeleportApi.teleportToLocation(in.readUTF(), in.readUTF(), in.readDouble(), in.readDouble(),
								in.readDouble(), in.readFloat(), in.readFloat());
					}

					if (task.equals("TeleportToPlayer")) {
						TeleportApi.teleportPlayerToPlayer(in.readUTF(), in.readUTF());
					}

					if (task.equals("TeleportAccept")) {
						TeleportApi.finishTPA(Bukkit.getPlayerExact(in.readUTF()), in.readUTF());
					}

					if (task.equals("SendActionMessage")) {
						String getMessage = in.readUTF();
						String reason = in.readUTF();
						Bukkit.getConsoleSender().sendMessage(getMessage);
						Bukkit.getConsoleSender().sendMessage(reason);
						for (Player p : Bukkit.getOnlinePlayers()) {
							if (p.hasPermission("cookieApi.ban.notifyinfo")) {
								p.sendMessage(getMessage);
								p.sendMessage(reason);
							}
						}
					}
					if (task.equals("SendDeActionMessage")) {
						String getMessage = in.readUTF();
						Bukkit.getConsoleSender().sendMessage(getMessage);

						for (Player p : Bukkit.getOnlinePlayers()) {
							if (p.hasPermission("cookieApi.ban.notifyinfo")) {
								p.sendMessage(getMessage);
							}
						}
					}

				} catch (IOException e) {
					e.printStackTrace();
					return;
				}
			}
		});
	}

}