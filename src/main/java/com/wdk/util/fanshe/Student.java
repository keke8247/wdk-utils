package com.wdk.util.fanshe;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


public class Student extends Person<Student,String>{
	public static void main(String[] args) {
		Student student = new Student();
		
		Class clazz = student.getClass();
		
		System.out.println(clazz.getSuperclass()); //获取该类的父类.
		
		//getGenericSuperclass 获取带有泛型的父类.
		//Type是 Java 编程语言中所有类型的公共高级接口。它们包括原始类型、参数化类型、数组类型、类型变量和基本类型。
		Type type=clazz.getGenericSuperclass();
		
		System.out.println(type);
		
		//ParameterizedType参数化类型，即泛型
		ParameterizedType parameterizedType = (ParameterizedType) type;
		
		Type[] typs = parameterizedType.getActualTypeArguments();
		
		for(Type t : typs){
			Class c = (Class) t;
			System.out.println(c);
		}
		
	}
	
}


