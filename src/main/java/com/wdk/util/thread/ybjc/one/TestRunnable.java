package com.wdk.util.thread.ybjc.one;

public class TestRunnable{
	public static void main(String[] args) {
		RunnableDemo r1 = new RunnableDemo("Thread-1");
		r1.start();
		
		RunnableDemo r2 = new RunnableDemo("Thread-2");
		r2.start();
	}
}
