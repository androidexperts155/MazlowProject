package com.mazlow.signup.postalcode;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;

import com.mazlow.Networking.CrafClickUk;
import com.mazlow.Networking.RestApiClientAuth;
import com.mazlow.Networking.RestApiInterface;
import com.mazlow.adduserdetails.SecondPageActivity;
import com.mazlow.customclasses.M;
import com.mazlow.signup.models.SignupResponseModel;
import com.mazlow.signup.postalcode.model.PostalCodeResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostalCodeImplementation implements PostalcodePresenter{

    postalcodeView signupView;
    Context context;
    Dialog dialog;

    public PostalCodeImplementation(postalcodeView signupView, Context context) {
        this.signupView = signupView;
        this.context = context;
    }

    @Override
    public void dopostalSearch(String phonenumber, String ccode) {
        if (M.isNetworkAvailable(context)) {
            dialog.show();
            RestApiInterface apiInterface = CrafClickUk.Retrofit().create(RestApiInterface.class);
            Call<PostalCodeResponse> call = apiInterface.postalcodeAddress(phonenumber, ccode);
            call.enqueue(new Callback<PostalCodeResponse>() {
                @Override
                public void onResponse(Call<PostalCodeResponse> call, Response<PostalCodeResponse> response) {
                    dialog.dismiss();
                    if (response.isSuccessful()) {
                        PostalCodeResponse signupResponseModel = response.body();
                        if (response != null && response.body().getThoroughfareCount() == null) {

                            signupView.onErrorPostal("");

                            }
                            else {
                            signupView.onSuccessPostal(signupResponseModel);
                            }
                        } else {
                            signupView.onErrorPostal("");
                        }
                    }

                @Override
                public void onFailure(Call<PostalCodeResponse> call, Throwable t) {
                    Log.e("error", String.valueOf(t));
                    dialog.dismiss();
                }
            });
        }
        else {
            signupView.noInternetPostal("ok");
        }
    }
}
