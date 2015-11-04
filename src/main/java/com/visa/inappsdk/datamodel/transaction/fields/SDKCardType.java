package com.visa.inappsdk.datamodel.transaction.fields;

/**
 *     /**
 * Describes the type of Token PAN or token
 * Created by fzubair on 11/3/2015.
 */
public enum SDKCardType {
    PAN("PAN"), TOKEN("token");

    private String cardType;
    private SDKCardType(String type) {
        this.cardType = type;
    }

    @Override
    public String toString(){
        return cardType;
    }
}
