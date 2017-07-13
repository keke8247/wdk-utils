package com.wdk.util.matches;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMatches {
	private static void testMatches(){
		String line = "13267007582";
		
		String pattern = "^((13[0-9])|(15[^4,\\D])|(18[0-9]))\\d{8}$";
		
		Pattern r = Pattern.compile(pattern);
		
		Matcher m = r.matcher(line);
	      if (m.find( )) {
	         System.out.println(m.matches());
	      } else {
	         System.out.println("NO MATCH");
	      }
	}
	
	public static void main(String[] args) {
		testMatches();
	}
}
