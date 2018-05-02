package testCases;

import java.sql.SQLException;
import java.util.List;

import DAO.DALException;
import daoimpl01917.MySQLReceptDAO;
import daoimpl01917.MySQLProduktBatchDAO;
import daoimpl01917.MySQLProduktBatchKompDAO;
import dto01917.ProduktBatchDTO;
import dto01917.ProduktBatchKompDTO;
import dto01917.ReceptKompDTOtest;

/**
 * Udfører en demonstration af operationerne til "lav en produktbatch"
 * 
 * @author Kahnugo
 *	
 */
public class TestCaseLavOpskrift {
	MySQLReceptDAO rcptDAO = new MySQLReceptDAO();
	MySQLProduktBatchDAO pbDAO = new MySQLProduktBatchDAO();
	MySQLProduktBatchKompDAO pbkDAO = new MySQLProduktBatchKompDAO();
	
	public TestCaseLavOpskrift() {
		
	};
	
	
	public List visOpskrift(String opskriftNavn) {
		
		try {
			
			List<ReceptKompDTOtest> list = rcptDAO.visOpskrift("margherita");
			System.out.println("Viser opskrift: (id:"+list.get(0).getReceptId()+" navn:"+opskriftNavn+")");
				System.out.println();
			for(ReceptKompDTOtest komponent:list) {
				System.out.println("raavare: "+komponent.getRaavareNavn()+"(id:"+komponent.getRaavareId()+") maengde: "+komponent.getNomNetto()+" tolerance: "+komponent.getTolerance()+" ");
			}
			return list;
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}


	public boolean opretProduktBatch(int receptId) {
		
		
		ProduktBatchDTO pbDTO = new ProduktBatchDTO(pbDAO.getProduktBatchId(), 0, receptId);
		try {
			pbDAO.createProduktBatch(pbDTO);
			return true;
		} catch (DALException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} 
		
		
	}


	public void opretProduktBatchKomp(ReceptKompDTOtest komponent) {
		System.out.println("Afvej komponent: "+komponent.getRaavareNavn()+" maengde:"+komponent.getNomNetto()+" med tolerance:"+komponent.getTolerance());
		System.out.println();
		/*
		 * Afvejning foretages
		 */
		int oprId = 3;
		int rbId = getRbId(komponent.getRaavareId()); //hackmethod
		double tara = 0.5;
		double netto = komponent.getNomNetto();
		ProduktBatchKompDTO pbKomp = new ProduktBatchKompDTO(pbDAO.getProduktBatchId(), rbId, tara, netto, oprId);
		try {
			pbkDAO.createProduktBatchKomp(pbKomp);
			System.out.println("produktbatchkomp for "+komponent.getRaavareNavn()+" er oprettet");
			System.out.println();
			ProduktBatchKompDTO pbk = pbkDAO.getProduktBatchKomp(pbKomp.getPbId(), rbId);
			System.out.println("Henter oprettet produktbatchkomponent fra database:");
			System.out.println("pbId: "+pbk.getPbId()+" rbId: "+pbk.getRbId()+" tara: "+pbk.getTara()+" netto: "+pbk.getNetto()+" oprId: "+pbk.getOprId());
			System.out.println();
			System.out.println();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private int getRbId(int raavareId) {
		
		switch (raavareId) {
		case 1:
			return 31;
		case 2:
			return 32;
		case 5:
			return 33;
		default:
			return 0;
		}
	}
}
