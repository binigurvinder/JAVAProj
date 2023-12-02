package FinalProjectPackage;

import java.sql.SQLException;

public interface IAccountDAO {

	// Method to create a new account in the database
	int create(Account account) throws SQLException;

	// Method to update an existing account in the database
	int update(Account account) throws SQLException;

	// Method to find an account by its account number
	Account find(Object id) throws SQLException;

	// Method to delete an account from the database
	int delete(Account account) throws SQLException;

	// Method to deposit an amount into an account
	double deposit(String accountNumber, double amount) throws SQLException;

	// Method to withdraw an amount from an account
	double withdraw(String accountNumber, double amount) throws SQLException;

	// You might also want to include the utility methods if they are part of the
	// public API,
	// otherwise, they can remain private within the AccountDAO implementation.
}
