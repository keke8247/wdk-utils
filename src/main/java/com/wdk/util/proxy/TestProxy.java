package com.wdk.util.proxy;

import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.FileOutputStream;

public class TestProxy {
	public static void main(String[] args) throws Exception {
		//获得代理类
		Calculator calculator = (Calculator) new CalculatorInvocationHandler(new CalculatorImpl2()).getProxy();

//		byte [] bytes = ProxyGenerator.generateProxyClass("WangDKTestProxy",new Class[]{Calculator.class});
//
//		FileOutputStream fileOutputStream = new FileOutputStream(new File("WangDKTestProxy.class"));
//
//		fileOutputStream.write(bytes);
//		fileOutputStream.close();

		calculator.add(10, 11);
	}
}
