package com.wdk.util.thread;

/**
 *	@Description
 *	测试volatile关键字
 *  @author wangdk,wangdk@erongdu.com
 *  @CreatTime 2017年2月6日 上午11:29:38
 *  @since version 1.0.0
 */
public class TestVolatile {
	private static int count = 0;
	
	private static volatile int count_volatile = 0;
	
	private static Object lock = new Object();
	
	private static void inc(){
		synchronized(lock){
			try {
				Thread.sleep(1);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			count++;
		}	
	}
	
	/**
	 * 对于volatile修饰的变量，jvm虚拟机只是保证从主内存加载到线程工作内存的值是最新的

		例如假如线程1，线程2 在进行read,load 操作中，发现主内存中count的值都是5，那么都会加载这个最新的值
		
		在线程1堆count进行修改之后，会write到主内存中，主内存中的count变量就会变为6
		
		线程2由于已经进行read,load操作，在进行运算之后，也会更新主内存count的变量值为6
		
		导致两个线程及时用volatile关键字修改之后，还是会存在并发的情况。
	 * */
	
	private static void inc_volatile(){
		try {
			Thread.sleep(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		count_volatile++;
		System.out.println("运算结果 TestVolatile.count_volatile = "+ TestVolatile.count_volatile);
	}
	
	public static void main(String[] args) {
		for (int i = 0; i < 1000; i++) {
			new Thread(new Runnable() {
				
				public void run() {
					TestVolatile.inc();
					TestVolatile.inc_volatile();
				}
			}).start();
		}
		
		System.out.println("运算结果 TestVolatile.count = "+ TestVolatile.count);
//		System.out.println("运算结果 TestVolatile.count_volatile = "+ TestVolatile.count_volatile);
	}
}
