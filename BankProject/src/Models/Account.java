package Models;

import java.util.Date;

public class Account extends BaseModel 
{
    private String accountNumber;
    private double balance;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private String accountType; // e.g., "Savings", "Checking"
 
    private Date dob;

    public Account() {
    	
    }
    
    // Constructor
    public Account(String accountNumber, 
    		double initialBalance, 
    		String firstName,
    		String lastName,
            String email, 
            String phoneNumber, 
            String address, 
            String accountType, 
            Date dob,
            Date creationDate,
            boolean isActive,
            boolean isDeleted,
            Date dateUpdated) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
        this.firstName=firstName;
        this.lastName=lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.accountType = accountType;
        this.dob=dob;

        this.creationDate=creationDate;
        this.isActive=isActive;
        this.isDeleted=isDeleted;
        this.dateUpdated=dateUpdated;

    }

    // Getter and setter methods for each field

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
		this.accountType = accountType;
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



	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

   
}
