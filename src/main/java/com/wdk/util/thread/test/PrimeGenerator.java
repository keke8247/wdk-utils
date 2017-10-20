package com.wdk.util.thread.test;

/**
 *	@Description
 *	测试  线程中断操作 
 *  @author wangdk,wangdk@erongdu.com
 *  @CreatTime 2017年9月26日 上午11:17:42
 *  @since version 1.0.0
 */
public class PrimeGenerator extends Thread{

	@Override
	public void run(){
		long number = 1;
		
		while(true){ //如果是质数  输出信息
			if(isPrime(number)){
				System.out.printf("Number %d is Prime\n",number);
			}
			
			//检查线程是否被中断
			if(isInterrupted()){ // isInterrupted 是否被中断
				System.out.printf("The Prime Generator has been Interrupted");
				return;
			}
			
			number++;
		}
	}
	
	/**
	 * @description
	 * 判断  数字 是否是质数
	 * @param number
	 * @return
	 * @author wangdk
	 * @return boolean
	 * @since  1.0.0
	*/
	private boolean isPrime(long number){
		if(number <= 2){
			return true;
		}
		
		for(int i=2;i<number;i++){
			if((number % i) == 0){
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		Thread t = new PrimeGenerator();
		t.start();
		
		//线程休眠5秒  中断线程
		
		try {
			t.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		t.interrupt();
	}
}
