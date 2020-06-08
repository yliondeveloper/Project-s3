package xyz.starmc.commands;

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.MessageChannel;
import xyz.starmc.api.Commands;
import xyz.starmc.api.DiscordGroup;

public class DebugCMD extends Commands {

	public DebugCMD() {
		super("debug");
	}

	public void execute(Member sender, MessageChannel channel, String message) {
		channel.sendMessage(DiscordGroup.getGroup(sender)).queue();
	}

}
