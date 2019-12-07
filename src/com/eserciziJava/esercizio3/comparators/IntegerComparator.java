package com.eserciziJava.esercizio3.comparators;

import java.util.Comparator;

public class IntegerComparator implements Comparator<Integer> {

	@Override
	//ritorna -1 se diverso, 0 se uguale ,1 se maggiore
	public int compare(Integer l1, Integer l2) {
		return l1.compareTo(l2);
	}
}