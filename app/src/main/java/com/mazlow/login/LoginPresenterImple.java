package com.mazlow.login;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import com.mazlow.Networking.RestApiClientAuth;
import com.mazlow.Networking.RestApiInterface;
import com.mazlow.customclasses.M;
import com.mazlow.login.model.LoginResponseModel;
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
              Call<LoginResponseModel> call = apiInterface.login(phonenumber,ccode,pcode);
              call.enqueue(new Callback<LoginResponseModel>() {
                  @Override
                  public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                      dialog.dismiss();
                      if (response.isSuccessful()) {
                          LoginResponseModel LoginResponseModel=response.body();
                          if (response!=null&&response.body().getSuccess()==true) {
                              if (response.body().getMessage().equals("User logIn successfully")){
                                      loginView.onSuccess(LoginResponseModel);
                              }
                              else
                                  {
                                  loginView.onError(LoginResponseModel.getMessage());
                              }
                          }
                          else
                          {
                              loginView.onError(LoginResponseModel.getMessage());
                          }
                      }
                  }
                  @Override
                  public void onFailure(Call<LoginResponseModel> call, Throwable t) {

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

    @Override
    public void doLoginWithPhone(String phonenumber, String ccode) {
        if (M.isNetworkAvailable(context)){
            dialog.show();
            RestApiInterface apiInterface = RestApiClientAuth.Retrofit().create(RestApiInterface.class);
            Call<LoginResponseModel> call = apiInterface.loginwithphone(phonenumber,ccode);
            call.enqueue(new Callback<LoginResponseModel>() {
                @Override
                public void onResponse(Call<LoginResponseModel> call, Response<LoginResponseModel> response) {
                    dialog.dismiss();
                    if (response.isSuccessful()) {
                        LoginResponseModel LoginResponseModel=response.body();
                        if (response!=null&&response.body().getSuccess()==true) {
                            if (response.body().getMessage().equals("User logIn successfully")){
                                loginView.onSuccess(LoginResponseModel);
                            }
                            else
                            {
                                loginView.onError(LoginResponseModel.getMessage());
                            }
                        }
                        else
                        {
                            loginView.onError(LoginResponseModel.getMessage());
                        }
                    }
                }
                @Override
                public void onFailure(Call<LoginResponseModel> call, Throwable t) {

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
