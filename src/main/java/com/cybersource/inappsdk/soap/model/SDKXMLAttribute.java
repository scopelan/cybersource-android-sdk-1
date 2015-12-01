package com.cybersource.inappsdk.soap.model;

/**
 * Class representing single XML attribute.
 *
 * @author fzubair
 */

public class SDKXMLAttribute extends SDKXMLBase {
	/** Value of the attribute. */
	private String value;

	/**
	 * @param namespace The namespace of the attribute (can be null).
	 * @param name The name of the attribute.
	 * @param value The value of the attribute.
	 */
	public SDKXMLAttribute(String namespace, String name, String value) {
		super(namespace, name);
		this.value = value;
	}

	SDKXMLAttribute(){
	}

	/**
	 * @return attribute value
	 */
	public String getValue() {
		return value;
	}

/*	protected SDKXMLAttribute(Parcel in){
		readFromParcel(in);
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		super.writeToParcel(dest, flags);
		dest.writeString(value);
	}

	@Override
	public void readFromParcel(Parcel in) {
		super.readFromParcel(in);
		value = in.readString();
	}

	public static final Parcelable.Creator<SDKXMLAttribute> CREATOR = new Parcelable.Creator<SDKXMLAttribute>() {

		@Override
		public SDKXMLAttribute createFromParcel(Parcel in) {
			return new SDKXMLAttribute(in);
		}

		@Override
		public SDKXMLAttribute[] newArray(int size) {
			return new SDKXMLAttribute[size];
		}
	};*/

}
