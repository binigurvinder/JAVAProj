package Backend;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import Models.Account;

public class AccountDAO implements IAccountDAO {

	public AccountDAO() throws SQLException {

		DatabaseConnection.createStatement();

		String sql = "CREATE TABLE IF NOT EXISTS accounts (" + "accountNumber VARCHAR(255) PRIMARY KEY,"
				+ "balance DOUBLE," + "firstName VARCHAR(255)," + "lastName VARCHAR(255)," + "email VARCHAR(255),"
				+ "phoneNumber VARCHAR(255)," + "address VARCHAR(255)," + "accountType VARCHAR(255),"
				+ "creationDate DATETIME," + "isActive BOOLEAN," + "isDeleted BOOLEAN," + "dateUpdated DATETIME,"
				+ "dob DATETIME" + ");";

		DatabaseConnection.Statement.execute(sql);

		DatabaseConnection.closeStatement();

	}

	@Override
	public int create(Account account) throws SQLException {

		if (accountExists(account.getAccountNumber())) {
			// Account already exists
			return -1; // or throw an exception, or use another way to indicate the account exists
		}

		String sql = "INSERT INTO accounts (accountNumber, balance, firstName, lastName, email, phoneNumber, address, accountType,"
				+ "creationDate, isActive, isDeleted, dateUpdated, dob)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try (PreparedStatement statement = DatabaseConnection.Connection.prepareStatement(sql)) {
			statement.setString(1, account.getAccountNumber());
			statement.setDouble(2, account.getBalance());
			statement.setString(3, account.getFirstName());
			statement.setString(4, account.getLastName());
			statement.setString(5, account.getEmail());
			statement.setString(6, account.getPhoneNumber());
			statement.setString(7, account.getAddress());
			statement.setString(8, account.getAccountType());
			statement.setDate(9, new java.sql.Date(account.getCreationDate().getTime()));
			statement.setBoolean(10, account.isActive());
			statement.setBoolean(11, account.isDeleted());
			statement.setDate(12, new java.sql.Date(account.getDateUpdated().getTime()));
			statement.setDate(13, new java.sql.Date(account.getDob().getTime()));
			statement.executeUpdate();
		}
		return 1;
	}

	@Override
	public int update(Account account) throws SQLException {

		// TODO Auto-generated method stub

		String sql = "UPDATE accounts SET balance = ?, firstName = ?, lastName = ?, email = ?, phoneNumber = ?, address = ?, accountType = ?, creationDate = ?, isActive = ?, isDeleted = ?, dateUpdated = ?, dob = ? WHERE accountNumber = ?";
		try (PreparedStatement statement = DatabaseConnection.Connection.prepareStatement(sql)) {
			statement.setDouble(1, account.getBalance());
			statement.setString(2, account.getFirstName());
			statement.setString(3, account.getLastName());
			statement.setString(4, account.getEmail());
			statement.setString(5, account.getPhoneNumber());
			statement.setString(6, account.getAddress());
			statement.setString(7, account.getAccountType());
			statement.setDate(8, new java.sql.Date(account.getCreationDate().getTime()));
			statement.setBoolean(9, account.isActive());
			statement.setBoolean(10, account.isDeleted());
			statement.setDate(11, new java.sql.Date(account.getDateUpdated().getTime()));
			statement.setDate(12, new java.sql.Date(account.getDob().getTime()));
			statement.setString(13, account.getAccountNumber());
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

					// Setting properties of account from the ResultSet
					account.setAccountNumber(resultSet.getString("accountNumber"));
					account.setBalance(resultSet.getDouble("balance"));
					account.setFirstName(resultSet.getString("firstName"));
					account.setLastName(resultSet.getString("lastName"));
					account.setEmail(resultSet.getString("email"));
					account.setPhoneNumber(resultSet.getString("phoneNumber"));
					account.setAddress(resultSet.getString("address"));
					account.setAccountType(resultSet.getString("accountType"));
					account.setCreationDate(resultSet.getDate("creationDate"));
					account.setActive(resultSet.getBoolean("isActive"));
					account.setDeleted(resultSet.getBoolean("isDeleted"));
					account.setDateUpdated(resultSet.getDate("dateUpdated"));
					account.setDob(resultSet.getDate("dob")); // Assuming you have a dob field

					return account;
				}
			}
			return null;
		}

	}

	@Override
	public int delete(Account account) throws SQLException {
		// TODO Auto-generated method stub

		String sql = "UPDATE accounts SET isDeleted = ?, dateUpdated = ? WHERE accountNumber = ?";
		try (PreparedStatement statement = DatabaseConnection.Connection.prepareStatement(sql)) {
			statement.setBoolean(1, true); // Set isDeleted to true
			statement.setDate(2, new java.sql.Date(new Date().getTime())); // Set dateUpdated to current time
			statement.setString(3, account.getAccountNumber());
			return statement.executeUpdate(); // Executes the update
		}
	}

	@Override
	public double Deposit(String accountNumber, double amount) throws SQLException {

		if (amount <= 0) {
			return -1; // or throw an IllegalArgumentException
		}

		// Check if the account exist
		if (!doesAccountExist(accountNumber)) {
			return -2;
		}

		// Check if the account is deleted
		if (!isAccountNotDeleted(accountNumber)) {
			return -3;
		}

		// Check if the account is active
		if (!isAccountActive(accountNumber)) {
			return -4;
		}

		String sql = "UPDATE accounts SET balance = balance + ? , dateUpdated = ? WHERE accountNumber = ?";
		try (PreparedStatement statement = DatabaseConnection.Connection.prepareStatement(sql)) {
			statement.setDouble(1, amount);
			statement.setDate(2, new java.sql.Date(new Date().getTime())); // Set dateUpdated to current time
			statement.setString(3, accountNumber);
			statement.executeUpdate(); // Returns the number of rows affected
		}

		return find(accountNumber).getBalance();
	}

	@Override
	public double Withdraw(String accountNumber, double amount) throws SQLException {

		if (amount <= 0) {
			return -1; // or throw an IllegalArgumentException
		}

		// Check if the account exist
		if (!doesAccountExist(accountNumber)) {
			return -2;
		}

		// Check if the account is deleted
		if (!isAccountNotDeleted(accountNumber)) {
			return -3; // or indicate insufficient funds in another way
		}

		// Check if the account is deleted
		if (!isAccountActive(accountNumber)) {
			return -4; // or indicate insufficient funds in another way
		}

		// Check if the account has enough balance
		if (!hasSufficientBalance(accountNumber, amount)) {
			return -5; // or indicate insufficient funds in another way
		}

		String sql = "UPDATE accounts SET balance = balance - ? , dateUpdated = ? WHERE accountNumber = ?";
		try (PreparedStatement statement = DatabaseConnection.Connection.prepareStatement(sql)) {
			statement.setDouble(1, amount);
			statement.setDate(2, new java.sql.Date(new Date().getTime())); // Set dateUpdated to current time
			statement.setString(3, accountNumber);
			statement.executeUpdate(); // Returns the number of rows affected
		}

		return find(accountNumber).getBalance();
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

	private boolean isAccountNotDeleted(String accountNumber) throws SQLException {
		String sql = "SELECT isDeleted FROM accounts WHERE accountNumber = ?";
		try (PreparedStatement statement = DatabaseConnection.Connection.prepareStatement(sql)) {
			statement.setString(1, accountNumber);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					// Return true if isDeleted is false
					return !resultSet.getBoolean("isDeleted");
				} else {
					// Account not found
					return false;
				}
			}
		}
	}

	private boolean isAccountActive(String accountNumber) throws SQLException {
		String sql = "SELECT isActive FROM accounts WHERE accountNumber = ?";
		try (PreparedStatement statement = DatabaseConnection.Connection.prepareStatement(sql)) {
			statement.setString(1, accountNumber);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					// Return the value of isActive
					return resultSet.getBoolean("isActive");
				} else {
					// Account not found
					return false;
				}
			}
		}
	}

	private boolean doesAccountExist(String accountNumber) throws SQLException {
		String sql = "SELECT COUNT(*) FROM accounts WHERE accountNumber = ?";
		try (PreparedStatement statement = DatabaseConnection.Connection.prepareStatement(sql)) {
			statement.setString(1, accountNumber);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					// Check if the count is greater than 0
					return resultSet.getInt(1) > 0;
				}
				return false;
			}
		}
	}

}
