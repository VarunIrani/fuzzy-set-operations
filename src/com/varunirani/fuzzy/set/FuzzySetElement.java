package com.varunirani.fuzzy.set;

public final class FuzzySetElement{
	private String value;
	private double degreeOfMembership;

	public FuzzySetElement(String value, double degreeOfMembership) {
		this.value = value;
		this.degreeOfMembership = degreeOfMembership;
	}

	public FuzzySetElement() {}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setDegreeOfMembership(double degreeOfMembership) {
		this.degreeOfMembership = degreeOfMembership;
	}

	public double getDegreeOfMembership() {
		return degreeOfMembership;
	}

	@Override
	public String toString() {
		return "(" + value + ", " + degreeOfMembership + ")";
	}
}
