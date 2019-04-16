package com.wdk.util.design.pattern.proxy;

<<<<<<< HEAD
=======

import org.junit.Test;
>>>>>>> d4c3c1ca74b4ff97c00dafb0946a55f57a537f9b

import org.junit.Test;
import java.lang.reflect.Proxy;


public class TestProxy {
	@Test
	public void testProxy() throws Exception{
		//获得代理类
//		Calculator calculator = (Calculator) new CalculatorInvocationHandler(new CalculatorImpl()).getProxy();

		Calculator calculator = new CalculatorImpl();

		Calculator c1 = (Calculator) Proxy.newProxyInstance(calculator.getClass().getClassLoader(), new Class[]{Calculator.class},new CalculatorInvocationHandler(new CalculatorImpl()));

		c1.add(10, 11);
	}
}
