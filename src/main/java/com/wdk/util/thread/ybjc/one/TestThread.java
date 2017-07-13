package com.wdk.util.thread.ybjc.one;

public class TestThread{
	public static void main(String[] args) {
		ThreadDemo r1 = new ThreadDemo("Thread-1");
		r1.start();
		
		ThreadDemo r2 = new ThreadDemo("Thread-2");
		r2.start();
	}
}
