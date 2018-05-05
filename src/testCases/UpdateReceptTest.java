package testCases;

import DTO.RaavareDTO;
import DTO.ReceptDTO;
import DTO.ReceptKompDTO;
import controllers.ReceptController;
import tools.KeyValue;

public class UpdateReceptTest {

	public void Run(int receptId) {
		ReceptController recCtrl = new ReceptController();
		ReceptDTO recept = recCtrl.getRecept(receptId);
		int recept_id = recept.getReceptId();

		System.out.println("Før opdatering af recept");
		printRecepter(recCtrl);

		System.out.println();

		recept.clearComponents();
		recept.addComponent(createReceptKomp(recept_id, ReceptController.DEJ, 10, 0.8));
		//recept.addComponent(createReceptKomp(recept_id, ReceptController.OST, 10, 0.8));		
		recept.addComponent(createReceptKomp(1, ReceptController.CHAMPIGNON, 10, 0.8));
		//recept.addComponent(createReceptKomp(1, ReceptController.SKINKE, 10, 0.8));
		recept.addComponent(createReceptKomp(1, ReceptController.TOMAT, 10, 0.8));
		recCtrl.updateRecept(recept);

		System.out.println();
		System.out.println("Efter opdatering af recept");
		printRecepter(recCtrl);

	}

	private static ReceptKompDTO createReceptKomp(int recept_id, KeyValue<Integer, String> raavare, double nomNetto,
			double tolerance) {
		return new ReceptKompDTO(recept_id, raavare.getKey(), nomNetto, tolerance);
	}

	private static void printRaavare(ReceptController recCtrl) {

		for (RaavareDTO item : recCtrl.getRaavareList()) {
			System.out.println(item);
		}

	}

	private static void printRecepter(ReceptController recCtrl) {

		for (ReceptDTO recept : recCtrl.getRecepter()) {
			
			String s = String.format("Recept nr.: %s - %s", recept.getReceptId(), recept.getReceptNavn());
			
			s+= " [";
			
			System.out.print(s);

			for (ReceptKompDTO item : recept.getComponents()) {
				System.out.print(item.getNavn() + ", ");
			}

			System.out.print("]");
			// System.out.println();
			System.out.println();
		}
	}
}
