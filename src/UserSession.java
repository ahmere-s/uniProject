import java.io.Serializable;

public class UserSession implements Serializable {
	public String userName;
	public int numOperations;
	
	public UserSession(String userName){
		this.userName = userName;
		this.numOperations = 0;
	}
}
