package com.visa.inappsdk.common;

import com.visa.inappsdk.connectors.inapp.InAppSDKApiClient;
import com.visa.inappsdk.connectors.inapp.envelopes.InAppBaseEnvelope;

/**
 * Allows to manage settings fields (get and set their values)
 * 
 * @author fzubair
 */
public final class SDKSettings {

	// settings object instance
	private static SDKSettings instance;
    private SDKCurrency currency;

    // end point decision for CyberSource environment
    public enum Environment {ENV_TEST, ENV_PROD};

    public static Environment environment;

	/**
	 * @return an instance of Setting for merchant for which those Setting were
	 *         initialized. Make sure you are calling
	 *         <code>initForMerchant(String merchantId)</code> beforehand.
	 */
	public static SDKSettings getInstance() {
		if (instance == null) {
			instance = new SDKSettings();
		}

        return instance;
	}
}