package com.eserciziJava.esercizio4.graph;


import java.util.ArrayList;
import java.util.List;

public class DirectedGraph<T> extends Graph<T> {

	/**
	 * @return
	 */
	@Override
	public List<Edge> getEdgesList() {
		List<Edge> edgeList = new ArrayList<>();

		for (Vertex x : adjacencyList.keySet()) {
			edgeList.addAll(adjacencyList.get(x));
		}

		return edgeList;
	}

	/**
	 * @param source
	 * @param destination
	 * @param weight
	 */
	@Override
	public void addEdge(Vertex source, Vertex destination, T weight) {
		boolean b1 = false;
		this.weight = add((Double) weight);
		Edge<T> newEdge = new Edge<>(source, destination, weight);

		this.edgeList.add(newEdge);


		if (!adjacencyList.containsKey(source)) {
			List<Edge> tempList = new ArrayList<>();
			tempList.add(newEdge);
			addVertex(source, tempList);
			b1 = true;
		}

		if (!adjacencyList.containsKey(destination)) {
			List<Edge> tempList = new ArrayList<>();
			addVertex(destination, tempList);
		}

		if (!b1) {
			adjacencyList.get(source).add(newEdge);
		}
	}

	/**
	 * @param vertexA
	 * @param vertexB
	 * @return
	 */
	@Override
	public boolean isAdjacent(Vertex vertexA, Vertex vertexB) {
		return getAdjacentEdges(vertexA)
						.stream()
						.anyMatch(it -> it.getVertexB().equals(vertexB));
	}

	/**
	 * @param vertexA
	 * @param vertexB
	 */
	@Override
	public void removeEdge(Vertex vertexA, Vertex vertexB) {
		if (isAdjacent(vertexA, vertexB)) {
			adjacencyList.get(vertexA).removeIf(item -> item.getVertexB().equals(vertexB));
		}
	}

	/**
	 * @return
	 */
	@Override
	public boolean isDirected() {
		return true;
	}

	/**
	 * @return
	 */
	@Override
	public int getEdgesCount() {
		int count = 0;
		for (Vertex x : adjacencyList.keySet()) {
			count = count + adjacencyList.get(x).size();
		}
		return count;
	}
}
