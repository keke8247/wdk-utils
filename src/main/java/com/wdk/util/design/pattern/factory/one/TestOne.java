package com.wdk.util.design.pattern.factory.one;

public class TestOne {
	private static String name;
	private static int age;
	
	public TestOne(String name, int age){
		this.name = name;
		this.age = age;
	}
	
	public TestOne(){
		this.name = "deafult";
		this.age = 18;
	}
	
	public void getUserMsg(){
		System.out.println("姓名:"+name);
		System.out.println("年龄:"+age);
	}
}
