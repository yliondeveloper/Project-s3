package xyz.starmc.socket;

public class SocketFunction {

	private static SocketFunction api = new SocketFunction();

	public static SocketFunction getApi() {
		return api;
	}

	public void sendMessage(String server, String message) {
		ClientInfo sendMessage = new ClientInfo("127.0.0.1", getPort(server), 2, false);
		sendMessage.createClient();
		sendMessage.sendMessage(getServer(server) + " "+ message.replace("§", "&"));
		sendMessage.closeConnection();
	}

	public Integer getPort(String server) {
		if (server.equalsIgnoreCase("AuthServer")) {
			return 3007;
		} else if (server.equalsIgnoreCase("Lobby")) {
			return 3002;
		} else if (server.equalsIgnoreCase("PotPvP")) {
			return 3003;
		} else if (server.equalsIgnoreCase("PvP")) {
			return 3004;
		} else if (server.equalsIgnoreCase("Gladiator")) {
			return 3005;
		} else if (server.equalsIgnoreCase("ScreenShare")) {
			return 3006;
		} else if (server.equalsIgnoreCase("MainServer")) {
			return 3000;
		} else if (server.equalsIgnoreCase("Proxy")) {
			return 3001;
		} else {
			return 0;
		}
	}

	public String getServer(String server) {
		if (server.equalsIgnoreCase("AuthServer")) {
			return "( AUTHSERVER )";
		} else if (server.equalsIgnoreCase("Lobby")) {
			return "( Lobby ) ";
		} else if (server.equalsIgnoreCase("PotPvP")) {
			return "( PotPvP ) ";
		} else if (server.equalsIgnoreCase("PvP")) {
			return "( PvP ) ";
		} else if (server.equalsIgnoreCase("Gladiator")) {
			return "( Gladiator ) ";
		} else if (server.equalsIgnoreCase("ScreenShare")) {
			return "( ScreenShare ) ";
		} else if (server.equalsIgnoreCase("MainServer")) {
			return "( MainServer ) ";
		} else if (server.equalsIgnoreCase("Proxy")) {
			return "( Proxy )";
		} else {
			return " ( NOSERVER )";
		}
	}

}
