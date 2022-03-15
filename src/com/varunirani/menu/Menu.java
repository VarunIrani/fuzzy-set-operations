package com.varunirani.menu;

import com.varunirani.FuzzySets;
import com.varunirani.fuzzy.relation.FuzzyRelation;
import com.varunirani.fuzzy.set.FuzzySet;
import com.varunirani.fuzzy.set.FuzzySetElement;

import java.io.IOException;

public class Menu {
	public static void insert() {
		System.out.println("\nINSERT INTO FUZZY SET\n");
		System.out.print("Select Fuzzy Set: ");
		for (String fuzzySet : FuzzySets.fuzzySets.keySet()) {
			System.out.print(fuzzySet + " ");
		}
		System.out.print("\nEnter your choice: ");
		try {
			String selected = FuzzySets.reader.readLine();
			var fuzzySet = FuzzySets.fuzzySets.get(selected);
			if (fuzzySet != null) {
				System.out.print("Enter value to insert: ");
				String data = FuzzySets.reader.readLine();
				FuzzySetElement element = new FuzzySetElement();
				element.setValue(data);
				System.out.print("Enter degree of membership: ");
				double degreeOfMembership = Double.parseDouble(FuzzySets.reader.readLine());
				element.setDegreeOfMembership(degreeOfMembership);
				if (!fuzzySet.insert(element)) {
					System.out.println("Element " + element.getValue() + " already exists.");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void remove() {
		System.out.println("\nREMOVE FROM FUZZY SET\n");
		System.out.print("Select Fuzzy Set: ");
		for (String fuzzySet : FuzzySets.fuzzySets.keySet()) {
			System.out.print(fuzzySet + " ");
		}
		System.out.print("\nEnter your choice: ");
		try {
			String selected = FuzzySets.reader.readLine();
			var fuzzySet = FuzzySets.fuzzySets.get(selected);
			if (fuzzySet != null) {
				System.out.println(selected + " = " + fuzzySet);
				System.out.print("Enter index of element to remove (0 based): ");
				int index = Integer.parseInt(FuzzySets.reader.readLine());
				FuzzySetElement element;
				if ((element = fuzzySet.remove(index)) == null) {
					System.out.println("Index out of bounds");
				} else {
					System.out.println("Element " + element.getValue() + " removed");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void show() {
		System.out.println("\nSHOW FUZZY SET\n");
		System.out.print("Select Fuzzy Set: ");
		for (String fuzzySet : FuzzySets.fuzzySets.keySet()) {
			System.out.print(fuzzySet + " ");
		}
		System.out.print("\nEnter your choice: ");
		try {
			String selected = FuzzySets.reader.readLine();
			var fuzzySet = FuzzySets.fuzzySets.get(selected);
			if (fuzzySet != null) {
				System.out.println(fuzzySet);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void union() {
		System.out.println("\nUNION OF TWO FUZZY SETS\n");
		System.out.print("Select First Fuzzy Set: ");
		for (String fuzzySet : FuzzySets.fuzzySets.keySet()) {
			System.out.print(fuzzySet + " ");
		}
		System.out.print("\nEnter your choice: ");
		try {
			String firstSet = FuzzySets.reader.readLine();
			var fuzzySetA = FuzzySets.fuzzySets.get(firstSet);
			if (fuzzySetA != null) {
				System.out.print("Select Second Fuzzy Set: ");
				for (String fuzzySet : FuzzySets.fuzzySets.keySet()) {
					System.out.print(fuzzySet + " ");
				}
				System.out.print("\nEnter your choice: ");
				String secondSet = FuzzySets.reader.readLine();
				var fuzzySetB = FuzzySets.fuzzySets.get(secondSet);
				if (fuzzySetB != null) {
					System.out.print("Enter new name for set: ");
					String newName = FuzzySets.reader.readLine();
					var missingInA = fuzzySetA.getMissingElements(fuzzySetB);
					var missingInB = fuzzySetB.getMissingElements(fuzzySetA);
					var completeA = fuzzySetA.addAll(missingInA);
					var completeB = fuzzySetB.addAll(missingInB);
					System.out.println(completeA);
					System.out.println(completeB);
					var u = completeA.union(completeB, newName);
					System.out.println(u);
					FuzzySets.fuzzySets.put(newName, u);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void intersection() {
		System.out.println("\nINTERSECTION OF TWO FUZZY SETS\n");
		System.out.print("Select First Fuzzy Set: ");
		for (String fuzzySet : FuzzySets.fuzzySets.keySet()) {
			System.out.print(fuzzySet + " ");
		}
		System.out.print("\nEnter your choice: ");
		try {
			String firstSet = FuzzySets.reader.readLine();
			var fuzzySetA = FuzzySets.fuzzySets.get(firstSet);
			if (fuzzySetA != null) {
				System.out.print("Select Second Fuzzy Set: ");
				for (String fuzzySet : FuzzySets.fuzzySets.keySet()) {
					System.out.print(fuzzySet + " ");
				}
				System.out.print("\nEnter your choice: ");
				String secondSet = FuzzySets.reader.readLine();
				var fuzzySetB = FuzzySets.fuzzySets.get(secondSet);
				if (fuzzySetB != null) {
					System.out.print("Enter new name for set: ");
					String newName = FuzzySets.reader.readLine();
					var missingInA = fuzzySetA.getMissingElements(fuzzySetB);
					var missingInB = fuzzySetB.getMissingElements(fuzzySetA);
					var completeA = fuzzySetA.addAll(missingInA);
					var completeB = fuzzySetB.addAll(missingInB);
					var i = completeA.intersect(completeB, newName);
					System.out.println(i);
					FuzzySets.fuzzySets.put(newName, i);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void compliment() {
		System.out.println("\nCOMPLIMENT OF FUZZY SET\n");
		System.out.print("Select Fuzzy Set: ");
		for (String fuzzySet : FuzzySets.fuzzySets.keySet()) {
			System.out.print(fuzzySet + " ");
		}
		System.out.print("\nEnter your choice: ");
		try {
			String selection = FuzzySets.reader.readLine();
			var fuzzySetA = FuzzySets.fuzzySets.get(selection);
			if (fuzzySetA != null) {
				System.out.print("Enter new name for set: ");
				String newName = FuzzySets.reader.readLine();
				var c = fuzzySetA.complement(newName);
				System.out.println(c);
				FuzzySets.fuzzySets.put(newName, c);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void difference() {
		System.out.println("\nDIFFERENCE OF TWO FUZZY SETS\n");
		System.out.print("Select First Fuzzy Set: ");
		for (String fuzzySet : FuzzySets.fuzzySets.keySet()) {
			System.out.print(fuzzySet + " ");
		}
		System.out.print("\nEnter your choice: ");
		try {
			String firstSet = FuzzySets.reader.readLine();
			var fuzzySetA = FuzzySets.fuzzySets.get(firstSet);
			if (fuzzySetA != null) {
				System.out.print("Select Second Fuzzy Set: ");
				for (String fuzzySet : FuzzySets.fuzzySets.keySet()) {
					System.out.print(fuzzySet + " ");
				}
				System.out.print("\nEnter your choice: ");
				String secondSet = FuzzySets.reader.readLine();
				var fuzzySetB = FuzzySets.fuzzySets.get(secondSet);
				if (fuzzySetB != null) {
					System.out.print("Enter new name for set: ");
					String newName = FuzzySets.reader.readLine();
					var missingInA = fuzzySetA.getMissingElements(fuzzySetB);
					var missingInB = fuzzySetB.getMissingElements(fuzzySetA);
					var completeA = fuzzySetA.addAll(missingInA);
					var completeB = fuzzySetB.addAll(missingInB);
					var d = completeA.intersect(completeB.complement(completeB.getId() + "'"), newName);
					System.out.println(d);
					FuzzySets.fuzzySets.put(newName, d);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void cartesianProduct() {
		System.out.print("Select First Fuzzy Set: ");
		for (String fuzzySet : FuzzySets.fuzzySets.keySet()) {
			System.out.print(fuzzySet + " ");
		}
		System.out.print("\nEnter your choice: ");
		try {
			String firstSet = FuzzySets.reader.readLine();
			var fuzzySetA = FuzzySets.fuzzySets.get(firstSet);
			if (fuzzySetA != null) {
				System.out.print("Select Second Fuzzy Set: ");
				for (String fuzzySet : FuzzySets.fuzzySets.keySet()) {
					System.out.print(fuzzySet + " ");
				}
				System.out.print("\nEnter your choice: ");
				String secondSet = FuzzySets.reader.readLine();
				var fuzzySetB = FuzzySets.fuzzySets.get(secondSet);
				if (fuzzySetB != null) {
					System.out.print("Enter new name for relation: ");
					String newName = FuzzySets.reader.readLine();
					FuzzyRelation cartesian = fuzzySetA.cartesianProduct(fuzzySetB, newName);
					System.out.println(cartesian);
					FuzzySets.fuzzyRelations.put(newName, cartesian);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void maxMinComposition() {
		if (FuzzySets.fuzzyRelations.size() > 1) {
			System.out.print("Select First Fuzzy Relation: ");
			for (String fuzzyRelation : FuzzySets.fuzzyRelations.keySet()) {
				System.out.print(fuzzyRelation + " ");
			}
			System.out.print("\nEnter your choice: ");
			try {
				String firstRelation = FuzzySets.reader.readLine();
				var fuzzyRelationA = FuzzySets.fuzzyRelations.get(firstRelation);
				if (fuzzyRelationA != null) {
					System.out.print("Select Second Fuzzy Relation: ");
					for (String fuzzyRelation : FuzzySets.fuzzyRelations.keySet()) {
						System.out.print(fuzzyRelation + " ");
					}
					System.out.print("\nEnter your choice: ");
					String secondRelation = FuzzySets.reader.readLine();
					var fuzzyRelationB = FuzzySets.fuzzyRelations.get(secondRelation);
					if (fuzzyRelationB != null) {
						System.out.print("Enter new name for relation: ");
						String newName = FuzzySets.reader.readLine();
						FuzzyRelation maxMin = fuzzyRelationA.maxMinComposition(fuzzyRelationB, newName);
						System.out.println(maxMin);
						FuzzySets.fuzzyRelations.put(newName, maxMin);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("There should be more than 2 Fuzzy Relations");
		}
	}

	public static void showSets() {
		for (FuzzySet s : FuzzySets.fuzzySets.values())
			System.out.println(s);
	}

	public static void showRelations() {
		for (FuzzyRelation r : FuzzySets.fuzzyRelations.values())
			System.out.println(r);
	}
}
