package Views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.PlainDocument;

import Controllers.AccountController;
import Models.Account;
import Shared.Bank_Main;
import Shared.Helpers;

public class DepositWidhrawView extends JFrame {

	JFrame _parentView;

	boolean _isDepositing;
	Account _account;
	AccountController _accountController;

	public DepositWidhrawView(JFrame parentView, boolean isDepositing, Account account,
			AccountController accountController) {
		_parentView = parentView;
		_isDepositing = isDepositing;
		_account = account;
		_accountController = accountController;

		CreateView();

	}

	private void CreateView() {

		setTitle(_isDepositing ? "Deposit Balance " : "Withdraw Balance");
		setSize(400, 100);

		// Creating the components
		JLabel label = new JLabel("Amount:");
		JTextField textField = new JTextField(20); // 20 columns wide
		((PlainDocument) textField.getDocument()).setDocumentFilter(new DoubleDocumentFilter());

		JButton button = new JButton(_isDepositing ? "Deposit" : "Withdraw");

		// Adding action listener to the button
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				try {

					double response = 0;
					var balance = textField.getText();

					if (!Helpers.isNotEmpty(textField) || !Helpers.isIntegerValueGreaterThanZero(balance)) {
						JOptionPane.showMessageDialog(null, "Please add valid balance to continue");

						return;
					}

					if (balance != null && !balance.isEmpty()) {
						if (_isDepositing) {

							response = _accountController.deposit(_account.getAccountNumber(), balance);

						} else {
							response = _accountController.withdraw(_account.getAccountNumber(), balance);

						}

						if (response == -1) {
							// Handle case for -1
							JOptionPane.showMessageDialog(null, "Sorry something went wrong");

						} else if (response == -2) {
							// Handle case for -2
							JOptionPane.showMessageDialog(null, "Account Number not exist");
						} else if (response == -3) {
							// Handle case for -3
							JOptionPane.showMessageDialog(null, "Account is deleted");
						} else if (response == -4) {
							// Handle case for -4
							JOptionPane.showMessageDialog(null, "Account is not active");
						} else if (response == -5) {
							// Handle case for -5
							JOptionPane.showMessageDialog(null, "Account does not have sufficient balance");
						} else {

							String output = "The updated balance  for account number : " + _account.getAccountNumber()
									+ " is " + response;
							JOptionPane.showMessageDialog(null, output);

							setVisible(false);

							_parentView.setVisible(false);
							var account = _accountController.GetAccount(_account.getAccountNumber());

							ViewAccountDetailsView viewAccountDetailsView = new ViewAccountDetailsView(
									Bank_Main.MenuView, account, _accountController);

						}

					}

				} catch (Exception ex) {

					System.out.print(ex.getMessage());

				}

			}
		});

		// Creating a panel to add the components
		JPanel panel = new JPanel();
		panel.add(label);
		panel.add(textField);
		panel.add(button);

		// Adding the panel to the frame
		getContentPane().add(panel);

		setLocationRelativeTo(_parentView);

		// Setting the frame visibility
		setVisible(true);

	}

}
