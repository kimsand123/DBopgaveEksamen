package testCases;

import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import com.mysql.jdbc.Connection;

import DAO.DAOCreateRaavareBatch;
import DTO.RaavareBatchDTO;
import ExceptionHandling.DALException;
import connector.Connector;
import connector.Constant;

public class createRaavareBatchTest {

	
	public void showNewRaavarebatch() {
//Connection conn = createConn();
		DAOCreateRaavareBatch creator = new DAOCreateRaavareBatch();
		CallableStatement st = null;
		Scanner sc = new Scanner(System.in);
		System.out.println("indtast rbId");
		int rbId = sc.nextInt();
		System.out.println("indtast raavareId");
		int raavareId = sc.nextInt();
		 System.out.println("indtast maengde");
		double maengde =  sc.nextDouble();
		System.out.println("indtast leverandoer");
		int leverandoer = sc.nextInt();
		sc.close();
		RaavareBatchDTO dto = new RaavareBatchDTO( rbId,  raavareId,  maengde, leverandoer);
		creator.CreateRaavare(dto);
		Connector connection;
		creator.ShowNewRaavarebatch();
			
		
	}
	// should not be used here
	public Connection createConn()
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
	
}
	
	
	
	
	
	
	
	
	
