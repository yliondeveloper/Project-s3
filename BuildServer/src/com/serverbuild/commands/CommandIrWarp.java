package com.serverbuild.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.serverbuild.main.Main;

public class CommandIrWarp implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("warp") && args.length == 0) {
			p.sendMessage("§cSintaxe correta:§7 /warp (warp)");
			return true;
		}
			if (Main.getInstance().warps.getConfigurationSection(args[0]) == null) {
				p.sendMessage("§cA warp " + (args[0]) +" ainda não foi setada!");
				return true;
			}
			World w = Bukkit.getServer().getWorld(Main.getInstance().warps.getString((args[0]) +".world"));
			double x = Main.getInstance().warps.getDouble((args[0]) +".x");
			double y = Main.getInstance().warps.getDouble((args[0]) +".y");
			double z = Main.getInstance().warps.getDouble((args[0]) +".z");
			Location lobby = new Location(w, x, y, z);
			lobby.setPitch((float) Main.getInstance().warps.getDouble((args[0]) +".pitch"));
			lobby.setYaw((float) Main.getInstance().warps.getDouble((args[0]) +".yaw"));
			p.teleport(lobby);
			p.sendMessage("§eVocê foi teleportado para a warp §e" + (args[0]));
			p.getActivePotionEffects().forEach(potionEffect -> p.removePotionEffect(potionEffect.getType()));
			p.updateInventory();
			return true;
		}
    

}
