package com.cybersource.inappsdk.common;

/**
 * Describes Card brand type.
 * <ul>
 * <li>000 - SDK_OTHER</li>
 * <li>001 - SDK_VISA</li>
 * <li>002 - SDK_MASTER_CARD</li>
 * <li>003 - SDK_AMERICAN_EXPRESS</li>
 * <li>004 - SDK_DISCOVER</li>
 * <li>005 - SDK_DINERS_CLUB</li>
 * <li>006 - SDK_CARTE_BLANCHE</li>
 * <li>007 - SDK_JCB</li>
 * <li>008 - SDK_CHINESE_UNIONPAY</li>
 * <li>999 - SDK_INVALID_CARD_NUMBER</li>
 * </ul>
 * 
 * @author fzubair
 */
public enum SDKCardBrandType {

    SDK_INVALID_CARD_NUMBER("999", ""), SDK_INVALID_CARD_CODE("", ""), SDK_INVALID_CARD_NAME("", ""), SDK_OTHER("000", ""), SDK_VISA("001", "Visa"), SDK_MASTER_CARD("002",
			"Master Card"), SDK_AMERICAN_EXPRESS("003", "American Express"), SDK_DISCOVER("004", "Discover"), SDK_DINERS_CLUB(
			"005", "Diners Club"), SDK_CARTE_BLANCHE("006", "Carte Blanche"), SDK_JCB("007", "JCB"), SDK_CHINESE_UNIONPAY(
			"008", "Chinese UnionPay");

	private String brandCode;
	private String brandName;

	SDKCardBrandType(String code, String name) {
		brandCode = code;
		brandName = name;
	}

	public String getBrandCode() {
		return brandCode;
	}

	public String getBrandName() {
		return brandName;
	}

	/**
	 * @param code three digit code of the card brand type
	 * @return a proper <code>SDKCardBrandType</code> or
	 *         <code>SDK_OTHER</code> when code was not recognized
	 */
	public static SDKCardBrandType getCardBrandTypeByCode(String code) {
        if(code == null || code.isEmpty())
            return SDK_INVALID_CARD_NAME;

		for (SDKCardBrandType type : values()) {
			if (type.brandCode.equals(code)) {
				return type;
			}
		}

		return SDK_OTHER;
	}
	
	public static SDKCardBrandType getCardBrandTypeByName(String name) {
        if(name == null || name.isEmpty())
            return SDK_INVALID_CARD_NAME;

		for (SDKCardBrandType type : values()) {
			if (naturalize(type.brandName).equals(naturalize(name))) {
				return type;
			}
		}

		return SDK_OTHER;
	}
	
	private static String naturalize(String name) {
		if (name != null) {
			return name.replace(" ","").toLowerCase();
		} else {
			return "";
		}
	}
}
