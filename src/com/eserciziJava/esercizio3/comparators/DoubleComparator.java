package com.eserciziJava.esercizio3.comparators;

import java.util.Comparator;

public class DoubleComparator implements Comparator<Double> {
	@Override

	public int compare(Double l1, Double l2) {
		return l1.compareTo(l2);
	}
}
