package xyz.starmc.commands;

import java.awt.Color;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.MessageChannel;
import xyz.starmc.api.Commands;

public class ContatoCMD extends Commands {

	public ContatoCMD() {
		super("contato");
	}

	public void execute(Member sender, MessageChannel channel, String message) {
		EmbedBuilder embed = new EmbedBuilder();
		embed.setColor(Color.blue);
		embed.setDescription(
				"Desenvolvemos para total suporte um meio totalmente diferente, o email, nele possu√≠mos um profissional totalmente competente para sempre conseg‚?¨uir responder as d√∫vidas. \n \nEnvie uma d√∫vida para o mesmo por: \ncontato@starmc.com.br");
		embed.setTitle("**StarMC - Corporation**");
		channel.sendMessage(embed.build()).queue();
	}

}
