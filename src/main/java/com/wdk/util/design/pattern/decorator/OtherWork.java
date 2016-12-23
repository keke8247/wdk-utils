package com.wdk.util.design.pattern.decorator;

import java.util.Date;

public class OtherWork {
	public static void insertLog(){
		System.out.println("记录日志!!!!");
	}
	
	public static void getDate(){
		System.out.println("记录执行时间:"+new Date());
	}
}
