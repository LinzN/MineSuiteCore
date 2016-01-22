package de.kekshaus.cookieApi.bukkit.managerApi;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import de.kekshaus.cookieApi.bukkit.CookieApiBukkit;
import de.kekshaus.cookieApi.bukkit.tasks.SendBungeeInvDataMessage;
import de.kekshaus.cookieApi.bukkit.utils.CookieInventory;
import de.kekshaus.cookieApi.bukkit.utils.Mapped;

public class InvApi {
	public static ThreadPoolExecutor executorServiceCommands = new ThreadPoolExecutor(1, 1, 250L, TimeUnit.MILLISECONDS,
			new LinkedBlockingQueue<Runnable>());

	public static void loadInventory(final String pname) {
		final Player player = Bukkit.getPlayer(pname);
		if (player != null) {

			executorServiceCommands.submit(new Runnable() {
				public void run() {
					int counter = 0;
					boolean loaded = false;
					while (counter <= 10 || !loaded) {
						if (Mapped.hasInventory(pname)) {
							CookieInventory inv = Mapped.getInventory(pname);
							inv.restore(player);
							loaded = true;
							sendSuccess(player.getName(), player.getUniqueId().toString());
						}
						counter++;
					}
				}
			});
		} else {
			// Player is not online
		}
	}

	public static void saveInventory(final String pname) {
		final Player player = Bukkit.getPlayer(pname);
		executorServiceCommands.submit(new Runnable() {
			public void run() {
				CookieInventory inv = new CookieInventory();
				inv.create(player);
				sendPlayerData(inv);
			}
		});
	}

	public static void sendPlayerData(CookieInventory cInv) {
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try {
			out.writeUTF("SendPlayerData");
			out.writeUTF(cInv.getPlayername());
			out.writeUTF(cInv.getPlayerUUIDString());
			out.writeUTF(cInv.getArmorData());
			out.writeUTF(cInv.getInventoryData());
			out.writeUTF(cInv.getEnderchestData());
			out.writeDouble(cInv.getHeathData());
			out.writeInt(cInv.getFoodData());
			out.writeInt(cInv.getExperienceData());
			out.writeLong(cInv.getInventoryTime());
		} catch (IOException e) {
			e.printStackTrace();
		}

		CookieApiBukkit.getInstance().getServer().getScheduler().runTaskAsynchronously(CookieApiBukkit.getInstance(),
				new SendBungeeInvDataMessage(b));
	}

	public static void addInventory(String playerName, String playerUUID, String armorData, String inventoryData,
			String enderchestData, double heathData, int foodData, int experienceData, long inventoryTime) {

		CookieInventory cInv = new CookieInventory(playerName, playerUUID);
		cInv.setArmorData(armorData);
		cInv.setInventoryData(inventoryData);
		cInv.setEnderchestData(enderchestData);
		cInv.setHeathData(heathData);
		cInv.setFoodData(foodData);
		cInv.setExperienceData(experienceData);
		cInv.setInventoryTime(inventoryTime);

		Mapped.addInventory(playerName, cInv);
	}

	public static void sendSuccess(String playername, String playeruuid) {
		Mapped.setLoaded(playername);
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		DataOutputStream out = new DataOutputStream(b);
		try {
			out.writeUTF("SendSuccess");
			out.writeUTF(playername);
			out.writeUTF(playeruuid);

		} catch (IOException e) {
			e.printStackTrace();
		}

		CookieApiBukkit.getInstance().getServer().getScheduler().runTaskAsynchronously(CookieApiBukkit.getInstance(),
				new SendBungeeInvDataMessage(b));
	}

}
