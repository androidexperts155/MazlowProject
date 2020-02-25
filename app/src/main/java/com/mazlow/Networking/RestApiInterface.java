package com.mazlow.Networking;

import com.mazlow.adduserdetails.model.UpdateUserDetails;
import com.mazlow.login.model.LoginResponseModel;
import com.mazlow.otp.models.CheckOtpResponseModel;
import com.mazlow.search_address.model.AddressZipcodeResponse;
import com.mazlow.signup.models.SignupResponseModel;
import com.mazlow.signup.postalcode.model.PostalCodeResponse;
import com.mazlow.ui.users.changephonenumber.model.ChangeNumberResponseModel;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

import static com.mazlow.Networking.Constants.Signup;
import static com.mazlow.Networking.Constants.checkotp;
import static com.mazlow.Networking.Constants.chengenumber;
import static com.mazlow.Networking.Constants.login;
import static com.mazlow.Networking.Constants.postalcode;
import static com.mazlow.Networking.Constants.resenotp;
import static com.mazlow.Networking.Constants.update_profile;
import static com.mazlow.Networking.Constants.zipcodeaddress;


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

    //login
    @FormUrlEncoded
    @POST(login)
    Call<LoginResponseModel> login(@Field("phoneNumber") String phone,
                                   @Field("countryCode") String countycode,
                                   @Field("passCode") String pin);

    @FormUrlEncoded
    @POST(update_profile)
    Call<SignupResponseModel> updatpasscode(@Header("Authorization") String phone,
                                           @Field("passCode") String pin);

    @FormUrlEncoded
    @POST(update_profile)
    Call<UpdateUserDetails> updateDetails(@Header("Authorization") String accesstoken,
                                          @Field("firstName") String firstname,
                                          @Field("lastName") String lastname,
                                          @Field("dob")  String dateofbirth,
                                          @Field("email") String emailaddress,
                                          @Field("country") String country,
                                          @Field("postalCode") String postalcode,
                                          @Field("address") String address,
                                          @Field("addressline2") String address2,
                                          @Field("city") String city,
                                          @Field("deviceToken") String token,
                                          @Field("deviceType") String deviceType);

    //login with phone
    @FormUrlEncoded
    @POST(login)
    Call<LoginResponseModel> loginwithphone(@Field("phoneNumber") String phone,
                                            @Field("countryCode") String countycode);
    @GET(zipcodeaddress)
    Call<AddressZipcodeResponse> searchAddresszipcode(@Header("Authorization")String accesstoken,
                                                      @Query("zipCode") String zipcode);

    //resend otp
    @FormUrlEncoded
    @POST(resenotp)
    Call<SignupResponseModel> resendotp(@Body JSONObject phone);

    //resend otp
    @FormUrlEncoded
    @POST(resenotp)
    Call<SignupResponseModel> resendotp(@Field("countryCode") String coutrycode,
                                        @Field("phoneNumber") String phone);

    @GET(postalcode)
    Call<PostalCodeResponse> postalcodeAddress(@Query("key") String phonenumber,
                                               @Query("postcode") String ccode);


    @FormUrlEncoded
    @POST(chengenumber)
    Call<ChangeNumberResponseModel> changeNumber(@Field("phoneNumber") String phonenumber,
                                                 @Field("countryCode") String countrycode);
}

