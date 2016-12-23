package com.wdk.util.proxy;

public class TestProxy {
	public static void main(String[] args) throws Exception {
		//获得代理类
		Calculator calculator = (Calculator) new CalculatorInvocationHandler(new CalculatorImpl()).getProxy();
		
		calculator.add(10, 11);
	}
}
