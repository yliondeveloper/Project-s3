package xyz.starmc.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLPvP {

	private static Connection con;

	private static SQLPvP api = new SQLPvP();

	public static SQLPvP getAPI() {
		return api;
	}

	public static Connection getConnection() {
		return con;
	}

	public boolean checkconnect() {
		return con != null;
	}

	public void startConnection() {
		if (checkconnect()) {
			System.out.println("SQLAPI Erro ao executar a conexão MySQL!");
		} else {
			try {
				con = DriverManager.getConnection("jdbc:mysql://177.54.156.184:3306/KitPvP?autoReconnect=true", "root",
						"dhfNMCnshj@uu44");
				System.out.println("SQLAPI Conexão SQLPvP iniciada com sucesso! ");
			} catch (SQLException e) {
				System.out.println("SQLAPI Ocorreu um erro ao efetuar a conexão MySQL (Class: SQLPvP)");
			}
		}
	}

	public void disconnect() {
		try {
			con.close();
			System.out.println("SQLAPI Conexão SQLPvP finalizada com sucesso!");
		} catch (SQLException e) {
			System.out.println("SQLAPI Ocorreu um erro ao finalizar a conexão SQLPvP.");
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

	public Integer getKills(String name) {
		try {
			PreparedStatement ps = getStatement("SELECT * FROM PvP WHERE NICK= ?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			rs.next();
			int Kills = rs.getInt("Kills");
			rs.close();
			ps.close();
			return Kills;
		} catch (Exception localException) {
		}
		return 0;
	}

	public Integer getDeaths(String name) {
		try {
			PreparedStatement ps = getStatement("SELECT * FROM PvP WHERE NICK= ?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			rs.next();
			int Deaths = rs.getInt("Deaths");
			rs.close();
			ps.close();
			return Deaths;
		} catch (Exception localException) {
		}
		return 0;
	}

}
