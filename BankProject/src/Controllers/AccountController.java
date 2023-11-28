package Controllers;

import java.sql.SQLException;

import Backend.IAccountDTO;
import Models.Account;

public class AccountController {

	private IAccountDTO _accountDAO;

	public AccountController(IAccountDTO accountDAO) {
		_accountDAO = accountDAO;

	}

	public Account GetAccount(String accountNumber) throws SQLException {

		return _accountDAO.find(accountNumber);
	}

	public double deposit(String accountNumber, String balance) throws SQLException {

		// TODO Auto-generated method stub
		return _accountDAO.Deposit(accountNumber, Double.parseDouble(balance));

	}

	public double withdraw(String accountNumber, String balance) throws SQLException {
		// TODO Auto-generated method stub
		return _accountDAO.Withdraw(accountNumber, Double.parseDouble(balance));

	}

	public int DeleteAccount(Account _account) throws SQLException {
		// TODO Auto-generated method stub

		return _accountDAO.delete(_account);

	}

	public int CreateAccount(Account account) throws SQLException {

		return _accountDAO.create(account);
	}

	public int UpdateAccount(Account account) throws SQLException {

		return _accountDAO.update(account);
	}

}
