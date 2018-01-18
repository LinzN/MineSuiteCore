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
