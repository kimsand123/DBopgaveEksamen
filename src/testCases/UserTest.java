package testCases;

import java.sql.Connection;
import ExceptionHandling.*;
import java.util.ArrayList;
import DAO.DAOuser;
import DTO.UserDTO;
import connector01917.Connector;

public class UserTest {

	public static void Run() {
		Connection conn = null;
		try {
			conn = Connector.getConnection();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<String> Roles = new ArrayList<String>();
		ArrayList<UserDTO> userList = new ArrayList<UserDTO>();
		Roles.add("Administrator");
		Roles.add("Foreman");
		Roles.add("Master_Chef");
		Roles.add("Operatoer");
		UserDTO DTO = new UserDTO(10, "Bjaren", "Ris", "BR", "210494-2000", "KHah1241", Roles);
		 
		
		
		DAOuser DAO = new DAOuser(conn);
		
		
		
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
		/*
		System.out.println("Press Enter to change something about the new user");
		try{System.in.read();}
		catch(Exception e){}
		
		// opdatere nogle celler og printer listen ud
		DTO.setUser_id(15);
		DTO.setUser_efternavn("Sørensen");
		DAO.updateUser(DTO);
		*/
		// Printer vores bruger ud.
		userList = DAO.getUserList();
		printlist(userList);
		
		System.out.println("Press Enter to make the user disappear");
		try{System.in.read();}
		catch(Exception e){}
		
		DAO.deleteUser(10);
		/*
		// Printer listen af bruger ud.
		userList = DAO.getUserList();
		printlist(userList);
		System.out.println("Press Enter to end user demonstartion");
		try{System.in.read();}
		catch(Exception e){}
		*/
		
		DAO.closeUserDAO();
		
	}
	public static void printlist(ArrayList<UserDTO> listDTO)
	{
		for (UserDTO DTO1 : listDTO) {
			System.out.println("\n");
			System.out.println("ID: " + DTO1.getUser_id() +" "+ "Fornavn:  " + DTO1.getUser_fornavn() +" "+ "Efternavn: "
					+ DTO1.getUser_efternavn() +" "+ "initialer: " + DTO1.getUser_ini() +" "+ "CPR: " + DTO1.getUser_cpr()
					+" "+ "Password: " + DTO1.getUser_password());

			for (int x = 0; x < DTO1.getRoles().size(); x++) {

				System.out.println(DTO1.getRoles().get(x) + ", ");

			}
		}
		
	}

}
