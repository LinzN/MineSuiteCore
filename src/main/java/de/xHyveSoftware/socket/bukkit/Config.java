package de.xHyveSoftware.socket.bukkit;

public class Config {
	private String ip;
	private Integer port;
	private Integer multicastport;
	private String networkinterface;
	private String multicastip;

	public Config(String ip, int port, int multicastport, String networkinterface, String multicastip) {
		this.ip = ip;
		this.port = port;
		this.multicastport = multicastport;
		this.networkinterface = networkinterface;
		this.multicastip = multicastip;

	}

	public String getIp() {
		return this.ip;
	}

	public int getPort() {
		return this.port;
	}

	public int getMultiCastPort() {
		return this.multicastport;
	}

	public String getNetworkInterface() {
		return this.networkinterface;
	}

	public String getMultiCastIP() {
		return this.multicastip;
	}
}
