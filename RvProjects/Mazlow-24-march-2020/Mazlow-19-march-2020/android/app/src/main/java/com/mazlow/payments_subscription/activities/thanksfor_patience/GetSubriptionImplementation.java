package com.mazlow.payments_subscription.activities.thanksfor_patience;
import android.content.Context;
import android.util.Log;
import com.mazlow.Networking.RestApiClientAuth;
import com.mazlow.Networking.RestApiInterface;
import com.mazlow.customclasses.M;
import com.mazlow.payments_subscription.activities.thanksfor_patience.model.SubcriptionResponsemodel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GetSubriptionImplementation implements SubcriptionPresenter {

    Context context;
    GetSubcriptionView subcriptionPresenter;

    public GetSubriptionImplementation(Context context, GetSubcriptionView subcriptionPresenter) {
        this.context = context;
        this.subcriptionPresenter = subcriptionPresenter;
    }

    @Override
    public void getsubCription(String token) {

        if (M.isNetworkAvailable(context)){
            RestApiInterface apiInterface = RestApiClientAuth.Retrofit().create(RestApiInterface.class);
            Call<SubcriptionResponsemodel> call = apiInterface.getSubcription(token);
            call.enqueue(new Callback<SubcriptionResponsemodel>() {
                @Override
                public void onResponse(Call<SubcriptionResponsemodel> call, Response<SubcriptionResponsemodel> response) {

                    SubcriptionResponsemodel SubcriptionResponsemodel = response.body();

                    if (response.isSuccessful()) {

                        subcriptionPresenter.onSuccessSubcription(SubcriptionResponsemodel);
                    }
                    else
                    {
                        subcriptionPresenter.onErrorSubcription(SubcriptionResponsemodel.getMessage());
                    }
                }

                @Override
                public void onFailure(Call<SubcriptionResponsemodel> call, Throwable t) {

                    //  addDetailsView.onError(t.getMessage());
                    Log.e("error", String.valueOf(t));

                }
            });

        }
        else{
            subcriptionPresenter.noInternetSubcription("login");
        }
    }
}
