package com.serverbuild.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandTp implements CommandExecutor {
	public String[] aliases = { "tp" };
	public String description = "Sistema de teleporte.";

	public boolean onCommand(CommandSender Sender, Command Cmd, String Label, String[] Args) {
		Player p = (Player) Sender;
		if (p.hasPermission("starpvp.tp")) {
			if (Args.length == 0) {
				p.sendMessage("§cSintaxe correta:§7 /tp [jogador] ou /tp [jogador] [alvo]");
			} else if (Args.length == 1) {
				Player target = p.getServer().getPlayer(Args[0]);
				if (target != null) {
					p.teleport(target.getLocation());
					for (Player p1 : Bukkit.getOnlinePlayers())
						if ((p1.hasPermission("starpvp.tp")) || (p1.isOp()))
							;
				} else {
					p.sendMessage("§cJogador offline.");
				}
			} else if (Args.length == 2) {
				Player target1 = p.getServer().getPlayer(Args[0]);
				Player starget = p.getServer().getPlayer(Args[1]);
				if (target1 != null) {
					if (starget != null) {
						target1.teleport(starget.getLocation());
						for (Player p1 : Bukkit.getOnlinePlayers())
							if ((p1.hasPermission("starpvp.tp")) || (p1.isOp()))
								;
					} else {
						p.sendMessage("§cJogador offline.");
					}
				} else
					p.sendMessage("§cJogador offline.");
			} else if (Args.length == 3) {
				double x2 = Args[0].startsWith("~") ? p.getLocation().getX() + Integer.parseInt(Args[0].substring(1))
						: Integer.parseInt(Args[0]);
				double y2 = Args[1].startsWith("~") ? p.getLocation().getY() + Integer.parseInt(Args[1].substring(1))
						: Integer.parseInt(Args[1]);
				double z2 = Args[2].startsWith("~") ? p.getLocation().getZ() + Integer.parseInt(Args[2].substring(1))
						: Integer.parseInt(Args[2]);
				if ((x2 > 30000000.0D) || (y2 > 30000000.0D) || (z2 > 30000000.0D) || (x2 > -30000000.0D)
						|| (y2 > -30000000.0D) || (z2 > -30000000.0D)) {
					p.sendMessage("§cSintaxe correta:§7 /tp [nick] [alvo]");
					return true;
				}
				Location locpos = new Location(p.getWorld(), x2, y2, z2, p.getLocation().getYaw(),
						p.getLocation().getPitch());
				p.teleport(locpos);
				for (Player p1 : Bukkit.getOnlinePlayers()) {
					if ((p1.hasPermission("starpvp.tp")) || (p1.isOp()))
						;
				}
			} else if (Args.length == 4) {
				Player target1 = p.getServer().getPlayer(Args[0]);
				double x2 = Args[1].startsWith("~")
						? target1.getLocation().getX() + Integer.parseInt(Args[0].substring(1))
						: Integer.parseInt(Args[1]);
				double y2 = Args[2].startsWith("~")
						? target1.getLocation().getY() + Integer.parseInt(Args[1].substring(1))
						: Integer.parseInt(Args[2]);
				double z2 = Args[3].startsWith("~")
						? target1.getLocation().getZ() + Integer.parseInt(Args[2].substring(1))
						: Integer.parseInt(Args[3]);
				if ((x2 > 30000000.0D) || (y2 > 30000000.0D) || (z2 > 30000000.0D) || (x2 > -30000000.0D)
						|| (y2 > -30000000.0D) || (z2 > -30000000.0D) || (target1 == null)) {
					p.sendMessage("§cSintaxe correta:§7 /tp [nick] [alvo]");
					return true;
				}
				Location locpos = new Location(p.getWorld(), x2, y2, z2, target1.getLocation().getYaw(),
						target1.getLocation().getPitch());
				target1.teleport(locpos);
				for (Player p1 : Bukkit.getOnlinePlayers()) {
					if ((p1.hasPermission("starpvp.tp")) || (p1.isOp())) {
					}
				}
			}
			return false;
		}
		return false;
	}
}