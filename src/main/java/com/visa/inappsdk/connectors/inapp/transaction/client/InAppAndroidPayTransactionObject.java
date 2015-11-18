package com.visa.inappsdk.connectors.inapp.transaction.client;

import static com.visa.inappsdk.connectors.inapp.transaction.client.InAppTransactionType.SDK_TRANSACTION_ANDROID_PAY;

/**
 * Created by fzubair on 11/18/2015.
 */
final class InAppAndroidPayTransactionObject extends InAppTransactionObject {

    private InAppAndroidPayTransactionObject(Builder builder)
    {
        this.transactionType = builder.transactionType;
        this.merchantReferenceCode = builder.merchantReferenceCode;
        this.transactionTime = builder.transactionTime;
        this.transactionDate = builder.transactionDate;
        this.billTo = builder.billTo;
    }

    public static class Builder extends InAppTransactionObject.Builder{

        public Builder(){
            this.transactionType = SDK_TRANSACTION_ANDROID_PAY;
            this.merchantReferenceCode = Long.toString(System.currentTimeMillis());
        }

        @Override
        public InAppAndroidPayTransactionObject build(){
            return new InAppAndroidPayTransactionObject(this);
        }
    }
}
