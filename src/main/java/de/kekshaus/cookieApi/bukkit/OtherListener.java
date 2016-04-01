package de.kekshaus.cookieApi.bukkit;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class OtherListener implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
	public void onSignChange(SignChangeEvent event) {
		for (int forInt = 0; forInt < 4; forInt++) {
			if (!event.getLine(forInt).isEmpty()) {
				String line = event.getLine(forInt);
				line = ChatColor.translateAlternateColorCodes('&', line);
				event.setLine(forInt, line);
			}
		}
	}
}
