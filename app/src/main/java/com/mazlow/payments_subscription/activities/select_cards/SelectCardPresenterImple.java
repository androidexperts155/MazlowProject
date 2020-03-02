package com.mazlow.payments_subscription.activities.select_cards;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;

import com.mazlow.Networking.RestApiClientAuth;
import com.mazlow.Networking.RestApiInterface;
import com.mazlow.customclasses.M;
import com.mazlow.payments_subscription.models.CouponCodeDetailToServer;
import com.mazlow.payments_subscription.models.apply_coupon_code.ApplyCouponCodeResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectCardPresenterImple implements SelectCardPresenter {
    SelectCardView selectCardView;
    Context context;
    Dialog dialog;

    public SelectCardPresenterImple(SelectCardView selectCardView, Context context) {
        this.selectCardView = selectCardView;
        this.context = context;
        dialog = M.showloader(context, "", false, false);
    }

    @Override
    public void applyCoponCode(String token,String subscriptionId, String couponCode) {
        CouponCodeDetailToServer detailToServer=new CouponCodeDetailToServer();
        detailToServer.setDiscountCode(couponCode);
        detailToServer.setSubscriptionId(subscriptionId);

        if (M.isNetworkAvailable(context)){
            dialog.show();
            RestApiInterface apiInterface = RestApiClientAuth.Retrofit().create(RestApiInterface.class);
            Call<ApplyCouponCodeResponse> call = apiInterface.applyCouponCode(token,detailToServer);
            call.enqueue(new Callback<ApplyCouponCodeResponse>() {
                @Override
                public void onResponse(Call<ApplyCouponCodeResponse> call, Response<ApplyCouponCodeResponse> response) {
                    dialog.dismiss();
                    if (response.isSuccessful()) {
                        ApplyCouponCodeResponse applyCouponCodeResponse=response.body();
                        if (response!=null&&response.body().getSuccess()==true) {
                                selectCardView.onSuccess(applyCouponCodeResponse);
                        }
                        else
                        {
                            selectCardView.onError(applyCouponCodeResponse.getMessage());
                        }
                    }
                }
                @Override
                public void onFailure(Call<ApplyCouponCodeResponse> call, Throwable t) {

                    selectCardView.onError(t.getMessage());
                    Log.e("error", String.valueOf(t));
                    dialog.dismiss();
                }
            });

        }
        else{
            selectCardView.noInternet("login");
        }
    }


}
