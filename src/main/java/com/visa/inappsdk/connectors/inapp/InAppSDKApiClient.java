package com.visa.inappsdk.connectors.inapp;

import android.app.Application;
import android.content.Context;

import com.visa.inappsdk.connectors.inapp.connection.InAppConnectionData;
import com.visa.inappsdk.connectors.inapp.transaction.InAppTransactionObject;
import com.visa.inappsdk.datamodel.SDKGateway;
import com.visa.inappsdk.datamodel.transaction.SDKTransactionObject;
import com.visa.inappsdk.datamodel.transaction.callbacks.SDKConnectionCallback;

import java.lang.ref.WeakReference;

/**
 * Created by fzubair on 10/9/2015.
 */
public class InAppSDKApiClient {
    public static String DEFAULT_NAMESPACE = "urn:schemas-cybersource-com:transaction-data-1.120";

    private static WeakReference<Context> context;
    // end point decision for CyberSource environment
    public enum Environment {ENV_TEST, ENV_PROD};
    private final Environment environment;
    private final String merchantID;
    private final SDKConnectionCallback connectionCallback;

    // endpoint API to be used once the connect method is invoked
    public enum Api {API_ENCRYPTION, API_ANDROID_PAY_TRANSACTION}

    private InAppSDKApiClient(Builder builder) {
        this.context = builder.context;
        this.environment = builder.environment;
        this.merchantID = builder.merchantID;
        this.connectionCallback = builder.connectionCallback;
        setGatewayMerchantID();
        setActiveCurrentUrl();
        if(builder.transactionNamespace != null)
            DEFAULT_NAMESPACE = builder.transactionNamespace;
    }

    public static WeakReference<Context> getContext() {
        return context;
    }

    public Environment getEnvironment() {
        return environment;
    }

    private void setActiveCurrentUrl(){
            InAppConnectionData.PAYMENTS_CURRENT_URL = (this.environment == Environment.ENV_PROD) ?
                    InAppConnectionData.PAYMENTS_PROD_URL : InAppConnectionData.PAYMENTS_TEST_URL_NEW;
    }

    private void setGatewayMerchantID(){
        InAppGateway.getGateway().setMerchantID(merchantID);
    }

    public static void dispose() {
        InAppGateway.dispose();
    }

    public boolean connect(Api api, SDKTransactionObject transactionObject, String messageSignature){
        if(api == null)
            throw new NullPointerException("API must not be null");
        if(transactionObject == null)
            throw new NullPointerException("Transaction Object must not be null");
        if(transactionObject.getCardData() == null)
            throw new NullPointerException("Missing fields: Card Data must not be null");
        if(messageSignature == null || messageSignature.isEmpty())
            throw new NullPointerException("Invalid Message Signature");

        InAppGateway.getGateway().setMessageSignature(messageSignature);
        switch (api){
            case API_ENCRYPTION:
                return InAppGateway.getGateway().performEncryption(transactionObject, this.connectionCallback);
            case API_ANDROID_PAY_TRANSACTION:
                return false;
            default:
                return false;
        }
    }

    public static class Builder {
        private final WeakReference<Context> context;
        private final Environment environment;
        private final String merchantID;
        private SDKConnectionCallback connectionCallback;
        private String transactionNamespace = null;

        public Builder(Context context, Environment environment, String merchantID){
            if(context == null)
                throw new NullPointerException("Context must not be null");
            if(merchantID == null || merchantID.isEmpty())
                throw new NullPointerException("Invalid Merchant ID");
            this.context = new WeakReference<>(context);
            this.environment = (environment == null) ? Environment.ENV_TEST : environment;
            this.merchantID = merchantID;
        }

        public InAppSDKApiClient.Builder setTransactionNamespace(String transactionNamespace) {
            this.transactionNamespace = transactionNamespace;
            return this;
        }

        public InAppSDKApiClient.Builder setSDKConnectionCallback(SDKConnectionCallback connectionCallback) {
            this.connectionCallback = connectionCallback;
            return this;
        }

        public InAppSDKApiClient build(){
            return new InAppSDKApiClient(this);
        }
    }
}
