package com.wdk.util.proxy;

/**
 *	@Description
 *	计算器接口
 *  @author wangdk,wangdk@erongdu.com
 *  @CreatTime 2016年11月7日 下午2:25:28
 *  @since version 1.0.0
 */
public interface Calculator {
	double add(double num1,double num2) throws Exception; //加
	double subtract(double num1,double num2) throws Exception; //减
	double divide(double num1,double num2) throws Exception; //除
	double multiply(double num1,double num2) throws Exception; //乘
}
