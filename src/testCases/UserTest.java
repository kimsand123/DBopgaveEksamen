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

	DTO.setUser_id(15);
	DTO.setUser_ini("Sørensen");
	DAO.updateUser(DTO);
	
	DAO.deleteUser(15);	
	}

}
