package com.wdk.util.thread.ybjc.one;

import java.util.logging.Logger;

public class ThreadDemo extends Thread{
private static final Logger log = Logger.getLogger("ThreadDemo");
	
	private Thread t;
	private String threadName;
	
	public ThreadDemo(String name){
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
