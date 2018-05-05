package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import DAOinterfaces.IDAOProduktBatch;
import DTO.ProduktBatchDTO;
import DTO.ProduktBatchKompDTO;
import connector01917.Connector;

public class MySQLProduktBatchDAO implements IDAOProduktBatch {

	
	public ProduktBatchDTO getProduktBatch(int pbId) throws DALException {
		Connection db = null;
//		Statement st = null;
//		boolean found = false; // username found or not.
		ProduktBatchDTO pb = null;
		
		try {
			
			db = Connector.connectToDatabase("jdbc:mysql://mysql3.unoeuro.com:3306/nybaad_dk_db2", "nybaad_dk", "rgkd49cz");
//			st = db.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			String query = "SELECT * FROM produktbatch WHERE pb_id=?;";

			PreparedStatement tmp = db.prepareStatement(query);
			tmp.setInt(1, pbId); // produktbatchID
		
		
			ResultSet rs = tmp.executeQuery();
			if(rs.next()){
				pb = new ProduktBatchDTO(rs.getInt("pb_id"), rs.getInt("status"), rs.getInt("recept_id"));
				}
			
			rs.close();
			tmp.close();
			db.close();
			return pb;
		} catch (Exception e) {
			throw new DALException(e);
		}
		
	}

	public List<ProduktBatchDTO> getProduktBatchList() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	public void createProduktBatch(ProduktBatchDTO produktbatch)  throws DALException {
		Connection db = null;
		//Statement st = null;
		boolean found = false; // username found or not.
		
		try {
			
			db = Connector.connectToDatabase("jdbc:mysql://mysql3.unoeuro.com:3306/nybaad_dk_db2", "nybaad_dk", "rgkd49cz");
			//st = db.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			String query = "CALL fm_create_productbatch (?,?,?)";

			PreparedStatement tmp = db.prepareStatement(query);
			tmp.setInt(1, produktbatch.getPbId()); // produktbatchID
			tmp.setInt(2, produktbatch.getStatus());
			tmp.setInt(3, produktbatch.getReceptId());

			tmp.executeUpdate();
			
//			System.out.println("ProduktBatch oprettet");// TODO Auto-generated method stub
			
		//	st.close();
			tmp.close();
			db.close();
		} catch (Exception e) {
			throw new DALException(e);
		}
	}

	public void updateProduktBatch(ProduktBatchDTO produktbatch) throws DALException {
		// TODO Auto-generated method stub
		
	}

	public int getProduktBatchId() {
		
		// TODO Auto-generated method stub
		return 42;
	}

}
