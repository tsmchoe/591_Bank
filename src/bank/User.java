
package bank;

public abstract class User {
	protected String userID;
	protected String firstName;
	protected String lastName;
	protected String username;
	protected String password;
	protected double balance;

	public User(String userID, String fistName, String lastName, String username, String password, double balance) {
		setUserID(userID);
		setFirstname(fistName);
		setLastname(lastName);
		setUsername(username);
		setPassword(password);
		setBalance(balance);
	}

	public abstract double getBalance();

	public void setBalance(double balance) {
		this.balance = balance;
	}

	// Getters and Setters
	public String getUserID() {
		return userID;
	}

	private void setUserID(String userID) {
		this.userID = userID;
	}


	private void setFirstname(String firstName) {
		this.firstName = firstName;
	}

	private void setLastname(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}
	private void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	private void setPassword(String password) {
		this.password = password;
	}

	public String toString() {
		return firstName + " " + lastName;
	}
}
