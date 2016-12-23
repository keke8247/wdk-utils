package com.wdk.util.design.pattern.proto;

/**
 *	@Description
 *	原型模式:用原型实例指定创建对象的种类,并且通过拷贝这些原型创建新的对象.
 *	其实就是 clone()方法的使用
 *  @author wangdk,wangdk@erongdu.com
 *  @CreatTime 2016年12月9日 下午5:03:28
 *  @since version 1.0.0
 */
public abstract class ProtoType implements Cloneable{
	
	@Override
	public Object clone(){
		Object object = null;
		try {
			object = super.clone();
		} catch (CloneNotSupportedException e) {
			System.out.println("clone失败");
		}
		return object;
	}
}


class TestProtoType extends ProtoType{
	public TestProtoType(){
		
	}
}