package com.wdk.util;

public class TestClass {
	
	static int k = 15;
	public static void main(String[] args) {
		int i = 9;
		int j = 17;
//		i <<= 1;
		System.out.println("k的二进制表示:"+java.lang.Integer.toBinaryString(k));
		
		System.out.println("i的二进制表示:"+java.lang.Integer.toBinaryString(i));
		System.out.println("j的二进制表示:"+java.lang.Integer.toBinaryString(j));
//		
		
		// & 两个操作数中位都为1，结果才为1，否则结果为0
		
		// | 两个位只要有一个为1，那么结果就是1，否则就为0
		
		// ~ 如果位为0，结果是1，如果位为1，结果是0
		
		// ^ 两个操作数的位中，相同则结果为0，不同则结果为1
		System.out.println("i^=j的二进制表示:"+java.lang.Integer.toBinaryString(i^=j));
//		
//		System.out.println("i的二进制表示:"+java.lang.Integer.toBinaryString(i));
//		System.out.println("j的二进制表示:"+java.lang.Integer.toBinaryString(j));
//		
		System.out.println(i^=j);
	}
}
