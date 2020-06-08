package xyz.starmc.commands;

import java.awt.Color;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.MessageChannel;
import xyz.starmc.api.Commands;
import xyz.starmc.api.DiscordGroup;
import xyz.starmc.api.JavaPlugin;
import xyz.starmc.mysql.SQLTicket;
import xyz.starmc.socket.SocketFunction;

public class TicketCMD extends Commands {

	public TicketCMD() {
		super("ticket");
	}

	public void execute(Member sender, MessageChannel channel, String message) {
		if (DiscordGroup.getGroup(sender).equalsIgnoreCase("CEO")
				|| DiscordGroup.getGroup(sender).equalsIgnoreCase("DONO")
				|| DiscordGroup.getGroup(sender).equalsIgnoreCase("CEO")
				|| DiscordGroup.getGroup(sender).equalsIgnoreCase("SUBDONO")
				|| DiscordGroup.getGroup(sender).equalsIgnoreCase("DIRETOR")
				|| DiscordGroup.getGroup(sender).equalsIgnoreCase("COORDENADOR")
				|| DiscordGroup.getGroup(sender).equalsIgnoreCase("GERENTEGCC")
				|| DiscordGroup.getGroup(sender).equalsIgnoreCase("GERENTE")
				|| DiscordGroup.getGroup(sender).equalsIgnoreCase("ADMINGC")
				|| DiscordGroup.getGroup(sender).equalsIgnoreCase("ADMIN")
				|| DiscordGroup.getGroup(sender).equalsIgnoreCase("MODERADOR+")
				|| DiscordGroup.getGroup(sender).equalsIgnoreCase("MODGC")
				|| DiscordGroup.getGroup(sender).equalsIgnoreCase("MOD")
				|| DiscordGroup.getGroup(sender).equalsIgnoreCase("TRIALGC")
				|| DiscordGroup.getGroup(sender).equalsIgnoreCase("TRIAL")
				|| DiscordGroup.getGroup(sender).equalsIgnoreCase("BUILDER")
				|| DiscordGroup.getGroup(sender).equalsIgnoreCase("HELPER+")
				|| DiscordGroup.getGroup(sender).equalsIgnoreCase("HELPER")) {
			String[] messageargs = message.split(" ");
			if (message.contains("!ticket list")) {
				try {
					PreparedStatement ps = SQLTicket.getConnection()
							.prepareStatement("SELECT * FROM Ticket ORDER BY ID DESC LIMIT 100000");
					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
						boolean stats = SQLTicket.getAPI().checkTicket(rs.getString("NICK"));
						String result = "";
						if (!stats) {
							result = "Respondido";
						} else {
							result = "Aguardando resposta";
						}
						channel.sendMessage("**[" + rs.getInt("ID") + "]** [" + rs.getString("Date") + "] Criado por **"
								+ rs.getString("NICK") + "** Status " + result + " Solicitação "
								+ rs.getString("Message")).queue();
						;
					}
				} catch (SQLException localSQLException) {
					localSQLException.printStackTrace();
				}
				channel.sendMessage("**TICKET:** Utilize !ticket responder (id) para responder um ticket.").queue();
				return;
			}
			if (message.length() <= 16) {
				channel.sendMessage("**ERRO:** Comando incorreto, utilize !ticket responder (id/nick)").queue();
			} else if (!messageargs[2].matches("[0-9]+")) {
				if (SQLTicket.getAPI().checkTicket(messageargs[2])) {
					if (SQLTicket.getAPI().checkRepost(messageargs[2])) {
						channel.sendMessage("**TICKET:** Este ticket já foi respondido!").queue();
					} else {
						try {
							SocketFunction.getApi().sendMessage("Proxy", "Response " + messageargs[2]);
							SQLTicket.getAPI().respostPlayer(SQLTicket.getAPI().getTicketIdPlayer(messageargs[2]),
									message.replace("!ticket responder " + messageargs[2], ""));
						} catch (NumberFormatException | SQLException e) {
							channel.sendMessage("**ERRO: ** Ocorreu um erro ao tentar responder este ticket!").queue();
						} finally {
							channel.sendMessage("**TICKET:** Ticket respondido com sucesso!").queue();
							EmbedBuilder embed = new EmbedBuilder();
							embed.setAuthor("TICKET " + SQLTicket.getAPI().getTicketIdPlayer(messageargs[2]));
							embed.setColor(Color.green);
							embed.setDescription("Quem engendrou: "
									+ SQLTicket.getAPI()
											.getPlayerByTicket(SQLTicket.getAPI().getTicketIdPlayer(messageargs[2]))
									+ " \nRespondido as: " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
							JavaPlugin.getInstance().getAPI().getTextChannelById("543564465900355604")
									.sendMessage(embed.build()).queue();
							JavaPlugin.getInstance().getAPI().getTextChannelById("548675003369914369")
									.sendMessage(embed.build()).queue();
							;
							JavaPlugin.getInstance().getAPI().getTextChannelById("548674842417823746")
									.sendMessage(embed.build()).queue();
							JavaPlugin.getInstance().getAPI().getTextChannelById("548675048081195011")
									.sendMessage(embed.build()).queue();
							;
						}
					}
				} else {
					channel.sendMessage("**ERRO:** O ticket selecionado não existe!").queue();
				}
			} else if (SQLTicket.getAPI().checkTicketId(Integer.parseInt(messageargs[2]))) {
				if (SQLTicket.getAPI()
						.checkRepost(SQLTicket.getAPI().getPlayerByTicket(Integer.parseInt(messageargs[2])))) {
					channel.sendMessage("**TICKET:** Este ticket já foi respondido!").queue();
				} else {
					try {
						SQLTicket.getAPI().respostPlayer(Integer.parseInt(messageargs[2]),
								message.replace("!ticket responder " + messageargs[2], ""));
					} catch (NumberFormatException | SQLException e) {
						channel.sendMessage("**ERRO: ** Ocorreu um erro ao tentar responder este ticket!").queue();
					} finally {
						channel.sendMessage("**TICKET:** Ticket respondido com sucesso!").queue();
						EmbedBuilder embed = new EmbedBuilder();
						embed.setAuthor("TICKET " + SQLTicket.getAPI().getTicketIdPlayer(
								SQLTicket.getAPI().getPlayerByTicket(Integer.parseInt(messageargs[2]))));
						embed.setColor(Color.green);
						embed.setDescription("Quem engendrou: "
								+ SQLTicket.getAPI().getPlayerByTicket(Integer.parseInt(messageargs[2]))
								+ " \nRespondido as: " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
						JavaPlugin.getInstance().getAPI().getTextChannelById("543564465900355604")
								.sendMessage(embed.build()).queue();
						JavaPlugin.getInstance().getAPI().getTextChannelById("548675003369914369")
								.sendMessage(embed.build()).queue();
						;
						JavaPlugin.getInstance().getAPI().getTextChannelById("548674842417823746")
								.sendMessage(embed.build()).queue();
						JavaPlugin.getInstance().getAPI().getTextChannelById("548675048081195011")
								.sendMessage(embed.build()).queue();
						;
						SocketFunction.getApi().sendMessage("Proxy",
								"Response " + SQLTicket.getAPI().getPlayerByTicket(Integer.parseInt(messageargs[2])));
					}
				}
			} else {
				channel.sendMessage("**ERRO:** O ticket selecionado não existe!").queue();
			}
		} else {
			channel.sendMessage("**ERRO:** Você não possui permissão para executar esse comando.").queue();
		}
	}

}
