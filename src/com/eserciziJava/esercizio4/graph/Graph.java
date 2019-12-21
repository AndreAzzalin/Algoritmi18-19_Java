package com.eserciziJava.esercizio4.graph;


import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Graph<T>{

	/*
	grafo G(V,E) denso se : |E| = O(|V|^2). Esempio da ogni nodo si dipartono |V| archi(Grafo completo)
	grafo G(V,E) sparso se : |E| = O(|V|). Esempio da ogni nodo si dipartono k archi(Bounded Valence Graph)
	*/


	//hashmap con vertice e lista di edge
	protected HashMap<Vertex, List<Edge>> adjacencyList;
	protected List<Edge> edgeList;


	protected double weight;

	public Graph() {
		this.weight = 0;
		this.adjacencyList = new HashMap<>();
		this.edgeList = new ArrayList<>();
	}

	// edge continee il destinatario e il peso per raffiungerlo
	public void addVertex(Vertex vertex, List<Edge> connectedVertices) {
		if (adjacencyList.containsKey(vertex)) {
			if (connectedVertices != null)
				connectedVertices.addAll(adjacencyList.get(vertex));
			else
				connectedVertices = adjacencyList.get(vertex);
		}
		adjacencyList.put(vertex, connectedVertices);
	}

	public abstract void addEdge(Vertex source, Vertex destination, T tag);

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

	protected double add(double weightToSum) {
		return this.weight + weightToSum;
	}

	// Return true if vertex are adjacent
	public abstract boolean isAdjacent(Vertex vertexA, Vertex vertexB) ;

	// TODO: 21/12/2019  assolutamente da riconsiderare ci sono 3 cicli annidati
	// Remove vertex v from graph
	public void removeVertex(Vertex v) {
		for (Vertex x : adjacencyList.keySet()) {
			if (!x.equals(v)) {
				boolean b = false;
				while (!b) {
					b = true;
					for (int j = 0; j < adjacencyList.get(x).size() && b; j++) {
						if (adjacencyList.get(x).get(j).getVertexA().equals(v)) {
							adjacencyList.get(x).remove(j);
							b = false;
						}
					}
				}
			}
		}
		adjacencyList.remove(v);
	}

	public abstract void removeEdge(Vertex vertexA, Vertex vertexB);

	public double getWeight() {
		return this.weight;
	}

	public HashMap<Vertex, List<Edge>> getAdjacencyList() {
		return adjacencyList;
	}

	public abstract boolean isDirected();

	public List<Vertex> getVerticiesList() {
		return new ArrayList<>(this.adjacencyList.keySet());
	}

	public abstract List<Edge> getEdgesList();

	public boolean containVertex(Vertex v) {
		return this.adjacencyList.containsKey(v);
	}

	public boolean containEdge(Vertex vertexA, Vertex vertexB) {
		//comlpexity O(n)
		//se sono adiacenti significa che esiste un arco tra i due nodi
		return isAdjacent(vertexA,vertexB);
	}

	public int getVerticesCount() {
		return this.adjacencyList.size();
	}

	public abstract int getEdgesCount();

	public T vertexTag(Vertex vertexA, Vertex vertexB) {
		for (Edge edge : this.adjacencyList.get(vertexA)) {
			if (edge.getVertexA().equals(vertexB)) {
				return (T) edge.getTag();
			}
		}
		return null;
	}

	public void printGraph() {
		for (Vertex v : adjacencyList.keySet()) {
			System.out.println(v + " " + adjacencyList.get(v));
		}
	}

}
