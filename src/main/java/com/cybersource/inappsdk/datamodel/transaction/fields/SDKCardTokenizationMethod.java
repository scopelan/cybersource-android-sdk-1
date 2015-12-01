package com.cybersource.inappsdk.datamodel.transaction.fields;

/**
 *<ul>
 * <li>ANDROID_PAY - "android_pay"</li>
 *</ul>
 *
 * Created by fzubair on 10/22/2015.
 */
public enum SDKCardTokenizationMethod {

    ANDROID_PAY("android_pay");

    private String tokenizationMethon;
    private SDKCardTokenizationMethod(String method) {
        this.tokenizationMethon = method;
    }

    @Override
    public String toString(){
        return tokenizationMethon;
    }
}
