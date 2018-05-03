package DAO;

import connector01917.Connector;
import connector01917.Constant;
import dto01917.UserDTO;
import DAOinterfaces.*;
import DTO.ProductNProviderDTO;
import java.sql.*;




public class DAOuser {
	private Connection createConn()
	{
		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mysql://mysql3.unoeuro.com:3306/nybaad_dk_db2", "nybaad_dk", "rgkd49cz");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
		
	}
	
	
	
	public void createUser( UserDTO userDTO) {
		
						
			 
		Connection conn = createConn();
		
		
	CallableStatement castm = null;
	try {
		castm = conn.prepareCall("{call sp_create_medarbejder(?,?,?,?,?)}");
		castm.setInt(1, userDTO.getOprId());
		castm.setString(2, userDTO.getOprNavn());
		castm.setString(3,userDTO.getIni());
		castm.setString(4, userDTO.getCpr());
		castm.setString(5,userDTO.getPassword());
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
	
	
	
	
	public void updateUser(UserDTO userDTO) {
	
		
		
		
	}
	
	
	
	public void deleteUser(int userId)
	{
		Connection conn = createConn();
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
