package com.visa.inappsdk.connectors.inapp.transaction.client;

import com.visa.inappsdk.datamodel.transaction.fields.SDKBillTo;
import com.visa.inappsdk.datamodel.transaction.fields.SDKCardData;

/**
 * This class represents a transaction that is sent to a reader and then received back from it.
 * 
 * @author fzubair
 */
public abstract class InAppTransactionObject {

	protected String merchantReferenceCode;
	protected InAppTransactionType transactionType;
	protected String transactionTime;
	protected String transactionDate;
	protected SDKCardData cardData;
	protected SDKBillTo billTo;

	InAppTransactionObject(Builder builder) {
	}

    InAppTransactionObject() {
    }

	public String getMerchantReferenceCode() {
		return merchantReferenceCode;
	}

	public final String getTransactionTime() {
		return transactionTime;
	}

	public final String getTransactionDate() {
		return transactionDate;
	}

	public SDKCardData getCardData() {
		return cardData;
	}

	public SDKBillTo getBillTo() {
		return billTo;
	}

	public InAppTransactionType getTransactionType() {
		return transactionType;
	}

	/**
	 * A factory method for creating proper transaction object.
	 * 
	 * @param type transaction type
	 * @return one of transaction objects
	 */
	public static InAppTransactionObject.Builder createTransactionObject(InAppTransactionType type) {

		switch (type) {
			case SDK_TRANSACTION_ENCRYPTION:
                return new InAppEncryptTransactionObject.Builder();
            case SDK_TRANSACTION_ANDROID_PAY:
                return new InAppAndroidPayTransactionObject.Builder();
            default:
                return new InAppEncryptTransactionObject.Builder();
        }
	}

    public static abstract class Builder {
		protected String merchantReferenceCode;
		protected InAppTransactionType transactionType;
		protected String transactionTime;
		protected String transactionDate;
		protected SDKCardData cardData;
        protected SDKBillTo billTo;

        public InAppTransactionObject.Builder merchantReferenceCode(String merchantReferenceCode) {
            this.merchantReferenceCode = merchantReferenceCode;
            return this;
        }

        public InAppTransactionObject.Builder cardData(SDKCardData cardData) {
            this.cardData = cardData;
            return this;
        }

        public InAppTransactionObject.Builder billTo(SDKBillTo billTo) {
            this.billTo = billTo;
            return this;
        }

        public InAppTransactionObject.Builder transactionDate(String transactionDate) {
            this.transactionDate = transactionDate;
            return this;
        }

        public InAppTransactionObject.Builder transactionTime(String transactionTime) {
            this.transactionTime = transactionTime;
            return this;
        }

        public abstract InAppTransactionObject build();

	}
}
