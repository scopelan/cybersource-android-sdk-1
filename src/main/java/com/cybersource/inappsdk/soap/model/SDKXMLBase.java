package com.cybersource.inappsdk.soap.model;

import java.io.Serializable;

/**
 * Base class for {@link SDKXMLAttribute} and {@link SDKXMLNode}.
 *
 * @author fzubair
 */

public abstract class SDKXMLBase implements Serializable{

	/** The namespace of the element. */
	protected String namespace;
	/** The name of the element. */
	protected String name;

	/**
	 * Instantiates a new XMLObject.
	 * 
	 * @param namespace The namespace of the object (can be null).
	 * @param name The name of the object.
	 */
	public SDKXMLBase(String namespace, String name) {
		this.namespace = namespace;
		this.name = name;
	}

	protected SDKXMLBase() {
	}

	/**
	 * Get the namespace URI for this element.
	 * 
	 * @return The namespace URI, as a String.
	 */
	public String getNamespace() {
		return namespace;
	}

	/**
	 * Sets the namespace of this element.
	 * 
	 * @param namespace The namespace URI as a String.
	 */
	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	/**
	 * Get the name of the element.
	 * 
	 * @return The name as a string.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of the element.
	 * 
	 * @param name The new name as a string.
	 */
	public void setName(String name) {
		this.name = name;
	}

/*	protected SDKXMLBase(Parcel in){
		readFromParcel(in);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(namespace);
		dest.writeString(name);
	}

	public void readFromParcel(Parcel in) {
		namespace = in.readString();
		name = in.readString();
	}*/

}
