package com.eserciziJava.esercizio3;

import org.jetbrains.annotations.NotNull;

public class Node<T> {
	private Node<T> parent;
	private T key;
	int rank = 0;

	//key è il valore del nodo
	public Node(@NotNull T key) {
		this.key = key;

		this.parent = this;
		this.rank = 0;
	}

	public Node<T> getParent() {
		return parent;
	}

	public T getKey() {
		return key;
	}

	public int getRank() {
		return rank;
	}

	public void setParent(Node<T> parent) {
		this.parent = parent;
	}

	public void setKey(T key) {
		this.key = key;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
}



