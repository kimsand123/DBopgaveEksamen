package testCases;
import java.util.ArrayList;
import java.util.List;

import daoimpl01917.MySQLReceptDAO;
import dto01917.ProduktBatchDTO;
import dto01917.ReceptKompDTOtest;
import testCases.TestCaseLavOpskrift;
public class MainTestCaseLavOpskrift {

	public static void main(String[] args) {
		/*
		 * Der vaelges opskrift
		 * Aktivt input: {recept_navn}
		 */
		String receptNavn = "margherita";
		
		/*
		 * Der oprettes en produktbatch
		 * Aktivt input: {}
		 */
		TestCaseLavOpskrift testCase1 = new TestCaseLavOpskrift();
		List<ReceptKompDTOtest> opskrift = testCase1.visOpskrift("margherita");
		System.out.println();
		
		/*
		 * Hvis det lykkes at oprette en produktbatch skal der afvejes og oprettes de produktbatchkomponenter der skal bruges til produktionen
		 * Aktivt input {data ved afvejning (rb_id, tara, netto)}
		 */
		if(testCase1.opretProduktBatch(opskrift.get(0).getReceptId())) {
			for(ReceptKompDTOtest komponent: opskrift) {
				testCase1.opretProduktBatchKomp(komponent);
			}
		}
		else {
			System.out.println("Terminating lifesupport");
			System.exit(0);
		}
		
	}
	
}