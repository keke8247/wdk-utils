package com.wdk.util.design.pattern.proxy;

import org.junit.Test;


public class TestProxy {
	@Test
	public void testProxy() throws Exception{
		//获得代理类
		Calculator calculator = (Calculator) new CalculatorInvocationHandler(new CalculatorImpl()).getProxy();
		
		calculator.add(10, 11);
	}
}
