package com.kata.code;

public class StringCalculator {

	public Integer Add(String numbers) {
		if(numbers.equals(""))
			return 0;
		else if(!numbers.contains(","))
			return Integer.valueOf(numbers);
		else {
			String[] numberArray = numbers.split(",");
			int sum = 0;
			for(int i =0;i<numberArray.length;i++) {
				sum += Integer.valueOf(numberArray[i]);
			}
			
			return sum;
		}
	}

}
