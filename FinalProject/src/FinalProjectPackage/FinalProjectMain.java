package FinalProjectPackage;

import java.sql.SQLException;

import javax.swing.JFrame;

public class FinalProjectMain {

	private static AccountController _accountController;
	public static JFrame MenuView;

	public static void main(String[] args) throws SQLException {

		DatabaseConnection.getConnection();

		_accountController = new AccountController(new AccountDAO());

		AccountView accountView = new AccountView(_accountController);

	}

}
