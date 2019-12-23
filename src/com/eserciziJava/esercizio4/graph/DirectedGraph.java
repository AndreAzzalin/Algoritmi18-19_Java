package com.eserciziJava.esercizio4.graph;


import java.util.ArrayList;
import java.util.List;

public class DirectedGraph<T> extends Graph<T> {

	/**
	 * @return return list of edges in directed graph
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
	 * @param source      source vertex for new edge in directed graph
	 * @param destination destination vertex for new edge in directed graph
	 * @param weight      weight for new edge in directed graph
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
	 * @param vertexA first vertex to be analyzed
	 * @param vertexB second vertex to be analyzed
	 * @return return true if vertexA is adjacent to vertexB, false if not
	 */
	@Override
	public boolean isAdjacent(Vertex vertexA, Vertex vertexB) {
		return getAdjacentEdges(vertexA)
						.stream()
						.anyMatch(it -> it.getVertexB().equals(vertexB));
	}

	/**
	 * @param vertexA source vertex of edge who will be removed from directed graph
	 * @param vertexB destination vertex of edge who will be removed from directed graph
	 */
	@Override
	public void removeEdge(Vertex vertexA, Vertex vertexB) {
		if (isAdjacent(vertexA, vertexB)) {
			adjacencyList.get(vertexA).removeIf(item -> item.getVertexB().equals(vertexB));
		}
	}

	/**
	 * @return always true because invoke method from directed graph instance
	 */
	@Override
	public boolean isDirected() {
		return true;
	}

	/**
	 * @return return number of edge in directed graph
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
