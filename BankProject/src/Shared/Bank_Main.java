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
		// _menuView.createMenu();

//		 IAccountDAO accountDAO=new AccountDAO();
//		 
//		 String accountNumber = "123456789";
//	        double balance = 1000.00;
//	        String firstName = "John";
//	        String lastName = "Doe";
//	        String email = "john.doe@example.com";
//	        String phoneNumber = "123-456-7890";
//	        String address = "1234 Main St 1234 Main St 1234 Main St 1234 Main St 1234 Main St 1234 Main St";
//	        String accountType = "Checking";
//	        Date creationDate = Helpers.convertToDate(LocalDateTime.now()); // Current date and time
//
//	        // Creating an Account object
//	        Account myAccount = new Account(accountNumber, balance, firstName, lastName, email, 
//	        		phoneNumber, address, accountType, creationDate,
//	        		creationDate,true,false,creationDate);

		// accountDAO.create(myAccount);

		// accountDAO.Deposit(accountNumber, 50000);

		// accountDAO.Withdraw(accountNumber, 100);

		// System.out.println(accountDAO.Withdraw(accountNumber, 10));

//	      var account= accountDAO.find(accountNumber);
//	      account.setFirstName("binny");
//	      account.setLastName("singh");
//	      accountDAO.update(account);

		System.out.println("test");
	}

}
