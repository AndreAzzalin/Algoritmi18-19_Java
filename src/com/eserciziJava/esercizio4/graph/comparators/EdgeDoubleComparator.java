package com.eserciziJava.esercizio4.graph.comparators;

import com.eserciziJava.esercizio4.graph.Edge;
import java.util.Comparator;

public class EdgeDoubleComparator implements Comparator<Edge> {

	/**
	 * @param edgeA first edge to be compared
	 * @param edgeB second edge to be compared
	 * @return edge with greater tag
	 */
	@Override
	public int compare(Edge edgeA, Edge edgeB) {
		return Double.compare((double) edgeA.getTag(), (double) edgeB.getTag());
	}
}
