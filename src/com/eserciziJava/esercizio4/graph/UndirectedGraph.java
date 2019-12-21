package com.eserciziJava.esercizio4.graph;

import java.util.ArrayList;
import java.util.List;

public class UndirectedGraph<T> extends Graph<T> {


	@Override
	public void addEdge(Vertex source, Vertex destination, T weight) {
		boolean b1 = false;
		boolean b2 = false;
		this.weight = add((Double) weight);

		Edge nDEdge = new Edge(source, destination, weight);
		edgeList.add(nDEdge);

		if (!adjacencyList.containsKey(source)) {
			List<Edge> tempList = new ArrayList<>();
			tempList.add(new Edge<>(source,destination, weight));
			addVertex(source, tempList);
			b1 = true;
		}

		if (!adjacencyList.containsKey(destination)) {
			List<Edge> tempList = new ArrayList<>();
			tempList.add(new Edge<>(destination,source, weight));
			addVertex(destination, tempList);
			b2 = true;
		}

		if (!b1) {
			adjacencyList.get(source).add(new Edge<>(destination,source, weight));
		}
		if (!b2) {
			adjacencyList.get(destination).add(new Edge<>(source,destination, weight));
		}
	}

	@Override
	public void removeEdge(Vertex vertexA, Vertex vertexB) {
		if (isAdjacent(vertexA, vertexB)) {
			adjacencyList.get(vertexA).removeIf(item -> item.getVertexA().equals(vertexB));
			adjacencyList.get(vertexB).removeIf(item -> item.getVertexA().equals(vertexA));
		}
	}

	@Override
	public boolean isDirected() {
		return false;
	}

	@Override
	public List<Edge> getEdgeList() {
		return edgeList;
	}

	@Override
	public int getEdgeNumber() {
		int count = 0;
		for (Vertex x : adjacencyList.keySet()) {
			count = count + adjacencyList.get(x).size();
		}

		return count / 2;
	}
}
