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

package de.linzn.jSocket.core;

import de.linzn.mineSuite.core.MineSuiteCorePlugin;
import org.bukkit.Bukkit;

public class TaskRunnable {

    public void runThreadPoolExecutor(Runnable run) {

    }

    public void runThreadExecutor(Thread thread) {
        thread.start();
    }

    public void runSingleThreadExecutor(Runnable run) {
        Bukkit.getScheduler().runTaskAsynchronously(MineSuiteCorePlugin.getInstance(), run);
    }
}
