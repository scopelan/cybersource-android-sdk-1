package com.cybersource.inappsdk.soap.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Class representing single XML node.
 * 
 * @author fzubair
 */
public class SDKXMLNode extends SDKXMLBase {
	/**
	 * Set of attributes for this node element.
	 */
	private Set<SDKXMLAttribute> attributes = new HashSet<SDKXMLAttribute>();
	/**
	 * Namespace prefixes declared in this element (map of prefixes to urls).
	 */
	private Map<String, String> declaredPrefixes = new HashMap<String, String>();

	/**
	 * Creates a new XML Element.
	 * 
	 * @param namespace The namespace of the element (can be null).
	 * @param name The name of the element.
	 */
	public SDKXMLNode(String namespace, String name) {
		super(namespace, name);
	}

	/**
	 * @return Collection of XML attributes of this node.
	 */
	public Collection<SDKXMLAttribute> getAttributes() {
		return attributes;
	}

	/**
	 * Adds new attribute to the node.
	 * 
	 * @param namespace Attribute namespace.
	 * @param name Attribute name.
	 * @param value Attribute value.
	 */
	public void addAttribute(String namespace, String name, String value) {
		SDKXMLAttribute att = new SDKXMLAttribute(namespace, name, value);
		attributes.add(att);
	}

	/**
	 * Adds the provided attribute to the node.
	 * 
	 * @param att Provided attribute to be added to attributes collection.
	 */
	public void addAttribute(SDKXMLAttribute att) {
		attributes.add(att);
	}

	/**
	 * Adds new prefix (maps prefix to url).
	 * 
	 * @param prefix Namespace prefix.
	 * @param namespace Namespace URL.
	 */
	public void declarePrefix(String prefix, String namespace) {
		declaredPrefixes.put(prefix, namespace);
	}

	/**
	 * @return Map of declared prefixes (mappings of prefixes to namespaces
	 *         URLs).
	 */
	public Map<String, String> getDeclaredPrefixes() {
		return declaredPrefixes;
	}

    SDKXMLNode(){
    }

/*

	protected SDKXMLNode(Parcel in){
        readFromParcel(in);
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
		List attributesList = new ArrayList<>(attributes);
		dest.writeTypedList(attributesList);

		dest.writeInt(declaredPrefixes.size());
		for(Map.Entry<String,String> entry : declaredPrefixes.entrySet()){
			dest.writeString(entry.getKey());
			dest.writeString(entry.getValue());
		}
	}

    @Override
	public void readFromParcel(Parcel in) {
        super.readFromParcel(in);
        List attributeList = new ArrayList<>();
        in.readTypedList(attributeList, SDKXMLAttribute.CREATOR);
		attributes = new HashSet<>(attributeList);
		declaredPrefixes = new HashMap();
		int size = in.readInt();
		for(int i = 0; i < size; i++){
			String key = in.readString();
			String value = in.readString();
			declaredPrefixes.put(key,value);
		}
	}

	public static final Parcelable.Creator<SDKXMLNode> CREATOR = new Parcelable.Creator<SDKXMLNode>() {

        @Override
        public SDKXMLNode createFromParcel(Parcel in) {
			return new SDKXMLNode(in);
		}

        @Override
		public SDKXMLNode[] newArray(int size) {
			return new SDKXMLNode[size];
		}
	};*/
}
