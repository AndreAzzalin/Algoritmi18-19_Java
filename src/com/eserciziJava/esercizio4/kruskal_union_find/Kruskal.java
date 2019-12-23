package com.eserciziJava.esercizio4.kruskal_union_find;


import com.eserciziJava.esercizio4.graph.Edge;
import com.eserciziJava.esercizio4.graph.Graph;
import com.eserciziJava.esercizio4.graph.UndirectedGraph;
import com.eserciziJava.esercizio4.graph.Vertex;
import com.eserciziJava.esercizio4.graph.comparators.EdgeDoubleComparator;
import org.jetbrains.annotations.NotNull;

import java.util.List;


public class Kruskal {

	/**
	 * based on 3 steps:
	 * 1) sort the edges in ascending order of weights
	 * 2) pick the edge with the minimum weight. Check if including this edge in MST
	 * 		will form a cycle is TRUE then ignore it, if FALSE then add it to MST
	 * 3) repeat the step 2 foreach graph's edges
	 *
	 * @param graph graph to be applied Kruskal algorithm
	 * @return return the minimum spanning three with Kruskal algorithm supported by UnionFind-Set
	 */
	public static Graph mstKruskal(@NotNull Graph graph) {
		Graph tree = new UndirectedGraph<>();


		List<Vertex> vertexList = graph.getVerticiesList();
		UnionFindSet set = new UnionFindSet();
		vertexList.forEach(set::makeSet);
		List<Edge> edgeList = graph.getEdgesList();

		//step 1
		edgeList.sort(new EdgeDoubleComparator());

		Vertex vertexA;
		Vertex vertexB;

		//step 3
		for (Edge edge : edgeList) {
			vertexA = edge.getVertexA();
			vertexB = edge.getVertexB();

			//step 2
			//if edge is included the cycle will be formed so ignore this edge
			if (set.find(vertexA) != set.find(vertexB)) {
				tree.addEdge(vertexA, vertexB, edge.getTag());
				set.union(vertexA, vertexB);
			}
		}
		return tree;
	}

}
