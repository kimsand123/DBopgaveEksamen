package main;

import java.util.Scanner;
import testCases.*;

public class Main {

	
	public static void main(String[] args) {
		TestProductNProvider productnproviderlist = new TestProductNProvider();
		
		Scanner sc = new Scanner(System.in);
		
		
		System.out.println("Vis users TRYK ENTER");
		sc.nextLine();
		UserTest.Run();
		System.out.println("Vis users Slut TRYK ENTER");
		sc.nextLine();
		
		System.out.println("Update Recept TRYK ENTER");
		sc.nextLine();
		new UpdateReceptTest().Run(1);
		System.out.println("Update Recept Slut TRYK ENTER");
		sc.nextLine();
		
		System.out.println();
		System.out.println();
		System.out.println("Lav en opskrift TRYK ENTER");
		sc.nextLine();
		MainTestCaseLavOpskrift.Run();
		System.out.println("Lav en opskrift slut TRYK ENTER");
		sc.nextLine();

		System.out.println("List leverandører og produkter med sum pr gruppe. leverandør.vare TRYK ENTER");
		sc.nextLine();
		new TestProductNProvider().Run();
		System.out.println("List slut");
		System.out.println();
		System.out.println("Tryk Enter for at slutte fremvisning");
		sc.nextLine();
//		
		System.out.println("slutPrut");
	}
	
	
}
