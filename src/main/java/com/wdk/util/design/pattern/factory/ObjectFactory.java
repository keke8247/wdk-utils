package com.wdk.util.design.pattern.factory;

/**
 *	@Description
 *	工厂模式
 *  @author wangdk,wangdk@erongdu.com
 *  @CreatTime 2016年12月9日 下午5:01:25
 *  @since version 1.0.0
 */
public class ObjectFactory {
	private static ObjectFactory factory;
	
	public static ObjectFactory getInstance(){
		if(null == factory){
			factory = new ObjectFactory();
		}
		return factory;
	}
	
	public Object createObject(String className) throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		Object obj = Class.forName(className).newInstance();
		return obj;
	}
}
