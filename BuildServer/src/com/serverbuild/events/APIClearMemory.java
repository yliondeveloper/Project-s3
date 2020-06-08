package com.serverbuild.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.serverbuild.main.Main;

public class APIClearMemory {

	public static void RestartAPI() {
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.plugin, (Runnable) new Runnable() {
			public void run() {
				Bukkit.getServer().broadcastMessage("§aEfetuando limpeza no servidor para não afetar o kitpvp.");
				System.gc();
				for (final org.bukkit.entity.Entity entity : Bukkit.getServer().getWorld("StarPvP").getEntities()){
					{
						if(entity instanceof Player) continue;
						 entity.remove();
				     }
				}
			}
		}, 0L, 8920L);
	}

}
