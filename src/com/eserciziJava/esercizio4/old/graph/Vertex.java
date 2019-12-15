package com.eserciziJava.esercizio4.old.graph;

/**
 * A vertex in a graph, with methods to access the properties.
 *
 * @param <T> the type of the element of the vertex
 * @author Bruno Lopardo
 * @author Alessandro Nasso
 */
public class Vertex<T> {

	//elem valore vertex
	private T elem;
	private int rank = 0;
	private Vertex<T> parent;

	public Vertex(T elem) {
		this.elem = elem;
	}

	/**
	 * Returns the value of the vertex.
	 *
	 * @return the value of this vertex
	 */
	public T getElem() {
		return this.elem;
	}


	public void setElem(T elem) {
		this.elem = elem;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	/**
	 * Returns the parent of the vertex.
	 *
	 * @return the parent of this vertex
	 */
	public Vertex<T> getParent() {
		return this.parent;
	}

	/**
	 * Sets the parent of the vertex.
	 *
	 * @param parent the new parent of this vertex
	 */
	public void setParent(Vertex<T> parent) {
		this.parent = parent;
	}

	/**
	 * Returns a string representation of the vertex.
	 *
	 * @return a string representation of the vertex
	 */
	public String toString() {
		return elem + "";
	}
}