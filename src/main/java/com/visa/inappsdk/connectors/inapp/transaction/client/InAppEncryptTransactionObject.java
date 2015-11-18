package com.visa.inappsdk.connectors.inapp.transaction.client;

import static com.visa.inappsdk.connectors.inapp.transaction.client.InAppTransactionType.SDK_TRANSACTION_ENCRYPTION;

/**
 * Provides data for refund transaction
 * 
 * @author fzubair
 */
final class InAppEncryptTransactionObject extends InAppTransactionObject {

	private InAppEncryptTransactionObject(Builder builder)
	{
		this.transactionType = builder.transactionType;
		this.merchantReferenceCode = builder.merchantReferenceCode;
        this.transactionTime = builder.transactionTime;
        this.transactionDate = builder.transactionDate;
        this.cardData = builder.cardData;
        this.billTo = builder.billTo;
	}

	public static class Builder extends InAppTransactionObject.Builder{

		public Builder(){
            this.transactionType = SDK_TRANSACTION_ENCRYPTION;
            this.merchantReferenceCode = Long.toString(System.currentTimeMillis());
		}

        @Override
		public InAppEncryptTransactionObject build(){
			return new InAppEncryptTransactionObject(this);
		}
	}
}