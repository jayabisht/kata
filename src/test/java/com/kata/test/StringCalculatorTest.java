package com.kata.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import com.kata.code.StringCalculator;

class StringCalculatorTest {
	
	StringCalculator calculator = new StringCalculator();

	@Test
	void testEmptyString() throws Exception {		
		assertEquals(0,calculator.Add(""));
	}
	
	@Test
	void testOneNumber() throws Exception {
		assertEquals(10,calculator.Add("10"));
	}
	
	@Test
	void testTwoNumbers() throws Exception {
		assertEquals(3,calculator.Add("1,2"));
	}
	
	@Test
	void testMutipleNumbers() throws Exception {
		assertEquals(19,calculator.Add("1,8,10"));
		assertEquals(27,calculator.Add("1,8,10,1,7"));
		assertEquals(176,calculator.Add("1,8,10,1,7,10,40,99"));
	}
	
	@Test
	void testNewLinesWithNumbers() throws Exception {
		assertEquals(19,calculator.Add("1\n8\n10"));
		assertEquals(27,calculator.Add("1,8,10\n1,7"));
		assertEquals(176,calculator.Add("1,8\n10,1,7\n10,40\n99"));
	}
	
	@Test
	void testDynamicDelimiter() throws Exception {
		assertEquals(0,calculator.Add("//;\n"));
		assertEquals(1,calculator.Add("//;\n1"));
		assertEquals(3,calculator.Add("//;\n1;2"));
		assertEquals(27,calculator.Add("//>\n1>8>10>1>7"));
		assertEquals(176,calculator.Add("//++\n1++8++10++1++7++10++40++99"));
	}
	
	@Test
	void testNegativeNumberException() {
		Throwable exception1 = assertThrows(
	            Exception.class, () -> {
	            	calculator.Add("1,2\n-10,8");
	            }
	    );
		
		Throwable exception2 = assertThrows(
	            Exception.class, () -> {
	            	calculator.Add("//;\n-100");
	            }
	    );
	 
	    assertEquals("negatives not allowed -10", exception1.getMessage());
	    assertEquals("negatives not allowed -100", exception2.getMessage());
	}
	
	@Test
	void testMultipleNegativeNumberException() {
		Throwable exception1 = assertThrows(
	            Exception.class, () -> {
	            	calculator.Add("1,2\n-10,-8");
	            }
	    );
		
		Throwable exception2 = assertThrows(
	            Exception.class, () -> {
	            	calculator.Add("//;\n-100;-9;8;-78");
	            }
	    );
	 
	    assertEquals("negatives not allowed -10,-8", exception1.getMessage());
	    assertEquals("negatives not allowed -100,-9,-78", exception2.getMessage());
	}
	
	@Test
	void testNumberBiggerThanThousand() throws Exception {
		assertEquals(0,calculator.Add("//;\n1002"));
		assertEquals(1,calculator.Add("//;\n1;101010"));
		assertEquals(20,calculator.Add("//>\n1>8>10>1>7000"));
		assertEquals(167,calculator.Add("//++\n2010++89090++10++1++7++10++40++99"));
	}
	
	@Test
	void testMultipleLengthDelimiter() throws Exception {
		assertEquals(0,calculator.Add("//[;]\n"));
		assertEquals(1,calculator.Add("//[---]\n1"));
		assertEquals(3,calculator.Add("//[.....]\n1.....2"));
		assertEquals(27,calculator.Add("//[>>>]\n1>>>8>>>10>>>1>>>7"));
		assertEquals(176,calculator.Add("//[++]\n1++8++10++1++7++10++40++99"));
	}
	
	@Test
	void testMultipleDelimiters() throws Exception {
		assertEquals(0,calculator.Add("//[;][&]\n"));
		assertEquals(1,calculator.Add("//[-]\n1"));
		assertEquals(3,calculator.Add("//[*]\n1*2"));
		assertEquals(27,calculator.Add("//[_][;][%]\n1_8%10;1_7"));
		assertEquals(176,calculator.Add("//[@][&]\n1@8@10&1@7&10&40&99"));
	}
	
	@Test
	void testMultipleDelimitersWithlongerLength() throws Exception {
		assertEquals(0,calculator.Add("//[;;;][&]\n"));
		assertEquals(1,calculator.Add("//[--]\n1"));
		assertEquals(3,calculator.Add("//[****]\n1****2"));
		assertEquals(27,calculator.Add("//[__][;][%%%%]\n1__8%%%%10;1__7"));
		assertEquals(176,calculator.Add("//[@@][&&&]\n1@@8@@10&&&1@@7&&&10&&&40&&&99"));
	}
	
	@AfterAll
	static void testAddMethodInvocationCount() {
		assertEquals(37, StringCalculator.GetCalledCount());
	}

}
