package com.mazlow.payments_subscription.activities.billing_address;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;

import com.mazlow.Networking.CrafClickUk;
import com.mazlow.Networking.RestApiClientAuth;
import com.mazlow.Networking.RestApiInterface;
import com.mazlow.customclasses.M;
import com.mazlow.payments_subscription.activities.billing_address.model.BillingAddressResponse;
import com.mazlow.payments_subscription.activities.select_cards.SelectCardView;
import com.mazlow.signup.postalcode.model.PostalCodeResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BillingAddressPresenterImplement implements BillingAddressPresenter {

    Context context;
    BillingAddrssView billingAddress;
    Dialog dialog;


    public BillingAddressPresenterImplement(BillingAddress selectCardView, Context context) {
        this.billingAddress = selectCardView;
        this.context = context;
        dialog = M.showloader(context, "", false, false);
    }


    @Override
    public void dobillingAddress(String token,String amount, String city, String address, String postalCode, String countryCode, String transactionName, String corporateDc, String currencyCode,String subscriptionId) {

        if (M.isNetworkAvailable(context)) {
            dialog.show();
            RestApiInterface apiInterface = RestApiClientAuth.Retrofit().create(RestApiInterface.class);
            Call<BillingAddressResponse> call = apiInterface.billingAddress(token,amount, city,address,postalCode,countryCode,transactionName,corporateDc,currencyCode,subscriptionId);
            call.enqueue(new Callback<BillingAddressResponse>() {
                @Override
                public void onResponse(Call<BillingAddressResponse> call, Response<BillingAddressResponse> response) {
                    dialog.dismiss();
                    if (response.isSuccessful()) {

                        BillingAddressResponse billingAddressResponse= response.body();

                        if (billingAddressResponse != null && billingAddressResponse.getSuccess() == true) {


                            billingAddress.billingAddressSuccess(billingAddressResponse);
                        }
                        else {
                            billingAddress.billingAddrssError("Something Wrong");
                        }

                    } else {
                        billingAddress.billingAddrssError("");
                    }
                }

                @Override
                public void onFailure(Call<BillingAddressResponse> call, Throwable t) {
                    Log.e("error", String.valueOf(t));
                    dialog.dismiss();
                }
            });
        }
        else {
            billingAddress.noInternet("ok");

    }
    }
}
