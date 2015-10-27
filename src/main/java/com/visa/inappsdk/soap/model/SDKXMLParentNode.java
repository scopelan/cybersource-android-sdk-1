package com.visa.inappsdk.soap.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Class representing parent node with sub nodes.
 * 
 * @author fzubair
 */
public class SDKXMLParentNode extends SDKXMLNode {
	/** List of sub elements of the node. */
	private List<SDKXMLNode> subElements = new ArrayList<SDKXMLNode>();

	/**
	 * @param namespace The namespace for the new node.
	 * @param name The name for the new node.
	 */
	public SDKXMLParentNode(String namespace, String name) {
		super(namespace, name);
	}

	/**
	 * @return list of all child nodes of this parent node
	 */
	public List<SDKXMLNode> getChildNodes() {
		return Collections.unmodifiableList(subElements);
	}

	/**
	 * Adds new node to the children nodes collection.
	 * 
	 * @param namespace The namespace for the new node.
	 * @param name The name for the new node.
	 * @return Newly created node.
	 */
	public SDKXMLParentNode addNode(String namespace, String name) {
		SDKXMLParentNode newNode = new SDKXMLParentNode(namespace, name);
		subElements.add(newNode);
		return newNode;
	}

	/**
	 * Adds the provided node to the children nodes collection.
	 * 
	 * @param node The node that will be added.
	 */
	public SDKXMLNode addNode(SDKXMLNode node) {
		subElements.add(node);
		return node;
	}

	/**
	 * Adds new text node to the children nodes collection.
	 * 
	 * @param namespace The namespace for the new node.
	 * @param name The name for the new node.
	 * @param value The value of the newly created text node.
	 * @return Newly created node.
	 */
	public SDKXMLTextNode addTextNode(String namespace, String name, String value) {
		SDKXMLTextNode newTextElement = new SDKXMLTextNode(namespace, name, value);
		subElements.add(newTextElement);
		return newTextElement;
	}

/*	protected SDKXMLParentNode(Parcel in){
        readFromParcel(in);
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		super.writeToParcel(dest, flags);
		dest.writeTypedList(subElements);
	}

    @Override
	public void readFromParcel(Parcel in) {
		super.readFromParcel(in);
		in.readTypedList(subElements, SDKXMLNode.CREATOR);
	}

	public static final Parcelable.Creator<SDKXMLParentNode> CREATOR = new Parcelable.Creator<SDKXMLParentNode>() {

        @Override
        public SDKXMLParentNode createFromParcel(Parcel in) {
			return new SDKXMLParentNode(in);
		}

        @Override
		public SDKXMLParentNode[] newArray(int size) {
			return new SDKXMLParentNode[size];
		}
	};*/
}
