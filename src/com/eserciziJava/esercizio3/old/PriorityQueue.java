package com.eserciziJava.esercizio3.old;


import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.stream.Collectors;

public class PriorityQueue<T, U> {

	/*
	https://www.geeksforgeeks.org/heap-sort/
	https://towardsdatascience.com/course-2-data-structure-part-2-priority-queues-and-disjoint-set-ed11a0383011
	https://www.geeksforgeeks.org/building-heap-from-array/
	 */

	private List<Node<T, U>> queue;
	private Comparator<U> comparator;
	Map<T, Integer> hMap;

	public PriorityQueue(Comparator<U> comparator) {
		queue = new ArrayList<>();
		this.comparator = comparator;
		hMap = new HashMap<>();
	}


	//
	private void heapify(int index) {
		// get indexes
		int largest = index;
		int leftIndex = getLeft(index);
		int rightIndex = getRight(index);

		// If left child is larger than root
		if (leftIndex < getSize() && (comparator.compare(queue.get(index).getPriority(), queue.get(leftIndex).getPriority()) < 0))
			largest = leftIndex;

		// If right  child is larger than root
		if (rightIndex < getSize() && comparator.compare(queue.get(largest).getPriority(), queue.get(rightIndex).getPriority()) < 0)
			largest = rightIndex;

		// If largest is not root
		if (largest != index) {
			swap(index, largest);
			// Recursively heapify the affected sub-tree
			heapify(largest);
		}


	}

	// create heap from index
	private void heapBuilder(int index) {
		for (int i = (getSize() / 2 - 1); i >= 0; i--) {
			heapify(i);
		}
	}

	private void swap(int indexA, int indexB) {
		Node<T, U> tmp = queue.get(indexA);

		hMap.put(queue.get(indexA).getValue(), indexB);
		hMap.put(queue.get(indexB).getValue(), indexA);
		queue.set(indexA, queue.get(indexB));
		queue.set(indexB, tmp);
	}

	private int getParent(int index) {
		return (index - 1) / 2;
	}

	private int getRight(int index) {
		return (index - 1) + 2;
	}

	private int getLeft(int index) {
		return (index - 1) + 1;
	}

	/*------------------------------------------------------------------*/

	public void insert(@NotNull T elem, @NotNull U priority) throws ProrityQueueException {

		if (isContained(elem)) throw new ProrityQueueException("Duplicate element not allowed");

		Node<T, U> tmp = new Node<>(elem, priority);
		queue.add(tmp);
		int i = getSize() - 1;
		hMap.put(elem, i);
		//If it violate the heap property, we will bubble up(called SiftUp)
		while (i > 0 && comparator.compare(tmp.getPriority(), queue.get(getParent(i)).getPriority()) > 0) {
			swap(i, getParent(i));
			i = getParent(i);
		}
	}

	public void updatePriority(T elem, @NotNull U priority) throws ProrityQueueException {
		if (!isContained(elem)) throw new ProrityQueueException("Element doesn't exist");

		int i = hMap.get(elem);
		if (comparator.compare(priority, queue.get(i).getPriority()) > 0) {
			queue.get(i).setPriority(priority);
			while (i > 0 && (comparator.compare(queue.get(i).getPriority(), queue.get(getParent(i)).getPriority()) > 0)) {
				swap(i, getParent(i));
				i = getParent(i);
			}
		} else {
			queue.get(i).setPriority(priority);
			heapify(i);
		}

	}

	public T getRoot() throws ProrityQueueException {
		if (queue.isEmpty()) throw new ProrityQueueException("Can't read from empty queue");

		return queue.get(0).getValue();
	}

	public T getExtractMax() throws ProrityQueueException {
		if (queue.isEmpty()) throw new ProrityQueueException("Can't read from empty queue");

		Node<T, U> maxNode = queue.get(0);
		swap(0, getSize() - 1);
		hMap.remove(maxNode.getValue());
		//before  without queue remove
		queue.remove(getSize() - 1);
		heapify(0);

		return maxNode.getValue();
	}

	public List<T> getList() {
		return queue.stream()
						.map(Node::getValue)
						.collect(Collectors.toList());
	}

	public int getSize() {
		return queue.size();
	}

	public boolean isContained(T key) {
		return key != null && hMap.containsKey(key);
	}

	public boolean isEmpty() {
		return queue.isEmpty();
	}
}
