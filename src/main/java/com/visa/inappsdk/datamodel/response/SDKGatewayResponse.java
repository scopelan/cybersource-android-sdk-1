package com.visa.inappsdk.datamodel.response;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * Response object of gateway requests
 * 
 * Created by fzubair on 10/8/2015.
 */
public class SDKGatewayResponse implements Parcelable{

	private final SDKResponseDecision decision;
	private final SDKResponseReasonCode reasonCode;
	private final String requestId;
    private final String requestToken;
	private final SDKGatewayResponseType type;
	private final BigDecimal authorizedAmount;
	private final BigDecimal refundedAmount;
	private final String authorizationCode;
	private final String date;
	private final String time;
	private final String emvTags;
	private final String encryptedPaymentData;
	//private final HashMap<String, String> nvpMap;

	private SDKGatewayResponse(Builder builder) {
		this.decision = builder.decision;
		this.reasonCode = builder.reasonCode;
		this.requestId = builder.requestId;
		this.requestToken = builder.requestToken;
		this.type = builder.type;
		this.authorizedAmount = builder.authorizedAmount;
		this.refundedAmount = builder.refundedAmount;
		this.authorizationCode = builder.authorizationCode;
		this.date = builder.date;
		this.time = builder.time;
		this.emvTags = builder.emvTags;
		this.encryptedPaymentData = builder.encryptedPaymentData;
		//this.nvpMap = builder.nvpMap;
	}

	public SDKGatewayResponseType getType() {
		return type;
	}

	public String getAuthorizationCode() {
		return authorizationCode;
	}

	public BigDecimal getAuthorizedAmount() {
		return authorizedAmount;
	}

	public String getDate() {
		return date;
	}

	public SDKResponseDecision getDecision() {
		return decision;
	}

	public String getEmvTags() {
		return emvTags;
	}

/*	public HashMap<String, String> getNvpMap() {
		return nvpMap;
	}*/

	public SDKResponseReasonCode getReasonCode() {
		return reasonCode;
	}

	public BigDecimal getRefundedAmount() {
		return refundedAmount;
	}

	public String getRequestId() {
		return requestId;
	}

    public String getRequestToken() {
        return requestToken;
    }

	public String getEncryptedPaymentData() {
		return encryptedPaymentData;
	}

