package com.wdk.util.thread;
/**
 * 测试内容: synchronized锁住的是代码还是对象
 * 
 * */
public class ThreadSynchronizedTest {
	public static void main(String[] args) {
		for(int i=0;i<3;i++){
			Thread thread = new MyThread();
			thread.start();
		}
	}
}


class Sync {
	
	/**
	 * 从输出结果可以看出来，上面的程序起了三个线程，同时运行Sync类中的test()方法，
	 * 虽然test()方法加上了synchronized，但是还是同时运行起来，貌似synchronized没起作用。
	 * */
	public synchronized void test(){ 
		System.out.println("test开始....");
		try {
			Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("test结束....");
	}
	
	/**
	 * 将test()方法上的synchronized去掉，在方法内部加上synchronized(this)：
	 * 运行结果,和test()方法一致,没有看到synchronized起到作用
	 * 
	 * 实际上，synchronized(this)以及非static的synchronized方法（至于static synchronized方法请往下看），
	 * 只能防止多个线程同时执行同一个对象的同步代码段。	
	 * 
	 * 回到本文的题目上：synchronized锁住的是代码还是对象。
	 * 答案是：synchronized锁住的是括号里的对象，而不是代码。对于非static的synchronized方法，锁的就是对象本身，也就是this。
	 * 
	 * 所以 要做到线程同步, 多个线程锁的必须是同一个对象.这样一个线程给对象加了锁,另一个对象等待锁的释放.
	 * 在使用synchronized的时候,能缩小代码块的范围的时候尽量缩小.减小锁的粒度.是代码能更大程度的并发.
	 * 
	 * 为了验证上述观点  参考 ThreadSynchronizedTest2
	 * 
	 * */
	public void test2(){
		synchronized (this) {
			System.out.println("test开始....");
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("test结束....");
		}
	}
}

class MyThread extends Thread{
	@Override
	public void run() {
		Sync sync = new Sync();
//		sync.test();
		sync.test2();
	}
}