package xyz.starmc.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLHardcoreGames {

	private static Connection con;

	private static SQLHardcoreGames api = new SQLHardcoreGames();

	public static SQLHardcoreGames getAPI() {
		return api;
	}

	public static Connection getConnection() {
		return con;
	}

	public static boolean checkconnect() {
		return con != null;
	}

	public void startConnection() {
		if (checkconnect()) {
			System.out.println("SQLAPI Erro ao executar a conexão MySQL!");
		} else {
			try {
				con = DriverManager.getConnection("jdbc:mysql://177.54.156.184:3306/HardcoreGames?autoReconnect=true",
						"root", "dhfNMCnshj@uu44");
				System.out.println("SQLAPI Conexão SQLHardcoreGames iniciada com sucesso! ");
			} catch (SQLException e) {
				System.out.println("SQLAPI Ocorreu um erro ao efetuar a conexão MySQL (Class: SQLHardcoreGames)");
			}
		}
	}

	public void disconnect() {
		try {
			con.close();
			System.out.println("SQLAPI Conexão SQLHardcoreGames finalizada com sucesso!");
		} catch (SQLException e) {
			System.out.println("SQLAPI Ocorreu um erro ao finalizar a conexão SQLHardcoreGames.");
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

	public Integer getKill(String name) {
		try {
			PreparedStatement ps = getStatement("SELECT * FROM HardcoreGames WHERE NICK= ?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			rs.next();
			int Kills = rs.getInt("Kills");
			rs.close();
			ps.close();
			return Kills;
		} catch (Exception localException) {
			localException.printStackTrace();
		}
		return 0;
	}

	public Integer getDeath(String name) {
		try {
			PreparedStatement ps = getStatement("SELECT * FROM HardcoreGames WHERE NICK= ?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			rs.next();
			int Deaths = rs.getInt("Deaths");
			rs.close();
			ps.close();
			return Deaths;
		} catch (Exception localException) {
			localException.printStackTrace();
		}
		return 0;
	}

	public Integer getCoin(String name) {
		try {
			PreparedStatement ps = getStatement("SELECT * FROM HardcoreGames WHERE NICK= ?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			rs.next();
			int Coins = rs.getInt("Coins");
			rs.close();
			ps.close();
			return Coins;
		} catch (Exception localException) {
			localException.printStackTrace();
		}
		return 0;
	}

}
