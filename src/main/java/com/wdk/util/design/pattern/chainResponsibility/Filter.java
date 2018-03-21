package com.wdk.util.design.pattern.chainResponsibility;

/**
 * @Description
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2018/3/9 13:47
 * @Since version 1.0.0
 */
public interface Filter {
    void doFilter(Request request,Response response,FilterChain chain);
}
