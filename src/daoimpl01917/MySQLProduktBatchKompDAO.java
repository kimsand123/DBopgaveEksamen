package daoimpl01917;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import DAO.DALException;
import connector01917.Connector;
import daointerfaces01917.ProduktBatchKompDAO;
import dto01917.ProduktBatchKompDTO;

public class MySQLProduktBatchKompDAO implements ProduktBatchKompDAO {

	public ProduktBatchKompDTO getProduktBatchKomp(int pbId, int rbId) throws DALException {
		Connection db = null;
		Statement st = null;
		boolean found = false; // username found or not.
		ProduktBatchKompDTO pbk = null;
		
		try {
			
			db = Connector.connectToDatabase("jdbc:mysql://mysql3.unoeuro.com:3306/nybaad_dk_db2", "nybaad_dk", "rgkd49cz");
			st = db.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			String query = "CALL sp_get_produktbatchkomp (?,?)";

			PreparedStatement tmp = db.prepareStatement(query);
			tmp.setInt(1, pbId); // produktbatchID
			tmp.setInt(2, rbId);
		
			ResultSet rs = tmp.executeQuery();
			if(rs.next()){
				pbk = new ProduktBatchKompDTO(rs.getInt("pb_id"), rs.getInt("rb_id"), rs.getDouble("netto"), rs.getInt("opr_id"));
				}
			
		
			
			
			rs.close();
			st.close();
			db.close();
			return pbk;
		} catch (Exception e) {
			throw new DALException(e);
		}
		
	}

	public List<ProduktBatchKompDTO> getProduktBatchKompList(int pbId) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ProduktBatchKompDTO> getProduktBatchKompList() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	public void createProduktBatchKomp(ProduktBatchKompDTO produktbatchkomponent) throws DALException {
		Connection db = null;
		Statement st = null;
		boolean found = false; // username found or not.
		
		try {
			
			db = Connector.connectToDatabase("jdbc:mysql://mysql3.unoeuro.com:3306/nybaad_dk_db2", "nybaad_dk", "rgkd49cz");
			st = db.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			
			String query = "CALL sp_create_productbatchkomponent (?,?,?,?)";

			PreparedStatement tmp = db.prepareStatement(query);
			tmp.setInt(1, produktbatchkomponent.getPbId()); // produktbatchID
			tmp.setInt(2, produktbatchkomponent.getRbId());
			tmp.setDouble(3, produktbatchkomponent.getNetto());
			tmp.setInt(4, produktbatchkomponent.getOprId());

			tmp.executeUpdate();
			
			System.out.println("ProduktBatchKomp oprettet");// TODO Auto-generated method stub
			
			st.close();
			db.close();
		} catch (Exception e) {
			throw new DALException(e);
		}
	}

	public void updateProduktBatchKomp(ProduktBatchKompDTO produktbatchkomponent) throws DALException {
		// TODO Auto-generated method stub

	}

}
