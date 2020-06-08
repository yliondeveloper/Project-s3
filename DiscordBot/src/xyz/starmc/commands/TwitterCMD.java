package xyz.starmc.commands;

import java.awt.Color;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.MessageChannel;
import xyz.starmc.api.Commands;

public class TwitterCMD extends Commands {

	public TwitterCMD() {
		super("twitter");
	}

	public void execute(Member sender, MessageChannel channel, String message) {
		EmbedBuilder embed = new EmbedBuilder();
		embed.setColor(Color.red);
		embed.setDescription(
				"Apresentamos no mesmo as principais noticias de Atualizações Pre-Lançadas, anunciamos algumas idéias de atualizações, iniciamos votações para vocês decidirem no que acham melhor para o servidor, juntamente com staffers que foram removidos da rede recentemente entre muitas outras coisas \n\nEncontre o mesmo por:\n@StarMC_");
		embed.setTitle("**Twitter**");
		channel.sendMessage(embed.build()).queue();
	}

}
