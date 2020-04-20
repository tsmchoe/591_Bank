//Allows the user to enter the app
public abstract class User {
	public int id;
	public String username;
	public String password;
	public User(int id, String username, String password) {
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
}
