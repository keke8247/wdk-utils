package com.wdk.util.thread;

/**
 * 为了验证 synchronized关键字锁的是对象, 让多个线程访问同一个对象.
 * */
public class ThreadSynchronizedTest2 {
	public static void main(String[] args){
		Sync2 sync = new Sync2();
		for(int i=0;i<3;i++){
			Thread thread = new MyThread2(sync);
			thread.start();
		}
	}
}

class Sync2 {
	/**
	 * 此时可以看到 synchronized关键字起作用了.
	 * */
	public synchronized void test(){
		System.out.println("test开始.....");
		
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("test结束.....");
	}
	
	/**
	 * 如果单纯的锁住这段代码,如果还是最开始的那段代码，每个线程new一个Sync对象，怎么才能让test方法不会被多线程执行。
	 * 解决也很简单，只要锁住同一个对象不就行了，synchronized后的括号中锁同一个固定对象。比较多的做法是让synchronized锁这个类对应的Class对象。
	 * */
	public void test2(){
		synchronized (Sync2.class) {
			System.out.println("test开始.....");
			
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			System.out.println("test结束.....");
		}
	}
}




class MyThread2 extends Thread{
	public Sync2 sync;
	
	public MyThread2(Sync2 sync){
		this.sync =sync;
	}
	
	@Override
	public void run(){
//		sync.test();
		sync.test2();
	}
}