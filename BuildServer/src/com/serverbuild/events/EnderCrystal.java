package com.serverbuild.events;

import java.util.logging.Logger;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class EnderCrystal implements CommandExecutor {
	public static EnderCrystal plugin;
	public final Logger logger = Logger.getLogger("Minecraft");

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player player = (Player) sender;
		if (args.length == 0) {
			sender.sendMessage("§cComando incorreto, digite: /setcrs (normal/bedrock)");
			return true;
		}
		if (!sender.hasPermission("starpvp.endercrystal")) {
			sender.sendMessage("§cVocê não possui permissão para executar esse comando.");
			return true;
		}
		if (args[0].equalsIgnoreCase("normal")) {
			if ((player.getLocation().getBlock().isEmpty())
					&& (player.getLocation().clone().add(0.0D, 1.0D, 0.0D).getBlock().isEmpty())) {
				player.getLocation().getWorld().spawnEntity(player.getLocation(), EntityType.ENDER_CRYSTAL);
			}
			sender.sendMessage("§aCrystal spawnado com sucesso!");
			return true;
		}
		if (args[0].equalsIgnoreCase("bedrock")) {
			if ((player.getLocation().getBlock().isEmpty())
					&& (player.getLocation().clone().add(0.0D, 1.0D, 0.0D).getBlock().isEmpty())) {
				player.getLocation().getWorld().spawnEntity(player.getLocation(), EntityType.ENDER_CRYSTAL);
				player.getLocation().getBlock().setType(Material.BEDROCK);
				player.getLocation().clone().add(0.0D, 1.0D, 0.0D).getBlock().setType(Material.FIRE);
			}
			sender.sendMessage("§aCrystal spawnado com sucesso!");
			return true;
		}
		return false;
	}
}
