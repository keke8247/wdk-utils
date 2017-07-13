/**
 * zoneland.net Inc.
 * Copyright (c) 2002-2013 All Rights Reserved.
 */
package com.wdk.util;

import java.util.HashMap;
import java.util.Map;

import org.dozer.DozerBeanMapper;

/**
 * 对象复杂工具类
 * @author pengyuyong
 * @version $Id: ConvertObject.java, v 0.1 2013-1-23 下午04:02:42 pengyuyong Exp $
 */
public class ConvertObject {
	 /** The mapper. */
    private static DozerBeanMapper mapper;

    /**
     * 对象之间的属性拷贝.
     *
     * @param orig 源对象
     * @param dest 目标对象
     */
    public static void origToDest(final Object orig, final Object dest) {
        mapper.map(orig, dest);
    }

	public DozerBeanMapper getMapper() {
		return mapper;
	}

	public static void setMapper(DozerBeanMapper mapper) {
		ConvertObject.mapper = mapper;
	}


	public static void main(String[] args) {
		Map<String,Object> map1 = new HashMap<String, Object>();
		map1.put("test", "testValue");
		
		Map<String,Object> map2 = new HashMap<String,Object>();
		
		setMapper(new DozerBeanMapper());
		
		origToDest(map1, map2);
		
		System.out.println(map2.size());
	}
	
}
