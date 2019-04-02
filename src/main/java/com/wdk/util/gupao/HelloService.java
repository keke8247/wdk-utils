package com.wdk.util.gupao;

import com.google.common.util.concurrent.RateLimiter;

/**
 * @Description
 * 接口层面的限流 每秒QPS 20
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2019/1/18 15:55
 * @Since version 1.0.0
 */
public class HelloService {
    RateLimiter rateLimiter = RateLimiter.create(10);

    public void doRequest(){
        if(rateLimiter.tryAcquire()){
            System.out.println("请求成功");
        }else {
            System.out.println("请求数过多,请稍后重试");
        }
    }
}
