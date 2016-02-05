package de.kekshaus.cookieApi.bukkit.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.spigotmc.event.player.PlayerSpawnLocationEvent;

import de.kekshaus.cookieApi.bukkit.CookieApiBukkit;
import de.kekshaus.cookieApi.bukkit.managerApi.OtherApi;
import de.kekshaus.cookieApi.guild.utils.GuildNameTag;

public class BukkitListener implements Listener {

	@EventHandler
	public void onChange(PlayerChangedWorldEvent e) {
		GuildNameTag.getAPI().refresh();
	}

	@EventHandler
	public void playerConnect(PlayerSpawnLocationEvent e) {
		if (OtherApi.pendingTeleports.containsKey(e.getPlayer().getName())) {
			Player t = OtherApi.pendingTeleports.get(e.getPlayer().getName());
			OtherApi.pendingTeleports.remove(e.getPlayer().getName());
			if ((t == null) || (!t.isOnline())) {
				e.getPlayer().sendMessage("Player is no longer online");
				return;
			}
			OtherApi.ignoreTeleport.add(e.getPlayer());
			e.setSpawnLocation(t.getLocation());
		} else if (OtherApi.pendingTeleportLocations.containsKey(e.getPlayer().getName())) {
			Location l = OtherApi.pendingTeleportLocations.get(e.getPlayer().getName());
			OtherApi.ignoreTeleport.add(e.getPlayer());
			e.setSpawnLocation(l);
		}
	}

	@EventHandler(ignoreCancelled = true)
	public void playerDeath(PlayerDeathEvent e) {
		if (CookieApiBukkit.isWorldAllowed(e.getEntity().getLocation().getWorld())) {
			OtherApi.sendDeathBackLocation(e.getEntity());
			OtherApi.ignoreTeleport.add(e.getEntity());
		}
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void playerJoin(final PlayerJoinEvent event) {
		OtherApi.ignoreTeleport.add(event.getPlayer());
		Bukkit.getScheduler().runTaskLaterAsynchronously(CookieApiBukkit.getInstance(), new Runnable() {
			@Override
			public void run() {
				OtherApi.ignoreTeleport.remove(event.getPlayer());
				if (OtherApi.pendingStringMessage.containsKey(event.getPlayer().getName())) {
					event.getPlayer().sendMessage(OtherApi.pendingStringMessage.get(event.getPlayer().getName()));
				}
			}
		}, 20);
	}
}
