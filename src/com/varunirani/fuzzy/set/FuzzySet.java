package com.varunirani.fuzzy.set;

import com.varunirani.fuzzy.relation.FuzzyRelation;
import com.varunirani.fuzzy.relation.FuzzyRelationElement;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class FuzzySet {
	private final ArrayList<FuzzySetElement> fuzzySetElements = new ArrayList<>();
	private String id;

	public FuzzyRelation cartesianProduct(FuzzySet b, String name) {
		FuzzyRelation fuzzyRelation = new FuzzyRelation(fuzzySetElements.size(), b.getFuzzySetElements().size(), name);
		for (var i : fuzzySetElements) {
			for (var j : b.getFuzzySetElements()) {
				fuzzyRelation.insert(new FuzzyRelationElement(
						i.getValue(), j.getValue(),
						Math.min(i.getDegreeOfMembership(), j.getDegreeOfMembership())
				));
			}
		}
		return fuzzyRelation;
	}

	public ArrayList<FuzzySetElement> getMissingElements(FuzzySet b) {
		ArrayList<FuzzySetElement> missingElements = new ArrayList<>();
		ArrayList<String> aList = getValues();
		ArrayList<String> bList = b.getValues();
		Set<String> s = new HashSet<>();
		s.addAll(aList);
		s.addAll(bList);
		var valueUnion = new ArrayList<>(s);
		for (String value : valueUnion) {
			if (!aList.contains(value)) {
				missingElements.add(new FuzzySetElement(value, 0.0));
			}
		}
		return missingElements;
	}

	private ArrayList<String> getValues() {
		ArrayList<String> values = new ArrayList<>();
		for (FuzzySetElement element : fuzzySetElements)
			values.add(element.getValue());

		return values;
	}

	public FuzzySet(String id) {
		this.id = id;
	}

	public FuzzySet() {
	}


	public FuzzySet union(FuzzySet b, String newName) {
		FuzzySet fuzzyUnion = new FuzzySet(newName);
		for (int i = 0; i < b.getFuzzySetElements().size(); i++) {
			FuzzySetElement x = this.get(i);
			FuzzySetElement y = b.get(b.searchElement(x));
			if (x.getDegreeOfMembership() > y.getDegreeOfMembership())
				fuzzyUnion.insert(x);
			else
				fuzzyUnion.insert(y);
		}
		return fuzzyUnion;
	}

	public FuzzySet intersect(FuzzySet b, String newName) {
		FuzzySet fuzzyIntersection = new FuzzySet(newName);
		for (int i = 0; i < b.getFuzzySetElements().size(); i++) {
			FuzzySetElement x = this.get(i);
			FuzzySetElement y = b.get(b.searchElement(x));
			if (x.getDegreeOfMembership() < y.getDegreeOfMembership())
				fuzzyIntersection.insert(x);
			else
				fuzzyIntersection.insert(y);
		}
		return fuzzyIntersection;
	}

	public FuzzySet complement(String newName) {
		FuzzySet c = new FuzzySet(newName);
		for (int i = 0; i < fuzzySetElements.size(); i++) {
			c.insert(new FuzzySetElement(get(i).getValue(),
					Double.parseDouble(new DecimalFormat("0.0")
							.format(1.0 - get(i).getDegreeOfMembership()))));
		}
		return c;
	}

	private int searchElement(FuzzySetElement key) {
		for (int i = 0, fuzzySetSize = fuzzySetElements.size(); i < fuzzySetSize; i++) {
			FuzzySetElement e = fuzzySetElements.get(i);
			if (e.getValue().equals(key.getValue()))
				return i;
		}

		return -1;
	}

	public FuzzySetElement get(int i) {
		return fuzzySetElements.get(i);
	}

	public boolean insert(FuzzySetElement e) {
		if (!fuzzySetElements.contains(e))
			return fuzzySetElements.add(e);
		else
			return false;
	}

	public FuzzySet addAll(ArrayList<FuzzySetElement> list) {
		var f = new FuzzySet();
		for (FuzzySetElement e : getFuzzySetElements()) {
			f.getFuzzySetElements().add(e);
		}
		for (FuzzySetElement e : list) {
			f.getFuzzySetElements().add(e);
		}
		return f;
	}

	public FuzzySetElement remove(int index) {
		if (fuzzySetElements.contains(get(index)))
			return fuzzySetElements.remove(index);
		else
			return null;
	}

	public boolean contains(FuzzySetElement e) {
		for (FuzzySetElement element : fuzzySetElements)
			if (element.getValue().equals(e.getValue()))
				return true;

		return false;
	}

	public ArrayList<FuzzySetElement> getFuzzySetElements() {
		return fuzzySetElements;
	}

	@Override
	public String toString() {
		return id + " = " + "{" + fuzzySetElements.toString().substring(1, fuzzySetElements.toString().length() - 1) + "}";
	}


	public String getId() {
		return id;
	}
}
