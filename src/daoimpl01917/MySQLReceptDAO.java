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
import connector01917.Connector;
import daointerfaces01917.ReceptDAO;
import dto01917.RaavareDTO;
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
			db = Connector.getConnection();
			st = Connector.getStatement(db);

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
		Connection con = Connector.getConnection();

		try {
			Statement st = Connector.getStatement(con);

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

		Connection con = Connector.getConnection();

		try {
			// turn off auto. trans.
			con.setAutoCommit(false);

			PreparedStatement deletecomps = con.prepareStatement("CALL sp_delete_receptKomponenter(?);");
			PreparedStatement upReceptStatement = con.prepareStatement("CALL sp_updateRecept(?, ?);");
			PreparedStatement upReceptKompStatement = con.prepareStatement("CALL sp_addKompToRecept(?, ?, ?, ?);");

			// delete all existing comps
			deletecomps.setInt(1, recept.getReceptId());
			deletecomps.executeUpdate();

			// update recept
			upReceptStatement.setInt(1, recept.getReceptId());
			upReceptStatement.setString(2, recept.getReceptNavn());
			upReceptStatement.executeUpdate();

			for (ReceptKompDTO comp : recept.getComponents()) {
				upReceptKompStatement.setInt(1, recept.getReceptId());
				upReceptKompStatement.setInt(2, comp.getRaavareId());
				upReceptKompStatement.setDouble(3, comp.getNomNetto());
				upReceptKompStatement.setDouble(4, comp.getTolerance());
				upReceptKompStatement.executeUpdate();
			}

			con.commit();

			upReceptStatement.close();
			con.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<RaavareDTO> getRaavareList() {

		ArrayList<RaavareDTO> list = new ArrayList<RaavareDTO>(0);

		Connection con;
		try {
			con = Connector.getConnection();

			PreparedStatement stm = con.prepareStatement("select * from v_raavare;");

			ResultSet rs = stm.executeQuery();

			while (rs.next()) {
				list.add(createRaavareDTO(rs));
			}

		} catch (DALException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	private RaavareDTO createRaavareDTO(ResultSet rs) throws SQLException {
		return new RaavareDTO(rs.getInt("raavare_id"), rs.getString("raavare_navn"), "");
	}

	public ReceptDTO getRecept(int receptId) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

}
