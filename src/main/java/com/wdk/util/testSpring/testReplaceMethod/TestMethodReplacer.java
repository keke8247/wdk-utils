package com.wdk.util.testSpring.testReplaceMethod;

import java.lang.reflect.Method;

import org.springframework.beans.factory.support.MethodReplacer;

public class TestMethodReplacer implements MethodReplacer{

	public Object reimplement(Object obj, Method method, Object[] args)
			throws Throwable {
		System.out.println("替换了原先类中的changeMe方法!");
		return null;
	}

}
