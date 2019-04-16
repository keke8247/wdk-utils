package com.wdk.util.data.structure;

import org.junit.Test;

import java.util.Random;


public class PaiXu {


	@org.junit.Test
	public void testOne(){
		Random rm = new Random();
		int[][] list = new int[10][10];
		for(int i=0;i<10;i++){
			for(int j=0;j<10;j++){
				list[i][j] = rm.nextInt(500);
				System.out.print("["+list[i][j]+"]");
			}
			System.out.println();
		}
	}
	@org.junit.Test
	/**
	 * 冒泡排序
	 * */
	public static void testTwo(){
		Random rm = new Random();
		int[] list = new int[100];
		int x = 0;
		for(int i=0;i<100;i++){
			list[i] = rm.nextInt(200);
		}
		for(int j=0;j<list.length-1;j++){
			//第一次排序把最大的放到了最后。
			for(int i=0;i<list.length-j-1;i++){
				if(list[i]>list[i+1]){
					x = list[i+1];
					list[i+1] = list[i];
					list[i] = x;
				}
			}
			System.out.println("第"+(j+1)+"次排序结果");
			for(int k=0;k<list.length;k++){
				System.out.print(list[k]+"\t");
			}
			System.out.println();
		}
		System.out.println("最终的排序结果！");
		for (int i = 0; i < list.length; i++) {
			System.out.print(list[i]+"\t");
		}
	}
	
	/**
	 * 插入排序
	 * */
	@org.junit.Test
	public static void testThree(){
		Random rm = new Random();
		int[] list = new int[100];
		int x = 0;
		for(int i=0;i<100;i++){
			list[i] = rm.nextInt(200);
		}
		for(int i=1;i<list.length;i++){
			x = list[i];
			int j = i;
			while((j>0)&&list[j-1]>x){
				list[j] = list[j-1];
				--j;
			}
			list[j]=x;
			
			System.out.println("第"+(i)+"次排序结果");
			for(int k=0;k<list.length;k++){
				System.out.print(list[k]+"\t");
			}
			System.out.println();
		}
		
		System.out.println("最终的排序结果！");
		for(int i=0;i<list.length;i++){
			System.out.print(list[i] +"\t");
		}
	}

	/**
	 * @Description:
	 * 快速排序
	 * @Date 2019/3/26 0026 21:18
	 * @Param a 需要排序的数组   low=0 high=a.length()
	 * @return
	 **/
	public static void kuaisu(int[] a,int low,int high){
		int start = low;
		int end = high;
		int key = a[low];


		int i= 0;
		while(end>start){
			//从后往前比较
			while(end>start&&a[end]>=key)  //如果没有比关键值小的，比较下一个，直到有比关键值小的交换位置，然后又从前往后比较
				end--;
			if(a[end]<=key){
				int temp = a[end];
				a[end] = a[start];
				a[start] = temp;
			}
			//从前往后比较
			while(end>start&&a[start]<=key)//如果没有比关键值大的，比较下一个，直到有比关键值大的交换位置
				start++;
			if(a[start]>=key){
				int temp = a[start];
				a[start] = a[end];
				a[end] = temp;
			}
			//此时第一次循环比较结束，关键值的位置已经确定了。左边的值都比关键值小，右边的值都比关键值大，但是两边的顺序还有可能是不一样的，进行下面的递归调用

			i++;
			System.out.println("第"+(i)+"次遍历结果");

			for (int k=0;k<a.length;k++){
				System.out.print(a[k]+" ");
			}

			System.out.println();

			System.out.println("start="+start +" end=" + end);
		}
		//递归
		if(start>low) kuaisu(a,low,start-1);//左边序列。第一个索引位置到关键值索引-1
		if(end<high) kuaisu(a,end+1,high);//右边序列。从关键值索引+1到最后一个
	}
	
	public static void main(String[] args) {
//		testTwo();
		testThree();
	}
}
