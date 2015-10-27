package com.visa.inappsdk.common.error;

/**
 * Describes all error types, which can occurs during encryption.
 *
 * Created by fzubair on 10/7/2015.
 */
public enum SDKEncryptError implements SDKError {
    SDK_AUTHORIZATION_INVALID_CREDENTIALS(4000, "Invalid Credentials: Unable to log in"),
    SDK_AUTHORIZATION_ACCESS_DENIED(4001, "Access Denied: No permission to log in"),
    SDK_AUTHORIZATION_LOCKED_OUT(4002, "Lockout: You are locked out. Call an administrator to get unlocked"),
    SDK_AUTHORIZATION_CONNECTION_TIMEOUT(4003, "Timeout: Gateway is not responding"),
    SDK_AUTHORIZATION_DEVICE_NOT_APPROVED(4004, "Device not approved: Each smartphone/tablet must be approved by CyberSource online"),
    SDK_AUTHORIZATION_DEVICE_LIMITED_OR_DISABLED(4005, "Device limited or disabled: The device is limited or disabled by CyberSource"),
    SDK_AUTHORIZATION_SESSION_TIMEOUT(4006, "Session Timeout: Session has expired"),
    SDK_AUTHORIZATION_UNKNOWN_HOST(4007, "Unknown Host: Unable to resolve given host. No address associated with hostname"),
    SDK_AUTHORIZATION_OTHER_GENERAL(4008, "General: Other type of error");

    private int errorCode;
    private String errorMessage;
    private String errorExtraMessage;

    SDKEncryptError(int errorCode, String errorMessage) {
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
        return SDKErrorType.SDK_ERROR_TYPE_ENCRYPTION;
    }

    public static SDKEncryptError getAuthorizationErrorByErrorCode(int errorCode) {
        switch (errorCode) {
            case 4000:
                return SDKEncryptError.SDK_AUTHORIZATION_INVALID_CREDENTIALS;
            case 4001:
                return SDKEncryptError.SDK_AUTHORIZATION_ACCESS_DENIED;
            case 4002:
                return SDKEncryptError.SDK_AUTHORIZATION_LOCKED_OUT;
            case 4003:
                return SDKEncryptError.SDK_AUTHORIZATION_CONNECTION_TIMEOUT;
            case 4004:
                return SDKEncryptError.SDK_AUTHORIZATION_DEVICE_NOT_APPROVED;
            case 4005:
                return SDKEncryptError.SDK_AUTHORIZATION_SESSION_TIMEOUT;
            case 4006:
                return SDKEncryptError.SDK_AUTHORIZATION_UNKNOWN_HOST;
            case 4007:
                return SDKEncryptError.SDK_AUTHORIZATION_OTHER_GENERAL;
            default:
                return null;
        }
    }
}
