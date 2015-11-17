package com.visa.inappsdk.common.error;

/**
 * Created by fzubair on 10/7/2015.
 */
public enum SDKInternalError implements SDKError {
    SDK_INTERNAL_ERROR_TRANSACTION_IN_PROGRESS(
            1001, "Another transaction is already in progress. Cancel/terminate previous transaction first."),
    SDK_INTERNAL_ERROR_OPERATION_NOT_AVAILABLE(
            1002, "This operation is not available in the current version of the library."),
    SDK_INTERNAL_ERROR_NETWORK_CONNECTION(
            1003, "Cannot access the network."),
    SDK_INTERNAL_ERROR_NETWORK_CONNECTION_TIMEOUT(
            1004, "Network connection timed out."),
    SDK_INTERNAL_ERROR_SERVER(
            1005, "Cannot contact the server."),
    SDK_INTERNAL_ERROR_GENERAL_FAILURE(
            1006, "Unknown general error occured."),
    SDK_INTERNAL_ERROR_RESULTS_NOT_FOUND(
            1007, "Results not found."),
    SDK_INTERNAL_ERROR_NO_AUTHENTICATION_CHALLANGES_FOUND(
            1008, "No authentication challenges found");

    private int errorCode;
    private String errorMessage;
    private String errorExtraMessage;

    SDKInternalError(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    @Override
    public int getErrorCode() {
        return errorCode;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public String getErrorExtraMessage() {
        return errorExtraMessage;
    }

    @Override
    public void setErrorExtraMessage(String errorExtraMessage) {
        this.errorExtraMessage = errorExtraMessage;
    }

    @Override
    public SDKErrorType getErrorType() {
        return SDKErrorType.SDK_ERROR_TYPE_INTERNAL;
    }

    public static SDKInternalError getInternalErrorByErrorCode(int errorCode) {
        switch (errorCode) {
            case 1001:
                return SDKInternalError.SDK_INTERNAL_ERROR_TRANSACTION_IN_PROGRESS;
            case 1002:
                return SDKInternalError.SDK_INTERNAL_ERROR_OPERATION_NOT_AVAILABLE;
            case 1003:
                return SDKInternalError.SDK_INTERNAL_ERROR_NETWORK_CONNECTION;
            case 1004:
                return SDKInternalError.SDK_INTERNAL_ERROR_SERVER;
            case 1005:
                return SDKInternalError.SDK_INTERNAL_ERROR_GENERAL_FAILURE;
            default:
                return null;
        }
    }
}
