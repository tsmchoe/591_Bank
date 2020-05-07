import java.util.ArrayList;

public abstract class User {
	protected int userID;
	protected String firstName;
	protected String lastName;
	protected String username;
	protected String password;
	protected double balance;
	protected static Bank bank;

	public User(int userID, String fistName, String lastName, String username, String password, double balance) {
		setUserID(userID);
		setFirstname(fistName);
		setLastname(lastName);
		setUsername(username);
		setPassword(password);
		setBalance(balance);
		if(bank == null) {
			bank = new Bank();
		}
	}

	public abstract double getBalance();

	public void setBalance(double balance) {
		this.balance = balance;
	}

	// Getters and Setters
	public int getUserID() {
		return userID;
	}

	private void setUserID(int userID) {
		this.userID = userID;
	}


	public void setFirstname(String firstName) {
		this.firstName = firstName;
	}

	public void setLastname(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String toString() {
		return firstName + " " + lastName;
	}
}
