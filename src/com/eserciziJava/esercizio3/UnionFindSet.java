package com.eserciziJava.esercizio3;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;

public class UnionFindSet<T> {

	private HashMap<T, Node<T>> map;

	//T il tipo del nodo

	public UnionFindSet() {
		map = new HashMap<>();
	}

	public void makeSet(@NotNull List<T> subSets) {
		subSets.forEach(subset -> map.put(subset, new Node<T>(subset)));
	}

	public T find(@NotNull T key) throws UnionFindSetException {

		if (!map.containsKey(key)) throw new UnionFindSetException("Set dont contain key: " + key);

		Node<T> node = getNode(key);

		if (node.getParent() != node)
			node.setParent(getNode(
							find(node.getParent().getKey())));
		return node.getParent().getKey();
	}

	public void union(T key1, T key2) throws UnionFindSetException {
		Node<T> node1 = getNode(find(key1));
		Node<T> node2 = getNode(find(key2));

		if (node1.getRank() > node2.getRank()) {
			node2.setParent(node1);

		} else if (node1.getRank() < node2.getRank()) {
			node1.setParent(node2);

		} else {
			node2.setParent(node1);
			node1.setRank(node1.getRank() + 1);
		}
	}

	public HashMap<T, Node<T>> getMap() {
		return map;
	}

	public Node<T> getNode(T key) {
		return map.get(key);
	}


}

