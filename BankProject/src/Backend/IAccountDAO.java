package Backend;

import java.sql.SQLException;

import Models.Account;

public interface IAccountDAO extends IBaseDAO<Account>
{
	double Deposit(String accountNumber,double amount) throws SQLException;
	double Withdraw(String accountNumber,double amount) throws SQLException;
}
