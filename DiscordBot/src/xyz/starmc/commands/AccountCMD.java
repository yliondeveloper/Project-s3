package xyz.starmc.commands;

import java.awt.Color;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.MessageChannel;
import xyz.starmc.api.Commands;
import xyz.starmc.mysql.SQLEmail;
import xyz.starmc.mysql.SQLGladiator;
import xyz.starmc.mysql.SQLHardcoreGames;
import xyz.starmc.mysql.SQLPerms;
import xyz.starmc.mysql.SQLPlayer;
import xyz.starmc.mysql.SQLPotPvP;
import xyz.starmc.mysql.SQLPvP;
import xyz.starmc.mysql.SQLTimer;

public class AccountCMD extends Commands {
	
	  public AccountCMD() {
		super("account");
	}

	public static String sToTimeComplete(int totalTime) {
	        long years = totalTime / 32140800;
	        long months = totalTime % 32140800 / 2678400;
	        long days = totalTime % 2678400 / 86400;
	        long hours = totalTime % 86400 / 3600;
	        long minutes = totalTime % 3600 / 60;
	        long seconds = totalTime % 60;
	        if (totalTime > 0) {
	            String result = "";
	            if (years > 0) {
	                result += years + " ano" + (years == 1 ? "" : "s");
	                if (months > 0 || days > 0 || hours > 0 || minutes > 0) {
	                    result += ", ";
	                } else if (seconds > 0) {
	                    result += " e ";
	                }
	            }
	            if (months > 0) {
	                result += months + " " + (months == 1 ? "mês" : "meses");
	                if (days > 0 || hours > 0 || minutes > 0) {
	                    result += ", ";
	                } else if (seconds > 0) {
	                    result += " e ";
	                }
	            }
	            if (days > 0) {
	                result += days + " dia" + (days == 1 ? "" : "s");
	                if (hours > 0 || minutes > 0) {
	                    result += ", ";
	                } else if (seconds > 0) {
	                    result += " e ";
	                }
	            }
	            if (hours > 0) {
	                result += hours + " hora" + (hours == 1 ? "" : "s");
	                if (minutes > 0) {
	                    result += ", ";
	                } else if (seconds > 0) {
	                    result += " e ";
	                }
	            }
	            if (minutes > 0) {
	                result += minutes + " minuto" + (minutes == 1 ? "" : "s");
	                if (seconds > 0) {
	                    result += " e ";
	                }
	            }
	            if (seconds > 0) {
	                result += seconds + " segundo" + (seconds == 1 ? "" : "s");
	            }
	            return result;
	        }
	        return "0 segundos";
	    }

	public void execute(Member sender, MessageChannel channel, String message) {
		String[] args = message.split(" ");
		if (message.length() == 8) {
			channel.sendMessage("**ERRO:** Comando incorreto, utilize !account jogador").queue();
		} else if (!SQLPlayer.getAPI().registerCheck(args[1])) {
			channel.sendMessage("**ERRO:** Não foi encontrado nenhum registro do jogador " + args[1]
					+ " em nosso banco de dados.").queue();
		} else {
			EmbedBuilder embed = new EmbedBuilder();
			embed.setColor(Color.green);
			embed.setAuthor("Estatísticas do jogador " + args[1]);
			if(SQLTimer.getAPI().checkTimer(args[1])) {
				embed.setDescription("\n **Informações básicas:** \nJogador: " + args[1] + " \nUUID: "
						+ SQLPerms.getAPI().getUUID(args[1]) + "\nTempo online: " + sToTimeComplete(SQLTimer.getAPI().getPlayerSeconds(args[1])) + "  \nGrupo: " + SQLPerms.getAPI().getGroup(args[1])
						+ "\nXP: " + SQLEmail.getAPI().getXp(args[1]) + " \nRank: "
						+ SQLEmail.getAPI().getRankComplete(SQLEmail.getAPI().getXp(args[1]))
						+ " \n\n**Informações KitPvP:** " + "\nKills: " + SQLPvP.getAPI().getKills(args[1]) + " \nDeaths: "
						+ SQLPvP.getAPI().getDeaths(args[1]) + " \n\n**Informações PotPvP:** \nVitórias"
						+ SQLPotPvP.getAPI().getVictorys(args[1]) + " \nDerrótas: "
						+ SQLPotPvP.getAPI().getDeaths(args[1]) + " \n\n**Informações Gladiator:** \nVitórias: "
						+ SQLGladiator.getAPI().getWins(args[1]) + " \nDerrótas: "
						+ SQLGladiator.getAPI().getLoses(args[1]) + " \nElo: "
						+ SQLGladiator.getAPI()
								.getElo(args[1] + " \n\n**Informações HardcoreGames** \nKills: "
										+ SQLHardcoreGames.getAPI().getDeath(args[1]) + " \nDeaths: "
										+ SQLHardcoreGames.getAPI().getDeath(args[1])));
			} else {
				embed.setDescription("\n **Informações básicas:** \nJogador: " + args[1] + " \nUUID: "
						+ SQLPerms.getAPI().getUUID(args[1]) + "\nGrupo: " + SQLPerms.getAPI().getGroup(args[1])
						+ "\nXP: " + SQLEmail.getAPI().getXp(args[1]) + " \nRank: "
						+ SQLEmail.getAPI().getRankComplete(SQLEmail.getAPI().getXp(args[1]))
						+ " \n\n**Informações KitPvP:** " + "\nKills: " + SQLPvP.getAPI().getKills(args[1]) + " \nDeaths: "
						+ SQLPvP.getAPI().getDeaths(args[1]) + " \n\n**Informações PotPvP:** \nVitórias"
						+ SQLPotPvP.getAPI().getVictorys(args[1]) + " \nDerrótas: "
						+ SQLPotPvP.getAPI().getDeaths(args[1]) + " \n\n**Informações Gladiator:** \nVitórias: "
						+ SQLGladiator.getAPI().getWins(args[1]) + " \nDerrótas: "
						+ SQLGladiator.getAPI().getLoses(args[1]) + " \nElo: "
						+ SQLGladiator.getAPI()
								.getElo(args[1]) + " \n\n**Informações HardcoreGames** \nKills: "
										+ SQLHardcoreGames.getAPI().getDeath(args[1]) + " \nDeaths: "
										+ SQLHardcoreGames.getAPI().getDeath(args[1]));
			}
			channel.sendMessage(embed.build()).queue();
		}
	}

}
