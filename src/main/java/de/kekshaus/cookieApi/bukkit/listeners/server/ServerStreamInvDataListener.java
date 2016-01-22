package de.kekshaus.cookieApi.bukkit.listeners.server;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import de.kekshaus.cookieApi.bukkit.CookieApiBukkit;
import de.kekshaus.cookieApi.bukkit.managerApi.InvApi;
import de.kekshaus.cookieApi.bukkit.socketEvents.ServerStreamInvDataEvent;
import de.xHyveSoftware.socket.bukkit.api.annotation.Channel;
import de.xHyveSoftware.socket.bukkit.api.annotation.PacketHandler;
import de.xHyveSoftware.socket.bukkit.api.listener.AbstractPacketListener;

@Channel("ServerStreamInvData")
public class ServerStreamInvDataListener extends AbstractPacketListener {
	@PacketHandler
	public void onCookieApiMessageEvent(final ServerStreamInvDataEvent event) {
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

					if (task.equals("AddPlayerData")) {
						InvApi.addInventory(in.readUTF(), in.readUTF(), in.readUTF(), in.readUTF(), in.readUTF(),
								in.readDouble(), in.readInt(), in.readInt(), in.readLong());
					}
					if (task.equals("GetPlayerData")) {
						InvApi.saveInventory(in.readUTF());
					}

				} catch (IOException e) {
					e.printStackTrace();
					return;
				}
			}
		});
	}

}