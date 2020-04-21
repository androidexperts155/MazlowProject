package com.mazlow.search_address;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import com.mazlow.Networking.RestApiClientAuth;
import com.mazlow.Networking.RestApiInterface;
import com.mazlow.customclasses.M;
import com.mazlow.search_address.model.AddressZipcodeResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchPresentermple implements SearchAddressPresenter{

    Context context;
    Dialog dialog;
    SearchAddressView searchAddressView;

    public SearchPresentermple(SearchAddressView searchAddressView, Context context) {

        this.context=context;
        this.searchAddressView = searchAddressView;
        dialog = M.showloader(context, "", false, false);

    }

    @Override
    public void dosearch(String accesstoken, String zipcode) {
        if (M.isNetworkAvailable(context)){
            dialog.show();
            RestApiInterface apiInterface = RestApiClientAuth.Retrofit().create(RestApiInterface.class);
            Call<AddressZipcodeResponse> call = apiInterface.searchAddresszipcode(accesstoken,zipcode);
            call.enqueue(new Callback<AddressZipcodeResponse>() {
                @Override
                public void onResponse(Call<AddressZipcodeResponse> call, Response<AddressZipcodeResponse> response) {
                    dialog.dismiss();
                    if (response.isSuccessful()) {
                        AddressZipcodeResponse addressZipcodeResponse=response.body();
                        if (response!=null&&response.body().getSuccess()==true)
                            {
                                searchAddressView.onSucess(addressZipcodeResponse);
                            }
                            else
                            {
                                searchAddressView.onError("Something is Wrong");
                            }
                        }
                        else
                        {
                            searchAddressView.onError("Something is Wrong");
                        }

                }
                @Override
                public void onFailure(Call<AddressZipcodeResponse> call, Throwable t) {

                    searchAddressView.onError(t.getMessage());
                    Log.e("error", String.valueOf(t));
                    dialog.dismiss();
                }
            });

        }
        else{
            searchAddressView.noInternet("login");
        }
    }
    }
