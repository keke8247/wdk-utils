package com.wdk.util.thread;

/**
 * 测试死锁
 * */
public class ThreadSynchronizedTest3 {
	public static void main(String [] args){
		Thread thread = new MyThread3();
		thread.start();
	}
}

class Sync3{
	public void test(){
		synchronized (Sync3.class) {
			System.out.println("test开始....");
			try {
				test2();
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("test结束....");
		}
	}
	
	public void test2(){
		synchronized (Sync3.class) {
			System.out.println("测试一下死锁......,应该不会执行到.因为Sync3.class 被锁住了");
		}
	}
}

class MyThread3 extends Thread{
	@Override
	public void run(){
		Sync3 sync = new Sync3();
		sync.test();
	}
}