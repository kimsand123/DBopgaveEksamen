package DAO;

import java.sql.SQLException;

import com.mysql.jdbc.Connection;

import connector01917.Connector;
import connector01917.Constant;
import dto01917.ProductNProviderDTO;
import dto01917.RaavareBatchDTO;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import DAOinterfaces.*;

public class DAOCreateRaavareBatch implements IDAOFm_create_raavarebatch {
	ResultSet rset = null;
	PreparedStatement cs=null;
	RaavareBatchDTO dto;
	Connection conn = createConn();
	
	private Connection createConn()
    {

    Connection conn = null;
        try {
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://mysql3.unoeuro.com:3306/nybaad_dk_db2", "nybaad_dk", "rgkd49cz");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return conn;

    }
	
	public DAOCreateRaavareBatch() {}
	
	
	PreparedStatement rs=null;

	public void  CreateRaavare(RaavareBatchDTO raavaredto){

	
		try {
			rs = conn.prepareStatement("Execute( fm_create_raavarebatch("+raavaredto.getRbId()+", "
					+raavaredto.getRaavareId()+", "+raavaredto.getMaengde() +"))");
			rs.execute();
		} catch (SQLException e) {	System.out.println("Error concerning DB, cannot execute statement");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	
}
	
	
	
	
	
	
	
	
	
}
	

