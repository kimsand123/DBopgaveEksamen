package main;

import controllers.ReceptController;
import dto01917.ReceptDTO;
import dto01917.ReceptKompDTO;

public class Main {
	public static void main(String[] args) {		
		
		ReceptController recCtrl = new ReceptController();
		
		
		
		System.out.println("Før");
		printRecepter(recCtrl);
		
		ReceptDTO recept = recCtrl.getRecepter().get(0);
		recept.setReceptNavn("Boller i karry");		
		recCtrl.updateRecept(recept);
		
		System.out.println();
		System.out.println("Efter");
		printRecepter(recCtrl);		
	}

	private static void printRecepter(ReceptController recCtrl) {

		for (ReceptDTO recept : recCtrl.getRecepter()) {
			
			System.out.print("Recept: " + recept.getReceptNavn() + " [");
			
			for (ReceptKompDTO item : recept.getComponents()) {
				System.out.print(item.getNavn() + ", ");
			}
			
			System.out.print("]");
			//System.out.println();
			System.out.println();
		}
	}
}
