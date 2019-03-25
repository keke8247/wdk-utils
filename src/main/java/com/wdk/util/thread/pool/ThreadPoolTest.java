package com.wdk.util.thread.pool;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * @Description 
 * java 四种线程池的使用示例
 * @author wangdk,wangdk@erongdu.com
 * @CreatTime 2017年5月22日 上午10:40:57
 * @since version 1.0.0
 */
public class ThreadPoolTest {
	
	/**
	 * @description
	 * newCachedThreadPool 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。示例代码如下：
	 * 	线程池为无限大，当执行第二个任务时第一个任务已经完成，会复用执行第一个任务的线程，而不用每次新建线程。
	 * @author wangdk
	 * @return void
	 * @since  1.0.0
	*/
	private static void threadPoolExecutorTest(){
		
		ExecutorService cachedThreadPool = Executors.newCachedThreadPool();	
		for(int i=0;i<5;i++){
			final int index = i;
			try {
				Thread.sleep(20);
			} catch (Exception e) { 
				e.printStackTrace();
			}
			
			cachedThreadPool.execute(new Runnable() {
				public void run() {
					System.out.println(index+":"+Thread.currentThread().getName());	
				}
			});
		}
	}

	/**
	 * @description
	 * newFixedThreadPool() 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。示例代码如下：
	 * 因为线程池大小为3，每个任务输出index后sleep 2秒，所以每两秒打印3个数字。
	      定长线程池的大小最好根据系统资源进行设置。如Runtime.getRuntime().availableProcessors()
	 * @author wangdk
	 * @return void
	 * @since  1.0.0
	*/
	private static void fixedThreadPoolTest(){
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3); //定长为3的线程池
		//Runtime.getRuntime().availableProcessors(); //返回java虚拟机可用的处理器数量.
		for(int i=0;i<10;i++){
			final int index = i;
			fixedThreadPool.execute(new Runnable() {
				public void run() {
					System.out.println(index+""+Thread.currentThread());
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
	}
	
	/**
	 * @description
	 * newScheduledThreadPool() 创建一个定长线程池，支持定时及周期性任务执行。延迟执行示例代码如下：
	 * 表示延迟3秒执行。
	 * @author wangdk
	 * @return void
	 * @since  1.0.0
	*/
	private static void scheduledThreadPoolTestOne(){
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
		scheduledThreadPool.schedule(new Runnable() {
			public void run() {
				System.out.println("delay 3 seconds");
			}
		},3,TimeUnit.SECONDS);
	}
	/**
	 * @description
	 * 表示延迟1秒后每3秒执行一次。
	 * @author wangdk
	 * @return void
	 * @since  1.0.0
	*/
	private static void scheduledThreadPoolTestTwo(){
		ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
		scheduledThreadPool.scheduleAtFixedRate(new Runnable() {
			public void run() {
				System.out.println("delay 1 seconds, and excute every 3 seconds");
			}
		},1,3,TimeUnit.SECONDS);
	}
	
	/**
	 * @description
	 * 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。示例代码如下：
	 * 结果依次输出，相当于顺序执行各个任务。
	 * @author wangdk
	 * @return void
	 * @since  1.0.0
	*/
	private static void singleThreadTest(){
		ExecutorService singleThread = Executors.newSingleThreadExecutor();
		
		for(int i=0;i<10;i++){
			final int index = i;
			singleThread.execute(new Runnable() {
				public void run() {
					System.out.println(index+":"+Thread.currentThread().getName());
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
	}
	
	private static void testWY(){
		int one = Integer.SIZE;
		System.out.println((1 << 2));  //左移运算符，num << 1,相当于num乘以2
		System.out.println(-1 << one);
		System.out.println(0 << one);
		System.out.println(1 << one);
		System.out.println(2 << one);
		System.out.println(3 << one);
	}

	/**
	 * @Description:
	 * 使用线程池 循环20000次
	 * @Author :wangdk
	 * @CreatTime: 2018/10/29 11:35
	*/
	private static void testUseThreadPool(){
		long startTime = System.currentTimeMillis();
		final List<Integer> l = new LinkedList<Integer>();
		ThreadPoolExecutor tp = new ThreadPoolExecutor(30, 30, 20, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(20000));
		final Random random = new Random();
		for (int i = 0; i < 20000; i++)
		{
			tp.execute(new Runnable()
			{
				public void run()
				{
					l.add(random.nextInt());
				}
			});
		}
		tp.shutdown();
		try
		{
			tp.awaitTermination(1, TimeUnit.DAYS);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		System.out.println("testUseThreadPool \n"+(System.currentTimeMillis() - startTime));
		System.out.println(l.size());
	}

	/**
	 * @Description:
	 * 使用可缓存线程池
	 * @Author :wangdk
	 * @CreatTime: 2018/10/29 11:46
	 */
	private static void testCacheThreadPool(){
		long startTime = System.currentTimeMillis();
		final List<Integer> l = new LinkedList<Integer>();
		ExecutorService cacheThreadPool = Executors.newCachedThreadPool();
		final Random random = new Random();
		for (int i = 0; i < 20000; i++)
		{
			cacheThreadPool.execute(new Runnable()
			{
				public void run()
				{
					l.add(random.nextInt());
				}
			});
		}
		cacheThreadPool.shutdown();
		try
		{
			cacheThreadPool.awaitTermination(1, TimeUnit.DAYS);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		System.out.println("testCacheThreadPool \n"+(System.currentTimeMillis() - startTime));
		System.out.println(l.size());

	}

	/**
	 * @Description:
	 * 使用单例化线程池
	 * @Author :wangdk
	 * @CreatTime: 2018/10/29 11:46
	 */
	private static void testSingleThreadPool(){
		long startTime = System.currentTimeMillis();
		final List<Integer> l = new LinkedList<Integer>();
		ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
		final Random random = new Random();
		for (int i = 0; i < 20000; i++)
		{
			singleThreadExecutor.execute(new Runnable()
			{
				public void run()
				{
					l.add(random.nextInt());
				}
			});
		}
		singleThreadExecutor.shutdown();
		try
		{
			singleThreadExecutor.awaitTermination(1, TimeUnit.MINUTES);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		System.out.println("testSingleThreadPool \n"+(System.currentTimeMillis() - startTime));
		System.out.println(l.size());

	}




	/**
	 * @Description:
	 * 不实用线程池 循环20000次
	 * @Author :wangdk
	 * @CreatTime: 2018/10/29 11:35
	*/
	private static void didnotUseThreadPool(){
		long startTime = System.currentTimeMillis();
		final List<Integer> l = new LinkedList<Integer>();
		final Random random = new Random();
		for (int i = 0; i < 20000; i++)
		{
			Thread thread = new Thread()
			{
				public void run()
				{
					l.add(random.nextInt());
				}
			};
			thread.start();
			try
			{
				thread.join();
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		System.out.println("didnotUseThreadPool \n"+(System.currentTimeMillis() - startTime));
		System.out.println(l.size());
	}


	public static void main(String[] args) {
//		testUseThreadPool();
//		testCacheThreadPool();
//		testSingleThreadPool();
//		didnotUseThreadPool();

//		testWY();
//		threadPoolExecutorTest();
		fixedThreadPoolTest();
//		scheduledThreadPoolTestOne();
//		scheduledThreadPoolTestTwo();
//		singleThreadTest();
	}
}
