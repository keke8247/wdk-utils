package com.wdk.util.design.pattern.decorator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Decorator implements Work{

	public  Work work;
	
	public  List<Method> otherMethodList = new ArrayList<Method>();
	
	public Decorator(Work work,List<Method> methodList){
		this.work = work;
		otherMethodList.addAll(methodList);
	}
	
	public void insert() {
		try {
			otherMethod();
		} catch (Exception e) {
			System.out.println("出错了!!!!!!!!!!");
			e.printStackTrace();
		}
		work.insert();
	}

	public void otherMethod() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		ListIterator listIterator = otherMethodList.listIterator();
		
		while(listIterator.hasNext()){
			Method method = (Method) listIterator.next();
			method.invoke(null,null); //执行静态方法!!!!
			
			System.out.println(method.getName()+":正在执行......");
		}
	}
}
