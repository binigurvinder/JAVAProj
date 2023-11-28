package Backend;

import java.sql.SQLException;

import Models.Account;


public class AccountDTO implements IAccountDTO{
	
	
	private IAccountDAO _accountDAO;
	public AccountDTO() throws SQLException 
	{
		_accountDAO= new AccountDAO();
	}
	

	@Override
	public int create(Account account) throws SQLException {
		// TODO Auto-generated method stub
		return _accountDAO.create(account);

	}

	@Override
	public int update(Account account)  throws SQLException{
		// TODO Auto-generated method stub
		return _accountDAO.update(account);

	}

	@Override
	public Account find(Object id) throws SQLException {
		// TODO Auto-generated method stub
		return _accountDAO.find(id);
	}

	@Override
	public int delete(Account account) throws SQLException {
		// TODO Auto-generated method stub
		return _accountDAO.delete(account);
	}

	@Override
	public double Deposit(String accountNumber, double amount)  throws SQLException{
		// TODO Auto-generated method stub
		return _accountDAO.Deposit(accountNumber,amount);
	}

	@Override
	public double Withdraw(String accountNumber, double amount) throws SQLException {
		// TODO Auto-generated method stub
		return _accountDAO.Withdraw(accountNumber,amount);
	}

}
