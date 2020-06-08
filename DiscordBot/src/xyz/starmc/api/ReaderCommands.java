package xyz.starmc.api;

import net.dv8tion.jda.core.hooks.*;

import java.util.Map.Entry;

import net.dv8tion.jda.core.events.message.*;

public class ReaderCommands extends ListenerAdapter {

	@Override
	public void onMessageReceived(MessageReceivedEvent e) {
		if (!e.getAuthor().isBot()) {
			if (e.getMessage().getContentRaw().contains("!")) {
				if (Commands.commands.containsKey(e.getMessage().getContentRaw())) {
					Commands.commands.get(e.getMessage().getContentRaw()).execute(e.getMember(), e.getChannel(),
							e.getMessage().getContentRaw());
				} else {
					for (Entry<String, Commands> command : Commands.commands.entrySet()) {
						String[] common = e.getMessage().getContentRaw().split(" ");
						if (command.getKey().contains(common[0])) {
							Commands.commands.get(common[0]).execute(e.getMember(), e.getChannel(),
									e.getMessage().getContentRaw());
						}
					}
				}
			}
		}
	}

}
