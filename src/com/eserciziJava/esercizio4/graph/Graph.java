package com.eserciziJava.esercizio4.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Graph<T> {


	//hashmap con vertice e lista di edge relativi al vertice
	protected HashMap<Vertex, List<Edge>> adjacencyList;
	//list of edges in graph
	protected List<Edge> edgeList;
	protected double weight;

	public Graph() {
		this.weight = 0;
		this.adjacencyList = new HashMap<>();
		this.edgeList = new ArrayList<>();
	}

	/**
	 * add new vertex and list of all edges connected to it
	 *
	 * @param vertex            new vertex to add
	 * @param connectedVertices list of edges connected to vertex
	 */
	public void addVertex(Vertex vertex, List<Edge> connectedVertices) {
		if (adjacencyList.containsKey(vertex)) {
			if (connectedVertices != null)
				connectedVertices.addAll(adjacencyList.get(vertex));
			else
				connectedVertices = adjacencyList.get(vertex);
		}
		adjacencyList.put(vertex, connectedVertices);
	}

	/**
	 * add new edge to graph
	 *
	 * @param source      source vertex
	 * @param destination destination vertex
	 * @param tag         edge tag
	 */
	public abstract void addEdge(Vertex source, Vertex destination, T tag);

	/**
	 * @param vertex vertex to get the adjacent edges list
	 * @return list of all adjacent edges from vertex
	 */
	public List<Edge> getAdjacentEdges(Vertex vertex) {
		List<Edge> adjacentEdge = new ArrayList<>();
		for (Vertex it : adjacencyList.keySet()) {
			if (adjacencyList.get(it) != null) {
				if (it.equals(vertex)) {
					adjacentEdge.addAll(adjacencyList.get(it));
				}
			}
		}
		return adjacentEdge;
	}

	/**
	 * @param weightToSum edge weight to add to total
	 * @return new weight
	 */
	protected double add(double weightToSum) {
		return this.weight + weightToSum;
	}

	/**
	 * @param vertexA first vertex to be compared
	 * @param vertexB second vertex to be compared
	 * @return true if vertices are adjacent, false if not
	 */
	public abstract boolean isAdjacent(Vertex vertexA, Vertex vertexB);


	/**
	 * remove vertex from graph
	 *
	 * @param vertex vertex to be removed
	 */
	public void removeVertex(Vertex vertex) {
		for (Vertex vertexIt : adjacencyList.keySet()) {
			if (!vertexIt.equals(vertex)) {
				adjacencyList.get(vertexIt).
								removeIf(edge -> edge.getVertexA().equals(vertex)
												|| edge.getVertexB().equals(vertex) && edgeList.remove(edge));
			}
		}
		adjacencyList.remove(vertex);
	}

	/**
	 * remove edge from graph
	 *
	 * @param vertexA source vertex of edge who will be removed
	 * @param vertexB destination vertex of edge who will be removed
	 */
	public abstract void removeEdge(Vertex vertexA, Vertex vertexB);

	/**
	 * @return weight of graph
	 */
	public double getWeight() {
		return this.weight;
	}

	/**
	 * @return return HashMap of graph who stored vertices and edges
	 */
	public HashMap<Vertex, List<Edge>> getAdjacencyList() {
		return adjacencyList;
	}

	/**
	 * @return return true if graph is directed, false if not
	 */
	public abstract boolean isDirected();

	/**
	 * @return return list of all vertices of graph
	 */
	public List<Vertex> getVerticiesList() {
		return new ArrayList<>(this.adjacencyList.keySet());
	}

	/**
	 * @return return list of all edges of graph
	 */
	public abstract List<Edge> getEdgesList();

	/**
	 * @param vertex vertex to be analyzed
	 * @return return true if vertex is contained in graph, false if not
	 */
	public boolean containVertex(Vertex vertex) {
		return this.adjacencyList.containsKey(vertex);
	}

	/**
	 * @param vertexA source vertex of edge who will be analyzed
	 * @param vertexB destination vertex of edge who will be analyzed
	 * @return return true if vertexA are connected to vertexB, false if not
	 */
	public boolean containEdge(Vertex vertexA, Vertex vertexB) {
		//comlpexity O(n)
		//se sono adiacenti significa che esiste un arco tra i due nodi
		return isAdjacent(vertexA, vertexB);
	}

	/**
	 * @return return the number of vertices in graph
	 */
	public int getVerticesCount() {
		return this.adjacencyList.size();
	}

	/**
	 * @return return the number of edges in graph
	 */
	public abstract int getEdgesCount();

	/**
	 * @param vertexA source vertex of edge who will be analyzed
	 * @param vertexB destination vertex of edge who will be analyzed
	 * @return return tag of edge between vertexA and vertexB
	 */
	public T getVerticiesTag(Vertex vertexA, Vertex vertexB) {
		for (Edge edge : this.adjacencyList.get(vertexA)) {
			if (edge.getVertexA().equals(vertexB) || edge.getVertexB().equals(vertexB)) {
				return (T) edge.getTag();
			}
		}
		return null;
	}

	/**
	 * util method for print graph to terminal
	 */
	public void printGraph() {
		for (Vertex v : adjacencyList.keySet()) {
			System.out.println(v + " " + adjacencyList.get(v));
		}
	}

}
