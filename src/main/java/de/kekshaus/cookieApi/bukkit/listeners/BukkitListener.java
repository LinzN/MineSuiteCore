package de.kekshaus.cookieApi.bukkit.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.spigotmc.event.player.PlayerSpawnLocationEvent;

import de.kekshaus.cookieApi.bukkit.CookieApiBukkit;
import de.kekshaus.cookieApi.bukkit.managerApi.InvApi;
import de.kekshaus.cookieApi.bukkit.managerApi.OtherApi;
import de.kekshaus.cookieApi.bukkit.utils.Mapped;

public class BukkitListener implements Listener {

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
		InvApi.loadInventory(e.getPlayer().getName());
	}

	@EventHandler(ignoreCancelled = true)
	public void playerDeath(PlayerDeathEvent e) {
		if (CookieApiBukkit.isWorldAllowed(e.getEntity().getLocation().getWorld())) {
			OtherApi.sendDeathBackLocation(e.getEntity());
			OtherApi.ignoreTeleport.add(e.getEntity());
		}
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void playerLeave(PlayerQuitEvent e) {
		Mapped.unsetLoaded(e.getPlayer().getName());
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

	// Deny Events if not unlocked

	@EventHandler
	public void onDamage(EntityDamageByEntityEvent event) {
		if (!Mapped.isLoaded(event.getDamager().getName())) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onDrop(PlayerDropItemEvent event) {
		if (!Mapped.isLoaded(event.getPlayer().getName())) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onChange(InventoryInteractEvent event) {
		if (!Mapped.isLoaded(event.getWhoClicked().getName())) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onPickup(PlayerPickupItemEvent event) {
		if (!Mapped.isLoaded(event.getPlayer().getName())) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
		if (!Mapped.isLoaded(event.getPlayer().getName())) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		if (!Mapped.isLoaded(event.getPlayer().getName())) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onPlace(BlockPlaceEvent event) {
		if (!Mapped.isLoaded(event.getPlayer().getName())) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onInteractAt(PlayerInteractAtEntityEvent event) {
		if (!Mapped.isLoaded(event.getPlayer().getName())) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onInteractOther(PlayerInteractEntityEvent event) {
		if (!Mapped.isLoaded(event.getPlayer().getName())) {
			event.setCancelled(true);
		}
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void onAllDamage(EntityDamageEvent event) {
		if (event.getEntity() instanceof Player) {
			if (!Mapped.isLoaded(event.getEntity().getName())) {
				event.setCancelled(true);
			}

		}
	}
}
