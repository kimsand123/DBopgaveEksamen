package testCases;

import java.sql.ResultSet;
import java.sql.SQLException;

import DAO.DAOProductNProvider;
import DTO.ProductNProviderDTO;

public class TestProductNProvider {

	public TestProductNProvider() {
		DAOProductNProvider dao = new DAOProductNProvider();
		ProductNProviderDTO dto;
		String varenavn;
		String leverandoer;
		double maengde;
		String line;

		dao.getList();
		dto = dao.getDTO();
		ResultSet rs = dto.getResultSet();
		
		System.out.println("Vare                 Leverandør                         Mængde");
		try {
			while(rs.next()) {
				varenavn = rs.getString("raavare_navn");
				leverandoer = rs.getString("navn");
				maengde = rs.getDouble("maengde");
				//System.out.println(varenavn + " " + leverandoer + " " + maengde);
				line = buildString(varenavn, leverandoer, maengde);
				System.out.println(line);
			}
		} catch (SQLException e) {
			System.out.println("Something went wrong with the resultset");
			e.printStackTrace();
		}
	}
	private String buildString(String varenavn, String leverandoer, double maengde) {
		String line = "";
		String maengdelaengde;
		
		line = line + varenavn;
		line = fillSpaces(line, 20-line.length());
		line = line + leverandoer;
		line = fillSpaces(line, 50-line.length());
		maengdelaengde = Double.toString(maengde);
		
		line = fillSpaces(line, 10-maengdelaengde.length());
		line = line + maengde;
		
		
		return line;
	}

	private String fillSpaces(String line, int spaces) {
		
		for (int counter = 0;counter <=spaces;counter++) {
			line = line + " ";
		}
		
		return line;
	}
	
}
