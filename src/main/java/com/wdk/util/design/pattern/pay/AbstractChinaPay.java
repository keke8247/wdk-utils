package com.wdk.util.design.pattern.pay;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractChinaPay implements Pay{

    public static Map<String,AbstractChinaPay> payMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void init(){
        payMap.put(getType(),this);
    }


    @Override
    public void payment(Object params) {
        //校验 封装参数
        checkParams(params);

        //支付
        pay(params);

        //回调支付结果
        callBack();
    }

    public abstract String getType();

    public abstract Object checkParams(Object params);

    public abstract Object pay(Object params);

    public abstract Object callBack();
}
