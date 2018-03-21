package com.wdk.util.design.pattern.chainResponsibility;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * Filter链  用于存储FilterList
 * @Author wangdk, wangdk@erongdu.com
 * @CreatTime 2018/3/9 14:01
 * @Since version 1.0.0
 */
public class FilterChain implements Filter{

    List<Filter> filters = new ArrayList<Filter>();

    int index = 0;

    public FilterChain addFilter(Filter filter){
        filters.add(filter);
        return this;
    }

    public void doFilter(Request request, Response response,FilterChain chain) {
        if (index == filters.size()){return;}

        Filter filter = filters.get(index);

        index++;

        filter.doFilter(request,response,chain);
    }
}
