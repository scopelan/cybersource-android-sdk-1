package com.visa.inappsdk.connectors.inapp.datamodel;

import com.visa.inappsdk.soap.model.SDKXMLParentNode;

/**
 * Created by fzubair on 11/16/2015.
 */
public class InAppBillTo implements InAppBaseModel {

    public final String OBJECT_NAME = "billTo";

    public final String FIRST_NAME = "firstName";
    public final String LAST_NAME = "lastName";
    public final String POSTAL_CODE = "postalCode";

    public String firstName;
    public String lastName;
    public String postalCode;

    /**
     * @param firstName
     * @param lastName
     * @param postalCode
     */
    public InAppBillTo(String firstName, String lastName, String postalCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.postalCode = postalCode;
    }

    @Override
    public void updateEnvelope(SDKXMLParentNode request) {
        SDKXMLParentNode billTo = request.addNode(request.getNamespace(), OBJECT_NAME);
        if (this.firstName != null) {
            billTo.addTextNode(billTo.getNamespace(), FIRST_NAME, this.firstName);
        }
        if (this.lastName != null) {
            billTo.addTextNode(billTo.getNamespace(), LAST_NAME, this.lastName);
        }
        if (this.postalCode != null) {
            billTo.addTextNode(billTo.getNamespace(), POSTAL_CODE, this.postalCode);
        }
    }
}
