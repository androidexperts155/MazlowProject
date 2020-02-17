package com.mazlow.signup;
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
public class SignupPresenterImple implements SignupPresenter {

    SignupView signupView;
    Context context;
    Dialog dialog;

    public SignupPresenterImple(SignupView signupActivity, Context context) {
        this.signupView = signupActivity;
        this.context = context;
        dialog = M.showloader(context, "", false, false);
    }
    @Override
    public void doSignup(String phonenumber, String ccode) {
        if (M.isNetworkAvailable(context)) {
            dialog.show();
            RestApiInterface apiInterface = RestApiClientAuth.Retrofit().create(RestApiInterface.class);
            Call<SignupResponseModel> call = apiInterface.signup(phonenumber, ccode);
            call.enqueue(new Callback<SignupResponseModel>() {
                @Override
                public void onResponse(Call<SignupResponseModel> call, Response<SignupResponseModel> response) {
                    dialog.dismiss();
                    if (response.isSuccessful()) {

                        SignupResponseModel signupResponseModel = response.body();
                        if (response != null && response.body().getSuccess() == true) {
                            if (response.body().getMessage().equals("Successfully registered, for verification please enter verification code")) {
                                signupView.onSuccess(signupResponseModel);
                                M.showToast(context,signupResponseModel.getMessage());
                            }
                            else {
                                signupView.onError(signupResponseModel.getMessage());
                            }
                        } else {
                            signupView.onError(signupResponseModel.getMessage());
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
        else {
            signupView.noInternet("ok");
        }

    }
}
