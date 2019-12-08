package com.eserciziJava.esercizio3.old.tests;

import com.eserciziJava.esercizio3.old.ProrityQueueException;
import com.eserciziJava.esercizio3.comparators.IntegerComparator;
import com.eserciziJava.esercizio3.old.PriorityQueue;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestPriorityQueue {


	PriorityQueue<Integer, Integer> queueTest = new PriorityQueue<>(new IntegerComparator());

	private void fillQueueTest() throws ProrityQueueException {
		queueTest.insert(15, 2);
		queueTest.insert(5, 2);
		queueTest.insert(10, 1);
	}

	@Test
	public void test_getSize() throws ProrityQueueException {
		queueTest.insert(10, 1);
		assertEquals(1, queueTest.getSize());
	}

	@Test
	public void test_insert() throws ProrityQueueException {
		fillQueueTest();

		Integer[] arrayTest = {15, 5, 10};
		assertArrayEquals(arrayTest, queueTest.getList().toArray());
	}

	@Test
	public void test_updatePriority() throws ProrityQueueException {
		fillQueueTest();

		Integer[] arrayTest = {10, 5, 15};
		queueTest.updatePriority(10, 10);

		assertArrayEquals(arrayTest, queueTest.getList().toArray());
	}

	@Test
	public void test_getRoot() throws ProrityQueueException {
		fillQueueTest();

		assertEquals(15, queueTest.getRoot());
	}


	@Test
	public void test_extractMax() throws ProrityQueueException {
		fillQueueTest();
		int max = queueTest.getExtractMax();
		//check sia il val max sia se è stato rimosso

		Integer[] arrayTest = {5, 10};

		assertEquals(15, max);
		assertArrayEquals(arrayTest, queueTest.getList().toArray());
	}

	@Test
	public void test_isContained() throws ProrityQueueException {
		fillQueueTest();
		assertFalse(queueTest.isContained(4));
		assertTrue(queueTest.isContained(15));
	}


	@Test
	public void test_isEmpty() {
		assertTrue(queueTest.isEmpty());
	}
}
