package com.wdk.util.design.pattern.pay;

public abstract class AbstractChinaPay implements Pay{
    @Override
    public void payment(Object params) {
        //校验 封装参数
        checkParams(params);

        //支付
        pay(params);

        //回调支付结果
        callBack();
    }

    public abstract Object checkParams(Object params);

    public abstract Object pay(Object params);

    public abstract Object callBack();
}
