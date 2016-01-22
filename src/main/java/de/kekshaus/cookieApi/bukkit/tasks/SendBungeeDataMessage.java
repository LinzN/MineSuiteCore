package de.kekshaus.cookieApi.bukkit.tasks;

import java.io.ByteArrayOutputStream;

import de.kekshaus.cookieApi.bukkit.socketEvents.BungeeStreamDataEvent;

public class SendBungeeDataMessage implements Runnable {

	private final ByteArrayOutputStream bytes;

	public SendBungeeDataMessage(ByteArrayOutputStream bytes) {
		this.bytes = bytes;
	}

	public void run() {
		BungeeStreamDataEvent bungeeStreamEvent = new BungeeStreamDataEvent();
		bungeeStreamEvent.setBytes(bytes.toByteArray());
		bungeeStreamEvent.send();
	}

}