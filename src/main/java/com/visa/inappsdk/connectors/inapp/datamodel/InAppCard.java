package com.visa.inappsdk.connectors.inapp.datamodel;

import com.visa.inappsdk.common.SDKCardBrandType;
import com.visa.inappsdk.soap.model.SDKXMLParentNode;

/**
 * Describes fields in "card" object in WebService request
 *
 * Created by fzubair on 10/7/2015.
 */
public class InAppCard implements InAppBaseModel {

	public final String OBJECT_NAME = "card";
	public final String ACCOUNT_NUMBER = "accountNumber";
	public final String EXPIRATION_MONTH = "expirationMonth";
	public final String EXPIRATION_YEAR = "expirationYear";
	public final String CARD_TYPE = "cardType";
	public final String CV_INDICATOR = "cvIndicator";
	public final String CV_NUMBER = "cvNumber";

	public String accountNumber;
	public String expirationMonth;
	public String expirationYear;
	public SDKCardBrandType cardType;
	public String cvIndicator;
	public String cvNumber;

	/**
	 * @param accountNumber
	 * @param expirationMonth
	 * @param expirationYear
	 * @param cardType
	 * @param cvIndicator
	 * @param cvNumber
	 */
	public InAppCard(String accountNumber, String expirationMonth, String expirationYear,
					 SDKCardBrandType cardType, String cvIndicator, String cvNumber) {
		this.accountNumber = accountNumber;
		this.expirationMonth = expirationMonth;
		this.expirationYear = expirationYear;
		this.cardType = cardType;
		this.cvIndicator = cvIndicator;
		this.cvNumber = cvNumber;
	}

	@Override
	public void updateEnvelope(SDKXMLParentNode request) {
		if (validateObject()) {
			SDKXMLParentNode card = request.addNode(request.getNamespace(), OBJECT_NAME);
			if (this.accountNumber != null) {
				card.addTextNode(card.getNamespace(), ACCOUNT_NUMBER, this.accountNumber);
			}
			if (this.expirationMonth != null) {
				card.addTextNode(card.getNamespace(), EXPIRATION_MONTH, this.expirationMonth);
			}
			if (this.expirationYear != null) {
				card.addTextNode(card.getNamespace(), EXPIRATION_YEAR, this.expirationYear);
			}
			if (this.cvIndicator != null) {
				card.addTextNode(card.getNamespace(), CV_INDICATOR, this.cvIndicator);
			}
			if (this.cvNumber != null) {
				card.addTextNode(card.getNamespace(), CV_NUMBER, this.cvNumber);
			}
			if (checkIfCardTypeIsValid()) {
				card.addTextNode(card.getNamespace(), CARD_TYPE, this.cardType.getBrandCode());
			}
		}
	}

	private boolean validateObject() {
		return !(this.accountNumber == null && this.expirationMonth == null && this.expirationYear == null
				&& this.cardType == null && this.cvIndicator == null && this.cvNumber == null);
	}

	private boolean checkIfCardTypeIsValid() {
		return (this.cardType != null && this.cardType != SDKCardBrandType.SDK_OTHER
				&& this.cardType != SDKCardBrandType.SDK_INVALID_CARD_NUMBER);
	}
}