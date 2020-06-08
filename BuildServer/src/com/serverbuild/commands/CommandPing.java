package com.serverbuild.commands;
 
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
 
 public class CommandPing implements CommandExecutor
 {
   public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
   {
     if (!(sender instanceof Player)) {
       return false;
     }
     Player p = (Player)sender;
     
     if (args.length == 0) {
       p.sendMessage("§2§lPING §7» §aSeu ping é de §7" + getPing(p) + "ms §aClasificacao: §7[§a" + getResultPing(p) + "§7]");
     } else {
       p.sendMessage("§4§lERRO §7» Sintaxe correta: /ping");
     }
     
     return false;
   }
   
	public static int getPing(Player p) {
		return ((CraftPlayer) p).getHandle().ping - 10;
	}
	
    public static String getResultPing(Player p) {
        final double a = getPing(p);
        if (a <= 50) {
            return "§aOTIMO";
        }
        if (a <= 90) {
            return "§2BOM";
        }
        if (a <= 150) {
            return "§eMEDIO";
        }
        if (a <= 220) {
            return "§4PESSIMO";
        }
        if (a <= 500) {
            return "§4HORRIVEL";
        }
        return "§4HORRIVEL";
    }
 }