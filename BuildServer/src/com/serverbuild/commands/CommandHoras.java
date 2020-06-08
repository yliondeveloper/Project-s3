package com.serverbuild.commands;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandHoras implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player p = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("horas")) {
			if(args.length == 0) {
				p.sendMessage("");
				p.sendMessage("      §e§lDATA/HORARIO ATUAL §7(São Paulo) ");
				p.sendMessage("    §7Data e horário atual: §b" + getHorario());
				p.sendMessage(" ");
				return true;
			}
		}
		return false;
	}
	
	public static String getHorario() {
		TimeZone tz = TimeZone.getTimeZone("America/Sao_Paulo");
		Calendar calendar = GregorianCalendar.getInstance(tz);
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
		return sdf.format(calendar.getTime());
	}
}
