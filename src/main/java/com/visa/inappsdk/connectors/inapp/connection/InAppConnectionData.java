package com.visa.inappsdk.connectors.inapp.connection;

/**
 * Contains data set for all connections to CyberSource Servers
 * 
 * @author fzubair
 */
public class InAppConnectionData {

    /** SOAP Payments CURRENT endpoint address. */
    public static String PAYMENTS_CURRENT_URL;
    /** SOAP Payments TEST endpoint address. */
    public static String PAYMENTS_TEST_URL = "https://mobiletest.ic3.com/mpos/transactionProcessor/";
    /** SOAP Payments PROD endpoint address. */
    public static String PAYMENTS_PROD_URL = "https://mobile.ic3.com/mpos/transactionProcessor/";
    /** Connection timeout for the HTTPS Connection. */
    public static int connectionTimeout = 0; // Defaults to 0 if nothing is set by the Merchant
}
