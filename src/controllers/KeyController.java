package controllers;

import java.sql.ResultSet;
import java.sql.SQLException;

import ExceptionHandling.DALException;
import connector.Connector;
import connector.Constant;
import enums.TableNames;


public class KeyController {
	ResultSet rs=null;
	Connector connection;


	public KeyController() {
		ResultSet rs=null;
		//Get the resultset
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
			rs = connection.doQuery("Select * FROM keytable");
		} catch (DALException e) {
			System.out.println("Something is wrong with the DB, cannot execute the SQL");
			e.printStackTrace();
		}
		this.rs = rs;
	}

	public String getKey(TableNames tabelname ) {
		String key="";
		int keypart=0;
		String[] keyparts= new String[2];
		String tablepart = "";
		String newKey="";
		int hashposition=0;
		
		try {
			switch (tabelname) {

			case LEVERANDOER: 
				key = rs.getString("leverandoerid");
				hashposition = key.indexOf("#");
				
				keyparts=key.split("#");
				keypart = Integer.parseInt(keyparts[1]);
				tablepart = keyparts[0];
				keypart++;
				newKey = tablepart + "#" + String.valueOf(keypart);
				
				
				
				//call storedprocedure med newKey and tablepart

				break;
			case OPERATOER:
				key = rs.getString("operatoerid");

				break;
			case PRODUKTBATCH:
				key = rs.getString("produktbatchid");

				break;
			case PRODUKTBATCHKOMPONENT:
				key = rs.getString("produktbatchkomponentid");

				break;
			case RAAVARE:
				key = rs.getString("raavareid");

				break;
			case RAAVAREBATCH:
				key = rs.getString("raavarebatchid");

				break;
			case RECEPT:
				key = rs.getString("receptid");

				break;
			case RECEPTKOMPONENT:
				key = rs.getString("receptkomponentid");

				break;
			default:
				break;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return key;
	}

	private void calculateAndWriteNewKey(TableNames tabelname, String key) {
		




	}

}


