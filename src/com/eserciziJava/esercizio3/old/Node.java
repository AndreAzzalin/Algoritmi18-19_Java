package com.eserciziJava.esercizio3.old;

public class Node<T, U> {

	private T value;
	private U priority;


	public Node(T value, U priority) {
		this.value = value;
		this.priority = priority;
	}


	public T getValue() {
		return value;
	}

	public U getPriority() {
		return priority;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public void setPriority(U priority) {
		this.priority = priority;
	}

}
