package testCases;

import java.util.ArrayList;

import DAO.DAOuser;
import DTO.UserDTO;

public class UserTest {

	public static void main(String[] args) {
		ArrayList<String> Roles = new ArrayList<String>();
		UserDTO DTO = new UserDTO(10, "Bjaren", "Ris","BR", "210494-2000", "KHah1241", Roles );
		DAOuser DAO = new DAOuser();

	DAO.createUser(DTO);
	//Printer liste af bruger ud.
	System.out.println("ID: \n"+ DTO.getUser_id()+ "Fornavn: \n "+DTO.getUser_fornavn()+ "Efternavn: \n"+DTO.getUser_efternavn()+"initialer: \n"+DTO.getUser_ini()+"CPR: \n"+DTO.getUser_cpr()+"Password: \n"+DTO.getUser_password());

	for(int x = 0; x < DTO.getRoles().size(); x++)
	{
	
		System.out.println(DTO.getRoles().get(x)+"\n");	

	}

	DTO.setUser_id(15);
	DTO.setUser_ini("Sørensen");
	
	
	DAO.updateUser(DTO);
	//Printer vores bruger ud.
	System.out.println("ID: \n"+ DTO.getUser_id()+ "Fornavn: \n "+DTO.getUser_fornavn()+ "Efternavn: \n"+DTO.getUser_efternavn()+"initialer: \n"+DTO.getUser_ini()+"CPR: \n"+DTO.getUser_cpr()+"Password: \n"+DTO.getUser_password());

	for(int x = 0; x < DTO.getRoles().size(); x++)
	{
	
		System.out.println(DTO.getRoles().get(x)+"\n");	

	}

	
	DAO.deleteUser(15);
	//Printer listen af bruger ud.
	System.out.println("ID: \n"+ DTO.getUser_id()+ "Fornavn: \n "+DTO.getUser_fornavn()+ "Efternavn: \n"+DTO.getUser_efternavn()+"initialer: \n"+DTO.getUser_ini()+"CPR: \n"+DTO.getUser_cpr()+"Password: \n"+DTO.getUser_password());

	for(int x = 0; x < DTO.getRoles().size(); x++)
	{
	
		System.out.println(DTO.getRoles().get(x)+"\n");	

	}
}

}
