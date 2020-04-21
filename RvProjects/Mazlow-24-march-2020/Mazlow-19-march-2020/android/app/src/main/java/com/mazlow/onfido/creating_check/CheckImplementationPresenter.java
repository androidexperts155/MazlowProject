package com.mazlow.onfido.creating_check;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.mazlow.Networking.OnFidoClient;
import com.mazlow.Networking.RestApiInterface;
import com.mazlow.customclasses.M;
import com.mazlow.onfido.model.OnFidoResponseModel;
import com.mazlow.onfido.verification_identity.ConductingVerificationActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CheckImplementationPresenter implements OnChechPresenter {


    Context context;
    CreatingCheckview creatingCheckview;

    public CheckImplementationPresenter(ConductingVerificationActivity conductingVerificationActivity, ConductingVerificationActivity conductingVerificationActivity1) {

        context = conductingVerificationActivity;
        creatingCheckview = conductingVerificationActivity;
    }


    @Override
    public void doCheck(String token, String id, String type, JSONObject jsonArray) {

        if (M.isNetworkAvailable(context)) {

               String BASE_URL = "https://api.onfido.com/v2/applicants/"+id+"/";

                HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
                logging.setLevel(HttpLoggingInterceptor.Level.BODY);

                OkHttpClient okHttpClient = new OkHttpClient.Builder()
                        .readTimeout(60 * 2000, TimeUnit.MILLISECONDS).addInterceptor(logging)
                        .build();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient)
                        .build();



            RestApiInterface apiInterface = retrofit.create(RestApiInterface.class);
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, jsonArray.toString());
            Call<String> call = apiInterface.doCheck(token, "application/json",body);
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.isSuccessful()) {

                        String geresponse= response.body().toString();

                        Log.e("response",""+geresponse);

                }
            }
                @Override
                public void onFailure(Call<String> call, Throwable t) {


                }
            });
        }
        else
            {

            }


    }
}
