package com.visa.inappsdk.common.utils;

import com.visa.inappsdk.common.SDKCardBrandType;
import com.visa.inappsdk.common.exceptions.SDKInvalidCardException;

import java.util.Calendar;

/**
 * A utils class with card verification methods.
 * 
 * @author fzubair
 */
public class SDKCardUtils {

	private static final String ASTERIX = "*";

	/**
	 * Checks the given card number is valid w.r.t Luhn formula
	 * 
	 * @param cardNumber
	 * @return
	 */
	public static boolean isValid(String cardNumber) throws SDKInvalidCardException {
		if (cardNumber == null || cardNumber.length() < 6) {
			throw new SDKInvalidCardException();
		}
		if (!cardNumber.matches("\\d+")) {
			throw new SDKInvalidCardException();
		}

		// reversing the string
		String reversedNumber = new StringBuilder(cardNumber).reverse().toString();

		String mutilatedString = "";

		// Even digits are doubled, odd digits are not modified
		for (int i = 0; i < reversedNumber.length(); i++) {
			int c = Integer.parseInt(String.valueOf(reversedNumber.charAt(i)));
			if (i % 2 != 0) {
				c *= 2;
			}
			mutilatedString += c;
		}

		int sumOfDigits = 0;

		// summing up all digits
		for (int i = 0; i < mutilatedString.length(); i++) {
			int c = Integer.parseInt(String.valueOf(mutilatedString.charAt(i)));
			sumOfDigits += c;
		}

		if (sumOfDigits > 0 && sumOfDigits % 10 == 0) {
			return true;
		} else {
			throw new SDKInvalidCardException();
		}
	}

    /**
     *
     * Expects a
     *
     * @param month a string in two-digit format
     * @param year a string in four digit format
     * @return
     * @throws SDKInvalidCardException
     */
	public static boolean isValidExpirationDate(String month, String year) throws SDKInvalidCardException{
		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		int currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1; // since JANUARY = 0 for Calendar class

        if( month.length() != 2 || year.length() < 4)
            throw new SDKInvalidCardException("Invalid Card Date format");

        // replace 0 of month from 01 to 09
/*        if(month.charAt(0) == '0')
            month = String.valueOf(month.charAt(1));*/
        int checkYear = Integer.parseInt(year);
		int checkMonth = Integer.parseInt(month);
        if (checkMonth < 1 || checkMonth > 12)
            throw new SDKInvalidCardException("Invalid Card Expiration Date");
		if (checkYear < currentYear)
			throw new SDKInvalidCardException("Invalid Card Expiration Date");
        else if ( (checkYear == currentYear) && (checkMonth < currentMonth) )
            throw new SDKInvalidCardException("Invalid Card Expiration Date");

		return true;
	}

	/**
	 * Check which brand is the card.
	 * 
	 * @param cardNumber full card number
	 * @return
	 */
	public static SDKCardBrandType getBrandByCardNumber(String cardNumber) throws SDKInvalidCardException {
		if (isValid(cardNumber)) {
			return checkBrand(cardNumber);
		} else {
			return SDKCardBrandType.SDK_INVALID_CARD_NUMBER;
		}
	}

	/**
	 * Check which brand is the card by masked card number.
	 * 
	 * @param //maskedCardNumber
	 * @return
	 */
/*	public static SDKCardBrandType getBrandByMaskedCardNumber(String maskedCardNumber) {
		return checkBrand(maskedCardNumber);
	}*/

	private static SDKCardBrandType checkBrand(String cardNumber) {
		String value = cardNumber.replace("-", "").replace(" ", "");
		int size = value.length();
		try {
			Long.parseLong(value.replace(ASTERIX, ""));
		} catch (NumberFormatException ex) {
			return SDKCardBrandType.SDK_INVALID_CARD_NUMBER;
		}
		if (checkIfVisa(size, value)) {
			return SDKCardBrandType.SDK_VISA;
		} else if (checkIfMasterCard(size, value)) {
			return SDKCardBrandType.SDK_MASTER_CARD;
		} else if (checkIfDiscover(size, value)) {
			return SDKCardBrandType.SDK_DISCOVER;
		} else if (checkIfAmericanExpress(size, value)) {
			return SDKCardBrandType.SDK_AMERICAN_EXPRESS;
		} else if (checkIfDinnersClub(size, value)) {
			return SDKCardBrandType.SDK_DINERS_CLUB;
		} else if (checkIfJCB(size, value)) {
			return SDKCardBrandType.SDK_JCB;
		} else if (checkChinaUnionParty(size, value)) {
			return SDKCardBrandType.SDK_CHINESE_UNIONPAY;
		} else {
			return SDKCardBrandType.SDK_OTHER;
		}
	}

	// Card rules
	// American Express starts with: 34, 37 (length: 15)
	//
	// Diners Club Carte Blanche starts with: 300-305 (length: 14 )
	//
	// Discover Card starts with: 6011, 622, 64, 65 (length: 16 )
	//
	// JCB starts with: 3528-3589 (length: 16)
	//
	// MasterCard starts with: 51-55 (length: 16)
	//
	// Visa starts with: 4 (length: 13, 16)
	//
	// China UnionPay starts with: 62 or 88 (length: 16-19)

	private static boolean checkIfVisa(int size, String value) {
		if ((size == 13 || size == 16) && value.startsWith("4")) {
			return true;
		} else {
			return false;
		}
	}

	private static boolean checkIfMasterCard(int size, String value) {
		int firstChars = Integer.parseInt(value.substring(0, 2));
		if (size == 16 && (firstChars >= 51 && firstChars <= 55)) {
			return true;
		} else {
			return false;
		}
	}

	private static boolean checkIfDiscover(int size, String value) {
		if (size == 16
				&& (value.startsWith("6011") || value.startsWith("622") || value.startsWith("64") || value
						.startsWith("65"))) {
			return true;
		} else {
			return false;
		}
	}

	private static boolean checkIfAmericanExpress(int size, String value) {
		if (size == 15 && (value.startsWith("34") || value.startsWith("37"))) {
			return true;
		} else {
			return false;
		}
	}

	private static boolean checkIfDinnersClub(int size, String value) {
		int firstChars = Integer.parseInt(value.substring(0, 3));
		if ((firstChars >= 300 && firstChars <= 305) && (size == 14 || size == 16)) {
			return true;
		} else {
			return false;
		}
	}

	private static boolean checkIfJCB(int size, String value) {
		int firstChars = Integer.parseInt(value.substring(0, 4));
		if (firstChars >= 3528 && firstChars <= 3589 && size == 16) {
			return true;
		} else {
			return false;
		}
	}

	private static boolean checkChinaUnionParty(int size, String value) {
		if ((size >= 16 && size <= 19) && (value.startsWith("62") || value.startsWith("88"))) {
			return true;
		} else {
			return false;
		}
	}
}