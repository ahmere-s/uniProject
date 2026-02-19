import java.io.Serializable;

public class UserSession implements Serializable {
	public String userName;
	public int numOperations;
	public int totalPrimeChecks;
	
	public UserSession(String userName){
		this.userName = userName;
		this.numOperations = 0;
		this.totalPrimeChecks = 0;
	}
}
