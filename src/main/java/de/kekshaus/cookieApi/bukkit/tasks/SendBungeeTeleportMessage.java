package de.kekshaus.cookieApi.bukkit.tasks;

import java.io.ByteArrayOutputStream;

import de.kekshaus.cookieApi.bukkit.socketEvents.BungeeStreamTeleportEvent;

public class SendBungeeTeleportMessage implements Runnable {

	private final ByteArrayOutputStream bytes;

	public SendBungeeTeleportMessage(ByteArrayOutputStream bytes) {
		this.bytes = bytes;
	}

	public void run() {
		BungeeStreamTeleportEvent bungeeStreamEvent = new BungeeStreamTeleportEvent();
		bungeeStreamEvent.setBytes(bytes.toByteArray());
		bungeeStreamEvent.send();
	}

}