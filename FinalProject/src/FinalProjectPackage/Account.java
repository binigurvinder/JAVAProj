package FinalProjectPackage;

import java.util.Date;

public class Account {
    private String accountNumber;
    private double balance;
    private String firstName;
    private String lastName;

    // Constructor
    public Account(String accountNumber, double initialBalance, String firstName, String lastName) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Default constructor
    public Account() {
    }

    // Getter and setter methods for the retained fields

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}


