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
		UserDTO DTO = new UserDTO(10, "Bjaren", "Ris", "BR", "210494-2000", "KHah1241", Roles);
		 
		
		
		DAOuser DAO = new DAOuser(conn);
		
		
		
		// Printer liste af bruger ud.
		userList = DAO.getUserList();
		printlist(userList);
		System.out.println("Press Enter to Create a new User");
		try{
			System.in.read();}
		catch(Exception e){}
		// laver en n bruger og printer listen
		DAO.createUser(DTO);
		
		
		userList = DAO.getUserList();
		printlist(userList);

		System.out.println("Press Enter to change something about the new user");
		try{
		
		System.in.read();}
		catch(Exception e){}
		
		// opdatere nogle celler og printer listen ud
		DTO.setUser_fornavn("Lasse");
		DTO.setUser_efternavn("Soerensen");
		DAO.updateUser(DTO);
		
		// Printer vores bruger ud.
		userList = DAO.getUserList();
		printlist(userList);
		
		System.out.println("Press Enter to make the user disappear");
		
		try{
			System.in.read();}
		catch(Exception e){}
		

		DAO.deleteUser(DTO.getUser_id());
		
		// Printer listen af bruger ud.
		userList = DAO.getUserList();
		printlist(userList);
		System.out.println("Press Enter to end user demonstartion");
		try {
			System.in.read();}
		catch(Exception e){}
		
		
		DAO.closeUserDAO();
		
	}
	public static void printlist(ArrayList<UserDTO> listDTO)
	{

		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("%10s %30s %20s %5s %30s", "User ID", "First Name", "Last Name", "InI", "CPR");
		System.out.println();
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------");
		for(UserDTO DTO1 : listDTO){
			System.out.format("%10s %30s %20s %5s %30s",
					DTO1.getUser_id(), DTO1.getUser_fornavn(), DTO1.getUser_efternavn(),DTO1.getUser_ini(), DTO1.getUser_cpr());
			System.out.println();
			for(String roles : DTO1.getRoles())
			{
				System.out.println(roles);
			}
			
		}
		System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------");
		}
		
	}


