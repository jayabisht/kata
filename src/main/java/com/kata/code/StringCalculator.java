package com.kata.code;

public class StringCalculator {

	public Integer Add(String numbers) {
		if(numbers.equals(""))
			return 0;
		else if(!numbers.contains(","))
			return Integer.valueOf(numbers);
		else {
			String[] numberArray = numbers.split(",");
			int num1 = Integer.valueOf(numberArray[0]);
			int num2 = Integer.valueOf(numberArray[1]);
			
			return num1+num2;
		}
	}

}
