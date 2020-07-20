package com.kata.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.kata.code.StringCalculator;

class StringCalculatorTest {

	@Test
	void testEmptyString() {
		StringCalculator calculator = new StringCalculator();
		assertEquals(0,calculator.Add(""));
	}
	
	@Test
	void testOneNumber() {
		StringCalculator calculator = new StringCalculator();
		assertEquals(10,calculator.Add("10"));
	}
	
	@Test
	void testTwoNumbers() {
		StringCalculator calculator = new StringCalculator();
		assertEquals(3,calculator.Add("1,2"));
	}
	
	@Test
	void testMutipleNumbers() {
		StringCalculator calculator = new StringCalculator();
		assertEquals(19,calculator.Add("1,8,10"));
		assertEquals(27,calculator.Add("1,8,10,1,7"));
		assertEquals(176,calculator.Add("1,8,10,1,7,10,40,99"));
	}
	
	@Test
	void testNewLinesWithNumbers() {
		StringCalculator calculator = new StringCalculator();
		assertEquals(19,calculator.Add("1\n8\n10"));
		assertEquals(27,calculator.Add("1,8,10\n1,7"));
		assertEquals(176,calculator.Add("1,8\n10,1,7\n10,40\n99"));
	}

}
