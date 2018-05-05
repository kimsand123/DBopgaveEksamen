package main;

import java.util.Scanner;
import testCases.*;

public class Main {

	
	public static void main(String[] args) {
		TestProductNProvider productnproviderlist = new TestProductNProvider();
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Vis users");
		sc.nextLine();
		UserTest.Run();
		sc.nextLine();
		//new UpdateReceptTest().Run(1);
		//sc.nextline();
		//MainTestCaseLavOpskrift.Run();
		//sc.nextLine();
		//productnproviderlist.TestProductNProvider();
		//sc.nextLine();
		//System.out.println("slut");
		System.out.println("List leverandører og produkter med sum pr gruppe. leverandør.vare");
		sc.nextLine();
		new TestProductNProvider().Run();
		System.out.println();
		System.out.println();
		System.out.println("Tryk Enter for at slutte");
		sc.nextLine();
		System.out.println("slutPrut");
	}
	
	
}
