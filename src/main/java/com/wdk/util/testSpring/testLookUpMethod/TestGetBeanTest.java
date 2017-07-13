package com.wdk.util.testSpring.testLookUpMethod;

import java.io.File;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

public class TestGetBeanTest {
	
	@Test
	public void testGetBean(){
		File file = new File("testLookupMethod-beans.xml");
		
		Resource resource = new FileSystemResource(file);
		
		BeanFactory bf = new XmlBeanFactory(resource);
		
//		ApplicationContext ac = new ClassPathXmlApplicationContext("context-beans.xml");
		
//		GetBeanTest gbt = (GetBeanTest) bf.getBean("getBeanTest");
		
		Teacher teacher = (Teacher) bf.getBean("teacher");
		
		teacher.showMe();
	}
}
