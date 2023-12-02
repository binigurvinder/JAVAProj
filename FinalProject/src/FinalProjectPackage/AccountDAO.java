package FinalProjectPackage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountDAO implements IAccountDAO {

	public AccountDAO() throws SQLException {
		DatabaseConnection.createStatement();

		String sql = "CREATE TABLE IF NOT EXISTS accounts (" + "accountNumber VARCHAR(255) PRIMARY KEY,"
				+ "balance DOUBLE," + "firstName VARCHAR(255)," + "lastName VARCHAR(255)" + ");";

		DatabaseConnection.Statement.execute(sql);
		DatabaseConnection.closeStatement();
	}

	@Override
	public int create(Account account) throws SQLException {
		if (accountExists(account.getAccountNumber())) {
			// Account already exists
			return -1; // or throw an exception, or use another way to indicate the account exists
		}

		String sql = "INSERT INTO accounts (accountNumber, balance, firstName, lastName)" + " VALUES (?, ?, ?, ?)";
		try (PreparedStatement statement = DatabaseConnection.Connection.prepareStatement(sql)) {
			statement.setString(1, account.getAccountNumber());
			statement.setDouble(2, account.getBalance());
			statement.setString(3, account.getFirstName());
			statement.setString(4, account.getLastName());
			statement.executeUpdate();
		}
		return 1;
	}

	@Override
	public int update(Account account) throws SQLException {
		String sql = "UPDATE accounts SET balance = ?, firstName = ?, lastName = ? WHERE accountNumber = ?";
		try (PreparedStatement statement = DatabaseConnection.Connection.prepareStatement(sql)) {
			statement.setDouble(1, account.getBalance());
			statement.setString(2, account.getFirstName());
			statement.setString(3, account.getLastName());
			statement.setString(4, account.getAccountNumber());
			return statement.executeUpdate();
		}
	}

	@Override
	public Account find(Object id) throws SQLException {
		String sql = "SELECT * FROM accounts WHERE accountNumber = ?";
		try (PreparedStatement statement = DatabaseConnection.Connection.prepareStatement(sql)) {
			statement.setString(1, (String) id);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					Account account = new Account();
					account.setAccountNumber(resultSet.getString("accountNumber"));
					account.setBalance(resultSet.getDouble("balance"));
					account.setFirstName(resultSet.getString("firstName"));
					account.setLastName(resultSet.getString("lastName"));
					return account;
				}
			}
			return null;
		}
	}

	@Override
	public int delete(Account account) throws SQLException {
		String sql = "DELETE FROM accounts WHERE accountNumber = ?";
		try (PreparedStatement statement = DatabaseConnection.Connection.prepareStatement(sql)) {
			statement.setString(1, account.getAccountNumber());
			return statement.executeUpdate(); // Executes the delete
		}
	}

	@Override
	public double deposit(String accountNumber, double amount) throws SQLException {
		if (amount <= 0) {
			return -1; // or throw an IllegalArgumentException
		}

		// Check if the account exists
		if (!accountExists(accountNumber)) {
			return -2;
		}

		String sql = "UPDATE accounts SET balance = balance + ? WHERE accountNumber = ?";
		try (PreparedStatement statement = DatabaseConnection.Connection.prepareStatement(sql)) {
			statement.setDouble(1, amount);
			statement.setString(2, accountNumber);
			statement.executeUpdate(); // Executes the update
		}

		return find(accountNumber).getBalance();
	}

	@Override
	public double withdraw(String accountNumber, double amount) throws SQLException {
		if (amount <= 0) {
			return -1; // or throw an IllegalArgumentException
		}

		// Check if the account exists
		if (!accountExists(accountNumber)) {
			return -2;
		}

		// Check if the account has enough balance
		if (!hasSufficientBalance(accountNumber, amount)) {
			return -3; // or indicate insufficient funds in another way
		}

		String sql = "UPDATE accounts SET balance = balance - ? WHERE accountNumber = ?";
		try (PreparedStatement statement = DatabaseConnection.Connection.prepareStatement(sql)) {
			statement.setDouble(1, amount);
			statement.setString(2, accountNumber);
			statement.executeUpdate(); // Executes the update
		}

		return find(accountNumber).getBalance();
	}

	private boolean accountExists(String accountNumber) throws SQLException {
		String sql = "SELECT COUNT(*) FROM accounts WHERE accountNumber = ?";
		try (PreparedStatement statement = DatabaseConnection.Connection.prepareStatement(sql)) {
			statement.setString(1, accountNumber);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					return resultSet.getInt(1) > 0;
				}
				return false;
			}
		}
	}

	private boolean hasSufficientBalance(String accountNumber, double amount) throws SQLException {
		String sql = "SELECT balance FROM accounts WHERE accountNumber = ?";
		try (PreparedStatement statement = DatabaseConnection.Connection.prepareStatement(sql)) {
			statement.setString(1, accountNumber);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					double currentBalance = resultSet.getDouble("balance");
					return currentBalance >= amount;
				}
				return false;
			}
		}
	}

}
