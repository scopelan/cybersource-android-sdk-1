package com.visa.inappsdk.connectors.inapp.datamodel;

import com.visa.inappsdk.common.SDKCardBrandType;
import com.visa.inappsdk.datamodel.transaction.fields.SDKCardType;
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
	public final String CV_NUMBER = "cvNumber";
	public final String POSTAL_CODE = "postalCode";
	public final String TYPE = "type";

	public String accountNumber;
	public String expirationMonth;
	public String expirationYear;
	public String cvNumber;
	public SDKCardType type;

	/**
	 * @param accountNumber
	 * @param expirationMonth
	 * @param expirationYear
	 * @param cvNumber
     * @param type
	 */
	public InAppCard(String accountNumber, String expirationMonth, String expirationYear,
					 String cvNumber, SDKCardType type) {
		this.accountNumber = accountNumber;
		this.expirationMonth = expirationMonth;
		this.expirationYear = expirationYear;
		this.cvNumber = cvNumber;
		this.type = type;
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
			if (this.cvNumber != null) {
				card.addTextNode(card.getNamespace(), CV_NUMBER, this.cvNumber);
			}
/*			if (this.zipCode != null) {
				card.addTextNode(card.getNamespace(), POSTAL_CODE, this.zipCode);
			}*/
/*			if (this.type != null) {
				card.addTextNode(card.getNamespace(), TYPE, this.type.toString());
			}*/
/*			if (checkIfCardTypeIsValid()) {
				card.addTextNode(card.getNamespace(), CARD_TYPE, this.cardBrandType.getBrandCode());
			}*/
		}
	}

	private boolean validateObject() {
		return !(this.accountNumber == null && this.expirationMonth == null && this.expirationYear == null);
	}

/*	private boolean checkIfCardTypeIsValid() {
		return (this.cardBrandType != null && this.cardBrandType != SDKCardBrandType.SDK_INVALID_CARD_NUMBER);
	}*/
}