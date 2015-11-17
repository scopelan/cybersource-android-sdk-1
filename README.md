# cybersource-android-sdk
This is a private repo for the CyberSource InApp SDK, it will be moved under CyberSource when it goes public

## SDK Installation

### Android Studio (or Gradle)

Jus add this line to your app's `build.gradle` inside the `dependencies` section as follows:

```groovy
    dependencies {
        compile 'com.cybersource:cybersource-inapp-android:+'
    }
```

### Eclipse

1. Download the Android SDK jar file (cybersource-inapp-android-x.x.x.jar) from cybersource.com/inapp-sdk.
2. Include the `cybersource-inapp-android-x.x.x.jar` into the `libs` folder of your Android application project.

### Certificates
The InAppSDK expects the Android app project to have the server certificates placed in it's `assets` folder under a new directory named `certificates`.

1. TEST ENVIRONMENT: Download the `.cer` file from https://mobiletest.ic3.com/mpos/transactionProcessor/
2. PROD ENVIRONMENT: Download the `.cer` file from https://mobile.ic3.com/mpos/transactionProcessor/

## SDK Usage
Once the installation is succesfully complete. Please follow this section on how to program an Android app with this SDK.

1) To initiate any requests with the SDK, we need to create an Api Client that will make Api requests on our behalf.
The InApp SDK Api client can be built as follows:

```java
// parameters:
// 1) Context - current context
// 2) InAppSDKApiClient.Environment - CYBS ENVIRONMENT
// 3) API_LOGIN_ID String - merchant's API LOGIN ID 
apiClient = new InAppSDKApiClient.Builder (getActivity(),
                                          InAppSDKApiClient.Environment.ENV_TEST, API_LOGIN_ID) 
                                          .sdkConnectionCallback(this) // receive callbacks for connection results
                                          .sdkApiProdEndpoint(PAYMENTS_PROD_URL) // option to configure PROD Endpoint
                                          .sdkApiTestEndpoint(PAYMENTS_TEST_URL) // option to configure TEST Endpoint
                                          .transactionNamespace(TRANSACT_NAMESPACE) // optional
                                          .connectionTimeout(5000) // optional connection time out in milliseconds
                                          .build();
```

2) To make the api call we need a transaction object that can be created as follows:

```java
SDKTransactionObject 
  // type of transaction object 
  .createTransactionObject(SDKTransactionType.SDK_TRANSACTION_ENCRYPTION)
  //merchant reference code can be set to anything meaningful
  .merchantReferenceCode("Android_Sample_Code")
  //card data to be encrypted
  .cardData(prepareTestCardData())
  //optional billing info
  .billTo(prepareBillingInfo())
  .build();
```

3) A card object can be created as follows:

```java
SDKCardData cardData = new SDKCardData.Builder(ACCOUNT_NUMBER,
                                               EXPIRATION_MONTH, // MM
                                               EXPIRATION_YEAR) // YYYY
                                               .cvNumber(CARD_CVV) // optional
                                               .type(SDKCardAccountNumberType.PAN) //optional - if token then not optional and must be set to SDKCardType.TOKEN
                                               .build();
```

4) Billing information can be created as follows:

```java
SDKBillTo billTo = new SDKBillTo.Builder()
                .firstName("First Name")
                .lastName("Last Name")
                .postalCode("98052")
                .build();
```

5) Once we have the Api client and the transaction information ready, it's time to make a call to perform a specific Api.

```java
// parameters: 
// 1) InAppSDKApiClient.Api - Type of API to make a request
// 2) SDKTransactionObject - The transactionObject for current transaction
// 3) Signature String - fresh message signature for this transaction
apiClient.performApi(InAppSDKApiClient.Api.API_ENCRYPTION, transactionObject, generateSignature(transactionObject));
```

6) To get a response back, the Activity/fragment should implement the `SDKApiConnectionCallback` interface.

```java
@Override
public void onApiConnectionFinished(SDKGatewayResponse response) 
{ 
  Toast.makeText(getActivity(), 
                 response.getType() + " : " + response.getDecision().toString(),
                 Toast.LENGTH_LONG)
                 .show();
}
```
**OR**

In case of an error:

```java
@Override
public void onErrorReceived(SDKError error) 
{ 
  Toast.makeText(getActivity(), 
                 error.getErrorExtraMessage().toString(),
                 Toast.LENGTH_LONG)
                 .show();
}
```
