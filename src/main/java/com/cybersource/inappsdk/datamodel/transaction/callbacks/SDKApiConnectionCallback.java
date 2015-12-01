package com.cybersource.inappsdk.datamodel.transaction.callbacks;

import com.cybersource.inappsdk.common.error.SDKError;
import com.cybersource.inappsdk.datamodel.response.SDKGatewayResponse;

/**
 * Provides listeners for gateway transaction events.
 * 
 * @author fzubair
 */
public interface SDKApiConnectionCallback {

	/**
	 * Called when an error occurred caused by sending/receiving a request. It
	 * might be an error returned by the gateway as well as by the gateway
	 * driver (e.g connection problems, missing request parameters)
	 * 
	 * @param error
	 */
	public abstract void onErrorReceived(SDKError error);


	/**
	 * Called when transaction request completed.
	 * 
	 * @param response
	 */
	public abstract void onApiConnectionFinished(SDKGatewayResponse response);

}
