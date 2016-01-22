package de.kekshaus.cookieApi.bukkit.managerApi;

import de.kekshaus.cookieApi.bukkit.CookieApiBukkit;
import de.kekshaus.cookieApi.bukkit.tasks.SendBungeeDataMessage;
import de.kekshaus.cookieApi.bukkit.tasks.SendBungeeOtherMessage;
import de.kekshaus.cookieApi.guild.objects.SpawnLocation;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class GuildApi {

	public static void createGuildOut(String serverSqlInject, String guildname, String guildOwner) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try {
			out.writeUTF(serverSqlInject);
			out.writeUTF("createGuild");
			out.writeUTF(guildname);
			out.writeUTF(guildOwner);
		} catch (IOException e) {
			e.printStackTrace();
		}

		CookieApiBukkit.getInstance().getServer().getScheduler().runTaskAsynchronously(CookieApiBukkit.getInstance(),
				new SendBungeeDataMessage(b));
	}

	public static void deleteGuildOut(String serverSqlInject, String guildname) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try {
			out.writeUTF(serverSqlInject);
			out.writeUTF("deleteGuild");
			out.writeUTF(guildname);
		} catch (IOException e) {
			e.printStackTrace();
		}
		CookieApiBukkit.getInstance().getServer().getScheduler().runTaskAsynchronously(CookieApiBukkit.getInstance(),
				new SendBungeeDataMessage(b));
	}

	public static void addGuildToPlayer(String serverSqlInject, String player, String guild) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try {
			out.writeUTF(serverSqlInject);
			out.writeUTF("AddGuildToPlayer");
			out.writeUTF(player);
			out.writeUTF(guild);
		} catch (IOException e) {
			e.printStackTrace();
		}
		CookieApiBukkit.getInstance().getServer().getScheduler().runTaskAsynchronously(CookieApiBukkit.getInstance(),
				new SendBungeeDataMessage(b));
	}

	public static void deleteGuildFromPlayer(String serverSqlInject, String player) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try {
			out.writeUTF(serverSqlInject);
			out.writeUTF("DeleteGuildFromPlayer");
			out.writeUTF(player);
		} catch (IOException e) {
			e.printStackTrace();
		}

		CookieApiBukkit.getInstance().getServer().getScheduler().runTaskAsynchronously(CookieApiBukkit.getInstance(),
				new SendBungeeDataMessage(b));
	}

	public static void setGuildSpawn(String serverSqlInject, String guildname, String servername,
			SpawnLocation location) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try {
			out.writeUTF(serverSqlInject);
			out.writeUTF("SetGuildSpawn");
			out.writeUTF(guildname);
			out.writeUTF(servername);
			out.writeUTF(location.getWorld());
			out.writeDouble(location.getX());
			out.writeDouble(location.getY());
			out.writeDouble(location.getZ());
			out.writeFloat(location.getYaw());
			out.writeFloat(location.getPitch());
		} catch (IOException e) {
			e.printStackTrace();
		}

		CookieApiBukkit.getInstance().getServer().getScheduler().runTaskAsynchronously(CookieApiBukkit.getInstance(),
				new SendBungeeDataMessage(b));
	}

	public static void unsetGuildSpawn(String serverSqlInject, String guildname, String servername) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try {
			out.writeUTF(serverSqlInject);
			out.writeUTF("UnsetGuildSpawn");
			out.writeUTF(guildname);
		} catch (IOException e) {
			e.printStackTrace();
		}

		CookieApiBukkit.getInstance().getServer().getScheduler().runTaskAsynchronously(CookieApiBukkit.getInstance(),
				new SendBungeeDataMessage(b));
	}

	public static void sendGuildInvite(String player, String invitedPlayer, String guild) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try {
			out.writeUTF("SendGuildInvite");
			out.writeUTF(player);
			out.writeUTF(invitedPlayer);
			out.writeUTF(guild);
		} catch (IOException e) {
			e.printStackTrace();
		}
		CookieApiBukkit.getInstance().getServer().getScheduler().runTaskAsynchronously(CookieApiBukkit.getInstance(),
				new SendBungeeOtherMessage(b));
	}

	public static void sendGuildAccept(String player) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try {
			out.writeUTF("AcceptGuildInvite");
			out.writeUTF(player);
		} catch (IOException e) {
			e.printStackTrace();
		}
		CookieApiBukkit.getInstance().getServer().getScheduler().runTaskAsynchronously(CookieApiBukkit.getInstance(),
				new SendBungeeOtherMessage(b));
	}

}
