package com.wdk.util.core;

import org.apache.commons.lang3.time.StopWatch;

import java.util.ArrayList;

/**
 *	@Description
 *	循环1000000时   
 *		try 放到for外面  快
 *		try 放到for外面  慢
 *	循环10000000时   
 *		try 放到for外面  慢
 *		try 放到for外面  快
 *  @author wangdk,wangdk@erongdu.com
 *  @CreatTime 2017年7月12日 下午3:05:26
 *  @since version 1.0.0
 */
public class TestTryInForAndOutFor {

	private static void method1(int size) {
		StopWatch watch = new StopWatch();
		watch.start();

		ArrayList<String> al = new ArrayList<String>();
		String str = null;
		try {
			for (int i = 0; i < size; i++) {
				str = "str" + i;
				al.add(str);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		watch.stop();

		System.out.println("method1方法执行时间:" + watch.getTime());
	}

	private static void method2(int size) {
		StopWatch watch = new StopWatch();
		watch.start();

		ArrayList<String> al = new ArrayList<String>();
		String str = null;

		for (int i = 0; i < size; i++) {
			try {
				str = "str" + i;
				al.add(str);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		watch.stop();

		System.out.println("method2方法执行时间:" + watch.getTime());
	}
	private static void method3(int size) {
		StopWatch watch = new StopWatch();
		watch.start();
		
		ArrayList<String> al = new ArrayList<String>();
		String str = null;
		
		for (int i = 0; i < size; i++) {
			str = "str" + i;
			al.add(str);
		}
		
		watch.stop();
		
		System.out.println("method3方法执行时间:" + watch.getTime());
	}
	
	public static void main(String[] args) {
		method1(1000000);
		method2(1000000);
		method3(1000000);
	}
}
