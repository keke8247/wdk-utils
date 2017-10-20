package com.wdk.util.thread.test;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Thread.State;

import org.apache.log4j.Logger;

public class Calculator implements Runnable{
	
	private static Logger log = Logger.getLogger(Calculator.class);

	private int number;
	
	public Calculator(int number){
		this.number = number;
	}
	
	@Override
	public void run() {
		for(int i=1;i<10;i++){
			System.out.printf("%s : %d * %d = %d\n", Thread.currentThread().getName(),number,i,number*i);
		}
	}
	
	
	private static void writeThreadInfo(PrintWriter pw, Thread thread, State state) {
	   pw.printf("Main : Id %d - %s\n",thread.getId(),thread.getName());
	   pw.printf("Main : Priority: %d\n",thread.getPriority());
	   pw.printf("Main : Old State: %s\n",state);
	   pw.printf("Main : New State: %s\n",thread.getState());
	   pw.printf("Main : ************************************\n");
	}

	
	public static void main(String[] args) {
		Thread[] thread = new Thread[10];
		Thread.State[] state = new Thread.State[10];
		
		for(int i=0;i<10;i++){
			Calculator calculator = new Calculator(i);
			thread[i] = new Thread(calculator);
			if(i%2 == 0){
				thread[i].setPriority(Thread.MAX_PRIORITY);
			}else{
				thread[i].setPriority(Thread.MIN_PRIORITY);
			}
			thread[i].setName("Thread--"+i);
		}
		try {
			FileWriter fileWriter = new FileWriter("G://log.txt",true);
			PrintWriter printWriter = new PrintWriter(fileWriter);
			
			for(int i=0;i<10;i++){
				  
				printWriter.printf("Main : Status of Thread "+i+" : " +thread[i].getState());
				
				state[i] = thread[i].getState();
			}
			
			for(int i=0;i<10;i++){
				thread[i].start();
			}
			
			boolean isfinish = false;
			
			while(!isfinish){
				for(int i=0;i<10;i++){
					if(thread[i].getState() != state[i]){
						//写入文件
						writeThreadInfo(printWriter,thread[i],state[i]);
						state[i] = thread[i].getState();
					}
				}
				
				isfinish = true;
				
				for(int i=0;i<10;i++){
					isfinish = isfinish && (thread[i].getState() == State.TERMINATED);
				}
			}
			
			printWriter.flush();
			printWriter.close();
			
			System.out.println("~~~~~~~~~~~");
		} catch (IOException e) {
			log.error("创建fileWriter失败!!!!");
			e.printStackTrace();
		}
	}
}
