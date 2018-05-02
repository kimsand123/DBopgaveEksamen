package daoimpl01917;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import DAO.DALException;
import daointerfaces01917.ReceptDAO;
import dto01917.ReceptDTO;
import dto01917.ReceptKompDTOtest;

public class MySQLReceptDAO implements ReceptDAO {

	// TODO move to other DAO receptkomp?
	public List<ReceptKompDTOtest> visOpskrift(String opskriftNavn) throws DALException {
		Connection db = null;
		Statement st = null;
		boolean found = false; // username found or not.
		List<ReceptKompDTOtest> list = new ArrayList<ReceptKompDTOtest>();

		try {
			db = getConnection();
			st = getStatement(db);

			String query = "CALL sp_show_recept_ver2(?)";

			PreparedStatement tmp = db.prepareStatement(query);
			tmp.setString(1, opskriftNavn); // username

			ResultSet rs = tmp.executeQuery();

			while (rs.next()) {
				list.add(new ReceptKompDTOtest(rs.getInt("recept_id"), rs.getString("raavare"), rs.getInt("raavare_id"),
						rs.getDouble("maengde"), rs.getDouble("tolerance")));
			}

			rs.close();
			st.close();
			db.close();

			return list;

		} catch (SQLException e) {
			throw new DALException(e);
		}

	}

	public List<ReceptDTO> getReceptList() throws DALException {

		List<ReceptDTO> list = new ArrayList<ReceptDTO>(0);
		Connection con = getConnection();
		String query = ";";

		try {
			Statement stmt = getStatement(con);			
			ResultSet rs = stmt.executeQuery("SELECT * FROM v_recepter_fcm;");			

			while (rs.next()) {
				list.add(createReceptDTO(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	private ReceptDTO createReceptDTO(ResultSet rs) {

		try {
			int id = rs.getInt("recept_id");
			String name = rs.getString("recept_navn");

			return new ReceptDTO(id, name);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public void createRecept(ReceptDTO recept) throws DALException {
		// TODO Auto-generated method stub

	}

	public void updateRecept(ReceptDTO recept) throws DALException {
		// TODO Auto-generated method stub

	}

	private Connection getConnection() throws DALException {

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			return DriverManager.getConnection("jdbc:mysql://mysql3.unoeuro.com:3306/nybaad_dk_db2", "nybaad_dk",
					"rgkd49cz");
		} catch (Exception e) {
			throw new DALException(e);
		}

	}

	private Statement getStatement(Connection db) throws SQLException {
		return db.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
	}

	public ReceptDTO getRecept(int receptId) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

}
