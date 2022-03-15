package com.varunirani;

import com.varunirani.fuzzy.relation.FuzzyRelation;
import com.varunirani.fuzzy.set.FuzzySet;
import com.varunirani.menu.Menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class FuzzySets {
	public static final HashMap<String, FuzzySet> fuzzySets = new HashMap<>();
	public static final HashMap<String, FuzzyRelation> fuzzyRelations = new HashMap<>();
	public static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

	private static void menu() throws IOException {
		boolean exit = false;
		HashMap<Integer, Runnable> menuOptions = new HashMap<>();
		menuOptions.put(0, Menu::insert);
		menuOptions.put(1, Menu::remove);
		menuOptions.put(2, Menu::show);
		menuOptions.put(3, Menu::union);
		menuOptions.put(4, Menu::intersection);
		menuOptions.put(5, Menu::compliment);
		menuOptions.put(6, Menu::difference);
		menuOptions.put(7, Menu::cartesianProduct);
		menuOptions.put(8, Menu::maxMinComposition);
		menuOptions.put(9, Menu::showSets);
		menuOptions.put(10, Menu::showRelations);
		ArrayList<String> options = new ArrayList<>(Arrays.asList(
				"Insert into Fuzzy Set", "Remove from Fuzzy Set", "Show Fuzzy Set", "Union of 2 Fuzzy Sets",
				"Intersection of 2 Fuzzy Sets", "Compliment of a Fuzzy Set", "Difference of 2 Fuzzy Sets",
				"Cartesian Product of 2 Fuzzy Sets", "Max-Min Composition of 2 Fuzzy Relations",
				"Show All Sets", "Show All Relations"
		));
		while (!exit) {
			for (int i = 0, optionsSize = options.size(); i < optionsSize; i++) {
				String option = options.get(i);
				System.out.println("(" + (i + 1) + ")" + " " + option);
			}
			System.out.print("Enter your choice: ");
			int choice = Integer.parseInt(reader.readLine()) - 1;
			if (choice < 0 || choice > options.size() - 1) {
				System.out.println("Invalid choice");
			} else {
				menuOptions.get(choice).run();
			}
			System.out.print("Would you like to EXIT (y, n)?: ");
			String exitString = reader.readLine();
			if (exitString.equalsIgnoreCase("y"))
				exit = true;
		}
	}

	public static void main(String[] args) throws IOException {
		System.out.println("Welcome to the Fuzzy Sets Program");
		System.out.print("Enter the number of fuzzy sets: ");
		int numSets = Integer.parseInt(reader.readLine());
		for (int i = 0; i < numSets; i++) {
			System.out.println("Fuzzy Set " + (i + 1));
			System.out.print("Enter name: ");
			String name = reader.readLine();
			FuzzySet fuzzy = new FuzzySet(name);
			fuzzySets.put(name, fuzzy);
		}
		menu();
	}
}
