package com.serverbuild.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandClearChat implements CommandExecutor {
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender.hasPermission("starpvp.cc"))) {
			sender.sendMessage("§cVocê não possui permissão para executar esse comando.");
			return true;
		}
		for (Player s : Bukkit.getOnlinePlayers()) {
			for (int i = 0; i != 100; i++) {
				s.sendMessage("  ");
			}
		}
		for (Player s : Bukkit.getOnlinePlayers()) {
			s.sendMessage("§aO chat foi limpo.");
		}
		return false;
	}

} 