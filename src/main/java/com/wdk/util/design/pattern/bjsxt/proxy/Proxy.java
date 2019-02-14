package com.wdk.util.design.pattern.bjsxt.proxy;

/**
 * @Description
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2018/3/27 17:35
 * @Since version 1.0.0
 */
public class Proxy {
    public static Object newProxyInstance(){
        String str =
        "package com.wdk.util.design.pattern.bjsxt.proxy;" +

        "public class TankTimeProxy implements Moveable{" +
                "private Moveable t;" +

                "public TankTimeProxy(Moveable t) {" +
                "this.t = t;" +
                "}" +

                "public void move() throws InterruptedException {" +
                "Long start = System.currentTimeMillis();" +
                "t.move();" +
                "Long end = System.currentTimeMillis();" +
                "System.out.println(\"time:\"+(end-start));" +
                "}" +
        "}" ;

        return null;
    }
}
