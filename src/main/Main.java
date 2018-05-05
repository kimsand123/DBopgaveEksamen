package main;

import java.util.Scanner;

import testCases.*;

public class Main {

	
	public static void main(String[] args) {
		TestProductNProvider productnproviderlist = new TestProductNProvider();
		
		Scanner sc = new Scanner(System.in);
		
		
		//UserTest.Run();
		//sc.nextLine();
		System.out.println("Opdatering af en recept");
		sc.nextLine();
		new UpdateReceptTest().Run(1);
		sc.nextLine();
		//MainTestCaseLavOpskrift.Run();
		//sc.nextLine();
		System.out.println("List leverandører og produkter hvor mængden er >= 100");
		sc.nextLine();
		new TestProductNProvider().Run();
		sc.nextLine();
		System.out.println("slutPrut");
	}
	
	
}
