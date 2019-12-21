
package com.eserciziJava.esercizio4.graph;


public class Edge<T> {

	Vertex vertexA;
	Vertex vertexB;
	T tag;


	public Edge(Vertex vertexA, Vertex vertexB, T tag) {
		this.vertexA = vertexA;
		this.vertexB = vertexB;
		this.tag = tag;
	}

	public Edge(Vertex vertexA, T tag) {
		this.vertexA = vertexA;
		this.vertexB = null;
		this.tag = tag;
	}

	public Vertex getVertexA() {
		return vertexA;
	}

	public void setVertexA(Vertex vertexA) {
		this.vertexA = vertexA;
	}

	public Vertex getVertexB() {
		return vertexB;
	}

	public void setVertexB(Vertex vertexB) {
		this.vertexB = vertexB;
	}

	public T getTag() {
		return tag;
	}

	public void setTag(T tag) {
		this.tag = tag;
	}

	@Override
	public String toString() {
		if (vertexB == null) {
			return "\n\tEdge -> " + "destination= " + vertexA + " | tag=" + tag;
		} else if (vertexA == null) {
			return "\n\tEdge -> " + "destination= " + vertexB + " | tag=" + tag;
		} else {
			return "\n\tEdge -> " + "source= " + vertexA + " | destination= " + vertexB + " | tag=" + tag;
		}
	}

}
