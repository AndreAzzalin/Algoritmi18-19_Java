package com.eserciziJava.esercizio4.graph;

import java.util.ArrayList;
import java.util.List;

public class UndirectedGraph<T> extends Graph<T> {


	@Override
	public void addEdge(Vertex source, Vertex destination, T weight) {
		boolean b1 = false;
		boolean b2 = false;
		this.weight = add(this.weight, weight);

		Edge nDEdge = new Edge(source, destination, weight);
		edgeList.add(nDEdge);

		if (!adjacencyList.containsKey(source)) {
			List<Edge> tempList = new ArrayList<>();
			tempList.add(new Edge<>(destination, weight));
			addVertex(source, tempList);
			b1 = true;
		}

		if (!adjacencyList.containsKey(destination)) {
			List<Edge> tempList = new ArrayList<>();
			tempList.add(new Edge<>(source, weight));
			addVertex(destination, tempList);
			b2 = true;
		}

		if (!b1) {
			adjacencyList.get(source).add(new Edge<>(destination, weight));
		}
		if (!b2) {
			adjacencyList.get(destination).add(new Edge<>(source, weight));
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

			for (int j = 0; j < adjacencyList.get(v2).size(); j++) {
				if (adjacencyList.get(v2).get(j).getVertex1().equals(v1)) {
					adjacencyList.get(v2).remove(j);
				}
			}
		}
	}

	@Override
	public boolean isDirected() {
		return false;
	}

	@Override
	public int getEdgeNumber() {
		int count = 0;
		for ( Vertex x: adjacencyList.keySet()){
			count = count + adjacencyList.get(x).size();
		}

		return count / 2;
	}
}
