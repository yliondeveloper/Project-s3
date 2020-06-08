package xyz.starmc.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLGladiator {

	private static Connection con;

	private static SQLGladiator api = new SQLGladiator();

	public static SQLGladiator getAPI() {
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
			System.out.println("§cSQLAPI Erro ao executar a conexão MySQL!");
		} else {
			try {
				con = DriverManager.getConnection("jdbc:mysql://177.54.156.184:3306/Gladiator?autoReconnect=true",
						"root", "dhfNMCnshj@uu44");
				System.out.println("SQLAPI Conexão SQLGladiator iniciada com sucesso! ");
			} catch (SQLException e) {
				System.out.println("§cSQLAPI Ocorreu um erro ao efetuar a conexão MySQL (Class: SQLGladiator)");
			}
		}
	}

	public void disconnect() {
		try {
			con.close();
			System.out.println("SQLAPI Conexão SQLGladiator finalizada com sucesso!");
		} catch (SQLException e) {
			System.out.println("§cSQLAPI Ocorreu um erro ao finalizar a conexão SQLGladiator.");
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

	public Integer getWins(String name) {
		try {
			PreparedStatement ps = getStatement("SELECT * FROM Gladiator WHERE NICK= ?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			rs.next();
			int Kills = rs.getInt("Vitorias");
			rs.close();
			ps.close();
			return Kills;
		} catch (Exception localException) {
		}
		return 0;
	}

	public Integer getLoses(String name) {
		try {
			PreparedStatement ps = getStatement("SELECT * FROM Gladiator WHERE NICK= ?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			rs.next();
			int Deaths = rs.getInt("Derrotas");
			rs.close();
			ps.close();
			return Deaths;
		} catch (Exception localException) {
		}
		return 0;
	}

	public Integer getElo(String name) {
		try {
			PreparedStatement ps = getStatement("SELECT * FROM Gladiator WHERE NICK= ?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			rs.next();
			int XP = rs.getInt("ELO");
			rs.close();
			ps.close();
			return XP;
		} catch (Exception localException) {
		}
		return 0;
	}

}
