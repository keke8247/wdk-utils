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
		System.out.println(Math.random());
	}
	
	public static  Singleton getInstance(){
		synchronized (Singleton.class) {
			if(null == instance){
				instance = new Singleton();
			}
			return instance;
		}
	}
}
