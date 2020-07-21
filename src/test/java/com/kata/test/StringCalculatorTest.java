package com.kata.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.kata.code.StringCalculator;

class StringCalculatorTest {
	
	StringCalculator calculator = new StringCalculator();

	@Test
	void testEmptyString() {
		
		assertEquals(0,calculator.Add(""));
	}
	
	@Test
	void testOneNumber() {
		assertEquals(10,calculator.Add("10"));
	}
	
	@Test
	void testTwoNumbers() {
		assertEquals(3,calculator.Add("1,2"));
	}
	
	@Test
	void testMutipleNumbers() {
		assertEquals(19,calculator.Add("1,8,10"));
		assertEquals(27,calculator.Add("1,8,10,1,7"));
		assertEquals(176,calculator.Add("1,8,10,1,7,10,40,99"));
	}
	
	@Test
	void testNewLinesWithNumbers() {
		assertEquals(19,calculator.Add("1\n8\n10"));
		assertEquals(27,calculator.Add("1,8,10\n1,7"));
		assertEquals(176,calculator.Add("1,8\n10,1,7\n10,40\n99"));
	}
	
	@Test
	void testDynamicDelimiters() {
		assertEquals(0,calculator.Add("//;\n"));
		assertEquals(1,calculator.Add("//;\n1"));
		assertEquals(3,calculator.Add("//;\n1;2"));
		assertEquals(27,calculator.Add("//>\n1>8>10>1>7"));
		assertEquals(176,calculator.Add("//++\n1++8++10++1++7++10++40++99"));
	}

}
