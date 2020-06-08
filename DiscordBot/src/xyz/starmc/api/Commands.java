package xyz.starmc.api;

import java.util.HashMap;
import java.util.Map;

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.MessageChannel;

public abstract class Commands {

	public static Map<String, Commands> commands = new HashMap<String, Commands>();

	public String command;

	public Commands(String command) {
		this.command = "!" + command;
		if (!commands.containsKey("!" + command)) {
			commands.put("!" + command, this);
		}
	}

	public abstract void execute(Member sender, MessageChannel channel, String message);

}
