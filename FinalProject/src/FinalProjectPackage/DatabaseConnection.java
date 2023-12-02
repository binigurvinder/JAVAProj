package FinalProjectPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseConnection {

	public static Connection Connection = null;
	public static Statement Statement = null;

	public static Connection getConnection() {
		try {

			if (Connection == null) {
				Connection = DriverManager.getConnection(AppConsts.URL, AppConsts.USERNAME, AppConsts.PASSWORD);

			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return Connection;

	}

	public static Statement createStatement() {
		try {

			if (Statement == null && Connection != null) {
				Statement = Connection.createStatement();
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return Statement;

	}

	public static void closeConnection() {

		if (Connection != null) {
			try {
				Connection.close();
				Connection = null;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void closeStatement() {
		if (Statement != null) {
			try {
				Statement.close();
				Statement = null;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

}
