package com.eserciziJava.esercizio4.graph;




import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class Graph<T> {


	protected HashMap<Vertex, List<Edge>> adjacencyList;
	protected List<Edge> edgeList;

	protected double weight;

	public Graph() {
		this.weight = 0;
		this.adjacencyList = new HashMap<>();
		this.edgeList = new ArrayList<>();
	}

	// TODO: 15/12/2019 magari cambiare connected vertices con edge
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

	public abstract void addEdge(Vertex source, Vertex destination, T weight);


	public List<Edge> adjacentEdge(Vertex v) {
		List<Edge> adjacentEdge = new ArrayList<>();
		for (Vertex x : adjacencyList.keySet()) {

			if (adjacencyList.get(x) != null) {
				if (x.equals(v)) {
				/*	for(int j = 0; j < adjacencyList.get(x).size(); j++ ){
						adjacentEdge.add(adjacencyList.get(x).get(j));
					}*/

					// TODO: 15/12/2019 verificare se  è equivalente
					adjacentEdge.addAll(adjacencyList.get(x));
				}

				/*else if (directed){
					for(int j = 0; j < adjacencyList.get(x).size(); j++ ){
						if(adjacencyList.get(x).get(j).getVertex().equals(v)){
							adjacentEdge.add(adjacencyList.get(x).get(j));
						}
					}
				}*/
			}
		}
		return adjacentEdge;
	}


	public double add(double weight, @NotNull T x) {
		return weight + (double) x;
	}

	// Return true if vertex are adjacent
	public boolean adjacentVertex(Vertex v1, Vertex v2){
		List<Edge> adjacentEdge1 = adjacentEdge(v1);
		for(int j = 0; j < adjacentEdge1.size(); j++ ){
			if(adjacentEdge1.get(j).getVertex1().equals(v2)){
				return true;
			}
		}
		List<Edge> adjacentEdge2 = adjacentEdge(v2);
		for(int j = 0; j < adjacentEdge2.size(); j++ ){
			if(adjacentEdge1.get(j).getVertex1().equals(v1)){
				return true;
			}
		}

		return false;
	}

	// Remove vertex v from graph
	public void removeVertex(Vertex v){
		for (Vertex x: adjacencyList.keySet()){
			if(!x.equals(v)){
				boolean b = false;
				while(!b){
					b = true;
					for(int j = 0; j < adjacencyList.get(x).size() && b; j++ ){
						if(adjacencyList.get(x).get(j).getVertex1().equals(v)){
							adjacencyList.get(x).remove(j);
							b = false;
						}
					}
				}
			}
		}
		adjacencyList.remove(v);
	}

	public abstract void removeEdge( Vertex v1, Vertex v2);

	public double weight(){
		return weight;
	}

	public void printGraph(){
		for ( Vertex v: adjacencyList.keySet()){
			System.out.println(v + " " + adjacencyList.get(v));
		}

	}

	public HashMap<Vertex, List<Edge>> getAdjacencyList() {
		return adjacencyList;
	}

	public abstract boolean isDirected();

	public List<Vertex> getVertexList(){
		List<Vertex> vertexList = new ArrayList<Vertex>();
		vertexList.addAll(this.adjacencyList.keySet());
		return vertexList;
	}

	public List<Edge> getEdgeList(){
		return edgeList;
	}


	// TODO: 15/12/2019 da valutare se lasciarlo o meno
	public List<Edge> getDirectedEdgeList(){
		List<Edge> edgeList = new ArrayList<>();
		for (Vertex x: adjacencyList.keySet()){
			edgeList.addAll(adjacencyList.get(x));
		}
		return edgeList;
	}

	public boolean containVertex( Vertex v){
		return this.adjacencyList.containsKey(v);
	}

	public boolean containEdge(Vertex v1, Vertex v2){
		List<Edge> edgeList= this.adjacencyList.get(v1);
		boolean contain = false;
		int i;
		for (i=0; i<edgeList.size() && !contain; i++){
			if(edgeList.get(i).getVertex1().equals(v2))
				contain = true;
		}
		return contain;
	}

	public int vertexNumber(){
		return this.adjacencyList.size();
	}

	public abstract int getEdgeNumber();

	public T vertexLabel(Vertex v1, Vertex v2){
		T label = null;
		boolean contain = false;
		List<Edge> edgeList= this.adjacencyList.get(v1);
		int i;
		for (i=0; i<edgeList.size() && !contain; i++){
			if(edgeList.get(i).getVertex1().equals(v2)){
				contain = true;
				label = (T) edgeList.get(i).getWeight();
			}
		}
		return label;
	}



}
