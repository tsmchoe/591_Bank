
//allows the user to use the app as a Bank Manager
import java.util.*;
public class Bank_Manager extends User{
	public Bank_Manager(int id, String username, String password) {
		super(id, username, password);
	}

	public Scanner sc;
	public void seeAccounts(){
		
	}
	
	public boolean loanApproval(Loan l) {
		System.out.println(l);
		System.out.println("Will you approve the loan?");
		boolean x = sc.nextBoolean();
		if(x == true) {
			//let the loan get approved
		}
		
		
	}
}
