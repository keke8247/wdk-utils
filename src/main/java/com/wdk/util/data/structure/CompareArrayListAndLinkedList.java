package com.wdk.util.data.structure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 *	@Description
 *	比较 ArrayList 和 LinkedList
 * 	 1.ArrayList是实现了基于动态数组的数据结构，LinkedList基于链表的数据结构。 
     2.对于随机访问get和set，ArrayList觉得优于LinkedList，因为LinkedList要移动指针。 
     3.对于新增和删除操作add和remove，LinedList比较占优势，因为ArrayList要移动数据。
 *  @author wangdk,wangdk@erongdu.com
 *  @CreatTime 2017年9月5日 下午5:34:12
 *  @since version 1.0.0
 */
public class CompareArrayListAndLinkedList {

	private static final Integer num = 50000;
	
	private static List values;
	
	static{
		Integer vals[]=new Integer[num];   
		  
        Random r=new Random();   
 
        for(int i=0,currval=0;i<num;i++){   
            vals[i]= new Integer(currval);   
            currval+=r.nextInt(100)+1;   
        }   
 
        values=Arrays.asList(vals);   
	}
	
	/**
	 * @description
	 * 测试查找
	 * @author wangdk
	 * @return void
	 * @since  1.0.0
	*/
	private static long testSearch(List list){
		long start=System.currentTimeMillis();   
        for(int i=0;i<num;i++){   
            int index=Collections.binarySearch(list, values.get(i));   
            if(index!=i)   
                System.out.println("***错误***");   
        }   
        return System.currentTimeMillis()-start;   
	}
	
	private static long testAdd(List list){
		long start=System.currentTimeMillis();   
	     Object o = new Object();   
	     for(int i=0;i<num;i++)   
	         list.add(0, o);   
	     return System.currentTimeMillis()-start;   
	}
	
	public static void main(String[] args) {
		System.out.println("ArrayList查询消耗时间："+testSearch(new ArrayList(values)));   
        System.out.println("LinkedList查询消耗时间："+testSearch(new LinkedList(values)));   
        
        System.out.println("ArrayList添加消耗时间："+testAdd(new ArrayList()));   
        System.out.println("LinkedList添加消耗时间："+testAdd(new LinkedList()));   
	}
}
