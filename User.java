//Allows the user to enter the app
public abstract class User {
	private String firstName;
	private String lastName;
	private int id;
	private String username;
	private String password;
	
	public User(String fistName, String lastName, int id, String username, String password) {
		this.firstName = fistName;
		this.lastName = lastName;
		this.id = id;
		this.username = username;
		this.password = password;
	}
	//Setter and getter
	public int getID() {
		return id;
	}
	public void setID(int id) {
		this.id = id;
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
