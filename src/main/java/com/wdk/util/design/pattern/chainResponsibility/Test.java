package com.wdk.util.design.pattern.chainResponsibility;

/**
 * @Description
 * 责任链模式 过滤request,和 response内容
 * 请求的时候  正向过滤request   HtmlFilter--->SesitiveFilter
 * 返回的时候  倒叙过滤response  SesitiveFilter--->HtmlFilter
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2018/3/9 13:59
 * @Since version 1.0.0
 */
public class Test {
    public static void main(String[] args) {
        Request request = new Request();
        request.setRequestStr("大家好<哈哈哈>,接下来是敏感字眼");

        Response response = new Response();
        response.setResponseStr("");

        FilterChain filterChain = new FilterChain();
        filterChain.addFilter(new HtmlFilter()).addFilter(new SesitiveFilter());

        filterChain.doFilter(request,response,filterChain);

        System.out.println(request.getRequestStr());

        System.out.println(response.getResponseStr());
    }
}
