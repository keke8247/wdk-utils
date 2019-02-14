package com.wdk.util.design.pattern.chainResponsibility;

/**
 * @Description
 * 敏感字过滤器
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2018/3/9 13:56
 * @Since version 1.0.0
 */
public class SesitiveFilter implements Filter{
    public void doFilter(Request request, Response response,FilterChain chain) {
        request.requestStr = request.getRequestStr().replace("敏感","") + "~SesitiveFilterDone~~~";
        chain.doFilter(request,response,chain);
        response.responseStr = response.getResponseStr()+"SesitiveFilter~~~";
    }
}
