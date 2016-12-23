package com.wdk.util.design.pattern.decorator;

/**
 *	@Description
 *	字符串数据类型工作器
 *  @author wangdk,wangdk@erongdu.com
 *  @CreatTime 2016年12月12日 上午11:33:19
 *  @since version 1.0.0
 */
public class StringDataWork implements Work{

	public void insert() {
		System.out.println("写入一个字符串!");
	}
	
	//如果在执行 insert方法之前 想做一些前置操作  比如记录日志信息, 就要可预见的扩展StringDataWork方式.采用 decorator模式可以动态的前置这些操作.
}
