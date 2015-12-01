package com.cybersource.inappsdk.connectors.inapp.envelopes;

import com.cybersource.inappsdk.common.error.SDKError;
import com.cybersource.inappsdk.connectors.inapp.datamodel.InAppBillTo;
import com.cybersource.inappsdk.connectors.inapp.datamodel.InAppCard;
import com.cybersource.inappsdk.connectors.inapp.responses.InAppResponseObject;
import com.cybersource.inappsdk.connectors.inapp.services.InAppEncryptPaymentDataService;
import com.cybersource.inappsdk.connectors.inapp.transaction.InAppEncryptionTransactionObject;
import com.cybersource.inappsdk.datamodel.response.SDKGatewayResponseType;
import com.cybersource.inappsdk.datamodel.transaction.SDKTransactionObject;
import com.cybersource.inappsdk.datamodel.transaction.fields.SDKBillTo;
import com.cybersource.inappsdk.datamodel.transaction.fields.SDKCardData;
import com.cybersource.inappsdk.soap.model.SDKXMLParentNode;

import java.io.InputStream;

/**
 * Created by fzubair on 10/7/2015.
 */
public class InAppEncryptEnvelope extends InAppBaseEnvelope {

    InAppEncryptEnvelope() {
    }

    public InAppEncryptEnvelope(SDKTransactionObject transactionObject, String merchantId, String messageSignature) {
        createEnvelopeHeader(merchantId, messageSignature);
        InAppEncryptionTransactionObject encryptionTransactionObject = convertTransactionObject(transactionObject, merchantId);
        createEnvelopeBody(encryptionTransactionObject);
    }

    private void createEnvelopeBody(InAppEncryptionTransactionObject paymentObject) {
        SDKXMLParentNode request = this.createRequestMessage();
        paymentObject.updateEnvelope(request);
    }

    private InAppEncryptionTransactionObject convertTransactionObject(SDKTransactionObject transactionObject,
                                                                                            String merchantId) {

        String merchantReferenceCode = transactionObject.getMerchantReferenceCode();

        SDKCardData cardData = transactionObject.getCardData();
        InAppCard card = null;
        if (cardData != null) {
            card = new InAppCard(cardData.getAccountNumber(), cardData.getCardExpirationMonth(),
                    cardData.getCardExpirationYear(), cardData.getCvNumber(),
                    cardData.getCardAccountNumberType());
        }

        SDKBillTo billTo = transactionObject.getBillTo();
        InAppBillTo bill = null;
        if (billTo != null) {
            bill = new InAppBillTo(billTo.getFirstName(), billTo.getLastName(),
                    billTo.getPostalCode());
        }

        InAppEncryptPaymentDataService inAppEncryptPaymentDataService = new InAppEncryptPaymentDataService(true, null);
                /*transactionObject.getPurchaseDetails().getCommerceIndicator());*/

        // Faizan -- added the encrypted payment part
        //VMposEncryptedPayment encryptedPayment = transactionObject.getEncryptedPayment();

/*        VMposEncryptedPayment encryptedPayment = new VMposEncryptedPayment();
        encryptedPayment.setEncodedData(getIDTechTestBlob());
        encryptedPayment.setEncodedMetaData(VMposMessageSignature.MetadataEncodedValue);
        encryptedPayment.setPaymentSolution(VMposMessageSignature.PAYMENT_SOLUTION_DEFAULT_VALUE);*/

        InAppEncryptionTransactionObject inAppEncryptionTransactionObject = new InAppEncryptionTransactionObject(
                merchantId, merchantReferenceCode, card, bill, inAppEncryptPaymentDataService, CLIENT_LIBRARY/*, encryptedPayment*/);
        return inAppEncryptionTransactionObject;
    }

    @Override
    protected void createEnvelopeHeader(String merchantID, String messageSignature) {
        super.createEnvelopeHeader(merchantID, messageSignature);
    }

    @Override
    public SDKError parseGatewayError(InputStream inputStream) {
        return super.parseGatewayError(inputStream);
    }

    @Override
    public InAppResponseObject parseResponse(InputStream inputStream) {
        return InAppResponseObject.createEncryptionResponse(inputStream, getResponseType());
    }

    @Override
    public SDKGatewayResponseType getResponseType() {
        return SDKGatewayResponseType.SDK_ENCRYPTION;
    }

/*  protected InAppEncryptEnvelope(Parcel in) {
        super(in);
    }

    public static final Parcelable.Creator<InAppEncryptEnvelope> CREATOR = new Parcelable.Creator<InAppEncryptEnvelope>() {

        @Override
        public InAppEncryptEnvelope createFromParcel(Parcel in) {
            return new InAppEncryptEnvelope(in);
        }

        @Override
        public InAppEncryptEnvelope[] newArray(int size) {
            return new InAppEncryptEnvelope[size];
        }
    };*/
}
