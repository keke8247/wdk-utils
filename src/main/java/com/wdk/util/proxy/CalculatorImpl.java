package com.wdk.util.proxy;

/**
 *	@Description
 *	计算器接口实现类
 *	如果要监听每个方法执行时间,以及记录日志,不使用动态代理做切面逻辑的情况下,需要在每个函数开始 和 结束时候记录时间. 例如注释部分
 *	把日志记录做倒切面逻辑动态代理类.实现动态代理
 *  @author wangdk,wangdk@erongdu.com
 *  @CreatTime 2016年11月7日 下午2:29:31
 *  @since version 1.0.0
 */
public class CalculatorImpl implements Calculator{

//	public double add(double num1, double num2) throws Exception {
//		System.out.println("开始执行,时间:"+ new Date());
//		double result = num1+num2;
//		System.out.println("结束执行,时间:"+ new Date());
//		return result;
//	}
	
	public double add(double num1, double num2) throws Exception {
		return num1+num2;
	}

	public double subtract(double num1, double num2) throws Exception {
		return num1-num2;
	}

	public double divide(double num1, double num2) throws Exception {
		return num1/num2;
	}

	public double multiply(double num1, double num2) throws Exception {
		return num1*num2;
	}

}
