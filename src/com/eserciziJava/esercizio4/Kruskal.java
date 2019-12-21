package com.eserciziJava.esercizio4;


import com.eserciziJava.esercizio4.graph.Edge;
import com.eserciziJava.esercizio4.graph.Graph;
import com.eserciziJava.esercizio4.graph.UndirectedGraph;
import com.eserciziJava.esercizio4.graph.Vertex;
import com.eserciziJava.esercizio4.graph.comparators.EdgeDoubleComparator;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public class Kruskal {

	// Kruskal algorithm
	public static Graph mstKruskal(@NotNull Graph graph) {
		Graph tree = new UndirectedGraph<>();


		List<Vertex> vertexList = graph.getVerticiesList();
		UnionFindSet set = new UnionFindSet();
		vertexList.forEach(set::makeSet);
		List<Edge> edgeList = graph.getEdgesList();
		// Sorting edge list
		edgeList.sort(new EdgeDoubleComparator());

		Vertex vertexA;
		Vertex vertexB;

		for (Edge edge : edgeList) {
			vertexA = edge.getVertexA();
			vertexB = edge.getVertexB();
			if (set.find(vertexA) != set.find(vertexB)) {
				tree.addEdge(vertexA, vertexB, edge.getTag());
				set.union(vertexA, vertexB);
			}
		}
		return tree;
	}

}
