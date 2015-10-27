package com.visa.inappsdk.connectors.inapp;

import android.os.AsyncTask;
import android.util.Xml.Encoding;

import com.visa.inappsdk.common.error.SDKError;
import com.visa.inappsdk.common.error.SDKGatewayError;
import com.visa.inappsdk.common.error.SDKInternalError;
import com.visa.inappsdk.common.utils.SDKUtils;
import com.visa.inappsdk.connectors.inapp.connection.InAppConnectionData;
import com.visa.inappsdk.connectors.inapp.envelopes.InAppBaseEnvelope;
import com.visa.inappsdk.connectors.inapp.responses.InAppResponseObject;
import com.visa.inappsdk.datamodel.SDKGatewayErrorMapping;
import com.visa.inappsdk.datamodel.response.SDKGatewayResponse;
import com.visa.inappsdk.datamodel.response.SDKGatewayResponseType;
import com.visa.inappsdk.datamodel.transaction.callbacks.SDKConnectionCallback;
import com.visa.inappsdk.soap.connection.SDKConnectionConstants;
import com.visa.inappsdk.soap.parser.SDKSoapParser;


import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import javax.net.ssl.HttpsURLConnection;

/**
 * Allows with CyberSource Gateway and provides its response.
 * 
 * @author fzubair
 */
class InAppConnection {

	static SDKConnectionCallback connectionCallback;

	/** Reason code: 100 */
	private static String REASON_CODE_OK = "100";
    /** Reason code: 120 */
    private static String REASON_CODE_DISCOUNTED_OK = "120";
	/** Reason code: 101 */
	private static String REASON_CODE_MISSING_FIELD = "101";
	/** Reason code: 102 */
	private static String REASON_CODE_INVALID_FIELD = "102";
	/** Reason code: 110 */
	private static String REASON_CODE_PARTIAL = "110";
	private final static String POST = "POST";

	/**
	 * @param envelope - Envelope that will be send to Gateway
	 * @param delegate - used to notify user when given operation will finish
	 */
	public static void connection(final InAppBaseEnvelope envelope, SDKConnectionCallback delegate) {
		connectionCallback = delegate;
		new ConnectionAsyncTask().execute(envelope);
	}

	private static class ConnectionAsyncTask extends AsyncTask<InAppBaseEnvelope, Void, Object> {
		@Override
		protected Object doInBackground(InAppBaseEnvelope... envelops) {
			final InAppBaseEnvelope envelope = envelops[0];
			Object resultObject = null;
            String url = InAppConnectionData.PAYMENTS_CURRENT_URL;
			try {

				HttpsURLConnection urlConnection = SDKUtils.getHttpsURLConnection(url, POST, true);
				urlConnection.setRequestProperty(SDKConnectionConstants.CONTENT_TYPE_LABEL,
						SDKConnectionConstants.XML_CONTENT_TYPE_PREFIX + envelope.getEncoding());
				urlConnection.setRequestProperty(SDKConnectionConstants.HEADER_KEY_SOAP_ACTION,
						InAppBaseEnvelope.DEFAULT_SOAP_ACTION);

				OutputStream os = urlConnection.getOutputStream();
				BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, Encoding.UTF_8.name()));
				writer.write(SDKSoapParser.parseEnvelope(envelope));
				writer.flush();
				writer.close();
				os.close();

				int responseCode = urlConnection.getResponseCode();
				if (responseCode == HttpsURLConnection.HTTP_OK) {
					InAppResponseObject result = envelope.parseResponse(urlConnection.getInputStream());
					if (result.reasonCode.equals(REASON_CODE_OK) || result.reasonCode.equals(REASON_CODE_PARTIAL)
                            || result.reasonCode.equals(REASON_CODE_DISCOUNTED_OK)) { // -- Faizan -- Added this line since the server has a code
                                                                                      // for discounted successful transaction as: 120

						SDKGatewayResponse response = null;
						if(result.type == SDKGatewayResponseType.SDK_NVP_TRANSACTION)
							response = result.convertNVPToGatewayResponse(result);
						else
							response = result.convertToGatewayResponse();

                        resultObject = response;

					} else {
						SDKGatewayError error = SDKGatewayErrorMapping.getGatewayError(result.reasonCode);
						if (result.reasonCode.equals(REASON_CODE_MISSING_FIELD)) {
							error.setErrorExtraMessage(result.missingField);
						} else {
							error.setErrorExtraMessage(result.icsMessage.icsRmsg);
						}
						resultObject = error;
					}
				} else if (responseCode == HttpsURLConnection.HTTP_INTERNAL_ERROR) {
					SDKError error = envelope.parseGatewayError(urlConnection.getErrorStream());
					resultObject = error;
				} else {
					SDKError error = SDKInternalError.SDK_INTERNAL_ERROR_NETWORK_CONNECTION;
					error.setErrorExtraMessage(String.valueOf(responseCode));
					resultObject = error;
				}
			} catch (IOException e) {
                e.printStackTrace();
				SDKError error = SDKInternalError.SDK_INTERNAL_ERROR_NETWORK_CONNECTION;
				error.setErrorExtraMessage(e.getMessage());
				resultObject = error;
			}
			return resultObject;
		}

		@Override
		protected void onPostExecute(Object result) {
			if (result instanceof SDKGatewayResponse) {
				SDKGatewayResponse response = (SDKGatewayResponse)result;
				//SDKGateway.getGateway();
				switch (response.getType()) {
				case SDK_ANDROID_PAY_TRANSACTION:
					connectionCallback.onApiConnectionFinished(response);
					break;
/*				case SDK_NVP_TRANSACTION:
						connectionCallback.onNvpTransactionFinished(response);
						break;*/
				default:
					break;
				}
			} else if (result instanceof SDKError) {
				connectionCallback.onErrorReceived((SDKError) result);
			}
		}
	}
}