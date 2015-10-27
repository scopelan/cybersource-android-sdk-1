package com.visa.inappsdk.datamodel.transaction.fields;

import com.visa.inappsdk.common.SDKCurrency;

import java.math.BigDecimal;


/**
 * This class represents purchase and contains purchase data which are required
 * for transaction.
 * 
 * Created by fzubair on 10/8/2015.
 */
public final class SDKPurchaseDetails {

	//private BigDecimal tax = BigDecimal.ZERO;
	private SDKCurrency currency;
	private BigDecimal tip;
	private BigDecimal shippingAmount;
    private String commerceIndicator;

	private SDKPurchaseDetails(Builder builder) {
		this.currency = builder.currency;
		this.tip = builder.tip;
		this.shippingAmount = builder.tip;
		this.commerceIndicator = builder.commerceIndicator;
	}

	/**
	 * @return the tip
	 */
	public BigDecimal getTipAmount() {
		return tip;
	}

	/**
	 * @param tip the tip to set
	 */
	public void setTipAmount(BigDecimal tip) {
		this.tip = tip;
	}

	/**
	 * @return the currency
	 */
	public SDKCurrency getCurrency() {
		return currency;
	}

	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(SDKCurrency currency) {
		this.currency = currency;
	}

	/**
	 * @return the shippingAmount
	 */
	public BigDecimal getShippingAmount() {
		return shippingAmount;
	}

	/**
	 * @param shippingAmount the shippingAmount to set
	 */
	public void setShippingAmount(BigDecimal shippingAmount) {
		this.shippingAmount = shippingAmount;
	}

	public static class Builder {
		private final SDKCurrency currency;
		private BigDecimal tip = BigDecimal.ZERO;
		private BigDecimal shippingAmount = BigDecimal.ZERO;
		private String commerceIndicator;

		public Builder(SDKCurrency currency) {
			this.currency = currency;
		}

		public Builder tip(BigDecimal tip) {
			this.tip = tip;
			return this;
		}

		public Builder shippingAmount(BigDecimal shippingAmount) {
			this.shippingAmount = shippingAmount;
			return this;
		}

		public Builder commerceIndicator(String commerceIndicator) {
			this.commerceIndicator = commerceIndicator;
			return this;
		}

		public SDKPurchaseDetails build() {
			return new SDKPurchaseDetails(this);
		}
	}

/*	*//**
	 * @return the tax
	 *//*
	public BigDecimal getTaxPercentage() {
		return tax;
	}

	*//**
	 * @param //tax the tax to set
	 *//*
	public void setTaxPercentage(BigDecimal tax) {
		this.tax = tax;
	}*/

    public String getCommerceIndicator() {
        return commerceIndicator;
    }
/*
    public void setCommerceIndicator(VMposCommerceIndicator commerceIndicator) {
        this.commerceIndicator = commerceIndicator.toString();
    }*/

}
