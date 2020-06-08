package xyz.starmc.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLEmail {

	private static SQLEmail api = new SQLEmail();

	public static SQLEmail getAPI() {
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
				System.out.println("SQLAPI Conexão SQLEmail iniciada com sucesso! ");
			} catch (SQLException e) {
				System.out.println("SQLAPI Ocorreu um erro ao efetuar a conexão MySQL (Class: SQLEmail)");
			}
		}
	}

	public void disconnect() {
		try {
			con.close();
			System.out.println("SQLAPI Conexão SQLEmail finalizada com sucesso!");
		} catch (SQLException e) {
			System.out.println("SQLAPI Ocorreu um erro ao finalizar a conexão SQLEmail.");
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

	public void deleteEmail(String email) {
		try {
			con.createStatement().executeUpdate("DELETE FROM `EmailPlayer` WHERE `NICK`='" + email + "'");
		} catch (SQLException e) {
		}
	}

	public String getEmail(String name) {
		try {
			PreparedStatement ps = getStatement("SELECT * FROM EmailPlayer WHERE NICK= ?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			rs.next();
			String email = rs.getString("EMAIL");
			rs.close();
			ps.close();
			return email;
		} catch (Exception localException) {
			localException.printStackTrace();
		}
		return "";
	}

	public void registerEmail(String nick, String ip, String email) {
		try {
			PreparedStatement ps = getStatement("INSERT INTO EmailPlayer (NICK, IP, EMAIL) VALUES (?, ?, ?)");
			ps.setString(1, nick);
			ps.setString(2, ip);
			ps.setString(3, email);
			ps.executeUpdate();
			ps.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public boolean checkEmail(String nick) {
		try {
			PreparedStatement ps = getStatement("SELECT * FROM EmailPlayer WHERE NICK= '" + nick + "'");
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
	
	public String getRankComplete(int calc) {
		if (calc == 0) {
			return "Unranked";
		}
		if (calc >= 50000) {
			return "Extreme";
		}
		if (calc >= 20000) {
			return "Legendary";
		}
		if (calc >= 15000) {
			return "Master";
		}
		if (calc >= 10000) {
			return "Emerald";
		}
		if (calc >= 5000) {
			return "Diamond";
		}
		if (calc >= 3500) {
			return "Gold";
		}
		if (calc >= 2000) {
			return "Silver";
		}
		if (calc >= 1000) {
			return "Primary";
		}
		if (calc <= 0) {
			return "Unranked";
		}

		return "Unranked";
	}

	public String getRank(int calc) {
		if (calc >= 50000) {
			return "✯";
		}
		if (calc >= 20000) {
			return "✪";
		}
		if (calc >= 15000) {
			return "✾";
		}
		if (calc >= 10000) {
			return "✽";
		}
		if (calc >= 5000) {
			return "�?�";
		}
		if (calc >= 3500) {
			return "✴";
		}
		if (calc >= 2000) {
			return "✳";
		}
		if (calc >= 1000) {
			return "⚌";
		}
		if (calc >= 0) {
			return "-";
		}
		return "-";
	}

	public Integer getXp(String name) {
		try {
			PreparedStatement ps = getStatement("SELECT * FROM Xp WHERE NICK= ?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			rs.next();
			int xp = rs.getInt("EXP");
			rs.close();
			ps.close();
			return xp;
		} catch (Exception localException) {
			localException.printStackTrace();
		}
		return 0;
	}
}
