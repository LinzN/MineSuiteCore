package de.kekshaus.cookieApi.bukkit.utils;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class CookieInventory {
	private String playername;
	private UUID playeruuid;
	private long inventoryTimeData;
	private String armorData;
	private String inventoryData;
	private String endChestData;
	private double healthData;
	private int footData;
	private int experienceData;

	public CookieInventory() {
		// If only empty
	}

	public CookieInventory(Player player) {
		this.playername = player.getName();
		this.playeruuid = player.getUniqueId();
	}

	public CookieInventory(String playername, String playerUUID) {
		this.playername = playername;
		this.playeruuid = UUID.fromString(playerUUID);
	}

	// Setup this Object
	public void create(Player player) {
		this.playername = player.getName();
		this.playeruuid = player.getUniqueId();
		this.inventoryTimeData = new Date().getTime();
		this.healthData = Math.min(player.getHealth(), player.getMaxHealth());
		this.footData = player.getFoodLevel();
		this.experienceData = BukkitEXPFix.getTotalExperience(player);

		ItemStack[] armorStacks = player.getInventory().getArmorContents();
		armorData = Serializer.encodeItemStack(armorStacks);

		ItemStack[] inventoryStacks = player.getInventory().getContents();
		inventoryData = Serializer.encodeItemStack(inventoryStacks);

		ItemStack[] endChestStacks = player.getEnderChest().getContents();
		endChestData = Serializer.encodeItemStack(endChestStacks);
	}

	// Restore from Object
	public void restore(Player player) {
		try {
			BukkitEXPFix.setTotalExperience(player, this.experienceData);
			player.setHealth(this.healthData);
			player.setFoodLevel(this.footData);
			player.getInventory().setArmorContents(Serializer.decodeItemStack(this.armorData));
			player.getInventory().setContents(Serializer.decodeItemStack(this.inventoryData));
			player.getEnderChest().setContents(Serializer.decodeItemStack(this.endChestData));
		} catch (IOException e) {
			// Error
			e.printStackTrace();
		}
		player.updateInventory();
	}

	// GetData
	public String getArmorData() {
		return this.armorData;
	}

	public String getInventoryData() {
		return this.inventoryData;
	}

	public String getEnderchestData() {
		return this.endChestData;
	}

	public long getInventoryTime() {
		return this.inventoryTimeData;
	}

	public double getHeathData() {
		return this.healthData;
	}

	public int getFoodData() {
		return this.footData;
	}

	public int getExperienceData() {
		return this.experienceData;
	}

	public String getPlayername() {
		return this.playername;
	}

	public UUID getPlayerUUID() {
		return this.playeruuid;
	}

	public String getPlayerUUIDString() {
		return this.playeruuid.toString();
	}

	// SetData
	public void setArmorData(String data) {
		this.armorData = data;
	}

	public void setInventoryData(String data) {
		this.inventoryData = data;
	}

	public void setEnderchestData(String data) {
		this.endChestData = data;
	}

	public void setInventoryTime(long data) {
		this.inventoryTimeData = data;
	}

	public void setHeathData(double data) {
		this.healthData = data;
	}

	public void setFoodData(int data) {
		this.footData = data;
	}

	public void setExperienceData(int data) {
		this.experienceData = data;
	}

}
