# Bank Project

## Description

The Bank Project is a comprehensive Java application that simulates banking operations. It is designed to showcase object-oriented programming principles, database management, and user interface design in Java.

## Project Structure

The application is structured into packages with a focus on separation of concerns, making it modular and maintainable:

### Backend

Contains the core backend logic, including data access layer and database connection management.

- `AccountDAO.java`: Implements data access operations for account management such as creating, updating, and fetching account details from the database.
- `AccountDTO.java`: A Data Transfer Object that carries account data between processes, ensuring a loose coupling between the database and business logic.
- `AppConsts.java`: Defines application-wide constants, likely including database connection strings and configuration settings.
- `DatabaseConnection.java`: Manages the lifecycle of database connections, ensuring efficient use of resources and handling connectivity issues.
- `IAccountDAO.java`: Interface defining the contract for account-related data access methods, promoting a decoupled architecture and easier testing.
- `IAccountDTO.java`: Interface for the Account Data Transfer Object, used to type-check account data in transit.
- `IBaseDAO.java`: Generic interface for basic data access operations, intended to be extended by more specific DAO interfaces.
- `IBaseDTO.java`: Generic interface for basic data transfer objects, providing a template for more detailed DTOs.

### Controllers

Manages the flow of data between the backend and the views, acting as an intermediary layer.

- `AccountController.java`: Handles incoming requests related to accounts, invokes backend services, and forwards responses to the appropriate views.

### Models

Represents the application's business data and enforces the business logic.

- `Account.java`: Represents an account with its properties and methods encapsulating business rules.
- `BaseModel.java`: Abstract class providing common attributes and behaviors that can be inherited by specific model classes.

### Shared

Includes classes that are used across various layers of the application, providing common functionality.

- `Bank_Main.java`: The main driver class that boots up the application and sets the initial state or screen.
- `Helpers.java`: Utility class containing static methods that provide common functionality like input validation, formatting, etc.

### Views

Manages the user interface, presenting data to the user, and interpreting user commands.

- `CreateAccountView.java`: User interface class for creating new bank accounts, includes form inputs and validation routines.
- `DepositWithdrawView.java`: Interface for deposit and withdrawal operations, handling user inputs and displaying transaction results.
- `DoubleDocumentFilter.java`: A filter for text components that restricts the input to double values, typically used in monetary input fields.
- `MenuView.java`: The main navigation view that presents the different operations a user can perform.
- `NumericDocumentFilter.java`: Similar to `DoubleDocumentFilter`, but restricts input to integer values.
- `ViewAccountDetailsView.java`: Displays detailed information about a user's bank account, including balance and transaction history.

## Installation and Setup

To get this application up and running on your local machine:

1. Ensure you have Java SE 17 and an appropriate Java IDE installed.
2. Clone the repository to your local machine.
3. Import the project into your IDE.
4. Resolve any dependencies that are required (e.g., JDBC driver for the database).
5. Build the project to compile the source files.
6. Run `Bank_Main.java` to start the application.

## Usage

After launching the application, the `MenuView` serves as the main dashboard, presenting you with various options to interact with the bank system:

- Create Account: This option leads you to `CreateAccountView`, where you can input the necessary details to open a new bank account. The interface guides you through the process, ensuring all required fields are filled out correctly before submission.

- View Details: If you select this option, the `ViewAccountDetailsView` will display comprehensive information about an existing account, such as the account holder's name, account number, balance etc.

- Update Account: From the ViewAccountDetailsView menu, you can choose to update account information. This could involve changing the account holder's personal information or modifying account features.

- Deposit: This function takes you to a secure interface where you can deposit funds into an account. You'll be prompted to enter the  amount you wish to deposit.

- Withdraw: Similarly, if you wish to withdraw funds, this option will ask for the amount to be withdrawn, processing the transaction if the funds are available.

- Delete Account: For account closure, this option will guide you through the process of safely and securely deleting an account from the bank's records.

Navigation through these options is intuitive, with clear prompts and confirmation messages to ensure successful completion of your banking tasks.
Always ensure you have the necessary information, such as account numbers and user details, at hand when performing operations.

## License

This project is open-sourced under the MIT License. See the LICENSE file for more information.
