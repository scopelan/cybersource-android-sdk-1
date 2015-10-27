package com.visa.inappsdk.connectors.inapp.responses;

/**
 * Object InAppIcsMessageReply stores all possible data of object
 * icsMessage from gateway responses (most of fields are optional, so they
 * will be 'null', this object appears only in Encryption Method)
 *
 * Created by fzubair on 10/14/2015.
 */
public class InAppIcsMessageReply {
    public String icsRmsg;
    public String icsReturnCode;
    public String icsRFlag;
}
