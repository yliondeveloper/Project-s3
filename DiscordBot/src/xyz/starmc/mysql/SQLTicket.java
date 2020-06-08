package xyz.starmc.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLTicket {

	private static SQLTicket api = new SQLTicket();

	public static SQLTicket getAPI() {
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
				System.out.println("SQLAPI Conexão SQLTicket iniciada com sucesso! ");
			} catch (SQLException e) {
				System.out.println("SQLAPI Ocorreu um erro ao efetuar a conexão MySQL (Class: SQLTicket)");
			}
		}
	}

	public void disconnect() {
		try {
			con.close();
			System.out.println("SQLAPI Conexão SQLTicket finalizada com sucesso!");
		} catch (SQLException e) {
			System.out.println("SQLAPI Ocorreu um erro ao finalizar a conexão SQLTicket.");
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
					"CREATE TABLE IF NOT EXISTS Ticket (NICK VARCHAR(100), ID INTEGER(100), Date VARCHAR(100), Message VARCHAR(100), Respost VARCHAR(100))");
			ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public boolean checkTicket(String nick) {
		try {
			PreparedStatement ps = getStatement("SELECT * FROM Ticket WHERE NICK= '" + nick + "'");
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

	public boolean checkTicketId(Integer ID) {
		try {
			PreparedStatement ps = getStatement("SELECT * FROM Ticket WHERE ID= '" + ID + "'");
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

	public void deleteTicket(String nick) throws SQLException {
		con.createStatement().executeUpdate("DELETE FROM `Ticket` WHERE `NICK`='" + nick + "'");
	}

	public String getTicket(String name) {
		try {
			PreparedStatement ps = getStatement("SELECT * FROM Ticket WHERE NICK= ?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			rs.next();
			String email = rs.getString("Message");
			rs.close();
			ps.close();
			return email;
		} catch (Exception localException) {
			localException.printStackTrace();
		}
		return "";
	}

	public Boolean checkRepost(String name) {
		if (!getRespost(name).equalsIgnoreCase("NULL RESPOST")) {
			return true;
		} else {
			return false;
		}
	}

	public String getRespost(String name) {
		try {
			PreparedStatement ps = getStatement("SELECT * FROM Ticket WHERE NICK= ?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			rs.next();
			String repost = rs.getString("Respost");
			rs.close();
			ps.close();
			return repost;
		} catch (Exception localException) {
			localException.printStackTrace();
		}
		return "NULL RESPOST";
	}

	public String getPlayerByTicket(Integer id) {
		try {
			PreparedStatement ps = getStatement("SELECT * FROM Ticket WHERE ID= ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			String name = rs.getString("NICK");
			rs.close();
			ps.close();
			return name;
		} catch (Exception localException) {
			localException.printStackTrace();
		}
		return "NULL RESPOST";
	}
	
	public Integer getTicketIdPlayer(String name) {
		try {
			PreparedStatement ps = getStatement("SELECT * FROM Ticket WHERE NICK= ?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			rs.next();
			Integer respost = rs.getInt("ID");
			rs.close();
			ps.close();
			return respost;
		} catch (Exception localException) {
			localException.printStackTrace();
		}
		return 0;
	}

	public void respostPlayer(Integer ID, String respost) throws SQLException {
		PreparedStatement ps = getStatement("UPDATE Ticket SET Respost= ? WHERE ID= ?");
		ps.setString(1, respost);
		ps.setInt(2, ID);
		ps.executeUpdate();
	}

	public void registerTicket(String nick, Integer ID, String date, String message, String respost)
			throws SQLException {
		PreparedStatement ps = getStatement(
				"INSERT INTO Ticket (NICK, ID, Date, Message, Respost) VALUES (? ,?, ?, ?, ?)");
		ps.setString(1, nick);
		ps.setInt(2, ID);
		ps.setString(3, date);
		ps.setString(4, message);
		ps.setString(5, respost);
		ps.executeUpdate();
		ps.close();
	}

}
