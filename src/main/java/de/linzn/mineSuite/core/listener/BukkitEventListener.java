/*
 * Copyright (C) 2018. MineGaming - All Rights Reserved
 * You may use, distribute and modify this code under the
 * terms of the LGPLv3 license, which unfortunately won't be
 * written for another century.
 *
 *  You should have received a copy of the LGPLv3 license with
 *  this file. If not, please write to: niklas.linz@enigmar.de
 *
 */

package de.linzn.mineSuite.core.listener;

import de.linzn.mineLib.MineLibPlugin;
import de.linzn.mineLib.actionBar.MineActionBar;
import de.linzn.mineSuite.core.database.hashDatabase.PendingTeleportsData;
import de.linzn.mineSuite.core.utils.LanguageDB;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class BukkitEventListener implements Listener {

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

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        PendingTeleportsData.cleanPlayer(event.getPlayer().getUniqueId());
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onPlayerBedEnterEvent(PlayerBedEnterEvent event)
    {
        if(!event.isCancelled()) {
            Player p = event.getPlayer();
            p.setStatistic(Statistic.TIME_SINCE_REST, 0);
            new MineActionBar(LanguageDB.SLEED_BED_TIMER).send(p);
        }
    }
}
