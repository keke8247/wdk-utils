package com.wdk.util.thread.ybjc.two;

import java.util.logging.Logger;

public class RunnableDemo implements Runnable {

	private static final Logger log = Logger.getLogger("RunnableDemo");

	public Thread t;
	private String threadName;
	private boolean suspended = false;

	public RunnableDemo(String name) {
		this.threadName = name;
		log.info("Creating Thread:" + threadName);
	}

	@Override
	public void run() {
		log.info("Starting Thread:" + threadName);
		try {
			for (int i = 10; i > 0; i--) {
				log.info("Thread:" + threadName + "," + i);
				Thread.sleep(300);

				synchronized (this) {
					while (suspended) {
						wait();
					}
				}
			}
		} catch (InterruptedException e) {
			log.info("Thread " + threadName + " 被中断.");
		}
		log.info("Thread " + threadName + " 退出.");
	}

	public void start() {
		log.info("Starting " + threadName);
		if (t == null) {
			t = new Thread(this, threadName);
			t.start();
		}
	}
	
	void suspend(){
		suspended = true;
	}
	
	synchronized void resume(){
		suspended = false;
		notify();
	}
}
