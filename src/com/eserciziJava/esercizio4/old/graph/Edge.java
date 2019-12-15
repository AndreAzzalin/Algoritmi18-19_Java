package com.eserciziJava.esercizio4.old.graph;

/**
 * An Edge links two vertices. Each edge has both a source node and a target 
 * node. An edge can have a weight. This class provides methods for the management of edges in a graph.
 * 
 * @author Bruno Lopardo
 * @author Alessandro Nasso
 * @param <E> the type of the element of the vertex
 */
public class Edge <E> implements Comparable<Edge>{

	private Vertex<E> vertex1;
	private Vertex<E> vertex2;
	private Number weight;

	public Edge(Vertex<E> vertex1, Vertex<E> vertex2, Number weight) {
		this.vertex1 = vertex1;
		this.vertex2 = vertex2;
		this.weight = weight;
	}

  /**
   * Returns the first vertex of the edge.
   * @return the first vertex of this edge
   */
  public Vertex<E> getVertex1() {
    return vertex1;
  }

  /**
   * Returns the second vertex of the edge.
   * @return the second vertex of this edge
   */
  public Vertex<E> getVertex2() {
    return vertex2;
  }

  /**
   * Returns the weight of the edge.
   * @return the weight of this edge
   */
  public Number getWeight() {
    return weight;
  }

  /**
   * Returns a string representation of the edge.
   * @return a string representation of the edge
   */
  public String toString() {
    return vertex2 + "," + weight;
  }


	@Override
	public int compareTo(Edge edg) {
		Integer x = (Integer) this.weight;
		Integer y = (Integer) edg.weight;
		return  x.compareTo(y);
	}


}