package com.serverbuild.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.serverbuild.main.Main;

public class CommandSetWarp implements CommandExecutor {

	public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
		final Player p = (Player) sender;
		if (!sender.hasPermission("starpvp.setwarp")) {
			p.sendMessage("�c�Voc� n�o possui permiss�o para executar esse comando.)");

			return true;
		}
		Main.getInstance().warps.set((args[0]) + ".x", (Object) p.getLocation().getX());
		Main.getInstance().warps.set((args[0]) + ".y", (Object) p.getLocation().getY());
		Main.getInstance().warps.set((args[0]) + ".z", (Object) p.getLocation().getZ());
		Main.getInstance().warps.set((args[0]) + ".pitch", (Object) p.getLocation().getPitch());
		Main.getInstance().warps.set((args[0]) + ".yaw", (Object) p.getLocation().getYaw());
		Main.getInstance().warps.set((args[0]) + ".world", (Object) p.getLocation().getWorld().getName());
		Main.getInstance().SalvarWarps();
		p.sendMessage("�aVoc� setou a warp �7" + (args[0]));
		return true;
	}

}
