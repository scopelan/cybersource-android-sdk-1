package com.cybersource.inappsdk.connectors.inapp.responses;

/**
 * Object InAppCcEncryptedPaymentReply stores all possible data of object
 * ccEncryptedPaymentReply from gateway responses (most of fields are optional, so they
 * will be 'null', this object appears only in Encryption Method)
 *
 * Created by fzubair on 10/14/2015.
 */
public class InAppCcEncryptedPaymentDataReply {
    public String data;
    public String reasonCode;
    public String requestDateTime;
}
