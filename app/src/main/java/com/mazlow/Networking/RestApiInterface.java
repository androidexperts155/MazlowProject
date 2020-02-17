package com.mazlow.Networking;

import com.mazlow.otp.models.CheckOtpResponseModel;
import com.mazlow.signup.models.SignupResponseModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

import static com.mazlow.Networking.Constants.Signup;
import static com.mazlow.Networking.Constants.checkotp;
import static com.mazlow.Networking.Constants.login;
import static com.mazlow.Networking.Constants.update_profile;


public interface RestApiInterface {


    //Signup
    @FormUrlEncoded
    @POST(Signup)
    Call<SignupResponseModel> signup(@Field("phoneNumber") String device_type,
                                     @Field("countryCode") String email);

    //verify otp
    @FormUrlEncoded
    @POST(checkotp)
    Call<CheckOtpResponseModel> verifyOtp(@Field("phoneNumber") String getphoenumber,
                                          @Field("otpCode") String mOtp);

    //verify otp
    @FormUrlEncoded
    @POST(login)
    Call<SignupResponseModel> login(@Field("phoneNumber") String phone,
                                  @Field("countryCode") String countycode,
                                  @Field("passCode") String pin);

    @FormUrlEncoded
    @POST(update_profile)
    Call<SignupResponseModel> updatpasscode(@Header("Authorization") String phone,
                                           @Field("passCode") String pin);
}


