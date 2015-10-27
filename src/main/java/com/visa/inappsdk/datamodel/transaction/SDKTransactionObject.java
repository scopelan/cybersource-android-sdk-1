package com.visa.inappsdk.datamodel.transaction;

import com.visa.inappsdk.datamodel.transaction.fields.SDKCardData;

/**
 * This class represents a transaction that is sent to a reader and then received back from it.
 * 
 * @author fzubair
 */
public abstract class SDKTransactionObject {

	protected String merchantReferenceCode;
	protected SDKTransactionType transactionType;
	protected String transactionTime;
	protected String transactionDate;
	protected SDKCardData cardData;

/*	protected SDKGatewayResponse response;
	protected String linkToTransactionId;
	protected String authCode;*/

/*	protected BigDecimal totalAmount = BigDecimal.ZERO;
	protected BigDecimal subtotalAmount = BigDecimal.ZERO;
	protected BigDecimal totalTax = BigDecimal.ZERO;
    protected BigDecimal refundAmount = BigDecimal.ZERO;*/

/*	protected VMposCyberSourceShipTo shipTo;
	protected VMposCyberSourceBillTo billTo;
	protected List<VMposItem> items;

	protected VMposMerchantDetails merchantDetails;
	protected VMposPurchaseDetails purchaseDetails;

	protected VMposTransactionNote note;
	protected VMposTransactionObject nextTransaction;
	protected VMposTransactionObject previousTransaction;
	protected VMposSignature signature;*/


	SDKTransactionObject(Builder builder) {
	}

    SDKTransactionObject() {
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

	public SDKTransactionType getTransactionType() {
		return transactionType;
	}

	/**
	 * A factory method for creating proper transaction object.
	 * 
	 * @param type transaction type
	 * @return one of transaction objects
	 */
	public static SDKTransactionObject.Builder createTransactionObject(SDKTransactionType type) {

		switch (type) {
			case SDK_TRANSACTION_ENCRYPTION:
                return new SDKEncryptTransactionObject.Builder();

/*            case SDK_TRANSACTION_HISTORY:
                return new SDKHistoryTransactionObject();

            case SDK_TRANSACTION_REFUND:
                return new SDKRefundTransactionObject();

            case SDK_TRANSACTION_VOID:
                return new SDKVoidTransactionObject();
*/
            default:
                return new SDKEncryptTransactionObject.Builder();
        }
	}

    public static abstract class Builder {
		protected String merchantReferenceCode;
		protected SDKTransactionType transactionType;
		protected String transactionTime;
		protected String transactionDate;
		protected SDKCardData cardData;

        public SDKTransactionObject.Builder setMerchantReferenceCode(String merchantReferenceCode) {
            this.merchantReferenceCode = merchantReferenceCode;
            return this;
        }

        public SDKTransactionObject.Builder setCardData(SDKCardData cardData) {
            this.cardData = cardData;
            return this;
        }

        public SDKTransactionObject.Builder setTransactionDate(String transactionDate) {
            this.transactionDate = transactionDate;
            return this;
        }

        public SDKTransactionObject.Builder setTransactionTime(String transactionTime) {
            this.transactionTime = transactionTime;
            return this;
        }

        public abstract SDKEncryptTransactionObject build();

	}
}
