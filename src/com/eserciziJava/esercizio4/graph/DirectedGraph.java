package com.eserciziJava.esercizio4.graph;


import java.util.ArrayList;
import java.util.List;

public class DirectedGraph<T> extends Graph<T> {

	@Override
	public List<Edge> getEdgeList() {
		List<Edge> edgeList = new ArrayList<>();

		for (Vertex x : adjacencyList.keySet()) {
			edgeList.addAll(adjacencyList.get(x));
		}

		return edgeList;
	}

	@Override
	public void addEdge(Vertex source, Vertex destination, T weight) {
		boolean b1 = false;
		this.weight = add((Double) weight);


		if (!adjacencyList.containsKey(source)) {
			List<Edge> tempList = new ArrayList<>();
			tempList.add(new Edge<>(source,destination, weight));
			addVertex(source, tempList);
			b1 = true;
		}

		if (!adjacencyList.containsKey(destination)) {
			List<Edge> tempList = new ArrayList<>();
			addVertex(destination, tempList);
		}

		if (!b1) {
			adjacencyList.get(source).add(new Edge<>(source,destination, weight));
		}
	}

	@Override
	public void removeEdge(Vertex vertexA, Vertex vertexB) {
		if (isAdjacent(vertexA, vertexB)) {
			adjacencyList.get(vertexA).removeIf(item -> item.getVertexA().equals(vertexB));
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
