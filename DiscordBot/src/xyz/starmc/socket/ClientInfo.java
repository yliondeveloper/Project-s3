package xyz.starmc.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

public class ClientInfo {

	static Map<Integer, ClientInfo> ClientSocket = new HashMap<Integer, ClientInfo>();

	public static ClientInfo getSocket(Integer code) {
		if (ClientSocket.containsKey(code)) {
			return ClientSocket.get(code);
		} else {
			System.out.println("(Client) SOCKET Ocoreeu um erro ao buscar a informação do Socket!");
			return null;
		}
	}

	private InetAddress adress;
	private Integer port;
	private Integer code;
	private DatagramSocket clientSocket;
	private Boolean debug;

	public ClientInfo(String adress, Integer port, Integer code, Boolean debug) {
		try {
			this.debug = debug;
			this.adress = InetAddress.getByName(adress);
			this.port = port;
			this.code = code;
			if (this.debug) {
				System.out.println("(Client) SOCKET Informações do Socket setadas com sucesso!");
			}
		} catch (UnknownHostException e) {
			System.out.println("(Client) SOCKET Ocorreu um erro ao buscar o IP!");
		}
	}

	public void createClient() {
		try {
			this.clientSocket = new DatagramSocket();
			ClientSocket.put(this.code, this);
			if (this.debug) {
				System.out.println("(Client) SOCKET Cliente aberto, aguardando para enviar informações...");
				System.out.println("(Client) SOCKET Código da conexão: " + this.code);
			}
		} catch (SocketException e) {
			System.out.println("(Client) SOCKET Ocorreu um erro ao abrir a conexão socket!");
		}
	}

	public void closeConnection() {
		this.clientSocket.disconnect();
	}

	public void sendMessage(String message) {
		message = encript(message);
		byte[] sendData = new byte[1024];
		sendData = message.getBytes();
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, this.adress, this.port);
		try {
			clientSocket.send(sendPacket);
		} catch (IOException e) {
			System.out.println("(Client) SOCKET Ocorreu um erro ao enviar a mensagem socket!");
		}
	}

	public InetAddress getAdress() {
		return this.adress;
	}

	public Integer getPort() {
		return this.port;
	}

	public Integer getCode() {
		return this.code;
	}

	public DatagramSocket getSocket() {
		return this.clientSocket;
	}

	public Boolean getDebug() {
		return this.debug;
	}

	private static String encript(String message) {
		if (message.length() != 1024) {
			for (int i = 0; i <= 1024; i++) {
				if (message.length() < i) {
					message += "\\";
				}
			}
		}
		return message;
	}

}
