package DAO;

import java.sql.SQLException;
import com.mysql.jdbc.Connection;
import dto01917.RaavareBatchDTO;
import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


public class DAOCreateRaavareBatch {
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
	
		Connection conn = createConn();
		
		CallableStatement st = null;
		try {
			st = conn.prepareCall("{call fm_create_RaavareBatch(?,?,?,?)}");
			st.setInt(1, raavaredto.getRbId());
			st.setInt(2, raavaredto.getRaavareId());
			st.setDouble(3, raavaredto.getMaengde());
			st.setInt(4, raavaredto.getLeverandoer());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		try {
			st.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
			
		}
		
}
	
	
	
	
	
	
	
	
	

	

