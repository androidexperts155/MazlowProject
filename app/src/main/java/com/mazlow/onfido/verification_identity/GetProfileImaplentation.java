package com.mazlow.onfido.verification_identity;

import android.content.Context;
import android.util.Log;

import com.mazlow.Networking.OnFidoClient;
import com.mazlow.Networking.RestApiClientAuth;
import com.mazlow.Networking.RestApiInterface;
import com.mazlow.customclasses.M;
import com.mazlow.login.model.LoginResponseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetProfileImaplentation implements GetProfilePrsenter {

    GetProfileView getProfileView;
    Context context;

    public GetProfileImaplentation(ConductingVerificationActivity conductingVerificationActivity, ConductingVerificationActivity conductingVerificationActivity1) {
        getProfileView = conductingVerificationActivity;

        context =conductingVerificationActivity;

    }

    @Override
    public void onGetProfile(String token) {
        if (M.isNetworkAvailable(context)){
            RestApiInterface apiInterface = RestApiClientAuth.Retrofit().create(RestApiInterface.class);
            Call<LoginResponseModel> call = apiInterface.getPriofle(token);
            call.enqueue(new Callback<LoginResponseModel>() {
                @Override
                public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {

                    if (response.isSuccessful()) {
                        LoginResponseModel LoginResponseModel=response.body();
                        getProfileView.Sucess(LoginResponseModel);
                    }
                    else
                    {
                        getProfileView.error("Somthing is wrong");
                    }
                }

                @Override
                public void onFailure(Call<LoginResponseModel> call, Throwable t) {

                    //  addDetailsView.onError(t.getMessage());
                    Log.e("error", String.valueOf(t));

                }
            });

        }
        else{
            getProfileView.noInternet("login");
        }

    }
}
