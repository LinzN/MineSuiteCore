package de.linzn.mineSuite.core.socket;

import de.linzn.jSocket.core.ThreadTaskler;
import de.linzn.mineSuite.core.MineSuiteCorePlugin;
import org.bukkit.Bukkit;

public class BukkitTaskler implements ThreadTaskler {
    @Override
    public void runThreadPoolExecutor(Runnable runnable) {

    }

    @Override
    public void runThreadExecutor(Thread thread) {

    }

    @Override
    public void runSingleThreadExecutor(Runnable runnable) {
        Bukkit.getScheduler().runTaskAsynchronously(MineSuiteCorePlugin.getInstance(), runnable);
    }
}
