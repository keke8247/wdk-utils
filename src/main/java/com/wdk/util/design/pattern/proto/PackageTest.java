package com.wdk.util.design.pattern.proto;

import org.junit.Test;

public class PackageTest {
	
	@Test
	public void testProtoTye(){
		ProtoType tpt = new TestProtoType();
		
		ProtoType tpt2 = (ProtoType) tpt.clone();
		
		System.out.println(tpt.getClass());
		System.out.println(tpt2.getClass());
	}
}
