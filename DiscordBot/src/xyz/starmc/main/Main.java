package xyz.starmc.main;

import javax.security.auth.login.LoginException;

import xyz.starmc.api.JavaPlugin;

public class Main {

	public static void main(String[] args) throws LoginException, InterruptedException {
		JavaPlugin DiscordAPI = new JavaPlugin("NTE2NDYwOTI3MjIzMDcwNzQx.Dxe2Wg.YmNRSGb8PSrImhlvfAZfwQftIto");
		DiscordAPI.createBot();
	}

}
