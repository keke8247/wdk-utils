package com.wdk.util.design.pattern.single;

/**
 *	@Description
 *	单例
 *  @author wangdk,wangdk@erongdu.com
 *  @CreatTime 2016年12月9日 下午5:01:48
 *  @since version 1.0.0
 */
public class Singleton {
	private static Singleton instance;
	
	public Singleton(){
	}

	private static synchronized void syncInit(){
		instance = new Singleton();
	}

	public static  Singleton getInstance(){
		if(null == instance){
			syncInit();
		}

		return instance;
	}

	/* 如果该对象被用于序列化，可以保证对象在序列化前后保持一致 */
	public Object readResolve() {
		return getInstance();
	}

}
