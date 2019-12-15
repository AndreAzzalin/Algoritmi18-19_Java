package com.eserciziJava.esercizio4.graph;


import java.util.ArrayList;
import java.util.List;

public class DirectedGraph<T> extends Graph<T> {

	@Override
	public void addEdge(Vertex source, Vertex destination, T weight) {
		boolean b1 = false;
		this.weight = add(this.weight, weight);

		if (!adjacencyList.containsKey(source)) {
			List<Edge> tempList = new ArrayList<>();
			tempList.add(new Edge<>(destination, weight));
			addVertex(source, tempList);
			b1 = true;
		}

		if (!adjacencyList.containsKey(destination)) {
			List<Edge> tempList = new ArrayList<>();
			addVertex(destination, tempList);
		}

		if (!b1) {
			adjacencyList.get(source).add(new Edge<>(destination, weight));
		}
	}


	@Override
	public void removeEdge(Vertex v1, Vertex v2) {

		if (adjacentVertex(v1, v2)) {
			for (int j = 0; j < adjacencyList.get(v1).size(); j++) {
				if (adjacencyList.get(v1).get(j).getVertex1().equals(v2)) {
					adjacencyList.get(v1).remove(j);
				}
			}
		}
	}

	@Override
	public boolean isDirected() {
		return true;
	}

	@Override
	public int getEdgeNumber() {
		int count = 0;
		for (Vertex x : adjacencyList.keySet()) {
			count = count + adjacencyList.get(x).size();
		}
		return count;
	}
}
