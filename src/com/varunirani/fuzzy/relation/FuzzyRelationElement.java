package com.varunirani.fuzzy.relation;

public final class FuzzyRelationElement {
	private String rowValue;
	private String columnValue;
	private double degreeOfMembership;


	public FuzzyRelationElement(String rowValue, String columnValue, double degreeOfMembership) {
		this.rowValue = rowValue;
		this.columnValue = columnValue;
		this.degreeOfMembership = degreeOfMembership;
	}

	public String getRowValue() {
		return rowValue;
	}

	public void setRowValue(String rowValue) {
		this.rowValue = rowValue;
	}

	public String getColumnValue() {
		return columnValue;
	}

	public void setColumnValue(String columnValue) {
		this.columnValue = columnValue;
	}

	public double getDegreeOfMembership(String x, String y) {
		if (rowValue.equals(x) && columnValue.equals(y))
			return degreeOfMembership;
		else
			return 0.0;
	}

	public double getDegreeOfMembership() {
		return degreeOfMembership;
	}

	public void setDegreeOfMembership(double degreeOfMembership) {
		this.degreeOfMembership = degreeOfMembership;
	}

	@Override
	public String toString() {
		return "(" + rowValue + ", " + columnValue + ", " + degreeOfMembership + ")";
	}
}
