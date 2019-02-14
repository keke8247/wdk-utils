package com.wdk.util.design.pattern.single;

/**
 * @Description
 * 饿汉模式
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2019/2/14 11:20
 * @Since version 1.0.0
 */
public class SingleInstance02 {
    private static SingleInstance02 singleInstance02 = new SingleInstance02();

    /**
     * @Description:
     * 私有构造函数 防止其他地方创建实例
     */
    private SingleInstance02(){

    }

    //饿汉模式  不存在线程安全问题  在使用该类的时候就会创建实例  缺点:如果只是用到这个类 没有用到该方法也会创建实例
    public static SingleInstance02 getSingleInstance02(){
        return singleInstance02;
    }
}

/**
 * @Description
 * 饿汉模式2
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2019/2/14 11:20
 * @Since version 1.0.0
 */
class SingleInstance03{
    private static class InstanceHolder{
        private static SingleInstance03 singleInstance03 = new SingleInstance03();
    }

    private SingleInstance03(){

    }

    //饿汉模式  不存在线程安全问题  只有在使用该方法的时候 才会加载内部类InstanceHolder 才会创建实例.不会造成资源浪费.
    public static SingleInstance03 getSingleInstance02(){
        return InstanceHolder.singleInstance03;
    }
}
