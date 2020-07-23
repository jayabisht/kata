package com.kata.code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCalculator {
	
	public final int MAX_NUMBER = 1000;
	public final String DELIMITER_FORMAT = "\\[?([^\\]]*)\\]?";			
	public final String[] DEFAULT_DELIMITERS = {",","\n"};
	public static int addCount = 0;

	public Integer Add(String numbers) throws Exception {
		
		addCount++;
		
		// Return 0 for empty string
		if(numbers.equals(""))
			return 0;
		
		List<String> delimiters = getDelimiters(numbers);
		
		int sum = 0;
		// Extract exact numbers from input
		if(!delimiters.contains("\n")) {
			String[] parts = numbers.split("\n");
			if(parts.length>1)
				numbers = parts[1];
			else
				numbers = "";
			
		}
		
		// Return 0 for empty string
		if(numbers.equals(""))
			return 0;
		final String exactNumber = numbers;
		// If there is no delimiter in input, return the input as number
		if(!delimiters.stream().anyMatch(d->exactNumber.contains(d))) {
			checkForNegativeNumbers(new String[] {numbers});
			if(Integer.valueOf(numbers)>MAX_NUMBER)
				return 0;
			return Integer.valueOf(numbers);
		}
		
		// Split numbers according to delimiters and calculate sum
		String splitPattern = "";
		for(String d: delimiters)
			splitPattern += Pattern.quote(d) + "|";
		splitPattern = splitPattern.substring(0, splitPattern.length()-1);
		
		String[] numberArray = numbers.split(splitPattern);
		checkForNegativeNumbers(numberArray);
		for(int i =0;i<numberArray.length;i++) {
			if(Integer.valueOf(numberArray[i])>MAX_NUMBER)
				continue;
			sum += Integer.valueOf(numberArray[i]);
		}		
		return sum;
		
	}
	
	/**
	 * Get Delimiters according to the pattern if exists or default ones
	 * @param numbers
	 * @return
	 */
	private List<String> getDelimiters(String numbers) {
		List<String> delimiters = new ArrayList<String>();
		if(numbers.startsWith("//")) {
			numbers = numbers.replaceFirst("//", "");
			numbers = numbers.split("\n")[0];
			Pattern pattern = Pattern.compile(DELIMITER_FORMAT);
			Matcher matcher = pattern.matcher(numbers);
			while (matcher.find()) {
				if(!matcher.group(1).equals(""))
					delimiters.add(matcher.group(1));
			}
			return delimiters;
		}
		return Arrays.asList(DEFAULT_DELIMITERS);
	}
	
	/**
	 * Check for negative numbers in the given Array and throw Exception if there is any negative number.
	 * @param numberArray
	 * @throws Exception
	 */
	private void checkForNegativeNumbers(String numberArray[]) throws Exception {
		String negativeNumbers = "";
		for(String number : numberArray) {
			int parsedNumber = Integer.valueOf(number);
			if(parsedNumber<0)
				negativeNumbers += parsedNumber+",";
				
		}
		if(!negativeNumbers.equals(""))
			throw new Exception("negatives not allowed " + negativeNumbers.substring(0, negativeNumbers.length()-1));
	}

	public static int GetCalledCount() {	
		return addCount;
	}

}
