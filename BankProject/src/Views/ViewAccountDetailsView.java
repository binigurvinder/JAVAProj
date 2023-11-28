package Views;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Controllers.AccountController;
import Models.Account;

public class ViewAccountDetailsView extends JFrame {

	Account _account;
	AccountController _accountController;
	public JFrame ParentView;

	public ViewAccountDetailsView(JFrame parentView, Account account, AccountController accountController) {
		_account = account;
		_accountController = accountController;
		ParentView = parentView;

		CreateFrame();
	}

	public void RefreshAccountAndCreateFrame() throws SQLException {

		System.out.println("complete");

		setVisible(false);

		_account = _accountController.GetAccount(_account.getAccountNumber());

		System.out.println(_account.getAccountNumber());
		System.out.println(_account.getBalance());

		// CreateFrame();

	}

	private void CreateFrame() {
		// Create a new frame for viewing account details
		setTitle("Account Details");
		setLayout(new BorderLayout());

		// Panel to hold account details
		JPanel detailsPanel = new JPanel(new GridLayout(0, 2, 10, 10));
		detailsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		// Add labels for each attribute in the Account model
		addDetailToPanel(detailsPanel, "Account Number:", _account.getAccountNumber()); // Replace with actual data
		addDetailToPanel(detailsPanel, "Balance:", String.valueOf(_account.getBalance()));
		addDetailToPanel(detailsPanel, "First Name:", _account.getFirstName());
		addDetailToPanel(detailsPanel, "Last Name:", _account.getLastName());
		addDetailToPanel(detailsPanel, "DOB:", String.valueOf(_account.getDob()));
		addDetailToPanel(detailsPanel, "Email:", _account.getEmail());
		addDetailToPanel(detailsPanel, "Phone Number:", _account.getPhoneNumber());
		addDetailToPanel(detailsPanel, "Address:", _account.getAddress());
		addDetailToPanel(detailsPanel, "Account Type:", _account.getAccountType());
		addDetailToPanel(detailsPanel, "Creation Date:", String.valueOf(_account.getCreationDate()));
		addDetailToPanel(detailsPanel, "Active:", String.valueOf(_account.isActive()));
		// addDetailToPanel(detailsPanel, "Deleted:", "No");
		addDetailToPanel(detailsPanel, "Date Updated:", String.valueOf(_account.getDateUpdated()));

		// Add the panel to the frame
		add(detailsPanel, BorderLayout.CENTER);

		// Set size, location and visibility
		pack(); // Or use detailsFrame.setSize(400, 600); for fixed size
		setLocationRelativeTo(ParentView);
		setVisible(true);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		// Add menu items
		JMenu menu = new JMenu("Options");
		JMenuItem depositItem = new JMenuItem("Make a Deposit");
		JMenuItem withdrawalItem = new JMenuItem("Make a Withdrawal");
		JMenuItem updateAccountItem = new JMenuItem("Update Account");
		JMenuItem deleteAccountItem = new JMenuItem("Delete Account");
		menu.add(depositItem);
		menu.add(withdrawalItem);
		menu.add(updateAccountItem);
		menu.add(deleteAccountItem);
		menuBar.add(menu);

		// Add action listeners to menu items (placeholder actions)
		depositItem.addActionListener(e -> deposit());
		withdrawalItem.addActionListener(e -> widhraw());
		updateAccountItem.addActionListener(e -> updateAccount());
		deleteAccountItem.addActionListener(e -> {
			try {
				deleteAccount();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

	}

	private void deposit() {

		DepositWidhrawView depositWidhrawView = new DepositWidhrawView(this, true, _account, _accountController);

	}

	private void widhraw() {
		DepositWidhrawView depositWidhrawView = new DepositWidhrawView(this, false, _account, _accountController);

	}

	private void updateAccount() {

		CreateAccountView createAccountView = new CreateAccountView(this, _account, _accountController);

	}

	private void deleteAccount() throws SQLException {

		// Show a confirmation dialog with Yes and No options
		int result = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the account?",
				"Confirm Deletion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

		// Check the user's response
		if (result == JOptionPane.YES_OPTION) {
			// User clicked Yes
			var response = _accountController.DeleteAccount(_account);
			if (response > 0) {

				JOptionPane.showMessageDialog(null, "Account Deleted Successfully");
				setVisible(false);

			} else {

				JOptionPane.showMessageDialog(null, "Account cannot be deleted ");

			}

			// Proceed with the deletion process
		} else {
			// User clicked No or closed the dialog
		}

	}

	private static void addDetailToPanel(JPanel panel, String label, String value) {
		panel.add(new JLabel(label));
		panel.add(new JLabel(value));
	}

}
