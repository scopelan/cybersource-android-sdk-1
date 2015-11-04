package com.visa.inappsdk.connectors.inapp.connection;

/**
 * Contains data set for all connections to CyberSource Servers
 * 
 * @author fzubair
 */
public class InAppConnectionData {

	/** REST oAuth TEST Authorization endpoint address. */
	protected final static String AUTHORIZATION_TEST_DOMAIN = "https://authtest.ic3.com/apiauth/v1/oauth/";
    /** REST oAuth PROD Authorization endpoint address. */
    protected final static String AUTHORIZATION_PROD_DOMAIN = "https://auth.ic3.com/apiauth/v1/oauth/";

    /** SOAP Payments CURRENT endpoint address. */
    public static String PAYMENTS_CURRENT_URL;
    /** SOAP Payments TEST endpoint address. */
    public final static String PAYMENTS_TEST_URL = "https://mobiletest.ic3.com/mpos/transactionProcessor/";
    /** SOAP Payments PROD endpoint address. */
    public final static String PAYMENTS_PROD_URL = "https://mobile.ic3.com/mpos/transactionProcessor/";
    /** SOAP Payments BYZ TEST endpoint address. */
    public final static String PAYMENTS_TEST_URL_BYZ = "https://sl73mpossapq001.visa.com:2021/mpos/transactionProcessor/";
    /** SOAP Payments TEST endpoint address. */
    public final static String PAYMENTS_TEST_URL_NEW = "https://sl73ntaapq005.visa.com:8443/commerce/1.x/transactionProcessor/";

    /** ----NEW---- SOAP Payments TEST endpoint address. */
    //public final static String PAYMENTS_TEST_URL_NEW = "https://ics2wstest.ic3.com/commerce/1.x/transactionProcessor/";

	/** REST TEST Search endpoint address. */
	public final static String SEARCH_SERVER_TEST_BASE_URL = "https://mobiletest.ic3.com/payment/v1/payments/";
    /** REST PROD Search endpoint address. */
    public final static String SEARCH_SERVER_PROD_BASE_URL = "https://mobile.ic3.com/payment/v1/payments/";

	/** REST TEST Search endpoint address. */
	public final static String SEARCH_SERVER_TEST_SEARCH_URL = SEARCH_SERVER_TEST_BASE_URL + "search?";
    /** REST PROD Search endpoint address. */
    public final static String SEARCH_SERVER_PROD_SEARCH_URL = SEARCH_SERVER_PROD_BASE_URL + "search?";

	/** REST TEST Email endpoint address for sending email with recipe. */
	public final static String EMAIL_TEST_RECEIPT_ADDRESS = SEARCH_SERVER_TEST_BASE_URL + "receipts";
    /** REST PROD Email endpoint address for sending email with recipe. */
    public final static String EMAIL_PROD_RECEIPT_ADDRESS = SEARCH_SERVER_PROD_BASE_URL + "receipts";

	/** REST Search Server Data: 'q=' */
	public final static String SEARCH_SERVER_QUERY = "q=";
}
