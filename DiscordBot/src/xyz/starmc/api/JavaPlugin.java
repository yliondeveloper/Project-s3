package xyz.starmc.api;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import xyz.starmc.commands.AccountCMD;
import xyz.starmc.commands.AuthorCMD;
import xyz.starmc.commands.AutorCMD;
import xyz.starmc.commands.CommandCMD;
import xyz.starmc.commands.ContatoCMD;
import xyz.starmc.commands.DebugCMD;
import xyz.starmc.commands.LojaCMD;
import xyz.starmc.commands.RegrasCMD;
import xyz.starmc.commands.SiteCMD;
import xyz.starmc.commands.TicketCMD;
import xyz.starmc.commands.TwitterCMD;
import xyz.starmc.mysql.SQLEmail;
import xyz.starmc.mysql.SQLGladiator;
import xyz.starmc.mysql.SQLHardcoreGames;
import xyz.starmc.mysql.SQLPerms;
import xyz.starmc.mysql.SQLPlayer;
import xyz.starmc.mysql.SQLPotPvP;
import xyz.starmc.mysql.SQLPvP;
import xyz.starmc.mysql.SQLTicket;
import xyz.starmc.mysql.SQLTimer;
import xyz.starmc.socket.ServerInfo;

public class JavaPlugin {

	private static JavaPlugin instance;

	public static JavaPlugin getInstance() {
		return instance;
	}

	private String token;
	private JDA jdaAPI;
	private JDABuilder jdaBuilder;

	public JavaPlugin(String token) throws LoginException {
		System.out.println("DiscordBot: Inserindo informações do BOT...");
		this.token = token;
		instance = this;
		System.out.println("DiscordBot: Informações inseridas com sucesso!");

	}

	public void createBot() throws InterruptedException, LoginException {
		System.out.println("DiscordBot: Iniciando APIDiscord...");
		this.jdaAPI = new JDABuilder(AccountType.BOT).setToken(this.token).build();
		System.out.println("DiscordBot: Carregando Listeners...");
		this.jdaAPI.addEventListener(new ReaderCommands());
		System.out.println("DiscordBot: Listeners carregadas com sucesso!");
		System.out.println("DiscordBot: Carregando comandos...");
		loadCommands();
		System.out.println("DiscordBot: Comandos carregados com sucesso!");
		System.out.println("DiscordBot: Iniciando conexão com o banco de dados...");
		SQLGladiator.getAPI().startConnection();
		SQLHardcoreGames.getAPI().startConnection();
		SQLPlayer.getAPI().startConnection();
		SQLPotPvP.getAPI().startConnection();
		SQLPvP.getAPI().startConnection();
		SQLEmail.getAPI().startConnection();
		SQLPerms.getAPI().startConnection();
		SQLTimer.getAPI().startConnection();
		SQLTicket.getAPI().startConnection();
		ServerInfo server = new ServerInfo("127.0.0.1", 3008, 8);
		server.createServer();
		System.out.println("DiscordBot: Conexão MySQL efetuada com sucesso!");
		System.out.println("DiscordBot: API iniciada com sucesso!");
		DiscordMessage.activeAPI();
	}

	@SuppressWarnings("unused")
	public void loadCommands() {
		new AuthorCMD();
		new AutorCMD();
		new TwitterCMD();
		new ContatoCMD();
		new DebugCMD();
		new AccountCMD();
		new CommandCMD();
		new TicketCMD();
		new RegrasCMD();
		new SiteCMD();
		new LojaCMD();
		new TwitterCMD();
	}

	public String getToken() {
		return this.token;
	}

	public JDA getAPI() {
		return this.jdaAPI;
	}

	public JDABuilder getBuilder() {
		return this.jdaBuilder;
	}

}
