package com.wdk.util;

import com.wdk.util.gupao.jvm.Test;

/**
 * @Description:
 * @Author:wang_dk
 * @Date:2020-06-11 21:41
 * @Version: v1.0
 **/

public class TestA {
    public TestA(){
        System.out.println("TestA 无参构造");
    }

    public TestA(String name){
        System.out.println("TestA 有参构造 name:"+name);
    }
}

class TestB extends TestA{
    public TestB(){
        //这个地方默认会隐式的调用 父类的无参构造器 super(); 如果父类没有无参构造器 会编译不通过
        System.out.println("TestB的无参构造");
    }

    public TestB(String name){
        super(name);
        System.out.println("TestB的有参构造 name:"+name);
    }
}

class C {
    public static void main(String[] args) {
        TestB tb = new TestB("jack");
    }
}
