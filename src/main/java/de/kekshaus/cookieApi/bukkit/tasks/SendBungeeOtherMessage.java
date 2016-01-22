package de.kekshaus.cookieApi.bukkit.tasks;

import java.io.ByteArrayOutputStream;

import de.kekshaus.cookieApi.bukkit.socketEvents.BungeeStreamOtherEvent;

public class SendBungeeOtherMessage implements Runnable {

	private final ByteArrayOutputStream bytes;

	public SendBungeeOtherMessage(ByteArrayOutputStream bytes) {
		this.bytes = bytes;
	}

	public void run() {
		BungeeStreamOtherEvent bungeeStreamEvent = new BungeeStreamOtherEvent();
		bungeeStreamEvent.setBytes(bytes.toByteArray());
		bungeeStreamEvent.send();
	}

}