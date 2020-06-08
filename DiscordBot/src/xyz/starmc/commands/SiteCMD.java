package xyz.starmc.commands;

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.MessageChannel;
import xyz.starmc.api.Commands;

public class SiteCMD extends Commands {

	public SiteCMD() {
		super("site");
	}

	public void execute(Member sender, MessageChannel channel, String message) {
		channel.sendMessage("**SITE:** http://starmc.com.br/").queue();
	}

}
