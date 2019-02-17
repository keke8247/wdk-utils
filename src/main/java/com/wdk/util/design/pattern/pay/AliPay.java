package com.wdk.util.design.pattern.pay;

import org.springframework.stereotype.Service;

/**
 * 假设是阿里支付
 * 实现阿里支付的具体逻辑即可
 */
@Service
public class AliPay extends AbstractChinaPay{

    @Override
    public String getType() {
        return Constants.ALI_PAY;
    }

    @Override
    public Object checkParams(Object params) {
        return null;
    }

    @Override
    public Object pay(Object params) {
        System.out.println("使用支付寶支付了。。。。。");
        return null;
    }

    @Override
    public Object callBack() {
        return null;
    }
}
