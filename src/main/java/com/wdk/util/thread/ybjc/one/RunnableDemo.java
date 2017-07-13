package com.wdk.util.thread.ybjc.one;

import java.util.logging.Logger;

/**
 *	@Description
 *	实现Runnable接口
 *  @author wangdk,wangdk@erongdu.com
 *  @CreatTime 2017年7月13日 下午5:25:44
 *  @since version 1.0.0
 */
public class RunnableDemo implements Runnable{
	private static final Logger log = Logger.getLogger("RunnableDemo");
	
	private Thread t;
	private String threadName;
	
	public RunnableDemo(String name){
		this.threadName = name;
		
		log.info("creating thread:"+threadName);
	}

	@Override
	public void run() {
		log.info("Running :"+threadName);
		
		try {
			for(int i = 4; i > 0; i--) {
				log.info("Thread: " + threadName + ", " + i);
				Thread.sleep(50);
			}
		} catch (InterruptedException  e) {
			log.info("Thread " +  threadName + " interrupted.");
		}
		
		log.info("Thread " +  threadName + " exiting.");
	}
	
	public void start(){
		log.info("Starting:"+threadName);
		
		if(null == t){
			t = new Thread(this,threadName);
			t.start();
		}
	}
}
