package com.wdk.util.design.pattern.pay;

/**
 * 假设是阿里支付
 * 实现阿里支付的具体逻辑即可
 */
public class AliPay extends AbstractChinaPay{

    @Override
    public Object checkParams(Object params) {
        return null;
    }

    @Override
    public Object pay(Object params) {
        return null;
    }

    @Override
    public Object callBack() {
        return null;
    }
}
