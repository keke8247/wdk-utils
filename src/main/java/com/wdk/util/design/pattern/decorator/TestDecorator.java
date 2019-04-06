package com.wdk.util.design.pattern.decorator;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


import com.wdk.util.design.pattern.factory.one.ObjectFactory;
import org.junit.Test;

public class TestDecorator {
	
	@Test
	public void testDecortor() throws Exception{
		ObjectFactory factory = ObjectFactory.getInstance();
		OtherWork otherWork = (OtherWork) factory.createObject("com.wdk.util.design.pattern.decorator.OtherWork");
		
		List<Method> methodList = new ArrayList<Method>();
		
		methodList.add(otherWork.getClass().getMethod("insertLog", null));
		methodList.add(otherWork.getClass().getMethod("getDate", null));
		
		Decorator decorator = new Decorator(new StringDataWork(), methodList);
		
		decorator.insert();
	}
}
