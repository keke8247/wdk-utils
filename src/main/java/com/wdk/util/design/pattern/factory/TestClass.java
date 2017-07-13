package com.wdk.util.design.pattern.factory;

public class TestClass {
	
	public static void testMethod() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		TestOne test = (TestOne) ObjectFactory.getInstance().createObject("com.wdk.util.design.pattern.factory.TestOne");
		test.getUserMsg();
	}
	
	public static void main(String[] args) {
		try {
			testMethod();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
