package xyz.starmc.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLPotPvP {

	private static Connection con;

	private static SQLPotPvP api = new SQLPotPvP();

	public static SQLPotPvP getAPI() {
		return api;
	}

	public static boolean checkconnect() {
		return con != null;
	}

	public void startConnection() {
		if (checkconnect()) {
			System.out.println("SQLAPI Erro ao executar a conexão MySQL!");
		} else {
			try {
				con = DriverManager.getConnection("jdbc:mysql://177.54.156.184:3306/PotPvP?autoReconnect=true", "root",
						"dhfNMCnshj@uu44");
				System.out.println("SQLAPI Conexão SQLPotPvP iniciada com sucesso! ");
			} catch (SQLException e) {
				System.out.println("SQLAPI Ocorreu um erro ao efetuar a conexão MySQL (Class: SQLPotPvP)");
			}
		}
	}

	public void disconnect() {
		try {
			con.close();
			System.out.println("SQLAPI Conexão SQLPotPvP finalizada com sucesso!");
		} catch (SQLException e) {
			System.out.println("SQLAPI Ocorreu um erro ao finalizar a conexão SQLPotPvP.");
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

	public Integer getVictorys(String name) {
		try {
			PreparedStatement ps = getStatement("SELECT * FROM PotPvP WHERE NICK= ?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			rs.next();
			int Victory = rs.getInt("Victory");
			rs.close();
			ps.close();
			return Victory;
		} catch (Exception localException) {
		}
		return 0;
	}

	public Integer getDeaths(String name) {
		try {
			PreparedStatement ps = getStatement("SELECT * FROM PotPvP WHERE NICK= ?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			rs.next();
			int Defeats = rs.getInt("Defeats");
			rs.close();
			ps.close();
			return Defeats;
		} catch (Exception localException) {
		}
		return 0;
	}

}
