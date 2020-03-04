package com.mazlow.payments_subscription.activities.billing_address;

import android.util.Log;

import com.mazlow.Networking.CrafClickUk;
import com.mazlow.Networking.RestApiInterface;
import com.mazlow.customclasses.M;
import com.mazlow.signup.postalcode.model.PostalCodeResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public interface BillingAddressPresenter {





    void dobillingAddress(String token,String amount,String city,String address,String postalCode,String countryCode,String transactionName,String corporateDc,String currencyCode,String subscriptionId);



}
