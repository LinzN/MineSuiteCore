package de.xHyveSoftware.socket.bukkit.api.message;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import de.xHyveSoftware.socket.bukkit.api.annotation.Channel;
import de.xHyveSoftware.socket.bukkit.cache.ChannelKeyCache;
import de.xHyveSoftware.socket.bukkit.cache.ClassKeyCache;
import de.xHyveSoftware.socket.bukkit.sockets.P2PServers;

public abstract class AbstractMessage implements Message {
	public AbstractMessage() {
		String channel;
		if (getClass().isAnnotationPresent(Channel.class)) {
			channel = getClass().getAnnotation(Channel.class).value();
		} else {
			channel = getChannel();
		}

		channelKey = ChannelKeyCache.getKey(channel);
		classKey = ClassKeyCache.getKey(getClass());
	}

	private Long channelKey;
	private Long classKey;

	public String getChannel() {
		return null;
	}

	public Message send() {
		if (channelKey != null && classKey != null) {
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);

			write(dataOutputStream);

			de.xHyveSoftware.socket.bukkit.packet.protocol.Message message = new de.xHyveSoftware.socket.bukkit.packet.protocol.Message();
			message.setChannel(channelKey);
			message.setPacket(classKey);
			message.setMessage(byteArrayOutputStream.toByteArray());

			P2PServers.broadCastToAll(message);
		}

		return this;
	}
}
