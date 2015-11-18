package com.visa.inappsdk.connectors.inapp.transaction;

import com.visa.inappsdk.soap.model.SDKXMLParentNode;

import java.util.HashMap;

/**
 * Core class for every CYBS transaction type.
 * 
 * @author fzubair
 */
public abstract class InAppEnvelopeTransactionObject {

	public final String MERCHANT_ID = "merchantID";
	public final String MERCHANT_REFERENCE_CODE = "merchantReferenceCode";

	public String merchantID;
	public String merchantReferenceCode;

	/**
	 * Updates request with fields needed in WebService request.
	 * 
	 * @param request original request that is being created to be sent to the
	 *            gateway
	 */
	public abstract void updateEnvelope(SDKXMLParentNode request);

	/**
	 * Updates request with fields needed in WebService NVP request.
	 * @param request original request that is being created to be sent to the gateway
	 * @param nvpParams the name-value pairs
	 */
	public void updateEnvelope(SDKXMLParentNode request, HashMap<String, String> nvpParams){}

	protected void createMerchantData(SDKXMLParentNode request) {
		if (this.merchantID != null) {
			request.addTextNode(request.getNamespace(), MERCHANT_ID, this.merchantID);
		}
		if (this.merchantReferenceCode != null) {
			request.addTextNode(request.getNamespace(), MERCHANT_REFERENCE_CODE, this.merchantReferenceCode);
		}
	}
}