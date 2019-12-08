package com.eserciziJava.esercizio2.tests;

import com.eserciziJava.esercizio2.EditDistance;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class TestEditDistance {


	// editDistance normal
	@Test
	public void test_calculateOneEmpty() {
		String strA = "test";
		String strB = "";
		assertEquals(strA.length(), EditDistance.calculate(strA, strB));
	}

	@Test
	public void test_calculateEmpty() {
		assertEquals(0, EditDistance.calculate("", ""));
	}

	//@todo approfondire tipo Executable
	@Test
	public void test_calculateNull() {
		assertThrows(IllegalArgumentException.class, () -> EditDistance.calculate(null, null));
	}

	@Test
	public void test_calculateOne() {
		assertEquals(1, EditDistance.calculate("casa", "cassa"));
	}


	// editDistance dynamic normal
	@Test
	public void test_calculateDynOneEmpty() {
		String strA = "test";
		String strB = "";
		assertEquals(strA.length(), EditDistance.calculateDyn(strA, strB));
	}

	@Test
	public void test_calculateDynEmpty() {
		assertEquals(0, EditDistance.calculateDyn("", ""));
	}

	//@todo approfondire tipo Executable
	@Test
	public void test_calculateDynNull() {
		assertThrows(IllegalArgumentException.class, () -> EditDistance.calculateDyn(null, null));
	}

	@Test
	public void test_calculateDynOne() {
		assertEquals(1, EditDistance.calculateDyn("casa", "cassa"));
	}


}
