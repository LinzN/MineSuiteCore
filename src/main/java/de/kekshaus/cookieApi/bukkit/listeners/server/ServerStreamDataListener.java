package de.kekshaus.cookieApi.bukkit.listeners.server;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import de.kekshaus.cookieApi.bukkit.CookieApiBukkit;
import de.kekshaus.cookieApi.bukkit.socketEvents.ServerStreamDataEvent;
import de.kekshaus.cookieApi.guild.cookieapiConnector.API;
import de.kekshaus.cookieApi.guild.objects.Guild;
import de.kekshaus.cookieApi.guild.objects.ObjectMapping;
import de.kekshaus.cookieApi.guild.objects.SpawnLocation;
import de.xHyveSoftware.socket.bukkit.api.annotation.Channel;
import de.xHyveSoftware.socket.bukkit.api.annotation.PacketHandler;
import de.xHyveSoftware.socket.bukkit.api.listener.AbstractPacketListener;

@Channel("ServerStreamData")
public class ServerStreamDataListener extends AbstractPacketListener {
	@PacketHandler
	public void onCookieApiMessageEvent(final ServerStreamDataEvent event) {
		CookieApiBukkit.getInstance().getServer().getScheduler().runTask(CookieApiBukkit.getInstance(), new Runnable() {

			public void run() {
				DataInputStream in = new DataInputStream(new ByteArrayInputStream(event.getData()));
				String servername = null;
				String ServerConfigName = CookieApiBukkit.getServerName();
				String task = null;
				try {
					servername = in.readUTF();
					if (servername.equalsIgnoreCase(ServerConfigName)) {
						return;
					}

					task = in.readUTF();

					if (task.equals("createGuild")) {
						String gname = in.readUTF();
						String owner = in.readUTF();
						API.createGuild(gname, owner);
						return;
					}

					if (task.equals("deleteGuild")) {
						String name = in.readUTF();
						API.deleteGuild(name);
						return;
					}
					if (task.equals("AddGuildToPlayer")) {
						String pname = in.readUTF();
						String guildname = in.readUTF();
						API.addPlayerToGuild(pname, guildname);
						return;
					}
					if (task.equals("DeleteGuildFromPlayer")) {
						String pname = in.readUTF();
						API.delPlayerFromGuild(pname);
						return;
					}
					if (task.equals("SetGuildSpawn")) {
						// some code
						String gname = in.readUTF();
						String server = in.readUTF();
						String world = in.readUTF();
						double x = in.readDouble();
						double y = in.readDouble();
						double z = in.readDouble();
						float yaw = in.readFloat();
						float pitch = in.readFloat();
						SpawnLocation location = new SpawnLocation(server, world, x, y, z, yaw, pitch);
						Guild guild = ObjectMapping.getGuild(gname);
						guild.setGuildSpawn(location);
						return;

					}
					if (task.equals("UnsetGuildSpawn")) {
						String name = in.readUTF();
						API.unsetGuildSpawn(name);
						return;
					}

				} catch (IOException e) {
					e.printStackTrace();
					return;
				}
			}
		});
	}

}