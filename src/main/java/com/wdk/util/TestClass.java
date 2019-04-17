package com.wdk.util;

import java.util.HashMap;
import java.util.Hashtable;

public class TestClass {
	
	public static void main(String[] args) {
		Hashtable<Object,String> testTable = new Hashtable<>();
		testTable.put(null,"ewqeq");

		System.out.println(testTable.entrySet().size());
	}
}
