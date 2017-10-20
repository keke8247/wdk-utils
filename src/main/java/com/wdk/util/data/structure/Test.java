package com.wdk.util.data.structure;

import java.util.Random;


public class Test {
	static int [] intArray ;
	
	static{
		intArray = new int[100];
		
		Random random = new Random();
		
		for(int i=0;i<100;i++){
			intArray[i] = random.nextInt(300);
		}
		
		System.out.println("排序前的int数组为:");
		for(int k=0;k<intArray.length;k++){
			System.out.print(intArray[k]+"\t");
		}
		
	}
	
	
	private static void testCR(){ //插入排序
		System.out.println("开始插入排序:");
		
		int x = 0; //排序过渡中间数
		
		for(int m=1;m<intArray.length;m++){ //从第二个元素开始比
			x = intArray[m]; //记录中间数
			
			int n = m;
			
			//用后一个和前一个开始比.如果后一个<前一个   前后值交换 
			while(n>0 && intArray[n-1]>x){
				intArray[n] = intArray[n-1];
				n--;
			}
			
			intArray[n] = x;
			
			System.out.println("第"+(m)+"次排序的结果为:");
			
			for(int k=0;k<intArray.length;k++){
				System.out.print(intArray[k]+"\t");
			}
			
			System.out.println();
		}
	}
	
	private static void mp(){
		System.out.println("开始冒泡排序:");
		
		int x = 0;
		
		//第m次排序  把第m大的数  放到第length-m处
		for(int m=0;m<intArray.length-1;m++){
			
			for(int n=0;n<intArray.length-m-1;n++){ //排序好的部分不需要再次循环排序
				if(intArray[n]>intArray[n+1]){
					x = intArray[n+1];
					intArray[n+1] = intArray[n];
					intArray[n] = x;
				}
			}
			
			System.out.println("第"+(m)+"次排序的结果为:");
			
			for(int k=0;k<intArray.length;k++){
				System.out.print(intArray[k]+"\t");
			}
			
			System.out.println();
			
		}
	}
	
	
	public static void main(String[] args) {
		testCR();
		mp();
	}
}
