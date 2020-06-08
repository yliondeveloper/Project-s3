package xyz.starmc.commands;

import java.awt.Color;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.MessageChannel;
import xyz.starmc.api.Commands;

public class AuthorCMD extends Commands {

	public AuthorCMD() {
		super("author");
	}

	public void execute(Member sender, MessageChannel channel, String message) {
		EmbedBuilder embed = new EmbedBuilder();
		embed.setColor(Color.blue);
		embed.setDescription(
				"Bot desenvolvido para total suporte de d�vidas e suprir necessidades da staff, Criado pelo **yLionCodes** para ampliar totalmente o uso do discord, ficando algo �nico para o Discord, programado para conseguir simplesmente diferenciar e inovar totalmente de quaisquer outros.");
		embed.setTitle("**StarMC - Corporation**");
		channel.sendMessage(embed.build()).queue();
	}


}
