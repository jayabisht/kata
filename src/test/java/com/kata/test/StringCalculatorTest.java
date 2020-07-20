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

}
