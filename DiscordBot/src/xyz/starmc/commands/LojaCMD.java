package xyz.starmc.commands;

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.MessageChannel;
import xyz.starmc.api.Commands;

public class LojaCMD extends Commands {

	public LojaCMD() {
		super("loja");
	}

	public void execute(Member sender, MessageChannel channel, String message) {
		channel.sendMessage(
				"**LOJA: ** Para ver todos os produtos que estão a venda em nosso servidor acesse: https://loja.starmc.com.br/")
				.queue();
	}

}
