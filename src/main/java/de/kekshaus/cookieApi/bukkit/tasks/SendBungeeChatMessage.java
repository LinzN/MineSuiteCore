package de.kekshaus.cookieApi.bukkit.tasks;

import java.io.ByteArrayOutputStream;

import de.kekshaus.cookieApi.bukkit.socketEvents.BungeeStreamChatEvent;

public class SendBungeeChatMessage implements Runnable {

	private final ByteArrayOutputStream bytes;

	public SendBungeeChatMessage(ByteArrayOutputStream bytes) {
		this.bytes = bytes;
	}

	public void run() {
		BungeeStreamChatEvent bungeeStreamEvent = new BungeeStreamChatEvent();
		bungeeStreamEvent.setBytes(bytes.toByteArray());
		bungeeStreamEvent.send();
	}

}