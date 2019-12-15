package com.eserciziJava.esercizio3.tests;

import com.eserciziJava.esercizio3.ufsOld.Node;
import com.eserciziJava.esercizio3.ufsOld.UnionFindSet;
import com.eserciziJava.esercizio3.ufsOld.UnionFindSetException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestUnionFindSet {
	public List<Integer> arrayTest;
	public UnionFindSet<Integer> setTest;

	@BeforeEach
	private void fillArrayTest() {
		arrayTest = new ArrayList<>();
		arrayTest.add(1);
		arrayTest.add(2);
		arrayTest.add(3);
		arrayTest.add(4);
		arrayTest.add(5);


		setTest = new UnionFindSet<>();


		setTest.makeSet(arrayTest);


	}


	@Test
	public void test_makeSet() {


		HashMap<Integer, Node<Integer>> map = setTest.getMap();

		assertEquals(5, map.size());
	}

	@Test
	public void test_find() throws UnionFindSetException {


		assertEquals(5, setTest.find(5));
	}

	@Test
	public void test_union() throws UnionFindSetException {


		setTest.union(setTest.find(1), setTest.find(2));
		Node<Integer> child = setTest.getNode(2);

		assertEquals(1, child.getParent().getKey());
	}


}
