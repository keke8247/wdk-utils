package com.wdk.util.thread;

public class MyRunnable implements Runnable{
	private Foo foo = new Foo();
	
	public static void main(String[] args) {
		MyRunnable myRunnale = new MyRunnable();
		
		Thread ta = new Thread(myRunnale, "Thread-A");
		Thread tb = new Thread(myRunnale, "Thread-B");
		
		ta.start();
		tb.start();
	}
	
	public void run() {
		for (int i = 0; i < 3; i++) { 
            this.fix(30); 
            try { 
                Thread.sleep(1); 
            } catch (InterruptedException e) { 
                e.printStackTrace(); 
            } 
            System.out.println(Thread.currentThread().getName() + " : 当前foo对象的x值= " + foo.getX()); 
        } 
	}
	
	public int fix(int y) { 
        return foo.fix(y); 
    } 

}
