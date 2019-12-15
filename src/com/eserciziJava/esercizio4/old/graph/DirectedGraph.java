package com.eserciziJava.esercizio4.old.graph;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Class that ovverrides the addEdge method of the superclass Graph
 * in order to represent a directed graph.
 *
 * @param <T> the type of the element of the vertex
 * @author Bruno Lopardo
 * @author Alessandro Nasso
 */
public class DirectedGraph<T> extends Graph<T> {


	@Override
	public void addEdge(@NotNull T elem1, @NotNull T elem2, Number weight) throws GraphException {
		if (edgeExists(elem1, elem2)) throw new GraphException("Edge already in the graph");
		if (weight == null) weighted = false;

		Edge<T> edge = new Edge<>(getVertex(elem1), getVertex(elem2), weight);
		addAdj(getVertex(elem1), edge);
		numEdges++;
	}


	@Override
	public boolean isDirected() {
		return true;
	}

	@Override
	public void deleteEdge(T elem1, T elem2) throws GraphException {

		Edge<T> edgeToRemove = getEdge(elem1, elem2);

		adjList.values().forEach(list -> list.removeIf(item -> item.equals(edgeToRemove)));
		numEdges--;

	}


	@Override
	public int getNumEdges() {
		return this.numEdges;
	}


	@Override
	public void deleteVertex(T elem) throws GraphException {

		if (vertexExists(elem)) {

			adjList.keySet().remove(getVertex(elem));

			Vertex<T> vertex = getVertex(elem);
			List<Vertex<T>> allVertex = getAllVertices();

			allVertex.stream().filter(item -> !item.equals(vertex))
							.forEach(item -> {
								try {
									deleteEdge(item.getElem(),elem);
								} catch (GraphException e) {
									e.printStackTrace();
								}
							});

			vertices.remove(elem);

			numVertices--;

		}
	}
}

