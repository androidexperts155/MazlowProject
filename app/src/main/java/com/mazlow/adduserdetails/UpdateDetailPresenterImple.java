package com.mazlow.adduserdetails;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import com.mazlow.Networking.RestApiClientAuth;
import com.mazlow.Networking.RestApiInterface;
import com.mazlow.adduserdetails.model.UpdateUserDetails;
import com.mazlow.customclasses.M;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateDetailPresenterImple implements UpdateDetailPresenter {

    Context context;
    Dialog dialog;
    AddDetailsView addDetailsView;

    public UpdateDetailPresenterImple(AddDetailsView addDetailsView, Context activity1) {
        this.context= activity1;
        this.addDetailsView = addDetailsView;
        dialog = M.showloader(context, "", false, false);

    }
    @Override
    public void doUpdate(String accesstoken ,String firstname, String lastname, String dateofbirth, String emailaddress, String country, String postalcode, String Address, String Address2, String City,String Devectoken,String devicetype) {

        if (M.isNetworkAvailable(context)){
            dialog.show();
            RestApiInterface apiInterface = RestApiClientAuth.Retrofit().create(RestApiInterface.class);
            Call<UpdateUserDetails> call = apiInterface.updateDetails(accesstoken,firstname,lastname,dateofbirth,emailaddress,country,postalcode,Address,Address2,City,Devectoken ,devicetype);
            call.enqueue(new Callback<UpdateUserDetails>() {
                @Override
                public void onResponse(Call<UpdateUserDetails> call, Response<UpdateUserDetails> response) {
                    dialog.dismiss();
                    if (response.isSuccessful()) {
                        UpdateUserDetails signupResponseModel=response.body();
                        if (response!=null&&response.body().getSuccess()==true) {
                            if (response.body().getMessage().equals("User updated successfully")){
                                addDetailsView.onSucess(signupResponseModel);
                            }
                            else
                            {
                                addDetailsView.onError(signupResponseModel.getMessage());
                            }
                        }
                        else
                        {
                            addDetailsView.onError(signupResponseModel.getMessage());
                        }
                    }
                }
                @Override
                public void onFailure(Call<UpdateUserDetails> call, Throwable t) {

                    addDetailsView.onError(t.getMessage());
                    Log.e("error", String.valueOf(t));
                    dialog.dismiss();
                }
            });

        }
        else{
            addDetailsView.noInternet("login");
        }
    }
}


