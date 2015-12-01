package com.cybersource.inappsdk.common.exceptions;

/**
 * Created by fzubair on 10/19/2015.
 */
public class SDKInvalidCardException extends Exception{

    private static final String INVALID_CARD_NUMBER = "Invalid Card Number";

    // Parameterless Constructor
    public SDKInvalidCardException() {
        super(INVALID_CARD_NUMBER);
    }

    // Constructor that accepts a message
    public SDKInvalidCardException(String message)
    {
        super(message);
    }
}