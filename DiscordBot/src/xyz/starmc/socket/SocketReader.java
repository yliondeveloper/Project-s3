package xyz.starmc.socket;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.dv8tion.jda.core.EmbedBuilder;
import xyz.STARMC.api.JavaPlugin;

public class SocketReader {

	public static void main(String[] args) {
		String message = "( Proxy ) StaffChat TokenChat 543564465900355604 ResponseTT ID: 86412 Sopa_peida";
		String[] argsp = message.split(" ");
		for (int i = 0; i < argsp.length; i++) {
			System.out.println(argsp[i]);
		}
		System.out.println("TOKENCHAT: " + argsp[5]);
		System.out.println("MOTIVO: " + message.replace(
				"( Proxy ) StaffChat TokenChat " + argsp[5] + " Report " + argsp[7] + " " + argsp[8] + " ", ""));

	}

	private static SocketReader api = new SocketReader();

	public static SocketReader getAPI() {
		return api;
	}

	public void readerAPI(String message) {
		if (!message.contains("ListDayNo")) {
			System.out.println("SOCKET: " + message);
		}
		if (message.contains("StaffChat")) {
			if (message.contains("Report")) {
				String[] messagearg = message.split(" ");
				EmbedBuilder embed = new EmbedBuilder();
				embed.setAuthor("REPORT - STARMC");
				embed.setColor(Color.green);
				embed.setDescription("\n Author: " + messagearg[8] + " \nReportado: " + messagearg[7] + " \nMotivo: "
						+ message.replace("( Proxy ) StaffChat TokenChat " + messagearg[5] + " Report " + messagearg[7]
								+ " " + messagearg[8] + " ", "")
						+ " \nHorário: " + new SimpleDateFormat("HH:mm:ss").format(new Date())
						+ "\n \n Pedimos que verifique o mesmo o mais rápido possível, assim evitando mais e mais reports desnecessários e um ambiente melhor para os players, Para localizar o player digite \n/pfind (nick) \nVocê será automaticamente teleportado para o mesmo, aonde ele estiver, seja no PVP/HG/POTPVP/GLADIATOR/AFILIADOS.");
				JavaPlugin.getInstance().getAPI().getTextChannelById("548674979294871553").sendMessage(embed.build())
						.queue();
				;
				JavaPlugin.getInstance().getAPI().getTextChannelById("548674816354418703").sendMessage(embed.build())
						.queue();
				JavaPlugin.getInstance().getAPI().getTextChannelById("548675061247246357").sendMessage(embed.build())
						.queue();
				JavaPlugin.getInstance().getAPI().getTextChannelById(messagearg[5]).sendMessage(embed.build()).queue();
			} else if (message.contains("Ticket")) {
				String[] messagearg = message.split(" ");
				EmbedBuilder embed = new EmbedBuilder();
				embed.setAuthor("TICKET " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
				embed.setColor(Color.yellow);
				embed.setDescription("Author: "
						+ messagearg[9] + " \nDúvida: " + message.replace("( Proxy ) StaffChat TokenChat "
								+ messagearg[5] + " Ticket ID: " + messagearg[8] + " " + messagearg[9], "")
						+ " \nID: " + messagearg[8]);

				JavaPlugin.getInstance().getAPI().getTextChannelById("548675003369914369").sendMessage(embed.build())
						.queue();
				;
				JavaPlugin.getInstance().getAPI().getTextChannelById("548674842417823746").sendMessage(embed.build())
						.queue();
				JavaPlugin.getInstance().getAPI().getTextChannelById("548675048081195011").sendMessage(embed.build())
						.queue();
				JavaPlugin.getInstance().getAPI().getTextChannelById(messagearg[5]).sendMessage(embed.build()).queue();
			} else if (message.contains("ResponseTT")) {
				String[] messagearg = message.split(" ");
				EmbedBuilder embed = new EmbedBuilder();
				embed.setAuthor("TICKET " + messagearg[8]);
				embed.setColor(Color.green);
				embed.setDescription("Quem engendrou: " + messagearg[9] + " \nRespondido as: "
						+ new SimpleDateFormat("HH:mm:ss").format(new Date()));
				JavaPlugin.getInstance().getAPI().getTextChannelById(messagearg[5]).sendMessage(embed.build()).queue();
				;
				JavaPlugin.getInstance().getAPI().getTextChannelById("548675003369914369").sendMessage(embed.build())
						.queue();
				;
				JavaPlugin.getInstance().getAPI().getTextChannelById("548674842417823746").sendMessage(embed.build())
						.queue();
				JavaPlugin.getInstance().getAPI().getTextChannelById("548675048081195011").sendMessage(embed.build())
						.queue();
			} else if (message.contains("ListDayNo")) {
				System.out.println(
						"READER " + message.replace("( Proxy ) StaffChat TokenChat 544433140488339456 ListDayNo ", ""));
				JavaPlugin.getInstance().getAPI().getTextChannelById("549674490670612482")
						.sendMessage(message.replace("( Proxy ) StaffChat TokenChat 544433140488339456 ListDayNo ", ""))
						.queue();
				JavaPlugin.getInstance().getAPI().getTextChannelById("549674448622714890")
						.sendMessage(message.replace("( Proxy ) StaffChat TokenChat 544433140488339456 ListDayNo ", ""))
						.queue();
				JavaPlugin.getInstance().getAPI().getTextChannelById("549674472001503242")
						.sendMessage(message.replace("( Proxy ) StaffChat TokenChat 544433140488339456 ListDayNo ", ""))
						.queue();
				JavaPlugin.getInstance().getAPI().getTextChannelById("548675049348137048")
						.sendMessage(message.replace("( Proxy ) StaffChat TokenChat 544433140488339456 ListDayNo ", ""))
						.queue();
			} else if (message.contains("ListDay")) {
				System.out.println(
						"READER " + message.replace("( Proxy ) StaffChat TokenChat 544433140488339456 ListDay ", ""));
				JavaPlugin.getInstance().getAPI().getTextChannelById("549674490670612482")
						.sendMessage(message.replace("( Proxy ) StaffChat TokenChat 544433140488339456 ListDay ", ""))
						.queue();
				JavaPlugin.getInstance().getAPI().getTextChannelById("549674448622714890")
						.sendMessage(message.replace("( Proxy ) StaffChat TokenChat 544433140488339456 ListDay ", ""))
						.queue();
				JavaPlugin.getInstance().getAPI().getTextChannelById("549674472001503242")
						.sendMessage(message.replace("( Proxy ) StaffChat TokenChat 544433140488339456 ListDay ", ""))
						.queue();
				JavaPlugin.getInstance().getAPI().getTextChannelById("548675049348137048")
						.sendMessage(message.replace("( Proxy ) StaffChat TokenChat 544433140488339456 ListDay ", ""))
						.queue();
			}
		}
	}

}
