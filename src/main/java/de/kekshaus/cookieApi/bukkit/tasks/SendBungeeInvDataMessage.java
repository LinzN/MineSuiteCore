package de.kekshaus.cookieApi.bukkit.tasks;

import java.io.ByteArrayOutputStream;

import de.kekshaus.cookieApi.bukkit.socketEvents.BungeeStreamInvDataEvent;

public class SendBungeeInvDataMessage implements Runnable {

	private final ByteArrayOutputStream bytes;

	public SendBungeeInvDataMessage(ByteArrayOutputStream bytes) {
		this.bytes = bytes;
	}

	public void run() {
		BungeeStreamInvDataEvent bungeeStreamEvent = new BungeeStreamInvDataEvent();
		bungeeStreamEvent.setBytes(bytes.toByteArray());
		bungeeStreamEvent.send();
	}

}