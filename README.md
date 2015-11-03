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
3. Include the `cybersource-inapp-android-x.x.x.jar` into the `libs` folder of your Android application project.


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
                                          .setSDKConnectionCallback(this) // receive callbacks for connection results
                                          .setTransactionNamespace(TRANSACT_NAMESPACE) // optional
                                          .build();
```

2) To make the api call we need a transaction object that can be created as follows:

```java
SDKTransactionObject 
  // type of transaction object 
  .createTransactionObject(SDKTransactionType.SDK_TRANSACTION_ENCRYPTION)
  //merchant reference code can be set to anything meaningful
  .setMerchantReferenceCode("Android_Sample_Code")
  //card data to be encrypted
  .setCardData(prepareTestCardData())
  .build();
```

3) A card object can be created as follows:

```java
SDKCardData cardData = new SDKCardData.Builder(CARD_NUMBER,
                                               CARD_EXPIRATION_MONTH, // MM
                                               CARD_EXPIRATION_YEAR) // YYYY
                                               .setCardCVV(CARD_CVV) // XXX - optional
                                               .setCardZipCode(CARD_ZIP) // optional
                                               .setCardType(SDKCardType.PAN) //optional - if token, this must be set to SDKCardType.TOKEN
                                               .build();
```

4) Once we have the Api client and the transaction information ready, it's time to make a call to perform a specific Api.

```java
// parameters: 
// 1) InAppSDKApiClient.Api - Type of API to make a request
// 2) SDKTransactionObject - The transactionObject for current transaction
// 3) Signature String - fresh message signature for this transaction
apiClient.performApi(InAppSDKApiClient.Api.API_ENCRYPTION, transactionObject, generateSignature(transactionObject));
```

5) To get a response back, the Activity/fragment should implement the `SDKApiConnectionCallback` interface.

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
