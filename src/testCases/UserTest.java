package testCases;

import java.util.ArrayList;

import DAO.DAOuser;
import DTO.UserDTO;

public class UserTest {

	public static void Run() {
		
		ArrayList<String> Roles = new ArrayList<String>();
		ArrayList<UserDTO> userList = new ArrayList<UserDTO>();
		UserDTO DTO = new UserDTO(10, "Bjaren", "Ris", "BR", "210494-2000", "KHah1241", Roles);
		DAOuser DAO = new DAOuser();
	
		// Printer liste af bruger ud.
		userList = DAO.getUserList();
		printlist(userList);
		System.out.println("Press Enter to Create a new User");
		try{System.in.read();}
		catch(Exception e){}
		// laver en n bruger og printer listen
		DAO.createUser(DTO);
		userList = DAO.getUserList();
		printlist(userList);
		System.out.println("Press Enter to change something about the new user");
		try{System.in.read();}
		catch(Exception e){}
		// opdatere nogle celler og printer listn ud
		DTO.setUser_id(15);
		DTO.setUser_efternavn("S�rensen");
		DAO.updateUser(DTO);
		// Printer vores bruger ud.
		userList = DAO.getUserList();
		printlist(userList);
		System.out.println("Press Enter to make the user Disapear");
		try{System.in.read();}
		catch(Exception e){}
		DAO.deleteUser(15);
		// Printer listen af bruger ud.
		userList = DAO.getUserList();
		printlist(userList);
		System.out.println("Press Enter to end user demonstartion");
		try{System.in.read();}
		catch(Exception e){}
	}
	public static void printlist(ArrayList<UserDTO> listDTO)
	{
		for (UserDTO DTO1 : listDTO) {
			System.out.println("\n");
			System.out.println("ID: " + DTO1.getUser_id() + "Fornavn:  " + DTO1.getUser_fornavn() + "Efternavn: "
					+ DTO1.getUser_efternavn() + "initialer: " + DTO1.getUser_ini() + "CPR: " + DTO1.getUser_cpr()
					+ "Password: " + DTO1.getUser_password());

			for (int x = 0; x < DTO1.getRoles().size(); x++) {

				System.out.println(DTO1.getRoles().get(x) + ", ");

			}
		}
		
	}

}