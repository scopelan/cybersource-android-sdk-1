package com.cybersource.inappsdk.connectors.inapp.datamodel;

import com.cybersource.inappsdk.soap.model.SDKXMLParentNode;

/**
 * Describes fields in "purchaseTotals" object in WebService request
 * 
 * @author fzubair
 */
public class InAppPurchaseTotals implements InAppBaseModel {

	public final String OBJECT_NAME = "purchaseTotals";
	public final String CURRENCY = "currency";
	public final String GRAND_TOTAL_AMOUNT = "grandTotalAmount";

	public String currency; // http://apps.cybersource.com/library/documentation/sbc/quickref/currencies.pdf
	public String grandTotalAmount;

	/**
	 * All fields in constructor are required
	 * 
	 * @param currency - Currency used for the order.
	 * @param grandTotalAmount - Grand total for the order. (field should be
	 *            null for payment)
	 */
	public InAppPurchaseTotals(String currency, String grandTotalAmount) {
		this.currency = currency;
		this.grandTotalAmount = grandTotalAmount;
	}

	@Override
	public void updateEnvelope(SDKXMLParentNode request) {
		if (validateObject()) {
			SDKXMLParentNode purchase = request.addNode(request.getNamespace(), OBJECT_NAME);
			if (this.currency != null) {
				purchase.addTextNode(purchase.getNamespace(), CURRENCY, this.currency);
			}
			if (this.grandTotalAmount != null) {
				purchase.addTextNode(null, GRAND_TOTAL_AMOUNT, this.grandTotalAmount);
			}
		}
	}

	private boolean validateObject() {
		return !(this.currency == null && this.grandTotalAmount == null);
	}
}
