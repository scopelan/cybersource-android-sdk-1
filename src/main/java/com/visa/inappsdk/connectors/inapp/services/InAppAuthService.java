package com.visa.inappsdk.connectors.inapp.services;

import com.visa.inappsdk.soap.model.SDKXMLParentNode;

/**
 * Describes fields of "ccAuthService" object in WebService request
 * 
 * Created by fzubair on 11/18/2015.
 */
public class InAppAuthService extends InAppBaseService {

	public final String OBJECT_NAME = "ccAuthService";
	public final String RUN = "run";
	public final String COMMERCE_INDICATOR = "commerceIndicator";
	public final String BILL_PAYMENT = "billPayment";
	public final String PARTIAL_AUTH_INDICATOR = "partialAuthIndicator";

	public String run;
    String commerceIndicator;
	public String partialAuthIndicator;

	/**
	 * All fields in constructor are required
	 *
	 * @param run
	 */
	public InAppAuthService(boolean run, String commerceIndicator,
							boolean partialAuthIndicator) {
		this.run = String.valueOf(run);
        this.commerceIndicator = commerceIndicator;
		this.partialAuthIndicator = String.valueOf(partialAuthIndicator);
	}

	@Override
	public void updateEnvelope(SDKXMLParentNode request) {
		if (validateObject()) {
			SDKXMLParentNode creditService = request.addNode(request.getNamespace(), OBJECT_NAME);
			if (this.run != null) {
				creditService.addAttribute(null, RUN, this.run);
			}
			if (this.commerceIndicator != null) {
				creditService.addTextNode(creditService.getNamespace(), COMMERCE_INDICATOR, commerceIndicator);
			}
			if (this.partialAuthIndicator != null) {
				creditService.addTextNode(creditService.getNamespace(), PARTIAL_AUTH_INDICATOR, partialAuthIndicator);
			}
		}
	}

	private boolean validateObject() {
		if (this.run == null) {
			return false;
		} else {
			return true;
		}
	}
}
