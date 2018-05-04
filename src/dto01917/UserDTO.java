package dto01917;

import java.util.ArrayList;


public class UserDTO {

	int User_id;                     
	String User_efternavn;                
	String User_ini;                 
	String User_cpr;                 
	String User_password;
	String User_fornavn;
	ArrayList<String> Roles = new ArrayList<String>();

	
public UserDTO(int User_id, String User_fornavn,String User_efternavn, String User_ini,String User_cpr,String User_password, ArrayList<String> Roles) {
	
			this.User_id = User_id;
			this.User_fornavn = User_fornavn;
			this.User_efternavn = User_efternavn;
			this.User_ini = User_ini;
			this.User_cpr = User_cpr;
			this.User_password = User_password;
			for(int x = 0; x <= Roles.size()-1; x++)
			{


			this.Roles.add(Roles.get(x));
			
			}
			

		}


	

	public int getUser_id() {
		return User_id;
	}

	public void setUser_id(int user_id) {
		User_id = user_id;
	}


	public String getUser_ini() {
		return User_ini;
	}

	public void setUser_ini(String user_ini) {
		User_ini = user_ini;
	}


public String getUser_fornavn() {
	return User_fornavn;
}


public String getUser_efternavn() {
	return User_efternavn;
}

public void setUser_efternavn(String user_efternavn) {
	User_efternavn = user_efternavn;
}

public ArrayList<String> getRoles() {
	return Roles;
}

public void setRoles(ArrayList<String> roles) {
	for(int x = 0; x < roles.size(); x++)
	{
	Roles.add(roles.get(x));
	
	}
	}

public void setUser_fornavn(String user_fornavn) {
	User_fornavn = user_fornavn;
}

	public String getUser_cpr() {
		return User_cpr;
	}


	public void setUser_cpr(String user_cpr) {
		User_cpr = user_cpr;
	}

	public String getUser_password() {
		return User_password;
	}

	public void setUser_password(String user_password) {
		User_password = user_password;
	}

}
