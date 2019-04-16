package com.wdk.util.design.pattern.proxy;

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
