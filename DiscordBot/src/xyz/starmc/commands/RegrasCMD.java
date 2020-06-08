package xyz.starmc.commands;

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.MessageChannel;
import xyz.starmc.api.Commands;

public class RegrasCMD extends Commands {

	public RegrasCMD() {
		super("regras");
	}

	public void execute(Member sender, MessageChannel channel, String message) {
		channel.sendMessage(
				"**REGRAS:** Você pode ficar por dentro de todas as regras de nosso discord no chat #regras.").queue();
	}

}
