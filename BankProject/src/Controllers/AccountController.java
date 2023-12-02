package Controllers;

import java.sql.SQLException;

import Backend.IAccountDTO;
import Models.Account;

public class AccountController {

	private IAccountDTO _accountDTO;

	public AccountController(IAccountDTO accountDTO) {
		_accountDTO = accountDTO;

	}

	public Account GetAccount(String accountNumber) throws SQLException {

		return _accountDTO.find(accountNumber);
	}

	public double deposit(String accountNumber, String balance) throws SQLException {

		// TODO Auto-generated method stub
		return _accountDTO.Deposit(accountNumber, Double.parseDouble(balance));

	}

	public double withdraw(String accountNumber, String balance) throws SQLException {
		// TODO Auto-generated method stub
		return _accountDTO.Withdraw(accountNumber, Double.parseDouble(balance));

	}

	public int DeleteAccount(Account _account) throws SQLException {
		// TODO Auto-generated method stub

		return _accountDTO.delete(_account);

	}

	public int CreateAccount(Account account) throws SQLException {

		return _accountDTO.create(account);
	}

	public int UpdateAccount(Account account) throws SQLException {

		return _accountDTO.update(account);
	}

}
