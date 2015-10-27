package com.visa.inappsdk.connectors.inapp.datamodel;

import com.visa.inappsdk.soap.model.SDKXMLParentNode;

/**
 * Created by fzubair on 10/7/2015.
 */
public interface InAppBaseModel {

	/**
	 * Updates request with fields needed in WebService request.
	 * 
	 * @param request original request that is being created to be sent to the
	 *            gateway
	 */
	public void updateEnvelope(SDKXMLParentNode request);
}
