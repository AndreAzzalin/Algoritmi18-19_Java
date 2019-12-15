package com.eserciziJava.esercizio4.old.graph;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Represents a graph as a collection of nodes connected by edges. A graph does
 * not need to contain any nodes or edges however if there is at least one edge
 * then there must be at least one node. There can, however, be one or more
 * nodes with no edges present. This class permits any of the following
 * common variations of graphs: directed and undirected edges, edges with attributes,
 * loops on the same vertex (only in directed graphs).
 * The graph is rapresented through adjacency lists implemented through ArrayList.
 * The graph doesnt accept this.vertices with the same element (e.g. addVertex(3);
 * addVertex(3); will result in GraphException).
 * A graph can be weighed or not weighed. It is weighed only if all of its edges
 * are weighed.
 * <p>
 * The following features are supported: add this.vertices, add edges, num of this.vertices
 * and num of edges, Prim's algorithm (only for undirected graphs).
 *
 * @param <T> the element type of the vertex
 * @author Bruno Lopardo
 * @author Alessandro Nasso
 */

public abstract class Graph<T> {

	//lista di vertex key -> val vertex
	Map<T, Vertex<T>> vertices = new HashMap<>();

	//es (nodo 3, list di archi )  ( 3, { [3,4],[3,1],[3,2] })
	Map<Vertex<T>, List<Edge<T>>> adjList = new HashMap<>();

	public int numVertices;
	public int numEdges;
	public boolean weighted;


	public Graph() {
		this.numVertices = 0;
		this.numEdges = 0;
		this.weighted = true;
	}

	public void addVertex(@NotNull T elem)  {
		// TODO: 14/12/2019 verificare se effettivamente non serve lanciare l'eccezione 
		if (!vertexExists(elem)){ //throw new GraphException("Vertex already in the graph or elem is invalid");
		Vertex<T> tmp = new Vertex<>(elem);
		this.vertices.put(elem, tmp);
		this.adjList.put(tmp, new ArrayList<>());
		this.numVertices++;
	}
	}

	//implementazione per ogni tipo di grafo (directed o undirected)
	public abstract void addEdge(@NotNull T v1, @NotNull T v2, Number weight) throws GraphException;

	//in base al tipo ritorna se è diretto o meno
	public abstract boolean isDirected();

	public boolean vertexExists(@NotNull T elem) {
		return this.vertices.containsKey(elem);
	}

	//per undirectional vale per entrambe le direzioni
	public boolean edgeExists(@NotNull T elem1, @NotNull T elem2) throws GraphException {
		if (!vertexExists(elem1) || !vertexExists(elem2)) return false;
		Vertex<T> v1 = getVertex(elem1);
		Vertex<T> v2 = getVertex(elem2);

		List<Edge<T>> edgeList = adjList.get(v1);

		return edgeList.stream().anyMatch(
						item -> item.getVertex2().equals(v2));
	}

	public abstract void deleteVertex(T elem) throws GraphException ;

	public abstract void deleteEdge(T elem1, T elem2) throws GraphException;

	public int getNumVertices() {
		return this.numVertices;
	}

	public abstract int getNumEdges();


	public List<Vertex<T>> getAllVertices() {
		return new ArrayList<>(vertices.values());
	}

	public List<Edge<T>> getAllEdges() {

		List<Edge<T>> ret = new ArrayList<>();
		adjList.forEach((key, list) -> ret.addAll(list));
		return ret;
	}


	public List<Vertex<T>> getAdj(@NotNull T elem) throws GraphException {
		if (!vertexExists(elem)) throw new GraphException("Vertex doesn't exists or elem is invalid");
		Vertex vertex = getVertex(elem);

		return adjList.get(vertex)
						.stream().map(Edge::getVertex2)
						.collect(Collectors.toList());
	}

	public Number getWeight(@NotNull T elem1, @NotNull T elem2) throws GraphException {
		if (!weighted) throw new GraphException("Graph not weighted");

		if (edgeExists(elem1, elem2)) {
			return getEdge(elem1, elem2).getWeight();
		} else
			throw new GraphException("Edge dosen't exist between " + elem1 + "and" + elem2);

	}

	public Map<Vertex<T>, List<Edge<T>>> getAdjList() {
		return adjList;
	}

// ===================== TO BE PRIVATE =====================================

	//ritorna il vertex dato l'elem
	public Vertex<T> getVertex(T elem) throws GraphException {
		if (!vertexExists(elem)) throw new GraphException("Vertex doesn't exists or elem is invalid");
		return this.vertices.get(elem);
	}


	//get edge between vertex1 vertex2
	public Edge<T> getEdge(@NotNull T elem1, @NotNull T elem2) throws GraphException {
		if (!vertexExists(elem1) || !vertexExists(elem2))
			throw new GraphException("Vertex doesn't exists or elem is invalid");

		Vertex<T> v1 = getVertex(elem1);
		Vertex<T> v2 = getVertex(elem2);
		List<Edge<T>> edgeList = adjList.get(v1);

		return edgeList.stream()
						.filter(item -> item.getVertex2().equals(v2))
						.findFirst()
						.orElse(null);

	}


	public void addAdj(Vertex<T> vertex, Edge<T> edge) {
		adjList.get(vertex).add(edge);
	}





	/*============================ DOVREBBE NON SERVIRE ========================================================================================================*/


	public Number getEdgeWeight(T elem1, T elem2) throws GraphException {
		return getEdge(elem1, elem2).getWeight();
	}


	public double getWeight() throws GraphException {
		if (!this.weighted) throw new GraphException("Graph is not weighed");
		double weight = 0;


		for (List<Edge<T>> array : adjList.values()) {
			for (Edge<T> edge : array) {
				//weight += edge.getWeight();
			}
		}
		return weight;
	}


}