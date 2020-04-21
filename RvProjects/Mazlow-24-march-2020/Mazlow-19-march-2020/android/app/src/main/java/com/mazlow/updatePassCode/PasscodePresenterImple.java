package com.mazlow.updatePassCode;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import com.mazlow.Networking.RestApiClientAuth;
import com.mazlow.Networking.RestApiInterface;
import com.mazlow.customclasses.M;
import com.mazlow.signup.models.SignupResponseModel;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PasscodePresenterImple implements PasscodePresenter {

    PassCodeView passCodeView;
    Context context;
    Dialog dialog;


    public PasscodePresenterImple(PassCodeView passCodeView, Context createSecondPassword1) {
        this.passCodeView = passCodeView;
        this.context = createSecondPassword1;
        dialog = M.showloader(context, "", false, false);

    }

    @Override
    public void dopasscode(String accesstoken, String passcode) {
        if (M.isNetworkAvailable(context))
        {
            dialog.show();
            RestApiInterface apiInterface = RestApiClientAuth.Retrofit().create(RestApiInterface.class);
            Call<SignupResponseModel> call = apiInterface.updatpasscode(accesstoken,passcode);
            call.enqueue(new Callback<SignupResponseModel>() {
                @Override
                public void onResponse(Call<SignupResponseModel> call, Response<SignupResponseModel> response) {
                    dialog.dismiss();

                    if (response.isSuccessful()) {
                        SignupResponseModel signupResponseModel=response.body();
                        if (response!=null&&response.body().getSuccess()==true) {
                            if (response.body().getMessage().equals("User updated successfully"))
                            {
                                passCodeView.onSuccess(signupResponseModel);
                            }
                            else
                            {
                                passCodeView.onError(signupResponseModel.getMessage());
                            }
                        }
                        else
                        {
                            passCodeView.onError(signupResponseModel.getMessage());
                        }
                    }
                }
                @Override
                public void onFailure(Call<SignupResponseModel> call, Throwable t) {
                    Log.e("error", String.valueOf(t));
                    dialog.dismiss();
                }
            });
        }
        else
        {
            passCodeView.noInternet("ok");
        }

    }
}
