package xyz.starmc.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLPlayer {

	private static Connection con;

	private static SQLPlayer api = new SQLPlayer();

	public static SQLPlayer getAPI() {
		return api;
	}

	public boolean checkconnect() {
		return con != null;
	}

	public void startConnection() {
		if (checkconnect()) {
			System.out.println("SQLAPI Erro ao executar a conexão MySQL!");
		} else {
			try {
				con = DriverManager.getConnection("jdbc:mysql://177.54.156.184:3306/SyncServers?autoReconnect=true",
						"root", "dhfNMCnshj@uu44");
				System.out.println("SQLAPI Conexão SQLPlayer iniciada com sucesso! ");
			} catch (SQLException e) {
				System.out.println("SQLAPI Ocorreu um erro ao efetuar a conexão MySQL (Class: SQLPlayer)");
			}
		}
	}

	public void disconnect() {
		try {
			con.close();
			System.out.println("SQLAPI Conexão SQLPlayer finalizada com sucesso!");
		} catch (SQLException e) {
			System.out.println("SQLAPI Ocorreu um erro ao finalizar a conexão SQLPlayer.");
		}
	}

	public PreparedStatement getStatement(String sql) {
		if (checkconnect()) {
			try {
				return con.prepareStatement(sql);
			} catch (SQLException e) {
			}
		}
		return null;
	}

	public ResultSet getResult(String sql) {
		if (checkconnect()) {
			try {
				PreparedStatement ps = getStatement(sql);
				return ps.executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public Boolean registerCheck(String nick) {
		try {
			PreparedStatement ps = getStatement("SELECT * FROM PlayerLocation WHERE NICK= '" + nick + "'");
			ResultSet rs = ps.executeQuery();
			boolean user = rs.next();
			rs.close();
			ps.close();
			return user;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return false;
	}

}
