package com.visa.inappsdk.datamodel.transaction.fields;

import com.visa.inappsdk.common.SDKCardBrandType;
import com.visa.inappsdk.common.exceptions.SDKInvalidCardException;
import com.visa.inappsdk.common.utils.SDKCardUtils;

/**
 * This class represents the manual card entry.
 * 
 * Created by fzubair on 10/8/2015.
 */
public final class SDKCardData{

	//	required
	private final String cardNumber;
	private final String expirationMonth;
	private final String expirationYear;
	private final String cvv;
	private final String zip;

	// optional
	private final SDKCardBrandType cardBrandType;
	private final String lastFourDigits;
	private final SDKCardFundingType fundingType;
	private final SDKCardTokenizationMethod tokenizationMethod;

	/**
	 * Creates an instance of object to store keyed card data. Also it sets a
	 */
	private SDKCardData(Builder builder) {
		this.cardNumber = builder.cardNumber;
		this.expirationMonth = builder.expirationMonth;
		this.expirationYear = builder.expirationYear;
		this.cvv = builder.cvv;
		this.zip = builder.zip;
		this.cardBrandType = builder.cardBrandType;
		this.lastFourDigits = builder.lastFourDigits;
		this.fundingType = builder.fundingType;
		this.tokenizationMethod = builder.tokenizationMethod;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public String getCardExpirationMonth() {
		return expirationMonth;
	}

	public String getCardExpirationYear() {
		return expirationYear;
	}

	public String getZip() {
		return zip;
	}

	public String getCvv() {
		return cvv;
	}

	public SDKCardTokenizationMethod getTokenizationMethod() {
		return tokenizationMethod;
	}

	public SDKCardBrandType getCardBrandType() {
		return cardBrandType;
	}

	public SDKCardFundingType getFundingType() {
		return fundingType;
	}

	public String getLastFourDigits() {
		return lastFourDigits;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((cardNumber == null) ? 0 : cardNumber.hashCode());
		result = prime * result + ((cvv == null) ? 0 : cvv.hashCode());
		result = prime * result + ((expirationMonth == null) ? 0 : expirationMonth.hashCode());
		result = prime * result + ((expirationYear == null) ? 0 : expirationYear.hashCode());
		result = prime * result + ((zip == null) ? 0 : zip.hashCode());
		result = prime * result + ((cardBrandType == null) ? 0 : cardBrandType.hashCode());
		result = prime * result + ((lastFourDigits == null) ? 0 : lastFourDigits.hashCode());
		result = prime * result + ((fundingType == null) ? 0 : fundingType.hashCode());
		result = prime * result + ((tokenizationMethod == null) ? 0 : tokenizationMethod.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof SDKCardData)) {
			return false;
		}
		SDKCardData other = (SDKCardData)obj;
		if (cardNumber == null) {
			if (other.cardNumber != null) {
				return false;
			}
		} else if (!cardNumber.equals(other.cardNumber)) {
			return false;
		}
		if (cvv == null) {
			if (other.cvv != null) {
				return false;
			}
		} else if (!cvv.equals(other.cvv)) {
			return false;
		}
		if (expirationMonth == null) {
			if (other.expirationMonth != null) {
				return false;
			}
		} else if (!expirationMonth.equals(other.expirationMonth)) {
			return false;
		}
		if (expirationYear == null) {
			if (other.expirationYear != null) {
				return false;
			}
		} else if (!expirationYear.equals(other.expirationYear)) {
			return false;
		}
		if (zip == null) {
			if (other.zip != null) {
				return false;
			}
		} else if (!zip.equals(other.zip)) {
			return false;
		}
		if (cardBrandType != other.cardBrandType) {
			return false;
		}
		if (lastFourDigits == null) {
			if (other.lastFourDigits != null) {
				return false;
			}
		} else if (!lastFourDigits.equals(other.lastFourDigits)) {
			return false;
		}
		if (fundingType != other.fundingType) {
			return false;
		}
		if (tokenizationMethod != other.tokenizationMethod) {
			return false;
		}

		return true;
	}

	public static class Builder {
		//	required
		private String cardNumber;
		private String expirationMonth;
		private String expirationYear;
		private final String cvv;
		private String zip;

		// optional
		private SDKCardBrandType cardBrandType;
		private String lastFourDigits;
		private SDKCardFundingType fundingType;
		private SDKCardTokenizationMethod tokenizationMethod;

		public Builder(String cardNumber, String expirationMonth, String expirationYear,
					   String cvv) throws SDKInvalidCardException {
			if(SDKCardUtils.isValid(cardNumber)) {
				this.cardNumber = cardNumber;
			}
			if(SDKCardUtils.isValidExpirationDate(expirationMonth, expirationYear)) {
                this.expirationMonth = expirationMonth;
				this.expirationYear = expirationYear;
			}
			this.cvv = cvv;
			setBrandAndLastFourDigits();
		}

		public SDKCardData.Builder setCardZipCode(String zip) {
			this.zip = zip;
			return this;
		}

		public SDKCardData.Builder setCardBrandType(SDKCardBrandType cardBrandType) {
			this.cardBrandType = cardBrandType;
			return this;
		}

		public SDKCardData.Builder setCardFundingType(SDKCardFundingType fundingType) {
			this.fundingType = fundingType;
			return this;
		}

		public SDKCardData.Builder setCardTokenizationMethod(SDKCardTokenizationMethod method) {
			this.tokenizationMethod = method;
			return this;
		}

		public SDKCardData build(){
			return new SDKCardData(this);
		}

		private void setBrandAndLastFourDigits() throws SDKInvalidCardException {
			this.cardBrandType = SDKCardUtils.getBrandByCardNumber(cardNumber);
			// if there are at least 4 digits
			if (cardNumber != null && cardNumber.length() >= 4) {
				lastFourDigits = cardNumber.substring(cardNumber.length() - 4, cardNumber.length());
			}
		}
	}

}
