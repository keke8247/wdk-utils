package com.wdk.util.design.pattern.pay;
/**
 * 顶层的支付接口 所有的支付都需要实现该接口 （国内、国外）
 * */
public interface Pay {
    void payment(Object params);
}
