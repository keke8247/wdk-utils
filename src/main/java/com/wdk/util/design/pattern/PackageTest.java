package com.wdk.util.design.pattern;

import org.testng.annotations.Test;

import com.wdk.util.design.pattern.factory.one.ObjectFactory;
import com.wdk.util.design.pattern.factory.one.TestClass;
import com.wdk.util.design.pattern.single.Singleton;

public class PackageTest {
	
	@Test
	public void testFactory() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		ObjectFactory factory = ObjectFactory.getInstance();
		TestClass test = (TestClass) factory.createObject("com.wdk.util.factory.TestClass");
		test.testMethod();
	}
	
	@Test
	public void testSingleton(){
		for(int i=0;i<100;i++){
			new Thread(new Runnable() {
				public void run() {
					Singleton sigSingleton = Singleton.getInstance();
				}
			}).start();
		}
	}
}
