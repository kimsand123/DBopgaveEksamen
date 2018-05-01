
package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import connector01917.Connector;
import connector01917.Constant;


public class DAOProductNProvider {

	Connector connection;
	public DAOProductNProvider() {

	}

	public ResultSet getList () {
		ResultSet rs=null;

		try {
			connection = new Connector(Constant.server, Constant.port, Constant.database, Constant.username, Constant.password);		
		} catch (InstantiationException e) {

			e.printStackTrace();
		} catch (IllegalAccessException e) {

			e.printStackTrace();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		try {
			rs = connection.doQuery("Select * FROM v_product_provider_list");
		} catch (DALException e) {
			System.out.println("Something is wrong with the DB, cannot execute the SQL");
			e.printStackTrace();
		}

		connection.closeConnector();
		return rs;
	}

}
