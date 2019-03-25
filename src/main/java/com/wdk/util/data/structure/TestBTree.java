package com.wdk.util.data.structure;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 *	@Description
 *	验证B-Tree的查询复杂度O(n)
 *  @author wangdk,wangdk@erongdu.com
 *  @CreatTime 2017年9月12日 下午4:36:08
 *  @since version 1.0.0
 */
public class TestBTree {
	private static List<Integer> list = new ArrayList<Integer>();
	private static int[] intArray = {6,3,7,9,1,8,10};
//	private static int[] intArray = new  int[100];
	
	static{
		for(int i=0;i<100;i++){
			int j = new Random().nextInt(500);
			list.add(j);
//			intArray[i] = j;
		}
	}
	
	private static void writeOutMap(){
		Iterator<Integer> iterator = list.iterator();
		while(iterator.hasNext()){
			int key = iterator.next();
			System.out.println(key);
		}
	}
	
	private static void mp(){
		int x;
		for(int i=0;i<intArray.length;i++){
			for(int j=0;j<intArray.length-i-1;j++){
				if(intArray[j]>intArray[j+1]){
					x = intArray[j+1];
					intArray[j+1] = intArray[j];
					intArray[j] = x;
				}
			}
			System.out.println("第"+(i+1)+"次排序结果");
			for(int k=0;k<intArray.length;k++){
				System.out.print(intArray[k]+"\t");
			}

			
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		mp();
		
	}
}
