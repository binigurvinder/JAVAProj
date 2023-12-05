package Backend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseConnection {

	// Static variables for holding single instances of Connection and Statement.
	public static Connection Connection = null;
	public static Statement Statement = null;

	// Method to establish or return an existing database connection.
	public static Connection getConnection() {
		try {
			// Singleton pattern - ensures only one connection instance.
			if (Connection == null) {
				Connection = DriverManager.getConnection(AppConsts.URL, AppConsts.USERNAME, AppConsts.PASSWORD);
			}
		} catch (Exception ex) {
			ex.printStackTrace(); // Exception handling with stack trace printing.
		}
		return Connection;
	}

	// Method to create a SQL Statement object, following the singleton pattern.
	public static Statement createStatement() {
		try {
			if (Statement == null && Connection != null) {
				Statement = Connection.createStatement();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return null; // Returns null if statement creation fails.
		}
		return Statement;
	}

	// Method to close the database connection safely.
	public static void closeConnection() {
		if (Connection != null) {
			try {
				Connection.close();
				Connection = null; // Nullify the connection after closing.
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	// Method to close the SQL Statement safely.
	public static void closeStatement() {
		if (Statement != null) {
			try {
				Statement.close();
				Statement = null; // Nullify the statement after closing.
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
