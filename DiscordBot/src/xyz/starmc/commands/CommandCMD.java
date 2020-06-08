package xyz.starmc.commands;

import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.MessageChannel;
import xyz.starmc.api.Commands;
import xyz.starmc.api.DiscordGroup;
import xyz.starmc.socket.SocketFunction;

public class CommandCMD extends Commands {

	public CommandCMD() {
		super("command");
	}

	public void execute(Member sender, MessageChannel channel, String message) {
		if (DiscordGroup.getGroup(sender).equalsIgnoreCase("CEO")
				|| DiscordGroup.getGroup(sender).equalsIgnoreCase("DONO")) {
			message = message.replace("cmd", "COMMAND");
			if (message.length() == 7) {
				channel.sendMessage("**ERRO:** Comando incorreto, utilize !command run (server) (comando)").queue();
			} else if (message.length() == 8) {
				channel.sendMessage("**ERRO:** Comando incorreto, utilize !command run (server) (comando)").queue();
			} else if (message.length() == 9) {
				channel.sendMessage("**ERRO:* Comando incorreto, utilize !command run (server) (comando)").queue();
			} else if (message.length() == 10) {
				channel.sendMessage("**ERRO:** Comando incorreto, utilize !command run (server) (comando)").queue();
			} else if (message.length() == 11) {
				channel.sendMessage("**ERRO:** Comando incorreto, utilize !command run (server) (comando)").queue();
			} else if (message.length() == 12) {
				channel.sendMessage("**ERRO:** Comando incorreto, utilize !command run (server) (comando)").queue();
			} else if (message.contains("Proxy")) {
               channel.sendMessage("**SOCKET-CLIENT** Comando enviado com sucesso para o BungeeCord!").queue();
               channel.sendMessage("**SOCKET-CLIENT** Comando enviado: " + message.replace("!command run Proxy ", "")).queue();
               SocketFunction.getApi().sendMessage("Proxy", message.replace("!command run Proxy ", ""));
			}
		} else {
			channel.sendMessage("**ERRO:** Você não possui permissão para executar esse comando.").queue();
		}
	}

}
