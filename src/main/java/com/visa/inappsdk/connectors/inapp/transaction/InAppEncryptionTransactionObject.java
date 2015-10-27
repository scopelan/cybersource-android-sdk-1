package com.visa.inappsdk.connectors.inapp.transaction;

import com.visa.inappsdk.connectors.inapp.datamodel.InAppCard;
import com.visa.inappsdk.connectors.inapp.datamodel.InAppPurchaseTotals;
import com.visa.inappsdk.connectors.inapp.services.InAppEncryptPaymentDataService;
import com.visa.inappsdk.soap.model.SDKXMLParentNode;

/**
 * Contains payment data that will be send to WebService in request body;
 * 
 * Created by fzubair on 10/8/2015.
 */
public class InAppEncryptionTransactionObject extends InAppTransactionObject {
	
	public InAppPurchaseTotals purchaseTotals;
	public InAppCard card;
	public String clientLibrary;

/*    public VMposCyberSourceBillTo billTo;
	public VMposCyberSourceShipTo shipTo;*/
	public InAppEncryptPaymentDataService encryptPaymentDataService;

	private final String LINK_TO_REQUEST = "linkToRequest";
    private final String CLIENT_LIBRARY = "clientLibrary";
    public final String PAYMENT_SOLUTION = "paymentSolution";

    //public VMposEncryptedPayment encryptedPayment;
    //public String clientLibrary;

	/**
	 * All fields in constructor are required
	 * 
	 * @param merchantId
	 * @param merchantReferenceCode
	 * @param //vMposWebServiceBillTo
	 * @param vMposWebServicePurchaseTotals
	 * @param InAppWebServiceCard
	 * @param encryptPaymentDataService
	 * @param //shipTo
	 */
	public InAppEncryptionTransactionObject(String merchantId, String merchantReferenceCode,
											/*VMposCyberSourceBillTo vMposWebServiceBillTo,*/ InAppPurchaseTotals vMposWebServicePurchaseTotals,
											InAppCard InAppWebServiceCard,
											InAppEncryptPaymentDataService encryptPaymentDataService, /*VMposCyberSourceShipTo shipTo,*/
											String clientLibrary/*, VMposEncryptedPayment encryptedPayment,*/) {
		this.merchantID = merchantId;
		this.merchantReferenceCode = merchantReferenceCode;
		this.clientLibrary = clientLibrary;
		this.purchaseTotals = vMposWebServicePurchaseTotals;
		this.card = InAppWebServiceCard;
        //this.billTo = vMposWebServiceBillTo;
		//this.shipTo = shipTo;
		this.encryptPaymentDataService = encryptPaymentDataService;
        //this.encryptedPayment = encryptedPayment;
	}

	@Override
	public void updateEnvelope(SDKXMLParentNode request) {
		createMerchantData(request);
		if(this.clientLibrary != null){
			request.addTextNode(request.getNamespace(), CLIENT_LIBRARY, this.clientLibrary);
		}
/*        if (this.billTo != null) {
            this.billTo.updateEnvelope(request);
        }
		if (this.shipTo != null) {
			this.shipTo.updateEnvelope(request);
		}*/
		if (this.purchaseTotals != null) {
			this.purchaseTotals.updateEnvelope(request);
		}
/*        if (this.encryptedPayment != null) {
            this.encryptedPayment.updateEnvelope(request);
        }*/
		if (this.card != null) {
			this.card.updateEnvelope(request);
		}
		if (this.encryptPaymentDataService != null) {
			this.encryptPaymentDataService.updateEnvelope(request);
		}
	}
}