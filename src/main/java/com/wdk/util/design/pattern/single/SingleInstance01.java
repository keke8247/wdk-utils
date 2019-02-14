package com.wdk.util.design.pattern.single;

/**
 * @Description
 * 线程安全懒汉式单例模式
 * 懒汉:需要使用的时候才创建实例
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2019/2/14 11:07
 * @Since version 1.0.0
 */
public class SingleInstance01 {

    private static SingleInstance01 singleInstance = null;

    /**
     * @Description:
     * 私有构造函数 防止其他地方创建实例
    */
    private SingleInstance01(){

    }

    //此方法 单线程情况下 没任何问题 多线程并发执行的时候 不能保证单例
    public static SingleInstance01 getSingleInstance1(){
        if(null == singleInstance){
            singleInstance = new SingleInstance01();
        }
        return singleInstance;
    }

    //此方法 多线程情况下 也可以保证单例 缺点是:每次进来都会先抢占锁资源,效率不高
    public SingleInstance01 getSingleInstance2(){
        synchronized (SingleInstance01.class){
            if (null == singleInstance){
                singleInstance = new SingleInstance01();
            }
            return singleInstance;
        }
    }

    //double checking 双重验证
    //此方法 只在第一次创建的时候 抢夺锁资源 创建实例  后续再获取实例的时候 不需要抢占锁资源  兼顾性能 也能保证线程安全
    public SingleInstance01 getSingleInstance3(){
        if(null == singleInstance){
            synchronized (SingleInstance01.class){
                if(null == singleInstance){
                    singleInstance = new SingleInstance01();
                }
            }
        }
        return singleInstance;
    }
}
