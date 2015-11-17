package com.visa.inappsdk.datamodel.transaction;

import static com.visa.inappsdk.datamodel.transaction.SDKTransactionType.SDK_TRANSACTION_ENCRYPTION;

/**
 * Provides data for refund transaction
 * 
 * @author fzubair
 */
final class SDKEncryptTransactionObject extends SDKTransactionObject {

	private SDKEncryptTransactionObject(Builder builder)
	{
		this.transactionType = builder.transactionType;
		this.merchantReferenceCode = builder.merchantReferenceCode;
        this.transactionTime = builder.transactionTime;
        this.transactionDate = builder.transactionDate;
        this.cardData = builder.cardData;
        this.billTo = builder.billTo;
	}

	public static class Builder extends SDKTransactionObject.Builder{

		public Builder(){
            this.transactionType = SDK_TRANSACTION_ENCRYPTION;
            this.merchantReferenceCode = Long.toString(System.currentTimeMillis());
		}

        @Override
		public SDKEncryptTransactionObject build(){
			return new SDKEncryptTransactionObject(this);
		}
	}
}