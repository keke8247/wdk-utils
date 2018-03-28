package com.wdk.util.readSrc;

import java.lang.reflect.Field;

/**
 * @Description
 * Integer类的方法测试
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2018/3/22 9:54
 * @Since version 1.0.0
 */
public class TestInteger {
    public static void testToString(){
        Integer i = 999999999;

        //radix 基数(进制)  把i转换为2进制的字符串
        String str = Integer.toString(i,2);

        System.out.println(str);
    }


    public static void testIntegerCache(){
        //Integer 缓存了 -128--127

        //i1 i2 都是直接从缓存里取的  所有 i1 == i2
        Integer i1 = Integer.valueOf("100");
        Integer i2 = Integer.valueOf("100");
        System.out.println(i1 == i2); //true

        // 140不在缓存中  从新new的  所以 i3 != i4
        Integer i3 = Integer.valueOf("140");
        Integer i4 = Integer.valueOf("140");
        System.out.println(i3 == i4); //false
    }

    public static void testGetInteger(){
        System.out.println(Integer.decode("123"));
    }

    public static void testRotate(){
        Integer i = new Integer(124);

        System.out.println(Integer.rotateLeft(i,3)); //左移三位   等同于 乘以8
    }


    public static void testSwap(){
        Integer a = 1;
        Integer b = 2;

        System.out.println("a="+a+" b="+b);
        //调用swap 的a,b 为实参.用于调用时传递给方法的参数.在调用方法之前需要被赋值.
        swap1(a,b);
        System.out.println("a="+a+" b="+b);

        //swap2 不能交换成功
        swap2(a,b);
        System.out.println("a="+a+" b="+b);

        //swap3 可以交换成功
//        swap3(a,b);
//        System.out.println("a="+a+" b="+b);

        //结论: 为什么 field.set(numb, tmp) 会执行 Integer.valueOf() 而 field.set(numb, new Integer(tmp)) 不会执行。
        //这就是Integer的装箱操作，当 给 Integer.value 赋值 int时，JVM 检测到 int不是Integer类型,需要装箱，
        // 才执行了Integer.valueOf()方法。而field.set(numb, new Integer(tmp)) 设置的 是Integer类型了，就不会再拆箱后再装箱。
    }

    // numa numb 形参,定义方法时使用的参数,用来接收调用者传递参数的.
    // 形参只有在方法被调用的时候，虚拟机才会分配内存单元，在方法调用结束之后便会释放所分配的内存单元。
    // 因此,形参只在方法内部有效，所以针对引用对象的改动也无法影响到方法外。
    public static void swap1(Integer numa,Integer numb){
        Integer tmp = numa;
        numa = numb;
        numb = tmp;
        System.out.println("numa="+numa+" numb="+numb);
    }

    public static void swap2(Integer numa,Integer numb){
        int tmp = numa.intValue();
        try {
            Field field = Integer.class.getDeclaredField("value");
            field.setAccessible(true);
            field.set(numa,numb);
            field.set(numb,tmp);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("numa="+numa+" numb="+numb);
    }

    public static void swap3(Integer numa,Integer numb){
        int tmp = numa.intValue();
        try {
            Field field = Integer.class.getDeclaredField("value");
            field.setAccessible(true);
            field.set(numa,numb);
            field.set(numb,new Integer(tmp));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("numa="+numa+" numb="+numb);
    }

    public static void main(String[] args) {
        testSwap();
    }
}
