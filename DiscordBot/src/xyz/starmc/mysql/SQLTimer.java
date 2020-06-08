package xyz.starmc.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLTimer {

	private static SQLTimer api = new SQLTimer();

	public static SQLTimer getAPI() {
		return api;
	}

	private static Connection con;

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
				con = DriverManager.getConnection("jdbc:mysql://177.54.156.184:3306/SyncServers?autoReconnect=true", "root",
						"dhfNMCnshj@uu44");
				System.out.println("SQLAPI Conexão SQLTimer iniciada com sucesso! ");
			} catch (SQLException e) {
				System.out.println("SQLAPI Ocorreu um erro ao efetuar a conexão MySQL (Class: SQLTimer)");
			}
		}
	}

	public void disconnect() {
		try {
			con.close();
			System.out.println("SQLAPI Conexão SQLTimer finalizada com sucesso!");
		} catch (SQLException e) {
			System.out.println("SQLAPI Ocorreu um erro ao finalizar a conexão SQLTimer.");
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

	public void createTable() {
		try {
			PreparedStatement ps = getStatement(
					"CREATE TABLE IF NOT EXISTS TimerAPI (NICK VARCHAR(100), SECONDS INTEGER(100))");
			ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public Integer getPlayerSeconds(String nick) {
		try {
			PreparedStatement ps = getStatement("SELECT * FROM TimerAPI WHERE NICK= ?");
			ps.setString(1, nick);
			ResultSet rs = ps.executeQuery();
			rs.next();
			Integer seconds = rs.getInt("SECONDS");
			rs.close();
			ps.close();
			return seconds;
		} catch (Exception localException) {
			localException.printStackTrace();
		}
		return 0;
	}

	public void registerTimer(String nick, Integer seconds) throws SQLException {
		PreparedStatement ps = getStatement("INSERT INTO TimerAPI (NICK, SECONDS) VALUES (? ,?)");
		ps.setString(1, nick);
		ps.setInt(2, seconds);
		ps.executeUpdate();
		ps.close();
	}

	public boolean checkTimer(String nick) {
		try {
			PreparedStatement ps = getStatement("SELECT * FROM TimerAPI WHERE NICK= '" + nick + "'");
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
