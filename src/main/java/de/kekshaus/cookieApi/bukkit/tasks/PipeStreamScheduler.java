package de.kekshaus.cookieApi.bukkit.tasks;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.bukkit.Bukkit;

import de.kekshaus.cookieApi.bukkit.CookieApiBukkit;

public class PipeStreamScheduler {

	@SuppressWarnings("deprecation")
	public PipeStreamScheduler() {
		Bukkit.getServer().getScheduler().scheduleAsyncRepeatingTask(CookieApiBukkit.getInstance(), new Runnable() {

			public void run() {
				String servername = CookieApiBukkit.getServerName();
				ByteArrayOutputStream bytes = new ByteArrayOutputStream();
				DataOutputStream out = new DataOutputStream(bytes);

				try {
					out.writeUTF("Connected (" + servername + ")");
				} catch (IOException e) {
					e.printStackTrace();
				}

				CookieApiBukkit.getInstance().getServer().getScheduler()
						.runTaskAsynchronously(CookieApiBukkit.getInstance(), new PipeStream(bytes));
			}
		}, 80L, 1200L);
	}

}