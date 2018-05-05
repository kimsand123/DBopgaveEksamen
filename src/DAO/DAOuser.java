package DAO;

import connector01917.Connector;
import connector01917.Constant;
import DAOinterfaces.*;
import DTO.ProductNProviderDTO;
import DTO.UserDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DAOuser {
	Connection conn;

	public DAOuser(Connection conn) {

		this.conn = conn;

	}

	public void createUser(UserDTO userDTO) {

		CallableStatement castm = null;
		try {
			castm = conn.prepareCall("call sp_create_medarbejder(?,?,?,?,?,?,?,?,?,?)");
			castm.setInt(1, userDTO.getUser_id());
			castm.setString(2, userDTO.getUser_fornavn());
			castm.setString(3, userDTO.getUser_efternavn());
			castm.setString(4, userDTO.getUser_ini());
			castm.setString(5, userDTO.getUser_cpr());
			castm.setString(6, userDTO.getUser_password());

			for (int x = 0; x < userDTO.getRoles().size(); x++) {
				String hell = userDTO.getRoles().get(x);
				switch (hell) {

				case "Administrator":
					castm.setInt(7, 1);
				case "Foreman":
					castm.setInt(8, 1);
				case "Master_Chef":
					castm.setInt(9, 1);
				case "Operatoer":
					castm.setInt(10, 1);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			castm.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void updateUser(UserDTO userDTO) {

		CallableStatement castm = null;
		try {
			castm = conn.prepareCall("call sp_update_medarbejder(?,?,?,?,?,?,?,?,?,?)");
			castm.setInt(1, userDTO.getUser_id());
			castm.setString(2, userDTO.getUser_fornavn());
			castm.setString(3, userDTO.getUser_efternavn());
			castm.setString(4, userDTO.getUser_ini());
			castm.setString(5, userDTO.getUser_cpr());
			castm.setString(6, userDTO.getUser_password());

			for (int x = 0; x < userDTO.getRoles().size(); x++) {
				String hell = userDTO.getRoles().get(x);
				switch (hell) {

				case "Administrator":
					castm.setInt(7, 1);
				case "Foreman":
					castm.setInt(8, 1);
				case "Master_Chef":
					castm.setInt(9, 1);
				case "Operatoer":
					castm.setInt(10, 1);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			castm.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public ArrayList<UserDTO> getUserList() {
		ResultSet rs = null;
		Statement st = null;
		ArrayList<UserDTO> userList = new ArrayList<UserDTO>();
		try {
			st = conn.createStatement();
			int Highest = 0;
			st.executeQuery("Select * From v_view_users");
			rs = st.getResultSet();

			while (rs.next() == true) {

				if (rs.getInt(1) >= Highest) {
					Highest = rs.getInt(1);
				}
				UserDTO User = new UserDTO();
				User.setUser_id(rs.getInt(1));

				User.setUser_fornavn(rs.getString(2));

				User.setUser_efternavn(rs.getString(3));

				User.setUser_ini(rs.getString(4));

				User.setUser_cpr(rs.getString(5));

				User.setUser_password(rs.getString(6));

				userList.add(User);

			}

			st.executeQuery("Select * From v_view_roles");
			rs = st.getResultSet();
			

			for (UserDTO user : userList) {
				ArrayList<String> Roles = new ArrayList<String>();
				while (rs.next() == true) {

					if (rs.getInt(1) == user.getUser_id()) {
						Roles.add(rs.getString(2));
					}

				}

				user.setRoles(Roles);

				rs.beforeFirst();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return userList;

	}

	public void deleteUser(int userId) {

		CallableStatement castm = null;
		try {
			castm = conn.prepareCall("call sp_delete_medarbejder(?)");

			castm.setInt(1, userId);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			castm.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void closeUserDAO() {
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
