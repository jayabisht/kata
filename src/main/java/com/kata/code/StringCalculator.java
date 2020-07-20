package com.kata.code;

public class StringCalculator {

	public Integer Add(String numbers) {
		if(numbers.equals(""))
			return 0;
		else
			return Integer.valueOf(numbers);
	}

}
