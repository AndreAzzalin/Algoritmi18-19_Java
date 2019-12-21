package com.eserciziJava.esercizio4.graph;

public class Vertex<T> {
	T key;

	public Vertex(T key) {
		this.key = key;

	}

	public T getKey() {
		return key;
	}

	public void setKey(T key) {
		this.key = key;
	}

	@Override
	public String toString() {
		return "Vertex -> key= " + key;
	}

}
