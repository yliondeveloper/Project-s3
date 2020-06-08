package xyz.starmc.socket;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

public class ServerInfo {

	static Map<Integer, ServerInfo> ServerSocket = new HashMap<Integer, ServerInfo>();

	public static ServerInfo getSocket(Integer code) {
		if (ServerSocket.containsKey(code)) {
			return ServerSocket.get(code);
		} else {
			System.out.println("(Server) SOCKET §Ocorreu um erro ao buscar a informação do Socket!");
			return null;
		}
	}

	private InetAddress adress;
	private Integer port;
	private Integer code;
	private DatagramSocket serverSocket;

	public ServerInfo(String adress, Integer port, Integer code) {
		try {
			this.adress = InetAddress.getByName(adress);
			this.port = port;
			this.code = code;
			System.out.println("(Server) SOCKET Informações do Socket setadas com sucesso!");
		} catch (UnknownHostException e) {
			System.out.println("(Server) SOCKET Ocorreu um erro ao buscar o IP!");
		}
	}

	public void createServer() {
		try {
			this.serverSocket = new DatagramSocket(this.port);
			ServerSocket.put(this.code, this);
			System.out.println("(Server) SOCKET Servidor aberto, aguardando recebimento de dados...!");
			System.out.println("(Server) SOCKET Código da conexão: " + this.code);
		} catch (SocketException e) {
			System.out.println("(Server) SOCKET Ocorreu um erro ao abrir a conexão socket!");
		}
		new Thread(() -> {
			while (true) {
				try {
					byte[] receiveData = new byte[1024];
					DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
					this.serverSocket.receive(receivePacket);
					String message = descript(new String(receivePacket.getData()));
					SocketReader.getAPI().readerAPI(message);
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}
		}).start();
	}

	public void closeConnection() {
		this.serverSocket.disconnect();
	}

	public InetAddress getAdress() {
		return this.adress;
	}

	public Integer getCode() {
		return this.code;
	}

	public Integer getPort() {
		return this.port;
	}

	public DatagramSocket getSocket() {
		return this.serverSocket;
	}

	private static String descript(String message) {
		return message.replace("\\", "");
	}
}
