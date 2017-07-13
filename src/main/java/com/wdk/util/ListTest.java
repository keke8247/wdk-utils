package com.wdk.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ListTest {

	/**
	 * @description
	 * 合并list  去重
	 * @author wangdk
	 * @return void
	 * @since  1.0.0
	*/
	public static void listQc(){
		List<String> list1 = new ArrayList<String>();
		list1.add("1");
		list1.add("2");
		list1.add("4");
		list1.add("6");
		
		System.out.println(list1);
		
		
		List<String> list2 = new ArrayList<String>();
		list2.add("1");
		list2.add("2");
		list2.add("4");
		list2.add("7");
		
		System.out.println(list2);
		
		List<String> allList = new ArrayList<String>();
		allList.addAll(list1);
		allList.removeAll(list2);
		allList.addAll(list2);
		
		System.out.println(allList);
	}
	
	/**
	 * @description
	 * 合并list 去重
	 * @author wangdk
	 * @return void
	 * @since  1.0.0
	*/
	private static void listObjectQc(){
		List<Object> list1 = new ArrayList<Object>();
		List<Object> list2 = new ArrayList<Object>();
		
		//如果需要保持原来顺序  可以使用LinkedHashSet
		Set<Object> set = new HashSet<Object>();
		set.addAll(list1);
		set.addAll(list2);
		
		List<Object> list3 = new ArrayList<Object>(set);
		
	}

	public static void main(String[] args) {
		listQc();
	}
}
