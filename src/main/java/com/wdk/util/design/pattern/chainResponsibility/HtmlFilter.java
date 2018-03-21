package com.wdk.util.design.pattern.chainResponsibility;

/**
 * @Description
 * Html标签过滤器
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2018/3/9 13:51
 * @Since version 1.0.0
 */
public class HtmlFilter implements Filter{
    public void doFilter(Request request, Response response,FilterChain chain) {
        //处理Html标签
        request.requestStr= request.getRequestStr().replace("<", "{")
                .replace(">", "}") + "~HtmlFilterDone~~~";

        chain.doFilter(request,response,chain);

        response.responseStr = response.getResponseStr()+"HtmlFilter~~~";
    }
}
