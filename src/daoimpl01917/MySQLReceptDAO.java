package daoimpl01917;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import DAO.DALException;
import daointerfaces01917.ReceptDAO;
import dto01917.ReceptDTO;
import dto01917.ReceptKompDTO;
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

		ArrayList<ReceptKompDTO> components = new ArrayList<ReceptKompDTO>(0);
		List<ReceptDTO> list = new ArrayList<ReceptDTO>(0);
		Connection con = getConnection();

		try {
			Statement st = getStatement(con);

			// add recept komponenter to list
			ResultSet rs = st.executeQuery("SELECT * FROM v_recept_komponenter;");
			while (rs.next()) {
				components.add(createReceptKomponentDTO(rs));
			}
			rs.close();

			// add recepter
			rs = st.executeQuery("SELECT * FROM v_recepter_fcm;");
			while (rs.next()) {
				list.add(createReceptDTO(rs, components));
			}

			rs.close();
			st.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	private ReceptKompDTO createReceptKomponentDTO(ResultSet rs) {

		try {
			int rec_id = rs.getInt("recept_id");
			int rv_id = rs.getInt("raavare_id");
			double nomNetto = rs.getDouble("nom_netto");
			double tol = rs.getDouble("tolerance");
			String navn = rs.getString("raavare_navn");

			return new ReceptKompDTO(rec_id, rv_id, nomNetto, tol, navn);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private ReceptDTO createReceptDTO(ResultSet rs, ArrayList<ReceptKompDTO> components) {

		try {
			int id = rs.getInt("recept_id");
			String name = rs.getString("recept_navn");

			ReceptDTO tmp = new ReceptDTO(id, name);

			// add components
			for (ReceptKompDTO item : components) {

				if (tmp.getReceptId() == item.getReceptId())
					tmp.addComponent(item);
			}

			return tmp;

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

			return DriverManager.getConnection(
					"jdbc:mysql://mysql3.unoeuro.com:3306/nybaad_dk_db2?allowMultiQueries=true", "nybaad_dk",
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
