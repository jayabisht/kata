package com.kata.code;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
	
	public static final int MAX_NUMBER = 1000;

	public Integer Add(String numbers) throws Exception {
		
		if(numbers.equals(""))
			return 0;
		
		String defaultDelimiter = getDefaultDelimiter(numbers);
		
		int sum = 0;
		if(defaultDelimiter == null) {
		
			if(!numbers.contains(",") && !numbers.contains("\n")) {
				checkForNegativeNumbers(new String[] {numbers});
				if(Integer.valueOf(numbers)>MAX_NUMBER)
					return 0;
				return Integer.valueOf(numbers);
			}
			
			String[] numberArray = numbers.split(",|\n");
			checkForNegativeNumbers(numberArray);
			for(int i =0;i<numberArray.length;i++) {
				if(Integer.valueOf(numberArray[i])>MAX_NUMBER)
					continue;
				sum += Integer.valueOf(numberArray[i]);
			}
		}
		else {
			String exactNumber = numbers.replaceFirst("//(.*?)\n", "");
			
			if(exactNumber.equals(""))
				return 0;
			
			if(!exactNumber.contains(defaultDelimiter)) {
				checkForNegativeNumbers(new String[] {exactNumber});
				if(Integer.valueOf(exactNumber)>MAX_NUMBER)
					return 0;
				return Integer.valueOf(exactNumber);
			}
			
			String[] numberArray = exactNumber.split(Pattern.quote(defaultDelimiter));
			checkForNegativeNumbers(numberArray);
			for(int i =0;i<numberArray.length;i++) {
				if(Integer.valueOf(numberArray[i])>MAX_NUMBER)
					continue;
				sum += Integer.valueOf(numberArray[i]);;
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
	
	private void checkForNegativeNumbers(String numberArray[]) throws Exception {
		String negativeNumbers = "";
		for(String number : numberArray) {
			int parsedNumber = Integer.valueOf(number);
			if(parsedNumber<0)
				negativeNumbers += parsedNumber+",";
				
		}
		if(!negativeNumbers.equals(""))
			throw new Exception("negatives not allowed "+negativeNumbers.substring(0, negativeNumbers.length()-1));
	}

}
