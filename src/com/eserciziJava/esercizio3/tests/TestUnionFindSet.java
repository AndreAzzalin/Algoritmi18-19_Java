package com.eserciziJava.esercizio3.tests;



import com.eserciziJava.esercizio3.UnionFindSet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
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


		for (Integer el : arrayTest) {
			setTest.makeSet(el);
		}
	}


	@Test
	public void test_makeSet() {
		assertEquals(5, setTest.getElements().size());
	}

	@Test
	public void test_find() {
		assertEquals(5, setTest.find(5));
	}

	@Test
	public void test_union() {
		setTest.union(setTest.find(1), setTest.find(2));

		assertEquals(1, setTest.find(2));
		assertEquals(1, setTest.find(1));

		setTest.union(setTest.find(2), setTest.find(4));

		assertEquals(1, setTest.find(4));
	}


}
