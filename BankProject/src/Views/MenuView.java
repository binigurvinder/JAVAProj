package Views;

import java.awt.FlowLayout;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.PlainDocument;

import Controllers.AccountController;
import Shared.Helpers;

public class MenuView extends JFrame {

	JTextField _txtAccountNumber;
	AccountController _accountController;
	private static JPanel viewAccountPanel;

	public MenuView(AccountController accountController) throws SQLException {

		_accountController = accountController;
		createMenu();
	}

	public void createMenu() throws SQLException {

		// Create the main frame
		setTitle("Bank Application");
		setSize(300, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new FlowLayout(FlowLayout.CENTER));

		// Create buttons
		JButton btnCreateAccount = new JButton("Create New Account");
		JButton btnViewAccount = new JButton("View Account");

		// Add action listeners to buttons
		btnCreateAccount.addActionListener(e -> createAccount());
		btnViewAccount.addActionListener(e -> toggleViewAccountPanel());

		// Add buttons to the frame
		add(btnCreateAccount);
		add(btnViewAccount);

		// Initialize and add the view account panel
		initializeViewAccountPanel();
		add(viewAccountPanel);

		// Display the frame
		setVisible(true);

		toggleViewAccountPanel();
		toggleViewAccountPanel();

	}

	private void createAccount() {

		CreateAccountView createAccountView = new CreateAccountView(this, null, _accountController);

		// JOptionPane.showMessageDialog(null, "Create New Account");
	}

	private void viewAccount() throws SQLException {

		var accountNumber = _txtAccountNumber.getText();

		if (Helpers.isNotEmpty(_txtAccountNumber)) {
			// accountNumber is not null and not empty
			// You can proceed with further processing

			var account = _accountController.GetAccount(accountNumber);
			if (account != null) {

				if (account.isDeleted()) {
					JOptionPane.showMessageDialog(null, "This account is already deleted");
					return;
				}

				ViewAccountDetailsView viewAccountDetailsView = new ViewAccountDetailsView(this, account,
						_accountController);
				// viewAccountDetailsView.CreateFrame(MainFrame);

			} else {
				JOptionPane.showMessageDialog(null, accountNumber + ": Account Number does not exist");

			}

		} else {
			// accountNumber is null or empty
			// Handle the error case, maybe show a message to the user
			JOptionPane.showMessageDialog(null, "Please enter the valid account number");
		}

	}

	private void toggleViewAccountPanel() {
		// Toggle visibility of the view account panel
		viewAccountPanel.setVisible(!viewAccountPanel.isVisible());
		pack(); // Adjust the size of the frame to fit the content
	}

	private void initializeViewAccountPanel() throws SQLException {
		viewAccountPanel = new JPanel(new FlowLayout());
		_txtAccountNumber = new JTextField(20);
		((PlainDocument) _txtAccountNumber.getDocument()).setDocumentFilter(new NumericDocumentFilter());

		_txtAccountNumber.setText("123456789");

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(e -> {
			try {
				viewAccount();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		viewAccountPanel.add(new JLabel("Account Number:"));
		viewAccountPanel.add(_txtAccountNumber);
		viewAccountPanel.add(btnSubmit);

		viewAccountPanel.setVisible(false); // Initially hidden
	}

}
