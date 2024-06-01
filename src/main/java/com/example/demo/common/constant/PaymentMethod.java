package com.example.demo.common.constant;

public enum PaymentMethod {
    WECHAT_PAY("微信支付"),
    ALIPAY("支付宝支付"),
    CREDIT_CARD("信用卡支付");

    private final String description;

    PaymentMethod(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
