package com.wdk.util.design.pattern.pay;

import org.springframework.stereotype.Service;

/**
 * 微信支付具体逻辑
 */
@Service
public class WechatPay extends AbstractChinaPay{

    @Override
    public String getType() {
        return Constants.WECHAT_PAY;
    }

    @Override
    public Object checkParams(Object params) {
        return null;
    }

    @Override
    public Object pay(Object params) {
        System.out.println("使用微信支付了。。。。。");
        return null;
    }

    @Override
    public Object callBack() {
        return null;
    }
}
