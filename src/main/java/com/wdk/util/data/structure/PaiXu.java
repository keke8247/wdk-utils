package com.wdk.util.data.structure;

import java.util.Random;

import org.junit.Test;

public class PaiXu {
	@Test
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
	@Test
	/**
	 * 冒泡排序
	 * */
	public void testTwo(){
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
	@Test
	public  void testThree(){
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
}
