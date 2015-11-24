package com.visa.inappsdk.connectors.inapp.transaction;

import com.visa.inappsdk.connectors.inapp.datamodel.InAppBillTo;
import com.visa.inappsdk.connectors.inapp.datamodel.InAppCard;
import com.visa.inappsdk.connectors.inapp.services.InAppEncryptPaymentDataService;
import com.visa.inappsdk.soap.model.SDKXMLParentNode;

/**
 * Contains payment data that will be send to WebService in request body;
 * 
 * Created by fzubair on 10/8/2015.
 */
public class InAppEnvelopeEncryptionTransactionObject extends InAppEnvelopeTransactionObject {

	private InAppCard card;
	private InAppBillTo billTo;

	public InAppEncryptPaymentDataService encryptPaymentDataService;

    public final String PAYMENT_SOLUTION = "paymentSolution";

	/**
	 * All fields in constructor are required
	 * 
	 * @param merchantId
	 * @param merchantReferenceCode
	 * @param inAppWebServiceCard
	 * @param inAppBillTo
	 * @param encryptPaymentDataService
     * @param clientLibrary
	 */
	public InAppEnvelopeEncryptionTransactionObject(String merchantId, String merchantReferenceCode,
                                                    InAppCard inAppWebServiceCard, InAppBillTo inAppBillTo,
                                                    InAppEncryptPaymentDataService encryptPaymentDataService,
                                                    String clientLibrary) {
		this.merchantID = merchantId;
		this.merchantReferenceCode = merchantReferenceCode;
		this.clientLibrary = clientLibrary;
		this.card = inAppWebServiceCard;
		this.billTo = inAppBillTo;
		this.encryptPaymentDataService = encryptPaymentDataService;
	}

	@Override
	public void updateEnvelope(SDKXMLParentNode request) {
		createMerchantData(request);
        if (this.billTo != null) {
            this.billTo.updateEnvelope(request);
        }
		if (this.card != null) {
			this.card.updateEnvelope(request);
		}
		if (this.encryptPaymentDataService != null) {
			this.encryptPaymentDataService.updateEnvelope(request);
		}
	}
}