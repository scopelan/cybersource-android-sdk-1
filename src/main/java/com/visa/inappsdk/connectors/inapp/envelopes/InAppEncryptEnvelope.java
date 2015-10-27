package com.visa.inappsdk.connectors.inapp.envelopes;

import android.os.Parcel;
import android.os.Parcelable;

import com.visa.inappsdk.common.error.SDKError;
import com.visa.inappsdk.connectors.inapp.datamodel.InAppCard;
import com.visa.inappsdk.connectors.inapp.responses.InAppResponseObject;
import com.visa.inappsdk.connectors.inapp.services.InAppEncryptPaymentDataService;
import com.visa.inappsdk.connectors.inapp.transaction.InAppEncryptionTransactionObject;
import com.visa.inappsdk.datamodel.response.SDKGatewayResponseType;
import com.visa.inappsdk.datamodel.transaction.SDKTransactionObject;
import com.visa.inappsdk.datamodel.transaction.fields.SDKCardData;
import com.visa.inappsdk.soap.model.SDKXMLParentNode;

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

        //VMposAddress address = transactionObject.getBillTo();
        //VMposCyberSourceBillTo billTo = transactionObject.getBillTo();
/*        if (address != null) {
            billTo = new VMposCyberSourceBillTo(address.getFirstName(), address.getLastName(), address.getStreet(),
                    address.getStreet2(), address.getCity(), address.getState(), address.getZip(), address.getCountry(),
                    address.getPhone(), address.getEmail());
        }*/
        //-- Faizan -- if billto is null add this dummy address just to make the 'internet' and 'recurring' transaction work not needed for 'retial'
/*        else{
            billTo = new VMposCyberSourceBillTo(
                    "Faizan",
                    "Zubair",
                    "NE",
                    "street 2",
                    "Bellevue", "WA", "98004",
                    "US",
                    "206 209 8866", "fez.zubair@gmail.com"
            );
        }*/

/*        VMposCyberSourceShipTo shipTo = transactionObject.getShipTo();
        VMposAddress ship = transactionObject.getShipTo() != null ? transactionObject.getShipTo() : transactionObject.getFirstTransactionObject()
                .getShipTo();
        if (ship != null) {
            shipTo = new VMposCyberSourceShipTo(ship.getFirstName(), ship.getLastName(), ship.getStreet(), ship.getStreet2(),
                    ship.getCity(), ship.getState(), ship.getZip(), ship.getCountry(), ship.getPhone(), ship.getEmail());
        }*/

/*        InAppPurchaseTotals inAppPurchaseTotals = new InAppPurchaseTotals(
                details.getCurrency().name(), getGatewayAmountString(transactionObject.getTotalAmount()));*/

        SDKCardData cardData = transactionObject.getCardData();
        InAppCard card = null;
        if (cardData != null) {
            SDKCardData cardManual = cardData;
            card = new InAppCard(cardManual.getCardNumber(), cardManual.getCardExpirationMonth(),
                    cardManual.getCardExpirationYear(), cardManual.getCardBrandType(), DEFAULT_CARD_CV_INDICATOR,
                    cardManual.getCvv());
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
                merchantId, merchantReferenceCode, null, card, inAppEncryptPaymentDataService, CLIENT_LIBRARY/*, encryptedPayment*/);
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
