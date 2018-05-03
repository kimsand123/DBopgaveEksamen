package dto01917;

public class UserDTO {
	
	int User_id;                     
	
	String User_navn;                
	
	String User_ini;                 
	
	String User_cpr;                 
	
	String User_password;   
	
public void createUser(int User_id, String User_navn, String User_ini,String User_cpr,String User_password) {
	
	this.User_id = User_id;
			this.User_navn = User_navn;
			this.User_ini = User_ini;
			this.User_cpr = User_cpr;
			this.User_password = User_password;
	
		
	}
	
}
