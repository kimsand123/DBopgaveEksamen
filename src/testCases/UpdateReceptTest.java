package testCases;

import java.util.ArrayList;
import java.util.Arrays;

import DTO.RaavareDTO;
import DTO.ReceptDTO;
import DTO.ReceptKompDTO;
import ExceptionHandling.DALException;
import controllers.ReceptController;
import tools.KeyValue;
import tools.PrettyPrint;

public class UpdateReceptTest {

	public void Run(int receptId) {
		ReceptController recCtrl = new ReceptController();
		ReceptDTO recept = recCtrl.getRecept(receptId);
		int recept_id = recept.getReceptId();

		printRecepter(recCtrl, "Før opdatering af recept");

		System.out.println();

		recept.clearComponents();
		// recept.addComponent(createReceptKomp(recept_id, ReceptController.DEJ, 10,
		// 0.8));
		// recept.addComponent(createReceptKomp(recept_id, ReceptController.OST, 10,
		// 0.8));
		recept.addComponent(createReceptKomp(recept_id, ReceptController.CHAMPIGNON, 10, 0.8));
		recept.addComponent(createReceptKomp(recept_id, ReceptController.SKINKE, 10, 0.8));
		recept.addComponent(createReceptKomp(recept_id, ReceptController.TOMAT, 10, 0.8));
		recCtrl.updateRecept(recept);

		printRecepter(recCtrl, "Efter opdatering af recept");
	}

	private static ReceptKompDTO createReceptKomp(int recept_id, KeyValue<Integer, String> raavare, double nomNetto,
			double tolerance) {
		return new ReceptKompDTO(recept_id, raavare.getKey(), nomNetto, tolerance);
	}

	private static void printRaavare(ReceptController recCtrl) throws DALException {

		for (RaavareDTO item : recCtrl.getRaavareList()) {
			System.out.println(item);
		}

	}

	private static void printRecepter(ReceptController recCtrl, String heading) {

		System.out.println("==> " + heading);

		ArrayList<String> headers = new ArrayList<String>();
		headers.add("Opskrift");
		headers.add("Ingredienser");

		ArrayList<ArrayList<String>> content = new ArrayList<ArrayList<String>>();

		for (ReceptDTO recept : recCtrl.getRecepter()) {

			String comps = "";

			for (ReceptKompDTO item : recept.getComponents()) {

				if (comps.length() == 0)
					comps += item.getNavn();
				else
					comps +=  ", " + item.getNavn();
			}

			ArrayList<String> row = new ArrayList<String>();
			row.add(recept.getReceptNavn());
			row.add(comps);

			content.add(row);

		}

		PrettyPrint pp = new PrettyPrint(headers, content);
		pp.printTable();
	}
}
