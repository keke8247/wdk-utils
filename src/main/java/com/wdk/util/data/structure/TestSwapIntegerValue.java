package com.wdk.util.data.structure;

import java.lang.reflect.Field;

/**
 *	@Description
 *	交换Integer的值   形参和实参的思考
 *	参考:
 *		https://dailycast.github.io/Java-%E5%BD%A2%E5%8F%82%E4%B8%8E%E5%AE%9E%E5%8F%82/?hmsr=toutiao.io&utm_medium=toutiao.io&utm_source=toutiao.io
 *  @author wangdk,wangdk@erongdu.com
 *  @CreatTime 2017年11月22日 上午9:32:53
 *  @since version 1.0.0
 */
public class TestSwapIntegerValue {
	public static void main(String[] args) {
		Integer a = 1;
		Integer b = 2;
		
		System.out.println("a="+a+"  b="+b);
		
//		swap(a,b);//交换 a b的值
		swap1(a,b);
		
		System.out.println("a="+a+"  b="+b);
	}
	
	public static void swap(Integer numa,Integer numb){	//此时改变的只是形参  numa  numb  作用于方法内部
		Integer tmp = numa;
		
		numa = numb;
		
		numb = tmp;
	}
	
	public static void swap1(Integer numa,Integer numb){ //Integer包装类  拆箱装箱操作.
		int tmp = numa.intValue();
		
		try {
			Field field = Integer.class.getDeclaredField("value");
			field.setAccessible(true);
			field.set(numa, numb);
			field.set(numb, new Integer(tmp));
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
