package com.visa.inappsdk.datamodel.transaction.fields;

import com.visa.inappsdk.common.exceptions.SDKInvalidCardException;

/**
 * Created by fzubair on 11/16/2015.
 */
public final class SDKBillTo {
    //	required
    private String firstName;
    private String lastName;
    private String postalCode;

    // optional

    /**
     * Creates an instance of object to store keyed card data. Also it sets a
     */
    private SDKBillTo(Builder builder){
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.postalCode = builder.postalCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPostalCode() {
        return postalCode;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((postalCode == null) ? 0 : postalCode.hashCode());
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
        SDKBillTo other = (SDKBillTo)obj;
        if (firstName == null) {
            if (other.firstName != null) {
                return false;
            }
        } else if (!firstName.equals(other.firstName)) {
            return false;
        }
        if (lastName == null) {
            if (other.lastName != null) {
                return false;
            }
        } else if (!lastName.equals(other.lastName)) {
            return false;
        }
        if (postalCode == null) {
            if (other.postalCode != null) {
                return false;
            }
        } else if (!postalCode.equals(other.postalCode)) {
            return false;
        }
        return true;
    }

    public static class Builder {
        //	required
        private String firstName;
        private String lastName;
        private String postalCode;

        // optional

        public Builder() {
        }

        public SDKBillTo.Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public SDKBillTo.Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public SDKBillTo.Builder postalCode(String postalCode) {
            this.postalCode = postalCode;
            return this;
        }


        public SDKBillTo build(){
            return new SDKBillTo(this);
        }
    }

}
