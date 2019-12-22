package com.eserciziJava.esercizio4.graph.comparators;

import com.eserciziJava.esercizio4.graph.Edge;
import java.util.Comparator;

public class EdgeDoubleComparator implements Comparator<Edge> {

	/**
	 * @param edgeA
	 * @param edgeB
	 * @return
	 */
	@Override
	public int compare(Edge edgeA, Edge edgeB) {
		return Double.compare((double) edgeA.getTag(), (double) edgeB.getTag());
	}
}
