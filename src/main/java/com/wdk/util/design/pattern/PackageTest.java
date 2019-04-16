package com.wdk.util.design.pattern;


import com.wdk.util.design.pattern.factory.one.ObjectFactory;
import com.wdk.util.design.pattern.factory.one.TestClass;
import org.junit.Test;

public class PackageTest {
	
	@Test
	public void testFactory() throws ClassNotFoundException, InstantiationException, IllegalAccessException{
		ObjectFactory factory = ObjectFactory.getInstance();
		TestClass test = (TestClass) factory.createObject("com.wdk.util.factory.TestClass");
		test.testMethod();
	}
}