	public String getTime() {
		return time;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	private SDKGatewayResponse(Parcel in){
		this.decision = (SDKResponseDecision) in.readSerializable();
		this.reasonCode = (SDKResponseReasonCode) in.readSerializable();
		this.requestId = in.readString();
		this.requestToken =  in.readString();
		this.type = (SDKGatewayResponseType) in.readSerializable();
		this.authorizedAmount = new BigDecimal(in.readString());
		this.refundedAmount = new BigDecimal(in.readString());
		this.authorizationCode = in.readString();
		this.date = in.readString();
		this.time = in.readString();
		this.emvTags = in.readString();
		this.encryptedPaymentData = in.readString();
/*		this.nvpMap = new HashMap();
		int size = in.readInt();
		for(int i = 0; i < size; i++){
			String key = in.readString();
			String value = in.readString();
			this.nvpMap.put(key,value);
		}*/
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeSerializable(decision);
		dest.writeSerializable(reasonCode);
		dest.writeString(requestId);
		dest.writeString(requestToken);
		dest.writeSerializable(type);
		dest.writeString(String.valueOf(authorizedAmount.doubleValue()));
		dest.writeString(String.valueOf(refundedAmount.doubleValue()));
		dest.writeString(authorizationCode);
		dest.writeString(date);
		dest.writeString(time);
		dest.writeString(emvTags);
		dest.writeString(encryptedPaymentData);
	}

	public static final Parcelable.Creator<SDKGatewayResponse> CREATOR = new Parcelable.Creator<SDKGatewayResponse>() {

		@Override
		public SDKGatewayResponse createFromParcel(Parcel in) {
			return new SDKGatewayResponse(in);
		}

		@Override
		public SDKGatewayResponse[] newArray(int size) {
			return new SDKGatewayResponse[size];
		}
	};

/*	public SDKGatewayResponse(SDKResponseDecision success, String requestId, SDKResponseReasonCode reasonCode,
							  SDKGatewayResponseType type, BigDecimal authorizedAmount, String authorizationCode, String date,
							  String time, String emvTags) {
		this.decision = success;
		this.requestId = requestId;
		this.reasonCode = reasonCode;
		this.type = type;
		this.authorizedAmount = authorizedAmount;
		this.authorizationCode = authorizationCode;
		this.date = date;
		this.time = time;
		this.emvTags = emvTags;
	}

	public SDKGatewayResponse(SDKGatewayResponseType type, String requestId, BigDecimal refundedAmount,
							  SDKResponseReasonCode reasonCode) {
		this.type = type;
		this.requestId = requestId;
		this.refundedAmount = refundedAmount;
		this.reasonCode = reasonCode;
	}

	public SDKGatewayResponse(String requestId, BigDecimal authorizedAmount, SDKResponseReasonCode reasonCode) {
		this.requestId = requestId;
		this.authorizedAmount = authorizedAmount;
		this.reasonCode = reasonCode;
	}

	public SDKGatewayResponse(BigDecimal authorizedAmount, SDKResponseReasonCode reasonCode) {
		this.authorizedAmount = authorizedAmount;
		this.reasonCode = reasonCode;
	}*/

	/**
	 * Creates a shallow copy of this object copying only <code>authorizedAmount</code> and <code>reasonCode</code>
	 * 
	 * @return
	 */
/*	public SDKGatewayResponse shallowCopy() {
		SDKGatewayResponse copy = new SDKGatewayResponse(authorizedAmount, reasonCode);
		return copy;
	}*/

	public static class Builder {
		private final SDKGatewayResponseType type;
		private final SDKResponseReasonCode reasonCode;
		private final String requestId;
		private final String requestToken;
		private SDKResponseDecision decision;
		private BigDecimal authorizedAmount;
		private BigDecimal refundedAmount;
		private String authorizationCode;
		private String date;
		private String time;
		private String emvTags;
		private String encryptedPaymentData;
		private HashMap<String, String> nvpMap;

		public Builder(SDKGatewayResponseType type, SDKResponseReasonCode reasonCode, String requestId, String requestToken){
			this.type = type;
			this.reasonCode = reasonCode;
			this.requestId = requestId;
			this.requestToken = requestToken;
		}

		public SDKGatewayResponse.Builder setTime(String time) {
			this.time = time;
			return this;
		}

		public SDKGatewayResponse.Builder setAuthorizationCode(String authorizationCode) {
			this.authorizationCode = authorizationCode;
			return this;
		}

		public SDKGatewayResponse.Builder setAuthorizedAmount(BigDecimal authorizedAmount) {
			this.authorizedAmount = authorizedAmount;
			return this;
		}

		public SDKGatewayResponse.Builder setDate(String date) {
			this.date = date;
			return this;
		}

		public SDKGatewayResponse.Builder setDecision(SDKResponseDecision decision) {
			this.decision = decision;
			return this;
		}

		public SDKGatewayResponse.Builder setEmvTags(String emvTags) {
			this.emvTags = emvTags;
			return this;
		}

        public SDKGatewayResponse.Builder setEncryptedPaymentData(String data) {
            this.encryptedPaymentData = data;
            return this;
        }

		public SDKGatewayResponse.Builder setNvpMap(HashMap<String, String> nvpMap) {
			this.nvpMap = nvpMap;
			return this;
		}

		public SDKGatewayResponse.Builder setRefundedAmount(BigDecimal refundedAmount) {
			this.refundedAmount = refundedAmount;
			return this;
		}

		public SDKGatewayResponse build(){
			return new SDKGatewayResponse(this);
		}
	}
}