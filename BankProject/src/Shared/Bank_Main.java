package Shared;

import java.sql.SQLException;

import javax.swing.JFrame;

import Backend.AccountDTO;
import Backend.DatabaseConnection;
import Controllers.AccountController;
import Views.MenuView;

public class Bank_Main {

	private static AccountController _accountController;
	public static JFrame MenuView;

	public static void main(String[] args) throws SQLException {

		DatabaseConnection.getConnection();

		_accountController = new AccountController(new AccountDTO());

		MenuView = new MenuView(_accountController);

	}

}
