package com.serverbuild.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Events implements Listener {

	@EventHandler
	public void onFome(FoodLevelChangeEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void onChuva(WeatherChangeEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
		if (!event.isCancelled()) {
			if (Bukkit.getServer().getHelpMap().getHelpTopic(event.getMessage().split(" ")[0]) == null) {
				event.getPlayer().sendMessage("§cComando não encontrado.");
				event.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onSopa(PlayerInteractEvent e) {
		ItemStack pote = new ItemStack(Material.BOWL, 1);
		ItemMeta kpote = pote.getItemMeta();
		kpote.setDisplayName("§7Pote");
		pote.setItemMeta(kpote);
		Damageable hp;
		Player p = (Player) (hp = (Damageable) e.getPlayer());
		if (hp.getHealth() != 20.0) {
			int sopa = 7;
			if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK)
					&& p.getItemInHand().getType() == Material.MUSHROOM_SOUP) {
				p.setHealth((hp.getHealth() + sopa > hp.getMaxHealth()) ? hp.getMaxHealth() : (hp.getHealth() + sopa));
				p.getItemInHand().setType(Material.BOWL);
				p.updateInventory();
			}
		}
	}

	@EventHandler
	public void onEventAsyncPlayerChatEvent(AsyncPlayerChatEvent e) {
		if (!e.getPlayer().hasPermission("teckpvp.cor")) {
			e.setFormat("§7" + e.getPlayer().getDisplayName() + " §8» §7" + e.getMessage().replace("%", ""));
		} else {
			e.setFormat("§7" + e.getPlayer().getDisplayName() + " §8» §f"
					+ e.getMessage().replace("%", "").replace("&", "§"));
		}
	}
}
