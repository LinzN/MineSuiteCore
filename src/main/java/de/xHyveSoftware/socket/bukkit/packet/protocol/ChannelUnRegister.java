package de.xHyveSoftware.socket.bukkit.packet.protocol;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import de.xHyveSoftware.socket.bukkit.packet.DefinedPacket;

public class ChannelUnRegister extends DefinedPacket {

	private String channel;

	@Override
	public void handle(DataInputStream input) throws IOException {
		channel = DefinedPacket.readString(input);
	}

	@Override
	public void handle(DataOutputStream output) throws IOException {
		DefinedPacket.writeString(channel, output);
	}
}
