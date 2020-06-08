package xyz.starmc.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLPerms {

	private static SQLPerms api = new SQLPerms();

	public static SQLPerms getAPI() {
		return api;
	}

	private static Connection con;

	public static Connection getConnection() {
		return con;
	}

	public boolean checkConnection() {
		return con != null;
	}

	public PreparedStatement getStatement(String sql) {
		if (checkConnection()) {
			try {
				return con.prepareStatement(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public ResultSet getResult(String sql) {
		if (checkConnection()) {
			try {
				return getStatement(sql).executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public void startConnection() {
		try {
			con = DriverManager.getConnection(
					"jdbc:mysql://" + "177.54.156.184" + ":" + "3306" + "/" + "SyncServers" + "?autoReconnect=true",
					"root", "dhfNMCnshj@uu44");
			System.out.println("SQLAPI Conexão SQLPerms iniciada com sucesso! ");
		} catch (SQLException e) {
			System.out.println("SQLAPI Ocorreu um erro ao efetuar a conexão MySQL (Class: SQLPerms)");
		}
	}

	public void disconnect() {
		try {
			con.close();
			System.out.println("SQLAPI Conexão SQLPerms finalizada com sucesso!");
		} catch (SQLException e) {
			System.out.println("SQLAPI Ocorreu um erro ao finalizar a conexão SQLPerms.");
		}
	}

	public String getGroup(String name) {
		try {
			PreparedStatement ps = getStatement("SELECT * FROM Groups WHERE NICK= ?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			rs.next();
			String grupo = rs.getString("Grupo");
			rs.close();
			ps.close();

			return grupo;
		} catch (Exception localException) {
			localException.printStackTrace();
		}
		return "Sem registros.";
	}

	public String getUUID(String name) {
		try {
			PreparedStatement ps = getStatement("SELECT * FROM Groups WHERE NICK= ?");
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			rs.next();
			String grupo = rs.getString("UUID");
			rs.close();
			ps.close();
			return grupo;
		} catch (Exception localException) {
			localException.printStackTrace();
		}
		return "Sem registros.";
	}

}
