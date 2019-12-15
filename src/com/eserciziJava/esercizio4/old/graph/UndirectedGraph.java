package com.eserciziJava.esercizio4.old.graph;


import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Class that ovverrides addEdge() and getWeight() methods of the superclass
 * Graph in order to represent an undirected graph. The class provides
 * also the Prim's algorithm.
 * This class uses the priorityqeueue package for the Prim's algorithm.
 *
 * @param <T> the type of the element of the vertex
 * @author Bruno Lopardo
 * @author Alessandro Nasso
 */
public class UndirectedGraph<T> extends Graph<T> {

	/**
	 * Add an undirected edge in the graph between the vertex
	 * whose element is elem1 and the vertex whose element is elem2.
	 */
	@Override
	public void addEdge(@NotNull T elem1, @NotNull T elem2, Number weight) throws GraphException {
		if (elem1.equals(elem2)) throw new GraphException("No loops allowed in undirected graph");
		// TODO: 14/12/2019 verificare che effettivamente non serve
		//throw new GraphException("Edge already in the graph");
		if (weight == null) weighted = false;

		if (!edgeExists(elem1, elem2)) {

			Edge<T> edge1 = new Edge<>(getVertex(elem1), getVertex(elem2), weight);
			Edge<T> edge2 = new Edge<>(getVertex(elem2), getVertex(elem1), weight);

			addAdj(getVertex(elem1), edge1);
			addAdj(getVertex(elem2), edge2);

			numEdges++;
			numEdges++;
		}
	}

	@Override
	public boolean isDirected() {
		return false;
	}

	@Override
	public void deleteEdge(T elem1, T elem2) throws GraphException {


		Edge<T> e1 = getEdge(elem1, elem2);
		Edge<T> e2 = getEdge(elem2, elem1);


		adjList.values().forEach(list -> list.removeIf(item -> item.equals(e1) || item.equals(e2)));
		numEdges--;
		numEdges--;

	}


	@Override
	public int getNumEdges() {
		return this.numEdges / 2;
	}


	@Override
	public void deleteVertex(T elem) throws GraphException {

		if (vertexExists(elem)) {

			Vertex<T> vertex = getVertex(elem);

			List<Vertex<T>> allVertex = getAllVertices();

			allVertex.stream().filter(item -> !item.equals(vertex))
							.forEach(item -> {
								try {
									deleteEdge(item.getElem(), elem);
								} catch (GraphException e) {
									e.printStackTrace();
								}
							});

			vertices.remove(elem);

			numVertices--;

		}
	}

	/**
	 * Returns the weight of the graph.
	 *
	 * @return the weight of the graph
	 */
	@Override
	public double getWeight() throws GraphException {
		return super.getWeight() / 2;
	}
}