package com.wdk.util.testSpring.testReplaceMethod;

import java.io.File;


import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.testng.annotations.Test;

/**
 *	@Description
 *  @author wangdk,wangdk@erongdu.com
 *  @CreatTime 2017年2月6日 上午9:06:47
 *  @since version 1.0.0
 */
public class TestMethod {
	
	@Test
	public void TestMethodReplacer(){
		File file = new File("testMethodReplacer-beans.xml");
		
		Resource resource = new FileSystemResource(file);
		
		BeanFactory bf = new XmlBeanFactory(resource);
		
		TestChangeMethod testChangeMethod = (TestChangeMethod) bf.getBean("testChangeMethod");
		
		testChangeMethod.changeMe();
	}
}
