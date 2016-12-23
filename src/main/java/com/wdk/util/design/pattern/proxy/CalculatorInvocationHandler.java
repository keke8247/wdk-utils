package com.wdk.util.design.pattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *	@Description
 *	动态代理类,
 *	JDK动态代理只能代理接口,不能代理类
 *	实现动态代理 需要实现 InvocationHandler接口
 *  @author wangdk,wangdk@erongdu.com
 *  @CreatTime 2016年11月7日 下午2:36:08
 *  @since version 1.0.0
 */
public class CalculatorInvocationHandler implements InvocationHandler{

	/*要代理的对象，动态代理只有在运行时才知道代理谁，所以定义为Object类型，可以代理任意对象*/  
	private Object target = null;
	
	private static final String dateFormat = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * CalculatorInvocationHandler.
	 * 通过构造函数 传入需要代理的对象
	 * @param target 需要代理的对象
	 */
	public CalculatorInvocationHandler(Object target){
		this.target = target;
	}
	
	/*InvocationHandler接口的方法，proxy表示代理，method表示原对象被调用的方法，args表示方法的参数*/
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		DateFormat format = new SimpleDateFormat(dateFormat);
		
		//加入切面逻辑, 原对象方法调用前的日志记录
		System.out.println("开始执行"+method.getName()+"方法,时间:"+format.format(new Date()));
		
		//执行方法
		Object  result = method.invoke(target, args);
		
		//加入切面逻辑,原对象方法调用后的日志记录
		System.out.println("结束执行"+method.getName()+"方法,时间:"+format.format(new Date()));
		
		return result;
	}
	
	public Object getProxy(){
		return Proxy.newProxyInstance(this.target.getClass().getClassLoader(), this.target.getClass().getInterfaces(), this);
	}

}
