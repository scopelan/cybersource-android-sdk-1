package com.visa.inappsdk.common.error;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * A common interface used to represent errors returned by the sdk.
 *
 * Created by fzubair on 10/7/2015.
 */
public interface SDKError extends Serializable{
    /**
     * @return a unique code related to this error
     */
    public abstract int getErrorCode();

    /**
     * @return a standard message describing the source of the error
     */
    public abstract String getErrorMessage();

    /**
     * @return a extra message describing the source of the error
     */
    public abstract String getErrorExtraMessage();

    /**
     * @param errorExtraMessage a additional message that that can be set to
     *            provide more details about an error
     */
    public abstract void setErrorExtraMessage(String errorExtraMessage);

    /**
     * @return a type of the error
     */
    public abstract SDKErrorType getErrorType();
}
