package com.eserciziJava.esercizio2.tests;

import com.eserciziJava.esercizio2.EditDistance;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class EditDistanceTests {


	// editDistance normal
	@Test
	public void testCalculateOneEmpty() {
		String s1 = "test";
		String s2 = "";
		assertEquals(s1.length(), EditDistance.calculate(s1, s2));
	}

	@Test
	public void testCalculateEmpty() {
		assertEquals(0, EditDistance.calculate("", ""));
	}

	//@todo approfondire tipo Executable
	@Test
	public void testCalculateNull() {
		assertThrows(IllegalArgumentException.class, () -> EditDistance.calculate(null, null));
	}

	@Test
	public void testCalculateOne() {
		assertEquals(1, EditDistance.calculate("casa", "cassa"));
	}


	// editDistance dynamic normal
	@Test
	public void testCalculateDynOneEmpty() {
		String s1 = "test";
		String s2 = "";
		assertEquals(s1.length(), EditDistance.calculateDyn(s1, s2));
	}

	@Test
	public void testCalculateDynEmpty() {
		assertEquals(0, EditDistance.calculateDyn("", ""));
	}

	//@todo approfondire tipo Executable
	@Test
	public void testCalculateDynNull() {
		assertThrows(IllegalArgumentException.class, () -> EditDistance.calculateDyn(null, null));
	}

	@Test
	public void testCalculateDynOne() {
		assertEquals(1, EditDistance.calculateDyn("casa", "cassa"));
	}


}
