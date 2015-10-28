package com.visa.inappsdk.datamodel.transaction.callbacks;

import android.os.Parcel;
import android.os.Parcelable;

import com.visa.inappsdk.common.error.SDKError;
import com.visa.inappsdk.datamodel.response.SDKGatewayResponse;

import java.io.Serializable;

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
