package FinalProjectPackage;

import java.sql.SQLException;
import java.util.Scanner;

public class AccountView {

	AccountController _accountController;

	public AccountView(AccountController accountController) throws SQLException {

		_accountController = accountController;
		Scanner scanner = new Scanner(System.in);
		boolean running = true;

		while (running) {
			System.out.println("\n*** Banking System ***");
			System.out.println("1. Add Account");
			System.out.println("2. View Account");
			System.out.println("3. Deposit Balance");
			System.out.println("4. Withdraw Balance");
			System.out.println("5. Update Account");
			System.out.println("6. Delete Account");
			System.out.println("7. Exit");
			System.out.print("Enter your choice: ");

			int choice = scanner.nextInt();

			switch (choice) {
			case 1:
				// Add account logic
				System.out.println("Adding an account...");
				addAccount(scanner);
				// Add method call to add account
				break;
			case 2:
				// View account logic
				System.out.println("Viewing an account...");
				viewAccount(scanner);
				// Add method call to view account
				break;
			case 3:
				// Deposit balance logic
				System.out.println("Depositing balance...");
				deposit(scanner);
				// Add method call to deposit balance
				break;
			case 4:
				// Withdraw balance logic
				System.out.println("Withdrawing balance...");
				withdraw(scanner);
				// Add method call to withdraw balance
				break;
			case 5:
				// Update account logic
				System.out.println("Updating an account...");
				updateAccount(scanner);
				// Add method call to update account
				break;
			case 6:
				// Delete account logic
				System.out.println("Deleting an account...");
				deleteAccount(scanner);
				// Add method call to delete account
				break;
			case 7:
				// Exit
				System.out.println("Exiting...");
				running = false;
				break;
			default:
				System.out.println("Invalid choice. Please enter a number between 1 and 7.");
			}
		}

		scanner.close();
		System.out.println("Thank you for using the Account Management System!");
	}

	private void addAccount(Scanner scanner) throws SQLException {
		System.out.println("Enter account number:");
		String accountNumber = scanner.next();

		System.out.println("Enter initial balance:");
		double balance = scanner.nextDouble();

		System.out.println("Enter first name:");
		String firstName = scanner.next();

		System.out.println("Enter last name:");
		String lastName = scanner.next();

		Account newAccount = new Account(accountNumber, balance, firstName, lastName);
		int result = _accountController.CreateAccount(newAccount);
		if (result == 1) {
			System.out.println("Account successfully added.");
		} else {
			System.out.println("Failed to add account.");
		}
	}

	private void viewAccount(Scanner scanner) throws SQLException {
		System.out.println("Enter account number to view:");
		String accountNumber = scanner.next();

		Account account = _accountController.GetAccount(accountNumber);
		if (account != null) {
			System.out.println("Account Number: " + account.getAccountNumber());
			System.out.println("Balance: " + account.getBalance());
			System.out.println("First Name: " + account.getFirstName());
			System.out.println("Last Name: " + account.getLastName());
		} else {
			System.out.println("Account not found.");
		}
	}

	private void deleteAccount(Scanner scanner) throws SQLException {
		System.out.println("Enter account number to delete:");
		String accountNumber = scanner.next();

		var account = _accountController.GetAccount(accountNumber);
		int result = _accountController.DeleteAccount(account);
		if (result == 1) {
			System.out.println("Account successfully deleted.");
		} else {
			System.out.println("Failed to delete account.");
		}
	}

	private void deposit(Scanner scanner) throws SQLException {
		System.out.println("Enter account number for deposit:");
		String accountNumber = scanner.next();

		System.out.println("Enter amount to deposit:");
		double amount = scanner.nextDouble();

		double newBalance = _accountController.deposit(accountNumber, String.valueOf(amount));
		if (newBalance >= 0) {
			System.out.println("Deposit successful. New balance: " + newBalance);
		} else {
			System.out.println("Failed to deposit money.");
		}
	}

	private void withdraw(Scanner scanner) throws SQLException {
		System.out.println("Enter account number for withdrawal:");
		String accountNumber = scanner.next();

		System.out.println("Enter amount to withdraw:");
		double amount = scanner.nextDouble();

		double newBalance = _accountController.withdraw(accountNumber, String.valueOf(amount));
		if (newBalance >= 0) {
			System.out.println("Withdrawal successful. New balance: " + newBalance);
		} else {
			System.out.println("Failed to withdraw money.");
		}
	}

	private void updateAccount(Scanner scanner) throws SQLException {
		System.out.println("Enter account number to update:");
		String accountNumber = scanner.next();

		Account account = _accountController.GetAccount(accountNumber);
		if (account == null) {
			System.out.println("Account not found.");
			return;
		}

		// Display current account details
		System.out.println("Current Account Details:");
		System.out.println("Account Number: " + account.getAccountNumber());
		System.out.println("Balance: " + account.getBalance());
		System.out.println("First Name: " + account.getFirstName());
		System.out.println("Last Name: " + account.getLastName());

		System.out.println("\nSelect the field to update:");
		System.out.println("1. Balance");
		System.out.println("2. First Name");
		System.out.println("3. Last Name");
		System.out.println("4. Exit Update");

		System.out.print("Enter your choice: ");
		int updateChoice = scanner.nextInt();

		switch (updateChoice) {
		case 1:
			System.out.println("Enter new balance:");
			double balance = scanner.nextDouble();
			account.setBalance(balance);
			break;
		case 2:
			System.out.println("Enter new first name:");
			String firstName = scanner.next();
			account.setFirstName(firstName);
			break;
		case 3:
			System.out.println("Enter new last name:");
			String lastName = scanner.next();
			account.setLastName(lastName);
			break;
		case 4:
			// updating = false;
			break; // Skip the update operation as user chose to exit
		default:
			System.out.println("Invalid choice. Please enter a number between 1 and 4.");
			break; // Skip the update operation as choice was invalid
		}

		// Update the account in the database
		int result = _accountController.UpdateAccount(account);
		if (result == 1) {
			System.out.println("Account successfully updated.");
		} else {
			System.out.println("Failed to update account.");
		}
	}

}
