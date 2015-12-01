package com.cybersource.inappsdk.datamodel.transaction.fields;

/**
 *     /**
 * Describes the type of Token PAN or token
 * Created by fzubair on 11/3/2015.
 */
public enum SDKCardAccountNumberType {
    PAN("PAN"), TOKEN("token");

    private String cardAccountType;
    private SDKCardAccountNumberType(String type) {
        this.cardAccountType = type;
    }

    @Override
    public String toString(){
        return cardAccountType;
    }
}
