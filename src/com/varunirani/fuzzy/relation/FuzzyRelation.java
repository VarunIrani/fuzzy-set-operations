package com.varunirani.fuzzy.relation;

import java.util.ArrayList;

public class FuzzyRelation {
	private final ArrayList<FuzzyRelationElement> fuzzyRelationElements = new ArrayList<>();
	private String id;
	private int numRows, numColumns;

	public FuzzyRelation(int numRows, int numColumns, String id) {
		this.id = id;
		this.numRows = numRows;
		this.numColumns = numColumns;
	}

	public int getNumRows() {
		return numRows;
	}

	public int getNumColumns() {
		return numColumns;
	}

	public FuzzyRelation() {
	}

	public boolean insert(FuzzyRelationElement e) {
		if (!fuzzyRelationElements.contains(e))
			return fuzzyRelationElements.add(e);
		else
			return false;
	}

	public String getId() {
		return id;
	}

	public FuzzyRelation maxMinComposition(FuzzyRelation b, String name) {
		FuzzyRelation composition = new FuzzyRelation(numRows, b.getNumColumns(), name);
		for (int i = 0; i < numRows; i++) {
			ArrayList<Double> minimums = new ArrayList<>();
			for (int j = 0; j < b.getNumColumns(); j++) {
				for (int k = 0; k < b.getNumRows(); k++) {
					FuzzyRelationElement x = fuzzyRelationElements.get(k + i * numColumns);
					FuzzyRelationElement y = b.getFuzzyRelationElements().get(j + k * b.getNumColumns());
					minimums.add(Math.min(x.getDegreeOfMembership(), y.getDegreeOfMembership()));
				}
				composition.insert(new FuzzyRelationElement(
						fuzzyRelationElements.get(i * numColumns).getRowValue(),
						b.getFuzzyRelationElements().get(j + i * b.getNumColumns()).getColumnValue(),
						FuzzyRelation.getMax(minimums)
				));
			}
		}
		return composition;
	}

	private static double getMax(ArrayList<Double> minimums) {
		double max = minimums.get(0);
		for (double x : minimums)
			if (x > max)
				max = x;

		return max;
	}

	public ArrayList<FuzzyRelationElement> getFuzzyRelationElements() {
		return fuzzyRelationElements;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("\t");
		for (int i = 0; i < numColumns; i++) {
			var columnValue = fuzzyRelationElements.get(i).getColumnValue();
			int numTabs = columnValue.length();
			builder.append(columnValue).append("\t".repeat(numTabs + 1));
		}
		builder.append("\n");
		for (int i = 0; i < numRows; i++) {
			String rowValue = fuzzyRelationElements.get(i * numColumns).getRowValue();
			builder.append(rowValue).append("\t");
			for (int j = 0; j < numColumns; j++) {
				FuzzyRelationElement e = fuzzyRelationElements.get(j + i * numColumns);
				int numTabs = e.getColumnValue().length();
				builder.append(e.getDegreeOfMembership()).append("\t".repeat(numTabs));
			}
			builder.append("\n");
		}
		return builder.toString();
	}
}
