package de.kekshaus.cookieApi.bukkit.listeners.server;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import org.bukkit.Bukkit;

import de.kekshaus.cookieApi.bukkit.socketEvents.PipeLiveStreamEvent;
import de.xHyveSoftware.socket.bukkit.api.annotation.Channel;
import de.xHyveSoftware.socket.bukkit.api.annotation.PacketHandler;
import de.xHyveSoftware.socket.bukkit.api.listener.AbstractPacketListener;

@Channel("PipeStream")
public class PipeLiveStreamListener extends AbstractPacketListener {
	@PacketHandler
	public void onPipeLiveStreamEvent(PipeLiveStreamEvent event) {
		DataInputStream in = new DataInputStream(new ByteArrayInputStream(event.getData()));
		try {
			String pipeinfo = in.readUTF();
			Bukkit.getLogger().info("SOCKETSTREAM STATE: " + pipeinfo);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}