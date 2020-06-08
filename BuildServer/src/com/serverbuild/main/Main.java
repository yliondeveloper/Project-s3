package com.serverbuild.main;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.serverbuild.commands.CommandClearChat;
import com.serverbuild.commands.CommandHoras;
import com.serverbuild.commands.CommandIrWarp;
import com.serverbuild.commands.CommandPing;
import com.serverbuild.commands.CommandSetWarp;
import com.serverbuild.commands.CommandSpawn;
import com.serverbuild.commands.CommandTp;
import com.serverbuild.events.APIClearMemory;
import com.serverbuild.events.EnderCrystal;
import com.serverbuild.events.Events;
import com.serverbuild.events.other;
import com.serverbuild.scoreboard.ScoreBoard;

public class Main extends JavaPlugin implements Listener {

	public File warpsfile;
	public YamlConfiguration warps;
	
	HashMap<UUID, PermissionAttachment> perms = new HashMap<UUID, PermissionAttachment>();

	public void SalvarWarps() {
		try {
			this.warps.save(this.warpsfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Main plugin;
	public static Main instance;

	public static Main getInstance() {
		return instance;
	}

	public static Plugin getPlugin() {
		return plugin;
	}

	public void onLoad() {
		Bukkit.getConsoleSender().sendMessage("§ePlugin iniciando...");
	}

	public void onEnable() {
		Bukkit.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		Bukkit.getServer().getPluginManager().registerEvents(this, this);
		PluginManager evento = Bukkit.getPluginManager();
		evento.registerEvents(new Events(), this);
		evento.registerEvents(new other(), this);
		plugin = this;
		instance = this;
		Bukkit.getConsoleSender().sendMessage("§aPlugin iniciado com sucesso.");
		APIClearMemory.RestartAPI();
		getCommand("horas").setExecutor(new CommandHoras());
		getCommand("warpset").setExecutor(new CommandSetWarp());
		getCommand("warp").setExecutor(new CommandIrWarp());
		getCommand("cc").setExecutor(new CommandClearChat());
		getCommand("ping").setExecutor(new CommandPing());
		getCommand("spawn").setExecutor(new CommandSpawn());
		getCommand("tp").setExecutor(new CommandTp());
		getCommand("setcrs").setExecutor(new EnderCrystal());
		getCommand("horas").setExecutor(new CommandHoras());
		getCommand("warpset").setExecutor(new CommandSetWarp());
		getCommand("warp").setExecutor(new CommandIrWarp());
		this.warpsfile = new File(getDataFolder(), "warps.yml");
		this.warps = YamlConfiguration.loadConfiguration(this.warpsfile);
		SalvarWarps();
		new BukkitRunnable() {
			public void run() {
				for (Player player : Bukkit.getOnlinePlayers())  {
					PermissionAttachment attachment = player.addAttachment(Main.getInstance());
					perms.put(player.getUniqueId(), attachment);
					PermissionAttachment pperms = perms.get(player.getUniqueId());
					pperms.setPermission("worldedit.schematic.list", true);
					Bukkit.getConsoleSender().sendMessage("§aSetado permissão para: " + player.getName());
				}
				
			}
		}.runTaskTimer(Main.getInstance(), 0, 20 * 5);
	}

	public void onDisable() {
		SalvarWarps();
	}

	@EventHandler
	public void onQuitEvent(PlayerQuitEvent e) {
		e.setQuitMessage(null);
		for (Player p : Bukkit.getOnlinePlayers()) {
			ScoreBoard.update(p);
		}
	}

	@EventHandler
	public void onLoginEvent(PlayerJoinEvent e) {
		e.setJoinMessage(null);
		e.getPlayer().setFoodLevel(20);
		e.getPlayer().setHealth(20);
		e.getPlayer().teleport(e.getPlayer().getWorld().getSpawnLocation());
		e.getPlayer().getInventory().clear();
		e.getPlayer().getInventory().setArmorContents(null);
		e.getPlayer().setGameMode(GameMode.CREATIVE);
		ScoreBoard.update(e.getPlayer());

	}

}
