package FinalProjectPackage;

import java.sql.SQLException;

public class AccountController {

	private IAccountDAO _accountDAO;

	public AccountController(IAccountDAO accountDAO) {
		_accountDAO = accountDAO;

	}

	public Account GetAccount(String accountNumber) throws SQLException {

		return _accountDAO.find(accountNumber);
	}

	public double deposit(String accountNumber, String balance) throws SQLException {

		// TODO Auto-generated method stub
		return _accountDAO.deposit(accountNumber, Double.parseDouble(balance));

	}

	public double withdraw(String accountNumber, String balance) throws SQLException {
		// TODO Auto-generated method stub
		return _accountDAO.withdraw(accountNumber, Double.parseDouble(balance));

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
