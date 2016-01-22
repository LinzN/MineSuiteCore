package de.kekshaus.cookieApi.bukkit.listeners.server;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import de.kekshaus.cookieApi.bukkit.CookieApiBukkit;
import de.kekshaus.cookieApi.bukkit.socketEvents.ServerStreamOtherEvent;
import de.kekshaus.cookieApi.guild.objects.ObjectMapping;
import de.xHyveSoftware.socket.bukkit.api.annotation.Channel;
import de.xHyveSoftware.socket.bukkit.api.annotation.PacketHandler;
import de.xHyveSoftware.socket.bukkit.api.listener.AbstractPacketListener;

@Channel("ServerStreamOther")
public class ServerStreamOtherListener extends AbstractPacketListener {
	@PacketHandler
	public void onCookieApiMessageEvent(final ServerStreamOtherEvent event) {
		CookieApiBukkit.getInstance().getServer().getScheduler().runTaskAsynchronously(CookieApiBukkit.getInstance(),
				new Runnable() {
					public void run() {
						DataInputStream in = new DataInputStream(new ByteArrayInputStream(event.getData()));
						String task = null;
						try {

							task = in.readUTF();

							if (task.equals("AddToGuild")) {
								String player = in.readUTF();
								String guild = in.readUTF();
								if (ObjectMapping.isPlayerOnline(player)) {
									ObjectMapping.getGuildPlayer(player).updateGuild(ObjectMapping.getGuild(guild),
											false);
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