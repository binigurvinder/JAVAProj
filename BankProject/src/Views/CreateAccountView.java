package Views;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringJoiner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;
import javax.swing.text.PlainDocument;

import Controllers.AccountController;
import Models.Account;
import Shared.Helpers;

public class CreateAccountView extends JFrame {

	JFrame _parentView;

	JTextField _txtAccountNumber;
	JTextField _txtBalance;
	JTextField _txtFirstName;
	JTextField _txtLastName;
	JTextField _txtEmail;
	JTextField _txtAddress;
	JTextField _txtAccountType;
	JTextField _txtPhoneNumber;

	AccountController _accountController;

	Account _account;
	boolean _isUpdating;

	public CreateAccountView(JFrame mainFrame, Account account, AccountController accountController) {

		_parentView = mainFrame;
		_accountController = accountController;
		_account = account;
		_isUpdating = _account != null;
		CreateFrame();
	}

	public void CreateFrame() {
		// Create a new frame for account creation
		// Create a new frame for account creation
		setTitle(_isUpdating ? "Update Account " + _account.getAccountNumber() : "Create Account");
		setLayout(new BorderLayout()); // Use BorderLayout

		// Panel to hold form components
		JPanel formPanel = new JPanel(new GridLayout(0, 2, 10, 10)); // GridLayout for form components
		formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Margin around the form

		// Create text fields for account details
		_txtAccountNumber = new JTextField();
		_txtBalance = new JTextField();
		_txtFirstName = new JTextField();
		_txtLastName = new JTextField();
		_txtEmail = new JTextField();
		_txtAddress = new JTextField();
		_txtAccountType = new JTextField();
		_txtPhoneNumber = new JTextField();

		((PlainDocument) _txtBalance.getDocument()).setDocumentFilter(new DoubleDocumentFilter());
		((PlainDocument) _txtAccountNumber.getDocument()).setDocumentFilter(new NumericDocumentFilter());
		((PlainDocument) _txtPhoneNumber.getDocument()).setDocumentFilter(new NumericDocumentFilter());

//        InputVerifier numberVerifier = new NumericDocumentFilter();
//        txtAccountNumber.setInputVerifier(numberVerifier);
//        txtBalance.setInputVerifier(numberVerifier);

		// Add components to the panel

		if (!_isUpdating) {
			formPanel.add(new JLabel("Account Number:"));
			formPanel.add(_txtAccountNumber);
		}

		formPanel.add(new JLabel("Balance:"));
		formPanel.add(_txtBalance);
		formPanel.add(new JLabel("First Name:"));
		formPanel.add(_txtFirstName);
		formPanel.add(new JLabel("Last Name:"));
		formPanel.add(_txtLastName);
		formPanel.add(new JLabel("Email:"));
		formPanel.add(_txtEmail);
		formPanel.add(new JLabel("Phone Number:"));
		formPanel.add(_txtPhoneNumber);
		formPanel.add(new JLabel("Address:"));
		formPanel.add(_txtAddress);
		formPanel.add(new JLabel("Account Type:"));
		formPanel.add(_txtAccountType);

		// Add the panel to the frame
		add(formPanel, BorderLayout.CENTER);

		// Create submit button and add it to the south of BorderLayout
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(e -> {
			try {
				submit();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		// Create a panel for the button to apply margins
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		buttonPanel.add(btnSubmit);
		add(buttonPanel, BorderLayout.SOUTH);

		// Set size, location and visibility
		pack();
		setLocationRelativeTo(null);
		setVisible(true);

		if (_isUpdating) {

			_txtBalance.setText(String.valueOf(_account.getBalance()));
			_txtFirstName.setText(_account.getFirstName());
			_txtLastName.setText(_account.getLastName());
			_txtEmail.setText(_account.getEmail());
			_txtPhoneNumber.setText(_account.getPhoneNumber());
			_txtAddress.setText(_account.getAddress());
			_txtAccountType.setText(_account.getAccountType());

		}

	}

	private void submit() throws SQLException {

		var account = new Account();

		try {
			// Retrieving text from each JTextField and setting it in the Account object
			Date now = new Date(); // This captures the current date and time

			boolean error = false;
			List<String> errorList = new ArrayList<>();

			if (!_isUpdating) {

				if (!Helpers.isNotEmpty(_txtAccountNumber)) {
					error = true;
					errorList.add("Account number is required.");
				}
			}

			if (!Helpers.isNotEmpty(_txtBalance)) {
				error = true;
				errorList.add("Valid balance is required.");
			}

			if (!Helpers.isNotEmpty(_txtFirstName)) {
				error = true;
				errorList.add("First name is required.");
			}

			if (!Helpers.isNotEmpty(_txtEmail) || !Helpers.isValidEmail(_txtEmail.getText())) {
				error = true;
				errorList.add("Valid Email is required.");
			}

			if (!Helpers.isNotEmpty(_txtPhoneNumber)) {
				error = true;
				errorList.add("Valid Phone Number is required.");
			}

			if (error) {
				StringJoiner errorMessages = new StringJoiner("\n");
				for (String errorMsg : errorList) {
					errorMessages.add(errorMsg);
				}

				String finalErrorMessage = "Please correct the following errors: \n" + errorMessages.toString();
				// Now you can use finalErrorMessage to show the errors to the user
				System.out.println(finalErrorMessage); // Or use any other method to display the error messages

				JOptionPane.showMessageDialog(null, finalErrorMessage);

				return;

			}

			account.setBalance(Double.parseDouble(_txtBalance.getText())); // Assuming balance is a double
			account.setFirstName(_txtFirstName.getText());
			account.setLastName(_txtLastName.getText());
			account.setEmail(_txtEmail.getText());
			account.setAddress(_txtAddress.getText());
			account.setAccountType(_txtAccountType.getText());
			account.setPhoneNumber(_txtPhoneNumber.getText());
			account.setDob(now);
			account.setDateUpdated(now);
			account.setActive(true); // Example, setting this to true
			account.setDeleted(false);

			if (_isUpdating) {
				account.setAccountNumber(_account.getAccountNumber());
				account.setCreationDate(_account.getCreationDate());

			} else {
				account.setAccountNumber(_txtAccountNumber.getText());
				account.setCreationDate(now);

			}

			if (_isUpdating) {

				var response = _accountController.UpdateAccount(account);
				if (response >= 1) {

					setVisible(false);
					var newAccount = _accountController.GetAccount(account.getAccountNumber());
					ViewAccountDetailsView viewAccountDetailsView = new ViewAccountDetailsView(_parentView, newAccount,
							_accountController);

					JOptionPane.showMessageDialog(null, " Account updated Successfully");

				} else {
					JOptionPane.showMessageDialog(null, " Account cannot be updated ");

				}

			} else {

				var response = _accountController.CreateAccount(account);
				if (response >= 1) {

					setVisible(false);
					var newAccount = _accountController.GetAccount(account.getAccountNumber());
					ViewAccountDetailsView viewAccountDetailsView = new ViewAccountDetailsView(_parentView, newAccount,
							_accountController);

					JOptionPane.showMessageDialog(null, "New Account Created Successfully");

				} else {
					JOptionPane.showMessageDialog(null, "New Account Cannot Be Created");

				}

			}

		} catch (NumberFormatException e) {
			// Handle potential NumberFormatException if parsing to double fails
			System.out.println("Invalid input in numeric fields");
			// You might want to show an error message to the user here
		}

	}

	private static MaskFormatter createPhoneFormatter() {
		MaskFormatter formatter = null;
		try {
			// Example pattern: (###) ###-####, you can adjust it as per your needs
			formatter = new MaskFormatter("(###) ###-####");
			formatter.setPlaceholderCharacter('_');
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return formatter;
	}

}
