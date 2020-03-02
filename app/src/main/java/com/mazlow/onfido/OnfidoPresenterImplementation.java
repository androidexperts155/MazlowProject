package com.mazlow.onfido;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;

import com.mazlow.Networking.OnFidoClient;
import com.mazlow.Networking.RestApiInterface;
import com.mazlow.onfido.activites.FourthSignupActivity;
import com.mazlow.onfido.model.OnFidoResponseModel;
import com.mazlow.customclasses.M;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OnfidoPresenterImplementation implements OnfidoPrsenter {

    Dialog dialog;
    Context context;
    OnfidoView onfidoView;


    public OnfidoPresenterImplementation(Activity fourthSignupActivity, FourthSignupActivity fourthSignupActivity1) {

        context=fourthSignupActivity;
        onfidoView =fourthSignupActivity1;
        dialog = M.showloader(context, "", false, false);

    }

    @Override
    public void onFidoApplication(String token, String firstname, String lastname, String dop, String address, String email, String postalcode) {
        if (M.isNetworkAvailable(context)){
            dialog.show();
            RestApiInterface apiInterface = OnFidoClient.Retrofit().create(RestApiInterface.class);
            Call<OnFidoResponseModel> call = apiInterface.onfido(token,firstname,lastname,dop,email,address,postalcode);
            call.enqueue(new Callback<OnFidoResponseModel>() {
                @Override
                public void onResponse(Call<OnFidoResponseModel> call, Response<OnFidoResponseModel> response) {
                    dialog.dismiss();
                    if (response.isSuccessful()) {

                        OnFidoResponseModel onFidoResponseModel=response.body();
                        onfidoView.Sucess(onFidoResponseModel);
                    }
                    else
                    {
                        onfidoView.error("Somthing is wrong");
//                            addDetailsView.onError(signupResponseModel.getMessage());
                    }
                }

                @Override
                public void onFailure(Call<OnFidoResponseModel> call, Throwable t) {

                    //  addDetailsView.onError(t.getMessage());
                    Log.e("error", String.valueOf(t));
                    dialog.dismiss();
                }
            });

        }
        else{
            //addDetailsView.noInternet("login");
        }
    }
}
