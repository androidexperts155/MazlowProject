package com.mazlow.login;

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
public class LoginPresenterImple implements LoginPresenter {
    LoginView loginView;
    Context context;
    Dialog dialog;

    public LoginPresenterImple(LoginView loginView, Context context) {
        this.loginView = loginView;
        this.context = context;
        dialog = M.showloader(context, "", false, false);
    }
    @Override
    public void doLogin(String phonenumber, String ccode, String pcode) {
          if (M.isNetworkAvailable(context)){
              dialog.show();
              RestApiInterface apiInterface = RestApiClientAuth.Retrofit().create(RestApiInterface.class);
              Call<SignupResponseModel> call = apiInterface.login(phonenumber,ccode,pcode);
              call.enqueue(new Callback<SignupResponseModel>() {
                  @Override
                  public void onResponse(Call<SignupResponseModel> call, Response<SignupResponseModel> response) {
                      dialog.dismiss();
                      if (response.isSuccessful()) {
                          SignupResponseModel signupResponseModel=response.body();
                          if (response!=null&&response.body().getSuccess()==true) {
                              if (response.body().getMessage().equals("User logIn successfully")){
                                      loginView.onSuccess(signupResponseModel);
                              }
                              else
                                  {
                                  loginView.onError(signupResponseModel.getMessage());
                              }
                          }
                          else
                          {
                              loginView.onError(signupResponseModel.getMessage());
                          }
                      }
                  }
                  @Override
                  public void onFailure(Call<SignupResponseModel> call, Throwable t) {

                      loginView.onError(t.getMessage());
                      Log.e("error", String.valueOf(t));
                      dialog.dismiss();
                  }
              });

          }
          else{
              loginView.noInternet("login");
          }
    }
}
