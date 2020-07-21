package com.kata.code;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {

	public Integer Add(String numbers) throws Exception {
		
		if(numbers.equals(""))
			return 0;
		
		String defaultDelimiter = getDefaultDelimiter(numbers);
		
		int sum = 0;
		if(defaultDelimiter == null) {
		
			if(!numbers.contains(",") && !numbers.contains("\n")) {
				int parsedNumber = Integer.valueOf(numbers);
				checkForNegativeNumber(parsedNumber);
				return parsedNumber;
			}
			
			String[] numberArray = numbers.split(",|\n");
			for(int i =0;i<numberArray.length;i++) {
				int parsedNumber = Integer.valueOf(numberArray[i]);
				checkForNegativeNumber(parsedNumber);
				sum += parsedNumber;
			}
		}
		else {
			String exactNumber = numbers.replaceFirst("//(.*?)\n", "");
			
			if(exactNumber.equals(""))
				return 0;
			
			if(!exactNumber.contains(defaultDelimiter)) {
				int parsedNumber = Integer.valueOf(exactNumber);
				checkForNegativeNumber(parsedNumber);
				return parsedNumber;
			}
			
			String[] numberArray = exactNumber.split(Pattern.quote(defaultDelimiter));
			for(int i =0;i<numberArray.length;i++) {
				int parsedNumber = Integer.valueOf(numberArray[i]);
				checkForNegativeNumber(parsedNumber);
				sum += parsedNumber;
			}
		}
		
		return sum;
		
	}
	
	/**
	 * Get Default Delimiter according to the pattern '//[delimiter]\n[numbers]' if exists
	 * else return null
	 * @param numbers
	 * @return
	 */
	private String getDefaultDelimiter(String numbers) {
		if(numbers.startsWith("//")) {
			Pattern pattern = Pattern.compile("//(.*?)\n");
			Matcher matcher = pattern.matcher(numbers);
			while (matcher.find()) {
			    return matcher.group(1);
			}
		}
		return null;
	}
	
	private void checkForNegativeNumber(int number) throws Exception {
		if(number<0)
			throw new Exception("negatives not allowed "+number);
	}

}
