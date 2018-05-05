package testCases;

import java.io.IOException;
import java.util.List;
import DAO.DALException;
import DAO.MySQLProduktBatchDAO;
import DAO.MySQLProduktBatchKompDAO;
import DAO.MySQLReceptDAO;
import DTO.ProduktBatchDTO;
import DTO.ProduktBatchKompDTO;
import DTO.ReceptKompDTOtest;

import java.util.Scanner;

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
	Scanner scan = new Scanner(System.in);
	
	public TestCaseLavOpskrift() {
		
	};
	
	
	public List visOpskrift(String opskriftNavn) {
		
		try {
			System.out.println("Vis opskrift: margherita");
			scan.next();
			System.out.println();
			List<ReceptKompDTOtest> list = rcptDAO.visOpskrift("margherita");
			System.out.println("Viser opskrift:");
			System.out.format("%15s %5s %10s %15s", "RAAVARE","ID","MAENGDE","TOLERANCE");
					System.out.println();
			for(ReceptKompDTOtest komponent:list) {
				System.out.format("%15s %5d %10s %15s",
						komponent.getRaavareNavn(), komponent.getRaavareId(), Double.toString(komponent.getNomNetto()), Double.toString(komponent.getTolerance()));
				System.out.println();
//				System.out.println("raavare: "+komponent.getRaavareNavn()+"(id:"+komponent.getRaavareId()+") maengde: "+komponent.getNomNetto()+" tolerance: "+komponent.getTolerance()+" ");
			}
			return list;
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		return null;
		}
	}


	public boolean opretProduktBatch(int receptId) {
		
		System.out.println("Opret produktbatch:");
		scan.next();
		ProduktBatchDTO pbDTO = new ProduktBatchDTO(pbDAO.getProduktBatchId(), 0, receptId);
		try {
			pbDAO.createProduktBatch(pbDTO);
			System.out.println("Produktbatch oprettet");
			System.out.println();
			return true;
		} catch (DALException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} 
		
		
	}


	public void opretProduktBatchKomp(ReceptKompDTOtest komponent) {
		System.out.println("Afvej komponent:");
		System.out.format("%15s %15s %15s", 
				"RAAVARE", "MAENGDE", "TOLERANCE");
		System.out.println();
		System.out.format("%15s %15s %15s", 
				komponent.getRaavareNavn(),Double.toString(komponent.getNomNetto()),Double.toString(komponent.getTolerance()));
		
		System.out.println();
		scan.next();
		System.out.println();
		/*
		 * Afvejning foretages
		 */
		int oprId = 3;
		int rbId = getRbId(komponent.getRaavareId()); //hackmethod
//		double tara = 0.5;
		double netto = komponent.getNomNetto();
		ProduktBatchKompDTO pbKomp = new ProduktBatchKompDTO(pbDAO.getProduktBatchId(), rbId, netto, oprId);
		try {
			System.out.println("afvejning foretaget, produktbatchkomponent oprettes:");
			System.out.println();
			pbkDAO.createProduktBatchKomp(pbKomp);
			System.out.println("produktbatchkomp for "+komponent.getRaavareNavn()+" er oprettet");
			scan.next();
			System.out.println();
			ProduktBatchKompDTO pbk = pbkDAO.getProduktBatchKomp(pbKomp.getPbId(), rbId);
			System.out.println("Henter oprettet produktbatchkomponent fra database:");
			
			System.out.format("%15s %15s %15s %15s", "PBATCH ID", "RBATCH ID", "NETTO", "OPR ID");
			System.out.println();
			System.out.format("%15d %15d %15s %15d",
			pbk.getPbId(),pbk.getRbId(),Double.toString(pbk.getNetto()),pbk.getOprId());
			
			System.out.println();
			scan.next();
			
			System.out.println();
		} catch (DALException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	private int getRbId(int raavareId) {
		
		switch (raavareId) {
		case 1:
			return 1;
		case 2:
			return 3;
		case 5:
			return 5;
		default:
			return 0;
		}
	}


	public int getLatestKey() {
		return 42;
	}


	public boolean visProduktBatch(int latestKey) {
		
//		
		try {
			scan.next();
			System.out.println();
			
			ProduktBatchDTO pb = pbDAO.getProduktBatch(latestKey);
			
			System.out.println("Produktbatch vises:");
			System.out.format("%10s %10s %10s", "PBATCH ID","STATUS","RECEPT ID");
					System.out.println();
		
			System.out.format("%10d %10d %10d",
					pb.getPbId(), pb.getStatus(), pb.getReceptId());
			System.out.println();
			scan.next();
			System.out.println();
			return true;
		} catch(DALException e) {
			return false;
		}
		
		
	}
}
