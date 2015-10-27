package com.visa.inappsdk.soap.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Class representing single XML text node.
 * 
 * @author fzubair
 */
public class SDKXMLTextNode extends SDKXMLNode {

	private String value;

	/**
	 * Creates a new XML Element.
	 * 
	 * @param namespace The namespace of the element (can be null).
	 * @param name The name of the element.
	 */
	public SDKXMLTextNode(String namespace, String name, String value) {
		super(namespace, name);
		this.value = value;
	}

	/**
	 * @return Text value of this text node XML element.
	 */
	public String getValue() {
		return value;
	}

/*	protected SDKXMLTextNode(Parcel in){
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
