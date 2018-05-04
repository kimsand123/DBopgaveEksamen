package DAO;

import connector01917.Connector;
import connector01917.Constant;
import DAOinterfaces.*;
import DTO.ProductNProviderDTO;
import DTO.UserDTO;

import java.sql.*;




public class DAOuser {
	Connection conn;
	
	
	public DAOuser() {
		try {
			conn = Connector.getConnection();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void createUser( UserDTO userDTO) {

		
		CallableStatement castm = null;
		try {
			castm = conn.prepareCall("{call sp_create_medarbejder(?,?,?,?,?,?,?,?,?,?)}");
			castm.setInt(1, userDTO.getUser_id());
			castm.setString(2, userDTO.getUser_fornavn());
			castm.setString(3, userDTO.getUser_efternavn());
			castm.setString(4, userDTO.getUser_ini());
			castm.setString(5, userDTO.getUser_cpr());
			castm.setString(6, userDTO.getUser_password());

			for(int x = 0; x < userDTO.getRoles().size(); x++)
			{
				String hell =	userDTO.getRoles().get(x);
				switch(hell) {

				case "Administrator":
					castm.setInt(7, 1);
				case "Foreman":
					castm.setInt(8, 1);
				case"Master_Chef":
					castm.setInt(9, 1);
				case"Operatoer":
					castm.setInt(10, 1);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			castm.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	

	}




	public void  updateUser(UserDTO userDTO) {



		
		CallableStatement castm = null;
		try {
			castm = conn.prepareCall("{call sp_update_medarbejder(?,?,?,?,?,?,?,?,?,?)}");
			castm.setInt(1, userDTO.getUser_id());
			castm.setString(2, userDTO.getUser_fornavn());
			castm.setString(3, userDTO.getUser_efternavn());
			castm.setString(4, userDTO.getUser_ini());
			castm.setString(5, userDTO.getUser_cpr());
			castm.setString(6, userDTO.getUser_password());

			for(int x = 0; x < userDTO.getRoles().size(); x++)
			{
				String hell =	userDTO.getRoles().get(x);
				switch(hell) {

				case "Administrator":
					castm.setInt(7, 1);
				case "Foreman":
					castm.setInt(8, 1);
				case"Master_Chef":
					castm.setInt(9, 1);
				case"Operatoer":
					castm.setInt(10, 1);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			castm.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	


	}



	public void deleteUser(int userId)
	{
		
		CallableStatement castm = null;
		try {
			castm = conn.prepareCall("{call sp_delete_medarbejder(?)");
			castm.setInt(1, userId);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			castm.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	




	}









}
