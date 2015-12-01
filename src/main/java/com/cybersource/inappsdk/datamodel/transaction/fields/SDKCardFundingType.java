package com.cybersource.inappsdk.datamodel.transaction.fields;

/**
 * Enumeration used to indicate the card funding type. Possible states:
 * <ul>
 * <li>CREDIT - "credit"</li>
 * <li>DEBIT - "debit"</li>
 * <li>PREPAID - "prepaid"</li>
 * <li>UNKNOWN - "unknown"</li>
 * </ul>
 *
 * Created by fzubair on 10/22/2015.
 */
public enum SDKCardFundingType {

    CREDIT("credit"), DEBIT("debit"), PREPAID("prepaid"), UNKNOWN("unknown");

    private String fundingType;
    private SDKCardFundingType(String type) {
        this.fundingType = type;
    }

    @Override
    public String toString(){
        return fundingType;
    }

}
